package me.trae.champions.build.data;

import me.trae.champions.build.data.interfaces.IRoleSkill;
import me.trae.champions.skill.Skill;
import me.trae.framework.shared.utility.UtilFormat;

import java.util.Arrays;

public class RoleSkill implements IRoleSkill {

    private final String type, name;

    private int level;

    public RoleSkill(final String type, final String name, final int level) {
        this.type = type;
        this.name = name;
        this.level = level;
    }

    public RoleSkill(final Skill<?, ?> skill, final int level) {
        this(skill.getType().name(), skill.getName(), level);
    }

    public RoleSkill(final String[] tokens) {
        this(tokens[0], UtilFormat.unSliceString(tokens[1]), Integer.parseInt(tokens[2]));
    }

    @Override
    public String getType() {
        return this.type;
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

    @Override
    public String toString() {
        return String.join(":", Arrays.asList(this.getType(), UtilFormat.sliceString(this.getName()), String.valueOf(this.getLevel())));
    }
}