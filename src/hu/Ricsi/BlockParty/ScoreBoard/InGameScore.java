package hu.Ricsi.BlockParty.ScoreBoard;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Score;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

import hu.Ricsi.BlockParty.BlockParty;
import hu.Ricsi.BlockParty.FileConfiguration.Arena;

public class InGameScore {
	
	private static List<Player> InGame = BlockParty.getInstance().InGame;
    private static SimpleDateFormat style = new SimpleDateFormat("mm:ss");
	static long GameMilis = Arena.getInt("arena.game_time") * 1000l;
	
	
	  public static void setInGameScore() {
	        for (Player player : Bukkit.getOnlinePlayers()) {
	            Scoreboard sb = Bukkit.getScoreboardManager().getNewScoreboard();
	            Objective objective = sb.registerNewObjective("Test1", "dummy");
	            Team localTeam1 =  sb.registerNewTeam("countdown");
	            localTeam1.addEntry(ChatColor.GREEN.toString());
	            objective.setDisplayName("ingame-scoreboard-title");
	            objective.setDisplaySlot(DisplaySlot.SIDEBAR);
	            Score score = objective.getScore((Object)ChatColor.DARK_BLUE + "        ");
	            score.setScore(12);
	            Score score2 = objective.getScore("time");
	            score2.setScore(11);
	            Date dateg = new Date(GameMilis);
	            localTeam1.setPrefix("§c§l  " + style.format(dateg).replace(".", ":"));
	            objective.getScore(ChatColor.GREEN.toString()).setScore(10);
	            Score score3 = objective.getScore((Object)ChatColor.RED + "        ");
	            score3.setScore(9);
	            Score score4 = objective.getScore("asd");
	            score4.setScore(8);
	            Team localTeam2 = sb.registerNewTeam("live");
	            localTeam2.addEntry(ChatColor.RED.toString());
	            localTeam2.setPrefix("§a§l  " + InGame.size());
	            objective.getScore(ChatColor.RED.toString()).setScore(7);
	            Score score6 = objective.getScore((Object)ChatColor.DARK_RED + "          ");
	            score6.setScore(6);
	            Score score10 = objective.getScore("§7§m-------------------");
	            score10.setScore(5);
	            Score score11 = objective.getScore("play.SlimeCraft.hu");
	            score11.setScore(4);

	            
	            player.setScoreboard(sb);
	        }
	    }
	
	
}
