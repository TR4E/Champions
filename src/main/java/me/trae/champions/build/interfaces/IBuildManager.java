package me.trae.champions.build.interfaces;

import me.trae.champions.build.RoleBuild;
import me.trae.champions.role.Role;
import org.bukkit.entity.Player;

import java.util.Map;
import java.util.UUID;

public interface IBuildManager {

    Map<UUID, Map<String, Map<Integer, RoleBuild>>> getRoleBuilds();

    void addRoleBuild(final RoleBuild roleBuild);

    void removeRoleBuild(final RoleBuild roleBuild);

    Map<String, Map<Integer, RoleBuild>> getRoleBuilds(final Player player);

    Map<Integer, RoleBuild> getRoleBuilds(final Player player, final Role role);

    RoleBuild getRoleBuild(final Player player, final Role role, final int id);

    RoleBuild getActiveRoleBuild(final Player player, final Role role);

    RoleBuild getActiveRoleBuildOrDefault(final Player player, final Role role);

    void setActiveRoleBuild(final Player player, final Role role, final RoleBuild roleBuild);
}