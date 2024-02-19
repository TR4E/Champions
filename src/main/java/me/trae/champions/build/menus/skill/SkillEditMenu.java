package me.trae.champions.build.menus.skill;

import me.trae.champions.build.BuildManager;
import me.trae.champions.build.data.RoleBuild;
import me.trae.champions.build.data.RoleSkill;
import me.trae.champions.build.menus.skill.buttons.SkillPointsButton;
import me.trae.champions.build.menus.skill.buttons.SkillSelectButton;
import me.trae.champions.build.menus.skill.buttons.SkillTypeButton;
import me.trae.champions.build.menus.skill.interfaces.ISkillEditMenu;
import me.trae.champions.role.Role;
import me.trae.champions.skill.Skill;
import me.trae.champions.skill.enums.SkillType;
import me.trae.core.client.Client;
import me.trae.core.menu.Menu;
import org.bukkit.entity.Player;

public abstract class SkillEditMenu extends Menu<BuildManager> implements ISkillEditMenu {

    public SkillEditMenu(final BuildManager manager, final Player player, final Role role, final RoleBuild roleBuild) {
        super(manager, player, 54, String.format("<green><bold>%s Skill Page %s", role.getName(), roleBuild.getID()));
    }

    @Override
    public void fillPage(final Player player, final Client client) {
        addButton(new SkillPointsButton(this));

        this.addSkillTypeButtons();
        this.addSkillButtons();
    }

    private void addSkillTypeButtons() {
        int slot = 0;

        for (final SkillType skillType : SkillType.values()) {
            addButton(new SkillTypeButton(this, slot, skillType) {
                @Override
                public SkillType getSkillType() {
                    return skillType;
                }
            });

            slot += 9;
        }
    }

    private void addSkillButtons() {
        int index = 1;
        int slot = 1;

        for (final SkillType skillType : SkillType.values()) {
            for (final Skill<?, ?> skill : this.getRole().getSkillsByType(skillType)) {
                final RoleSkill roleSkill = this.getRoleBuild().getSkillByType(skillType);

                addButton(new SkillSelectButton(this, slot) {
                    @Override
                    public Skill<?, ?> getSkill() {
                        return skill;
                    }

                    @Override
                    public RoleSkill getRoleSkill() {
                        return roleSkill;
                    }
                });

                slot++;
            }

            index += 9;
            slot = index;
        }
    }
}