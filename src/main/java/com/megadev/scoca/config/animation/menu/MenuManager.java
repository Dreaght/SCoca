package com.megadev.scoca.config.animation.menu;

import com.megadev.scoca.config.animation.menu.boiler.BoilerManager;
import com.megadev.scoca.config.animation.menu.furnace.FurnaceManager;
import dev.mega.megacore.config.AbstractManager;
import org.bukkit.plugin.Plugin;

public class MenuManager extends AbstractManager {
    public MenuManager(Plugin plugin, String dataFolder) {
        super(plugin, dataFolder);

        addConfig(FurnaceManager.class, new FurnaceManager(plugin, dataFolder + "/furnace"));
        addConfig(BoilerManager.class, new BoilerManager(plugin, dataFolder + "/boiler"));
    }
}
