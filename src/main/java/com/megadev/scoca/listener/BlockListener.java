package com.megadev.scoca.listener;

import com.megadev.scoca.manager.BlockManager;
import com.megadev.scoca.manager.BoilManager;
import com.megadev.scoca.manager.ItemManager;
import com.megadev.scoca.object.block.PluginBlock;
import com.megadev.scoca.object.boil.BoilBlock;
import com.megadev.scoca.object.content.ContentBlock;
import com.megadev.scoca.object.content.SCocaBlock;
import com.megadev.scoca.object.content.SCocaItem;
import com.megadev.scoca.object.item.PluginStack;
import com.megadev.scoca.util.MetaUtil;
import com.megadev.scoca.util.PluginStackFactory;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;
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
                if (Arrays.stream(ContentBlock.values()).filter(contentBlock ->
                        contentBlock.getTitle().equals(content)).findAny().orElse(null) == null) {
                    event.getPlayer().sendMessage("You can't place an ingredient!");
                    event.setCancelled(true);
                    return;
                }

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

        SCocaBlock sCocaBlock = new SCocaBlock(uuid, pluginBlock);

        if (!BlockManager.getInstance().isRegistered(sCocaBlock)) return;

        BlockManager.getInstance().removeBlock(uuid, sCocaBlock);
        BoilManager.getInstance().removeBoilState(uuid, sCocaBlock);

        event.setDropItems(false);
    }
}
