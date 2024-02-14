package me.trae.champions.role.commands;

import me.trae.champions.role.RoleManager;
import me.trae.core.client.Client;
import me.trae.core.command.types.PlayerCommand;
import me.trae.framework.shared.client.enums.Rank;
import org.bukkit.entity.Player;

public class RoleCommand extends PlayerCommand<RoleManager> {

    public RoleCommand(final RoleManager manager) {
        super(manager, "role", new String[]{"class", "kit"}, Rank.DEFAULT);
    }

    @Override
    public void execute(final Player player, final Client client, final String[] args) {
    }
}