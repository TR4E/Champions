package me.trae.champions.build.menus.skill.buttons.interfaces;

import me.trae.champions.build.data.RoleSkill;
import me.trae.champions.skill.Skill;

public interface ISkillSelectButton {

    Skill<?, ?> getSkill();

    RoleSkill getRoleSkill();
}