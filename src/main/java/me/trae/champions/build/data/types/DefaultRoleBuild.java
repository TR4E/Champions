package me.trae.champions.build.data.types;

import me.trae.champions.build.data.RoleBuild;
import me.trae.champions.build.data.RoleSkill;
import me.trae.champions.role.Role;
import me.trae.champions.skill.Skill;

import java.util.UUID;

public class DefaultRoleBuild extends RoleBuild {

    public DefaultRoleBuild(final UUID uuid, final Role role) {
        super(uuid, role, 0);

        for (final Skill<?, ?> skill : role.getSubModulesByClass(Skill.class)) {
            if (skill.getDefaultLevel() <= 0) {
                continue;
            }

            this.addSkill(new RoleSkill(skill, skill.getDefaultLevel()));
        }
    }
}