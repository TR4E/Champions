package me.trae.champions.skill.types.interfaces;

import me.trae.core.utility.other.ActionType;
import org.bukkit.entity.Player;

public interface IActiveSkill {

    ActionType getActionType();

    void onActivate(final Player player, final int level);
}