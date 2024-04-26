package com.megadev.scoca;

import com.megadev.scoca.config.ConfigManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class SCoca extends JavaPlugin {
    @Override
    public void onEnable() {
        setupManagers();

    }

    private void setupManagers() {
        ConfigManager.init(this);
    }

    private void registerListeners() {

    }

    @Override
    public void onDisable() {
    }
}
