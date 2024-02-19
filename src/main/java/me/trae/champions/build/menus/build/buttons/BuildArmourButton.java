package me.trae.champions.build.menus.build.buttons;

import me.trae.champions.build.menus.build.BuildCustomizationMenu;
import me.trae.core.client.Client;
import me.trae.core.menu.Button;
import me.trae.framework.shared.utility.UtilFormat;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;

public class BuildArmourButton extends Button<BuildCustomizationMenu> {

    public BuildArmourButton(final BuildCustomizationMenu menu, final int slot, final ItemStack itemStack) {
        super(menu, slot, itemStack);
    }

    @Override
    public String getDisplayName() {
        return String.format("<green><bold>%s %s", this.getMenu().getRole().getName(), UtilFormat.cleanString(this.getItemBuilder().getItemStack().getType().name().split("_")[1]));
    }

    @Override
    public String[] getLore() {
        return new String[0];
    }

    @Override
    public void onClick(final Player player, final Client client, final ClickType clickType) {
    }
}