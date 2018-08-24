package hu.Ricsi.BlockParty.GameManager;

import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitScheduler;

import hu.Ricsi.BlockParty.BlockParty;
import hu.Ricsi.BlockParty.FileConfiguration.Arena;
import hu.Ricsi.BlockParty.FileConfiguration.Lang;
import hu.Ricsi.BlockParty.FileConfiguration.Templates;
import hu.Ricsi.BlockParty.GameState.GameState;
import hu.Ricsi.BlockParty.GameState.State;
import hu.Ricsi.BlockParty.ScoreBoard.InGameScore;
import hu.Ricsi.BlockParty.ScoreBoard.ScoreBoard;

public class Timer {
	private static List<Player> InGame = BlockParty.getInstance().InGame;
	public static List<String> themes = BlockParty.getInstance().themes;
	static int i = Arena.getInt("arena.countdown_time");
	static int GameTime = Arena.getInt("arena.game_time");
	static int iii = 5;
	public static void tTimer() {
        BukkitScheduler scheduler = BlockParty.getInstance().getServer().getScheduler();
        scheduler.scheduleSyncRepeatingTask(BlockParty.getInstance(), new Runnable() {
            @Override
            public void run() {
            	GameState GS = State.getState();
//            	Bukkit.broadcastMessage(GS + "");
            	if(GS.equals(GameState.LOBBY)) {
            		
            		if(InGame.size() >= Arena.getInt("player.min")) {
        				State.setState(GameState.STARTING);
        				i = Arena.getInt("arena.countdown_time");
        			}
            		
            	}
            	else if(GS.equals(GameState.STARTING)) {
            		for(Player p : Bukkit.getOnlinePlayers()) {
            			p.sendMessage(Lang.getString("Arena.started_msg_format").replace("(i)", i + ""));
            		}
            		i--;
            		if(InGame.size() < Arena.getInt("player.min")) {
            			State.setState(GameState.LOBBY);
            		}
            		if(i <= 0) {
            			if(!themes.isEmpty()) {
        					themes.clear();
        				}
            			for(String key : Templates.getTemplates().getConfigurationSection("templates").getKeys(false)) {
            				themes.add(key);
            			}
            			State.setState(GameState.INGAME);
            			i = Arena.getInt("arena.countdown_time");
            			GameTime = Arena.getInt("arena.game_time");
            			InGameScore.setInGameScore();
            		}
            	}
            	else if(GS.equals(GameState.INGAME)) {
            		GameTime--;
            		ScoreBoard.updateGameScore();
            		if(GameTime <= 0) {
            			State.setState(GameState.STOPPING);
            		}
            	}
            	else if(GS.equals(GameState.STOPPING)) {
            		iii--;
            		SendServer.sendAllPlayer();
            		if(iii <= 0) {
            			Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "stop");
            		}
            	}
            	
            }
        }, 0L, 20);
	}
	
}
