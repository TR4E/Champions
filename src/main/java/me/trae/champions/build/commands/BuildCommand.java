package me.trae.champions.build.commands;

import me.trae.champions.build.BuildManager;
import me.trae.champions.build.menus.build.BuildCustomizationMenu;
import me.trae.champions.role.Role;
import me.trae.champions.role.RoleManager;
import me.trae.champions.role.menus.RoleSelectionMenu;
import me.trae.core.client.Client;
import me.trae.core.command.types.PlayerCommand;
import me.trae.framework.shared.client.enums.Rank;
import org.bukkit.entity.Player;

public class BuildCommand extends PlayerCommand<BuildManager> {

    public BuildCommand(final BuildManager manager) {
        super(manager, "build", new String[0], Rank.DEFAULT);
    }

    @Override
    public void execute(final Player player, final Client client, final String[] args) {
        if (args.length == 0) {
            new RoleSelectionMenu(this.getInstance().getManagerByClass(RoleManager.class), player) {
                @Override
                public void onButtonClick(final Player player, final Role role) {
                    new BuildCustomizationMenu(BuildCommand.this.getManager(), player, role) {
                        @Override
                        public Role getRole() {
                            return role;
                        }
                    };
                }
            };
        }
    }
}