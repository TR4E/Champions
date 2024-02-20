package me.trae.champions.build.modules;

import me.trae.champions.build.BuildManager;
import me.trae.core.framework.types.SpigotListener;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.player.PlayerQuitEvent;

public class HandleUnLoadRoleBuildDataOnPlayerQuit extends SpigotListener<BuildManager> {

    public HandleUnLoadRoleBuildDataOnPlayerQuit(final BuildManager manager) {
        super(manager);
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void onPlayerQuit(final PlayerQuitEvent event) {
        this.getManager().getRoleBuilds().keySet().removeIf(uuid -> event.getPlayer().getUniqueId().equals(uuid));
    }
}