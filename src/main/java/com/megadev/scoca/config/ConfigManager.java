package com.megadev.scoca.config;

import com.megadev.scoca.config.menu.MenuManager;
import lombok.Getter;
import org.bukkit.plugin.Plugin;

public class ConfigManager extends Manager {
    @Getter private static ConfigManager instance;

    private ConfigManager(Plugin plugin) {
        super(plugin, ".");

        addConfig(MenuManager.class, new MenuManager(plugin, "menu"));

        addConfig(SettingsConfig.class, new SettingsConfig(plugin, "settings"));
        addConfig(MenuConfig.class, new MenuConfig(plugin, "menu"));
        addConfig(BoilConfig.class, new BoilConfig(plugin, "boil"));
        addConfig(ItemsConfig.class, new ItemsConfig(plugin, "items"));
        addConfig(MessagesConfig.class, new MessagesConfig(plugin, "message"));
    }

    public static void init(Plugin plugin) {
        if (instance == null) {
            instance = new ConfigManager(plugin);
        }
    }
}
