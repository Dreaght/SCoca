package com.megadev.scoca.util;

import com.megadev.scoca.config.ConfigManager;
import com.megadev.scoca.config.SettingsConfig;
import com.megadev.scoca.object.permission.Permission;
import org.bukkit.entity.Player;

public class CheckPermission {
    private static final SettingsConfig settingsConfig = ConfigManager.getInstance().getConfig(SettingsConfig.class);

    public static boolean hasPermission(Player player, Permission permission) {
        String permissionName = settingsConfig.getPermission(permission.getConfigPath());
        return player.hasPermission(permissionName);
    }

    public static boolean hasPermission(Player player, String permission) {
        return player.hasPermission(permission);
    }
}
