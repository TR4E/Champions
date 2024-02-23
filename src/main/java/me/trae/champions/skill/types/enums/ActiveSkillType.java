package me.trae.champions.skill.types.enums;

import me.trae.champions.skill.types.enums.interfaces.IActiveSkillType;
import me.trae.core.utility.enums.ActionType;
import org.bukkit.Material;

import java.util.Arrays;
import java.util.List;

public enum ActiveSkillType implements IActiveSkillType {

    SWORD(ActionType.RIGHT_CLICK, Material.IRON_SWORD, Material.GOLD_SWORD, Material.DIAMOND_SWORD),
    AXE(ActionType.RIGHT_CLICK, Material.IRON_AXE, Material.GOLD_AXE, Material.DIAMOND_AXE),
    BOW(ActionType.LEFT_CLICK, Material.BOW);

    private final ActionType actionType;
    private final List<Material> materials;

    ActiveSkillType(final ActionType actionType, final Material... materials) {
        this.actionType = actionType;
        this.materials = Arrays.asList(materials);
    }

    public static ActiveSkillType getByMaterial(final Material material) {
        for (final ActiveSkillType activeSkillType : values()) {
            if (!(activeSkillType.getMaterials().contains(material))) {
                continue;
            }

            return activeSkillType;
        }

        return null;
    }

    @Override
    public ActionType getActionType() {
        return this.actionType;
    }

    @Override
    public List<Material> getMaterials() {
        return this.materials;
    }
}