package me.trae.champions.weapon;

import me.trae.champions.weapon.types.ChampionsWeapon;
import me.trae.core.weapon.WeaponManager;
import me.trae.core.weapon.data.WeaponData;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class StandardAxe extends ChampionsWeapon<WeaponData> {

    public StandardAxe(final WeaponManager manager) {
        super(manager, new ItemStack(Material.IRON_AXE));
    }

    @Override
    public String[] getDescription() {
        return new String[]{
                "",
                "Just a Standard Axe."
        };
    }
}