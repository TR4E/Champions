package me.trae.champions.skill.modules;

import me.trae.champions.skill.SkillManager;
import me.trae.champions.skill.events.SkillPreActivateEvent;
import me.trae.champions.weapon.items.interfaces.BoosterWeapon;
import me.trae.core.framework.types.SpigotListener;
import me.trae.core.weapon.WeaponManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;

public class HandleSkillLevelOnBoosterWeapons extends SpigotListener<SkillManager> {

    public HandleSkillLevelOnBoosterWeapons(final SkillManager manager) {
        super(manager);
    }

    @EventHandler(priority = EventPriority.LOW)
    public void onSkillPreActivate(final SkillPreActivateEvent event) {
        if (event.isCancelled()) {
            return;
        }

        final Player player = event.getPlayer();

        if (!(this.getInstance().getManagerByClass(WeaponManager.class).getWeaponByItemStack(player.getInventory().getItemInHand()) instanceof BoosterWeapon)) {
            return;
        }

        event.setLevel(event.getLevel() + 1);
    }
}