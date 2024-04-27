package com.megadev.scoca.config.animation.sound;

import com.megadev.scoca.config.Manager;
import org.bukkit.plugin.Plugin;

/**
 * Represents a manager to access sound configs.
 */
public class SoundManager extends Manager {
    public SoundManager(Plugin plugin, String dataFolder) {
        super(plugin, dataFolder);
    }

    /**
     * Gets the Config object to access sound config file.
     * @param configName Configuration file name to get.
     * @return Config object.
     */
    public SoundConfig getSoundConfig(String configName) {
        return new SoundConfig(getPlugin(), getDataFolder(), configName);
    }
}
