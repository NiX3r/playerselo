package cz.nixii.playerselo;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

public class SetBlackListYAML {{
	
	File msg = new File(Main.inst.getDataFolder() + "/blacklist.yml");
    if (!msg.exists()) {
      
        try {
          
            msg.createNewFile();
          
        } catch (IOException ex) {
          
            System.out.println("ERROR: Failed to create list.yml file!");
            ex.printStackTrace();
          
        }
      
        FileConfiguration msgfc = YamlConfiguration.loadConfiguration(msg);
        
        List<String> list = new ArrayList<String>();
        
        list.add("asshole");
        list.add("fuck");
        
        msgfc.set("List", list);
        
        try {
			msgfc.save(msg);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
	
}}
