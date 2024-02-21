package me.trae.champions.role;

import me.trae.champions.build.BuildManager;
import me.trae.champions.build.data.RoleBuild;
import me.trae.champions.build.data.types.DefaultRoleBuild;
import me.trae.champions.role.interfaces.IRole;
import me.trae.champions.skill.Skill;
import me.trae.champions.skill.enums.SkillType;
import me.trae.core.framework.SpigotModule;
import me.trae.framework.shared.utility.UtilFormat;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public abstract class Role extends SpigotModule<RoleManager> implements IRole {

    public Role(final RoleManager manager) {
        super(manager);
    }

    @Override
    public String getPrefix() {
        return this.getName().substring(0, 1);
    }

    @Override
    public List<Player> getUsers() {
        final List<Player> list = new ArrayList<>();

        for (final Map.Entry<UUID, Role> entry : this.getManager().getPlayerRoles().entrySet()) {
            if (entry.getValue() != this) {
                continue;
            }

            final Player player = Bukkit.getPlayer(entry.getKey());
            if (player == null) {
                continue;
            }

            list.add(player);
        }

        return list;
    }

    @Override
    public boolean isUserByPlayer(final Player player) {
        return this.getManager().getPlayerRole(player) == this;
    }

    @Override
    public RoleBuild getDefaultRoleBuild(final Player player) {
        final BuildManager buildManager = this.getInstance().getManagerByClass(BuildManager.class);

        RoleBuild roleBuild = buildManager.getRoleBuildByID(player, this, 0);
        if (roleBuild == null) {
            roleBuild = new DefaultRoleBuild(player.getUniqueId(), this);
            buildManager.addRoleBuild(roleBuild);
        }

        return roleBuild;
    }

    @Override
    public RoleBuild getActiveRoleBuild(final Player player) {
        for (final RoleBuild roleBuild : this.getInstance().getManagerByClass(BuildManager.class).getRoleBuildsByRole(player, this).values()) {
            if (!(roleBuild.isActive())) {
                continue;
            }

            return roleBuild;
        }

        return null;
    }

    @Override
    public List<String> getEquipMessage(final RoleBuild roleBuild) {
        final List<String> list = new ArrayList<>();

        for (final SkillType skillType : SkillType.values()) {
            String skillName = "";

            if (roleBuild != null) {
                if (roleBuild.isSkillByType(skillType)) {
                    skillName = roleBuild.getSkillByType(skillType).getDisplayName();
                }
            }

            list.add(String.format(UtilFormat.pairString("<green>%s", "<white>%s</white>"), skillType.getName(), skillName));
        }

        return list;
    }

    @Override
    public List<Skill<?, ?>> getSkillsByType(final SkillType skillType) {
        final List<Skill<?, ?>> list = new ArrayList<>();

        for (final Skill<?, ?> skill : this.getSubModulesByClass(Skill.class)) {
            if (skill.getType() != skillType) {
                continue;
            }

            list.add(skill);
        }

        return list;
    }

    @Override
    public int getMaxSkillTokens() {
        return 12;
    }
}