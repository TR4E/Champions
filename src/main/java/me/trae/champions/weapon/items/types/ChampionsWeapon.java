package me.trae.champions.weapon.items.types;

import me.trae.core.weapon.WeaponManager;
import me.trae.core.weapon.data.WeaponData;
import me.trae.core.weapon.types.VanillaItem;
import org.bukkit.inventory.ItemStack;

public class ChampionsWeapon<D extends WeaponData> extends VanillaItem<D> {

    public ChampionsWeapon(final WeaponManager manager, final ItemStack itemStack) {
        super(manager, itemStack);
    }

    @Override
    public String getDisplayName() {
        return String.format("<gold>%s", this.getName());
    }
}