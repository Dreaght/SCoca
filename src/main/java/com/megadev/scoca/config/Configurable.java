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

    /**
     * Constructs a new Configurable object.
     *
     * @param plugin        The plugin instance.
     * @param fileName      The name of the configuration file.
     */
    protected Configurable(@NotNull Plugin plugin, String fileName) {
        this(plugin, new File(""), fileName);
    }

    /**
     * Constructs a new Configurable object with subfolder name.
     *
     * @param plugin        The plugin instance.
     * @param subFolderName The name of the subfolder where the configuration file will be stored.
     * @param fileName      The name of the configuration file.
     */
    protected Configurable(@NotNull Plugin plugin, String subFolderName, String fileName) {
        this(plugin, new File(subFolderName), fileName);
    }

    /**
     * Constructs a new Configurable object with subfolder.
     *
     * @param plugin        The plugin instance.
     * @param subFolder     The subFolder where the configuration file will be stored.
     * @param fileName      The name of the configuration file.
     */
    protected Configurable(@NotNull Plugin plugin, File subFolder, String fileName) {
        this.plugin = plugin;
        this.parentFolder = new File(plugin.getDataFolder(), subFolder.getPath());

        saveResource(subFolder, fileName);

        this.config = getConfig();
        saveConfig();
    }

    /**
     * Retrieves a string value from the configuration.
     *
     * @param path The path to the value.
     * @return The string value.
     */
    public String getString(String path) {
        return getConfig().getString(path);
    }

    /**
     * Retrieves a value from the configuration.
     *
     * @param path The path to the value.
     * @return The value.
     */
    public Object getValue(String path) {
        return getConfig().get(path);
    }

    /**
     * Retrieves a list of strings from the configuration.
     *
     * @param path The path to the list.
     * @return The list of strings.
     */
    public List<String> getStringList(String path) {
        return getConfig().getStringList(path);
    }

    /**
     * Sets a string value in the configuration.
     *
     * @param key   The key to set.
     * @param value The value to set.
     */
    public void setValue(String key, String value) {
        getConfig().set(key, value);
        saveConfig();
    }

    /**
     * Deletes the configuration file.
     */
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

    private void saveResource(File subFolder, String fileName) {
        subFolder.mkdirs();

        String filePath = fileName + ".yml";
        this.configFile = new File(parentFolder, filePath);

        File fileFrom = new File(subFolder, filePath);
        String pathFileFrom = fileFrom.getPath();

        if (pathFileFrom.startsWith("\\"))
            pathFileFrom = pathFileFrom.replaceFirst("\\\\", "");

        saveResource(pathFileFrom);
    }

    private void saveResource(String fileFromPath) {
        if (plugin.getResource(fileFromPath) == null) {
            configFile.getParentFile().mkdirs();

            try {
                configFile.createNewFile();
            } catch (IOException e) {
                plugin.getLogger().warning("Failed to save resource: " + configFile.getPath());
            }
        }

        try {
            plugin.saveResource(fileFromPath, true);
        } catch (IllegalArgumentException e) {
            plugin.getLogger().warning("Failed to find resource: " + fileFromPath);
        }

        plugin.saveConfig();
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