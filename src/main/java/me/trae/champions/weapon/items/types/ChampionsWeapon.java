package me.trae.champions.weapon.items.types;

import me.trae.champions.weapon.items.types.interfaces.IChampionsWeapon;
import me.trae.core.damage.events.CustomDamageEvent;
import me.trae.core.weapon.WeaponManager;
import me.trae.core.weapon.data.WeaponData;
import me.trae.core.weapon.types.VanillaItem;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;

public abstract class ChampionsWeapon<D extends WeaponData> extends VanillaItem<D> implements IChampionsWeapon, Listener {

    public ChampionsWeapon(final WeaponManager manager, final ItemStack itemStack) {
        super(manager, itemStack);
    }

    @EventHandler(priority = EventPriority.LOW)
    public void onCustomDamage(final CustomDamageEvent event) {
        if (event.isCancelled()) {
            return;
        }

        if (!(event.getDamager() instanceof LivingEntity)) {
            return;
        }

        final ItemStack itemStack = event.getDamagerCasted(LivingEntity.class).getEquipment().getItemInHand();

        if (this.getManager().getWeaponByItemStack(itemStack) != this) {
            return;
        }

        event.setDamage(this.getDamage());
    }
}