package com.megadev.scoca.manager;

import com.megadev.scoca.object.content.SCocaItem;
import com.megadev.scoca.storage.ItemData;
import dev.mega.megacore.MegaCore;
import dev.mega.megacore.manager.Manager;
import lombok.Getter;

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

    public boolean isRegistered(SCocaItem block) {
        if (block != null) {
            return itemData.contains(block);
        }
        return false;
    }

    public SCocaItem validateAndGetSCocaItem(SCocaItem sCocaItem) {
        return itemData.getRegistered(sCocaItem.getUuid(), sCocaItem);
    }

    public void addItem(SCocaItem pluginStack) {
        if (itemData != null) {
            itemData.addValueForUuid(pluginStack.getUuid(), pluginStack);
            System.out.println("items: " + itemData.getValue(pluginStack.getUuid()));
        }
    }

    public void removeItem(SCocaItem pluginStack) {
        if (itemData != null) {
            itemData.removeValue(pluginStack);
        }
    }
}
