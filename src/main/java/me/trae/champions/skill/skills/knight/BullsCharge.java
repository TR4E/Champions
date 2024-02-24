package me.trae.champions.skill.skills.knight;

import me.trae.champions.role.roles.Knight;
import me.trae.champions.skill.data.SkillData;
import me.trae.champions.skill.types.ActiveSkill;
import me.trae.champions.skill.types.enums.ActiveSkillType;
import me.trae.core.recharge.RechargeManager;
import me.trae.core.utility.UtilBlock;
import me.trae.core.utility.UtilEntity;
import me.trae.core.utility.UtilMessage;
import me.trae.core.utility.enums.EffectType;
import me.trae.framework.shared.utility.UtilFormat;
import me.trae.framework.shared.utility.UtilTime;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffectType;

import java.util.Collections;

public class BullsCharge extends ActiveSkill<Knight, SkillData> {

    public BullsCharge(final Knight module) {
        super(module, ActiveSkillType.AXE);

        this.addPrimitive("Recharge", 10_000L);

        this.addPrimitive("Duration", 5_000L);
        this.addPrimitive("Amplifier", 2);
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

    @Override
    public void onActivate(final Player player, final int level) {
        if (this.getInstance().getManagerByClass(RechargeManager.class).add(player, this.getName(), this.getPrimitiveCasted(Long.class, "Recharge"), true)) {
            UtilEntity.addPotionEffect(player, EffectType.SPEED, this.getPrimitiveCasted(Long.class, "Duration"), this.getPrimitiveCasted(Integer.class, "Amplifier"));

            UtilMessage.simpleMessage(player, this.getModule().getName(), "You used <green><var></green>.", Collections.singletonList(this.getDisplayName(level)));
        }
    }

    @Override
    public boolean canActivate(final Player player) {
        if (UtilBlock.isInLiquid(player.getLocation())) {
            UtilMessage.simpleMessage(player, "Skill", "You cannot use <green><var></green> while in liquid.", Collections.singletonList(this.getName()));
            return false;
        }

        return true;
    }
}