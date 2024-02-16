package me.trae.champions.role.modules.restrictions;

import me.trae.champions.role.RoleManager;
import me.trae.champions.role.roles.interfaces.Archer;
import me.trae.core.client.ClientManager;
import me.trae.core.framework.types.SpigotListener;
import me.trae.core.utility.UtilMessage;
import me.trae.framework.shared.utility.UtilJava;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.entity.EntityShootBowEvent;

public class DisableShootingArrowsForNonArchers extends SpigotListener<RoleManager> {

    public DisableShootingArrowsForNonArchers(final RoleManager manager) {
        super(manager);
    }

    @EventHandler(priority = EventPriority.LOW)
    public void onEntityShoot(final EntityShootBowEvent event) {
        if (event.isCancelled()) {
            return;
        }

        if (!(event.getEntity() instanceof Player)) {
            return;
        }

        final Player player = UtilJava.cast(Player.class, event.getEntity());

        if (this.getManager().getPlayerRole(player) instanceof Archer) {
            return;
        }

        if (this.getInstance().getManagerByClass(ClientManager.class).getClientByPlayer(player).isAdministrating()) {
            return;
        }

        event.setCancelled(true);

        player.updateInventory();

        UtilMessage.message(player, "Restrictions", "You must be an Archer Class to shoot arrows!");
    }
}