package me.purpleanarchist.mcball.arena;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.Location;

/**
 * 
 * @Author Jake
 */
public class Arena {
	public static ArrayList<Arena> arenaObjects = new ArrayList<Arena>();

	// Some fields we want each Arena object to store:
	private Location startLocation, endLocation;

	private String name, team1, team2;
	private ArrayList<String> players = new ArrayList<String>();

	private int maxPlayers;

	private boolean inGame = false;

	// Now for a Constructor:
	public Arena(String arenaName, Location startLocation,
			Location endLocation, int maxPlayers, String team1, String team2) {
		// Lets initalize it all:
		this.name = arenaName;
		this.startLocation = startLocation;
		this.endLocation = endLocation;
		this.maxPlayers = maxPlayers;
		this.team1 = team1;
		this.team2 = team2;

		// Now lets add this object to the list of objects:
		arenaObjects.add(this);

	}

	public Location getStartLocation() {
		return this.startLocation;
	}

	public void setStartLocation(Location startLocation) {
		this.startLocation = startLocation;
	}

	public Location getEndLocation() {
		return this.endLocation;
	}

	public void setEndLocation(Location endLocation) {
		this.endLocation = endLocation;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getMaxPlayers() {
		return this.maxPlayers;
	}

	public void setMaxPlayers(int maxPlayers) {
		this.maxPlayers = maxPlayers;
	}

	public ArrayList<String> getPlayers() {
		return this.players;
	}

	public String getTeam1() {
		return team1;
	}

	public void setTeam1(String team1) {
		this.team1 = team1;
	}

	public String getTeam2() {
		return team2;
	}

	public void setTeam2(String team2) {
		this.team2 = team2;
	}

	// And finally, some booleans:
	public boolean isFull() {
		if (players.size() >= maxPlayers) {
			return true;
		} else {
			return false;
		}
	}

	public boolean isInGame() {
		return inGame;
	}

	public void setInGame(boolean inGame) {
		this.inGame = inGame;
	}

	// To send each player in the arena a message
	public void sendMessage(String message) {
		for (String s : players) {
			Bukkit.getPlayer(s).sendMessage("§7[§cJump§7]: " + message);
		}
	}

	public void setExpLvl(int exp) {
		for (String s : players) {
			Bukkit.getPlayer(s).setLevel(exp);
		}
	}
}
