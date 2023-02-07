package me.deley.maintenance.command;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import me.deley.maintenance.PluginMain;

public class MaintenanceCMD implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		FileConfiguration config = PluginMain.getInstance().getConfig();
		if (sender.hasPermission(config.getString("maintenance-permission"))) {
			if (args.length == 0) {
				sender.sendMessage("§aUtilize: /manutencao §b[on,off,kickplayers]");
				return false;
			} else {
				if (cmd.getName().equalsIgnoreCase("manutencao")) {
					if (args[0].equalsIgnoreCase("kickplayers")) {
						for (Player p : Bukkit.getOnlinePlayers()) {
							if (p.hasPermission("maintenance.bypass"))
								continue;
							p.kickPlayer(config.getString("maintenance-kick.top").replace("&", "§") + "\n\n"
									+ config.getString("maintenance-kick.message").replace("&", "§") + "\n\n"
									+ config.getString("maintenance-kick.bottom").replace("&", "§"));
							sender.sendMessage("§aVocê kickou todos os jogadores online.");
						}
					} else if (args[0].equalsIgnoreCase("on")) {
						if (!config.getBoolean("maintenance-stats")) {
							config.set("maintenance-stats", true);
							PluginMain.getInstance().saveConfig();
							sender.sendMessage("§aA manutenção agora está: §bhabilitada§a.");
						} else {
							sender.sendMessage("§cA manutenção já está habilitada");
						}
					} else if (args[0].equalsIgnoreCase("off")) {
						if (config.getBoolean("maintenance-stats")) {
							config.set("maintenance-stats", false);
							PluginMain.getInstance().saveConfig();
							sender.sendMessage("§aA manutenção agora está: §bdesabilitada§a.");
						} else {
							sender.sendMessage("§cA manutenção já está desabilitada.");
						}
					}
				}
			}
		} else {
			sender.sendMessage("§cVocê não tem permissão.");
		}
		return true;
	}

}
