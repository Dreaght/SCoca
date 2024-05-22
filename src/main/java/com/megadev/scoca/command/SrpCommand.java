package com.megadev.scoca.command;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.*;
import com.megadev.scoca.config.ConfigManager;
import com.megadev.scoca.config.ItemsConfig;
import com.megadev.scoca.manager.ItemManager;
import com.megadev.scoca.object.content.ContentStack;
import com.megadev.scoca.object.content.SCocaItem;
import com.megadev.scoca.object.menu.MenuState;
import dev.mega.megacore.command.CommandHandler;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;

@CommandAlias("srp")
public class SrpCommand extends BaseCommand {

    @Subcommand("give")
    @CommandCompletion("@players @item @range:1-64")
    public void onPlayerGive(Player player, Player target, String item, @Default("1") int amount) {
        player.sendMessage("\nsender: " + player.getName() + "\n" +
                " destination player: " + target.getName() + "\n" +
                " item: " + item + "\n" +
                " amount: " + amount);
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

                SCocaItem sCocaItem = new SCocaItem(player.getUniqueId(), item.pluginStack());
                ItemManager.getInstance().addItem(sCocaItem);
                return;
            }
        }

        sender.sendMessage("matched block not found");
    }
}
