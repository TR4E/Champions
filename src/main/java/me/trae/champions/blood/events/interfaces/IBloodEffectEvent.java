package me.trae.champions.blood.events.interfaces;

import me.trae.core.event.types.IEntityEvent;
import org.bukkit.entity.LivingEntity;
import org.bukkit.inventory.ItemStack;

import java.util.List;

public interface IBloodEffectEvent extends IEntityEvent<LivingEntity> {

    List<ItemStack> getItemStacks();

    void setItemStacks(final List<ItemStack> itemStacks);
}