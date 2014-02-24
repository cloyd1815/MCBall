package me.purpleanarchist.mcball.arena;

import java.util.ArrayList;

import me.purpleanarchist.mcball.Main;
import me.purpleanarchist.mcball.run.Countdown;
import me.purpleanarchist.mcball.run.MessageSpam;
import me.purpleanarchist.mcball.run.PlayTime;
import me.purpleanarchist.mcball.util.ConfigurationAPI;
import me.purpleanarchist.mcball.util.Util;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitTask;

public class ArenaManager {
	private static ArenaManager am = new ArenaManager();
	JavaPlugin plugin = Main.getPlugin();

	public static ArenaManager getManager() {
		return am;
	}

	public Arena getArena(String name) {
		for (Arena a : Arena.arenaObjects) {
			if (a.getName().equals(name)) {
				return a;
			}
		}
		return null;
	}

	public void addPlayers(Player player, String arenaName) {
		if (getArena(arenaName) != null) {
			Arena arena = getArena(arenaName);
			if (arena.getStartLocation() != null
					&& arena.getEndLocation() != null) {
				if (!arena.isFull()) {
					if (!arena.isInGame()) {
						if (!arena.getPlayers().contains(player.getName())) {
							player.setHealth(20.0);
							player.setFireTicks(0);
							player.setSaturation(1000000);
							player.setLevel(0);
							player.getInventory().clear();
							player.teleport(arena.getStartLocation());
							if (player.getGameMode() == GameMode.CREATIVE)
								player.setGameMode(GameMode.SURVIVAL);
							arena.getPlayers().add(player.getName());
							arena.sendMessage(ChatColor.GREEN
									+ player.getName()
									+ ChatColor.GREEN + " has joined the game!");
							if (arena.getPlayers().size() == 2) {
								@SuppressWarnings("unused")
								BukkitTask countdown = new Countdown(plugin, 60, arenaName).runTaskTimer(plugin, 0, 20);
							} else if (arena.getPlayers().size() < 2) {
								@SuppressWarnings("unused")
								BukkitTask message = new MessageSpam(plugin, arena).runTaskTimer(plugin, 0, 20*10);
							}
						} else {
							Util.sendMessage(player,
									"�4You are already in an arena!");
						}

					} else {
						player.sendMessage(ChatColor.RED
								+ "�4The arena you are looking for is currently running!");

					}
				} else {
					player.sendMessage(ChatColor.RED
							+ "�4The arena you are looking for is currently full! Donors can get acess to full arenas!");
				}
			} else {
				Util.sendMessage(player,
						"�4This arena was not setup right. Contact an admin.");
			}
		} else {
			player.sendMessage(ChatColor.RED
					+ "�4The arena you are looking for could not be found!");
		}
	}

	public void removePlayer(Player player, String arenaName) {
		if (getArena(arenaName) != null) {
			Arena arena = getArena(arenaName);
			if (arena.getPlayers().contains(player.getName())) {
				arena.getPlayers().remove(player.getName());
				player.teleport(arena.getEndLocation());
				if (arena.getPlayers().isEmpty())
					endArena(arenaName);
			} else {
				player.sendMessage(ChatColor.RED
						+ "Your not in the arena you're looking for!");
			}
		} else {
			player.sendMessage(ChatColor.RED
					+ "The arena you are looking for could not be found!");
		}
	}

	public void startArena(String arenaName) {
		if (getArena(arenaName) != null) {
			// declare arena, send message, set in game
			Arena arena = getArena(arenaName);
			arena.sendMessage(ChatColor.GOLD + "MC Ball has started!!");
			arena.setInGame(true);
			for (String s : arena.getPlayers()) {
				Player player = Bukkit.getPlayer(s);

				player.teleport(arena.getStartLocation());
			}
			@SuppressWarnings("unused")
			BukkitTask playtime = new PlayTime(plugin, 60*5, arenaName).runTaskTimer(plugin, 0, 20);
		}
	}

	public void endArena(String arenaName) {
		if (getArena(arenaName) != null) {
			Arena arena = getArena(arenaName);
			arena.sendMessage("�cMC Ball has ended");
			arena.setExpLvl(0);
			ArrayList<String> players = new ArrayList<String>(
					arena.getPlayers());
			for (String s : players) {
				Player player = Bukkit.getPlayer(s);

				player.getInventory().clear();
				player.teleport(arena.getEndLocation());
				arena.getPlayers().remove(s);
			}
			arena.setInGame(false);
		}
	}

	public void loadArenas() {
		FileConfiguration fc = ConfigurationAPI.getConfig(plugin, "arenas.yml");

		if (fc.getConfigurationSection("arenas") != null) {
			for (String keys : fc.getConfigurationSection("arenas").getKeys(
					false)) {
				World world = Bukkit.getWorld("world");
				double startX = fc.getDouble("arenas." + keys + ".startX");
				double startY = fc.getDouble("arenas." + keys + ".startY");
				double startZ = fc.getDouble("arenas." + keys + ".startZ");
				double endX = fc.getDouble("endX");
				double endY = fc.getDouble("endY");
				double endZ = fc.getDouble(".endZ");
				String team1 = fc.getString("arenas." + keys + ".team1");
				String team2 = fc.getString("arenas." + keys + ".team2");
				int maxPlayers = fc.getInt("arenas." + keys + ".maxPlayers");

				Location startLocation = new Location(world, startX, startY,
						startZ);
				Location endLocation = new Location(world, endX, endY, endZ);

				@SuppressWarnings("unused")
				Arena arena = new Arena(keys, startLocation, endLocation,
						maxPlayers, team1, team2);
			}
		}
	}

	public void createArena(String arenaName, int maxPlayers) {
		@SuppressWarnings("unused")
		Arena arena = new Arena(arenaName, null, null, maxPlayers, null, null);
		ConfigurationAPI.getConfig(plugin, "arenas.yml").set(
				"arenas." + arenaName, null);
		String path = "arenas." + arenaName + ".";
		ConfigurationAPI.getConfig(plugin, "arenas.yml").set(
				path + "maxPlayers", maxPlayers);

		ConfigurationAPI.saveConfig(plugin, "arenas.yml");
	}

	public void SetEnd(Location joinLocation) {
		FileConfiguration fc = ConfigurationAPI.getConfig(plugin, "arenas.yml");
		fc.set("endX", joinLocation.getX());
		fc.set("endY", joinLocation.getY());
		fc.set("endZ", joinLocation.getZ());
		ConfigurationAPI.saveConfig(plugin, "arenas.yml");
	}

	public void SetStart(String arenaName, Location blueLocation) {
		FileConfiguration fc = ConfigurationAPI.getConfig(plugin, "arenas.yml");
		String path = "arenas." + arenaName + ".";
		fc.set(path + "startX", blueLocation.getX());
		fc.set(path + "startY", blueLocation.getY());
		fc.set(path + "startZ", blueLocation.getZ());
		Arena arena = getArena(arenaName);
		arena.setStartLocation(blueLocation);
		ConfigurationAPI.saveConfig(plugin, "arenas.yml");
	}

	public void RemoveArena(Player player, String arenaName) {
		FileConfiguration fc = ConfigurationAPI.getConfig(plugin, "arenas.yml");
		String path = "arenas." + arenaName;
		fc.set(path, null);
		ConfigurationAPI.saveConfig(plugin, "arenas.yml");
		Util.sendMessage(player, "Arena removed.");
		Arena.arenaObjects.remove(getArena(arenaName));
	}
	
	public void setTeam(int teamNumber, String arenaName, String name) {
		FileConfiguration fc = ConfigurationAPI.getConfig(plugin, "arenas.yml");
		String path = "arenas." + arenaName;
		if (teamNumber == 1) {
			String newname = name.substring(0, 15);
			fc.set(path + ".team1", newname);
		} else if (teamNumber == 2) {
			String newname = name.substring(0, 15);
			fc.set(path + ".team2", newname);
		}
	}
}
