package com.megadev.scoca.config.animation.particle;

import com.megadev.scoca.config.Manager;
import org.bukkit.plugin.Plugin;

public class ParticleManager extends Manager {
    public ParticleManager(Plugin plugin, String dataFolder) {
        super(plugin, dataFolder);
    }

    public ParticleConfig getParticleConfig(String configName) {
        return new ParticleConfig(getPlugin(), getDataFolder(), configName);
    }
}
