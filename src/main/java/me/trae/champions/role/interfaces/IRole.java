package me.trae.champions.role.interfaces;

import org.bukkit.Material;
import org.bukkit.entity.Player;

public interface IRole {

    void registerSkills();

    Material[] getArmour();

    boolean hasArmour(final Player player);

    void sendEquipMessage(final Player player);
}