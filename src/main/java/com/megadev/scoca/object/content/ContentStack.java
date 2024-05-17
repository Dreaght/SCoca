package com.megadev.scoca.object.content;

import lombok.Getter;

@Getter
public enum ContentStack {
    FUEL("fuel"),
    SULFUR("sulfur"),
    SALT("salt"),
    HERB("herb"),
    MEPH("meph"),
    ;

    private final String title;

    ContentStack(String title) {
        this.title = title;
    }
}
