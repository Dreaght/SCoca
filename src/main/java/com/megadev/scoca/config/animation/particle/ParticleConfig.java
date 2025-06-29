package com.megadev.scoca.config.animation.particle;

import dev.mega.megacore.config.Configurator;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;

/**
 * Represents particle config file access.
 */
public class ParticleConfig extends Configurator {
    /**
     * Constructs a Configurable object.
     *
     * @param plugin The plugin instance.
     * @param path   The path to the configuration file.
     */
    protected ParticleConfig(@NotNull Plugin plugin, String... path) {
        super(plugin, path);
    }
}
