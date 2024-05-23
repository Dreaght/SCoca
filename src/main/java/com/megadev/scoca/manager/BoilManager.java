package com.megadev.scoca.manager;

import com.megadev.scoca.object.boil.Stage;
import com.megadev.scoca.storage.BoilData;
import dev.mega.megacore.MegaCore;
import dev.mega.megacore.manager.Manager;

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

    public void startBoiling(Stage.ConfigStage configStage) {

    }
}
