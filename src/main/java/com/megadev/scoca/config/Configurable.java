package com.megadev.scoca.config;

import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * Represents a configurable object that handles YAML configuration files.
 */
public abstract class Configurable implements Config {
    protected Plugin plugin;
    protected FileConfiguration config;
    protected File configFile;
    protected File parentFolder;

    protected Configurable(@NotNull Plugin plugin, String... path) {
        this.plugin = plugin;
        this.parentFolder = new File(plugin.getDataFolder(), String.join(File.separator, path));
        saveResource(path);
        this.config = getConfig();
        saveConfig();
    }

    public String getString(String path) {
        return getConfig().getString(path);
    }

    public Object getValue(String path) {
        return getConfig().get(path);
    }

    public List<String> getStringList(String path) {
        return getConfig().getStringList(path);
    }

    public void setValue(String key, String value) {
        getConfig().set(key, value);
        saveConfig();
    }

    public void deleteConfig() {
        if (configFile.exists()) {
            configFile.delete();
            plugin.getLogger().info("Deleted configuration file: " + configFile.getName());
        }
    }

    private void saveConfig() {
        try {
            config.save(configFile);
        } catch (IOException e) {
            plugin.getLogger().warning("Failed to save configuration file: " + configFile.getName());
        }
    }

    private void saveResource(String... path) {
        String filePath = String.join("/", path) + ".yml";
        File file = new File(plugin.getDataFolder(), filePath);
        if (!file.exists()) {
            plugin.saveResource(filePath, true);
        }
        this.configFile = file;
    }

    private FileConfiguration getConfig() {
        plugin.getConfig().options().copyDefaults(true);
        YamlConfiguration yamlConfig = new YamlConfiguration();
        try {
            yamlConfig.load(configFile);
        } catch (IOException | InvalidConfigurationException e) {
            plugin.getLogger().warning("Failed to load configuration file: " + configFile.getName());
        }
        return yamlConfig;
    }
}
