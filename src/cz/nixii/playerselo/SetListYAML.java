package cz.nixii.playerselo;

import java.io.File;
import java.io.IOException;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

public class SetListYAML {{
	File msg = new File(Main.inst.getDataFolder() + "/list.yml");
    if (!msg.exists()) {
      
        try {
          
            msg.createNewFile();
          
        } catch (IOException ex) {
          
            System.out.println("ERROR: Failed to create list.yml file!");
            ex.printStackTrace();
          
        }
      
        FileConfiguration msgfc = YamlConfiguration.loadConfiguration(msg);
        
        msgfc.set("List.Tester.ELO", 200);
        msgfc.set("List.Tester.RP", 0);
        msgfc.set("List.Tester.PlusVote", "17:00:00");
        msgfc.set("List.Tester.MinusVote", "13:04:39");
        
        try {
			msgfc.save(msg);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
}}
