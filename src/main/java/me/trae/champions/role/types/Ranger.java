package me.trae.champions.role.types;

import me.trae.champions.role.Role;
import me.trae.core.framework.SpigotPlugin;
import org.bukkit.Material;

public class Ranger extends Role {

    public Ranger(final SpigotPlugin instance) {
        super(instance);
    }

    @Override
    public void registerSkills() {
    }

    @Override
    public Material[] getArmour() {
        return new Material[]{
                Material.CHAINMAIL_HELMET,
                Material.CHAINMAIL_CHESTPLATE,
                Material.CHAINMAIL_LEGGINGS,
                Material.CHAINMAIL_BOOTS
        };
    }
}