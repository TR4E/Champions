package me.trae.champions.skill.events.abstracts.interfaces;

import me.trae.champions.skill.Skill;

public interface ISkillEvent {

    Skill<?, ?> getSkill();
}