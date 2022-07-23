package me.trae.champions.role;

import me.trae.champions.role.interfaces.IRole;
import me.trae.core.framework.SpigotManager;
import me.trae.core.framework.SpigotPlugin;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;

public abstract class Role extends SpigotManager implements IRole {

    public Role(final SpigotPlugin instance) {
        super(instance);
    }

    @Override
    public void registerModules() {
    }

    @Override
    public boolean hasArmour(final Player player) {
        for (final ItemStack armor : player.getEquipment().getArmorContents()) {
            if (armor != null && Arrays.asList(this.getArmour()).contains(armor.getType())) {
                continue;
            }

            return false;
        }
        return true;
    }

    @Override
    public void sendEquipMessage(final Player player) {
    }
}