package me.trae.champions.role.modules;

import me.trae.champions.role.Role;
import me.trae.champions.role.RoleManager;
import me.trae.core.framework.types.SpigotUpdater;
import me.trae.core.utility.UtilMessage;
import me.trae.core.utility.UtilServer;
import me.trae.framework.shared.updater.annotations.Update;
import me.trae.framework.shared.utility.UtilFormat;
import me.trae.framework.shared.utility.enums.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class HandleRoleEquip extends SpigotUpdater<RoleManager> {

    public HandleRoleEquip(final RoleManager manager) {
        super(manager);
    }

    @Update(delay = 125L)
    public void onUpdate() {
        for (final Player player : UtilServer.getOnlinePlayers()) {
            Role playerRole = null;

            for (final Role role : this.getManager().getModulesByClass(Role.class)) {
                if (!(this.hasArmour(player, role))) {
                    continue;
                }

                playerRole = role;
                break;
            }

            this.equip(player, playerRole);
        }
    }

    private boolean hasArmour(final Player player, final Role role) {
        for (final ItemStack itemStack : player.getEquipment().getArmorContents()) {
            if (itemStack != null && role.getArmour().contains(itemStack.getType())) {
                continue;
            }

            return false;
        }

        return true;
    }

    private void equip(final Player player, final Role role) {
        if (this.getManager().hasPlayerRole(player) && this.getManager().getPlayerRole(player) == role) {
            return;
        }

        this.getManager().setPlayerRole(player, role);

        if (role == null) {
            UtilMessage.message(player, "Class", UtilFormat.pairString("Armor Class", ChatColor.RED + "None"));
        } else {
            UtilMessage.message(player, "Class", UtilFormat.pairString("Armor Class", ChatColor.GREEN + role.getName()));
        }
    }
}