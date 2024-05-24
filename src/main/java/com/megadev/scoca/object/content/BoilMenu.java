package com.megadev.scoca.object.content;

import com.megadev.scoca.object.boil.BoilBlock;
import dev.mega.megacore.inventory.MegaInventory;
import lombok.Getter;

@Getter
public class BoilMenu extends MegaInventory {
    private final BoilBlock boilBlock;

    public BoilMenu(BoilBlock boilBlock, int size, String title) {
        super(size, title);

        this.boilBlock = boilBlock;
    }

    public BoilBlock getBoilBlock() {
        return boilBlock;
    }
}
