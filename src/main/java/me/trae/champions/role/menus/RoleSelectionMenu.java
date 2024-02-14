package me.trae.champions.role.menus;

import me.trae.champions.role.Role;
import me.trae.champions.role.RoleManager;
import me.trae.champions.role.menus.interfaces.IRoleSelectionMenu;
import me.trae.core.client.Client;
import me.trae.core.menu.Button;
import me.trae.core.menu.Menu;
import me.trae.framework.shared.utility.enums.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;

public abstract class RoleSelectionMenu extends Menu<RoleManager> implements IRoleSelectionMenu {

    public RoleSelectionMenu(final RoleManager manager, final Player player) {
        super(manager, player, 36, ChatColor.build(ChatColor.DARK_GREEN, ChatColor.BOLD) + "Select a Class");
    }

    @Override
    public void fillPage(final Player player, final Client client) {
        int slot = 0;

        for (final Role role : this.getManager().getModulesByClass(Role.class)) {
            for (final Material material : role.getArmour()) {
                addButton(this.getButton(slot, role, new ItemStack(material)));

                slot += 9;
            }

            slot -= 34;
        }
    }

    private Button<RoleSelectionMenu> getButton(final int slot, final Role role, final ItemStack itemStack) {
        return new Button<RoleSelectionMenu>(this, slot, itemStack) {
            @Override
            public String getDisplayName() {
                return ChatColor.build(ChatColor.GREEN, ChatColor.BOLD) + String.join("{} Class", role.getName());
            }

            @Override
            public String[] getLore() {
                return role.getDescription();
            }

            @Override
            public void onClick(final Player player, final Client client, final ClickType clickType) {
                if (!(Arrays.asList(ClickType.LEFT, ClickType.RIGHT).contains(clickType))) {
                    return;
                }

                RoleSelectionMenu.this.onButtonClick(player, role);
            }
        };
    }
}