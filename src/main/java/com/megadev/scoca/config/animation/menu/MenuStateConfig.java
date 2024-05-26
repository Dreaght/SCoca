package com.megadev.scoca.config.animation.menu;

import com.megadev.scoca.config.ConfigManager;
import com.megadev.scoca.config.MenuConfig;
import com.megadev.scoca.object.content.BoilMenu;
import com.megadev.scoca.object.content.Ingredient;
import com.megadev.scoca.object.item.BukkitItemStack;
import com.megadev.scoca.object.item.Item;
import com.megadev.scoca.object.menu.MenuState;
import com.megadev.scoca.util.MenuStateUtil;
import com.megadev.scoca.util.MetaUtil;
import dev.mega.megacore.config.Configurator;
import dev.mega.megacore.util.Color;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;

import java.util.*;

public class MenuStateConfig extends Configurator {
    protected MenuStateConfig(@NotNull Plugin plugin, String... path) {
        super(plugin, path);
    }

    public MenuState getMenuState() {
        String title = config.getString("title", "");

        ConfigurationSection section = config.getConfigurationSection("slots");
        if (section == null) return new MenuState(title, List.of());

        List<MenuState.Slot> slots = new ArrayList<>();

        Set<String> keys = section.getKeys(false);

        MenuConfig menuConfig = ConfigManager.getInstance().getConfig(MenuConfig.class);
        List<Item> slotPatterns = menuConfig.getSlotPatterns();

        for (String key : keys) {
            Item item = slotPatterns.stream().filter(slotPattern -> {
                return Objects.equals(MetaUtil.getItemMeta(slotPattern.pluginStack().getItemStack(), "content"), section.get(key));
            }).findAny().orElse(null);

            if (item == null) {
                item = new Item("", new BukkitItemStack(new ItemStack(Material.AIR)));
            }

            slots.add(new MenuState.Slot(Integer.parseInt(key), item));
        }

        return new MenuState(title, slots);
    }

    public BoilMenu getBoilMenu(Location location) {
        MenuState menuState = getMenuState();

        String title = Color.getTranslated(menuState.title());

        BoilMenu boilMenu = new BoilMenu(location, MenuStateUtil.getSize(menuState), title);
        fillItems(boilMenu, menuState);

        return boilMenu;
    }

    private void fillItems(BoilMenu boilMenu, MenuState menuState) {
        for (MenuState.Slot slot : menuState.slots()) {
            boilMenu.setItem(slot.index(), slot.slotPattern().pluginStack().getItemStack());
        }
    }
}
