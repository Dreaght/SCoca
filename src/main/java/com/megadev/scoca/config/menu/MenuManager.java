package com.megadev.scoca.config.menu;

import com.megadev.scoca.config.Manager;
import com.megadev.scoca.config.menu.boiler.BoilerManager;
import com.megadev.scoca.config.menu.furnace.FurnaceManager;
import org.bukkit.plugin.Plugin;

public class MenuManager extends Manager {
    public MenuManager(Plugin plugin, String dataFolder) {
        super(plugin, dataFolder);

        addConfig(FurnaceManager.class, new FurnaceManager(plugin, dataFolder + "/furnace"));
        addConfig(BoilerManager.class, new BoilerManager(plugin, dataFolder + "/boiler"));
    }
}
