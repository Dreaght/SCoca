package com.megadev.scoca.object.boil;

import com.megadev.scoca.object.block.PluginBlock;
import com.megadev.scoca.object.content.Ingredient;
import dev.mega.megacore.MegaCore;
import lombok.Getter;

@Getter
public class BoilerState extends BoilState {
    private Ingredient firstIngredient; // salt -> herb

    public BoilerState(MegaCore megaCore, PluginBlock pluginBlock) {
        super(megaCore, pluginBlock);
    }

    @Override
    public BoilBlock getBoilBlock() {
        return BoilBlock.BOILER;
    }
}
