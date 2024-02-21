package me.trae.champions.weapon.items;

import me.trae.champions.weapon.items.types.ChampionsWeapon;
import me.trae.core.weapon.WeaponManager;
import me.trae.core.weapon.data.WeaponData;
import me.trae.framework.shared.utility.UtilFormat;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class PowerAxe extends ChampionsWeapon<WeaponData> {

    public PowerAxe(final WeaponManager manager) {
        super(manager, new ItemStack(Material.DIAMOND_AXE));
    }

    @Override
    public String[] getDescription() {
        return new String[]{
                "Increases Melee Damage by 1.",
                "",
                UtilFormat.pairString("<gray>Damage", String.format("<green>%s", this.getDamage()))
        };
    }

    @Override
    public double getDamage() {
        return 5.0D;
    }
}