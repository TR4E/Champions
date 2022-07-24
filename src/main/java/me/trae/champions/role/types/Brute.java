package me.trae.champions.role.types;

import me.trae.champions.role.Role;
import me.trae.core.framework.SpigotPlugin;
import org.bukkit.Material;

public class Brute extends Role {

    public Brute(final SpigotPlugin instance) {
        super(instance);
    }

    @Override
    public void registerSkills() {
    }

    @Override
    public Material[] getArmour() {
        return new Material[]{
                Material.DIAMOND_HELMET,
                Material.DIAMOND_CHESTPLATE,
                Material.DIAMOND_LEGGINGS,
                Material.DIAMOND_BOOTS
        };
    }

    @Override
    public String[] getDescription() {
        return new String[0];
    }
}