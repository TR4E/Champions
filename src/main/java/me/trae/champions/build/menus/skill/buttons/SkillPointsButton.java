package me.trae.champions.build.menus.skill.buttons;

import me.trae.champions.build.menus.skill.SkillEditMenu;
import me.trae.core.client.Client;
import me.trae.core.menu.Button;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;

public class SkillPointsButton extends Button<SkillEditMenu> {

    public SkillPointsButton(final SkillEditMenu menu) {
        super(menu, 8, new ItemStack(Material.EMERALD));

        this.getItemBuilder().getItemStack().setAmount(this.getMenu().getManager().getSkillPoints(this.getMenu().getRole(), this.getMenu().getRoleBuild()));
    }

    @Override
    public String getDisplayName() {
        return "<green><bold>Skill Points";
    }

    @Override
    public String[] getLore() {
        return new String[0];
    }

    @Override
    public void onClick(final Player player, final Client client, final ClickType clickType) {
    }
}