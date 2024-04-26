package com.megadev.scoca.object.block;

import com.megadev.scoca.object.item.PluginStack;
import org.bukkit.Location;

public interface PluginBlock {
    /**
     * @return PluginStack object
     */
    PluginStack getStack();

    /**
     * @return Block Location
     */
    Location getLocation();
}
