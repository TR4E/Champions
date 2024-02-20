package me.trae.champions.build.modules;

import me.trae.champions.build.BuildManager;
import me.trae.core.framework.types.SpigotListener;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.player.PlayerJoinEvent;

public class HandleLoadRoleBuildDataOnPlayerJoin extends SpigotListener<BuildManager> {

    public HandleLoadRoleBuildDataOnPlayerJoin(final BuildManager manager) {
        super(manager);
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void onPlayerJoin(final PlayerJoinEvent event) {
        this.getManager().getRepository().loadData(event.getPlayer().getUniqueId());
    }
}