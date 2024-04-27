package com.megadev.scoca.object.menu;

import lombok.Getter;

@Getter
public enum DefaultAnimationPath {
    FURNACE("furnace-default-animation-path"),
    BOILER("boiler-default-animation-path");

    private final String configPath;

    DefaultAnimationPath(String configPath) {
        this.configPath = configPath;
    }
}
