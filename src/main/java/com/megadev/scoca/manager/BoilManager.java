package com.megadev.scoca.manager;

import com.megadev.scoca.manager.animation.AnimationInterpreter;
import com.megadev.scoca.object.boil.BoilState;
import com.megadev.scoca.object.content.ContentStack;
import com.megadev.scoca.object.content.SCocaBlock;
import com.megadev.scoca.object.content.menu.BoilMenu;
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

        switch (contentStack) {
            case FURNACE ->
        }

        AnimationInterpreter animationInterpreter = new AnimationInterpreter(megaCore, null, sCocaBlock.getPluginBlock().getLocation());
        return new BoilState(uuid, sCocaBlock, animationInterpreter);
    }
}
