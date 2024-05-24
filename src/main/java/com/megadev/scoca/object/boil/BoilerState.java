package com.megadev.scoca.object.boil;

import com.megadev.scoca.object.content.Ingredient;
import com.megadev.scoca.object.content.SCocaBlock;

import java.util.UUID;

public class BoilerState extends BoilState {
    private Ingredient firstIngredient; // salt -> herb

    public BoilerState(UUID uuid, SCocaBlock sCocaBlock) {
        super(uuid, sCocaBlock);
    }
}
