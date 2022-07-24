package me.trae.champions.build.interfaces;

import me.trae.champions.build.RoleSkill;
import me.trae.champions.role.Role;
import me.trae.champions.skill.Skill;
import me.trae.champions.skill.enums.SkillType;

import java.util.Map;
import java.util.UUID;

public interface IRoleBuild {

    UUID getUUID();

    String getRoleName();

    int getID();

    Map<SkillType, RoleSkill> getRoleSkills();

    void addRoleSkill(final Skill<?, ?> skill, final int level);

    RoleSkill getRoleSkill(final SkillType skillType);

    boolean isRoleSkill(final Skill<?, ?> skill);

    boolean isActive();

    void setActive(final boolean active);

    String getDisplayName();

    int getSkillPoints(final Role role);
}