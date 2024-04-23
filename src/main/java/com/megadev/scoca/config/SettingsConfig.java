package com.megadev.scoca.config;

import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;

public class SettingsConfig extends Configurable {
    protected SettingsConfig(@NotNull Plugin plugin, String fileName) {
        super(plugin, ".", fileName);
    }

    public String getPermission(String permName) {
        return config.getString("permissions." + permName);
    }
}
