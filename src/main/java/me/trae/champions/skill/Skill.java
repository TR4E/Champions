package me.trae.champions.skill;

import me.trae.champions.role.Role;
import me.trae.champions.skill.data.SkillData;
import me.trae.champions.skill.enums.SkillType;
import me.trae.champions.skill.interfaces.ISkill;
import me.trae.core.framework.SpigotSubModule;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class Skill<R extends Role, D extends SkillData> extends SpigotSubModule<R> implements ISkill<D> {

    private final SkillType skillType;
    private final Map<UUID, D> users;

    public Skill(final R module, final SkillType skillType) {
        super(module);

        this.skillType = skillType;
        this.users = new HashMap<>();
    }

    @Override
    public SkillType getType() {
        return this.skillType;
    }

    @Override
    public Map<UUID, D> getUsers() {
        return this.users;
    }
}