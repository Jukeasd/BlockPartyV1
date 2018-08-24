package hu.Ricsi.BlockParty.FileConfiguration;

import java.io.File;
import java.io.IOException;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import hu.Ricsi.BlockParty.BlockParty;

public class Templates {

	private static	File Templates_yml = new File(BlockParty.getInstance().getDataFolder(), "templates.yml");       
	private static	FileConfiguration Templates = YamlConfiguration.loadConfiguration(Templates_yml);
	
	public static void reload() {
		Templates_yml = new File(BlockParty.getInstance().getDataFolder(), "templates.yml");   
		Templates = YamlConfiguration.loadConfiguration(Templates_yml);
	}
	
	public static void setTemplates(String s, Object o) {
		Templates.set(s, o);
	}
	
	public static String getString(String s) {
		return Templates.getString(s).replace("&", "§");
	} 
	
	public static int getInt(String i) {
		return Templates.getInt(i);
	}
	
	public static double getDouble(String d) {
		return Templates.getDouble(d);
	}
	
	public static FileConfiguration getTemplates() {
		return Templates;
	}
	
	public static boolean getBoolean(String b) {
		return Templates.getBoolean(b);
	}
	
	public static void saveTemplates() {
		try {
			Templates.save(Templates_yml);
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	}


	
	
}
