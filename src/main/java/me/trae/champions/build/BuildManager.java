package me.trae.champions.build;

import me.trae.champions.build.interfaces.IBuildManager;
import me.trae.champions.role.Role;
import me.trae.core.framework.SpigotManager;
import me.trae.core.framework.SpigotPlugin;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class BuildManager extends SpigotManager implements IBuildManager {

    private final Map<UUID, Map<String, Map<Integer, RoleBuild>>> roleBuilds;

    public BuildManager(final SpigotPlugin instance) {
        super(instance);

        this.roleBuilds = new HashMap<>();
    }

    @Override
    public void registerModules() {
    }

    @Override
    public Map<UUID, Map<String, Map<Integer, RoleBuild>>> getRoleBuilds() {
        return this.roleBuilds;
    }

    @Override
    public void addRoleBuild(final RoleBuild roleBuild) {
        if (!(this.getRoleBuilds().containsKey(roleBuild.getUUID()))) {
            this.getRoleBuilds().put(roleBuild.getUUID(), new HashMap<>());
        }

        final Map<String, Map<Integer, RoleBuild>> map = this.getRoleBuilds().get(roleBuild.getUUID());

        if (!(map.containsKey(roleBuild.getRoleName()))) {
            map.put(roleBuild.getRoleName(), new HashMap<>());
        }

        map.get(roleBuild.getRoleName()).put(roleBuild.getID(), roleBuild);
    }

    @Override
    public void removeRoleBuild(final RoleBuild roleBuild) {
        if (!(this.getRoleBuilds().containsKey(roleBuild.getUUID()))) {
            return;
        }

        final Map<String, Map<Integer, RoleBuild>> map = this.getRoleBuilds().get(roleBuild.getUUID());

        if (!(map.containsKey(roleBuild.getRoleName()))) {
            return;
        }

        map.get(roleBuild.getRoleName()).remove(roleBuild.getID());

        if (map.isEmpty()) {
            this.getRoleBuilds().remove(roleBuild.getUUID());
        }
    }

    @Override
    public Map<String, Map<Integer, RoleBuild>> getRoleBuilds(final Player player) {
        return this.getRoleBuilds().getOrDefault(player.getUniqueId(), new HashMap<>());
    }

    @Override
    public Map<Integer, RoleBuild> getRoleBuilds(final Player player, final Role role) {
        return this.getRoleBuilds(player).getOrDefault(role.getName(), new HashMap<>());
    }

    @Override
    public RoleBuild getRoleBuild(final Player player, final Role role, final int id) {
        if (id != 0) {
            final Map<Integer, RoleBuild> roleBuilds = this.getRoleBuilds(player, role);
            if (roleBuilds.containsKey(id)) {
                return roleBuilds.getOrDefault(id, null);
            }
        }
        return null;
    }

    @Override
    public RoleBuild getActiveRoleBuild(final Player player, final Role role) {
        for (final RoleBuild roleBuild : this.getRoleBuilds(player, role).values()) {
            if (!(roleBuild.isActive())) {
                continue;
            }

            return roleBuild;
        }
        return null;
    }

    @Override
    public void setActiveRoleBuild(final Player player, final Role role, final RoleBuild roleBuild) {
        for (final RoleBuild oldRoleBuild : this.getRoleBuilds(player, role).values()) {
            if (!(oldRoleBuild).isActive()) {
                continue;
            }

            if (roleBuild != null && oldRoleBuild == roleBuild) {
                continue;
            }

            oldRoleBuild.setActive(false);

//            this.getRepository().saveData(oldRoleBuild);
        }

        if (roleBuild == null || roleBuild.isActive()) {
            return;
        }

        roleBuild.setActive(true);

        if (roleBuild.getID() != 0) {
//            this.getRepository().saveData(oldRoleBuild);
        }
    }
}