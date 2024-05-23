package com.megadev.scoca.config;

import com.megadev.scoca.object.boil.Stage;
import dev.mega.megacore.config.Configurable;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents "boil.yml" config file access object.
 */
public class BoilConfig extends Configurable {
    /**
     * Constructs a Configurable object.
     *
     * @param plugin The plugin instance.
     * @param path   The path to the configuration file.
     */
    protected BoilConfig(@NotNull Plugin plugin, String... path) {
        super(plugin, path);
    }

    /**
     * Gets boiling stages from configuration.
     * @return List of boiling stages.
     */
    public List<Stage.ConfigStage> getStages() {
        List<Stage.ConfigStage> configStages = new ArrayList<>();

        for (String stageName : config.getKeys(false)) {
            configStages.add(getStage(stageName));
        }

        return configStages;
    }

    /**
     * Gets the stage from configuration.
     * @param stage Stage to get.
     * @return The stage.
     */
    public Stage.ConfigStage getStage(Stage stage) {
        return getStage(stage.getConfigPath());
    }

    /**
     * Gets the stage from configuration.
     * @param stageName Stage name.
     * @return The stage.
     */
    private Stage.ConfigStage getStage(String stageName) {
        String animationPath = config.getString("stages." + stageName + ".animation-path");
        return new Stage.ConfigStage(stageName, animationPath);
    }
}
