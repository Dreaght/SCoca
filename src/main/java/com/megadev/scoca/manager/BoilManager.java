package com.megadev.scoca.manager;

import com.megadev.scoca.object.block.PluginBlock;
import com.megadev.scoca.object.boil.BoilBlock;
import com.megadev.scoca.object.boil.BoilState;
import com.megadev.scoca.object.boil.BoilerState;
import com.megadev.scoca.object.boil.FurnaceState;
import com.megadev.scoca.object.content.ContentStack;
import com.megadev.scoca.object.item.PluginStack;
import com.megadev.scoca.storage.BoilData;
import com.megadev.scoca.util.MetaUtil;
import dev.mega.megacore.MegaCore;
import dev.mega.megacore.manager.Manager;
import lombok.Getter;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Objects;
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

    public BoilState getBoilState(UUID uuid, PluginBlock pluginBlock) {
        BoilState boilState = boilData.getRegistered(uuid, getNewBoilState(pluginBlock));

        if (boilState == null) {
            boilState = getNewBoilState(pluginBlock);
            boilState.startDefaultAnimation();
            boilState.startLifecycle();

            boilData.addValueForUuid(uuid, boilState);
        }

        return boilState;
    }

    public void removeBoilState(UUID uuid, PluginBlock pluginBlock) {
        BoilState boilState = boilData.getRegistered(uuid, pluginBlock.getLocation());

        if (boilState == null) {
            return;
        }

        boilState.stopAnimation();
        boilData.removeValueForUuid(uuid, boilState);
    }

    private BoilState getNewBoilState(PluginBlock pluginBlock) {
        PluginStack pluginStack = pluginBlock.getPluginStack();

        ContentStack contentStack = ContentStack.getContentStack(MetaUtil.getItemMeta(pluginStack.getItemStack(), "content"));

        if (contentStack == null) {
            return null;
        }

        BoilBlock boilBlock = BoilBlock.valueOf(contentStack.name());

        return switch (boilBlock) {
            case FURNACE -> new FurnaceState(megaCore, pluginBlock);
            case BOILER -> new BoilerState(megaCore, pluginBlock);
        };
    }


}
