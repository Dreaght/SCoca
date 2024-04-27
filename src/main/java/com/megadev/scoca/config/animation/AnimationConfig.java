package com.megadev.scoca.config.animation;

import com.megadev.scoca.config.Configurable;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;

/**
 * Represents an animation config file access.
 */
public class AnimationConfig extends Configurable {
    /**
     * Constructs a Configurable object.
     *
     * @param plugin The plugin instance.
     * @param path   The path to the configuration file.
     */
    protected AnimationConfig(@NotNull Plugin plugin, String... path) {
        super(plugin, path);
    }
}
