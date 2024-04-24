package com.megadev.scoca.config;

import com.megadev.scoca.config.menu.MenuManager;
import org.bukkit.plugin.Plugin;

public class ConfigManager extends Manager {
    private static volatile ConfigManager instance;

    private ConfigManager(Plugin plugin) {
        super(plugin, ".");
        addConfigSafely(MenuManager.class, new MenuManager(plugin, "menu"));
        addConfigSafely(SettingsConfig.class, new SettingsConfig(plugin, "config"));
        addConfigSafely(MenuConfig.class, new MenuConfig(plugin, "menu"));
        addConfigSafely(BoilConfig.class, new BoilConfig(plugin, "boil"));
        addConfigSafely(ItemsConfig.class, new ItemsConfig(plugin, "items"));
        addConfigSafely(MessagesConfig.class, new MessagesConfig(plugin, "message"));
    }

    public static synchronized ConfigManager getInstance() {
        if (instance == null) {
            throw new IllegalStateException("ConfigManager has not been initialized. Call init() first.");
        }
        return instance;
    }

    public static void init(Plugin plugin) {
        if (instance == null) {
            synchronized (ConfigManager.class) {
                if (instance == null) {
                    instance = new ConfigManager(plugin);
                }
            }
        }
    }

    public synchronized <T extends Config> void addConfigSafely(Class<T> configClass, T config) {
        addConfig(configClass, config);
    }
}
