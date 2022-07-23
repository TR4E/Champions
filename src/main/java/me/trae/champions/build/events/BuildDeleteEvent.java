package me.trae.champions.build.events;

import me.trae.champions.build.RoleBuild;
import me.trae.champions.build.events.parent.BuildEvent;
import me.trae.champions.role.Role;
import org.bukkit.entity.Player;

public class BuildDeleteEvent extends BuildEvent {

    public BuildDeleteEvent(final Player player, final Role role, final RoleBuild roleBuild) {
        super(player, role, roleBuild);
    }
}