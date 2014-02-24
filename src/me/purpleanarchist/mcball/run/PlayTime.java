package me.purpleanarchist.mcball.run;

import me.purpleanarchist.mcball.Main;
import me.purpleanarchist.mcball.arena.Arena;
import me.purpleanarchist.mcball.arena.ArenaManager;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scoreboard.Score;

public class PlayTime extends BukkitRunnable {
	private final JavaPlugin plugin;
	int countdown;
	String arena;

	public PlayTime(JavaPlugin plugin, int countdown, String arena) {
		this.plugin = plugin;
		this.countdown = countdown;
		this.arena = arena;
	}

	@Override
	public void run() {
		Arena a = ArenaManager.getManager().getArena(arena);
		countdown--;
		a.setExpLvl(countdown);
		Score score = Main.obj.getScore(Bukkit.getOfflinePlayer(ChatColor.YELLOW + "Time Left:"));
		score.setScore(countdown);
		if (countdown == 0) {
			ArenaManager.getManager().endArena(arena);
			this.cancel();
			Main.board.resetScores(Bukkit.getOfflinePlayer("Time Remainaing:"));
		}
		if (a.getPlayers().size() < 2) {
			this.cancel();
		}
		if (!a.isInGame()) {
			this.cancel();
		}
		plugin.getServer();
	}
}
