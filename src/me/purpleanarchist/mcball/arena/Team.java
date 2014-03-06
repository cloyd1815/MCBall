package me.purpleanarchist.mcball.arena;

import java.util.ArrayList;

import org.bukkit.Location;

public class Team {

	private TeamColor color;
	private Location spawn, basket;
	private int score;
	private String arena;

	private ArrayList<String> players = new ArrayList<String>();

	public static ArrayList<Team> teamObjects = new ArrayList<Team>();

	public Team(TeamColor color, Location spawn, Location basket, String arena) {
		this.color = color;
		this.basket = basket;
		this.spawn = spawn;
		this.arena = arena;

		teamObjects.add(this);
	}

	public TeamColor getColor() {
		return color;
	}

	public void setColor(TeamColor color) {
		this.color = color;
	}

	public Location getSpawn() {
		return spawn;
	}

	public void setSpawn(Location spawn) {
		this.spawn = spawn;
	}

	public Location getBasket() {
		return basket;
	}

	public void setBasket(Location basket) {
		this.basket = basket;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public ArrayList<String> getPlayers() {
		return players;
	}

	public String getArena() {
		return arena;
	}

	public void setArena(String arena) {
		this.arena = arena;
	}
}
