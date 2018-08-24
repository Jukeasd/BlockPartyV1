package hu.Ricsi.BlockParty.ScoreBoard;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Score;
import org.bukkit.scoreboard.Scoreboard;

public class LobbyScore {

    public static void lobby(Player player) {
        Scoreboard boardstart = Bukkit.getScoreboardManager().getNewScoreboard();
       
        Objective objective = boardstart.registerNewObjective("Test", "dummy");
        objective.setDisplayName("BlockParty");
        objective.setDisplaySlot(DisplaySlot.SIDEBAR);
        Score null0 = objective.getScore("");
        null0.setScore(10);
        Score score = objective.getScore("asdasdasdasasd");
        score.setScore(9);
        Score null1 = objective.getScore("§a§l 23423");
        null1.setScore(8);
        Score null02 = objective.getScore("  ");
        null02.setScore(7);
        Score score4 = objective.getScore("asdasdasd");
        score4.setScore(6);
        Score null4 = objective.getScore("§c§l  2");
        null4.setScore(5);
        Score null05 = objective.getScore("   ");
        null05.setScore(4);
        Score score5 = objective.getScore("a");
        score5.setScore(3);
        Score null5 = objective.getScore("§e§l  " + 1);
        null5.setScore(2);
       
        
        
        player.setScoreboard(boardstart);
    }
	
	
	
	
}
