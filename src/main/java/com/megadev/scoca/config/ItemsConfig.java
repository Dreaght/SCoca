package com.megadev.scoca.config;

import com.megadev.scoca.object.content.Ingredient;
import com.megadev.scoca.object.item.Item;
import com.megadev.scoca.util.MetaUtil;
import dev.mega.megacore.config.Configurator;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Represents "items.yml" config file access object.
 */
public class ItemsConfig extends Configurator {
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
     * Gets all titles of each item.
     * @return List of items names.
     */
    public List<String> getAllMenuTitles() {
        return getAll().stream()
                .map(Item::title)
                .collect(Collectors.toList());
    }

    /**
     * Gets and concatenates lists of items and blocks.
     * @return List of Item objects.
     */
    public List<Item> getAll() {
        List<Item> allItems = new ArrayList<>();
        allItems.addAll(getItems());
        allItems.addAll(getBlocks());
        return allItems;
    }

    /**
     * Gets items from its configuration section.
     * @return List of Item objects.
     */
    public List<Item> getItems() {
        return ItemFactory.getItems(config, "item-stack");
    }

    public Item getItem(Ingredient ingredient) {
        return getItems().stream()
                .filter(item -> Ingredient.getIngredient(
                        MetaUtil.getItemMeta(item.pluginStack().getItemStack(), "content")).equals(ingredient))
                .findAny().orElse(null);
    }

    /**
     * Gets blocks from its configuration section.
     * @return List of Item objects.
     */
    public List<Item> getBlocks() {
        return ItemFactory.getItems(config, "blocks");
    }
}
