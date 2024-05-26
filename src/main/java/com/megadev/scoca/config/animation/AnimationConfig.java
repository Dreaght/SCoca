package com.megadev.scoca.config.animation;

import com.megadev.scoca.object.animation.Animation;
import dev.mega.megacore.config.Configurator;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;

import java.util.*;

/**
 * Represents an animation config file access.
 */
public class AnimationConfig extends Configurator {
    /**
     * Constructs a Configurable object.
     *
     * @param plugin The plugin instance.
     * @param path   The path to the configuration file.
     */
    protected AnimationConfig(@NotNull Plugin plugin, String... path) {
        super(plugin, path);
    }

    public Animation getAnimation() {
        if (!config.contains("steps")) {
            return new Animation(new ArrayList<>());
        }

        List<Map<?, ?>> stepsSections = config.getMapList("steps");
        List<Animation.Step> steps = new ArrayList<>();

        for (Map<?, ?> stepSection : stepsSections) {
            for (Map.Entry<?, ?> entry : stepSection.entrySet()) {
                String key = entry.getKey().toString().strip();
                String value = entry.getValue().toString().strip();

                Animation.Command command;
                try {
                    command = Animation.Command.valueOf(key);
                } catch (IllegalArgumentException e) {
                    throw new RuntimeException("Invalid command: " + key);
                }

                steps.add(new Animation.Step(command, value));
            }
        }

        return new Animation(steps);
    }
}
