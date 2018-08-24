package hu.Ricsi.BlockParty.FileConfiguration;

import java.io.File;
import java.io.IOException;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import hu.Ricsi.BlockParty.BlockParty;

public class Lang {

	private static	File langhu = new File(BlockParty.getInstance().getDataFolder(), "lang_hu.yml");       
	private static	FileConfiguration lang = YamlConfiguration.loadConfiguration(langhu);
	static char join = '\u271a';
	static char vonal1 = '\u258e';
	static char vonal2 = '\u258f';
	static char leave = '\u2716';
	
	
	public static void reload() {
		langhu = new File(BlockParty.getInstance().getDataFolder(), "lang_hu.yml");   
		lang = YamlConfiguration.loadConfiguration(langhu);
	}
	
	public static String getString(String s) {
		return lang.getString(s).replace("&", "§");
	}
	public static int getInt(String i) {
		return lang.getInt(i);
	}
	public static double getDouble(String d) {
		return lang.getDouble(d);
	}
	
	public static FileConfiguration getLang() {
		return lang;
	}
	
	public static void DefaLang() {
			lang.addDefault("server_ping.LOBBY", "&8Várakozás..");
			lang.addDefault("server_ping.STARTING", "&7Indúl");
			lang.addDefault("server_ping.INGAME", "&aJátékban");
			lang.addDefault("server_ping.STOPPING", "&cÚjraindítás..");
			
			lang.addDefault("Vote.msg_header", "&8&m---------------------------------------------");
			lang.addDefault("Vote.msg_succes", "               &b" +'\u2726'+"&f&lSzavazatok" + "&b" +'\u2726'+"");
			lang.addDefault("Vote.msg_format", "(s)(theme)&8:" + " &e (votes)&7");
			lang.addDefault("Vote.msg_footer2", "&8&m---------------------------------------------");
			lang.addDefault("Vote.msg_invaild_song", "&cIsmeretlen zene");
			lang.addDefault("Vote.msg_invaild_cmd", "&7/szavaz &b<zene>");
			
			lang.addDefault("Vote.cooldown_msg", "Varj (time)");
			
			lang.addDefault("Vote.voted", "     §b"+ '\u2B1B' + " §3§l");
			lang.addDefault("Vote.!voted", "     §8"+ '\u2B1B' + " §7");
			
			lang.addDefault("Vote.gui_name", "a");
			
			
			

			lang.addDefault("Arena.full", "&cA játék már megtelt, így nem csatlakozhatsz!");
			lang.addDefault("Arena.started", "&cA játék már elindult, így nem csatlakozhatsz!");
			lang.addDefault("Arena.join_game", "&8" + vonal1 + " &bBlock&3Party &8" + vonal2 + "" + "&a " + join + "&e (player) &7jött táncolni! &8(" + "&b(size)&8/&b(maxsize)&8)");
			lang.addDefault("Arena.leave_game", "&8" + vonal1 + " &bBlock&3Party &8" + vonal2 + "" + "&c " + leave + "&e (player) &7elhagyta a játékot! &8(" + "&b(size)&8/&b(maxsize)&8)");
			lang.addDefault("Arena.started_msg_format", "&fIndul (i) másodperc múlva");
			
			lang.addDefault("login.spectator", "Beléptel megfigyelõnek");
			
			
			
			lang.addDefault("reload_conf_lang", "&eConfig &7és &elang&7 file újratöltve");
			lang.addDefault("no_perm", "&cNincs jogod");
			
		try {
			lang.options().copyDefaults(true);
			lang.save(langhu);
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	}
	

}
