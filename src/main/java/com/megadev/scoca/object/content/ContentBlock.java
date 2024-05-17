package com.megadev.scoca.object.content;

import lombok.Getter;

@Getter
public enum ContentBlock {
    FURNACE("furnace"),
    BOILER("boiler");

    private final String title;

    ContentBlock(String title) {
        this.title = title;
    }
}
