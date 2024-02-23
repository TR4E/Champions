package me.trae.champions.skill.enums;

import me.trae.champions.skill.types.enums.ActiveSkillType;
import me.trae.framework.shared.utility.UtilFormat;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

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
    public ItemStack getDisplayItemStack() {
        Material material = Material.INK_SACK;
        short durability = 0;

        switch (this) {
            case SWORD:
            case AXE:
            case BOW:
                material = ActiveSkillType.valueOf(this.name()).getMaterials().get(0);
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