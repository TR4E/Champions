package me.trae.champions.weapon.types;

import me.trae.core.weapon.WeaponManager;
import me.trae.core.weapon.data.WeaponData;
import me.trae.core.weapon.types.VanillaItem;
import me.trae.framework.shared.utility.enums.ChatColor;
import org.bukkit.inventory.ItemStack;

public class ChampionsWeapon<D extends WeaponData> extends VanillaItem<D> {

    public ChampionsWeapon(final WeaponManager manager, final ItemStack itemStack) {
        super(manager, itemStack);
    }

    @Override
    public String getDisplayName() {
        return ChatColor.GOLD + this.getName();
    }
}