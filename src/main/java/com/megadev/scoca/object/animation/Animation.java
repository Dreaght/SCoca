package com.megadev.scoca.object.animation;

import org.bukkit.Sound;

import java.util.List;

public record Animation(List<Command.Step> steps) {
    public enum Command {
        MENU,
        DELAY,
        PARTICLE,
        SOUND,
        ;

        public record Step(Command command, String value) {}
    }
}
