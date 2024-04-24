package com.megadev.scoca.config.menu.boiler;

import com.megadev.scoca.config.Manager;
import org.bukkit.plugin.Plugin;

public class BoilerManager extends Manager {
    public BoilerManager(Plugin plugin, String dataFolder) {
        super(plugin, dataFolder);

        addConfig(DefaultConfig.class, new DefaultConfig(plugin, dataFolder, "default"));
    }
}
