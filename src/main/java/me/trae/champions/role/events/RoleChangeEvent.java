package me.trae.champions.role.events;

import me.trae.champions.role.Role;
import me.trae.core.event.CustomCancellableEvent;
import org.bukkit.entity.Player;

public class RoleChangeEvent extends CustomCancellableEvent {

    private final Player player;
    private final Role role, oldRole;

    public RoleChangeEvent(final Player player, final Role role, final Role oldRole) {
        this.player = player;
        this.role = role;
        this.oldRole = oldRole;
    }

    public RoleChangeEvent(final Player player, final Role role) {
        this(player, role, null);
    }

    public Player getPlayer() {
        return this.player;
    }

    public Role getRole() {
        return this.role;
    }

    public Role getOldRole() {
        return this.oldRole;
    }
}