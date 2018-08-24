package hu.Ricsi.BlockParty.GameManager;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import hu.Ricsi.BlockParty.BlockParty;
import hu.Ricsi.BlockParty.FileConfiguration.Arena;
import hu.Ricsi.BlockParty.FileConfiguration.Lang;

public class ArenaManager {

	public static void joinSp(Player p) {
		p.setAllowFlight(true);
		p.setFlying(true);
		p.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, 86400, 3), false);
		p.sendMessage(Lang.getString("login.spectator"));
	}
	
	public static void Sp(Player p) {
		p.setAllowFlight(true);
		p.setFlying(true);
		p.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, 86400, 3), false);
		p.sendMessage(Lang.getString("Arena.fell_out"));
	}
	
	public static void joinMSG(Player p) {
		for(Player pp : Bukkit.getOnlinePlayers()) {
			pp.sendMessage(Lang.getString("Arena.join_game").replace("(player)", p.getName()).replace("(maxsize)", Arena.getInt("player.max") + "").replace("(size)", BlockParty.getInstance().InGame.size() + ""));
		}
	}
	
				
	public static void leaveMSG(Player p) {
		for(Player pp : Bukkit.getOnlinePlayers()) {
			pp.sendMessage(Lang.getString("Arena.leave_game").replace("(player)", p.getName()).replace("(maxsize)", Arena.getInt("player.max") + "").replace("(size)", BlockParty.getInstance().InGame.size() + ""));
		}
	}
}
