package me.trae.champions.skill.events;

import me.trae.champions.skill.Skill;
import me.trae.champions.skill.events.abstracts.SkillCancellableEvent;
import me.trae.champions.skill.events.interfaces.ISkillActivateEvent;
import org.bukkit.entity.Player;

public class SkillPreActivateEvent extends SkillCancellableEvent implements ISkillActivateEvent {

    private final Player player;

    private int level;

    public SkillPreActivateEvent(final Skill<?, ?> skill, final Player player, final int level) {
        super(skill);

        this.player = player;
        this.level = level;
    }

    @Override
    public Player getPlayer() {
        return this.player;
    }

    @Override
    public int getLevel() {
        return this.level;
    }

    @Override
    public void setLevel(final int level) {
        this.level = level;
    }
}