package me.trae.champions.role.modules;

import me.trae.champions.role.Role;
import me.trae.champions.role.RoleManager;
import me.trae.core.framework.types.SpigotListener;
import me.trae.core.item.ItemBuilder;
import me.trae.core.item.events.ItemUpdateEvent;
import me.trae.framework.shared.utility.UtilFormat;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;

public class HandleItemStackUpdate extends SpigotListener<RoleManager> {

    public HandleItemStackUpdate(final RoleManager manager) {
        super(manager);
    }

    @EventHandler
    public void onItemUpdate(final ItemUpdateEvent event) {
        final ItemBuilder builder = event.getBuilder();

        for (final Role role : this.getManager().getModulesByClass(Role.class)) {
            final Material material = builder.getItemStack().getType();

            if (!(role.getArmour().contains(material))) {
                continue;
            }

            builder.setDisplayName(role.getName() + " " + UtilFormat.cleanString(material.name().split("_")[1]));
            break;
        }
    }
}