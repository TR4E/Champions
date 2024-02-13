package me.trae.champions.role.interfaces;

import me.trae.core.utility.objects.SoundCreator;
import org.bukkit.Material;

import java.util.List;

public interface IRole {

    String[] getDescription();

    List<Material> getArmour();

    SoundCreator getDamageSound();
}