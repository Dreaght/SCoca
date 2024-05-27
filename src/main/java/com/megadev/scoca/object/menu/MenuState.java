package com.megadev.scoca.object.menu;

import com.megadev.scoca.object.item.Item;
import com.megadev.scoca.util.MetaUtil;
import com.megadev.scoca.util.PluginStackFactory;
import org.bukkit.inventory.InventoryView;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public record MenuState(String title, List<Slot> slots) {
    public static MenuState of(InventoryView inventoryView) {
        String title = inventoryView.getTitle();
        List<ItemStack> itemStacks = List.of(inventoryView.getTopInventory().getContents());
        List<Slot> slotList = new ArrayList<>();

        for (int i = 0; i < itemStacks.size(); i++) {
            String content = MetaUtil.getItemMeta(itemStacks.get(i), "content");
            slotList.add(new Slot(i, new Item(content, PluginStackFactory.getPluginStack(itemStacks.get(i)))));
        }

        return new MenuState(title, slotList);
    }

    public record Slot(int index, Item slotPattern) {}
}
