package me.trae.champions.role.modules;

import me.trae.champions.role.RoleManager;
import me.trae.champions.role.events.RoleChangeEvent;
import me.trae.champions.utility.UtilChampions;
import me.trae.core.framework.types.SpigotListener;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerJoinEvent;
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

            if (UtilChampions.getNegativePotionEffects().contains(potionEffectType)) {
                continue;
            }

            player.removePotionEffect(potionEffectType);
        }
    }

    @EventHandler
    public void onPlayerJoin(final PlayerJoinEvent event) {
        final Player player = event.getPlayer();
    }
}