package me.trae.champions.skill.types;

import me.trae.champions.role.Role;
import me.trae.champions.skill.Skill;
import me.trae.champions.skill.data.SkillData;
import me.trae.champions.skill.enums.SkillType;
import me.trae.champions.skill.types.interfaces.IDropSkill;

public abstract class DropSkill<R extends Role, SD extends SkillData> extends Skill<R, SD> implements IDropSkill {

    public DropSkill(final R manager) {
        super(manager, SkillType.SWORD_AND_AXE);
    }
}