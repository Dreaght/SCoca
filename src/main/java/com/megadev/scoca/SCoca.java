package com.megadev.scoca;

import com.megadev.scoca.command.GiveArg;
import com.megadev.scoca.config.ConfigManager;
import dev.mega.megacore.MegaCore;
import dev.mega.megacore.command.Argument;
import dev.mega.megacore.command.CommandManager;
import dev.mega.megacore.command.MegaCommand;
import dev.mega.megacore.command.matcher.StringArg;

public final class SCoca extends MegaCore {
    @Override
    public void enable() {
        setupManagers();
    }

    private void setupManagers() {
        ConfigManager.init(this);

        CommandManager commandManager = CommandManager.init();

        MegaCommand srpCommand = new MegaCommand(new StringArg("srp"));

        Argument giveArg = new GiveArg(new StringArg("give"));
        srpCommand.addArgument(giveArg);

        commandManager.addCommand(srpCommand);

        commandManager.registerCommands(getInstance());
    }

    private void registerListeners() {

    }

    @Override
    public void disable() {
    }


}
