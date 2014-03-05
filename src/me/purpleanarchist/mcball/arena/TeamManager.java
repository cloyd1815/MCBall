package me.purpleanarchist.mcball.arena;

import org.bukkit.entity.Player;

public class TeamManager {
	private static TeamManager tm;
	
	public static TeamManager getManager() {
		return tm;
	}
	
	public Team getTeam(TeamColor color) {
		for (Team team : Team.teamObjects) {
			if (team.getColor() == color) {
				return team;
			}
		}
		return null;
	}
	
	public void setTeam(Player p) {
		Team red = getTeam(TeamColor.RED);
		Team blue = getTeam(TeamColor.BLUE);
		if (red.getPlayers().size() == blue.getPlayers().size()) {
			//random
		} else if (red.getPlayers().size() < blue.getPlayers().size()) {
			red.getPlayers().add(p.getName());
		} else {
			blue.getPlayers().add(p.getName());
		}
	}
	
	public void setTeam(Player p, Team team) {
		team.getPlayers().add(p.getName());
	}
	
	public void setScore(Team team, int score) {
		team.setScore(score);
	}
	
	public void removePlayer(Player p, Team team) {
		team.getPlayers().remove(p.getName());
		
	}
}
