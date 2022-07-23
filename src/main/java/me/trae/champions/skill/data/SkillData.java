package me.trae.champions.skill.data;

import me.trae.champions.skill.data.interfaces.ISkillData;
import org.bukkit.entity.Player;

import java.util.UUID;

public class SkillData implements ISkillData {

    private final UUID uuid;
    private final int level;

    public SkillData(final Player player, final int level) {
        this.uuid = player.getUniqueId();
        this.level = level;
    }

    @Override
    public UUID getUUID() {
        return this.uuid;
    }

    @Override
    public int getLevel() {
        return this.level;
    }
}