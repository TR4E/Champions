package me.trae.champions.role;

import me.trae.champions.role.interfaces.IRoleManager;
import me.trae.champions.role.listeners.HandleRoleEquipUpdater;
import me.trae.core.framework.SpigotManager;
import me.trae.core.framework.SpigotPlugin;
import me.trae.framework.utility.UtilJava;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class RoleManager extends SpigotManager implements IRoleManager {

    private final Map<UUID, String> playerRoles;

    public RoleManager(final SpigotPlugin instance) {
        super(instance);

        this.playerRoles = new HashMap<>();
    }

    @Override
    public void registerModules() {
        addModule(new HandleRoleEquipUpdater(this));
    }

    @Override
    public Map<UUID, String> getPlayerRoles() {
        return this.playerRoles;
    }

    @Override
    public void setPlayerRole(final Player player, final Role role) {
        this.getPlayerRoles().put(player.getUniqueId(), (role != null ? role.getName() : null));
    }

    @Override
    public void removePlayerRole(final Player player) {
        this.getPlayerRoles().remove(player.getUniqueId());
    }

    @Override
    public Role getPlayerRole(final Player player) {
        if (this.getPlayerRoles().containsKey(player.getUniqueId())) {
            final String roleName = this.getPlayerRoles().getOrDefault(player.getUniqueId(), null);
            if (roleName != null) {
                return UtilJava.cast(Role.class, getInstance().getManager(roleName));
            }
        }
        return null;
    }

    @Override
    public boolean hasPlayerRole(final Player player) {
        return (this.getPlayerRoles().getOrDefault(player.getUniqueId(), null) != null);
    }

    @Override
    public boolean hasPlayerRole(final Player player, final Role role) {
        final Role playerRole = this.getPlayerRole(player);
        if (playerRole == null) {
            return false;
        }
        return (playerRole == role);
    }
}