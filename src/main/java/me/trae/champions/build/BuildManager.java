package me.trae.champions.build;

import me.trae.champions.build.commands.BuildCommand;
import me.trae.champions.build.data.RoleBuild;
import me.trae.champions.build.data.RoleSkill;
import me.trae.champions.build.interfaces.IBuildManager;
import me.trae.champions.build.modules.HandleClassCustomizationTable;
import me.trae.champions.role.Role;
import me.trae.champions.skill.Skill;
import me.trae.core.framework.SpigotManager;
import me.trae.core.framework.SpigotPlugin;
import me.trae.framework.shared.utility.UtilJava;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class BuildManager extends SpigotManager implements IBuildManager {

    private final Map<UUID, Map<String, Map<Integer, RoleBuild>>> ROLE_BUILDS = new HashMap<>();

    public BuildManager(final SpigotPlugin instance) {
        super(instance);
    }

    @Override
    public void registerModules() {
        // Commands
        addModule(new BuildCommand(this));

        // Modules
        addModule(new HandleClassCustomizationTable(this));
    }

    @Override
    public Map<UUID, Map<String, Map<Integer, RoleBuild>>> getRoleBuilds() {
        return this.ROLE_BUILDS;
    }

    @Override
    public void addRoleBuild(final UUID uuid, final RoleBuild roleBuild) {
        if (!(this.getRoleBuilds().containsKey(uuid))) {
            this.getRoleBuilds().put(uuid, new HashMap<>());
        }

        final Map<String, Map<Integer, RoleBuild>> map = this.getRoleBuilds().get(uuid);

        if (!(map.containsKey(roleBuild.getName()))) {
            map.put(roleBuild.getName(), new HashMap<>());
        }

        map.get(roleBuild.getName()).put(roleBuild.getID(), roleBuild);
    }

    @Override
    public void removeRoleBuild(final UUID uuid, final RoleBuild roleBuild) {
        this.getRoleBuilds().getOrDefault(uuid, new HashMap<>()).getOrDefault(roleBuild.getName(), new HashMap<>()).remove(roleBuild.getID());
    }

    @Override
    public Map<Integer, RoleBuild> getRoleBuildsByRole(final Player player, final Role role) {
        return this.getRoleBuilds().getOrDefault(player.getUniqueId(), new HashMap<>()).getOrDefault(role.getName(), new HashMap<>());
    }

    @Override
    public RoleBuild getRoleBuildByID(final Player player, final Role role, final int id) {
        return this.getRoleBuildsByRole(player, role).getOrDefault(id, null);
    }

    @Override
    public boolean isRoleBuildByID(final Player player, final Role role, final int id) {
        return this.getRoleBuildsByRole(player, role).containsKey(id);
    }

    @Override
    public void setActiveRoleBuild(final Player player, final Role role, final RoleBuild roleBuild) {
        for (final RoleBuild oldRoleBuild : this.getRoleBuildsByRole(player, role).values()) {
            if (!(oldRoleBuild.isActive())) {
                continue;
            }

            if (roleBuild != null && oldRoleBuild == roleBuild) {
                continue;
            }

            oldRoleBuild.setActive(false);

            // TODO: 19/02/2024 - Save Data
        }

        if (roleBuild == null || roleBuild.isActive()) {
            return;
        }

        roleBuild.setActive(true);

        if (roleBuild.getID() != 0) {
            // TODO: 19/02/2024 - Save Data
        }
    }

    @Override
    public int getSkillPoints(final Role role, final RoleBuild roleBuild) {
        int points = 12;

        for (final RoleSkill roleSkill : roleBuild.getSkills().values()) {
            final Skill<?, ?> skill = UtilJava.cast(Skill.class, role.getSubModuleByName(roleSkill.getName()));

            points -= roleSkill.getLevel() * skill.getTokenCost();
        }

        return points;
    }
}