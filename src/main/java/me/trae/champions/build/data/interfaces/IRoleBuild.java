package me.trae.champions.build.data.interfaces;

import me.trae.champions.build.data.RoleSkill;
import me.trae.champions.skill.enums.SkillType;

import java.util.LinkedHashMap;

public interface IRoleBuild {

    int getID();

    String getName();

    LinkedHashMap<String, RoleSkill> getSkills();

    void addSkill(final RoleSkill roleSkill);

    void removeSkill(final RoleSkill roleSkill);

    RoleSkill getSkillByType(final SkillType skillType);

    boolean isSkillByType(final SkillType skillType);

    boolean isActive();

    void setActive(final boolean active);
}