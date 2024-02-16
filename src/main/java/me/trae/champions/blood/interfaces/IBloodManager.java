package me.trae.champions.blood.interfaces;

import me.trae.champions.blood.Blood;
import org.bukkit.entity.Item;
import org.bukkit.entity.LivingEntity;
import org.bukkit.inventory.ItemStack;

import java.util.List;

public interface IBloodManager {

    List<Blood> getBloodList();

    void addBlood(final Blood blood);

    void removeBlood(final Blood blood);

    Blood getBloodByItem(final Item item);

    boolean isBloodByItem(final Item item);

    void display(final LivingEntity entity, final ItemStack itemStack, final long duration);
}