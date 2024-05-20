package com.megadev.scoca.object.block;

import com.megadev.scoca.object.item.PluginStack;
import lombok.Getter;
import org.bukkit.Location;

import java.util.Objects;

@Getter
public class PluginBlock {
    private final PluginStack pluginStack;
    private final Location location;

    public PluginBlock(PluginStack pluginStack, Location location) {
        this.pluginStack = pluginStack;
        this.location = location;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PluginBlock that = (PluginBlock) o;
        return Objects.equals(getLocation(), that.getLocation());
    }
}
