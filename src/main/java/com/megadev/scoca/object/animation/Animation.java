package com.megadev.scoca.object.animation;

import java.util.List;

public record Animation(List<Step> steps) {
    public enum Command {
        MENU,
        DELAY,
        PARTICLE,
        SOUND,
        ;

    }

    public record Step(Command command, String value) {}
}
