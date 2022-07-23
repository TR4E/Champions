package me.trae.champions.build;

import me.trae.champions.build.interfaces.IRoleBuild;
import me.trae.champions.role.Role;
import me.trae.champions.skill.Skill;
import me.trae.champions.skill.enums.SkillType;
import me.trae.framework.utility.UtilJava;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class RoleBuild implements IRoleBuild {

    private final UUID uuid;
    private final String roleName;
    private final int id;
    private final Map<SkillType, RoleSkill> roleSkills;
    private boolean active;

    public RoleBuild(final UUID uuid, final String roleName, final int id) {
        this.uuid = uuid;
        this.roleName = roleName;
        this.id = id;
        this.roleSkills = new HashMap<>();
        this.active = false;
    }

    public RoleBuild(final Player player, final Role role, final int id) {
        this(player.getUniqueId(), role.getName(), id);
    }

    @Override
    public UUID getUUID() {
        return this.uuid;
    }

    @Override
    public String getRoleName() {
        return this.roleName;
    }

    @Override
    public int getID() {
        return this.id;
    }

    @Override
    public Map<SkillType, RoleSkill> getRoleSkills() {
        return this.roleSkills;
    }

    @Override
    public void addRoleSkill(final Skill<?, ?> skill, final int level) {
        this.getRoleSkills().put(skill.getSkillType(), new RoleSkill(skill, level));
    }

    @Override
    public RoleSkill getRoleSkill(final Skill<?, ?> skill) {
        return this.getRoleSkills().getOrDefault(skill.getSkillType(), null);
    }

    @Override
    public boolean isRoleSkill(final Skill<?, ?> skill) {
        return this.getRoleSkills().containsKey(skill.getSkillType());
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
    public String getDisplayName() {
        if (this instanceof DefaultRoleBuild) {
            return "Default Build";
        }
        return "Custom Build #" + this.getID();
    }

    @Override
    public int getSkillPoints(final Role role) {
        int points = 12;

        for (final RoleSkill roleSkill : this.getRoleSkills().values()) {
            final Skill<?, ?> skill = UtilJava.cast(Skill.class, role.getModule(roleSkill.getName()));
            if (skill == null) {
                continue;
            }

            points -= (roleSkill.getLevel() * skill.getSkillTokenCost());
        }

        return points;
    }
}