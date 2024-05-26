package com.megadev.scoca.manager;

import com.megadev.scoca.object.boil.BoilBlock;
import com.megadev.scoca.object.boil.BoilState;
import com.megadev.scoca.object.boil.BoilerState;
import com.megadev.scoca.object.boil.FurnaceState;
import com.megadev.scoca.object.content.ContentStack;
import com.megadev.scoca.object.content.SCocaBlock;
import com.megadev.scoca.storage.BoilData;
import dev.mega.megacore.MegaCore;
import dev.mega.megacore.manager.Manager;
import lombok.Getter;

import java.util.UUID;

public class BoilManager extends Manager {
    @Getter
    private static BoilManager instance;
    private BoilData boilData;


    private BoilManager(MegaCore megaCore) {
        super(megaCore);
    }

    public static void init(MegaCore megaCore) {
        if (instance == null) {
            instance = new BoilManager(megaCore);
        }
    }

    @Override
    public void reload() {
        boilData = new BoilData();
    }

    @Override
    public void disable() {
        boilData = null;
    }

    public BoilState getBoilState(UUID uuid, SCocaBlock sCocaBlock) {
        BoilState boilState = boilData.getRegistered(uuid, getNewBoilState(sCocaBlock));

        if (boilState == null) {
            boilState = getNewBoilState(sCocaBlock);
            boilState.startDefaultAnimation();
            boilData.addValueForUuid(uuid, boilState);
        }

        return boilState;
    }

    public void removeBoilState(UUID uuid, SCocaBlock sCocaBlock) {
        BoilState boilState = boilData.getRegistered(uuid, sCocaBlock.getPluginBlock().getLocation());

        if (boilState == null) {
            return;
        }

        boilState.stopAnimation();
    }

    private BoilState getNewBoilState(SCocaBlock sCocaBlock) {
        ContentStack contentStack = sCocaBlock.getContentStack();
        BoilBlock boilBlock = BoilBlock.valueOf(contentStack.name());

        return switch (boilBlock) {
            case FURNACE -> new FurnaceState(megaCore, sCocaBlock);
            case BOILER -> new BoilerState(megaCore, sCocaBlock);
        };
    }


}
