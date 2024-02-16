package me.trae.champions.weapon.legendaries;

import me.trae.core.weapon.WeaponManager;
import me.trae.core.weapon.data.WeaponData;
import me.trae.core.weapon.types.Legendary;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class WindBlade extends Legendary<WeaponData> {

    public WindBlade(final WeaponManager manager) {
        super(manager, new ItemStack(Material.DIAMOND_SWORD));
    }
}