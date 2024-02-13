package me.trae.champions.role;

import me.trae.champions.role.interfaces.IRole;
import me.trae.core.framework.SpigotModule;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public abstract class Role extends SpigotModule<RoleManager> implements IRole {

    public Role(final RoleManager manager) {
        super(manager);
    }

    @Override
    public List<Player> getUsers() {
        final List<Player> list = new ArrayList<>();

        for (final Map.Entry<UUID, Role> entry : this.getManager().getPlayerRoles().entrySet()) {
            if (entry.getValue() != this) {
                continue;
            }

            final Player player = Bukkit.getPlayer(entry.getKey());
            if (player == null) {
                continue;
            }

            list.add(player);
        }

        return list;
    }

    @Override
    public boolean isUserByPlayer(final Player player) {
        return this.getManager().getPlayerRole(player) == this;
    }
}