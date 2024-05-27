package com.megadev.scoca.object.boil;

import com.megadev.scoca.object.block.PluginBlock;
import com.megadev.scoca.object.content.Ingredient;
import com.megadev.scoca.object.item.PluginStack;
import com.megadev.scoca.util.MetaUtil;
import dev.mega.megacore.MegaCore;
import lombok.Getter;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.stream.IntStream;

@Getter
public class FurnaceState extends BoilState {
    private boolean hasFuelCapacity = false;

    // 1: fuel
    // 2: sulfur -> salt
    // 3: herb
    // 4: meph


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

                MegaCore.getInstance().getLogger().info(ingredientMap + "");
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

    private void handleFirstIngredient() {
        ItemStack itemStack = ingredientMap.get(0).getItemStack();

        if (itemStack == null || itemStack.getType() == Material.AIR)
            return;

        int fuelCapacity = Integer.parseInt(Objects.requireNonNull(
                MetaUtil.getItemMeta(itemStack, "fuel-capacity")));

        fuelCapacity--;
        MegaCore.getInstance().getLogger().info(String.valueOf(fuelCapacity));

        MetaUtil.setItemMeta(itemStack, "fuel-capacity", String.valueOf(fuelCapacity));
        hasFuelCapacity = true;

        if (fuelCapacity == 0) {
            ingredientMap.get(0).getItemStack().setAmount(itemStack.getAmount() - 1);
            ingredientMap.put(0, null);
            hasFuelCapacity = false;
        }
    }

    public void addFuel(PluginStack pluginStack) {
        if (ingredientMap.get(0) != null) {
            ingredientMap.get(0).getItemStack().setAmount(
                    ingredientMap.get(0).getItemStack().getAmount() + pluginStack.getItemStack().getAmount());
        } else {
            ingredientMap.put(0, pluginStack);
        }
    }

    @Override
    public BoilBlock getBoilBlock() {
        return BoilBlock.FURNACE;
    }
}
