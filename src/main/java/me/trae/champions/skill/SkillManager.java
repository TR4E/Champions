package me.trae.champions.skill;

import me.trae.champions.skill.modules.HandleSkillLevelOnBoosterWeapons;
import me.trae.core.framework.SpigotManager;
import me.trae.core.framework.SpigotPlugin;

public class SkillManager extends SpigotManager {

    public SkillManager(final SpigotPlugin instance) {
        super(instance);
    }

    @Override
    public void registerModules() {
        addModule(new HandleSkillLevelOnBoosterWeapons(this));
    }
}