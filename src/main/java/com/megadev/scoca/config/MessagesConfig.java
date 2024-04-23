package com.megadev.scoca.config;

import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;

public class MessagesConfig extends Configurable {
    protected MessagesConfig(@NotNull Plugin plugin, String fileName) {
        super(plugin, ".", fileName);
    }
}
