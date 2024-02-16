package me.trae.champions.weapon.items;

import me.trae.champions.weapon.items.interfaces.BoosterWeapon;
import me.trae.champions.weapon.items.types.ChampionsWeapon;
import me.trae.core.weapon.WeaponManager;
import me.trae.core.weapon.data.WeaponData;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class BoosterAxe extends ChampionsWeapon<WeaponData> implements BoosterWeapon {

    public BoosterAxe(final WeaponManager manager) {
        super(manager, new ItemStack(Material.GOLD_AXE));
    }

    @Override
    public String[] getDescription() {
        return new String[]{
                "",
                "Increases Skill Level by 1."
        };
    }
}