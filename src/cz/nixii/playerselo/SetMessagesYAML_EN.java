package cz.nixii.playerselo;

import java.io.File;
import java.io.IOException;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

public class SetMessagesYAML_EN {{
	
	File msg = new File(Main.inst.getDataFolder() + "/Messages/messages_EN.yml");
    if (!msg.exists()) {
      
        try {
          
            msg.createNewFile();
          
        } catch (IOException ex) {
          
            System.out.println("ERROR: Failed to create messages.yml file!");
            ex.printStackTrace();
          
        }
      
        FileConfiguration msgfc = YamlConfiguration.loadConfiguration(msg);
      
        //Default values 
        msgfc.set("Messages.unknown_command", "Unknown command! Type: &6/pelo help");
        msgfc.set("Messages.have_elo", "You have &6%ELO &cELO!");
        msgfc.set("Messages.have_elo_other", "Player &6%PLAYER &chas &6%ELO &cELO.");
        msgfc.set("Messages.dont_access", "Unfortunately you haven't permission!\nPrikaz: &6%COMMAND \n&cPrava: &6%PERMISSION");
        msgfc.set("Messages.added_elo", "Player &6%PLAYER &cwas added &6%ELO&c.");
        msgfc.set("Messages.take_elo", "Admin &6%PLAYER &chas added &6%ELO &cELO.");
        msgfc.set("Messages.set_elo", "Player &6%PLAYER &cwas set &6%ELO &cELO.");
        msgfc.set("Messages.setTo_elo", "Admin &6%PLAYER &cwas set your &6%ELO &cELO.");
        msgfc.set("Messages.plus_elo_yourself", "You cannot give +REP yourself.");
        msgfc.set("Messages.minus_elo_yourself", "You cannot give -REP yourself.");
        msgfc.set("Messages.send_elo_plus", "You give +REP player &6%PLAYER.");
        msgfc.set("Messages.sendTo_elo_plus", "Player &6%PLAYER &cgive you +REP.");
        msgfc.set("Messages.send_elo_minus", "You give -REP player &6%PLAYER&c.");
        msgfc.set("Messages.sendTo_elo_minus", "Player &6%PLAYER &cgive you -REP.");
        msgfc.set("Messages.already_vote_plus", "You already give +REP in&6 %VOTE_TIME &cyou can give 1 +REP per hour.");
        msgfc.set("Messages.already_vote_minus", "You already give +REP in&6 %VOTE_TIME &cyou can give 1 -REP per hour.");
        msgfc.set("Messages.can_elo_vote", "Now you can give +REP");
        msgfc.set("Messages.reloaded", "Plugin was successfully reloaded.");
        msgfc.set("Messages.kill", "You kill &6%USER &cand you take &6%ELO &cELO.");
        msgfc.set("Messages.killed", "You dead by &6%USER &cand he steal your &6%ELO &cELO.");
        msgfc.set("Messages.reset", "You successfully reset your ELOs.");
        msgfc.set("Messages.resetTo", "You successfully reset &6%PLAYER &cELOs.");
        msgfc.set("Messages.resetBy", "Admin &6%PLAYER &creset your &6%ELO &cELOs.");
        msgfc.set("Messages.removeTo", "Player &6%PLAYER &cwas deducted &6%ELO &cELOs.");
        msgfc.set("Messages.removeBy", "Admin &6%PLAYER &cwas deducted your &6%ELO &cELOs.");
        msgfc.set("Messages.hourReward", "You play here for hour we give you &6%ELO &cELO");
        msgfc.set("Messages.killEntity", "You kill mob and we give you &6%ELO &cELO.");
        msgfc.set("Messages.vulgarism", "You wrote a sprout or a word forbid. We ruin you &4%ELO &cELO.");
        
        msgfc.set("Messages.helpCMD", "&6/pelo help &c- view this menu");
        msgfc.set("Messages.plusCMD", "&6/pelo plus <user> &c- you give +REP (ELO)");
        msgfc.set("Messages.minusCMD", "&6/pelo minus <user> &c- you give -REP (ELO)");
        msgfc.set("Messages.checkCMD", "&6/pelo check &c- view your ELOs");
        msgfc.set("Messages.check_otherCMD", "&6/pelo check <user> &c- view players ELOs");
        msgfc.set("Messages.addCMD", "&6/pelo add <user> <value> &c- add someones ELOs");
        msgfc.set("Messages.removeCMD", "&6/pelo remove <user> <value> &c- remove someones ELOs");
        msgfc.set("Messages.setCMD", "&6/pelo set <user> <value> &c- set someones ELOs");
        msgfc.set("Messages.resetCMD", "&6/pelo reset &c- reset your ELOs");
        msgfc.set("Messages.reset_otherCMD", "&6/pelo reset <user> &c- reset someones ELOs");
        msgfc.set("Messages.reloadCMD", "&6/pelo reload &c- restart config settings");
        try {
			msgfc.save(msg);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
	
}}
