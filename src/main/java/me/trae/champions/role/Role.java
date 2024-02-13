package me.trae.champions.role;

import me.trae.champions.role.interfaces.IRole;
import me.trae.core.framework.SpigotModule;

public abstract class Role extends SpigotModule<RoleManager> implements IRole {

    public Role(final RoleManager manager) {
        super(manager);
    }
}