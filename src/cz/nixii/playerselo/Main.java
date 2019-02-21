package cz.nixii.playerselo;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;

import ru.tehkode.permissions.bukkit.PermissionsEx;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

@SuppressWarnings("unused")
public class Main extends JavaPlugin implements Listener{
	
	public static JavaPlugin inst;
	
	private Connection connection;
	
	String message;
	
	Integer ELO;
	
	public void onEnable() {
		inst = this;
		getConfig().options().copyDefaults(true);
		saveConfig();
		
		SetMessagesYAML_EN msgEN = new SetMessagesYAML_EN();
		SetMessagesYAML_CZ msgCZ = new SetMessagesYAML_CZ();
		
		SetListYAML list = new SetListYAML();
		
		SetBlackListYAML blYAML = new SetBlackListYAML();
		
    	Bukkit.getServer().getLogger().info("[PlayersELO] just turned on!");
    	
    	getServer().getPluginManager().registerEvents(this, this);
    	
    	FileConfiguration config = this.getConfig();
    	
    	if(config.getBoolean("MySQL.use")) {
    		
    		MySQL sql = new MySQL();
    		
    	}
    	
    	this.getCommand("pelo").setExecutor(new PELO());
    	
	}

	public void onDisable() {
		
    	Bukkit.getServer().getLogger().info("[PlayersELO] just turned off!");
    	
    	try {
    		if (connection!=null && !connection.isClosed()){
            	
                connection.close();
                
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
    	
	}
	
	@EventHandler
	public void onChat(AsyncPlayerChatEvent event) {
		
		FileConfiguration config = Main.inst.getConfig();
		
		checkVulgarism(event);
		
		setFormat(event);
		
	}
	
	@EventHandler
	public void onKill(PlayerDeathEvent event){
		
		FileConfiguration config = Main.inst.getConfig();
		
		File msg = new File(Main.inst.getDataFolder() + "/Messages/messages_" + config.getString("language") + ".yml");
		FileConfiguration msgfc = YamlConfiguration.loadConfiguration(msg);
		
		File list = new File(Main.inst.getDataFolder() + "/list.yml");
		FileConfiguration listfc = YamlConfiguration.loadConfiguration(list);
		
		if(config.getBoolean("killEvent.use") == true) {
			
			Player killed = Bukkit.getPlayer(event.getEntity().getName());
			Player killer = Bukkit.getPlayer(event.getEntity().getKiller().getName());
			
			ELO = config.getInt("killEvent.killer");
			message = config.getString("prefix") + msgfc.getString("Messages.kill");
			message = message.replaceAll("%USER", killed.getName());
			message = message.replaceAll("%ELO", ELO.toString());
			message = message.replaceAll("&", "§");
			killer.sendMessage(message);
			
			ELO = config.getInt("killEvent.killed");
			message = config.getString("prefix") + msgfc.getString("Messages.killed");
			message = message.replaceAll("%USER", killer.getName());
			message = message.replaceAll("%ELO", ELO.toString());
			message = message.replaceAll("&", "§");
			killed.sendMessage(message);
			
			ELO = listfc.getInt("List." + killer.getName() + "ELO");
			if(ELO == null) { ELO = 0; }
			ELO = ELO + config.getInt("killEvent.killer");
			listfc.set("List." + killer.getName() + ".ELO", ELO);
			
			ELO.equals(listfc.getInt("List." + killed.getName() + "ELO"));
			if(ELO == null) { ELO = 0; }
			ELO = ELO - config.getInt("killEvent.killed");
			listfc.set("List." + killed.getName() + ".ELO", ELO);
			
			try {
				listfc.save(list);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
	}
	
	@EventHandler
	public void onEntityDeath(EntityDeathEvent event){
		
		FileConfiguration config = Main.inst.getConfig();
		
		File msg = new File(Main.inst.getDataFolder() + "/Messages/messages_" + config.getString("language") + ".yml");
		FileConfiguration msgfc = YamlConfiguration.loadConfiguration(msg);
		
		File list = new File(Main.inst.getDataFolder() + "/list.yml");
		FileConfiguration listfc = YamlConfiguration.loadConfiguration(list);
		
		if(config.getBoolean("killMobEvent.use") == true) {
			
			if(event.getEntity().getKiller() instanceof Player) {
				
				Player p = event.getEntity().getKiller();
				
				ELO = listfc.getInt("List." + p.getName() + ".ELO");
				if(ELO == null) { ELO = 0; }
				ELO = ELO + config.getInt("killMobEvent.addPoints");
				
				listfc.set("List." + p.getName() + ".ELO", ELO);
				
				ELO = config.getInt("killMobEvent.addPoints");
				
				message = config.getString("prefix") + msgfc.getString("Messages.killEntity");
				message = message.replaceAll("%ELO", ELO.toString());
				message = message.replaceAll("&", "§");
				p.sendMessage(message);
				
				try {
					listfc.save(list);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
			
		}
		
	}
	
	public void setFormat(AsyncPlayerChatEvent event) {
		
		FileConfiguration config = Main.inst.getConfig();
		
		if(config.getBoolean("changeNicks.use")) {
			
			File list = new File(Main.inst.getDataFolder() + "/list.yml");
			FileConfiguration listfc = YamlConfiguration.loadConfiguration(list);
			
			ELO = listfc.getInt("List." + event.getPlayer().getName() + ".ELO");
			
			message = config.getString("changeNicks.format");
			
			if(PermissionsEx.getUser(event.getPlayer().getName()).getPrefix() == "") {

				message = message.replace(" %PREFIX%", "%PREFIX%");
				message = message.replace("%PREFIX% ", "%PREFIX%");
				message = message.replace("%PREFIX%", "");
				
			}
			
			if(PermissionsEx.getUser(event.getPlayer().getName()).getSuffix() == "") {

				message = message.replace(" %SUFFIX%", "%SUFFIX%");
				message = message.replace("%SUFFIX% ", "%SUFFIX%");
				message = message.replace("%SUFFIX%", "");
				
			}
			
			message = message.replace("%ELO%", config.getString("changeNicks.value"));
			message = message.replace("%PTS%", ELO.toString());
			message = message.replace("%PREFIX%", PermissionsEx.getUser(event.getPlayer()).getPrefix());
			message = message.replace("%SUFFIX%", PermissionsEx.getUser(event.getPlayer()).getSuffix());
			message = message.replace("%NICK%", event.getPlayer().getName());
			message = message.replaceAll("&", "§");
			message = message.replace("%MSG%", event.getMessage());
			
			if(event.getPlayer().hasPermission("pelo.colorchat")) { message = message.replaceAll("&", "§"); }
			
			event.setFormat(message);
			
		}
		
	}
	
	public void checkVulgarism(AsyncPlayerChatEvent event) {
		
		FileConfiguration config = Main.inst.getConfig();
		
		if(config.getBoolean("checkVulgarism.use")) {
			
			File blacklist = new File(Main.inst.getDataFolder() + "/blacklist.yml");
			FileConfiguration blfc = YamlConfiguration.loadConfiguration(blacklist);
			
			File msg = new File(Main.inst.getDataFolder() + "/Messages/messages_" + config.getString("language") + ".yml");
			FileConfiguration msgfc = YamlConfiguration.loadConfiguration(msg);
			
			File list = new File(Main.inst.getDataFolder() + "/list.yml");
			FileConfiguration listfc = YamlConfiguration.loadConfiguration(list);
			
			for(String bl : blfc.getStringList("List")) {
				
				if(event.getMessage().contains(bl)) {
					
					ELO = config.getInt("checkVulgarism.toMinus");
					
					message = config.getString("prefix") + msgfc.getString("Messages.vulgarism");
					message = message.replaceAll("%ELO", ELO.toString());
					message = message.replaceAll("&", "§");
					event.getPlayer().sendMessage(message);
					
					if(config.getBoolean("checkVulgarism.deleteMessages")) {
						
						event.setCancelled(true);
						
					}
					
					if(config.getBoolean("checkVulgarism.replaceMessages")) {
						
						Bukkit.broadcastMessage("Iam here");
						message = event.getMessage();
						message = message.replace(bl, config.getString("checkVulgarism.censured"));
						message = message.replaceAll("&", "§");
						event.setMessage(message);
						event.setCancelled(false);
						
					}
					
					ELO = listfc.getInt("List." + event.getPlayer().getName() + ".ELO");
					ELO = ELO - config.getInt("checkVulgarism.toMinus");
					listfc.set("List." + event.getPlayer().getName() + ".ELO", ELO);
					
					try {
						listfc.save(list);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				}
				
	        }
			
		}
		
	}
	
}
