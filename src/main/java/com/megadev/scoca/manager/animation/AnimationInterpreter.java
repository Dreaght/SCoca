package com.megadev.scoca.manager.animation;

import com.megadev.scoca.object.content.menu.BoilMenu;
import lombok.Getter;
import org.bukkit.Location;
import org.bukkit.plugin.Plugin;

@Getter
public class AnimationInterpreter {
    private final Plugin plugin;
    private final BoilMenu menu;
    private final Location location;

    public AnimationInterpreter(Plugin plugin, BoilMenu menu, Location location) {
        this.plugin = plugin;
        this.menu = menu;
        this.location = location;
    }
}
