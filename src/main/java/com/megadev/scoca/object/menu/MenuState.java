package com.megadev.scoca.object.menu;

import com.megadev.scoca.object.item.Item;

import java.util.List;

public record MenuState(String title, List<Slot> slots) {
    public record Slot(int index, Item slotPattern) {}
}
