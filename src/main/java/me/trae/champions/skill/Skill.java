package me.trae.champions.skill;

import me.trae.champions.role.Role;
import me.trae.champions.skill.data.SkillData;
import me.trae.champions.skill.enums.SkillType;
import me.trae.champions.skill.interfaces.ISkill;
import me.trae.core.framework.SpigotModule;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class Skill<R extends Role, SD extends SkillData> extends SpigotModule<R> implements ISkill<SD> {

    private final SkillType skillType;
    private final Map<UUID, SD> users;

    public Skill(final R manager, final SkillType skillType) {
        super(manager);

        this.skillType = skillType;
        this.users = new HashMap<>();
    }

    @Override
    public int getDefaultSkillLevel() {
        return 2;
    }

    @Override
    public int getSkillTokenCost() {
        return 1;
    }

    @Override
    public int getMaxSkillLevel() {
        return 3;
    }

    @Override
    public SkillType getSkillType() {
        return this.skillType;
    }

    @Override
    public String getName(final int level) {
        return this.getName() + " " + level;
    }

    @Override
    public String getName(final Player player) {
        final int level = (this.isUser(player) ? this.getUser(player).getLevel() : 0);

        return this.getName(level);
    }

    @Override
    public Map<UUID, SD> getUsers() {
        return this.users;
    }

    @Override
    public void addUser(final Player player, final SD data) {
        this.getUsers().put(player.getUniqueId(), data);
    }

    @Override
    public void removeUser(final Player player) {
        this.getUsers().remove(player.getUniqueId());
    }

    @Override
    public SD getUser(final Player player) {
        return this.getUsers().getOrDefault(player.getUniqueId(), null);
    }

    @Override
    public boolean isUser(final Player player) {
        return this.getUsers().containsKey(player.getUniqueId());
    }

    @Override
    public void reset(final Player player) {
        this.removeUser(player);
    }

    @Override
    public boolean canActivate(final Player player) {
        return false;
    }
}