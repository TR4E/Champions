package me.trae.champions.build;

import me.trae.champions.role.Role;
import me.trae.champions.skill.Skill;
import org.bukkit.entity.Player;

public class DefaultRoleBuild extends RoleBuild {

    public DefaultRoleBuild(final Player player, final Role role, final boolean active) {
        super(player, role, 0);

        this.setActive(active);

        for (final Skill<?, ?> skill : role.getModules(Skill.class)) {
            if (skill.getDefaultSkillLevel() <= 0) {
                continue;
            }

        }
    }
}