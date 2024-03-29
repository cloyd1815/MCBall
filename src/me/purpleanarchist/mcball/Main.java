package me.purpleanarchist.mcball;

import me.purpleanarchist.mcball.arena.ArenaManager;
import me.purpleanarchist.mcball.commands.Ball;
import me.purpleanarchist.mcball.listener.PlayerListener;

import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;
import org.bukkit.scoreboard.Team;

public class Main extends JavaPlugin {
	private static Main plugin;
	public static Objective obj;
	public static Scoreboard board;
	public static ScoreboardManager manager;
	public static Team red;
	public static Team blue;
	
	@Override
	public void onEnable() {
		setPlugin(this);
		registerEvents(this, new PlayerListener());
		getCommand("ball").setExecutor(new Ball());
		manager = Bukkit.getScoreboardManager();
		board = manager.getNewScoreboard();
		obj = board.registerNewObjective("score", "dummy");
		red = board.registerNewTeam("Red");
		blue = board.registerNewTeam("Blue");
		ArenaManager.getManager().loadArenas();
	}
	
	@Override
	public void onDisable() {
		
	}
	
	public static void registerEvents(org.bukkit.plugin.Plugin plugin,
			Listener... listeners) {
		for (Listener listener : listeners) {
			Bukkit.getServer().getPluginManager()
					.registerEvents(listener, plugin);
		}
	}

	public static Main getPlugin() {
		return plugin;
	}

	public void setPlugin(Main plugin) {
		Main.plugin = plugin;
	}
}