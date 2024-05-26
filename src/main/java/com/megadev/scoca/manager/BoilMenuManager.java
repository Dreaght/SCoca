package com.megadev.scoca.manager;

import com.megadev.scoca.config.ConfigManager;
import com.megadev.scoca.object.boil.BoilState;
import com.megadev.scoca.object.content.BoilMenu;
import com.megadev.scoca.object.content.SCocaBlock;
import dev.mega.megacore.MegaCore;
import dev.mega.megacore.manager.Manager;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.UUID;

public class BoilMenuManager extends Manager {
    @Getter
    private static BoilMenuManager instance;
    private final ConfigManager configManager = ConfigManager.getInstance();

    private BoilMenuManager(MegaCore megaCore) {
        super(megaCore);
    }

    public static void init(MegaCore megaCore) {
        if (instance == null) {
            instance = new BoilMenuManager(megaCore);
        }
    }

    @Override
    public void reload() {

    }

    @Override
    public void disable() {

    }

    public void openMenu(UUID uuid, SCocaBlock sCocaBlock) {
        Player player = Bukkit.getPlayer(uuid);
        if (player == null) return;
        if (sCocaBlock == null) return;

        BoilState boilState = BoilManager.getInstance().getBoilState(uuid, sCocaBlock);

        BoilMenu boilMenu = boilState.getBoilMenu();
        if (boilMenu == null) {
            player.sendMessage("Boil menu has not loaded yet! Try again!");
            return;
        }
        boilMenu.open(player);
    }
}
