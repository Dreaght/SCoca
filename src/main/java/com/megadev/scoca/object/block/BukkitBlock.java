package com.megadev.scoca.object.block;

import com.megadev.scoca.object.item.PluginStack;
import org.bukkit.Location;

import java.util.Objects;

public class BukkitBlock implements PluginBlock {
    private final PluginStack pluginStack;
    private final Location location;

    public BukkitBlock(PluginStack pluginStack, Location location) {
        this.pluginStack = pluginStack;
        this.location = location;
    }

    @Override
    public PluginStack getStack() {
        return pluginStack;
    }

    @Override
    public Location getLocation() {
        return location;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BukkitBlock that = (BukkitBlock) o;
        return Objects.equals(getLocation(), that.getLocation());
    }
}
