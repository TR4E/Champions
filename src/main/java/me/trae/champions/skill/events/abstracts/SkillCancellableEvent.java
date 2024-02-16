package me.trae.champions.skill.events.abstracts;

import me.trae.champions.skill.Skill;
import me.trae.champions.skill.events.abstracts.interfaces.ISkillEvent;
import me.trae.core.event.CustomCancellableEvent;

public class SkillCancellableEvent extends CustomCancellableEvent implements ISkillEvent {

    private final Skill<?, ?> skill;

    public SkillCancellableEvent(final Skill<?, ?> skill) {
        this.skill = skill;
    }

    @Override
    public Skill<?, ?> getSkill() {
        return this.skill;
    }
}