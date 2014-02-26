package me.purpleanarchist.mcball.listener;

import me.purpleanarchist.mcball.arena.Arena;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Chicken;
import org.bukkit.entity.Egg;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.ProjectileLaunchEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerItemHeldEvent;
import org.bukkit.util.Vector;

public class PlayerListener implements Listener {

	@EventHandler(priority = EventPriority.HIGH)
	public void onEggThrow(PlayerInteractEvent e) {
		if (e.getItem().getType() == Material.EGG) {
			Player player = e.getPlayer();
			for (Arena arena : Arena.arenaObjects) {
				if (arena.getPlayers().contains(player.getName())) {
					double x = player.getLocation().getX() + 1;
					double y = player.getLocation().getY() + 1;
					double z = player.getLocation().getZ() + 1;
					Vector vector = player.getLocation().getDirection()
							.multiply(2);
					Location spawnLoc = new Location(player.getWorld(), x, y, z);
					Chicken c = player.getWorld()
							.spawn(spawnLoc, Chicken.class);
					c.setVelocity(vector);
					c.setHealth(0.0);
				}
			}
		}
	}

	@EventHandler(priority = EventPriority.HIGH)
	private void onThrow(ProjectileLaunchEvent e) {
		if (e.getEntity().getShooter() instanceof Player) {
			if (e.getEntity() instanceof Egg) {
				Player player = (Player) e.getEntity().getShooter();
				for (Arena arena : Arena.arenaObjects) {
					if (arena.getPlayers().contains(player.getName())) {
						e.setCancelled(true);
						player.getInventory().getItemInHand()
								.setType(Material.AIR);
					}
				}
			}
		}
	}
	
	@EventHandler
	public void onEnityDeath(EntityDeathEvent e) {
		e.getEntity().getLocation();
	}
	
	@EventHandler
	public void onPlayerClick(PlayerInteractEntityEvent e) {
		Player player = e.getPlayer();
		if (e.getRightClicked() instanceof Player) {
			Player playere = (Player) e.getRightClicked();
			for (Arena arena : Arena.arenaObjects) {
				if (arena.getBlue().contains(player) && arena.getBlue().contains(playere)) {
					if (player.getInventory().getItemInHand().getType() == Material.EGG) {
						//pass ball
					}
				} else if (arena.getRed().contains(player) && arena.getRed().contains(playere)) {
					if (player.getInventory().getItemInHand().getType() == Material.EGG) {
						//team pass ball
					}
				} else if (arena.getPlayers().contains(playere) && arena.getPlayers().contains(player)) {
					if (player.getInventory().getItemInHand().getType() == Material.EGG) {
						//steal ball
					}
				}
			}
		}
	}
	
	@EventHandler
	public void playerItemChange(PlayerItemHeldEvent e) {
		Player player = e.getPlayer();
		for (Arena arena : Arena.arenaObjects) {
			if (arena.getPlayers().contains(player)) {
				e.setCancelled(true);
			}
		}
	}
}
