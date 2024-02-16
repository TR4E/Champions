package me.trae.champions.blood;

import me.trae.champions.blood.interfaces.IBloodManager;
import me.trae.champions.blood.modules.HandleBloodOnPlayerDeath;
import me.trae.core.framework.SpigotManager;
import me.trae.core.framework.SpigotPlugin;
import me.trae.core.item.ItemBuilder;
import me.trae.core.utility.objects.SoundCreator;
import me.trae.framework.shared.updater.annotations.Update;
import me.trae.framework.shared.updater.interfaces.Updater;
import me.trae.framework.shared.utility.UtilJava;
import me.trae.framework.shared.utility.UtilMath;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.entity.Item;
import org.bukkit.entity.LivingEntity;
import org.bukkit.inventory.ItemStack;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.util.Vector;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class BloodManager extends SpigotManager implements IBloodManager, Updater {

    private final List<Blood> BLOOD_LIST = new ArrayList<>();

    public BloodManager(final SpigotPlugin instance) {
        super(instance);
    }

    @Override
    public void registerModules() {
        addModule(new HandleBloodOnPlayerDeath(this));
    }

    @Override
    public List<Blood> getBloodList() {
        return this.BLOOD_LIST;
    }

    @Override
    public void addBlood(final Blood blood) {
        this.getBloodList().add(blood);
    }

    @Override
    public void removeBlood(final Blood blood) {
        this.getBloodList().remove(blood);
    }

    @Override
    public Blood getBloodByItem(final Item item) {
        for (final Blood blood : this.getBloodList()) {
            if (!(blood.getItem().equals(item))) {
                continue;
            }

            return blood;
        }

        return null;
    }

    @Override
    public boolean isBloodByItem(final Item item) {
        return this.getBloodByItem(item) != null;
    }

    @Override
    public void display(final LivingEntity entity, final ItemStack itemStack, final long duration) {
        final ItemStack clone = new ItemBuilder(itemStack, String.valueOf(UUID.randomUUID()), null).toItemStack();

        final Item item = entity.getWorld().dropItem(entity.getLocation(), clone);

        UtilJava.call(0.10D, power -> {
            final double x = UtilMath.getRandomNumber(Double.class, -power, power);
            final double y = power * 3.0D;
            final double z = UtilMath.getRandomNumber(Double.class, -power, power);

            item.setVelocity(new Vector(x, y, z));
        });

        item.setPickupDelay(Integer.MAX_VALUE);

        item.setMetadata("Blood", new FixedMetadataValue(this.getInstance(), "Blood"));

        this.addBlood(new Blood(entity, item, duration));
    }

    @Update(delay = 250L)
    public void onUpdate() {
        this.getBloodList().removeIf(blood -> {
            if (!(blood.hasExpired())) {
                return false;
            }

            final Item item = blood.getItem();

            new SoundCreator(Sound.CHICKEN_EGG_POP, 2.0F, 1.5F).play(item.getLocation());

            item.remove();
            return true;
        });
    }

    @Override
    public void onInitialize() {
        for (final World world : Bukkit.getWorlds()) {
            for (final Item item : world.getEntitiesByClass(Item.class)) {
                if (!(item.hasMetadata("Blood"))) {
                    continue;
                }

                item.remove();
            }
        }
    }

    @Override
    public void onShutdown() {
        this.getBloodList().removeIf(blood -> {
            blood.getItem().remove();
            return true;
        });
    }
}