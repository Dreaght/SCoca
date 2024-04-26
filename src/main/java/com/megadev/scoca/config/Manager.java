package com.megadev.scoca.config;

import lombok.Getter;
import org.bukkit.plugin.Plugin;

import java.util.HashMap;
import java.util.Map;

/**
 * Manager object to handle Config objects
 */
public abstract class Manager implements Config {
    @Getter private final Plugin plugin;
    @Getter private final String dataFolder;
    private final Map<Class<? extends Config>, Config> configMap = new HashMap<>();

    public Manager(Plugin plugin, String dataFolder) {
        this.plugin = plugin;
        this.dataFolder = dataFolder;
    }

    public void addConfig(Class<? extends Config> configClass, Config config) {
        configMap.put(configClass, config);
    }

    public <T extends Config> T getConfig(Class<T> configClass) {
        T config = getConfigFromMap(configClass);
        if (config != null) return config;

        for (Config managerConfig : configMap.values()) {
            if (managerConfig instanceof Manager) {
                config = ((Manager) managerConfig).getConfig(configClass);
                if (config != null) return config;
            }
        }

        throw new IllegalArgumentException("No configuration found for class: " + configClass.getName());
    }

    private <T extends Config> T getConfigFromMap(Class<T> configClass) {
        Config config = configMap.get(configClass);
        if (config instanceof Manager) return ((Manager) config).getConfig(configClass);
        else return configClass.cast(config);
    }
}
