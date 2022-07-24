package me.trae.champions.build.listeners;

import me.trae.champions.build.BuildManager;
import me.trae.core.framework.SpigotListener;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerJoinEvent;

public class HandleLoadBuilds extends SpigotListener<BuildManager> {

    public HandleLoadBuilds(final BuildManager manager) {
        super(manager);
    }

    @EventHandler
    public void onPlayerJoin(final PlayerJoinEvent event) {
        final Player player = event.getPlayer();
    }
}