package com.megadev.scoca.config.animation.menu.furnace;

import dev.mega.megacore.config.AbstractManager;
import org.bukkit.plugin.Plugin;

public class FurnaceManager extends AbstractManager {
    public FurnaceManager(Plugin plugin, String dataFolder) {
        super(plugin, dataFolder);

        addConfig(DefaultConfig.class, new DefaultConfig(plugin, dataFolder, "default"));
    }
}
