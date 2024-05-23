package com.megadev.scoca.manager;

import com.megadev.scoca.object.boil.BoilState;
import com.megadev.scoca.storage.BoilData;
import dev.mega.megacore.MegaCore;
import dev.mega.megacore.inventory.MegaInventory;
import dev.mega.megacore.manager.Manager;

import java.util.UUID;

public class BoilManager extends Manager {
    private BoilData boilData;

    public BoilManager(MegaCore megaCore) {
        super(megaCore);
    }

    @Override
    public void reload() {
        boilData = new BoilData();
    }

    @Override
    public void disable() {
        boilData = null;
    }

    public MegaInventory verifyAndGetMenu(UUID uuid, BoilState similarBoilState) {
        BoilState boilState = boilData.getRegistered(uuid, similarBoilState);

        if (boilState == null) {

        }

        return boilState.getAnimationInterpreter().getMenu();
    }
}
