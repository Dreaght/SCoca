package com.megadev.scoca.object.content;

import com.megadev.scoca.object.block.PluginBlock;
import com.megadev.scoca.util.MetaUtil;
import lombok.Getter;

import java.util.Objects;
import java.util.UUID;

@Getter
public class SCocaBlock {
    private final UUID uuid;
    private final PluginBlock pluginBlock;

    public SCocaBlock(UUID uuid, PluginBlock pluginBlock) {
        this.uuid = uuid;
        this.pluginBlock = pluginBlock;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SCocaBlock that = (SCocaBlock) o;

        return Objects.equals(pluginBlock, that.getPluginBlock());
    }

    public ContentStack getContentStack() {
        String content = MetaUtil.getItemMeta(pluginBlock.getPluginStack().getItemMeta(), "content");
        return ContentStack.getContentStack(content);
    }
}
