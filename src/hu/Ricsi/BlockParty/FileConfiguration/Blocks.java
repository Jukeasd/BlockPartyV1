package hu.Ricsi.BlockParty.FileConfiguration;

import java.io.File;
import java.io.IOException;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import hu.Ricsi.BlockParty.BlockParty;

public class Blocks {

	private static	File Blocks_yml = new File(BlockParty.getInstance().getDataFolder(), "blocks.yml");       
	private static	FileConfiguration Blocks = YamlConfiguration.loadConfiguration(Blocks_yml);
	
	public static void reload() {
		Blocks_yml = new File(BlockParty.getInstance().getDataFolder(), "blocks.yml");   
		Blocks = YamlConfiguration.loadConfiguration(Blocks_yml);
	}
	
	public static void setBlocks(String s, Object o) {
		Blocks.set(s, o);
	}
	
	public static String getString(String s) {
		return Blocks.getString(s).replace("&", "§");
	} 
	
	public static int getInt(String i) {
		return Blocks.getInt(i);
	}
	
	public static double getDouble(String d) {
		return Blocks.getDouble(d);
	}
	
	public static FileConfiguration getLang() {
		return Blocks;
	}
	
	public static boolean getBoolean(String b) {
		return Blocks.getBoolean(b);
	}
	
	public static void saveBlocks() {
		
		Blocks.addDefault("Block.Feher.id", 159);
		Blocks.addDefault("Block.Feher.data", 0);
		Blocks.addDefault("Block.Feher.color", "&f");
		Blocks.addDefault("Block.Feher.name", "Fehér");
		
		Blocks.addDefault("Block.Narancssarga.id", 159);
		Blocks.addDefault("Block.Narancssarga.data", 1);
		Blocks.addDefault("Block.Narancssarga.color", "&6");
		Blocks.addDefault("Block.Narancssarga.name", "Narancssárga");
		
		Blocks.addDefault("Block.Magenta.id", 159);
		Blocks.addDefault("Block.Magenta.data", 2);
		Blocks.addDefault("Block.Magenta.color", "&5");
		Blocks.addDefault("Block.Magenta.name", "Magenta");
		
		Blocks.addDefault("Block.Vilagoskek.id", 159);
		Blocks.addDefault("Block.Vilagoskek.data", 3);
		Blocks.addDefault("Block.Vilagoskek.color", "&9");
		Blocks.addDefault("Block.Vilagoskek.name", "Világoskék");
		
		Blocks.addDefault("Block.Sarga.id", 159);
		Blocks.addDefault("Block.Sarga.data", 4);
		Blocks.addDefault("Block.Sarga.color", "&e");
		Blocks.addDefault("Block.Sarga.name", "Sárga");
		
		Blocks.addDefault("Block.Zold.id", 159);
		Blocks.addDefault("Block.Zold.data", 5);
		Blocks.addDefault("Block.Zold.color", "&a");
		Blocks.addDefault("Block.Zold.name", "Zöld");
		
		Blocks.addDefault("Block.Rozsaszin.id", 159);
		Blocks.addDefault("Block.Rozsaszin.data", 6);
		Blocks.addDefault("Block.Rozsaszin.color", "&d");
		Blocks.addDefault("Block.Rozsaszin.name", "Rózsaszín");
		
		Blocks.addDefault("Block.Sotetbarna.id", 159);
		Blocks.addDefault("Block.Sotetbarna.data", 7);
		Blocks.addDefault("Block.Sotetbarna.color", "&8");
		Blocks.addDefault("Block.Sotetbarna.name", "Sötétbarna");
		
		Blocks.addDefault("Block.Vilagosszurke.id", 159);
		Blocks.addDefault("Block.Vilagosszurke.data", 8);
		Blocks.addDefault("Block.Vilagosszurke.color", "&7");
		Blocks.addDefault("Block.Vilagosszurke.name", "Világos szürke");
		
		Blocks.addDefault("Block.Cian.id", 159);
		Blocks.addDefault("Block.Cian.data", 9);
		Blocks.addDefault("Block.Cian.color", "&7");
		Blocks.addDefault("Block.Cian.name", "Szürke");
		
		Blocks.addDefault("Block.Lila.id", 159);
		Blocks.addDefault("Block.Lila.data", 10);
		Blocks.addDefault("Block.Lila.color", "&5");
		Blocks.addDefault("Block.Lila.name", "Lila");
		
		Blocks.addDefault("Block.Kek.id", 159);
		Blocks.addDefault("Block.Kek.data", 11);
		Blocks.addDefault("Block.Kek.color", "&1");
		Blocks.addDefault("Block.Kek.name", "Kék");
		
		Blocks.addDefault("Block.Barna.id", 159);
		Blocks.addDefault("Block.Barna.data", 12);
		Blocks.addDefault("Block.Barna.color", "&8");
		Blocks.addDefault("Block.Barna.name", "Barna");
		
		Blocks.addDefault("Block.Sotetzold.id", 159);
		Blocks.addDefault("Block.Sotetzold.data", 13);
		Blocks.addDefault("Block.Sotetzold.color", "&2");
		Blocks.addDefault("Block.Sotetzold.name", "Sötétzöld");
		
		Blocks.addDefault("Block.Piros.id", 159);
		Blocks.addDefault("Block.Piros.data", 14);
		Blocks.addDefault("Block.Piros.color", "&c");
		Blocks.addDefault("Block.Piros.name", "Piros");
		
		Blocks.addDefault("Block.Fekete.id", 159);
		Blocks.addDefault("Block.Fekete.data", 15);
		Blocks.addDefault("Block.Fekete.color", "&0");
		Blocks.addDefault("Block.Fekete.name", "Fekete");
		
		Blocks.options().copyDefaults(true);
		
		try {
			Blocks.save(Blocks_yml);
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	}


	
	
	
}
