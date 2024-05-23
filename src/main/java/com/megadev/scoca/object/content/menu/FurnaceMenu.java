package com.megadev.scoca.object.content.menu;

import dev.mega.megacore.inventory.MegaInventory;

public class FurnaceMenu extends MegaInventory implements BoilMenu {
    public FurnaceMenu(int size, String title) {
        super(size, title);
    }
}
