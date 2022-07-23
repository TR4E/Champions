package me.trae.champions.skill.interfaces;

import me.trae.champions.skill.data.SkillData;
import me.trae.champions.skill.enums.SkillType;
import org.bukkit.entity.Player;

import java.util.Map;
import java.util.UUID;

public interface ISkill<SD extends SkillData> {

    SkillType getSkillType();

    String getName(final int level);

    String getName(final Player player);

    int getDefaultSkillLevel();

    int getSkillTokenCost();

    int getMaxSkillLevel();

    Map<UUID, SD> getUsers();

    void addUser(final Player player, final SD data);

    void removeUser(final Player player);

    SD getUser(final Player player);

    boolean isUser(final Player player);

    void reset(final Player player);

    boolean canActivate(final Player player);
}