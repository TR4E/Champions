package me.trae.champions.build.menus.skill.buttons;

import me.trae.champions.build.data.RoleBuild;
import me.trae.champions.build.data.RoleSkill;
import me.trae.champions.build.menus.skill.SkillEditMenu;
import me.trae.champions.build.menus.skill.buttons.interfaces.ISkillSelectButton;
import me.trae.champions.skill.Skill;
import me.trae.core.client.Client;
import me.trae.core.menu.Button;
import me.trae.core.utility.objects.SoundCreator;
import me.trae.framework.shared.utility.UtilFormat;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class SkillSelectButton extends Button<SkillEditMenu> implements ISkillSelectButton {

    public SkillSelectButton(final SkillEditMenu menu, final int slot) {
        super(menu, slot, new ItemStack(Material.BOOK));

        this.getItemBuilder().setGlowing(this.getRoleSkill() != null);
    }

    @Override
    public String getDisplayName() {
        final RoleSkill roleSkill = this.getRoleSkill();
        final Skill<?, ?> skill = this.getSkill();

        String displayName = skill.getName();

        if (roleSkill != null) {
            displayName = "<green><bold>" + displayName;

            displayName += String.format(" (%s/%s)", roleSkill.getLevel(), skill.getMaxLevel());
        } else {
            displayName = "<red>" + displayName;
        }

        return displayName;
    }

    @Override
    public String[] getLore() {
        final int level = (this.getRoleSkill() != null ? this.getRoleSkill().getLevel() : 1);

        final List<String> lore = new ArrayList<>(Arrays.asList(this.getSkill().getDescription(level)));

        for (int i = 0; i < 2; i++) {
            lore.add(" ");
        }

        lore.add(String.format(UtilFormat.pairString("<yellow>Skill Token Cost", "<white>%s</white>"), this.getSkill().getTokenCost()));

        return lore.toArray(new String[0]);
    }

    @Override
    public void onClick(final Player player, final Client client, final ClickType clickType) {
        if (!(Arrays.asList(ClickType.LEFT, ClickType.RIGHT).contains(clickType))) {
            return;
        }

        switch (clickType) {
            case LEFT:
                this.onLeftClickButton(player, this.getMenu().getRoleBuild(), this.getSkill(), this.getRoleSkill());
                break;
            case RIGHT:
                this.onRightClickButton(player, this.getMenu().getRoleBuild(), this.getSkill(), this.getRoleSkill());
                break;
        }
    }

    private void onLeftClickButton(final Player player, final RoleBuild roleBuild, final Skill<?, ?> skill, final RoleSkill roleSkill) {
        final int skillPoints = this.getMenu().getManager().getSkillPoints(this.getMenu().getRole(), roleBuild);
        if (skillPoints <= 0 || skillPoints < skill.getTokenCost()) {
            return;
        }

        if (roleSkill != null) {
            if (roleSkill.getLevel() >= skill.getMaxLevel()) {
                new SoundCreator(Sound.ITEM_BREAK, 1.0F, 2.0F).play(player);
                return;
            }

            roleSkill.setLevel(roleSkill.getLevel() + 1);
        } else {
            roleBuild.addSkill(new RoleSkill(skill, 1));
        }

        new SoundCreator(Sound.ORB_PICKUP).play(player);

        if (!(this.getMenu().getManager().isRoleBuildByID(player, this.getMenu().getRole(), roleBuild.getID()))) {
            this.getMenu().getManager().addRoleBuild(player.getUniqueId(), roleBuild);
        }

//        this.getMenu().getManager().getRepository().saveData(roleBuild);

        this.getMenu().refresh();
    }

    private void onRightClickButton(final Player player, final RoleBuild roleBuild, final Skill<?, ?> skill, final RoleSkill roleSkill) {
        if (roleSkill == null) {
            new SoundCreator(Sound.ITEM_BREAK).play(player);
            return;
        }

        if (roleSkill.getLevel() > 1) {
            roleSkill.setLevel(roleSkill.getLevel() - 1);
        } else {
            roleBuild.removeSkill(roleSkill);

            if (!(roleBuild.getSkills().isEmpty())) {
//                this.getMenu().getManager().getRepository().saveData(roleBuild);
            } else {
                this.getMenu().getManager().removeRoleBuild(player.getUniqueId(), roleBuild);
//                this.getMenu().getManager().getRepository().deleteData(roleBuild);
            }
        }

        new SoundCreator(Sound.ORB_PICKUP).play(player);

        this.getMenu().refresh();
    }
}