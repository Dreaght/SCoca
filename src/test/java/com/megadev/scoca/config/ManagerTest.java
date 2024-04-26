package com.megadev.scoca.config;

import com.megadev.scoca.config.animation.menu.MenuManager;
import com.megadev.scoca.config.animation.menu.boiler.DefaultConfig;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.FileConfigurationOptions;
import org.bukkit.plugin.Plugin;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ManagerTest {
    private static final File testDataFolder = new File("src/test/resources/testDataFolder");

    @BeforeAll
    static void makeTestDataFolder() {
        testDataFolder.mkdir();
    }

    @BeforeEach
    void setup() {
        Plugin plugin = mock(Plugin.class);
        when(plugin.getLogger()).thenReturn(Logger.getGlobal());

        when(plugin.getDataFolder()).thenReturn(testDataFolder);

        FileConfiguration fileConfiguration = mock(FileConfiguration.class);
        when(fileConfiguration.options()).thenReturn(mock(FileConfigurationOptions.class));

        when(plugin.getConfig()).thenReturn(fileConfiguration);

        ConfigManager.init(plugin);
    }

    @AfterAll
    static void removeTestDataFolder() {
        testDataFolder.delete();
    }

    @Test
    void testDefaultConfig() {
        assertInstanceOf(DefaultConfig.class, ConfigManager.getInstance().getConfig(DefaultConfig.class));
    }

    @Test
    void testMenuManagerThrowsIllegalException() {
        assertThrows(IllegalArgumentException.class, () -> ConfigManager.getInstance().getConfig(MenuManager.class));
    }
}
