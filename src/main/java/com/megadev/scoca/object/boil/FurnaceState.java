package com.megadev.scoca.object.boil;

import com.megadev.scoca.object.content.Ingredient;
import com.megadev.scoca.object.content.SCocaBlock;

import java.util.UUID;

public class FurnaceState extends BoilState {
    private double fuelCapacity;
    private Ingredient firstIngredient; // fuel
    private Ingredient secondIngredient; // sulfur -> salt
    private Ingredient thirdIngredient; // herb
    private Ingredient fourthIngredient; // meph

    public FurnaceState(UUID uuid, SCocaBlock sCocaBlock) {
        super(uuid, sCocaBlock);
    }
}
