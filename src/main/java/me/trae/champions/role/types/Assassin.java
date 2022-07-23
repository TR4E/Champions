package me.trae.champions.role.types;

import me.trae.champions.role.Role;
import me.trae.core.framework.SpigotPlugin;
import org.bukkit.Material;

public class Assassin extends Role {

    public Assassin(final SpigotPlugin instance) {
        super(instance);
    }

    @Override
    public void registerSkills() {
    }

    @Override
    public Material[] getArmour() {
        return new Material[]{
                Material.LEATHER_HELMET,
                Material.LEATHER_CHESTPLATE,
                Material.LEATHER_LEGGINGS,
                Material.LEATHER_BOOTS
        };
    }
}