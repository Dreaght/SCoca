package com.megadev.scoca.config;

import com.megadev.scoca.object.menu.DefaultAnimationPath;
import com.megadev.scoca.object.menu.MenuState;
import dev.mega.megacore.config.Configurable;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;

import java.util.List;

/**
 * Represents "menu.yml" config file access object.
 */
public class MenuConfig extends Configurable {
    /**
     * Constructs a Configurable object.
     *
     * @param plugin The plugin instance.
     * @param path   The path to the configuration file.
     */
    protected MenuConfig(@NotNull Plugin plugin, String... path) {
        super(plugin, path);
    }

    /**
     * Gets default animation configuration path for block menu type.
     * @param blockMenu Block type.
     * @return Default animation resource path.
     */
    public String getDefaultAnimationPath(DefaultAnimationPath blockMenu) {
        return config.getString(blockMenu.getConfigPath());
    }

    /**
     * Gets slot patterns from configuration.
     * @return List of Item objects.
     */
    public List<MenuState.Item> getSlotPatterns() {
        return ItemFactory.getItems(config, "slot-patterns");
    }
}
