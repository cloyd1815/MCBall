package me.purpleanarchist.mcball.commands;


import me.purpleanarchist.mcball.arena.Arena;
import me.purpleanarchist.mcball.arena.ArenaManager;
import me.purpleanarchist.mcball.util.Util;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Leave implements CommandExecutor {
	// TODO clean the messages up
	@Override
	public boolean onCommand(CommandSender sender, Command cmd,
			String commandLabel, String[] args) {
		Player p = (Player) sender;
		if (commandLabel.equalsIgnoreCase("leave")) {
			if (args.length == 0) {
				for (Arena a : Arena.arenaObjects) {
					if (a.getPlayers().contains(p.getName())) {
						ArenaManager.getManager().removePlayer(p, a.getName());
						Util.sendMessage(p, "You left the game!");
						return false;
					} else {
						Util.sendMessage(p, "You're not in a game!");
						return false;
					}
				}
				return false;
			} else {
				Util.sendMessage(p, "Use /ball help for help joining a game!");
			}
		}
		return false;
	}
}
