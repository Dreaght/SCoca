package com.megadev.scoca.manager.animation;

import com.megadev.scoca.config.ConfigManager;
import com.megadev.scoca.config.animation.AnimationConfigManager;
import com.megadev.scoca.config.animation.menu.MenuStateConfig;
import com.megadev.scoca.config.animation.menu.MenuStateManager;
import com.megadev.scoca.object.animation.Animation;
import com.megadev.scoca.object.block.PluginBlock;
import com.megadev.scoca.object.content.BoilMenu;
import com.megadev.scoca.object.content.Ingredient;
import com.megadev.scoca.object.item.PluginStack;
import com.megadev.scoca.util.MenuStateUtil;
import com.megadev.scoca.util.MetaUtil;
import dev.mega.megacore.MegaCore;
import dev.mega.megacore.manager.Manager;
import lombok.Getter;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Map;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

@Getter
public class AnimationInterpreter extends Manager {
    private final static ConfigManager configManager = ConfigManager.getInstance();

    private final PluginBlock pluginBlock;
    private BoilMenu boilMenu;
    private final Animation animation;

    private Map<Integer, PluginStack> ingredientMaps;

    private BukkitRunnable task;

    public AnimationInterpreter(
            MegaCore megaCore,
            PluginBlock pluginBlock,
            BoilMenu boilMenu,
            Animation animation,
            Map<Integer, PluginStack> ingredientMaps) {
        super(megaCore);

        this.pluginBlock = pluginBlock;
        this.boilMenu = boilMenu;
        this.animation = animation;
        this.ingredientMaps = ingredientMaps;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AnimationInterpreter that = (AnimationInterpreter) o;
        return Objects.equals(getPluginBlock(), that.getPluginBlock());
    }

    public void startAnimation() {
        if (animation.steps().isEmpty()) {
            return;
        }

        AtomicLong nextRunTime = new AtomicLong(0);
        AtomicInteger currentStepIndex = new AtomicInteger(0);

        task = new BukkitRunnable() {
            @Override
            public void run() {
                if (System.currentTimeMillis() < nextRunTime.get()) {
                    return;
                }

                if (currentStepIndex.getAndIncrement() < animation.steps().size()) {
                    Animation.Step step = animation.steps().get(currentStepIndex.get() - 1);
                    Animation.Command command = step.command();
                    String value = step.value();

                    switch (command) {
                        case DELAY -> {
                            long delay = Long.parseLong(value);
                            nextRunTime.set(System.currentTimeMillis() + (delay * 50)); // ticks -> milliseconds
                        }
                        case MENU -> {
                            MenuStateConfig menuStateConfig = getMenuStateConfig(value);

                            if (boilMenu == null) {
                                boilMenu = menuStateConfig.getBoilMenu(pluginBlock);
                            }

                            boilMenu.getInventory().setContents(menuStateConfig.getBoilMenu(pluginBlock).getInventory().getContents());

                            for (Map.Entry<Integer, PluginStack> entry : ingredientMaps.entrySet()) {
                                PluginStack stack = entry.getValue();

                                if (stack == null || stack.getItemMeta() == null) continue;

                                String content = MetaUtil.getItemMeta(stack.getItemMeta(), "content");
                                Ingredient ingredient = Ingredient.getIngredient(content);

                                int indexOfIngredient = MenuStateUtil.getIngredientIndex(boilMenu.getInventory(), ingredient);

                                ItemStack[] items = boilMenu.getInventory().getContents();

                                if (stack.getItemStack().getAmount() > 0)
                                    items[indexOfIngredient] = stack.getItemStack();

                                boilMenu.getInventory().setContents(items);
                            }
                        }
                        case PARTICLE -> {
                            // Code to spawn particle
                        }
                        case SOUND -> {
                            // Code to play sound
                        }
                    }

                } else {
                    currentStepIndex.set(0);


                }
            }
        };
        task.runTaskTimer(megaCore, 0, 1);
    }

    private MenuStateConfig getMenuStateConfig(String path) {
        AnimationConfigManager animationConfigManager = configManager.getConfig(AnimationConfigManager.class);
        MenuStateManager menuStateManager = animationConfigManager.getConfig(MenuStateManager.class);
        return menuStateManager.getMenuStateConfig(path);
    }

    @Override
    public void enable() {
        startAnimation();
    }

    @Override
    public void disable() {
        if (task != null)
            task.cancel();
    }
}
