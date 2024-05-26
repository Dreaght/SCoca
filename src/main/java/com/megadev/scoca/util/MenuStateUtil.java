package com.megadev.scoca.util;

import com.megadev.scoca.object.content.Ingredient;
import com.megadev.scoca.object.menu.MenuState;

import java.util.List;
import java.util.Optional;

public class MenuStateUtil {
    public static int getSize(MenuState menuState) {
        List<MenuState.Slot> slots = menuState.slots();

        Optional<Integer> maxSlotOpt = slots.stream().map(MenuState.Slot::index).max(Integer::compare);
        if (maxSlotOpt.isEmpty()) {
            return 9;
        }
        int maxSlot = maxSlotOpt.get();

        return 9 * (((int) (maxSlot / 9)) + ((maxSlot % 9) == 0 ? 0 : 1));
    }

    public static int getIngredientIndex(MenuState menuState, Ingredient ingredient) {
        List<MenuState.Slot> slots = menuState.slots();

        for (MenuState.Slot slot : slots) {
            String content = MetaUtil.getItemMeta(slot.slotPattern().pluginStack().getItemStack(), "content");

            if (ingredient.getTitle().equals(content)) {
                return slot.index();
            }
        }
        return 0;
    }
}
