package com.megadev.scoca.object.boil;

import com.megadev.scoca.config.BoilConfig;
import com.megadev.scoca.config.ConfigManager;
import com.megadev.scoca.config.ItemsConfig;
import com.megadev.scoca.config.animation.AnimationConfig;
import com.megadev.scoca.config.animation.AnimationConfigManager;
import com.megadev.scoca.object.animation.Animation;
import com.megadev.scoca.object.block.PluginBlock;
import com.megadev.scoca.object.content.Ingredient;
import com.megadev.scoca.object.item.Item;
import com.megadev.scoca.object.item.PluginStack;
import com.megadev.scoca.util.MetaUtil;
import dev.mega.megacore.MegaCore;
import dev.mega.megacore.util.MegaCoreUtil;
import lombok.Getter;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;
import org.jetbrains.annotations.Nullable;

@Getter
public class FurnaceState extends BoilState {
    private boolean hasFuelCapacity = false;

    // 0: fuel
    // 1: sulfur -> salt
    // 2: herb -> nothing
    // 3: meph

    public FurnaceState(MegaCore megaCore, PluginBlock pluginBlock) {
        super(megaCore, pluginBlock);

        startLifecycle();
    }

    @Override
    protected void executeLifecycle() {
        BukkitRunnable task = new BukkitRunnable() {
            @Override
            public void run() {
                if (ingredientMap.isEmpty()) return;

                if (ingredientMap.get(0) != null &&
                        (ingredientMap.get(1) != null || ingredientMap.get(2) != null)) {
                    handleFirstIngredient();
                }
            }
        };
        task.runTaskTimer(megaCore, 0, 20);
    }

    @Override
    public @Nullable PluginStack claimAllItems(int slot) {
        ItemStack itemStack = animationInterpreter.getBoilMenu().getInventory().getItem(slot);
        return null;
    }

    @Override
    public int getIngredientIndex(Ingredient ingredient) {
        return switch (ingredient) {
            case FUEL -> 0;
            case SULFUR -> 1;
            case HERB -> 2;
            case MEPH -> 3;
            default -> -1;
        };
    }

    @Override
    public Ingredient getIngredientByIndex(int index) {
        return switch (index) {
            case 0 -> Ingredient.FUEL;
            case 1 -> Ingredient.SULFUR;
            case 2 -> Ingredient.HERB;
            case 3 -> Ingredient.MEPH;
            default -> null;
        };
    }

    private void handleFirstIngredient() {
        ItemStack itemStack = ingredientMap.get(0).getItemStack();

        if (itemStack == null || itemStack.getType() == Material.AIR)
            return;

        String fuelCapacityString = MetaUtil.getItemMeta(itemStack, "fuel-capacity");

        MegaCoreUtil.getLogger().info("fuelCapacity IS "+ fuelCapacityString);

        int fuelCapacity = Integer.parseInt(fuelCapacityString);

        fuelCapacity--;
        megaCore.getLogger().info(String.valueOf(fuelCapacity));

        MetaUtil.setItemMeta(itemStack, "fuel-capacity", String.valueOf(fuelCapacity));
        hasFuelCapacity = true;

        if (ingredientMap.get(2) != null) {
            if (ingredientMap.get(1) != null) boilMephAndSalt();
            else boilMeph();
        } else boilSalt();

        if (fuelCapacity <= 0) {
            ingredientMap.get(0).getItemStack().setAmount(itemStack.getAmount() - 1);
            ingredientMap.remove(0);
            hasFuelCapacity = false;
        }
    }

    public void addFuel(PluginStack pluginStack) {
        PluginStack fuel = ingredientMap.get(0);
        if (fuel != null) {
            ItemStack fuelStack = fuel.getItemStack();
            if (fuelStack.getAmount() >= 64) return;
            fuelStack.setAmount(fuelStack.getAmount() + pluginStack.getItemStack().getAmount());

        } else {
            String fuelCapacityString = MetaUtil.getItemMeta(pluginStack.getItemMeta(), "fuel-capacity");
            if (fuelCapacityString  == null) {
                BoilConfig boilConfig = ConfigManager.getInstance().getConfig(BoilConfig.class);

                MetaUtil.setItemMeta(pluginStack.getItemStack(), "fuel-capacity", String.valueOf(boilConfig.getFuelCapacitySeconds()));
            }
            ingredientMap.put(0, pluginStack);
        }
    }

    protected void boilMeph() {
        boilIngredient(Ingredient.MEPH, 0, 2, 3, Stage.HERB_TO_MEPH.getConfigPath());
    }

    protected void boilSalt() {
        boilIngredient(Ingredient.SALT, 1, 1, 1, Stage.SULFUR_TO_SALT.getConfigPath());
    }

    protected void boilMephAndSalt() {
        boilMeph();
        boilSalt();
    }

    protected void boilIngredient(Ingredient ingredient, int fuelIndex, int rawIndex, int resultIndex, String animationPath) {
        if (ingredientMap.get(fuelIndex) == null) return;

        AnimationConfigManager animationConfigManager = ConfigManager.getInstance().getConfig(AnimationConfigManager.class);
        AnimationConfig animationConfig = animationConfigManager.getAnimationConfig(animationPath);

        int animationDelay = animationConfig.getAnimation().steps()
                .stream()
                .filter(step -> step.command().equals(Animation.Command.DELAY))
                .map(step -> Integer.valueOf(step.value()))
                .mapToInt(i -> i)
                .sum();

        Bukkit.getScheduler().runTaskLater(megaCore, () -> {
            ItemsConfig itemsConfig = ConfigManager.getInstance().getConfig(ItemsConfig.class);
            Item meph = itemsConfig.getItem(ingredient);
            PluginStack mephStack = ingredientMap.get(3);

            if (ingredientMap.get(rawIndex) == null || (mephStack != null && mephStack.getItemStack().getAmount() >= 64))
                return;

            ItemStack raw = ingredientMap.get(rawIndex).getItemStack();
            raw.setAmount(raw.getAmount() - 1);

            if (raw.getAmount() <= 0)
                ingredientMap.remove(rawIndex);

            if (mephStack == null) {
                ingredientMap.put(resultIndex, meph.pluginStack());
            } else {
                mephStack.getItemStack().setAmount(mephStack.getItemStack().getAmount() + 1);
            }
        }, animationDelay);
    }

    @Override
    public BoilBlock getBoilBlock() {
        return BoilBlock.FURNACE;
    }
}
