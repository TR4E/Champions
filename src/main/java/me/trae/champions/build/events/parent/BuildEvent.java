package me.trae.champions.build.events.parent;

import me.trae.champions.build.RoleBuild;
import me.trae.champions.role.Role;
import me.trae.core.event.CustomEvent;
import org.bukkit.entity.Player;

public class BuildEvent extends CustomEvent {

    private final Player player;
    private final Role role;
    private final RoleBuild roleBuild;

    public BuildEvent(final Player player, final Role role, final RoleBuild roleBuild) {
        this.player = player;
        this.role = role;
        this.roleBuild = roleBuild;
    }

    public Player getPlayer() {
        return this.player;
    }

    public Role getRole() {
        return this.role;
    }

    public RoleBuild getRoleBuild() {
        return this.roleBuild;
    }
}