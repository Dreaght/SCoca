package com.megadev.scoca.listener;

import com.megadev.scoca.manager.BlockManager;
import com.megadev.scoca.manager.ItemManager;
import com.megadev.scoca.object.block.PluginBlock;
import com.megadev.scoca.object.content.SCocaBlock;
import com.megadev.scoca.object.content.SCocaItem;
import com.megadev.scoca.object.item.PluginStack;
import com.megadev.scoca.util.MetaUtil;
import com.megadev.scoca.util.PluginStackFactory;
import org.bukkit.Location;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.ItemStack;

import java.util.UUID;

public class BlockListener implements Listener {
    @EventHandler
    public void onBlockPlace(BlockPlaceEvent event) {
        Location location = event.getBlock().getLocation();

        ItemManager itemManager = ItemManager.getInstance();

        UUID uuid = event.getPlayer().getUniqueId();
        ItemStack itemStack = event.getItemInHand().clone();
        itemStack.setAmount(1);

        PluginStack pluginStack = PluginStackFactory.getPluginStack(itemStack);

        SCocaItem sCocaItem = itemManager.validateAndGetSCocaItem(new SCocaItem(uuid, pluginStack));

        if (sCocaItem != null) {
            String content = MetaUtil.getItemMeta(itemStack, "content");
            if (content != null) {
                PluginBlock pluginBlock = new PluginBlock(pluginStack, location);

                BlockManager.getInstance().addBlock(uuid, new SCocaBlock(uuid, pluginBlock));
                ItemManager.getInstance().removeItem(sCocaItem);
            }
        }
    }

    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        Location location = event.getBlock().getLocation();
        UUID uuid = event.getPlayer().getUniqueId();

        PluginBlock pluginBlock = new PluginBlock(null, location);

        BlockManager.getInstance().removeBlock(uuid, new SCocaBlock(uuid, pluginBlock));
    }
}
