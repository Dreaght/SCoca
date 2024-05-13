package com.megadev.scoca;

import com.megadev.scoca.command.SrpCommand;
import com.megadev.scoca.config.ConfigManager;
import dev.mega.megacore.MegaCore;
import dev.mega.megacore.command.CommandManager;

public final class SCoca extends MegaCore {
    @Override
    public void enable() {
        setupManagers();
    }

    private void setupManagers() {
        ConfigManager.init(this);
        setupCommands();
    }

    private void setupCommands() {
        CommandManager commandManager = new CommandManager(this);
        commandManager.registerCommands(new SrpCommand());
    }

    private void registerListeners() {

    }

    @Override
    public void disable() {
    }


}
