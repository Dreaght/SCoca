package com.megadev.scoca.object.content;

import com.megadev.scoca.manager.BoilManager;
import com.megadev.scoca.manager.BoilMenuManager;
import com.megadev.scoca.object.block.PluginBlock;
import dev.mega.megacore.inventory.MegaInventory;
import lombok.Getter;
import org.bukkit.Location;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import java.util.Objects;
import java.util.UUID;

@Getter
public class BoilMenu extends MegaInventory {
    private final Location location;
    private final int size;

    public BoilMenu(Location location, int size, String title) {
        super(size, title);
        this.location = location;
        this.size = size;
    }

    @Override
    public void onClick(InventoryClickEvent event) {
        UUID uuid = event.getWhoClicked().getUniqueId();

        ItemStack itemStack = event.getCursor();
        System.out.println("clickedInv: " + event.getClickedInventory());

        boolean isMenu = event.getRawSlot() == event.getSlot();

        if (isMenu) {
            BoilManager.getInstance().getBoilState(uuid, new PluginBlock(null, location));
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BoilMenu that = (BoilMenu) o;
        return Objects.equals(getLocation(), that.getLocation());
    }
}
