package me.trae.champions.role;

import me.trae.champions.build.BuildManager;
import me.trae.champions.build.RoleBuild;
import me.trae.champions.build.RoleSkill;
import me.trae.champions.build.listeners.PremadeClasses;
import me.trae.champions.role.interfaces.IRole;
import me.trae.champions.skill.enums.SkillType;
import me.trae.core.framework.SpigotManager;
import me.trae.core.framework.SpigotPlugin;
import me.trae.core.utility.UtilMessage;
import me.trae.framework.utility.other.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;

public abstract class Role extends SpigotManager implements IRole {

    public Role(final SpigotPlugin instance) {
        super(instance);
    }

    @Override
    public void registerModules() {
    }

    @Override
    public boolean hasArmour(final Player player) {
        for (final ItemStack armor : player.getEquipment().getArmorContents()) {
            if (armor != null && Arrays.asList(this.getArmour()).contains(armor.getType())) {
                continue;
            }

            return false;
        }
        return true;
    }

    @Override
    public void sendEquipMessage(final Player player) {
        final BuildManager buildManager = getInstance().getManager(BuildManager.class);

        final RoleBuild roleBuild = buildManager.getActiveRoleBuildOrDefault(player, this);
        if (roleBuild == null) {
            UtilMessage.message(player, "Build", ChatColor.RED + "Failed to load your Role Build, contact an administrator ASAP!");
            return;
        }

        final boolean isPremadeClasses = buildManager.getModule(PremadeClasses.class).isEnabled();

        for (final SkillType skillType : SkillType.values()) {
            final RoleSkill roleSkill = roleBuild.getRoleSkill(skillType);

            String skillName = "";
            if (roleSkill != null) {
                skillName = (isPremadeClasses ? roleSkill.getName() : roleSkill.getDisplayName());
            }

            UtilMessage.formatMessage(player, skillType.getName(), skillName);
        }
    }
}