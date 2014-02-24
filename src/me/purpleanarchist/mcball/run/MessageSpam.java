package me.purpleanarchist.mcball.run;

import me.purpleanarchist.mcball.arena.Arena;

import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

public class MessageSpam extends BukkitRunnable{

	private Arena arena;

	public MessageSpam(JavaPlugin plugin, Arena arena) {
		this.arena = arena;
	}

	@Override
	public void run() {
		if (arena.getPlayers().size() <2){
		arena.sendMessage(ChatColor.GREEN + "We need 1 more player till the game starts...");
		} else {
			this.cancel();
		}
	}
}
