package me.trae.champions.role.modules;

import me.trae.champions.build.data.RoleBuild;
import me.trae.champions.role.Role;
import me.trae.champions.role.RoleManager;
import me.trae.champions.role.events.RoleChangeEvent;
import me.trae.core.framework.types.SpigotUpdater;
import me.trae.core.utility.UtilMessage;
import me.trae.core.utility.UtilServer;
import me.trae.core.utility.objects.SoundCreator;
import me.trae.framework.shared.updater.annotations.Update;
import me.trae.framework.shared.utility.UtilFormat;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.Collections;

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

        this.getManager().getPlayerRoles().keySet().removeIf(uuid -> Bukkit.getPlayer(uuid) == null);
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
            new SoundCreator(Sound.HORSE_ARMOR, 5.0F, 5.09F).play(player.getLocation());

            UtilMessage.simpleMessage(player, "Class", UtilFormat.pairString("Armor Class", "<red>None</red>"));
        } else {
            new SoundCreator(Sound.HORSE_ARMOR, 2.0F, 1.09F).play(player.getLocation());

            UtilMessage.simpleMessage(player, "Class", UtilFormat.pairString("Armor Class", "<green><var></green>"), Collections.singletonList(role.getName()));

            RoleBuild roleBuild = role.getActiveRoleBuild(player);
            if (roleBuild == null) {
                roleBuild = role.getDefaultRoleBuild(player);
            }

            UtilMessage.simpleMessage(player, "Skills", "Listing <light_purple><var></light_purple> Skills:", Collections.singletonList(roleBuild.getID() == 0 ? "Default Build" : String.format("Build #%s", roleBuild.getID())));

            role.getEquipMessage(roleBuild).forEach(string -> UtilMessage.simpleMessage(player, null, string));
        }

        UtilServer.callEvent(new RoleChangeEvent(player, role));
    }
}