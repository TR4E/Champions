package me.trae.champions.role.menus;

import me.trae.champions.role.menus.interfaces.IRoleSelectionButton;
import me.trae.core.client.Client;
import me.trae.core.menu.Button;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;

public abstract class RoleSelectionButton extends Button<RoleSelectionMenu> implements IRoleSelectionButton {

    public RoleSelectionButton(final RoleSelectionMenu menu, final int slot, final ItemStack itemStack) {
        super(menu, slot, itemStack);
    }

    @Override
    public String getDisplayName() {
        return String.format("<green><bold>%s Class", this.getRole().getName());
    }

    @Override
    public String[] getLore() {
        return this.getRole().getDescription();
    }

    @Override
    public void onClick(final Player player, final Client client, final ClickType clickType) {
        if (!(Arrays.asList(ClickType.LEFT, ClickType.RIGHT).contains(clickType))) {
            return;
        }

        this.getMenu().onButtonClick(player, this.getRole());
    }
}