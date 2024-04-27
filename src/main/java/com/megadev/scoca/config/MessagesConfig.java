package com.megadev.scoca.config;

import com.megadev.scoca.object.message.Message;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;

public class MessagesConfig extends Configurable {
    /**
     * Constructs a Configurable object.
     *
     * @param plugin The plugin instance.
     * @param path   The path to the configuration file.
     */
    protected MessagesConfig(@NotNull Plugin plugin, String... path) {
        super(plugin, path);
    }

    /**
     * Gets a message text from configuration.
     * @param message Message to get.
     * @return Message text.
     */
    public String getMessage(Message message) {
        return config.getString(message.getConfigPath());
    }
}
