package com.megadev.scoca.config;

import com.megadev.scoca.object.item.PluginStack;
import com.megadev.scoca.object.item.Item;
import com.megadev.scoca.util.PluginStackFactory;
import dev.mega.megacore.util.Color;
import org.bukkit.configuration.Configuration;
import org.bukkit.configuration.ConfigurationSection;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Represents a factory to make Item objects from configuration.
 */
public class ItemFactory {
    /**
     * Gets Item objects from configuration.
     * @param config Configuration to get from.
     * @param sectionName Configuration section name.
     * @return List of Item objects.
     */
    public static List<Item> getItems(Configuration config, String sectionName) {
        List<Item> items = new ArrayList<>();

        ConfigurationSection section = config.getConfigurationSection(sectionName);
        for (String key : section.getKeys(false)) {
            ItemFactory.handleNestedSection((ConfigurationSection) section.get(key), items);
        }

        return items;
    }

    private static void handleNestedSection(ConfigurationSection patternBlock, List<Item> items) {
        String contentName = patternBlock.getName();
        String item = patternBlock.getString("item");
        String name = Color.getTranslated(Objects.requireNonNull(patternBlock.getString("name")));
        List<String> lore = Color.getTranslated(patternBlock.getStringList("lore"));
        PluginStack pluginStack = PluginStackFactory.getPluginStack(contentName, item, name, lore);

        items.add(new Item(patternBlock.getName(), pluginStack));
    }
}
