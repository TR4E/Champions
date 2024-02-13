package me.trae.champions.weapon;

import me.trae.core.weapon.WeaponManager;
import me.trae.core.weapon.types.VanillaItem;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class PowerSword extends VanillaItem {

    public PowerSword(final WeaponManager manager) {
        super(manager, new ItemStack(Material.DIAMOND_SWORD));
    }
}