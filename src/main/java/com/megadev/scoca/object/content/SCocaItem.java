package com.megadev.scoca.object.content;

import com.megadev.scoca.object.item.PluginStack;
import lombok.Getter;

import java.util.Objects;
import java.util.UUID;

@Getter
public class SCocaItem {
    private final UUID uuid;
    private final PluginStack pluginStack;

    public SCocaItem(UUID uuid, PluginStack pluginStack) {
        this.uuid = uuid;
        this.pluginStack = pluginStack;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SCocaItem that = (SCocaItem) o;

        return Objects.equals(pluginStack, that.getPluginStack());
    }
}
