package me.trae.champions.skill.enums;

import me.trae.framework.shared.utility.UtilFormat;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public enum SkillType implements ISkillType {

    SWORD,
    AXE,
    BOW,
    PASSIVE_A,
    PASSIVE_B,
    GLOBAL;

    private final String name;

    SkillType() {
        this.name = UtilFormat.cleanString(this.name());
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public List<Material> getMaterials() {
        final List<Material> list = new ArrayList<>();

        switch (this) {
            case SWORD:
                list.addAll(Arrays.asList(Material.IRON_SWORD, Material.GOLD_SWORD, Material.DIAMOND_SWORD));
                break;
            case AXE:
                list.addAll(Arrays.asList(Material.IRON_AXE, Material.GOLD_AXE, Material.DIAMOND_AXE));
                break;
            case BOW:
                list.add(Material.BOW);
                break;
        }

        return list;
    }

    @Override
    public ItemStack getDisplayItemStack() {
        Material material = Material.INK_SACK;
        short durability = 0;

        switch (this) {
            case SWORD:
            case AXE:
            case BOW:
                material = this.getMaterials().get(0);
                break;
            case PASSIVE_A:
                durability = 1;
                break;
            case PASSIVE_B:
                durability = 14;
                break;
            case GLOBAL:
                durability = 11;
                break;
        }

        return new ItemStack(material, 1, durability);
    }
}