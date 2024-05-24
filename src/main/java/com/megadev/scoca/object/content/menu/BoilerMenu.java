package com.megadev.scoca.object.content.menu;

import com.megadev.scoca.object.boil.BoilBlock;
import dev.mega.megacore.inventory.MegaInventory;
import lombok.Getter;

@Getter
public class BoilerMenu extends MegaInventory implements BoilMenu {
    private final static BoilBlock boilBlock = BoilBlock.BOILER;

    public BoilerMenu(int size, String title) {
        super(size, title);
    }

    @Override
    public BoilBlock getBoilBlock() {
        return boilBlock;
    }
}
