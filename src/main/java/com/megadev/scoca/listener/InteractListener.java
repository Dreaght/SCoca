package com.megadev.scoca.listener;

import com.megadev.scoca.object.content.PluginBlock;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class InteractListener implements Listener {
    @EventHandler
    public void onBlockClick() {
        for (PluginBlock block : PluginBlock.values()) {

        }
    }
}
