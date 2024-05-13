package com.megadev.scoca.object.content;

import lombok.Getter;

@Getter
public enum PluginItemStack {
    FUEL("fuel"),
    SULFUR("sulfur"),
    SALT("salt"),
    HERB("herb"),
    MEPH("meph"),
    ;

    private final String title;

    PluginItemStack(String title) {
        this.title = title;
    }
}
