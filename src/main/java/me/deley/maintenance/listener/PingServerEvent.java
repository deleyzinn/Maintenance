package me.deley.maintenance.listener;

import java.util.ArrayList;

import org.bukkit.ChatColor;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.events.ListenerPriority;
import com.comphenix.protocol.events.PacketAdapter;
import com.comphenix.protocol.events.PacketContainer;
import com.comphenix.protocol.events.PacketEvent;
import com.comphenix.protocol.wrappers.WrappedServerPing;

import me.deley.maintenance.PluginMain;

public class PingServerEvent {
	private String header = "";
	private String maintenance = "";
	private String maintenanceName = "";

	public void inject() {
		ProtocolLibrary.getProtocolManager()
				.addPacketListener(new PacketAdapter(PluginMain.getInstance(), ListenerPriority.NORMAL, //
						PacketType.Status.Server.SERVER_INFO) {
					@Override
					public void onPacketSending(PacketEvent event) {
						PacketContainer container = event.getPacket();
						WrappedServerPing ping = container.getServerPings().read(0);
						ping.setPlayers(new ArrayList<>());
						if (getPlugin().getConfig().getBoolean("maintenance-stats")) {

							ping.setMotD(Chat
									.makeCenteredMotd(ChatColor.translateAlternateColorCodes('&',
											getPlugin().getConfig().getString("maintenance-motd.title")))
									+ "\n" + Chat.makeCenteredMotd(ChatColor.translateAlternateColorCodes('&',
											getPlugin().getConfig().getString("maintenance-motd.message"))));
							ping.setVersionProtocol(-1);
							ping.setVersionName("§cManutenção");
						}

					}
				});
	}

}
