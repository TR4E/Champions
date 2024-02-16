package me.trae.champions.blood;

import me.trae.champions.blood.interfaces.IBlood;
import org.bukkit.entity.Item;
import org.bukkit.entity.LivingEntity;

import java.util.UUID;

public class Blood implements IBlood {

    private final UUID uuid;
    private final Item item;
    private final long systemTime, duration;

    public Blood(final LivingEntity entity, final Item item, final long duration) {
        this.uuid = entity.getUniqueId();
        this.item = item;
        this.systemTime = System.currentTimeMillis();
        this.duration = duration;
    }

    @Override
    public UUID getUUID() {
        return this.uuid;
    }

    @Override
    public Item getItem() {
        return this.item;
    }

    @Override
    public long getSystemTime() {
        return this.systemTime;
    }

    @Override
    public long getDuration() {
        return this.duration;
    }
}