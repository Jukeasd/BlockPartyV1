package hu.Ricsi.BlockParty.ScoreBoard;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import hu.Ricsi.BlockParty.BlockParty;
import hu.Ricsi.BlockParty.FileConfiguration.Arena;

public class ScoreBoard {
	private static List<Player> InGame = BlockParty.getInstance().InGame;
    private static SimpleDateFormat style = new SimpleDateFormat("mm:ss");
	static long GameMilis = Arena.getInt("arena.game_time") * 1000l;
	 
	public static void updateGameScore() {
		Date dateg = new Date(GameMilis -= 1000L);
		for(Player p : Bukkit.getOnlinePlayers()) {
			  p.getScoreboard().getTeam("live").setPrefix(String.valueOf("§a§l  " + InGame.size()));
	            p.getScoreboard().getTeam("countdown").setPrefix(String.valueOf("§c§l  " + style.format(dateg)).replace(".", ":"));
		}
	}
	
}
