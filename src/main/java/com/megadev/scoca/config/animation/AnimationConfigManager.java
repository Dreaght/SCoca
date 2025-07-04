package com.megadev.scoca.config.animation;

import com.megadev.scoca.config.animation.particle.ParticleManager;
import com.megadev.scoca.config.animation.sound.SoundManager;
import com.megadev.scoca.config.animation.menu.MenuStateManager;
import dev.mega.megacore.config.SubFolder;
import org.bukkit.plugin.Plugin;

/**
 * Represents a manager that contains Config objects.
 */
public class AnimationConfigManager extends SubFolder {
    public AnimationConfigManager(Plugin plugin, String dataFolder) {
        super(plugin, dataFolder);

        addConfig(MenuStateManager.class, new MenuStateManager(plugin, dataFolder + "/menu"));
        addConfig(ParticleManager.class, new ParticleManager(plugin, dataFolder + "/particle"));
        addConfig(SoundManager.class, new SoundManager(plugin, dataFolder + "/sound"));
    }

    /**
     * Gets the animation config file.
     * @param configPath Configuration path.
     * @return The config.
     */
    public AnimationConfig getAnimationConfig(String configPath) {
        return new AnimationConfig(getPlugin(), configPath);
    }
}
