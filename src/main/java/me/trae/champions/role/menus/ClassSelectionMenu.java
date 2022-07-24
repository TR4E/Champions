package me.trae.champions.role.menus;

import me.trae.champions.role.Role;
import me.trae.champions.role.RoleManager;
import me.trae.core.menu.Button;
import me.trae.core.menu.Menu;
import me.trae.framework.utility.other.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;

public abstract class ClassSelectionMenu extends Menu<RoleManager> {

    public ClassSelectionMenu(final RoleManager manager, final Player player) {
        super(manager, 36, ChatColor.GREEN.toString() + ChatColor.BOLD + "Select a Class", player);
    }

    @Override
    public void fillPage() {
        int slot = 0;

        for (final Role role : getInstance().getManagers(Role.class)) {
            final String displayName = ChatColor.YELLOW.toString() + ChatColor.BOLD + role.getName() + " Class";

            for (final Material material : role.getArmour()) {
                addButton(new Button<ClassSelectionMenu>(this, slot, displayName, new ItemStack(material)) {
                    @Override
                    public void onClick(final Player player, final ClickType clickType) {
                        switch (clickType) {
                            case LEFT:
                            case RIGHT:
                                break;
                            default:
                                return;
                        }

                        ClassSelectionMenu.this.onButtonClick(player, role);
                    }

                    @Override
                    public String[] getLore() {
                        return role.getDescription();
                    }
                });

                slot += 9;
            }

            slot -= 34;
        }
    }

    protected abstract void onButtonClick(final Player player, final Role role);
}