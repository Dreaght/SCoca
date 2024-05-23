package com.megadev.scoca.object.boil;

import com.megadev.scoca.object.content.SCocaBlock;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
public class BoilState {
    private final UUID uuid;
    private final SCocaBlock sCocaBlock;
    @Setter private Stage currentStage;

    public BoilState(UUID uuid, SCocaBlock sCocaBlock) {
        this.uuid = uuid;
        this.sCocaBlock = sCocaBlock;
    }
}
