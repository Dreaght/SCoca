package com.megadev.scoca.config.animation.particle;

import dev.mega.megacore.config.AbstractManager;
import org.bukkit.plugin.Plugin;

/**
 * Represents a manager to access particle configs.
 */
public class ParticleManager extends AbstractManager {
    public ParticleManager(Plugin plugin, String dataFolder) {
        super(plugin, dataFolder);
    }

    /**
     * Gets the particle Config access object.
     * @param configName Configuration file name to get.
     * @return The config access object.
     */
    public ParticleConfig getParticleConfig(String configName) {
        return new ParticleConfig(getPlugin(), getDataFolder(), configName);
    }
}
