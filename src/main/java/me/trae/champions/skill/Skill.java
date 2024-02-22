package me.trae.champions.skill;

import me.trae.champions.role.Role;
import me.trae.champions.skill.data.SkillData;
import me.trae.champions.skill.enums.SkillType;
import me.trae.champions.skill.interfaces.ISkill;
import me.trae.champions.weapon.items.interfaces.BoosterWeapon;
import me.trae.core.framework.SpigotSubModule;
import me.trae.core.weapon.WeaponManager;
import org.bukkit.entity.Player;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class Skill<R extends Role, D extends SkillData> extends SpigotSubModule<R> implements ISkill<D> {

    private final SkillType skillType;
    private final Map<UUID, D> users;

    public Skill(final R module, final SkillType skillType) {
        super(module);

        this.skillType = skillType;
        this.users = new HashMap<>();
    }

    @Override
    public SkillType getType() {
        return this.skillType;
    }

    @Override
    public Map<UUID, D> getUsers() {
        return this.users;
    }

    @Override
    public int getLevel(final Player player) {
        final D data = this.getUserByPlayer(player);
        if (data == null) {
            return 0;
        }

        int level = data.getLevel();

        if (Arrays.asList(SkillType.SWORD, SkillType.AXE).contains(this.getType()) && this.getInstance().getManagerByClass(WeaponManager.class).getWeaponByItemStack(player.getInventory().getItemInHand()) instanceof BoosterWeapon) {
            level += 1;
        }

        return level;
    }

    @Override
    public String[] getDescription(final int level) {
        return new String[0];
    }

    @Override
    public int getMaxLevel() {
        return 5;
    }

    @Override
    public int getDefaultLevel() {
        return 0;
    }

    @Override
    public int getTokenCost() {
        return 1;
    }
}