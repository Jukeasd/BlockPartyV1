package hu.Ricsi.BlockParty.FileConfiguration;

import java.io.File;

import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import com.google.common.collect.Lists;

import hu.Ricsi.BlockParty.BlockParty;

public class Config {

	private static FileConfiguration conf = BlockParty.getInstance().getConfig();
	private static	File conf1;  
	public static void reload() {
		conf1 = new File(BlockParty.getInstance().getDataFolder(), "config.yml"); 
		conf = YamlConfiguration.loadConfiguration(conf1);
	}
	
	public static String getString(String s) {
		return conf.getString(s).replace("&", "§");
	}
	public static int getInt(String i) {
		return conf.getInt(i);
	}
	public static double getDouble(String d) {
		return conf.getDouble(d);
	}
	
	public static FileConfiguration getConfig() {
		return conf;
	}
	
	public static ConfigurationSection getSelection(String s) {
		return conf.getConfigurationSection(s);
	}
	
	public static Boolean getBoolean(String b) {
		return conf.getBoolean(b);
	}
	
	public static void DefaConfig() {
		
		
		
		//server ping
	//	conf.addDefault("", "");
		conf.addDefault("lobby_server", "Hub");
		
		//sogn themes
		conf.addDefault("vote.theme.1.loc", "Spooky_Scary_Skeletons");
		conf.addDefault("vote.theme.1.name", "Spooky_Scary_Skeletons");
		conf.addDefault("vote.theme.1.item_id", 2259);
		conf.addDefault("vote.theme.1.meta", 0);
		conf.addDefault("vote.theme.1.slot", 0);
		conf.addDefault("vote.theme.1.lore", Lists.newArrayList("&7Votes: (votes)"));
		
		conf.addDefault("vote.theme.2.loc", "Shrek_Theme");
		conf.addDefault("vote.theme.2.name", "Shrek_Theme");
		conf.addDefault("vote.theme.2.item_id", 2260);
		conf.addDefault("vote.theme.2.meta", 0);
		conf.addDefault("vote.theme.2.slot", 1);
		conf.addDefault("vote.theme.2.lore", Lists.newArrayList("&7Votes: (votes)"));
		
		conf.addDefault("vote.theme.3.loc", "Never_Gonna_Give_You_Up");
		conf.addDefault("vote.theme.3.name", "Never_Gonna_Give_You_Up");
		conf.addDefault("vote.theme.3.item_id", 2261);
		conf.addDefault("vote.theme.3.meta", 0);
		conf.addDefault("vote.theme.3.slot", 2);
		conf.addDefault("vote.theme.3.lore", Lists.newArrayList("&7Votes: (votes)"));
		
		conf.addDefault("vote.theme.4.loc", "Call_Me_Maybe");
		conf.addDefault("vote.theme.4.name", "Call_Me_Maybe");
		conf.addDefault("vote.theme.4.item_id", 2262);
		conf.addDefault("vote.theme.4.meta", 0);
		conf.addDefault("vote.theme.4.slot", 3);
		conf.addDefault("vote.theme.4.lore", Lists.newArrayList("&7Votes: (votes)"));
		
		conf.addDefault("vote.theme.5.loc", "Someone_Like_You");
		conf.addDefault("vote.theme.5.name", "Someone_Like_You");
		conf.addDefault("vote.theme.5.item_id", 2263);
		conf.addDefault("vote.theme.5.meta", 0);
		conf.addDefault("vote.theme.5.slot", 4);
		conf.addDefault("vote.theme.5.lore", Lists.newArrayList("&7Votes: (votes)"));
		
		conf.addDefault("vote.theme.6.loc", "Pirate");
		conf.addDefault("vote.theme.6.name", "Pirate");
		conf.addDefault("vote.theme.6.item_id", 2264);
		conf.addDefault("vote.theme.6.meta", 0);
		conf.addDefault("vote.theme.6.slot", 5);
		conf.addDefault("vote.theme.6.lore", Lists.newArrayList("&7Votes: (votes)"));
		
		conf.addDefault("vote.theme.7.loc", "Faded");
		conf.addDefault("vote.theme.7.name", "Faded");
		conf.addDefault("vote.theme.7.item_id", 2265);
		conf.addDefault("vote.theme.7.meta", 0);
		conf.addDefault("vote.theme.7.slot", 6);
		conf.addDefault("vote.theme.7.lore", Lists.newArrayList("&7Votes: (votes)"));
		
		conf.addDefault("vote.theme.8.loc", "Shape_Of_You");
		conf.addDefault("vote.theme.8.name", "Shape_Of_You");
		conf.addDefault("vote.theme.8.item_id", 2266);
		conf.addDefault("vote.theme.8.meta", 0);
		conf.addDefault("vote.theme.8.slot", 7);
		conf.addDefault("vote.theme.8.lore", Lists.newArrayList("&7Votes: (votes)"));
		
		
		conf.addDefault("vote.item.name", "&3Vote");
		conf.addDefault("vote.item.item_id", 2266);
		conf.addDefault("vote.item.meta", 0);
		conf.addDefault("vote.item.slot", 3);
		
		conf.addDefault("vote.cooldown_second", 5);
		
		
		conf.options().copyDefaults(true);
		BlockParty.getInstance().saveConfig();
	}
}
