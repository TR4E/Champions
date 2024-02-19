package me.trae.champions.build.modules;

import me.trae.champions.build.BuildManager;
import me.trae.champions.build.menus.build.BuildCustomizationMenu;
import me.trae.champions.role.Role;
import me.trae.champions.role.RoleManager;
import me.trae.champions.role.menus.RoleSelectionMenu;
import me.trae.core.client.ClientManager;
import me.trae.core.framework.types.SpigotListener;
import me.trae.core.item.events.ItemUpdateEvent;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

public class HandleClassCustomizationTable extends SpigotListener<BuildManager> {

    private final Material MATERIAL = Material.ENCHANTMENT_TABLE;

    public HandleClassCustomizationTable(final BuildManager manager) {
        super(manager);
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void onPlayerInteract(final PlayerInteractEvent event) {
        if (event.getAction() != Action.RIGHT_CLICK_BLOCK) {
            return;
        }

        final Block block = event.getClickedBlock();

        if (block.getType() != this.MATERIAL) {
            return;
        }

        final Player player = event.getPlayer();

        if (this.getInstance().getManagerByClass(ClientManager.class).getClientByPlayer(player).isAdministrating()) {
            return;
        }

        event.setCancelled(true);

        new RoleSelectionMenu(this.getInstance().getManagerByClass(RoleManager.class), player) {
            @Override
            public void onButtonClick(final Player player, final Role role) {
                new BuildCustomizationMenu(HandleClassCustomizationTable.this.getManager(), player, role) {
                    @Override
                    public Role getRole() {
                        return role;
                    }
                };
            }
        };
    }

    @EventHandler
    public void onItemUpdate(final ItemUpdateEvent event) {
        if (event.getBuilder().getItemStack().getType() != this.MATERIAL) {
            return;
        }

        event.getBuilder().setDisplayName("Class Customization");
    }
}