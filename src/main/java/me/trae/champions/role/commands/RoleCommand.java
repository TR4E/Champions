package me.trae.champions.role.commands;

import me.trae.champions.role.Role;
import me.trae.champions.role.RoleManager;
import me.trae.champions.role.menus.RoleSelectionMenu;
import me.trae.core.command.types.Command;
import me.trae.core.utility.UtilMessage;
import me.trae.core.utility.UtilPlayer;
import me.trae.framework.shared.client.enums.Rank;
import me.trae.framework.shared.utility.UtilJava;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Arrays;

public class RoleCommand extends Command<RoleManager> {

    public RoleCommand(final RoleManager manager) {
        super(manager, "role", new String[]{"class", "kit"}, Rank.DEFAULT);
    }

    @Override
    public String getDescription() {
        return "Receive a Role Kit";
    }

    @Override
    public void execute(final CommandSender sender, final String[] args) {
        if (args.length == 0) {
            if (!(this.isValidSender(sender, Player.class, true))) {
                return;
            }

            new RoleSelectionMenu(this.getManager(), UtilJava.cast(Player.class, sender)) {
                @Override
                public void onButtonClick(final Player player, final Role role) {
                    player.closeInventory();

                    this.getManager().giveRole(player, role);
                }
            };
            return;
        }

        final Role role = this.getManager().searchRole(sender, args[0], true);
        if (role == null) {
            return;
        }

        if (args.length == 1) {
            if (!(this.isValidSender(sender, Player.class, true))) {
                return;
            }

            final Player player = UtilJava.cast(Player.class, sender);

            this.getManager().giveRole(player, role);
        }

        if (args.length == 2) {
            final Player target = UtilPlayer.searchPlayer(sender, args[1], true);
            if (target == null) {
                return;
            }

            if (target == sender) {
                this.execute(sender, new String[]{role.getName()});
                return;
            }

            this.getManager().giveRole(target, role);

            UtilMessage.simpleMessage(sender, "Kit", "You gave <green><var> Class</green> to <yellow><var></yellow>.", Arrays.asList(role.getName(), target.getName()));
            UtilMessage.simpleMessage(target, "Kit", "<yellow><var></yellow> gave you <green><var> Class</green>.", Arrays.asList(sender.getName(), role.getName()));
        }
    }
}