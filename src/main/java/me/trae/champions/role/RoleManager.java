package me.trae.champions.role;

import me.trae.champions.role.commands.KitCommand;
import me.trae.champions.role.interfaces.IRoleManager;
import me.trae.champions.role.listeners.CustomItemNames;
import me.trae.champions.role.listeners.HandleRoleEquipUpdater;
import me.trae.champions.role.types.Assassin;
import me.trae.champions.role.types.Ranger;
import me.trae.core.client.ClientManager;
import me.trae.core.client.SpigotClient;
import me.trae.core.framework.SpigotManager;
import me.trae.core.framework.SpigotPlugin;
import me.trae.core.item.ItemManager;
import me.trae.core.utility.UtilMessage;
import me.trae.framework.utility.UtilJava;
import me.trae.framework.utility.other.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.*;

public class RoleManager extends SpigotManager implements IRoleManager {

    private final Map<UUID, String> playerRoles;

    public RoleManager(final SpigotPlugin instance) {
        super(instance);

        this.playerRoles = new HashMap<>();
    }

    @Override
    public void registerModules() {
        // Commands
        addModule(new KitCommand(this));

        // Listeners
        addModule(new HandleRoleEquipUpdater(this));
        addModule(new CustomItemNames(this));
    }

    @Override
    public Map<UUID, String> getPlayerRoles() {
        return this.playerRoles;
    }

    @Override
    public void setPlayerRole(final Player player, final Role role) {
        this.getPlayerRoles().put(player.getUniqueId(), (role != null ? role.getName() : null));
    }

    @Override
    public void removePlayerRole(final Player player) {
        this.getPlayerRoles().remove(player.getUniqueId());
    }

    @Override
    public Role getPlayerRole(final Player player) {
        if (this.getPlayerRoles().containsKey(player.getUniqueId())) {
            final String roleName = this.getPlayerRoles().getOrDefault(player.getUniqueId(), null);
            if (roleName != null) {
                return UtilJava.cast(Role.class, getInstance().getManager(roleName));
            }
        }
        return null;
    }

    @Override
    public boolean hasPlayerRole(final Player player) {
        return (this.getPlayerRoles().getOrDefault(player.getUniqueId(), null) != null);
    }

    @Override
    public boolean hasPlayerRole(final Player player, final Role role) {
        final Role playerRole = this.getPlayerRole(player);
        if (playerRole == null) {
            return false;
        }
        return (playerRole == role);
    }

    @Override
    public void giveKit(final Player player, final Role role) {
        final SpigotClient client = getInstance().getManager(ClientManager.class).getClient(player.getUniqueId());
        if (client == null) {
            return;
        }

        final boolean OP = client.isAdministrating();

        final ItemManager itemManager = UtilJava.cast(ItemManager.class, "Item Manager");

        itemManager.insertArmour(player, Arrays.stream(role.getArmour()).map(ItemStack::new).toArray(ItemStack[]::new));

        final List<ItemStack> list = new ArrayList<>();

        list.add(new ItemStack(OP ? Material.GOLD_SWORD : Material.IRON_SWORD));
        list.add(new ItemStack(OP ? Material.DIAMOND_AXE : Material.IRON_AXE));

        if (role instanceof Assassin || role instanceof Ranger) {
            list.add(new ItemStack(Material.BOW));
            list.add(new ItemStack(Material.ARROW, OP ? 64 : 32));
        }

        itemManager.insert(player, list.toArray(new ItemStack[0]));

        UtilMessage.message(player, "Kit", "You have received " + ChatColor.WHITE + role.getName() + " Class" + ChatColor.GRAY + "!");
    }
}