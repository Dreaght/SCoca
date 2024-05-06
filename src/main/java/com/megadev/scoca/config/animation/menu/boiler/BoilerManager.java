package com.megadev.scoca.config.animation.menu.boiler;

import dev.mega.megacore.config.Manager;
import org.bukkit.plugin.Plugin;

public class BoilerManager extends Manager {
    public BoilerManager(Plugin plugin, String dataFolder) {
        super(plugin, dataFolder);

        addConfig(DefaultConfig.class, new DefaultConfig(plugin, dataFolder, "default"));
    }
}
