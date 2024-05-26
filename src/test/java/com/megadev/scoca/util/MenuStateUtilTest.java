package com.megadev.scoca.util;

import com.megadev.scoca.object.menu.MenuState;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MenuStateUtilTest {

    @Test
    void testSizeEquals9() {
        List<MenuState.Slot> slots = new ArrayList<>();
        slots.add(new MenuState.Slot(9, null));

        MenuState menuState = new MenuState("someTitle", slots);

        int expected = 9;
        int actual = MenuStateUtil.getSize(menuState);

        assertEquals(expected, actual);
    }

    @Test
    void testSizeEquals27() {
        List<MenuState.Slot> slots = new ArrayList<>();
        slots.add(new MenuState.Slot(6, null));
        slots.add(new MenuState.Slot(17, null));
        slots.add(new MenuState.Slot(19, null));

        MenuState menuState = new MenuState("someTitle", slots);

        int expected = 27;
        int actual = MenuStateUtil.getSize(menuState);

        assertEquals(expected, actual);
    }
}