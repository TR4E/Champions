package me.trae.champions.weapon.items;

import me.trae.champions.weapon.items.types.ChampionsWeapon;
import me.trae.core.weapon.WeaponManager;
import me.trae.core.weapon.data.WeaponData;
import me.trae.framework.shared.utility.UtilFormat;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class StandardSword extends ChampionsWeapon<WeaponData> {

    public StandardSword(final WeaponManager manager) {
        super(manager, new ItemStack(Material.IRON_SWORD));
    }

    @Override
    public String[] getDescription() {
        return new String[]{
                "Just a Standard Sword.",
                "",
                UtilFormat.pairString("<gray>Damage", String.format("<green>%s", this.getDamage()))
        };
    }

    @Override
    public double getDamage() {
        return 4.0D;
    }
}