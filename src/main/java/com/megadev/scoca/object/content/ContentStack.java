package com.megadev.scoca.object.content;

import lombok.Getter;

import java.util.stream.Stream;

@Getter
public enum ContentStack {
    FURNACE("furnace"),
    BOILER("boiler"),
    FUEL("fuel"),
    SULFUR("sulfur"),
    SALT("salt"),
    HERB("herb"),
    MEPH("meph"),
    ;

    public static ContentStack getContentStack(String title) {
        return Stream.of(ContentStack.values())
                .filter(x -> x.getTitle().equals(title))
                .findAny().orElse(null);
    }

    private final String title;

    ContentStack(String title) {
        this.title = title;
    }
}
