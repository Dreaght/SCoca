package com.megadev.scoca.manager;

import com.megadev.scoca.object.block.PluginBlock;
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

    public void addBlock(UUID uuid, PluginBlock block) {
        if (blocksData != null) {
            blocksData.addValueForUuid(uuid, block);
        }
    }

    public void removeBlock(PluginBlock block) {
        if (blocksData != null) {
            blocksData.removeValue(block);
        }
    }

    public void removeBlock(UUID uuid, PluginBlock block) {
        if (blocksData != null) {
            blocksData.removeValueForUuid(uuid, block);
        }
    }
}
