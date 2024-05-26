package com.megadev.scoca.object.content;

import lombok.Getter;

import java.util.stream.Stream;

@Getter
public enum Ingredient {
    FUEL("fuel"),
    SULFUR("sulfur"),
    SALT("salt"),
    HERB("herb"),
    MEPH("meph"),
    ;

    public static Ingredient getIngredient(String title) {
        return Stream.of(Ingredient.values())
                .filter(x -> x.getTitle().equals(title))
                .findAny().orElse(null);
    }

    private final String title;

    Ingredient(String title) {
        this.title = title;
    }
}
