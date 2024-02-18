package me.trae.champions.build;

import me.trae.champions.build.data.RoleBuild;
import me.trae.champions.build.data.RoleSkill;
import me.trae.champions.build.interfaces.IBuildManager;
import me.trae.champions.role.Role;
import me.trae.champions.skill.Skill;
import me.trae.core.framework.SpigotManager;
import me.trae.core.framework.SpigotPlugin;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class BuildManager extends SpigotManager implements IBuildManager {

    private final Map<String, RoleBuild> DEFAULT_ROLE_BUILDS = new HashMap<>();

    private final Map<UUID, Map<String, Map<Integer, RoleBuild>>> ROLE_BUILDS = new HashMap<>();

    public BuildManager(final SpigotPlugin instance) {
        super(instance);
    }

    @Override
    public void registerModules() {
    }

    @Override
    public Map<String, RoleBuild> getDefaultRoleBuilds() {
        return this.DEFAULT_ROLE_BUILDS;
    }

    @Override
    public RoleBuild getDefaultRoleBuildByRole(final Role role) {
        if (!(this.getDefaultRoleBuilds().containsKey(role.getName()))) {
            final RoleBuild roleBuild = new RoleBuild(0, role);

            for (final Skill<?, ?> skill : role.getSubModulesByClass(Skill.class)) {
                if (skill.getDefaultLevel() <= 0) {
                    continue;
                }

                roleBuild.addSkill(new RoleSkill(skill, skill.getDefaultLevel()));
            }

            this.getDefaultRoleBuilds().put(role.getName(), roleBuild);
        }

        return this.getDefaultRoleBuilds().getOrDefault(role.getName(), null);
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
}