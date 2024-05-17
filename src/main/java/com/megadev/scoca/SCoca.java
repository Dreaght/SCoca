package com.megadev.scoca;

import com.megadev.scoca.command.SrpCommand;
import com.megadev.scoca.config.ConfigManager;
import com.megadev.scoca.listener.BlockListener;
import com.megadev.scoca.listener.InteractListener;
import com.megadev.scoca.manager.BlockManager;
import com.megadev.scoca.manager.ItemManager;
import dev.mega.megacore.MegaCore;
import dev.mega.megacore.command.CommandManager;
import org.bukkit.plugin.PluginManager;

public final class SCoca extends MegaCore {
    @Override
    public void enable() {
        setupManagers();
        setupCommands();
        registerListeners();
    }

    private void setupManagers() {
        ConfigManager.init(this);

        BlockManager.init(this);
        ItemManager.init(this);
    }

    private void setupCommands() {
        CommandManager commandManager = new CommandManager(this);
        commandManager.registerCommands(new SrpCommand());
    }

    private void registerListeners() {
        PluginManager pluginManager = getServer().getPluginManager();
        pluginManager.registerEvents(new BlockListener(), this);
        pluginManager.registerEvents(new InteractListener(), this);
    }

    @Override
    public void disable() {
    }
}
