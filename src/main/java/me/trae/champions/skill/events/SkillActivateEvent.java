package me.trae.champions.skill.events;

import me.trae.champions.skill.events.abstracts.SkillCancellableEvent;
import me.trae.champions.skill.events.interfaces.ISkillActivateEvent;
import org.bukkit.entity.Player;

public class SkillActivateEvent extends SkillCancellableEvent implements ISkillActivateEvent {

    private final Player player;

    private int level;

    public SkillActivateEvent(final SkillPreActivateEvent preActivateEvent) {
        super(preActivateEvent.getSkill());

        this.player = preActivateEvent.getPlayer();
        this.level = preActivateEvent.getLevel();
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