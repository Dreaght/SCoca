package com.megadev.scoca.listener;

import com.megadev.scoca.manager.BlockManager;
import com.megadev.scoca.manager.ItemManager;
import com.megadev.scoca.manager.MenuManager;
import com.megadev.scoca.object.block.PluginBlock;
import com.megadev.scoca.object.content.SCocaBlock;
import com.megadev.scoca.object.content.SCocaItem;
import com.megadev.scoca.object.item.PluginStack;
import com.megadev.scoca.util.PluginStackFactory;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import java.util.UUID;

public class InteractListener implements Listener {
    private final static ItemManager itemManager = ItemManager.getInstance();
    private final static BlockManager blockManager = BlockManager.getInstance();

    @EventHandler
    public void onBlockClick(PlayerInteractEvent event) {
        UUID uuid = event.getPlayer().getUniqueId();
        Block block = event.getClickedBlock();

        if (block != null) {
            PluginBlock pluginBlock = new PluginBlock(null, block.getLocation());
            SCocaBlock sCocaBlock = blockManager.validateAndGetSCocaBlock(new SCocaBlock(uuid, pluginBlock));

            MenuManager.getInstance().openMenu(uuid, sCocaBlock);
        }


    }

    @EventHandler
    public void onItemDrop(PlayerDropItemEvent event) {
        UUID uuid = event.getPlayer().getUniqueId();
        ItemStack itemStack = event.getItemDrop().getItemStack().clone();
        itemStack.setAmount(1);

        PluginStack pluginStack = PluginStackFactory.getPluginStack(itemStack);

        SCocaItem sCocaItem = itemManager.validateAndGetSCocaItem(new SCocaItem(uuid, pluginStack));

        if (sCocaItem != null) {
            event.setCancelled(true);
        }
    }
}
