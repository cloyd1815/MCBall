package me.purpleanarchist.mcball.util;

import me.purpleanarchist.mcball.arena.Team;
import me.purpleanarchist.mcball.arena.TeamColor;
import me.purpleanarchist.mcball.arena.TeamManager;

import org.bukkit.Material;
import org.bukkit.entity.Chicken;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class Util {

	public static void sendMessage(Player player, String string) {
		player.sendMessage("[MCBall] " + string);

	}

	public static void checkEntity(Entity e, String arenaName) {
		Chicken entity = (Chicken) e;
		Team blue = TeamManager.getManager().getTeam(TeamColor.BLUE, arenaName);
		Team red = TeamManager.getManager().getTeam(TeamColor.RED, arenaName);
		int bs = blue.getScore();
		int rs = red.getScore();
		
		if (entity.isOnGround()) {
			if (entity.getLocation() == blue.getBasket()) {
				blue.setScore(bs + 1);
				entity.setHealth(0.0);
			} else if (entity.getLocation() == red.getBasket()) {
				red.setScore(rs + 1);
				entity.setHealth(0.0);
			} else {
				entity.setHealth(0.0);
				e.getWorld().dropItemNaturally(e.getLocation(), new ItemStack(Material.EGG));
			}

		}
	}
}
