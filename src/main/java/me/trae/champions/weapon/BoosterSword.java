package me.trae.champions.weapon;

import me.trae.core.weapon.WeaponManager;
import me.trae.core.weapon.types.VanillaItem;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class BoosterSword extends VanillaItem {

    public BoosterSword(final WeaponManager manager) {
        super(manager, new ItemStack(Material.GOLD_SWORD));
    }

    @Override
    public String[] getDescription() {
        return new String[]{
                "",
                "Increases Skill Level by 1."
        };
    }
}