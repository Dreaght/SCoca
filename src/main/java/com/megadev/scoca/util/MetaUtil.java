package com.megadev.scoca.util;

import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

public class MetaUtil {
    private final static String namespace = "scoca";

    public static String getItemMeta(ItemStack itemStack, String key) {
        ItemMeta meta = itemStack.getItemMeta();
        if (meta != null) {
            return getItemMeta(meta, key);
        }

        return null;
    }

    public static String getItemMeta(ItemMeta itemMeta, String key) {
        NamespacedKey namespacedKey = new NamespacedKey(namespace, key);
        PersistentDataContainer container = itemMeta.getPersistentDataContainer();

        return container.get(namespacedKey, PersistentDataType.STRING);
    }

    public static void setItemMeta(ItemStack itemStack, String key, String value) {
        ItemMeta meta = itemStack.getItemMeta();

        if (meta != null) {
            NamespacedKey namespacedKey = new NamespacedKey(namespace, key);
            meta.getPersistentDataContainer().set(namespacedKey, PersistentDataType.STRING, value);
            itemStack.setItemMeta(meta);
        }
    }
}
