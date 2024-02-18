package me.trae.champions.build.data;

import me.trae.champions.build.data.interfaces.IRoleBuild;
import me.trae.champions.build.enums.RoleBuildProperty;
import me.trae.champions.role.Role;
import me.trae.champions.skill.enums.SkillType;
import me.trae.framework.shared.utility.interfaces.property.PropertyContainer;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.stream.Collectors;

public class RoleBuild implements IRoleBuild, PropertyContainer<RoleBuildProperty> {

    private final int id;
    private final String name;
    private final LinkedHashMap<String, RoleSkill> skills;

    private boolean active;

    public RoleBuild(final int id, final String name) {
        this.id = id;
        this.name = name;
        this.skills = new LinkedHashMap<>();
    }

    public RoleBuild(final int id, final Role role) {
        this(id, role.getName());
    }

    @Override
    public int getID() {
        return this.id;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public LinkedHashMap<String, RoleSkill> getSkills() {
        return this.skills;
    }

    @Override
    public void addSkill(final RoleSkill roleSkill) {
        this.getSkills().put(roleSkill.getType().toUpperCase(), roleSkill);
    }

    @Override
    public void removeSkill(final RoleSkill roleSkill) {
        this.getSkills().remove(roleSkill.getType().toUpperCase());
    }

    @Override
    public RoleSkill getSkillByType(final SkillType skillType) {
        return this.getSkills().getOrDefault(skillType.name(), null);
    }

    @Override
    public boolean isSkillByType(final SkillType skillType) {
        return this.getSkills().containsKey(skillType.name());
    }

    @Override
    public boolean isActive() {
        return this.active;
    }

    @Override
    public void setActive(final boolean active) {
        this.active = active;
    }

    @Override
    public Object getPropertyByValue(final RoleBuildProperty property) {
        switch (property) {
            case ID:
                return this.getID();
            case NAME:
                return this.getName();
            case SKILLS:
                return this.getSkills().values().stream().map(RoleSkill::toString).collect(Collectors.toList());
        }

        return null;
    }

    @Override
    public List<RoleBuildProperty> getProperties() {
        return Arrays.asList(RoleBuildProperty.values());
    }
}