package me.purpleanarchist.mcball.listener;

import me.purpleanarchist.mcball.Main;
import me.purpleanarchist.mcball.arena.Arena;
import me.purpleanarchist.mcball.arena.Team;
import me.purpleanarchist.mcball.arena.TeamColor;
import me.purpleanarchist.mcball.arena.TeamManager;
import me.purpleanarchist.mcball.run.EntityCheck;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Chicken;
import org.bukkit.entity.Egg;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.ProjectileLaunchEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerItemHeldEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.util.Vector;

public class PlayerListener implements Listener {
	private Plugin plugin = Main.getPlugin();
	
	@EventHandler(priority = EventPriority.HIGH)
	public void onEggThrow(PlayerInteractEvent e) {
		if (e.getItem() != null) {
			if (e.getItem().getType() == Material.STICK) {
				Player player = e.getPlayer();
				for (Arena arena : Arena.arenaObjects) {
					if (arena.getPlayers().contains(player.getName())) {
						if (e.getAction().equals(Action.RIGHT_CLICK_BLOCK)
								|| e.getAction().equals(Action.RIGHT_CLICK_AIR)) {
							double x = player.getLocation().getX() + 1;
							double y = player.getLocation().getY() + 1;
							double z = player.getLocation().getZ() + 1;
							Vector vector = player.getLocation().getDirection()
									.multiply(1.5);
							Location spawnLoc = new Location(player.getWorld(),
									x, y, z);
							Chicken c = player.getWorld().spawn(spawnLoc,
									Chicken.class);
							c.setVelocity(vector);
							new EntityCheck(plugin, c, arena.getName()).runTaskTimer(plugin, 0, 10);
						}
					}
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
						player.getInventory().getItemInHand()
								.setType(Material.AIR);
						e.setCancelled(true);
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
				Team red = TeamManager.getManager().getTeam(TeamColor.RED, arena.getName());
				Team blue = TeamManager.getManager().getTeam(TeamColor.BLUE, arena.getName());
				if (blue.getPlayers().contains(player)
						&& blue.getPlayers().contains(playere)) {
					if (player.getInventory().getItemInHand().getType() == Material.EGG) {
						// team pass ball
					}
				} else if (red.getPlayers().contains(player)
						&& red.getPlayers().contains(playere)) {
					if (player.getInventory().getItemInHand().getType() == Material.EGG) {
						// team pass ball
					}
				} else {
					if (player.getInventory().getItemInHand().getType() == Material.EGG) {
						// steal ball
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
