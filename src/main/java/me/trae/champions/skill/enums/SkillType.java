package me.trae.champions.skill.enums;

import me.trae.framework.shared.utility.UtilFormat;

public enum SkillType implements ISkillType {

    SWORD, AXE, BOW, PASSIVE_A, PASSIVE_B, GLOBAL;

    private final String name;

    SkillType() {
        this.name = UtilFormat.cleanString(this.name());
    }

    @Override
    public String getName() {
        return this.name;
    }
}