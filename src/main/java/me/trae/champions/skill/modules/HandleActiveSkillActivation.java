package me.trae.champions.skill.modules;

import me.trae.champions.build.data.RoleBuild;
import me.trae.champions.build.data.RoleSkill;
import me.trae.champions.role.Role;
import me.trae.champions.role.RoleManager;
import me.trae.champions.skill.SkillManager;
import me.trae.champions.skill.enums.SkillType;
import me.trae.champions.skill.events.SkillActivateEvent;
import me.trae.champions.skill.events.SkillPreActivateEvent;
import me.trae.champions.skill.types.ActiveSkill;
import me.trae.champions.skill.types.enums.ActiveSkillType;
import me.trae.champions.weapon.items.types.ChampionsWeapon;
import me.trae.core.framework.types.SpigotListener;
import me.trae.core.utility.UtilServer;
import me.trae.core.weapon.Weapon;
import me.trae.core.weapon.WeaponManager;
import me.trae.framework.shared.utility.UtilJava;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public class HandleActiveSkillActivation extends SpigotListener<SkillManager> {

    public HandleActiveSkillActivation(final SkillManager manager) {
        super(manager);
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void onPlayerInteract(final PlayerInteractEvent event) {
        final Player player = event.getPlayer();

        final ItemStack itemStack = player.getInventory().getItemInHand();
        if (itemStack == null) {
            return;
        }

        final Weapon<?> weapon = this.getInstance().getManagerByClass(WeaponManager.class).getWeaponByItemStack(itemStack);
        if (weapon != null && !(weapon instanceof ChampionsWeapon<?>)) {
            return;
        }

        final Role role = this.getInstance().getManagerByClass(RoleManager.class).getPlayerRole(player);
        if (role == null) {
            return;
        }

        RoleBuild roleBuild = role.getActiveRoleBuildByPlayer(player);
        if (roleBuild == null) {
            roleBuild = role.getDefaultRoleBuildByPlayer(player);
        }

        final ActiveSkillType activeSkillType = ActiveSkillType.getByMaterial(itemStack.getType());
        if (activeSkillType == null) {
            return;
        }

        if (!(activeSkillType.getActionType().getActions().contains(event.getAction()))) {
            return;
        }

        final RoleSkill roleSkill = roleBuild.getSkillByType(SkillType.valueOf(activeSkillType.name()));
        if (roleSkill == null) {
            return;
        }

        final ActiveSkill<?, ?> skill = UtilJava.cast(ActiveSkill.class, role.getSubModuleByName(roleSkill.getName()));
        if (skill == null) {
            return;
        }

        final int level = skill.getLevel(player);
        if (level == 0) {
            return;
        }

        final SkillPreActivateEvent preActivateEvent = new SkillPreActivateEvent(skill, player, level);
        UtilServer.callEvent(preActivateEvent);
        if (preActivateEvent.isCancelled()) {
            return;
        }

        if (!(this.canActivate(player, skill))) {
            return;
        }

        if (skill.isActive(player)) {
            return;
        }

        final SkillActivateEvent activateEvent = new SkillActivateEvent(preActivateEvent);
        UtilServer.callEvent(activateEvent);
        if (activateEvent.isCancelled()) {
            return;
        }

        skill.onActivate(player, level);
    }

    private boolean canActivate(final Player player, final ActiveSkill<?, ?> skill) {
        return skill.canActivate(player);
    }
}