package com.megadev.scoca.listener;

import com.megadev.scoca.manager.BlockManager;
import com.megadev.scoca.manager.ItemManager;
import com.megadev.scoca.object.block.PluginBlock;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.ItemStack;

import java.util.UUID;

public class BlockListener implements Listener {
    @EventHandler
    public void onBlockPlace(BlockPlaceEvent event) {
        ItemStack itemStack = event.getItemInHand();
//
//        if (ItemManager.getInstance())
//
////        UUID uuid = event.getPlayer().getUniqueId();
//
//
//
//        BlockManager.getInstance().addBlock();
    }

    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {

    }
}
