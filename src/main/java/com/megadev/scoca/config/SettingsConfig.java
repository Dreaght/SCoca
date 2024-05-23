package com.megadev.scoca.config;

import dev.mega.megacore.config.Configurator;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;

/**
 * Represents "config.yml" config file access object.
 */
public class SettingsConfig extends Configurator {
    /**
     * Constructs a Configurable object.
     *
     * @param plugin The plugin instance.
     * @param path   The path to the configuration file.
     */
    protected SettingsConfig(@NotNull Plugin plugin, String... path) {
        super(plugin, path);
    }

    /**
     * Gets the permission from configuration.
     * @param permName Permission name to get.
     * @return The permission.
     */
    public String getPermission(String permName) {
        return config.getString("permissions." + permName);
    }
}
