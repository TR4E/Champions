package me.trae.champions.role.listeners;

import me.trae.champions.role.Role;
import me.trae.champions.role.RoleManager;
import me.trae.champions.role.events.RoleChangeEvent;
import me.trae.core.framework.SpigotModule;
import me.trae.core.utility.UtilMessage;
import me.trae.core.utility.UtilServer;
import me.trae.framework.shared.updater.Update;
import me.trae.framework.shared.updater.Updater;
import me.trae.framework.utility.other.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

public class HandleRoleEquipUpdater extends SpigotModule<RoleManager> implements Updater {

    public HandleRoleEquipUpdater(final RoleManager manager) {
        super(manager);
    }

    @Update(time = 100L, async = true)
    public void onUpdate() {
        for (final Player player : Bukkit.getOnlinePlayers()) {
            Role playerRole = null;

            for (final Role role : getInstance().getManagers(Role.class)) {
                if (!(role.isEnabled())) {
                    continue;
                }

                if (!(role.hasArmour(player))) {
                    continue;
                }

                playerRole = role;
                break;
            }

            this.equipClass(player, playerRole);
        }

        getManager().getPlayerRoles().keySet().removeIf(uuid -> Bukkit.getPlayer(uuid) == null);
    }

    private void equipClass(final Player player, final Role role) {
        if (role == null) {
            if (getManager().getPlayerRoles().containsKey(player.getUniqueId())) {
                final Role oldRole = getManager().getPlayerRole(player);
                if (oldRole == null) {
                    return;
                }

                final RoleChangeEvent event = new RoleChangeEvent(player, null, oldRole);
                UtilServer.callEvent(event);
                if (event.isCancelled()) {
                    return;
                }
            }

            getManager().setPlayerRole(player, null);

            player.getWorld().playSound(player.getLocation(), Sound.HORSE_ARMOR, 5.0F, 5.09F);

            UtilMessage.message(player, "Class", "Armor Class: " + ChatColor.RED + "None");
            return;
        }

        final Role oldRole = getManager().getPlayerRole(player);
        if (oldRole == role) {
            return;
        }

        final RoleChangeEvent event = new RoleChangeEvent(player, role, oldRole);
        UtilServer.callEvent(event);
        if (event.isCancelled()) {
            return;
        }

        getManager().setPlayerRole(player, role);

        player.getWorld().playSound(player.getLocation(), Sound.HORSE_ARMOR, 2.0F, 1.09F);

        UtilMessage.message(player, "Class", "Armor Class: " + ChatColor.GREEN + role.getName());
        role.sendEquipMessage(player);
    }
}