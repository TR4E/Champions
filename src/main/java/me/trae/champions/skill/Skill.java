package me.trae.champions.skill;

import me.trae.champions.role.Role;
import me.trae.core.framework.SpigotSubModule;

public class Skill<R extends Role> extends SpigotSubModule<R> {

    public Skill(final R module) {
        super(module);
    }
}