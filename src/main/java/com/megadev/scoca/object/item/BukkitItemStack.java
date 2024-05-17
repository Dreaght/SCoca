package com.megadev.scoca.object.item;

import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Objects;

public class BukkitItemStack implements PluginStack {
    private final ItemStack itemStack;

    public BukkitItemStack(ItemStack itemStack) {
        this.itemStack = itemStack;
    }

    @Override
    public ItemStack getItemStack() {
        return itemStack;
    }

    @Override
    public String getName() {
        return itemStack.getType().name();
    }

    @Override
    public String getTitle() {
        return Objects.requireNonNull(itemStack.getItemMeta()).getDisplayName();
    }

    @Override
    public void setItemMeta(ItemMeta itemMeta) {
        itemStack.setItemMeta(itemMeta);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BukkitItemStack that = (BukkitItemStack) o;
        return Objects.equals(getItemStack(), that.getItemStack());
    }
}
