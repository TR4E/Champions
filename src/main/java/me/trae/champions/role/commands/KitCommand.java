package me.trae.champions.role.commands;

import me.trae.champions.role.Role;
import me.trae.champions.role.RoleManager;
import me.trae.champions.role.menus.ClassSelectionMenu;
import me.trae.core.command.types.PlayerCommand;
import me.trae.framework.shared.client.Rank;
import org.bukkit.entity.Player;

public class KitCommand extends PlayerCommand<RoleManager> {

    public KitCommand(final RoleManager manager) {
        super(manager, "kit", new String[]{"class"}, Rank.ADMIN);
    }

    @Override
    public void execute(final Player player, final String[] args) {
        new ClassSelectionMenu(getManager(), player) {
            @Override
            protected void onButtonClick(final Player player, final Role role) {
                getManager().giveKit(player, role);
            }
        }.openInventory();
    }

    @Override
    public void help(final Player player) {
    }
}