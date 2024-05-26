package com.megadev.scoca.config.animation.menu;

import dev.mega.megacore.config.AbstractManager;
import org.bukkit.plugin.Plugin;

public class MenuStateManager extends AbstractManager {
    public MenuStateManager(Plugin plugin, String dataFolder) {
        super(plugin, dataFolder);
    }

    public MenuStateConfig getMenuStateConfig(String configPath) {
        return new MenuStateConfig(getPlugin(), configPath);
    }
}
