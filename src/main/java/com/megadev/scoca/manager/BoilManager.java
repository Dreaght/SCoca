package com.megadev.scoca.manager;

import com.megadev.scoca.manager.animation.AnimationInterpreter;
import com.megadev.scoca.object.boil.BoilBlock;
import com.megadev.scoca.object.boil.BoilState;
import com.megadev.scoca.object.boil.BoilerState;
import com.megadev.scoca.object.boil.FurnaceState;
import com.megadev.scoca.object.content.ContentStack;
import com.megadev.scoca.object.content.SCocaBlock;
import com.megadev.scoca.object.content.BoilMenu;
import com.megadev.scoca.storage.BoilData;
import dev.mega.megacore.MegaCore;
import dev.mega.megacore.manager.Manager;
import lombok.Getter;
import org.bukkit.Location;

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

    public BoilMenu verifyAndGetMenu(UUID uuid, BoilState similarBoilState) {
        BoilState boilState = boilData.getRegistered(uuid, similarBoilState);

        if (boilState == null) {
            boilState = getNewBoilState(uuid, similarBoilState);
            boilData.addValueForUuid(uuid, boilState);
        }

        return boilState.getAnimationInterpreter().getMenu();
    }

    private BoilState getNewBoilState(UUID uuid, BoilState similarBoilState) {
        SCocaBlock sCocaBlock = similarBoilState.getSCocaBlock();
        ContentStack contentStack = sCocaBlock.getContentStack();

        BoilBlock boilBlock = BoilBlock.valueOf(contentStack.name());

        return switch (boilBlock) {
            case FURNACE -> new FurnaceState(uuid, sCocaBlock);
            case BOILER -> new BoilerState(uuid, sCocaBlock);
        };
    }
}
