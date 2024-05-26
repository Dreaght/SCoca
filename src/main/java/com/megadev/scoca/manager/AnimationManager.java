package com.megadev.scoca.manager;

import com.megadev.scoca.object.animation.Animation;
import com.megadev.scoca.object.content.BoilMenu;
import dev.mega.megacore.MegaCore;
import dev.mega.megacore.manager.Manager;
import lombok.Getter;
import org.bukkit.Location;
import org.bukkit.scheduler.BukkitRunnable;

@Getter
public class AnimationManager extends Manager {
    private final Location location;
    private final BoilMenu boilMenu;
    private final Animation animation;

    private BukkitRunnable task;

    public AnimationManager(MegaCore megaCore, Location location, BoilMenu boilMenu, Animation animation) {
        super(megaCore);

        this.location = location;
        this.boilMenu = boilMenu;
        this.animation = animation;
    }

    public void startAnimation() {
        task = new BukkitRunnable() {
            @Override
            public void run() {
                new AnimationStepRunner().runTask(megaCore);
            }
        };
        task.runTaskTimer(megaCore, 0, 1);
    }

    private class AnimationStepRunner extends BukkitRunnable {
        private int currentStepIndex = 0;
        private long nextRunTime = 0;

        @Override
        public void run() {
            if (System.currentTimeMillis() < nextRunTime) {
                return;
            }

            if (currentStepIndex < animation.steps().size()) {
                Animation.Step step = animation.steps().get(currentStepIndex);
                Animation.Command command = step.command();
                String value = step.value();

                switch (command) {
                    case DELAY -> {
                        long delay = Long.parseLong(value);
                        nextRunTime = System.currentTimeMillis() + (delay * 50); // ticks -> milliseconds
                    }
                    case MENU -> {
                        // Code to manipulate player inventory
                        System.out.println("Menu command called: " + value);
                    }
                    case PARTICLE -> {
                        // Code to spawn particle
                    }
                    case SOUND -> {
                        // Code to play sound
                    }
                }
                currentStepIndex++;
            } else {
                this.cancel();
            }
        }
    }

    @Override
    public void reload() {
        disable();
        startAnimation();
    }

    @Override
    public void disable() {
        task.cancel();
    }
}
