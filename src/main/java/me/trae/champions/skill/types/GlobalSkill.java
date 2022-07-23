package me.trae.champions.skill.types;

import me.trae.champions.role.Role;
import me.trae.champions.skill.Skill;
import me.trae.champions.skill.data.SkillData;
import me.trae.champions.skill.enums.SkillType;

public class GlobalSkill<R extends Role, SD extends SkillData> extends Skill<R, SD> {

    public GlobalSkill(final R manager) {
        super(manager, SkillType.GLOBAL);
    }
}