package hu.Ricsi.BlockParty.FileConfiguration;

import java.io.File;
import java.io.IOException;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import hu.Ricsi.BlockParty.BlockParty;

public class Arena {

	private static	File arena_yml = new File(BlockParty.getInstance().getDataFolder(), "arena.yml");       
	private static	FileConfiguration arena = YamlConfiguration.loadConfiguration(arena_yml);
	
	public static void reload() {
		arena_yml = new File(BlockParty.getInstance().getDataFolder(), "arena.yml");   
		arena = YamlConfiguration.loadConfiguration(arena_yml);
	}
	
	public static void setArena(String s, Object o) {
		arena.set(s, o);
		saveArena();
	}
	
	public static String getString(String s) {
		return arena.getString(s).replace("&", "§");
	} 
	
	public static int getInt(String i) {
		return arena.getInt(i);
	}
	
	public static double getDouble(String d) {
		return arena.getDouble(d);
	}
	
	public static FileConfiguration getLang() {
		return arena;
	}
	
	public static boolean getBoolean(String b) {
		return arena.getBoolean(b);
	}
	
	public static void saveArena() {
	//	arena.addDefault("enabled", "false");
		arena.addDefault("player.max", 12);
		arena.addDefault("player.min", 4);
		arena.addDefault("player.join_full_perm", "blockparty.join.full");
		arena.addDefault("arena.game_time", 600);
		arena.addDefault("arena.countdown_time", 10);
		
		arena.options().copyDefaults(true);
			
		try {
		//	arena.options().copyDefaults(true);
			arena.save(arena_yml);
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	}
	

}
