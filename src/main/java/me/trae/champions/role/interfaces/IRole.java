package me.trae.champions.role.interfaces;

import me.trae.champions.build.data.RoleBuild;
import me.trae.champions.skill.Skill;
import me.trae.champions.skill.enums.SkillType;
import me.trae.core.utility.objects.SoundCreator;
import org.bukkit.Material;
import org.bukkit.entity.Player;

import java.util.List;

public interface IRole {

    String getPrefix();

    String[] getDescription();

    List<Material> getArmour();

    SoundCreator getDamageSound();

    List<Player> getUsers();

    boolean isUserByPlayer(final Player player);

    RoleBuild getDefaultRoleBuild(final Player player);

    RoleBuild getActiveRoleBuild(final Player player);

    List<String> getEquipMessage(final RoleBuild roleBuild);

    List<Skill<?, ?>> getSkillsByType(final SkillType skillType);

    int getMaxSkillTokens();
}