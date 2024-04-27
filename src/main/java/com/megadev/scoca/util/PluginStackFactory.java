package com.megadev.scoca.util;

import com.megadev.scoca.object.item.BukkitItemStack;
import com.megadev.scoca.object.item.ItemsAdderStack;
import com.megadev.scoca.object.item.PluginStack;
import dev.lone.itemsadder.api.CustomBlock;
import dev.lone.itemsadder.api.CustomStack;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;

public class PluginStackFactory {
    public static PluginStack getPluginStack(String item, String title, List<String> lore) {
        PluginStack pluginStack = null;

        if (item.startsWith("itemsadder:")) {
            item = parseItemIdentifier(item);
            if (isCustomBlock(item)) {
                pluginStack = createItemsAdderStack(item);
            }
        } else {
            pluginStack = createBukkitItemStack(item);
        }

        if (pluginStack == null) {
            throw new IllegalArgumentException("Failed to find material: " + item);
        }

        setItemMeta(pluginStack, title, lore);

        return pluginStack;
    }

    private static String parseItemIdentifier(String item) {
        return item.split(":", 2)[1];
    }

    private static boolean isCustomBlock(String item) {
        return CustomBlock.isInRegistry(item);
    }

    private static PluginStack createItemsAdderStack(String item) {
        CustomStack stack = CustomStack.getInstance(item);
        return new ItemsAdderStack(stack);
    }

    private static PluginStack createBukkitItemStack(String item) {
        ItemStack itemStack = new ItemStack(Material.valueOf(item));
        return new BukkitItemStack(itemStack);
    }

    private static void setItemMeta(PluginStack pluginStack, String title, List<String> lore) {
        ItemMeta stackMeta = pluginStack.getItemMeta();
        stackMeta.setDisplayName(title);
        stackMeta.setLore(lore);
        pluginStack.setItemMeta(stackMeta);
    }
}
