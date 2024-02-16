package me.trae.champions.blood.events;

import me.trae.champions.blood.events.interfaces.IBloodEffectEvent;
import me.trae.core.event.CustomCancellableEvent;
import org.bukkit.Material;
import org.bukkit.entity.LivingEntity;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BloodEffectEvent extends CustomCancellableEvent implements IBloodEffectEvent {

    private final LivingEntity entity;

    private List<ItemStack> itemStacks;

    public BloodEffectEvent(final LivingEntity entity) {
        this.entity = entity;
        this.itemStacks = new ArrayList<>(Arrays.asList(new ItemStack(Material.INK_SACK, 1, (short) 1), new ItemStack(Material.BONE)));
    }

    @Override
    public LivingEntity getEntity() {
        return this.entity;
    }

    @Override
    public List<ItemStack> getItemStacks() {
        return this.itemStacks;
    }

    @Override
    public void setItemStacks(final List<ItemStack> itemStacks) {
        this.itemStacks = itemStacks;
    }
}