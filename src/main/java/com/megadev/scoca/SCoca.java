package com.megadev.scoca;

import com.megadev.scoca.config.BoilConfig;
import com.megadev.scoca.config.Config;
import com.megadev.scoca.config.ConfigManager;
import com.megadev.scoca.config.Configurable;
import org.bukkit.plugin.java.JavaPlugin;

public final class SCoca extends JavaPlugin {
    @Override
    public void onEnable() {
        setupManagers();

    }

    private void setupManagers() {
        ConfigManager.init(this);

        BoilConfig config = ConfigManager.getInstance().getConfig(BoilConfig.class);
    }

    private void registerListeners() {

    }

    @Override
    public void onDisable() {
    }
}
