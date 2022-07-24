package me.trae.champions.role.types;

import me.trae.champions.role.Role;
import me.trae.core.framework.SpigotPlugin;
import org.bukkit.Material;

public class Knight extends Role {

    public Knight(final SpigotPlugin instance) {
        super(instance);
    }

    @Override
    public void registerSkills() {
    }

    @Override
    public Material[] getArmour() {
        return new Material[]{
                Material.IRON_HELMET,
                Material.IRON_CHESTPLATE,
                Material.IRON_LEGGINGS,
                Material.IRON_BOOTS
        };
    }

    @Override
    public String[] getDescription() {
        return new String[0];
    }
}