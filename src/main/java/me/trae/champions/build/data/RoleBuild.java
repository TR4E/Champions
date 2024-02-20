package me.trae.champions.build.data;

import me.trae.champions.build.data.interfaces.IRoleBuild;
import me.trae.champions.build.enums.RoleBuildProperty;
import me.trae.champions.role.Role;
import me.trae.champions.skill.enums.SkillType;
import me.trae.framework.shared.utility.interfaces.property.PropertyContainer;
import me.trae.framework.shared.utility.objects.EnumData;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class RoleBuild implements IRoleBuild, PropertyContainer<RoleBuildProperty> {

    private final UUID uuid;
    private final String name;
    private final int id;
    private final LinkedHashMap<String, RoleSkill> skills;

    private boolean active;

    public RoleBuild(final UUID uuid, final String name, final int id, final boolean active) {
        this.uuid = uuid;
        this.name = name;
        this.id = id;
        this.skills = new LinkedHashMap<>();
        this.active = active;
    }

    public RoleBuild(final UUID uuid, final Role role, final int id) {
        this(uuid, role.getName(), id, false);
    }

    public RoleBuild(final EnumData<RoleBuildProperty> data) {
        this(UUID.fromString(data.get(String.class, "Key")), data.get(String.class, "Type 0"), data.get(Integer.class, "Type 1"), data.get(Boolean.class, RoleBuildProperty.ACTIVE));

        data.getList(String.class, RoleBuildProperty.SKILLS).forEach(string -> this.addSkill(new RoleSkill(string.split(":"))));
    }

    @Override
    public UUID getUUID() {
        return this.uuid;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public int getID() {
        return this.id;
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
            case SKILLS:
                return this.getSkills().values().stream().map(RoleSkill::toString).collect(Collectors.toList());
            case ACTIVE:
                return this.isActive();
        }

        return null;
    }

    @Override
    public List<RoleBuildProperty> getProperties() {
        return Arrays.asList(RoleBuildProperty.values());
    }
}