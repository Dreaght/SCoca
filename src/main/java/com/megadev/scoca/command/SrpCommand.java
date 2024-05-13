package com.megadev.scoca.command;

import com.megadev.scoca.config.ConfigManager;
import com.megadev.scoca.config.ItemsConfig;
import com.megadev.scoca.object.menu.MenuState;
import dev.mega.megacore.command.CommandHandler;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;

public class SrpCommand {
    @CommandHandler(name = "srp")
    public void srpCommand(CommandSender sender, String[] args) {
        switch (args.length) {
            case 3:
                handleGiveCommand(sender, args);
                break;
            default:
                sender.sendMessage("3 args required");
                break;
        }
    }

    private void handleGiveCommand(CommandSender sender, String[] args) {
        Player player = Bukkit.getPlayer(args[1]);

        if (player == null) {
            sender.sendMessage("input the valid player");
            return;
        }

        String itemName = args[2];

        ItemsConfig itemsConfig = ConfigManager.getInstance().getConfig(ItemsConfig.class);
        List<MenuState.Item> items = itemsConfig.getAll();

        for (MenuState.Item item : items) {
            if (itemName.equals(item.title())) {
                player.getInventory().addItem(item.pluginStack().getItemStack());
                return;
            }
        }
    }
}
