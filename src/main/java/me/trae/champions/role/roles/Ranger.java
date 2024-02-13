package me.trae.champions.role.roles;

import me.trae.champions.role.Role;
import me.trae.champions.role.RoleManager;
import me.trae.core.utility.objects.SoundCreator;
import org.bukkit.Material;

import java.util.Arrays;
import java.util.List;

public class Ranger extends Role {

    public Ranger(final RoleManager manager) {
        super(manager);
    }

    @Override
    public String[] getDescription() {
        return new String[0];
    }

    @Override
    public List<Material> getArmour() {
        return Arrays.asList(Material.CHAINMAIL_HELMET, Material.CHAINMAIL_CHESTPLATE, Material.CHAINMAIL_LEGGINGS, Material.CHAINMAIL_BOOTS);
    }

    @Override
    public SoundCreator getDamageSound() {
        return null;
    }
}