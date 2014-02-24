package me.purpleanarchist.mcball.run;

import me.purpleanarchist.mcball.arena.Arena;
import me.purpleanarchist.mcball.arena.ArenaManager;

import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

public class Countdown extends BukkitRunnable {
	private final JavaPlugin plugin;
	int countdown;
	String arena;

	public Countdown(JavaPlugin plugin, int countdown, String arena) {
		this.plugin = plugin;
		this.countdown = countdown;
		this.arena = arena;

	}

	@Override
	public void run() {
		Arena a = ArenaManager.getManager().getArena(arena);
		countdown--;
		a.setExpLvl(countdown);

		if (countdown == 60)
			a.sendMessage("§a60 Seconds until jump starts!");
		if (countdown == 50)
			a.sendMessage("§a50 Seconds until jump starts!");
		if (countdown == 40)
			a.sendMessage("§a40 Seconds until jump starts!");
		if (countdown == 30)
			a.sendMessage("§a30 Seconds until jump starts!");
		if (countdown == 20)
			a.sendMessage("§a20 Seconds until jump starts!");
		if (countdown == 10)
			a.sendMessage("§a10 Seconds until jump starts!");
		if (countdown == 5)
			a.sendMessage("§a5 Seconds until jump starts!");
		if (countdown == 4)
			a.sendMessage("§a4 Seconds until jump starts!");
		if (countdown == 3)
			a.sendMessage("§a3 Seconds until jump starts!");
		if (countdown == 2)
			a.sendMessage("§a2 Seconds until jump starts!");
		if (countdown == 1)
			a.sendMessage("§a1 Seconds until jump starts!");
		if (countdown == 0) {
			ArenaManager.getManager().startArena(arena);
			this.cancel();
		}
		if (a.getPlayers().size() < 2) {
			this.cancel();
		}
		plugin.getServer();
	}
}
