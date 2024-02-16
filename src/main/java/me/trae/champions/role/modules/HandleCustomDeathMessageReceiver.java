package me.trae.champions.role.modules;

import me.trae.champions.role.Role;
import me.trae.champions.role.RoleManager;
import me.trae.core.death.events.CustomDeathEvent;
import me.trae.core.death.events.CustomDeathMessageEvent;
import me.trae.core.framework.types.SpigotListener;
import me.trae.framework.shared.utility.UtilJava;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;

public class HandleCustomDeathMessageReceiver extends SpigotListener<RoleManager> {

    public HandleCustomDeathMessageReceiver(final RoleManager manager) {
        super(manager);
    }

    @EventHandler(priority = EventPriority.HIGH)
    public void onCustomDeathMessage(final CustomDeathMessageEvent event) {
        if (event.isCancelled()) {
            return;
        }

        final CustomDeathEvent deathEvent = event.getDeathEvent();

        if (deathEvent.getEntity() instanceof Player) {
            UtilJava.call(this.getName(deathEvent.getEntityCasted(Player.class), event.getEntityName()), entityName -> {
                if (entityName != null) {
                    event.setEntityName(entityName);
                }
            });
        }

        if (deathEvent.getKiller() instanceof Player) {
            UtilJava.call(this.getName(deathEvent.getKillerCasted(Player.class), event.getKillerName()), killerName -> {
                if (killerName != null) {
                    event.setKillerName(killerName);
                }
            });
        }
    }

    private String getName(final Player player, final String name) {
        final Role playerRole = this.getManager().getPlayerRole(player);
        if (playerRole == null) {
            return null;
        }

        return String.format("<green>%s</green><white>.</white>%s", playerRole.getPrefix(), name);
    }
}