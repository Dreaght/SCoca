package com.megadev.scoca.manager;

import com.megadev.scoca.config.ConfigManager;
import com.megadev.scoca.config.MenuConfig;
import com.megadev.scoca.config.animation.AnimationConfig;
import com.megadev.scoca.config.animation.AnimationManager;
import com.megadev.scoca.manager.animation.AnimationInterpreter;
import com.megadev.scoca.object.animation.Animation;
import com.megadev.scoca.object.boil.BoilState;
import com.megadev.scoca.object.content.ContentStack;
import com.megadev.scoca.object.content.SCocaBlock;
import com.megadev.scoca.object.menu.DefaultAnimationPath;
import com.megadev.scoca.util.MetaUtil;
import dev.mega.megacore.MegaCore;
import dev.mega.megacore.manager.Manager;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.UUID;

public class MenuManager extends Manager {
    @Getter
    private static MenuManager instance;
    private final ConfigManager configManager = ConfigManager.getInstance();

    public MenuManager(MegaCore megaCore) {
        super(megaCore);
    }

    public static void init(MegaCore megaCore) {
        if (instance == null) {
            instance = new MenuManager(megaCore);
        }
    }

    @Override
    public void reload() {

    }

    @Override
    public void disable() {

    }

    public void openMenu(UUID uuid, SCocaBlock sCocaBlock) {
        Player player = Bukkit.getPlayer(uuid);

        if (player == null) return;

        String content = MetaUtil.getItemMeta(sCocaBlock.getPluginBlock().getPluginStack().getItemMeta(), "content");
        ContentStack contentStack = ContentStack.getContentStack(content);

        if (contentStack == null) return;

        ///



        MenuConfig menuConfig = configManager.getConfig(MenuConfig.class);
        String animationPath = menuConfig.getDefaultAnimationPath(DefaultAnimationPath.valueOf(contentStack.name()));

        AnimationConfig animationConfig = configManager.getManager(AnimationManager.class).getAnimationConfig(animationPath);
        Animation animation = animationConfig.getAnimation();

        AnimationInterpreter animationInterpreter = new AnimationInterpreter();

        ///

        BoilState boilState = new BoilState(uuid, sCocaBlock, null);
    }
}
