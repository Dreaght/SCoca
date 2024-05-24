package com.megadev.scoca.object.boil;

import com.megadev.scoca.object.content.BoilMenu;
import com.megadev.scoca.object.content.SCocaBlock;
import lombok.Getter;

import java.util.Objects;
import java.util.UUID;

@Getter
public abstract class BoilState {
    protected final UUID uuid;
    protected final SCocaBlock sCocaBlock;
    protected BoilMenu boilMenu;

    public BoilState(UUID uuid, SCocaBlock sCocaBlock) {
        this.uuid = uuid;
        this.sCocaBlock = sCocaBlock;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BoilState that = (BoilState) o;
        return Objects.equals(getSCocaBlock(), that.getSCocaBlock());
    }
}
