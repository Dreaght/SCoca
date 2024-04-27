package com.megadev.scoca.config.animation.sound;

import com.megadev.scoca.config.Configurable;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;

/**
 * Represents sound config file access.
 */
public class SoundConfig extends Configurable {
    /**
     * Constructs a Configurable object.
     *
     * @param plugin The plugin instance.
     * @param path   The path to the configuration file.
     */
    protected SoundConfig(@NotNull Plugin plugin, String... path) {
        super(plugin, path);
    }
}
