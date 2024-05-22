package com.megadev.scoca;

import co.aikar.commands.PaperCommandManager;
import com.megadev.scoca.command.SrpCommand;
import com.megadev.scoca.config.ConfigManager;
import com.megadev.scoca.config.ItemsConfig;
import com.megadev.scoca.listener.BlockListener;
import com.megadev.scoca.listener.InteractListener;
import com.megadev.scoca.manager.BlockManager;
import com.megadev.scoca.manager.ItemManager;
import dev.mega.megacore.MegaCore;
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
        PaperCommandManager manager = new PaperCommandManager(this);
        manager.registerCommand(new SrpCommand());

        manager.getCommandCompletions().registerAsyncCompletion("item", c ->
                ConfigManager.getInstance().getConfig(ItemsConfig.class).getAllMenuTitles()
        );

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
