package me.trae.champions;

import me.trae.champions.build.BuildManager;
import me.trae.champions.role.RoleManager;
import me.trae.champions.role.types.*;
import me.trae.champions.skill.SkillManager;
import me.trae.core.energy.EnergyManager;
import me.trae.core.framework.MiniPlugin;

public class Champions extends MiniPlugin {

    @Override
    public void registerManagers() {
        addManager(EnergyManager.class);

        addManager(BuildManager.class);
        addManager(RoleManager.class);
        addManager(SkillManager.class);

        addManager(Assassin.class);
        addManager(Brute.class);
        addManager(Knight.class);
        addManager(Mage.class);
        addManager(Ranger.class);
    }
}