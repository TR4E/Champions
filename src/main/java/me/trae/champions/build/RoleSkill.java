package me.trae.champions.build;

import me.trae.champions.build.interfaces.IRoleSkill;
import me.trae.champions.skill.Skill;

public class RoleSkill implements IRoleSkill {

    private final String name;
    private int level;

    public RoleSkill(final String name, final int level) {
        this.name = name;
        this.level = level;
    }

    public RoleSkill(final Skill<?, ?> skill, final int level) {
        this(skill.getName(), level);
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public int getLevel() {
        return this.level;
    }

    @Override
    public void setLevel(final int level) {
        this.level = level;
    }

    @Override
    public String getDisplayName() {
        return this.getName() + " " + this.getLevel();
    }
}