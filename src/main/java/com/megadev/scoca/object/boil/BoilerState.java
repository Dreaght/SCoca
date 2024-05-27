package com.megadev.scoca.object.boil;

import com.megadev.scoca.object.block.PluginBlock;
import com.megadev.scoca.object.content.Ingredient;
import com.megadev.scoca.object.item.PluginStack;
import dev.mega.megacore.MegaCore;
import lombok.Getter;
import org.jetbrains.annotations.Nullable;

@Getter
public class BoilerState extends BoilState {
    private Ingredient firstIngredient; // salt -> herb

    public BoilerState(MegaCore megaCore, PluginBlock pluginBlock) {
        super(megaCore, pluginBlock);
    }

    @Override
    protected void executeLifecycle() {

    }

    @Override
    public @Nullable PluginStack claimItem(int slot) {
        return null;
    }

    @Override
    public @Nullable PluginStack claimAllItems(int slot) {
        return null;
    }

    @Override
    public boolean injectItem(PluginStack pluginStack) {
        return false;
    }

    @Override
    public int getIngredientIndex(Ingredient ingredient) {
        return 1;
    }

    @Override
    public BoilBlock getBoilBlock() {
        return BoilBlock.BOILER;
    }
}
