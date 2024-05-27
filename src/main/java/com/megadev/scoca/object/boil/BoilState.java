package com.megadev.scoca.object.boil;

import com.megadev.scoca.config.ConfigManager;
import com.megadev.scoca.config.MenuConfig;
import com.megadev.scoca.config.animation.AnimationConfigManager;
import com.megadev.scoca.object.animation.Animation;
import com.megadev.scoca.object.block.PluginBlock;
import com.megadev.scoca.object.menu.DefaultAnimationPath;
import com.megadev.scoca.manager.AnimationManager;
import dev.mega.megacore.MegaCore;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Getter
public abstract class BoilState {
    protected static final ConfigManager configManager = ConfigManager.getInstance();
    protected final MegaCore megaCore;
    protected final PluginBlock pluginBlock;
    @Setter protected AnimationManager animationInterpreter;

    public BoilState(MegaCore megaCore, PluginBlock pluginBlock) {
        this.megaCore = megaCore;
        this.pluginBlock = pluginBlock;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BoilState that = (BoilState) o;
        return Objects.equals(getPluginBlock(), that.getPluginBlock());
    }

    public void startDefaultAnimation() {
        Animation animation = getDefaultAnimation();
        animationInterpreter = new AnimationManager(megaCore, pluginBlock.getLocation(), null, animation);
        animationInterpreter.reload();
    }

    public void stopAnimation() {
        animationInterpreter.disable();
    }

    private Animation getDefaultAnimation() {
        MenuConfig menuConfig = configManager.getConfig(MenuConfig.class);
        DefaultAnimationPath defaultAnimationPath = DefaultAnimationPath.valueOf(getBoilBlock().name());

        String defaultAnimPath = menuConfig.getDefaultAnimationPath(defaultAnimationPath);

        AnimationConfigManager animationManager = configManager.getManager(AnimationConfigManager.class);
        return animationManager.getAnimationConfig(defaultAnimPath).getAnimation();
    }

    public abstract BoilBlock getBoilBlock();
}
