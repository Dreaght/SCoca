package com.megadev.scoca.object.item;

import dev.lone.itemsadder.api.CustomStack;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Objects;

public class ItemsAdderStack implements PluginStack {
    private final CustomStack itemStack;

    public ItemsAdderStack(CustomStack itemStack) {
        this.itemStack = itemStack;
    }

    @Override
    public ItemStack getItemStack() {
        return itemStack.getItemStack();
    }

    @Override
    public String getName() {
        return itemStack.getNamespacedID();
    }

    @Override
    public String getTitle() {
        return itemStack.getDisplayName();
    }

    @Override
    public void setItemMeta(ItemMeta itemMeta) {
        getItemStack().setItemMeta(itemMeta);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ItemsAdderStack that = (ItemsAdderStack) o;

        return Objects.equals(getName(), that.getName());
    }
}
