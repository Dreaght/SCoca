package com.megadev.scoca.object.boil;

import lombok.Getter;

@Getter
public enum Stages {
    FUEL("fuel"),
    SULFUR_TO_SALT("sulfur-to-salt"),
    SALT_TO_HERB("salt-to-herb"),
    HERB_TO_MEPH("herb-to-meph"),
    ;

    private final String configPath;

    Stages(String configPath) {
        this.configPath = configPath;
    }

    public record Stage(String title, String animationPath) {}
}
