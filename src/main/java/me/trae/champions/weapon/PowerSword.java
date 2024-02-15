package me.trae.champions.weapon;

import me.trae.champions.weapon.types.ChampionsWeapon;
import me.trae.core.weapon.WeaponManager;
import me.trae.core.weapon.data.WeaponData;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class PowerSword extends ChampionsWeapon<WeaponData> {

    public PowerSword(final WeaponManager manager) {
        super(manager, new ItemStack(Material.DIAMOND_SWORD));
    }

    @Override
    public String[] getDescription() {
        return new String[]{
                "",
                "Increases Melee Damage by 1."
        };
    }
}