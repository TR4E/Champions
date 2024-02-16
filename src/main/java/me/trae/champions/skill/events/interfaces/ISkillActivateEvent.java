package me.trae.champions.skill.events.interfaces;

import me.trae.core.event.types.IPlayerEvent;

public interface ISkillActivateEvent extends IPlayerEvent {

    int getLevel();

    void setLevel(final int level);
}