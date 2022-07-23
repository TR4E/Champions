package me.trae.champions.skill.types;

import me.trae.champions.role.Role;
import me.trae.champions.skill.Skill;
import me.trae.champions.skill.data.SkillData;
import me.trae.champions.skill.enums.SkillType;
import me.trae.champions.skill.types.interfaces.IActiveSkill;
import me.trae.core.utility.other.ActionType;

public abstract class ActiveSkill<R extends Role, SD extends SkillData> extends Skill<R, SD> implements IActiveSkill {

    public ActiveSkill(final R manager, final SkillType skillType) {
        super(manager, skillType);
    }

    @Override
    public ActionType getActionType() {
        return this.getSkillType().getActionType();
    }
}