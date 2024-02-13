package me.trae.champions.role.roles.submodules;

import me.trae.champions.role.Role;
import me.trae.core.framework.types.SpigotSubListener;
import me.trae.framework.shared.utility.UtilJava;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDamageEvent;

public class DisableFallDamage extends SpigotSubListener<Role> {

    public DisableFallDamage(final Role module) {
        super(module);
    }

    @EventHandler
    public void onEntityDamage(final EntityDamageEvent event) {
        if (event.isCancelled()) {
            return;
        }

        if (event.getCause() != EntityDamageEvent.DamageCause.FALL) {
            return;
        }

        if (!(event.getEntity() instanceof Player)) {
            return;
        }

        if (!(this.getModule().isUserByPlayer(UtilJava.cast(Player.class, event.getEntity())))) {
            return;
        }

        event.setCancelled(true);
    }
}