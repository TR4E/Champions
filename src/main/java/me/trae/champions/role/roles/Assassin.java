package me.trae.champions.role.roles;

import me.trae.champions.role.Role;
import me.trae.champions.role.RoleManager;
import me.trae.champions.role.roles.interfaces.Archer;
import me.trae.champions.role.roles.submodules.DisableFallDamage;
import me.trae.champions.role.roles.submodules.SpeedEffect;
import me.trae.core.utility.objects.SoundCreator;
import org.bukkit.Material;
import org.bukkit.Sound;

import java.util.Arrays;
import java.util.List;

public class Assassin extends Role implements Archer {

    public Assassin(final RoleManager manager) {
        super(manager);
    }

    @Override
    public void registerSubModules() {
        addSubModule(new DisableFallDamage(this));
        addSubModule(new SpeedEffect(this));
    }

    @Override
    public String[] getDescription() {
        return new String[]{
                "Assassins can quickly drop foes",
                "with powerful combinations!"
        };
    }

    @Override
    public List<Material> getArmour() {
        return Arrays.asList(Material.LEATHER_HELMET, Material.LEATHER_CHESTPLATE, Material.LEATHER_LEGGINGS, Material.LEATHER_BOOTS);
    }

    @Override
    public SoundCreator getDamageSound() {
        return new SoundCreator(Sound.SHOOT_ARROW, 1.0F, 2.0F);
    }
}