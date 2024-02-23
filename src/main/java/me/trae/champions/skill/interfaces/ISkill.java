package me.trae.champions.skill.interfaces;

import me.trae.champions.skill.data.SkillData;
import me.trae.champions.skill.enums.SkillType;
import org.bukkit.entity.Player;

import java.util.Map;
import java.util.UUID;

public interface ISkill<D extends SkillData> {

    SkillType getType();

    Map<UUID, D> getUsers();

    default void addUser(final D data) {
        this.getUsers().put(data.getUUID(), data);
    }

    default void removeUser(final Player player) {
        this.getUsers().remove(player.getUniqueId());
    }

    default D getUserByPlayer(final Player player) {
        return this.getUsers().getOrDefault(player.getUniqueId(), null);
    }

    default boolean isUserByPlayer(final Player player) {
        return this.getUsers().containsKey(player.getUniqueId());
    }

    String[] getDescription(final int level);

    String getDisplayName(final int level);

    int getLevel(final Player player);

    int getMaxLevel();

    int getDefaultLevel();

    int getTokenCost();
}