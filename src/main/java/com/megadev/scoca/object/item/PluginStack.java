package com.megadev.scoca.object.item;

import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;

public interface PluginStack {
    /**
     * @return The ItemStack object
     */
    ItemStack getItemStack();

    /**
     * @return The material name
     */
    String getName();

    /**
     * @return Display name of the ItemStack object
     */
    String getTitle();

    /**
     * @return Lore of the ItemStack meta
     */
    default List<String> getLore() {
        return getItemMeta().getLore();
    }

    /**
     * @return ItemStack meta
     */
    default ItemMeta getItemMeta() {
        return getItemStack().getItemMeta();
    }

    void setItemMeta(ItemMeta itemMeta);
}
