package com.megadev.scoca.object.boil;

import com.megadev.scoca.config.ConfigManager;
import com.megadev.scoca.config.MenuConfig;
import com.megadev.scoca.config.animation.AnimationConfigManager;
import com.megadev.scoca.object.animation.Animation;
import com.megadev.scoca.object.block.PluginBlock;
import com.megadev.scoca.object.content.Ingredient;
import com.megadev.scoca.object.item.PluginStack;
import com.megadev.scoca.object.menu.DefaultAnimationPath;
import com.megadev.scoca.manager.animation.AnimationInterpreter;
import com.megadev.scoca.util.MetaUtil;
import dev.mega.megacore.MegaCore;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.Nullable;

import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

@Getter
public abstract class BoilState {
    protected static final ConfigManager configManager = ConfigManager.getInstance();
    protected final MegaCore megaCore;
    protected final PluginBlock pluginBlock;
    @Setter protected AnimationInterpreter animationInterpreter;

    protected final Map<Integer, PluginStack> ingredientMap = new ConcurrentHashMap<>();

    public BoilState(MegaCore megaCore, PluginBlock pluginBlock) {
        this.megaCore = megaCore;
        this.pluginBlock = pluginBlock;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BoilState that = (BoilState) o;
        return Objects.equals(getPluginBlock(), that.getPluginBlock());
    }

    public void startLifecycle() {
        executeLifecycle();
    }

    protected abstract void executeLifecycle();

//    public ItemStack[] getFilledContents(ItemStack[] contents) {
//        return
//    }

    public void startDefaultAnimation() {
        Animation animation = getDefaultAnimation();
        animationInterpreter = new AnimationInterpreter(megaCore, pluginBlock, null, animation, ingredientMap);
        animationInterpreter.reload();
    }

    public void stopAnimation() {
        animationInterpreter.disable();
    }

    private Animation getDefaultAnimation() {
        MenuConfig menuConfig = configManager.getConfig(MenuConfig.class);
        DefaultAnimationPath defaultAnimationPath = DefaultAnimationPath.valueOf(getBoilBlock().name());

        String defaultAnimPath = menuConfig.getDefaultAnimationPath(defaultAnimationPath);

        AnimationConfigManager animationManager = configManager.getConfig(AnimationConfigManager.class);
        return animationManager.getAnimationConfig(defaultAnimPath).getAnimation();
    }

    public @Nullable PluginStack claimItem(int slot) {
        ItemStack itemStack = animationInterpreter.getBoilMenu().getInventory().getItem(slot);

        for (Map.Entry<Integer, PluginStack> entry : ingredientMap.entrySet()) {
            if (entry.getValue().getItemStack().equals(itemStack)) {
                PluginStack foundStack = entry.getValue();
                foundStack.getItemStack().setAmount(foundStack.getItemStack().getAmount() - 1);
                return foundStack;
            }
        }
        return null;
    }

    public @Nullable PluginStack claimAllItems(int slot) {
        return null;
    }

    public boolean injectItem(PluginStack pluginStack) {
        if (pluginStack.getItemStack() == null || !pluginStack.getItemStack().hasItemMeta()) return false;

        String content = MetaUtil.getItemMeta(pluginStack.getItemStack(), "content");
        Ingredient ingredient = Ingredient.getIngredient(content);

        if (ingredient == null
                || ingredient == Ingredient.MEPH
                || ingredient == Ingredient.SALT) {
            return false;
        }

        int ingredientIndex = getIngredientIndex(ingredient);
        if (ingredientIndex < 0) return false;

        int alreadyAmount = 0;
        if (ingredientMap.get(ingredientIndex) != null) {
            alreadyAmount = ingredientMap.get(ingredientIndex).getItemStack().getAmount();
        }
        if (ingredientMap.get(ingredientIndex) != null)
            if (ingredientMap.get(ingredientIndex).getItemStack().getAmount() >= 64) return false;

        int amountInjected = pluginStack.getItemStack().getAmount();
        int newAmount = (Math.min(64, alreadyAmount + amountInjected));
        int toAdd = newAmount - alreadyAmount;

        if (ingredientIndex == 0 && this instanceof FurnaceState) {
            ((FurnaceState) this).addFuel(pluginStack);
        } else {
            PluginStack ingredientPluginStack = ingredientMap.get(ingredientIndex);

            if (ingredientPluginStack == null) {
                ingredientMap.put(ingredientIndex, pluginStack);
            } else {
                ItemStack itemStack = ingredientMap.get(ingredientIndex).getItemStack();
                itemStack.setAmount(itemStack.getAmount() + toAdd);
            }
        }

        return true;
    }

    public abstract int getIngredientIndex(Ingredient ingredient);

    /**
     * Gets an ingredient by its index.
     */
    public abstract Ingredient getIngredientByIndex(int index);

    public abstract BoilBlock getBoilBlock();
}
