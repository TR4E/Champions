package me.trae.champions.skill.types;

import me.trae.champions.role.Role;
import me.trae.champions.skill.Skill;
import me.trae.champions.skill.data.SkillData;
import me.trae.champions.skill.enums.SkillType;

public class PassiveSkill<R extends Role, SD extends SkillData> extends Skill<R, SD> {

    public PassiveSkill(final R manager, final SkillType skillType) {
        super(manager, skillType);
    }
}