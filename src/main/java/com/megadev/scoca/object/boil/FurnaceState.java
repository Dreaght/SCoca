package com.megadev.scoca.object.boil;

import com.megadev.scoca.object.block.PluginBlock;
import com.megadev.scoca.object.item.PluginStack;
import com.megadev.scoca.util.MetaUtil;
import dev.mega.megacore.MegaCore;
import lombok.Getter;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Objects;

@Getter
public class FurnaceState extends BoilState {
    private boolean hasFuelCapacity = false;
    private PluginStack firstIngredient; // fuel
    private PluginStack secondIngredient; // sulfur -> salt
    private PluginStack thirdIngredient; // herb
    private PluginStack fourthIngredient; // meph

    public FurnaceState(MegaCore megaCore, PluginBlock pluginBlock) {
        super(megaCore, pluginBlock);

        startLifecycle();
    }

    public void startLifecycle() {
//        BoilConfig boilConfig = configManager.getConfig(BoilConfig.class);
//        int fuelCapacitySeconds = boilConfig.getFuelCapacitySeconds();

        BukkitRunnable task = new BukkitRunnable() {
            @Override
            public void run() {
                if (firstIngredient != null &&
                        (secondIngredient != null || thirdIngredient != null)) {
                    handleFirstIngredient();
                }
            }
        };
        task.runTaskTimer(megaCore, 0, 20);
    }

    private void handleFirstIngredient() {
        ItemStack itemStack = firstIngredient.getItemStack();
        int fuelCapacity = Integer.parseInt(Objects.requireNonNull(
                MetaUtil.getItemMeta(itemStack, "fuelCapacity")));

        fuelCapacity--;

        MetaUtil.setItemMeta(itemStack, "fuelCapacity", String.valueOf(fuelCapacity));
        hasFuelCapacity = true;

        if (fuelCapacity == 0) {
            firstIngredient.getItemStack().setAmount(itemStack.getAmount() - 1);
            firstIngredient = null;
            hasFuelCapacity = false;
        }
    }

    public void addFuel(PluginStack pluginStack) {
        if (firstIngredient != null) {
            firstIngredient.getItemStack().setAmount(
                    firstIngredient.getItemStack().getAmount() + pluginStack.getItemStack().getAmount());
        } else {
            firstIngredient = pluginStack;
        }
    }

    @Override
    public BoilBlock getBoilBlock() {
        return BoilBlock.FURNACE;
    }
}
