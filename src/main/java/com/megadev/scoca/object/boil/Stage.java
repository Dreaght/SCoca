package com.megadev.scoca.object.boil;

import lombok.Getter;

@Getter
public enum Stage {
    FUEL("fuel"),
    SULFUR_TO_SALT("sulfur-to-salt"),
    SALT_TO_HERB("salt-to-herb"),
    HERB_TO_MEPH("herb-to-meph"),
    ;

    public static Stage next(Stage foo) {
        return values()[(foo.ordinal() + 1) % values().length];
    }

    private final String configPath;

    Stage(String configPath) {
        this.configPath = configPath;
    }

    public record ConfigStage(String title, String animationPath) {}
}
