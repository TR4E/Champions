package me.trae.champions.skill.types.interfaces;

import me.trae.champions.skill.enums.SkillType;
import org.bukkit.entity.Player;

public interface IDropSkill {

    SkillType getSkillType();

    void onActivate(final Player player, final int level);
}