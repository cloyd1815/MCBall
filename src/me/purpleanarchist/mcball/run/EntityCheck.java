package me.purpleanarchist.mcball.run;

import me.purpleanarchist.mcball.util.Util;

import org.bukkit.entity.Entity;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

public class EntityCheck extends BukkitRunnable {
	private Entity entity;
	private String arenaName;
	
	public EntityCheck(Plugin plugin, Entity entity, String arenaName) {
		this.entity = entity;
		this.arenaName = arenaName;
	}
	
	@Override
	public void run() {
		Util.checkEntity(entity, arenaName);
		if (entity.isDead()) {
			this.cancel();
		}
	}

}
