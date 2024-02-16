package me.trae.champions.role.interfaces;

import me.trae.core.utility.objects.SoundCreator;
import org.bukkit.Material;
import org.bukkit.entity.Player;

import java.util.List;

public interface IRole {

    String getPrefix();

    String[] getDescription();

    List<Material> getArmour();

    SoundCreator getDamageSound();

    List<Player> getUsers();

    boolean isUserByPlayer(final Player player);
}