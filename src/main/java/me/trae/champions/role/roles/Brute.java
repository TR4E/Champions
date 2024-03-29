package me.trae.champions.role.roles;

import me.trae.champions.role.Role;
import me.trae.champions.role.RoleManager;
import me.trae.core.utility.objects.SoundCreator;
import org.bukkit.Material;
import org.bukkit.Sound;

import java.util.Arrays;
import java.util.List;

public class Brute extends Role {

    public Brute(final RoleManager manager) {
        super(manager);
    }

    @Override
    public String[] getDescription() {
        return new String[]{
                "Brutes control large crowds of enemies!",
                "with their unique abilities!"
        };
    }

    @Override
    public List<Material> getArmour() {
        return Arrays.asList(Material.DIAMOND_HELMET, Material.DIAMOND_CHESTPLATE, Material.DIAMOND_LEGGINGS, Material.DIAMOND_BOOTS);
    }

    @Override
    public SoundCreator getDamageSound() {
        return new SoundCreator(Sound.BLAZE_HIT, 1.0F, 0.9F);
    }
}