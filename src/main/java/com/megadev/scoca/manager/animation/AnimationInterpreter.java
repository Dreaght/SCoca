package com.megadev.scoca.manager.animation;

import dev.mega.megacore.inventory.MegaInventory;
import lombok.Getter;
import org.bukkit.Location;
import org.bukkit.plugin.Plugin;

@Getter
public class AnimationInterpreter {
    private final Plugin plugin;
    private final MegaInventory menu;
    private final Location location;

    public AnimationInterpreter(Plugin plugin, MegaInventory menu, Location location) {
        this.plugin = plugin;
        this.menu = menu;
        this.location = location;
    }
}
