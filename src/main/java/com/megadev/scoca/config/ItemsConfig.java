package com.megadev.scoca.config;

import com.megadev.scoca.object.menu.MenuState;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents "items.yml" config file access object.
 */
public class ItemsConfig extends Configurable {
    /**
     * Constructs a Configurable object.
     *
     * @param plugin The plugin instance.
     * @param path   The path to the configuration file.
     */
    protected ItemsConfig(@NotNull Plugin plugin, String... path) {
        super(plugin, path);
    }

    /**
     * Gets and concatenates lists of items and blocks.
     * @return List of Item objects.
     */
    public List<MenuState.Item> getAll() {
        List<MenuState.Item> allItems = new ArrayList<>();
        allItems.addAll(getItems());
        allItems.addAll(getBlocks());
        return allItems;
    }

    /**
     * Gets items from its configuration section.
     * @return List of Item objects.
     */
    public List<MenuState.Item> getItems() {
        return ItemFactory.getItems(config, "item-stack");
    }

    /**
     * Gets blocks from its configuration section.
     * @return List of Item objects.
     */
    public List<MenuState.Item> getBlocks() {
        return ItemFactory.getItems(config, "blocks");
    }
}
