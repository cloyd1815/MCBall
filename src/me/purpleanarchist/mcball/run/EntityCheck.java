package me.purpleanarchist.mcball.run;

import me.purpleanarchist.mcball.util.Util;

import org.bukkit.entity.Entity;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

public class EntityCheck extends BukkitRunnable {
	private Entity entity;
	
	public EntityCheck(Plugin plugin, Entity entity) {
		this.entity = entity;
	}
	
	@Override
	public void run() {
		Util.checkEntity(entity);
	}

}
