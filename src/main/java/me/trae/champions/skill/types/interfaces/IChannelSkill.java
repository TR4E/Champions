package me.trae.champions.skill.types.interfaces;

import org.bukkit.entity.Player;

public interface IChannelSkill {

    void onUsing(final Player player, final int level);
}