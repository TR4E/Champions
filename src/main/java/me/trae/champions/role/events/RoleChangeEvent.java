package me.trae.champions.role.events;

import me.trae.champions.role.Role;
import me.trae.champions.role.events.interfaces.IRoleEvent;
import me.trae.core.event.CustomEvent;
import me.trae.core.event.types.IPlayerEvent;
import org.bukkit.entity.Player;

public class RoleChangeEvent extends CustomEvent implements IPlayerEvent, IRoleEvent {

    private final Player player;
    private final Role role;

    public RoleChangeEvent(final Player player, final Role role) {
        this.player = player;
        this.role = role;
    }

    @Override
    public Player getPlayer() {
        return this.player;
    }

    @Override
    public Role getRole() {
        return this.role;
    }
}