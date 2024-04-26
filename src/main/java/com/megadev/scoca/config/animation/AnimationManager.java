package com.megadev.scoca.config.animation;

import com.megadev.scoca.config.Manager;
import com.megadev.scoca.config.animation.particle.ParticleManager;
import com.megadev.scoca.config.animation.sound.SoundManager;
import com.megadev.scoca.config.animation.menu.MenuManager;
import org.bukkit.plugin.Plugin;

public class AnimationManager extends Manager {
    public AnimationManager(Plugin plugin, String dataFolder) {
        super(plugin, dataFolder);

        addConfig(MenuManager.class, new MenuManager(plugin, dataFolder + "/menu"));
        addConfig(ParticleManager.class, new ParticleManager(plugin, dataFolder + "/particle"));
        addConfig(SoundManager.class, new SoundManager(plugin, dataFolder + "/sound"));
    }

    public AnimationConfig getAnimationConfig(String configName) {
        return new AnimationConfig(getPlugin(), getDataFolder(), configName);
    }
}
