package com.megadev.scoca.manager.animation;

import com.megadev.scoca.config.MenuConfig;
import com.megadev.scoca.config.animation.AnimationConfigManager;
import com.megadev.scoca.object.animation.Animation;
import com.megadev.scoca.object.block.PluginBlock;
import com.megadev.scoca.object.menu.DefaultAnimationPath;
import com.megadev.scoca.storage.AnimationData;
import dev.mega.megacore.MegaCore;
import dev.mega.megacore.manager.Manager;
import lombok.Getter;

import java.util.UUID;

@Getter
public class AnimationManager extends Manager {
    private static AnimationManager instance;
    private AnimationData animationData;

    private AnimationManager(MegaCore megaCore) {
        super(megaCore);
    }

    public static void init(MegaCore megaCore) {
        if (instance == null) {
            instance = new AnimationManager(megaCore);
        }
    }

    @Override
    public void reload() {
        animationData = new AnimationData();
    }

    @Override
    public void disable() {
        animationData = null;
    }

    public void addAnimationInterpreter(UUID uuid, AnimationInterpreter animationInterpreter) {
        animationData.addValueForUuid(uuid, animationInterpreter);
    }

    public void startDefaultAnimation() {
        Animation animation = getDefaultAnimation();
        AnimationInterpreter animationInterpreter = new AnimationInterpreter(megaCore, pluginBlock, null, animation);
        animationInterpreter.reload();
    }

    public void stopAnimation(UUID uuid, PluginBlock pluginBlock) {
        AnimationInterpreter animationInterpreter = animationData.getRegistered(uuid, new AnimationInterpreter(null, pluginBlock, null, null));
    }

    private Animation getDefaultAnimation() {
        MenuConfig menuConfig = configManager.getConfig(MenuConfig.class);
        DefaultAnimationPath defaultAnimationPath = DefaultAnimationPath.valueOf(getBoilBlock().name());

        String defaultAnimPath = menuConfig.getDefaultAnimationPath(defaultAnimationPath);

        AnimationConfigManager animationManager = configManager.getManager(AnimationConfigManager.class);
        return animationManager.getAnimationConfig(defaultAnimPath).getAnimation();
    }
}
