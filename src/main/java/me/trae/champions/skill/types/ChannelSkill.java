package me.trae.champions.skill.types;

import me.trae.champions.role.Role;
import me.trae.champions.skill.data.SkillData;
import me.trae.champions.skill.enums.SkillType;
import me.trae.champions.skill.types.interfaces.IChannelSkill;

public abstract class ChannelSkill<R extends Role, SD extends SkillData> extends ActiveSkill<R, SD> implements IChannelSkill {

    public ChannelSkill(final R manager) {
        super(manager, SkillType.SWORD);
    }
}