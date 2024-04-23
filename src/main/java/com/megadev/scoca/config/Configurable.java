package com.megadev.scoca.config;

import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;

import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.IOException;
import java.util.List;

public abstract class Configurable implements Config {
    protected Plugin plugin;
    protected FileConfiguration config;
    protected File configFile;
    protected File subFolder;

    protected Configurable(@NotNull Plugin plugin, String subFolderName, String fileName) {
        this.plugin = plugin;
        File dataFolder = plugin.getDataFolder();
        this.subFolder = new File(dataFolder, subFolderName);

        if (!subFolder.exists()) {
            subFolder.mkdirs();
        }

        this.configFile = new File(subFolder, fileName + ".yml");

        saveResource(fileName);

        this.config = getConfig();
    }

    public String getString(String path) {
        return (String) getValue(path);
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

    public void saveConfig() {
        try {
            config.save(configFile);
        } catch (IOException e) {
            plugin.getLogger().warning("Failed to save configuration file: " + configFile.getName());
        }
    }

    private void saveResource(String fileName) {
        if (plugin.getResource(fileName + "." + "yml") != null) {
            plugin.saveResource(fileName + "." + "yml", false);
        } else {
            try {
                configFile.getParentFile().mkdirs();
                configFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private FileConfiguration getConfig() {
        if (!configFile.exists()) {
            try {
                configFile.createNewFile();
                config = new YamlConfiguration();
                config.options().copyDefaults(true);
                saveConfig();
            } catch (IOException e) {
                plugin.getLogger().warning("Failed to create configuration file: " + configFile.getName());
            }
        }

        YamlConfiguration yamlConfig = new YamlConfiguration();
        try {
            yamlConfig.load(configFile);
        } catch (IOException | InvalidConfigurationException e) {
            plugin.getLogger().warning("Failed to load configuration file: " + configFile.getName());
        }
        return yamlConfig;
    }

    public void deleteConfig() {
        if (configFile.exists()) {
            configFile.delete();
            plugin.getLogger().info("Deleted configuration file: " + configFile.getName());
        }
    }
}
