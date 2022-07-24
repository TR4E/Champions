package me.trae.champions.role.types;

import me.trae.champions.role.Role;
import me.trae.core.framework.SpigotPlugin;
import org.bukkit.Material;

public class Mage extends Role {

    public Mage(final SpigotPlugin instance) {
        super(instance);
    }

    @Override
    public void registerSkills() {
    }

    @Override
    public Material[] getArmour() {
        return new Material[]{
                Material.GOLD_HELMET,
                Material.GOLD_CHESTPLATE,
                Material.GOLD_LEGGINGS,
                Material.GOLD_BOOTS
        };
    }

    @Override
    public String[] getDescription() {
        return new String[0];
    }
}