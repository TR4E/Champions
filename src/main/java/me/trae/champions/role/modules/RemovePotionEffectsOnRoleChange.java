package me.trae.champions.role.modules;

import me.trae.champions.role.RoleManager;
import me.trae.champions.role.events.RoleChangeEvent;
import me.trae.core.framework.types.SpigotListener;
import me.trae.core.utility.UtilGame;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class RemovePotionEffectsOnRoleChange extends SpigotListener<RoleManager> {

    public RemovePotionEffectsOnRoleChange(final RoleManager manager) {
        super(manager);
    }

    @EventHandler
    public void onRoleChange(final RoleChangeEvent event) {
        final Player player = event.getPlayer();

        for (final PotionEffect potionEffect : player.getActivePotionEffects()) {
            final PotionEffectType potionEffectType = potionEffect.getType();

            if (UtilGame.getNegativePotionEffects().contains(potionEffectType)) {
                continue;
            }

            player.removePotionEffect(potionEffectType);
        }
    }
}