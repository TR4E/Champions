package me.trae.champions.skill.types.enums.interfaces;

import me.trae.core.utility.enums.ActionType;
import org.bukkit.Material;

import java.util.List;

public interface IActiveSkillType {

    ActionType getActionType();

    List<Material> getMaterials();
}