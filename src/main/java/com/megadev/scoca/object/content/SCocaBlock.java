package com.megadev.scoca.object.content;

import com.megadev.scoca.object.block.PluginBlock;
import com.megadev.scoca.object.item.ItemsAdderStack;
import lombok.Getter;

import java.util.Objects;
import java.util.UUID;

@Getter
public class SCocaBlock {
    private final UUID uuid;
    private final PluginBlock pluginBlock;
    private final ContentBlock contentBlock;

    public SCocaBlock(UUID uuid, PluginBlock pluginBlock, ContentBlock contentBlock) {
        this.uuid = uuid;
        this.pluginBlock = pluginBlock;
        this.contentBlock = contentBlock;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SCocaBlock that = (SCocaBlock) o;

        return Objects.equals(pluginBlock, that.getPluginBlock()) &
                Objects.equals(contentBlock, that.getContentBlock());
    }
}
