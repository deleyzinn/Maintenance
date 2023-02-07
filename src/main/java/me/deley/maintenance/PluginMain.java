package me.deley.maintenance;

import java.io.File;

import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

import lombok.Getter;
import me.deley.maintenance.command.MaintenanceCMD;
import me.deley.maintenance.listener.PingServerEvent;
import me.deley.maintenance.listener.PlayerJoinListener;

@Getter
public class PluginMain extends JavaPlugin {
	@Getter
	private static PluginMain instance;

	@Override
	public void onLoad() {
		instance = this;
		saveDefaultConfig();
	}

	@Override
	public void onEnable() {
		new PingServerEvent().inject();
		getCommand("manutencao").setExecutor(new MaintenanceCMD());
		getServer().getPluginManager().registerEvents(new PlayerJoinListener(), this);
		getServer().getConsoleSender()
				.sendMessage(ChatColor.GREEN + "[Maintenance] O plugin foi iniciado com sucesso.");
		getServer().getConsoleSender().sendMessage(ChatColor.GREEN + "[Maintenance] Autor: deley#3205.");

	}

	@Override
	public void onDisable() {
		getServer().getConsoleSender()
				.sendMessage(ChatColor.RED + "[Maintenance] O plugin foi desativado com sucesso.");
		getServer().getConsoleSender().sendMessage(ChatColor.RED + "[Maintenance] Autor: deley#3205.");
	}

	@Override
	public File getFile() {
		return super.getFile();
	}
}
