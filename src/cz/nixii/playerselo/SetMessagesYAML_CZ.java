package cz.nixii.playerselo;

import java.io.File;
import java.io.IOException;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

public class SetMessagesYAML_CZ {{
	
	File msg = new File(Main.inst.getDataFolder() + "/Messages/messages_CZ.yml");
    if (!msg.exists()) {
      
        try {
          
            msg.createNewFile();
          
        } catch (IOException ex) {
          
            System.out.println("ERROR: Failed to create messages.yml file!");
            ex.printStackTrace();
          
        }
      
        FileConfiguration msgfc = YamlConfiguration.loadConfiguration(msg);
      
        //Default values 
        msgfc.set("Messages.unknown_command", "Neznamy prikaz! Napis: &6/pelo help");
        msgfc.set("Messages.have_elo", "Mas &6%ELO &cELO!");
        msgfc.set("Messages.have_elo_other", "Hrac &6%PLAYER &cma &6%ELO &cELO.");
        msgfc.set("Messages.dont_access", "Bohuzel nemas prava!\nPrikaz: &6%COMMAND \n&cPrava: &6%PERMISSION");
        msgfc.set("Messages.added_elo", "Hraci &6%PLAYER &cbylo pricteno &6%ELO&c.");
        msgfc.set("Messages.take_elo", "Admin &6%PLAYER &cti pridal &6%ELO &cELO.");
        msgfc.set("Messages.set_elo", "Hraci &6%PLAYER &cbylo nastaveno &6%ELO &cELO.");
        msgfc.set("Messages.setTo_elo", "Admin &6%PLAYER &cti nastavil &6%ELO &cELO.");
        msgfc.set("Messages.plus_elo_yourself", "Nemuzes sam sobe dat +rep.");
        msgfc.set("Messages.minus_elo_yourself", "Nemuzes sam sobe dat -rep.");
        msgfc.set("Messages.send_elo_plus", "Dal jsi hraci &6%PLAYER &c+rep.");
        msgfc.set("Messages.sendTo_elo_plus", "Hrac &6%PLAYER &cti dal +rep.");
        msgfc.set("Messages.send_elo_minus", "Dal jsi hraci &6%PLAYER &c-rep.");
        msgfc.set("Messages.sendTo_elo_minus", "Hrac &6%PLAYER &cti dal -rep.");
        msgfc.set("Messages.already_vote_plus", "Jiz jsi hlasoval +REP v&6 %VOTE_TIME &cmuzes hlasovat 1x za hodinu.");
        msgfc.set("Messages.already_vote_minus", "Jiz jsi hlasoval -REP v&6 %VOTE_TIME &cmuzes hlasovat 1x za hodinu.");
        msgfc.set("Messages.can_elo_vote", "Jiz muzes darovat +rep.");
        msgfc.set("Messages.reloaded", "Plugin byl uspesne restartovan.");
        msgfc.set("Messages.kill", "Zabil jsi &6%USER &ca dostal jsi &6%ELO &cELO.");
        msgfc.set("Messages.killed", "Zabil te &6%USER &ca ubral ti &6%ELO &cELO.");
        msgfc.set("Messages.reset", "Uspesne jsi vymazal sve ELO a zacinas od znova.");
        msgfc.set("Messages.resetTo", "Uspesne jsi vymazal hraci &6%PLAYER &cjeho ELO.");
        msgfc.set("Messages.resetBy", "Admin &6%PLAYER &cti vymazal tve ELO.");
        msgfc.set("Messages.removeTo", "Hraci &6%PLAYER &cbylo odecteno &6%ELO&c.");
        msgfc.set("Messages.removeBy", "Admin &6%PLAYER &cti odecetl &6%ELO&c.");
        msgfc.set("Messages.hourReward", "Hrajes na serveru jiz hodinu a proto ziskavas &6%ELO &cELO");
        msgfc.set("Messages.killEntity", "Zabil jsi moba a ziskavas &6%ELO &cELO.");
        msgfc.set("Messages.vulgarism", "Napsal jsi sproste nebo zakazane slovo. Ztrhavame ti &4%ELO &cELO.");
        
        msgfc.set("Messages.helpCMD", "&6/pelo help &c- zobrazi toto menu");
        msgfc.set("Messages.plusCMD", "&6/pelo plus <user> &c- posle hraci reputaci (+ELO)");
        msgfc.set("Messages.minusCMD", "&6/pelo minus <user> &c- posle hraci reputaci (-ELO)");
        msgfc.set("Messages.checkCMD", "&6/pelo check &c- ukaze hraci kolik ma ELO");
        msgfc.set("Messages.check_otherCMD", "&6/pelo check <user> &c- ukaze hraci kolik ma jiny hrac elo");
        msgfc.set("Messages.addCMD", "&6/pelo add <user> <value> &c- pricte nekomu ELO");
        msgfc.set("Messages.removeCMD", "&6/pelo remove <user> <value> &c- odecte nekomu ELO");
        msgfc.set("Messages.setCMD", "&6/pelo set <user> <value> &c- nastavi nekomu ELO");
        msgfc.set("Messages.resetCMD", "&6/pelo reset &c- vymaze dosavadni pocet ELO");
        msgfc.set("Messages.reset_otherCMD", "&6/pelo reset <user> &c- vymaze nekomu dosavadni pocet ELO");
        msgfc.set("Messages.reloadCMD", "&6/pelo reload &c- restartuje config pluginu");
        try {
			msgfc.save(msg);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
	
}}