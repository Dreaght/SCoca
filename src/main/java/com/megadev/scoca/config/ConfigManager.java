package com.megadev.scoca.config;

import com.megadev.scoca.config.animation.AnimationManager;
import dev.mega.megacore.config.Config;
import dev.mega.megacore.config.Manager;
import org.bukkit.plugin.Plugin;

/**
 * Represents the general config manager.
 */
public class ConfigManager extends Manager {
    private static volatile ConfigManager instance;

    private ConfigManager(Plugin plugin) {
        super(plugin, ".");

        addConfigSafely(AnimationManager.class, new AnimationManager(plugin, "animation"));

        addConfigSafely(SettingsConfig.class, new SettingsConfig(plugin, "config"));
        addConfigSafely(MenuConfig.class, new MenuConfig(plugin, "menu"));
        addConfigSafely(BoilConfig.class, new BoilConfig(plugin, "boil"));
        addConfigSafely(ItemsConfig.class, new ItemsConfig(plugin, "items"));
        addConfigSafely(MessagesConfig.class, new MessagesConfig(plugin, "messages"));
    }

    /**
     * Gets ConfigManager instance.
     * @return ConfigManager instance.
     */
    public static synchronized ConfigManager getInstance() {
        if (instance == null) {
            throw new IllegalStateException("ConfigManager has not been initialized. Call init() first.");
        }
        return instance;
    }

    /**
     * Initializes the ConfigManager instance.
     * @param plugin This plugin.
     */
    public static void init(Plugin plugin) {
        if (instance == null) {
            synchronized (ConfigManager.class) {
                if (instance == null) {
                    instance = new ConfigManager(plugin);
                }
            }
        }
    }

    /**
     * Adds the Config object with asynchronous safely access.
     * @param configClass Class of the Config
     * @param config Config object
     */
    public synchronized <T> void addConfigSafely(Class<? extends Config> configClass, Config config) {
        addConfig(configClass, config);
    }
}
