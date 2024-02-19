package me.trae.champions.skill.enums;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.util.List;

public interface ISkillType {

    String getName();

    List<Material> getMaterials();

    ItemStack getDisplayItemStack();
}