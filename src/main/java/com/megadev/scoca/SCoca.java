package com.megadev.scoca;

import com.megadev.scoca.config.ConfigManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.regex.Pattern;

public final class SCoca extends JavaPlugin {
    @Override
    public void onEnable() {
        setupManagers();

    }

    private void setupManagers() {
        ConfigManager.init(this);



//        BoilConfig config = ConfigManager.getInstance().getConfig(BoilConfig.class);
    }

    private void registerListeners() {

    }

    @Override
    public void onDisable() {
    }
}
