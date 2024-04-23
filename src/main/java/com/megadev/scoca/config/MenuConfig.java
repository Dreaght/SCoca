package com.megadev.scoca.config;

import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;

public class MenuConfig extends Configurable {
    protected MenuConfig(@NotNull Plugin plugin, String fileName) {
        super(plugin, ".", fileName);
    }
}
