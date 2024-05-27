package com.megadev.scoca.storage;

import com.megadev.scoca.object.boil.BoilState;
import dev.mega.megacore.storage.ListData;
import org.bukkit.Location;

import java.util.UUID;

public class BoilData extends ListData<BoilState> {
    public BoilState getRegistered(UUID uuid, Location location) {
        return contains(uuid) ? getValue(uuid).stream().filter(boilState ->
                boilState.getPluginBlock().getLocation().equals(location))
                .findAny()
                .orElse(null) : null;
    }
}
