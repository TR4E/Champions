package me.trae.champions.skill.enums;

import me.trae.core.utility.other.ActionType;
import me.trae.framework.utility.UtilFormat;
import org.bukkit.Material;

import java.util.Arrays;
import java.util.List;

public enum SkillType {

    SWORD(ActionType.RIGHT_CLICK, Material.GOLD_SWORD, Material.DIAMOND_SWORD, Material.IRON_SWORD),
    AXE(ActionType.RIGHT_CLICK, Material.GOLD_AXE, Material.DIAMOND_AXE, Material.IRON_AXE),
    SWORD_AND_AXE(ActionType.RIGHT_CLICK, Material.GOLD_SWORD, Material.DIAMOND_SWORD, Material.IRON_SWORD, Material.GOLD_AXE, Material.DIAMOND_AXE, Material.IRON_AXE),
    BOW(ActionType.LEFT_CLICK, Material.BOW),
    PASSIVE_A(null),
    PASSIVE_B(null),
    GLOBAL(null);

    private final ActionType actionType;
    private final List<Material> materials;

    SkillType(final ActionType actionType, final Material... materials) {
        this.actionType = actionType;
        this.materials = Arrays.asList(materials);
    }

    public static SkillType getSkillType(final Material material) {
        for (final SkillType skillType : values()) {
            if (!(skillType.getMaterials().contains(material))) {
                continue;
            }

            return skillType;
        }
        return null;
    }

    public String getName() {
        return UtilFormat.cleanString(this.name());
    }

    public ActionType getActionType() {
        return this.actionType;
    }

    public List<Material> getMaterials() {
        return this.materials;
    }
}