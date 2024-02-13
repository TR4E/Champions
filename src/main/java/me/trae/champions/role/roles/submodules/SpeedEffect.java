package me.trae.champions.role.roles.submodules;

import me.trae.champions.role.Role;
import me.trae.core.framework.types.SpigotSubUpdater;
import me.trae.core.utility.UtilEntity;
import me.trae.core.utility.UtilServer;
import me.trae.framework.shared.updater.annotations.Update;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.potion.PotionEffectType;

public class SpeedEffect extends SpigotSubUpdater<Role> implements Listener {

    public SpeedEffect(final Role module) {
        super(module);
    }

    @Update(delay = 250L)
    public void onUpdate() {
        final int amplifier = 2;

        for (final Player player : this.getModule().getUsers()) {
            if (UtilEntity.hasPotionEffect(player, PotionEffectType.SPEED, amplifier)) {
                continue;
            }

            UtilEntity.addPotionEffect(player, PotionEffectType.SPEED, Integer.MAX_VALUE, amplifier);
        }
    }

    @Override
    public void onShutdown() {
        for (final Player player : UtilServer.getOnlinePlayers()) {
            if (!(this.getModule().isUserByPlayer(player))) {
                continue;
            }

            player.removePotionEffect(PotionEffectType.SPEED);
        }
    }
}