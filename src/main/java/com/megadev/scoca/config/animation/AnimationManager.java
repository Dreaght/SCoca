package com.megadev.scoca.config.animation;

import com.megadev.scoca.config.animation.particle.ParticleManager;
import com.megadev.scoca.config.animation.sound.SoundManager;
import com.megadev.scoca.config.animation.menu.MenuManager;
import dev.mega.megacore.config.Manager;
import org.bukkit.plugin.Plugin;

/**
 * Represents a manager that contains Config objects.
 */
public class AnimationManager extends Manager {
    public AnimationManager(Plugin plugin, String dataFolder) {
        super(plugin, dataFolder);

        addConfig(MenuManager.class, new MenuManager(plugin, dataFolder + "/menu"));
        addConfig(ParticleManager.class, new ParticleManager(plugin, dataFolder + "/particle"));
        addConfig(SoundManager.class, new SoundManager(plugin, dataFolder + "/sound"));
    }

    /**
     * Gets the animation config file.
     * @param configName Configuration file name to get.
     * @return The config.
     */
    public AnimationConfig getAnimationConfig(String configName) {
        return new AnimationConfig(getPlugin(), getDataFolder(), configName);
    }
}
