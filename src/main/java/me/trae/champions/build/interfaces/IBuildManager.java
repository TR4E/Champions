package me.trae.champions.build.interfaces;

import me.trae.champions.build.data.RoleBuild;
import me.trae.champions.role.Role;
import org.bukkit.entity.Player;

import java.util.Map;
import java.util.UUID;

public interface IBuildManager {

    Map<String, RoleBuild> getDefaultRoleBuilds();

    RoleBuild getDefaultRoleBuildByRole(final Role role);

    Map<UUID, Map<String, Map<Integer, RoleBuild>>> getRoleBuilds();

    void addRoleBuild(final UUID uuid, final RoleBuild roleBuild);

    void removeRoleBuild(final UUID uuid, final RoleBuild roleBuild);

    Map<Integer, RoleBuild> getRoleBuildsByRole(final Player player, final Role role);

    RoleBuild getRoleBuildByID(final Player player, final Role role, final int id);

    boolean isRoleBuildByID(final Player player, final Role role, final int id);
}