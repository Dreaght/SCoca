package com.megadev.scoca.config.animation;

import com.megadev.scoca.object.animation.Animation;
import dev.mega.megacore.config.Configurator;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

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
            return new Animation(List.of());
        }

        ConfigurationSection stepsSection = config.getConfigurationSection("steps");
        List<Animation.Step> steps = new ArrayList<>();
        Set<String> stepKeys = stepsSection.getKeys(false);

        for (String stepKey : stepKeys) {
            steps.add(new Animation.Step(Animation.Command.valueOf(stepKey), stepsSection.getString(stepKey)));
        }

        return new Animation(steps);
    }
}
