package com.megadev.scoca.object.boil;

import com.megadev.scoca.object.content.Ingredient;
import com.megadev.scoca.object.content.SCocaBlock;
import dev.mega.megacore.MegaCore;
import lombok.Getter;

import java.util.UUID;

@Getter
public class BoilerState extends BoilState {
    private Ingredient firstIngredient; // salt -> herb

    public BoilerState(MegaCore megaCore, SCocaBlock sCocaBlock) {
        super(megaCore, sCocaBlock);
    }

    @Override
    public BoilBlock getBoilBlock() {
        return BoilBlock.BOILER;
    }
}
