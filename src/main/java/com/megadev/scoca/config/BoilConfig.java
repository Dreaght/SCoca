package com.megadev.scoca.config;

import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;

public class BoilConfig extends Configurable {
    protected BoilConfig(@NotNull Plugin plugin, String fileName) {
        super(plugin, ".", fileName);
    }

    public void test() {

    }
}
