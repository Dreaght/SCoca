package com.megadev.scoca.object.content;

import lombok.Getter;

@Getter
public enum PluginBlock {
    FURNACE("furnace"),
    BOILER("boiler");

    private final String title;

    PluginBlock(String title) {
        this.title = title;
    }
}
