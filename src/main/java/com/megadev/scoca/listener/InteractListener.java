package com.megadev.scoca.listener;

import com.megadev.scoca.manager.BlockManager;
import com.megadev.scoca.manager.ItemManager;
import com.megadev.scoca.manager.BoilMenuManager;
import com.megadev.scoca.object.block.PluginBlock;
import com.megadev.scoca.object.item.PluginStack;
import com.megadev.scoca.util.PluginStackFactory;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;

import java.util.UUID;

public class InteractListener implements Listener {
    private final static ItemManager itemManager = ItemManager.getInstance();
    private final static BlockManager blockManager = BlockManager.getInstance();

    @EventHandler
    public void onBlockClick(PlayerInteractEvent event) {
        if (event.getHand() == EquipmentSlot.OFF_HAND) {
            return;
        }

        Player player = event.getPlayer();

        if (!event.getAction().equals(Action.RIGHT_CLICK_BLOCK)) return;

        UUID uuid = player.getUniqueId();
        Block block = event.getClickedBlock();

        if (block != null) {
            PluginBlock pluginBlock = blockManager.validateAndGetPluginBlock(
                    uuid, new PluginBlock(null, block.getLocation()));

            BoilMenuManager.getInstance().openMenu(uuid, pluginBlock);
        }
    }

    @EventHandler
    public void onItemDrop(PlayerDropItemEvent event) {
        UUID uuid = event.getPlayer().getUniqueId();
        ItemStack itemStack = event.getItemDrop().getItemStack().clone();
        itemStack.setAmount(1);

        PluginStack pluginStack = PluginStackFactory.getPluginStack(itemStack);

        PluginStack sCocaItem = itemManager.validateAndGetSCocaItem(uuid, pluginStack);

        if (sCocaItem != null) {
            event.setCancelled(true);
        }
    }
}
