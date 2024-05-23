package com.megadev.scoca.manager;

import com.megadev.scoca.object.content.SCocaBlock;
import com.megadev.scoca.object.content.SCocaItem;
import com.megadev.scoca.storage.BlocksData;
import dev.mega.megacore.MegaCore;
import dev.mega.megacore.manager.Manager;
import lombok.Getter;

import java.util.UUID;

public class BlockManager extends Manager {
    @Getter private static BlockManager instance;
    private BlocksData blocksData;

    private BlockManager(MegaCore megaCore) {
        super(megaCore);
    }

    public static void init(MegaCore megaCore) {
        if (instance == null)
            instance = new BlockManager(megaCore);
    }

    @Override
    public void reload() {
        blocksData = new BlocksData();
    }

    @Override
    public void disable() {
        blocksData = null;
    }

    public boolean isRegistered(SCocaBlock block) {
        if (block != null) {
            return blocksData.contains(block);
        }
        return false;
    }

    public SCocaBlock validateAndGetSCocaBlock(SCocaBlock sCocaBlock) {
        return blocksData.getRegistered(sCocaBlock.getUuid(), sCocaBlock);
    }

    public void addBlock(UUID uuid, SCocaBlock block) {
        if (blocksData != null) {
            blocksData.addValueForUuid(uuid, block);
            System.out.println("blocks: " + blocksData.getValue(uuid));
        }
    }

    public void removeBlock(SCocaBlock block) {
        if (blocksData != null) {
            blocksData.removeValue(block);
        }
    }

    public void removeBlock(UUID uuid, SCocaBlock block) {
        if (blocksData != null) {
            blocksData.removeValueForUuid(uuid, block);
            System.out.println("blocks: " + blocksData.getValue(uuid));
        }
    }
}
