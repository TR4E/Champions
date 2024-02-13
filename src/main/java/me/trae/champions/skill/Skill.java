package me.trae.champions.skill;

import me.trae.champions.role.Role;
import me.trae.champions.skill.enums.SkillType;
import me.trae.champions.skill.interfaces.ISkill;
import me.trae.core.framework.SpigotSubModule;

public class Skill<R extends Role> extends SpigotSubModule<R> implements ISkill {

    private final SkillType skillType;

    public Skill(final R module, final SkillType skillType) {
        super(module);

        this.skillType = skillType;
    }

    @Override
    public SkillType getType() {
        return this.skillType;
    }
}