package me.purpleanarchist.mcball.commands;

import me.purpleanarchist.mcball.Main;
import me.purpleanarchist.mcball.arena.Arena;
import me.purpleanarchist.mcball.arena.ArenaManager;
import me.purpleanarchist.mcball.arena.Team;
import me.purpleanarchist.mcball.arena.TeamColor;
import me.purpleanarchist.mcball.arena.TeamManager;
import me.purpleanarchist.mcball.util.Util;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginDescriptionFile;

public class Ball implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd,
			String commandLabel, String[] args) {
		Player p = (Player) sender;
		if (commandLabel.equalsIgnoreCase("ball")) {
			if (args.length == 0) {
				Util.sendMessage(p, "Use /ball help for help joining a game!");

			}
			if (args.length == 1) {
				if (args[0].equalsIgnoreCase("join")) {
					Util.sendMessage(p,
							"§4Use /ball help for help joining a game!");

				} else if (args[0].equalsIgnoreCase("leave")) {
					for (Arena a : Arena.arenaObjects) {
						if (a.getPlayers().contains(p.getName())) {
							ArenaManager.getManager().removePlayer(p,
									a.getName());
							Util.sendMessage(p, "You left the game!");
						}
					}

				} else if (args[0].equalsIgnoreCase("list")) {
					Util.sendMessage(p,
							"§aArena is not in game. §4Arena is in game.");
					for (Arena a : Arena.arenaObjects) {
						String list = a.getName();
						if (a.isInGame()) {
							p.sendMessage("§4" + list);
							p.sendMessage("§4Use /ball info " + list
									+ " - to see more info!");
							continue;
						} else {
							p.sendMessage("§a" + list);
							continue;
						}
					}

				} else if (args[0].equalsIgnoreCase("info")) {
					PluginDescriptionFile pdf = Main.getPlugin()
							.getDescription();
					String ver = pdf.getVersion();
					Util.sendMessage(p,
							"MC Ball plugin Developed by Sockmokey. Version: "
									+ ver);
				} else if (args[0].equalsIgnoreCase("help")) {
					Util.sendMessage(p, "=-=-=-=-= MC Ball =-=-=-=-=");
					p.sendMessage("/ball join");
					p.sendMessage("/ball leave");
					p.sendMessage("/leave");
					p.sendMessage("/ball info");
					p.sendMessage("/ball list");
					p.sendMessage("/ball help");
					p.sendMessage("/ball start");
					p.sendMessage("/ball end");
					p.sendMessage("/ball setstart");
					p.sendMessage("/ball setblue");
					p.sendMessage("/ball setred");
					p.sendMessage("/ball setend");

				} else if (args[0].equalsIgnoreCase("setend")) {
					ArenaManager.getManager().setEnd(p.getLocation());
					Util.sendMessage(p, "set the end to MC Ball ");

				} else {
					Util.sendMessage(p,
							"§4Use /ball help for help joining a game!");
				}
			}
			if (args.length == 2) {
				if (args[0].equalsIgnoreCase("info")) {
					for (Arena a : Arena.arenaObjects) {
						if (args[1].equalsIgnoreCase(a.getName())) {
							// output info on arena
							Util.sendMessage(
									p,
									ChatColor.GREEN
											+ "==== Info for the MC Ball: "
											+ a.getName() + " ====");
							Util.sendMessage(
									p,
									ChatColor.GREEN + "Players: "
											+ a.getPlayers().size() + "/"
											+ a.getMaxPlayers());
							Util.sendMessage(p, ChatColor.GREEN + "Status:");
							Util.sendMessage(p, ChatColor.GREEN + "is full: "
									+ a.isFull());
							Util.sendMessage(p, ChatColor.GREEN
									+ "is in game: " + a.isInGame());
							Util.sendMessage(p, "Use /ball list " + a.getName()
									+ " - to get the players in the MC Ball!");

						}
					}
				} else if (args[0].equalsIgnoreCase("join")) {
					ArenaManager.getManager().addPlayers(p, args[1]);

				} else if (args[0].equalsIgnoreCase("list")) {
					Util.sendMessage(p, "Players in MC Ball "
							+ ArenaManager.getManager().getArena(args[1])
									.getName() + ":");
					for (String s : ArenaManager.getManager().getArena(args[1])
							.getPlayers()) {
						p.sendMessage("§a"
								+ s
								+ ":§b "
								+ Main.obj.getScore(Bukkit.getPlayer(s))
										.getScore()); // get score
					}

				} else if (args[0].equalsIgnoreCase("start")) {
					ArenaManager.getManager().startArena(args[1]);
					Util.sendMessage(p, "Starting MC Ball.");

				} else if (args[0].equalsIgnoreCase("end")) {
					ArenaManager.getManager().endArena(args[1]);
					Util.sendMessage(p, "Ending MC Ball.");

				} else if (args[0].equalsIgnoreCase("remove")) {
					ArenaManager.getManager().removeArena(p, args[1]);
					Util.sendMessage(p, "Removed that MC Ball.");

				} else if (args[0].equalsIgnoreCase("setstart")) {
					ArenaManager.getManager()
							.setStart(args[1], p.getLocation());
					Util.sendMessage(p, "set the start to MC Ball " + args[1]);

				} else if (args[0].equalsIgnoreCase("create")) {
					ArenaManager.getManager().createArena(args[1], 10);
					Util.sendMessage(p, "MC Ball created.");

				} else if (args[0].equalsIgnoreCase("redbasket")) {
					for (Arena arena : Arena.arenaObjects) {
						if (args[1].equalsIgnoreCase(arena.getName())) {
							Team red = TeamManager.getManager().getTeam(
									TeamColor.RED, arena.getName());
							TeamManager.getManager().setBasket(red,
									arena.getName(), p.getLocation());
							Util.sendMessage(p, "Saved that locaion!");
						}
					}
				} else if (args[0].equalsIgnoreCase("bluebasket")) {
					for (Arena arena : Arena.arenaObjects) {
						if (args[1].equalsIgnoreCase(arena.getName())) {
							Team blue = TeamManager.getManager().getTeam(
									TeamColor.BLUE, arena.getName());
							TeamManager.getManager().setBasket(blue,
									arena.getName(), p.getLocation());
							Util.sendMessage(p, "Saved that locaion!");
						}
					}
				} else {
					Util.sendMessage(p,
							"§4Use /ball help for help joining a game!");
				}
			}
		}
		return false;
	}
}
