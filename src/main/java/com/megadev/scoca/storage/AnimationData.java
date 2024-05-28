package com.megadev.scoca.storage;

import com.megadev.scoca.manager.animation.AnimationInterpreter;
import com.megadev.scoca.object.block.PluginBlock;
import dev.mega.megacore.storage.ListData;

import java.util.UUID;

public class AnimationData extends ListData<AnimationInterpreter> {
    public AnimationInterpreter getAnimationInterpreter(UUID uuid, PluginBlock pluginBlock) {
        return contains(uuid) ? getValue(uuid).stream()
                .filter(animationInterpreter -> animationInterpreter.getPluginBlock().equals(pluginBlock))
                .findAny().orElse(null) : null;
    }
}
