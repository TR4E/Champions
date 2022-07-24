package me.trae.champions.role.listeners;

import me.trae.champions.role.Role;
import me.trae.champions.role.RoleManager;
import me.trae.core.framework.SpigotListener;
import me.trae.core.item.events.ItemUpdateEvent;
import me.trae.framework.utility.UtilFormat;
import me.trae.framework.utility.other.ChatColor;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;

import java.util.Arrays;

public class CustomItemNames extends SpigotListener<RoleManager> {

    public CustomItemNames(final RoleManager manager) {
        super(manager);
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onItemUpdate(final ItemUpdateEvent event) {
        if (event.isCancelled()) {
            return;
        }

        final Material material = event.getMaterial();

        switch (material) {
            case GOLD_SWORD: {
                event.setDisplayName("Radiant Sword");
                event.addLore(ChatColor.WHITE + "Increases Sword Damage by 1.");
                return;
            }
            case GOLD_AXE: {
                event.setDisplayName("Radiant Axe");
                event.addLore(ChatColor.WHITE + "Increases Axe Damage by 1.");
                return;
            }
            case DIAMOND_SWORD: {
                event.setDisplayName("Booster Sword");
                event.addLore(ChatColor.WHITE + "Increases Sword Skill Level by 1.");
                return;
            }
            case DIAMOND_AXE: {
                event.setDisplayName("Booster Axe");
                event.addLore(ChatColor.WHITE + "Increases Axe Skill Level by 1.");
                return;
            }
            case IRON_SWORD: {
                event.setDisplayName("Standard Sword");
                event.addLore(ChatColor.WHITE + "Just a Standard Sword.");
                return;
            }
            case IRON_AXE: {
                event.setDisplayName("Standard Axe");
                event.addLore(ChatColor.WHITE + "Just a Standard Axe.");
                return;
            }
        }

        for (final Role role : getInstance().getManagers(Role.class)) {
            if (!(role.isEnabled())) {
                continue;
            }

            if (!(Arrays.asList(role.getArmour()).contains(material))) {
                continue;
            }

            final String[] description = role.getDescription();
            if (description.length > 0) {
                for (final String line : description) {
                    event.addLore(line);
                }
            }

            event.setDisplayName(role.getName() + " " + UtilFormat.cleanString(material.name().split("_")[1]));
            break;
        }
    }
}