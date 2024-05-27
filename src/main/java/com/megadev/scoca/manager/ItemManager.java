package com.megadev.scoca.manager;

import com.megadev.scoca.object.item.PluginStack;
import com.megadev.scoca.storage.ItemData;
import dev.mega.megacore.MegaCore;
import dev.mega.megacore.manager.Manager;
import lombok.Getter;

import java.util.UUID;

public class ItemManager extends Manager {
    @Getter private static ItemManager instance;
    private ItemData itemData;

    private ItemManager(MegaCore megaCore) {
        super(megaCore);
    }

    public static void init(MegaCore megaCore) {
        if (instance == null) {
            instance = new ItemManager(megaCore);
        }
    }

    @Override
    public void reload() {
        itemData = new ItemData();
    }

    @Override
    public void disable() {
        itemData = null;
    }

    public boolean isRegistered(PluginStack block) {
        if (block != null) {
            return itemData.contains(block);
        }
        return false;
    }

    public PluginStack validateAndGetSCocaItem(UUID uuid, PluginStack pluginStack) {
        return itemData.getRegistered(uuid, pluginStack);
    }

    public void addItem(UUID uuid, PluginStack pluginStack) {
        if (itemData != null) {
            itemData.addValueForUuid(uuid, pluginStack);
            System.out.println("items: " + itemData.getValue(uuid));
        }
    }

    public void removeItem(PluginStack pluginStack) {
        if (itemData != null) {
            itemData.removeValue(pluginStack);
        }
    }
}
