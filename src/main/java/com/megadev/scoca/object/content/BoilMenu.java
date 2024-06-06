package com.megadev.scoca.object.content;

import com.megadev.scoca.manager.BoilManager;
import com.megadev.scoca.manager.ItemManager;
import com.megadev.scoca.object.block.PluginBlock;
import com.megadev.scoca.object.boil.BoilState;
import com.megadev.scoca.object.boil.FurnaceState;
import com.megadev.scoca.object.item.PluginStack;
import com.megadev.scoca.object.menu.MenuState;
import com.megadev.scoca.util.MenuStateUtil;
import com.megadev.scoca.util.PluginStackFactory;
import dev.mega.megacore.inventory.MegaInventory;
import dev.mega.megacore.util.MegaCoreUtil;
import lombok.Getter;
import org.bukkit.Location;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import java.util.Objects;
import java.util.UUID;

@Getter
public class BoilMenu extends MegaInventory {
    private final PluginBlock pluginBlock;
    private final int size;

    public BoilMenu(PluginBlock pluginBlock, int size, String title) {
        super(size, title);
        this.pluginBlock = pluginBlock;
        this.size = size;
    }

    public static BoilMenu of(MenuState menuState, Location location) {
        return of(menuState, new PluginBlock(null, location));
    }

    public static BoilMenu of(MenuState menuState, PluginBlock pluginBlock) {
        BoilMenu boilMenu = new BoilMenu(pluginBlock, MenuStateUtil.getSize(menuState), menuState.title());
        menuState.slots().forEach(slot -> boilMenu.setItem(slot.index(), slot.slotPattern().pluginStack().getItemStack()));

        return boilMenu;
    }

    @Override
    public void onClick(InventoryClickEvent event) {
        UUID uuid = event.getWhoClicked().getUniqueId();

        ItemStack itemStack = event.getCurrentItem();

        boolean isMenu = event.getRawSlot() == event.getSlot();

        BoilState boilState = BoilManager.getInstance().getBoilState(uuid, new PluginBlock(pluginBlock.getPluginStack(), pluginBlock.getLocation()));

        if (isMenu) {
            PluginStack pluginStack = boilState.claimItem(event.getSlot());
            if (pluginStack != null) {
                event.getWhoClicked().getInventory().addItem(pluginStack.getItemStack());
                ItemManager.getInstance().addItem(uuid, pluginStack);
            }

            System.out.println(pluginBlock.getLocation());
        } else {
            if (itemStack != null) {
                if (!boilState.injectItem(PluginStackFactory.getPluginStack(itemStack.clone()))) {
                    MegaCoreUtil.getLogger().info("Can't inject item: " + pluginBlock.getPluginStack().getItemStack());
                }
                itemStack.setAmount(0);
            }

        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BoilMenu that = (BoilMenu) o;
        return Objects.equals(getPluginBlock(), that.getPluginBlock());
    }
}
