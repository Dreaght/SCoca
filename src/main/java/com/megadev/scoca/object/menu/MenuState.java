package com.megadev.scoca.object.menu;

import com.megadev.scoca.object.item.PluginStack;

import java.util.List;

public record MenuState(String title, List<Slot> slots) {
    public record Item(String title, PluginStack pluginStack) {}

    public record Slot(int index, Item slotPattern) {}
}
