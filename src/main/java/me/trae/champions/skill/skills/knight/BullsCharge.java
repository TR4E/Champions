package me.trae.champions.skill.skills.knight;

import me.trae.champions.role.roles.Knight;
import me.trae.champions.skill.Skill;
import me.trae.champions.skill.data.SkillData;
import me.trae.champions.skill.enums.SkillType;
import me.trae.framework.shared.utility.UtilFormat;
import me.trae.framework.shared.utility.UtilTime;

public class BullsCharge extends Skill<Knight, SkillData> {

    public BullsCharge(final Knight module) {
        super(module, SkillType.AXE);
    }

    @Override
    public String[] getDescription(final int level) {
        return new String[]{
                "What a skill you can use.",
                "",
                "Such wow.",
                "",
                String.format(UtilFormat.pairString("<gray>Recharge", "<green>%s"), UtilTime.getTime(5000L))
        };
    }

    @Override
    public int getDefaultLevel() {
        return 2;
    }
}