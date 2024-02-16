package me.trae.champions.role;

import me.trae.champions.role.commands.RoleCommand;
import me.trae.champions.role.interfaces.IRoleManager;
import me.trae.champions.role.modules.HandleCustomDeathMessageReceiver;
import me.trae.champions.role.modules.HandleItemStackUpdate;
import me.trae.champions.role.modules.HandleRoleEquip;
import me.trae.champions.role.modules.RemovePotionEffectsOnRoleChange;
import me.trae.champions.role.roles.*;
import me.trae.champions.role.roles.interfaces.Archer;
import me.trae.core.client.ClientManager;
import me.trae.core.framework.SpigotManager;
import me.trae.core.framework.SpigotPlugin;
import me.trae.core.item.ItemManager;
import me.trae.core.utility.UtilSearch;
import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.*;
import java.util.function.Function;
import java.util.function.Predicate;

public class RoleManager extends SpigotManager implements IRoleManager {

    private final Map<UUID, Role> PLAYER_ROLES = new HashMap<>();

    public RoleManager(final SpigotPlugin instance) {
        super(instance);

        this.addPrimitive("Overpowered-Kit", false);
        this.addPrimitive("Starter-Kit", false);
    }

    @Override
    public void registerModules() {
        // Roles
        addModule(new Assassin(this));
        addModule(new Brute(this));
        addModule(new Knight(this));
        addModule(new Mage(this));
        addModule(new Ranger(this));

        // Commands
        addModule(new RoleCommand(this));

        // Modules
        addModule(new HandleCustomDeathMessageReceiver(this));
        addModule(new HandleItemStackUpdate(this));
        addModule(new HandleRoleEquip(this));
        addModule(new RemovePotionEffectsOnRoleChange(this));
    }

    @Override
    public Map<UUID, Role> getPlayerRoles() {
        return this.PLAYER_ROLES;
    }

    @Override
    public void setPlayerRole(final Player player, final Role role) {
        this.getPlayerRoles().put(player.getUniqueId(), role);
    }

    @Override
    public void removePlayerRole(final Player player) {
        this.getPlayerRoles().remove(player.getUniqueId());
    }

    @Override
    public Role getPlayerRole(final Player player) {
        return this.getPlayerRoles().getOrDefault(player.getUniqueId(), null);
    }

    @Override
    public boolean hasPlayerRole(final Player player) {
        return this.getPlayerRoles().containsKey(player.getUniqueId());
    }

    @Override
    public Role searchRole(final CommandSender sender, final String name, final boolean inform) {
        final List<Predicate<Role>> predicates = Arrays.asList(
                (role -> role.getName().equalsIgnoreCase(name)),
                (role -> role.getName().toLowerCase().contains(name.toLowerCase()))
        );

        final Function<Role, String> function = (Role::getName);

        return UtilSearch.search(Role.class, this.getModulesByClass(Role.class), predicates, null, function, "Role Search", sender, name, inform);
    }

    @Override
    public void giveRole(final Player player, final Role role) {
        final boolean overpowered = this.getPrimitiveCasted(Boolean.class, "Overpowered-Kit") || this.getInstance().getManagerByClass(ClientManager.class).getClientByPlayer(player).isAdministrating();

        final List<ItemStack> list = new ArrayList<>();

        list.add(new ItemStack(overpowered ? Material.DIAMOND_SWORD : Material.IRON_SWORD));
        list.add(new ItemStack(overpowered ? Material.GOLD_AXE : Material.IRON_AXE));

        if (role instanceof Archer) {
            list.add(new ItemStack(Material.BOW));
            list.add(new ItemStack(Material.ARROW, overpowered ? 64 : 32));
        }

        if (this.getPrimitiveCasted(Boolean.class, "Starter-Kit")) {
            list.add(new ItemStack(overpowered ? Material.DIAMOND_SPADE : Material.IRON_SPADE));
            list.add(new ItemStack(overpowered ? Material.DIAMOND_PICKAXE : Material.IRON_PICKAXE));
        }

        final ItemManager itemManager = this.getInstance().getManagerByClass(ItemManager.class);

        itemManager.insertArmour(player, new ItemStack(role.getArmour().get(0)), new ItemStack(role.getArmour().get(1)), new ItemStack(role.getArmour().get(2)), new ItemStack(role.getArmour().get(3)));
        itemManager.insert(player, list);
    }
}