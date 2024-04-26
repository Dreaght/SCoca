package com.megadev.scoca.config.animation.sound;

import com.megadev.scoca.config.Manager;
import org.bukkit.plugin.Plugin;

public class SoundManager extends Manager {
    public SoundManager(Plugin plugin, String dataFolder) {
        super(plugin, dataFolder);
    }

    public SoundConfig getSoundConfig(String configName) {
        return new SoundConfig(getPlugin(), getDataFolder(), configName);
    }
}
