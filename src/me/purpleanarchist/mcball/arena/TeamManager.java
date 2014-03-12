package me.purpleanarchist.mcball.arena;

import java.util.Random;

import me.purpleanarchist.mcball.Main;
import me.purpleanarchist.mcball.util.ConfigurationAPI;

import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

public class TeamManager {
	private static TeamManager tm;
	FileConfiguration fc = ConfigurationAPI.getConfig(Main.getPlugin(),
			"arenas.yml");

	public static TeamManager getManager() {
		return tm;
	}

	public Team getTeam(TeamColor color, String name) {
		for (Team team : Team.teamObjects) {
			if (team.getArena().equals(name)) {
				if (team.getColor() == color) {
					return team;
				}
			}
		}
		return null;
	}

	public void setTeam(Player p, String arena) {
		Team red = getTeam(TeamColor.RED, arena);
		Team blue = getTeam(TeamColor.BLUE, arena);
		if (red.getPlayers().size() == blue.getPlayers().size()) {
			Random rand = new Random();
			boolean b = rand.nextBoolean();
			if (b)
				red.getPlayers().add(p.getName());
			else
				blue.getPlayers().add(p.getName());
		} else if (red.getPlayers().size() < blue.getPlayers().size()) {
			red.getPlayers().add(p.getName());
		} else {
			blue.getPlayers().add(p.getName());
		}
	}

	public void setScore(Team team, int score) {
		team.setScore(score);
	}

	public void removePlayer(Player p, Team team) {
		team.getPlayers().remove(p.getName());
	}

	public void setBasket(Team team, String arenaName, Location basket) {
		if (team.getColor() == TeamColor.RED) {
			team.setBasket(basket);
			fc.set("arenas." + arenaName + "rbX", basket.getX());
			fc.set("arenas." + arenaName + "rbY", basket.getY());
			fc.set("arenas." + arenaName + "rbZ", basket.getZ());
		} else if (team.getColor() == TeamColor.BLUE) {
			team.setBasket(basket);
			fc.set("arenas." + arenaName + "bbX", basket.getX());
			fc.set("arenas." + arenaName + "bbY", basket.getY());
			fc.set("arenas." + arenaName + "bbZ", basket.getZ());
		}
	}
}