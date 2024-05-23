package com.megadev.scoca.object.boil;

import com.megadev.scoca.manager.animation.AnimationInterpreter;
import com.megadev.scoca.object.block.PluginBlock;
import com.megadev.scoca.object.content.SCocaBlock;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;
import java.util.UUID;

@Getter
public class BoilState {
    private final UUID uuid;
    private final SCocaBlock sCocaBlock;
    @Setter private Stage currentStage;
    private final AnimationInterpreter animationInterpreter;

    public BoilState(UUID uuid, SCocaBlock sCocaBlock, AnimationInterpreter animationInterpreter) {
        this.uuid = uuid;
        this.sCocaBlock = sCocaBlock;
        this.animationInterpreter = animationInterpreter;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BoilState that = (BoilState) o;
        return Objects.equals(getSCocaBlock(), that.getSCocaBlock());
    }

    public void runAnimationInterpreter() {

    }
}
