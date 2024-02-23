package me.trae.champions.skill.types;

import me.trae.champions.role.Role;
import me.trae.champions.skill.Skill;
import me.trae.champions.skill.data.SkillData;
import me.trae.champions.skill.enums.SkillType;
import me.trae.champions.skill.types.enums.ActiveSkillType;
import me.trae.champions.skill.types.interfaces.IActiveSkill;
import org.bukkit.entity.Player;

public abstract class ActiveSkill<R extends Role, D extends SkillData> extends Skill<R, D> implements IActiveSkill {

    public ActiveSkill(final R module, final ActiveSkillType skillType) {
        super(module, SkillType.valueOf(skillType.name()));
    }

    @Override
    public boolean isActive(final Player player) {
        return false;
    }
}