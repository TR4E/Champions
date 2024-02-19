package me.trae.champions.role.menus;

import me.trae.champions.role.Role;
import me.trae.champions.role.RoleManager;
import me.trae.champions.role.menus.interfaces.IRoleSelectionMenu;
import me.trae.core.client.Client;
import me.trae.core.menu.Menu;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public abstract class RoleSelectionMenu extends Menu<RoleManager> implements IRoleSelectionMenu {

    public RoleSelectionMenu(final RoleManager manager, final Player player) {
        super(manager, player, 36, "<green><bold>Select a Class");
    }

    @Override
    public void fillPage(final Player player, final Client client) {
        int slot = 0;

        for (final Role role : this.getManager().getModulesByClass(Role.class)) {
            for (final Material material : role.getArmour()) {
                addButton(new RoleSelectionButton(this, slot, new ItemStack(material)) {
                    @Override
                    public Role getRole() {
                        return role;
                    }
                });

                slot += 9;
            }

            slot -= 34;
        }
    }
}