package me.trae.champions.weapon;

import me.trae.champions.weapon.types.ChampionsWeapon;
import me.trae.core.weapon.WeaponManager;
import me.trae.core.weapon.data.WeaponData;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class PowerAxe extends ChampionsWeapon<WeaponData> {

    public PowerAxe(final WeaponManager manager) {
        super(manager, new ItemStack(Material.DIAMOND_AXE));
    }

    @Override
    public String[] getDescription() {
        return new String[]{
                "",
                "Increases Melee Damage by 1."
        };
    }
}