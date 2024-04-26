package com.megadev.scoca.object.permission;

import lombok.Getter;

@Getter
public enum Permission {
    FURNACE_USE("furnace-use"),
    FURNACE_GIVE("furnace-give"),
    BOILER_USE("boiler-use"),
    BOILER_GIVE("boiler-give");

    Permission(String configPath) {
    }
}
