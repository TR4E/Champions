package me.trae.champions.blood.modules;

import me.trae.champions.blood.BloodManager;
import me.trae.champions.blood.events.BloodEffectEvent;
import me.trae.core.death.events.CustomDeathEvent;
import me.trae.core.framework.types.SpigotListener;
import me.trae.core.utility.UtilServer;
import me.trae.framework.shared.utility.UtilMath;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.inventory.ItemStack;

public class HandleBloodOnPlayerDeath extends SpigotListener<BloodManager> {

    public HandleBloodOnPlayerDeath(final BloodManager manager) {
        super(manager);

        this.addPrimitive("Players-Only", true);
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void onCustomDeath(final CustomDeathEvent event) {
        if (this.getPrimitiveCasted(Boolean.class, "Players-Only") && !(event.getEntity() instanceof Player)) {
            return;
        }

        final LivingEntity entity = event.getEntity();

        final BloodEffectEvent bloodEffectEvent = new BloodEffectEvent(entity);
        UtilServer.callEvent(bloodEffectEvent);
        if (bloodEffectEvent.isCancelled()) {
            return;
        }

        long duration = 4000L;

        for (int i = 0; i < UtilMath.getRandomNumber(Integer.class, 12, 18); i++) {
            for (final ItemStack itemStack : bloodEffectEvent.getItemStacks()) {
                duration += 200L;

                this.getManager().display(entity, itemStack, duration);
            }
        }
    }
}