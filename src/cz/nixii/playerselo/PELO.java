package cz.nixii.playerselo;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

public class PELO implements CommandExecutor{

	Integer ELO;
	
	String message;
	String command;
	String isVotedPlus = "List: ";
	String isVotedMinus = "List: ";
	
	Calendar cal;
	SimpleDateFormat sdf;
	
	FileConfiguration config = Main.inst.getConfig();
	
	File msg = new File(Main.inst.getDataFolder() + "/Messages/messages_" + config.getString("language") + ".yml");
	FileConfiguration msgfc = YamlConfiguration.loadConfiguration(msg);
	
	private Player player;
	
	@SuppressWarnings("unused")
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		// TODO Auto-generated method stub
		
		if(args.length == 0) {{
			
			message = config.getString("prefix") + msgfc.getString("Messages.unknown_command");
			message = message.replaceAll("&", "§");
			sender.sendMessage(message);
			
		}}
		
		else if(args[0].equals("help")) {	//Command: /pelo help
			
			if(args.length == 1) {
				
				if(sender.hasPermission("pelo.help")) {
					
					sender.sendMessage("§4§m========§4§l§m=====[§6§lPlayers ELO §e- by NiX3r§4§l§m]=====§4§m========");
					
					if(sender.hasPermission("pelo.help")) {
						message = msgfc.getString("Messages.helpCMD");
						message = message.replaceAll("&", "§");
						sender.sendMessage(message);
					}
					
					if(sender.hasPermission("pelo.plus")) {
						message = msgfc.getString("Messages.plusCMD");
						message = message.replaceAll("&", "§");
						sender.sendMessage(message);
					}
					
					if(sender.hasPermission("pelo.minus")) {
						message = msgfc.getString("Messages.minusCMD");
						message = message.replaceAll("&", "§");
						sender.sendMessage(message);
					}
					
					if(sender.hasPermission("pelo.check")) {
						message = msgfc.getString("Messages.checkCMD");
						message = message.replaceAll("&", "§");
						sender.sendMessage(message);
					}
					
					if(sender.hasPermission("pelo.check.other")) {
						message = msgfc.getString("Messages.check_otherCMD");
						message = message.replaceAll("&", "§");
						sender.sendMessage(message);
					}
					
					if(sender.hasPermission("pelo.add")) {
						message = msgfc.getString("Messages.addCMD");
						message = message.replaceAll("&", "§");
						sender.sendMessage(message);
					}
					
					if(sender.hasPermission("pelo.remove")) {
						message = msgfc.getString("Messages.removeCMD");
						message = message.replaceAll("&", "§");
						sender.sendMessage(message);
					}
					
					if(sender.hasPermission("pelo.set")) {
						message = msgfc.getString("Messages.setCMD");
						message = message.replaceAll("&", "§");
						sender.sendMessage(message);
					}
					
					if(sender.hasPermission("pelo.reset")) {
						message = msgfc.getString("Messages.resetCMD");
						message = message.replaceAll("&", "§");
						sender.sendMessage(message);
					}
					
					if(sender.hasPermission("pelo.reset.other")) {
						message = msgfc.getString("Messages.reset_otherCMD");
						message = message.replaceAll("&", "§");
						sender.sendMessage(message);
					}
					
					if(sender.hasPermission("pelo.reload")) {
						message = msgfc.getString("Messages.reloadCMD");
						message = message.replaceAll("&", "§");
						sender.sendMessage(message);
					}
					
				}
				else{
					
					message = config.getString("prefix") + msgfc.getString("Messages.dont_access");
					message = message.replaceAll("&", "§");
					message = message.replaceAll("%COMMAND", "/pelo " + args.toString());
					message = message.replaceAll("%PERMSSION", "pelo.help");
					sender.sendMessage(message);
					
				}
				
			}
			
			else {
				
				message = config.getString("prefix") + msgfc.getString("Messages.unknown_command");
				message = message.replaceAll("&", "§");
				sender.sendMessage(message);
				
			}
			
		}
		
		else if(args[0].equals("check")) {	//Command: /pelo check || /pelo check <user>
			
			File list = new File(Main.inst.getDataFolder() + "/list.yml");
			FileConfiguration listfc = YamlConfiguration.loadConfiguration(list);
			
			if(args.length == 1) {
				if(sender.hasPermission("pelo.check")) {
					ELO = listfc.getInt("List." + sender.getName() + ".ELO");
					
					message = config.getString("prefix") + msgfc.getString("Messages.have_elo");
					message = message.replaceAll("&", "§");
					message = message.replaceAll("%ELO", ELO.toString());
					sender.sendMessage(message);
				}
					
				else {
						
					message = config.getString("prefix") + msgfc.getString("Messages.dont_access");
					message = message.replaceAll("&", "§");
					message = message.replaceAll("%COMMAND", "/pelo " + args.toString());
					message = message.replaceAll("%PERMSSION", "pelo.check");
					sender.sendMessage(message);
							
				}
					
			}
			
			if(args.length == 2) {
				if(sender.hasPermission("pelo.check.other")) {
					ELO = listfc.getInt("List." + args[1] + ".ELO");
					
					message = config.getString("prefix") + msgfc.getString("Messages.have_elo_other");
					message = message.replaceAll("%PLAYER", args[1]);
					message = message.replaceAll("&", "§");
					message = message.replaceAll("%ELO", ELO.toString());
					sender.sendMessage(message);
				}
				else {
					
					message = config.getString("prefix") + msgfc.getString("Messages.dont_access");
					message = message.replaceAll("&", "§");
					message = message.replaceAll("%COMMAND", "/pelo " + args.toString());
					message = message.replaceAll("%PERMSSION", "pelo.check.other");
					sender.sendMessage(message);
					
				}
			}
		}
			
		else if(args[0].equals("add")) {	//Command: /pelo add <user> <value>
			
			File list = new File(Main.inst.getDataFolder() + "/list.yml");
			FileConfiguration listfc = YamlConfiguration.loadConfiguration(list);
			
			if(args.length == 3) {
				
				if(sender.hasPermission("pelo.add")) {
					
					ELO = listfc.getInt("List." + args[1] + ".ELO");
						
					ELO = ELO + Integer.parseInt(args[2]);
					
					listfc.set("List." + args[1] + ".ELO", ELO);
					
					message = config.getString("prefix") + msgfc.getString("Messages.added_elo");
					message = message.replaceAll("&", "§");
					message = message.replaceAll("%ELO", args[2]);
					message = message.replaceAll("%PLAYER", args[1]);
					sender.sendMessage(message);
						
					if(Bukkit.getPlayer(args[1]) != null) {
						player = Bukkit.getPlayer(args[1]);
						message = config.getString("prefix") + msgfc.getString("Messages.take_elo");
						message = message.replaceAll("%PLAYER", sender.getName());
						message = message.replaceAll("&", "§");
						message = message.replaceAll("%ELO", args[2]);
						player.sendMessage(message);
					}
						
					try {
						listfc.save(list);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				}
				else {
					
					message = config.getString("prefix") + msgfc.getString("Messages.dont_access");
					message = message.replaceAll("&", "§");
					message = message.replaceAll("%COMMAND", "/pelo " + args.toString());
					message = message.replaceAll("%PERMSSION", "pelo.add");
					sender.sendMessage(message);
					
				}
				
			}
			else {
				
				message = config.getString("prefix") + msgfc.getString("Messages.unknown_command");
				message = message.replaceAll("&", "§");
				sender.sendMessage(message);
				
			}
			
		}
		
		else if(args[0].equals("set")) {	//Command: /pelo set <user> <value>
			
			File list = new File(Main.inst.getDataFolder() + "/list.yml");
			FileConfiguration listfc = YamlConfiguration.loadConfiguration(list);
			
			if(args.length == 3) {
			
				if(sender.hasPermission("pelo.set")) {
				
					ELO = Integer.parseInt(args[2]);
					listfc.set("List." + args[1] + ".ELO", ELO);
				
					message = config.getString("prefix") + msgfc.getString("Messages.set_elo");
					message = message.replaceAll("&", "§");
					message = message.replaceAll("%ELO", args[2]);
					message = message.replaceAll("%PLAYER", args[1]);
					sender.sendMessage(message);
				
					if(Bukkit.getPlayer(args[1]) != null) {
						player = Bukkit.getPlayer(args[1]);
						message = config.getString("prefix") + msgfc.getString("Messages.setTo_elo");
						message = message.replaceAll("%PLAYER", sender.getName());
						message = message.replaceAll("&", "§");
						message = message.replaceAll("%ELO", args[2]);
						player.sendMessage(message);
					}
					
					try {
						listfc.save(list);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				
				}
				else {
					
					message = config.getString("prefix") + msgfc.getString("Messages.dont_access");
					message = message.replaceAll("&", "§");
					message = message.replaceAll("%COMMAND", "/pelo " + args.toString());
					message = message.replaceAll("%PERMSSION", "pelo.set");
					sender.sendMessage(message);
					
				}
				
			}
			else {
				
				message = config.getString("prefix") + msgfc.getString("Messages.unknown_command");
				message = message.replaceAll("&", "§");
				sender.sendMessage(message);
				
			}
			
		}
		
		else if(args[0].equals("plus")) {	//Command: /pelo plus <user>
			
			File list = new File(Main.inst.getDataFolder() + "/list.yml");
			FileConfiguration listfc = YamlConfiguration.loadConfiguration(list);
			
			if(args.length == 2) {
				
				if((sender.hasPermission("pelo.plus")) && ((sender.hasPermission("pelo.default")) || (sender.hasPermission("pelo.vip")))) {
					
					if(!(sender.getName().equals(args[1]))) {
						
						if(!(isVotedPlus.contains(sender.getName()))) {
							
							isVotedPlus = isVotedPlus + sender.getName() + " ";
							
							cal = Calendar.getInstance();
							sdf = new SimpleDateFormat("HH:mm:ss");
							
							ELO = listfc.getInt("List." + args[1] + ".ELO");
							
							if(sender.hasPermission("pelo.default")) {
								
								ELO = ELO + config.getInt("Permissions.default.plus");
								
							}
							else if(sender.hasPermission("pelo.vip")) {
								
								ELO = ELO + config.getInt("Permissions.vip.plus");
								
							}
							
							ELO = ELO + config.getInt("defaultPlusValueToSend");
							listfc.set("List." + args[1] + ".ELO", ELO);
							listfc.set("List." + sender.getName() + ".PlusVote", sdf.format(cal.getTime()).toString());
						
							message = config.getString("prefix") + msgfc.getString("Messages.send_elo_plus");
							message = message.replaceAll("&", "§");
							message = message.replaceAll("%PLAYER", args[1]);
							sender.sendMessage(message);
					
							if(Bukkit.getPlayer(args[1]) != null) {
								player = Bukkit.getPlayer(args[1]);
								message = config.getString("prefix") + msgfc.getString("Messages.sendTo_elo_plus");
								message = message.replaceAll("%PLAYER", sender.getName());
								message = message.replaceAll("&", "§");
								player.sendMessage(message);
							}
								
							Bukkit.getScheduler().scheduleSyncDelayedTask(Main.inst, () -> {
							
								isVotedPlus = isVotedPlus.replaceAll(" " + sender.getName() + " ", " ");
								message = config.getString("prefix") + msgfc.getString("Messages.can_elo_vote");
								message = message.replaceAll("&", "§");
								sender.sendMessage(message);
								
								listfc.set("List." + sender.getName() + ".PlusVote", "");
								
								try {
									listfc.save(list);
								} catch (IOException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
								
							
							},20L * config.getInt("timeToVote"));
							
							try {
								listfc.save(list);
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							
						}
						else {
							
							message = config.getString("prefix") + msgfc.getString("Messages.already_vote_plus");
							message = message.replaceAll("%VOTE_TIME", listfc.getString("List." + sender.getName() + ".PlusVote"));
							message = message.replaceAll("&", "§");
							command = listfc.getString("List." + sender.getName() + "PlusVote");
							sender.sendMessage(message);
							
						}
					}
					else {
						
						message = config.getString("prefix") + msgfc.getString("Messages.plus_elo_yourself");
						message = message.replaceAll("&", "§");
						sender.sendMessage(message);
						
					}
					
				}
				else {
					
					message = config.getString("prefix") + msgfc.getString("Messages.dont_access");
					message = message.replaceAll("&", "§");
					message = message.replaceAll("%COMMAND", "/pelo " + args.toString());
					message = message.replaceAll("%PERMSSION", "pelo.plus");
					sender.sendMessage(message);
					
				}
				
			}
			else {
				
				message = config.getString("prefix") + msgfc.getString("Messages.unknown_command");
				message = message.replaceAll("&", "§");
				sender.sendMessage(message);
				
			}
			
		}
		
		else if(args[0].equals("minus")) {	//Command: /pelo minus <user>
			
			File list = new File(Main.inst.getDataFolder() + "/list.yml");
			FileConfiguration listfc = YamlConfiguration.loadConfiguration(list);
			
			if(args.length == 2) {
				
				if((sender.hasPermission("pelo.plus")) && ((sender.hasPermission("pelo.default")) || (sender.hasPermission("pelo.vip")))) {
					
					if(!(sender.getName().equals(args[1]))) {
						
						if(!(isVotedPlus.contains(sender.getName()))) {
							
							isVotedPlus = isVotedPlus + sender.getName() + " ";
							
							cal = Calendar.getInstance();
							sdf = new SimpleDateFormat("HH:mm:ss");
							
							ELO = listfc.getInt("List." + args[1] + ".ELO");
							
							if(sender.hasPermission("pelo.default")) {
								
								ELO = ELO - config.getInt("Permissions.default.minus");
								
							}
							else if(sender.hasPermission("pelo.vip")) {
								
								ELO = ELO - config.getInt("Permissions.vip.minus");
								
							}
							
							ELO = ELO - config.getInt("defaultMinusValueToSend");
							listfc.set("List." + args[1] + ".ELO", ELO);
							listfc.set("List." + sender.getName() + ".MinusVote", sdf.format(cal.getTime()).toString());
						
							message = config.getString("prefix") + msgfc.getString("Messages.send_elo_minus");
							message = message.replaceAll("&", "§");
							message = message.replaceAll("%PLAYER", args[1]);
							sender.sendMessage(message);
					
							if(Bukkit.getPlayer(args[1]) != null) {
								player = Bukkit.getPlayer(args[1]);
								message = config.getString("prefix") + msgfc.getString("Messages.sendTo_elo_minus");
								message = message.replaceAll("%PLAYER", sender.getName());
								message = message.replaceAll("&", "§");
								player.sendMessage(message);
							}
								
							Bukkit.getScheduler().scheduleSyncDelayedTask(Main.inst, () -> {
							
								isVotedPlus = isVotedPlus.replaceAll(" " + sender.getName() + " ", " ");
								message = config.getString("prefix") + msgfc.getString("Messages.can_elo_vote");
								message = message.replaceAll("&", "§");
								sender.sendMessage(message);
								
								listfc.set("List." + sender.getName() + ".MinusVote", "");
								
								try {
									listfc.save(list);
								} catch (IOException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
								
							
							},20L * config.getInt("timeToVote"));
							
							try {
								listfc.save(list);
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							
						}
						else {
							
							message = config.getString("prefix") + msgfc.getString("Messages.already_vote_minus");
							message = message.replaceAll("%VOTE_TIME", listfc.getString("List." + sender.getName() + ".MinusVote"));
							message = message.replaceAll("&", "§");
							command = listfc.getString("List." + sender.getName() + "MinusVote");
							sender.sendMessage(message);
							
						}
					}
					else {
						
						message = config.getString("prefix") + msgfc.getString("Messages.plus_elo_yourself");
						message = message.replaceAll("&", "§");
						sender.sendMessage(message);
						
					}
					
				}
				else {
					
					message = config.getString("prefix") + msgfc.getString("Messages.dont_access");
					message = message.replaceAll("&", "§");
					message = message.replaceAll("%COMMAND", "/pelo " + args.toString());
					message = message.replaceAll("%PERMSSION", "pelo.minus");
					sender.sendMessage(message);
					
				}
				
			}
			else {
				
				message = config.getString("prefix") + msgfc.getString("Messages.unknown_command");
				message = message.replaceAll("&", "§");
				sender.sendMessage(message);
				
			}
			
		}
		
		else if(args[0].equals("reload")) {
			
			if(args.length == 1) {
			
				if(sender.hasPermission("pelo.reload")) {
				
					Main.inst.reloadConfig();
					Main.inst.saveConfig();
					if(Main.inst.getConfig() == null) { Main.inst.getConfig().options().copyDefaults(true); Main.inst.saveConfig(); }
					
					File blacklist = new File(Main.inst.getDataFolder() + "/blacklist.yml");
					FileConfiguration blfc = YamlConfiguration.loadConfiguration(blacklist);
					if(blacklist == null) { SetBlackListYAML blYAML = new SetBlackListYAML(); }
					
					File msgCZ = new File(Main.inst.getDataFolder() + "/Messages/messages_CZ.yml");
					FileConfiguration msgfcCZ = YamlConfiguration.loadConfiguration(msgCZ);
					if(msgCZ == null) { SetMessagesYAML_CZ CZmsgCZ = new SetMessagesYAML_CZ(); }
					
					File msgEN = new File(Main.inst.getDataFolder() + "/Messages/messages_EN.yml");
					FileConfiguration msgfcEN = YamlConfiguration.loadConfiguration(msgEN);
					if(msgEN == null) { SetMessagesYAML_EN ENmsgEN = new SetMessagesYAML_EN(); }
					
					File list = new File(Main.inst.getDataFolder() + "/list.yml");
					FileConfiguration listfc = YamlConfiguration.loadConfiguration(list);
					if(list == null) { SetListYAML listCR = new SetListYAML(); }
					
					try {
						blfc.save(blacklist);
						msgfcCZ.save(msgCZ);
						msgfcEN.save(msgEN);
						listfc.save(list);
					}catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					message = config.getString("prefix") + msgfc.getString("Messages.reloaded");
					message = message.replaceAll("&", "§");
					sender.sendMessage(message);
					
				}
				else {
				
					message = config.getString("prefix") + msgfc.getString("Messages.dont_access");
					message = message.replaceAll("&", "§");
					message = message.replaceAll("%COMMAND", "/pelo " + args.toString());
					message = message.replaceAll("%PERMSSION", "pelo.reload");
					sender.sendMessage(message);
				
				}
				
			}
			
		}
		
		else if(args[0].equals("reset")) {	//Command: /pelo reset || /pelo reset <nick>
			
			File list = new File(Main.inst.getDataFolder() + "/list.yml");
			FileConfiguration listfc = YamlConfiguration.loadConfiguration(list);
			
			if(args.length == 1) {	//	If its pelo reset
				
				if(sender.hasPermission("pelo.reset")) {
					
					listfc.set("List." + sender.getName() + ".ELO", 0);
					
					message = config.getString("prefix") + msgfc.getString("Messages.reset");
					message = message.replaceAll("&", "§");
					sender.sendMessage(message);
					
					try {
						listfc.save(list);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				}
				else {
					
					message = config.getString("prefix") + msgfc.getString("Messages.dont_access");
					message = message.replaceAll("&", "§");
					message = message.replaceAll("%COMMAND", "/pelo " + args.toString());
					message = message.replaceAll("%PERMSSION", "pelo.reset");
					sender.sendMessage(message);
					
				}
				
			}
			
			else if(args.length == 2) {	// If its pelo reset <nick>
				
				if(sender.hasPermission("pelo.reset.other")) {
					
					listfc.set("List." + args[1] + ".ELO", 0);
					
					message = config.getString("prefix") + msgfc.getString("Messages.resetTo");
					message = message.replaceAll("&", "§");
					message = message.replaceAll("%PLAYER", args[1]);
					sender.sendMessage(message);
					
					if(Bukkit.getPlayer(args[1]) != null) {
						Player p = Bukkit.getPlayer(args[1]);
						message = config.getString("prefix") + msgfc.getString("Messages.resetBy");
						message = message.replaceAll("&", "§");
						message = message.replaceAll("%PLAYER", sender.getName());
						p.sendMessage(message);
					}
					
					try {
						listfc.save(list);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				}
				else {
					
					message = config.getString("prefix") + msgfc.getString("Messages.dont_access");
					message = message.replaceAll("&", "§");
					message = message.replaceAll("%COMMAND", "/pelo " + args.toString());
					message = message.replaceAll("%PERMSSION", "pelo.reset.other");
					sender.sendMessage(message);
					
				}
				
			}
			
			else {
				
				message = config.getString("prefix") + msgfc.getString("Messages.unknown_command");
				message = message.replaceAll("&", "§");
				sender.sendMessage(message);
				
			}
			
		}
		
		else if(args[0].equals("remove")) {	//Command: /pelo remove <user> <value>
			
			File list = new File(Main.inst.getDataFolder() + "/list.yml");
			FileConfiguration listfc = YamlConfiguration.loadConfiguration(list);
			
			if(args.length == 3) {
				
				if(sender.hasPermission("pelo.remove")) {
					
					Integer toRemove = 0;
					
					ELO = listfc.getInt("List." + args[1] + ".ELO");
					toRemove = Integer.parseInt(args[2]);
					ELO = ELO - toRemove;
					
					listfc.set("List." + args[1] + ".ELO", ELO);
					
					message = config.getString("prefix") + msgfc.getString("Messages.removeTo");
					message = message.replaceAll("&", "§");
					message = message.replaceAll("%PLAYER", args[1]);
					message = message.replaceAll("%ELO", args[2]);
					sender.sendMessage(message);
					
					if(Bukkit.getPlayer(args[1]) != null) {
						Player p = Bukkit.getPlayer(args[1]);
						message = config.getString("prefix") + msgfc.getString("Messages.removeBy");
						message = message.replaceAll("&", "§");
						message = message.replaceAll("%PLAYER", sender.getName());
						message = message.replaceAll("%ELO", args[2]);
						p.sendMessage(message);
					}
					try {
						listfc.save(list);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				}
				else {
					
					message = config.getString("prefix") + msgfc.getString("Messages.dont_access");
					message = message.replaceAll("&", "§");
					message = message.replaceAll("%COMMAND", "/pelo " + args.toString());
					message = message.replaceAll("%PERMSSION", "pelo.remove");
					sender.sendMessage(message);
					
				}
				
			}
			else {
				
				message = config.getString("prefix") + msgfc.getString("Messages.unknown_command");
				message = message.replaceAll("&", "§");
				sender.sendMessage(message);
				
			}
			
		}
		
		else {
			
			message = config.getString("prefix") + msgfc.getString("Messages.unknown_command");
			message = message.replaceAll("&", "§");
			sender.sendMessage(message);
			
		}
		
		return false;
	}

}
