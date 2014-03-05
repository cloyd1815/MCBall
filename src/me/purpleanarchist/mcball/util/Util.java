package me.purpleanarchist.mcball.util;

import me.purpleanarchist.mcball.arena.Arena;
import me.purpleanarchist.mcball.arena.Team;
import me.purpleanarchist.mcball.arena.TeamColor;
import me.purpleanarchist.mcball.arena.TeamManager;

import org.bukkit.entity.Chicken;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

public class Util {

	public static void sendMessage(Player player, String string) {
		player.sendMessage("[MCBall] " + string);

	}

	public static void checkEntity(Entity e) {
		Chicken entity = (Chicken) e;
		if (entity.isOnGround()) {
			for (Arena arena : Arena.arenaObjects) {
				if (entity.getLocation() == arena.getBlueBasket()) {
					Team blue = TeamManager.getManager()
							.getTeam(TeamColor.BLUE);
					int bs = blue.getScore();
					blue.setScore(bs + 1);
					entity.setHealth(0.0);
				} else if (entity.getLocation() == arena.getRedBasket()) {
					Team red = TeamManager.getManager().getTeam(TeamColor.RED);
					int rs = red.getScore();
					red.setScore(rs + 1);
					entity.setHealth(0.0);
				}
			}
		}
	}
}
