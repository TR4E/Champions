package me.trae.champions.role.interfaces;

import me.trae.champions.role.Role;
import org.bukkit.entity.Player;

import java.util.Map;
import java.util.UUID;

public interface IRoleManager {

    Map<UUID, String> getPlayerRoles();

    void setPlayerRole(final Player player, final Role role);

    void removePlayerRole(final Player player);

    Role getPlayerRole(final Player player);

    boolean hasPlayerRole(final Player player);

    boolean hasPlayerRole(final Player player, final Role role);
}