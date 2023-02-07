package me.deley.maintenance.listener;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.event.player.PlayerLoginEvent.Result;

import me.deley.maintenance.PluginMain;

public class PlayerJoinListener implements Listener {
	@EventHandler(priority = EventPriority.LOWEST)
	public void onJoin(PlayerLoginEvent event) {
		Player p = event.getPlayer();
		if (PluginMain.getInstance().getConfig().getBoolean("maintenance-stats")) {
			if (!p.hasPermission(PluginMain.getInstance().getConfig().getString("bypass-permission"))) {
				event.disallow(Result.KICK_OTHER,
						PluginMain.getInstance().getConfig().getString("maintenance-top").replace("&", "§") + "\n\n"
								+ PluginMain.getInstance().getConfig().getString("maintenance-message").replace("&",
										"§")
								+ "\n\n" + PluginMain.getInstance().getConfig().getString("maintenance-bottom")
										.replace("&", "§"));
			}
		}
	}

}
