package me.trae.champions.build.menus.skill.buttons;

import me.trae.champions.build.menus.skill.SkillEditMenu;
import me.trae.champions.build.menus.skill.buttons.interfaces.ISkillTypeButton;
import me.trae.champions.skill.enums.SkillType;
import me.trae.core.client.Client;
import me.trae.core.menu.Button;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;

public abstract class SkillTypeButton extends Button<SkillEditMenu> implements ISkillTypeButton {

    public SkillTypeButton(final SkillEditMenu menu, final int slot, final SkillType skillType) {
        super(menu, slot, skillType.getDisplayItemStack());
    }

    @Override
    public String getDisplayName() {
        return String.format("<green><bold>%s Skills", this.getSkillType().getName());
    }

    @Override
    public String[] getLore() {
        return new String[0];
    }

    @Override
    public void onClick(final Player player, final Client client, final ClickType clickType) {
    }
}