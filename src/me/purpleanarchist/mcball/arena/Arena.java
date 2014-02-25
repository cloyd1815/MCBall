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
	private Location joinLocation, endLocation, blueBasket, redBasket, redSpawn, blueSpawn;

	private String name;
	private ArrayList<String> players = new ArrayList<String>();
	private ArrayList<String> red = new ArrayList<String>();
	private ArrayList<String> blue = new ArrayList<String>();
	
	private int maxPlayers;

	private boolean inGame = false;

	// Now for a Constructor:
	public Arena(String arenaName, Location startLocation,
			Location endLocation, Location redBasket, Location blueBasket, Location redSpawn, Location blueSpawn, int maxPlayers) {
		// Lets initalize it all:
		this.name = arenaName;
		this.blueSpawn = blueSpawn;
		this.redSpawn = redSpawn;
		this.joinLocation = startLocation;
		this.endLocation = endLocation;
		this.maxPlayers = maxPlayers;

		// Now lets add this object to the list of objects:
		arenaObjects.add(this);

	}

	public Location getJoinLocation() {
		return this.joinLocation;
	}

	public void setJoinLocation(Location startLocation) {
		this.joinLocation = startLocation;
	}

	public Location getEndLocation() {
		return this.endLocation;
	}

	public void setEndLocation(Location endLocation) {
		this.endLocation = endLocation;
	}

	public Location getRedSpawn() {
		return redSpawn;
	}

	public void setRedSpawn(Location redSpawn) {
		this.redSpawn = redSpawn;
	}

	public Location getBlueSpawn() {
		return blueSpawn;
	}

	public void setBlueSpawn(Location blueSpawn) {
		this.blueSpawn = blueSpawn;
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

	public ArrayList<String> getBlue() {
		return blue;
	}

	public ArrayList<String> getRed() {
		return red;
	}

	public Location getBlueBasket() {
		return blueBasket;
	}

	public void setBlueBasket(Location blueBasket) {
		this.blueBasket = blueBasket;
	}

	public Location getRedBasket() {
		return redBasket;
	}

	public void setRedBasket(Location redBasket) {
		this.redBasket = redBasket;
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
