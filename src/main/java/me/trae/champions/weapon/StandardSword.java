package me.trae.champions.weapon;

import me.trae.core.weapon.WeaponManager;
import me.trae.core.weapon.data.WeaponData;
import me.trae.core.weapon.types.VanillaItem;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class StandardSword extends VanillaItem<WeaponData> {

    public StandardSword(final WeaponManager manager) {
        super(manager, new ItemStack(Material.IRON_SWORD));
    }

    @Override
    public String[] getDescription() {
        return new String[]{
                "",
                "Just a Standard Sword."
        };
    }
}