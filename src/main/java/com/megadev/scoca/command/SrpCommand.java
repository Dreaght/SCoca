package com.megadev.scoca.command;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.*;
import com.megadev.scoca.config.ConfigManager;
import com.megadev.scoca.config.ItemsConfig;
import com.megadev.scoca.manager.ItemManager;
import com.megadev.scoca.object.item.Item;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;
import java.util.UUID;

@CommandAlias("srp")
public class SrpCommand extends BaseCommand {

    @Subcommand("give")
    @CommandCompletion("@players @item @range:1-64")
    public void onPlayerGive(CommandSender sender, String playerStr, String itemName, @Single @Default("1") int amount) {
        Player player = Bukkit.getPlayer(playerStr);

        if (player == null) {
            sender.sendMessage("Please, input the real player name!");
            return;
        }

        ItemsConfig itemsConfig = ConfigManager.getInstance().getConfig(ItemsConfig.class);
        List<Item> items = itemsConfig.getAll();

        for (Item item : items) {
            if (itemName.equals(item.title())) {
                for (int i = 0; i < amount; i++) {
                    player.getInventory().addItem(item.pluginStack().getItemStack());
                    ItemManager.getInstance().addItem(player.getUniqueId(), item.pluginStack());
                }
                return;
            }
        }

        sender.sendMessage("Matched block was not found!");
    }

}
