package com.megadev.scoca.config.animation.menu.furnace;

import com.megadev.scoca.config.Manager;
import org.bukkit.plugin.Plugin;

public class FurnaceManager extends Manager {
    public FurnaceManager(Plugin plugin, String dataFolder) {
        super(plugin, dataFolder);

        addConfig(DefaultConfig.class, new DefaultConfig(plugin, dataFolder, "default"));
    }
}
