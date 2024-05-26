package com.megadev.scoca.object.content;

import dev.mega.megacore.inventory.MegaInventory;
import lombok.Getter;
import org.bukkit.Location;

import java.util.Objects;

@Getter
public class BoilMenu extends MegaInventory {
    private final Location location;
    private final int size;

    public BoilMenu(Location location, int size, String title) {
        super(size, title);
        this.location = location;
        this.size = size;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BoilMenu that = (BoilMenu) o;
        return Objects.equals(getLocation(), that.getLocation());
    }

    public static BoilMenu of(BoilMenu boilMenu, String title) {
        return new BoilMenu(boilMenu.location, boilMenu.size, title);
    }
}
