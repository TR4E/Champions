package me.trae.champions.role.roles.submodules;

import me.trae.champions.role.Role;
import me.trae.core.damage.events.CustomDamageEvent;
import me.trae.core.framework.types.SpigotSubListener;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDamageEvent;

public class DisableFallDamage extends SpigotSubListener<Role> {

    public DisableFallDamage(final Role module) {
        super(module);
    }

    @EventHandler
    public void onCustomDamage(final CustomDamageEvent event) {
        if (event.isCancelled()) {
            return;
        }

        if (event.getCause() != EntityDamageEvent.DamageCause.FALL) {
            return;
        }

        if (!(event.getDamagee() instanceof Player)) {
            return;
        }

        if (!(this.getModule().isUserByPlayer(event.getDamageeCasted(Player.class)))) {
            return;
        }

        event.setCancelled(true);
    }
}