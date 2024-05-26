package com.megadev.scoca.object.boil;

import com.megadev.scoca.object.content.Ingredient;
import com.megadev.scoca.object.content.SCocaBlock;
import dev.mega.megacore.MegaCore;
import lombok.Getter;

import java.util.UUID;

@Getter
public class FurnaceState extends BoilState {
    private double fuelCapacity;
    private Ingredient firstIngredient; // fuel
    private Ingredient secondIngredient; // sulfur -> salt
    private Ingredient thirdIngredient; // herb
    private Ingredient fourthIngredient; // meph

    public FurnaceState(MegaCore megaCore, SCocaBlock sCocaBlock) {
        super(megaCore, sCocaBlock);
    }

    @Override
    public BoilBlock getBoilBlock() {
        return BoilBlock.FURNACE;
    }
}
