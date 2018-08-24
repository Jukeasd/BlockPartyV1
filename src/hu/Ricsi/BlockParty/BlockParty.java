package hu.Ricsi.BlockParty;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitScheduler;

import hu.Ricsi.BlockParty.Listener.CommandListener;
import hu.Ricsi.BlockParty.Listener.PingListener;
import hu.Ricsi.BlockParty.Listener.PlayerListener;
import hu.Ricsi.BlockParty.VoteSystem.VoteStatus;
import hu.Ricsi.BlockParty.Commands.Command;
import hu.Ricsi.BlockParty.Commands.gen;
import hu.Ricsi.BlockParty.Commands.gui;
import hu.Ricsi.BlockParty.Commands.leave;
import hu.Ricsi.BlockParty.Commands.removeCommand;
import hu.Ricsi.BlockParty.FileConfiguration.Arena;
import hu.Ricsi.BlockParty.FileConfiguration.Blocks;
import hu.Ricsi.BlockParty.FileConfiguration.Config;
import hu.Ricsi.BlockParty.FileConfiguration.Lang;
import hu.Ricsi.BlockParty.FileConfiguration.Templates;
import hu.Ricsi.BlockParty.GameManager.Timer;
import hu.Ricsi.BlockParty.GameState.GameState;
import hu.Ricsi.BlockParty.GameState.State;

public class BlockParty extends JavaPlugin{


	
	public Map<Player, String> votes = new HashMap<Player, String>();
	public Map<String, Integer> sz = new HashMap<String, Integer>();
	public Map<String, Integer> sz1 = new HashMap<String, Integer>();
	public Map<String, Command> Commands = new HashMap<String, Command>();
	public List<Player> cd = new ArrayList<Player>();
	public List<Player> InGame = new ArrayList<Player>();  
	public List<Player> kieset = new ArrayList<Player>();  
	public List<Player> Spectator = new ArrayList<Player>();  
	public List<String> themes = new ArrayList<String>();
	public List<String> blocks = new ArrayList<String>();
	
    private static BlockParty instance;
    
    public static BlockParty getInstance() {
        return instance; 
    }
    
//    public hu.Ricsi.BlockParty.FileConfiguration.Lang Lang() {
//    	return Lang();
//    }
//    
//    public hu.Ricsi.BlockParty.FileConfiguration.Config Config() {
//    	return hu.Ricsi.BlockParty.FileConfiguration.Config.class;
//    }
	
	public void onEnable() {
		instance = this;
		
		Bukkit.getMessenger().registerOutgoingPluginChannel(this, "BungeeCord");
		
		PluginManager pm = getServer().getPluginManager();
		pm.registerEvents(new CommandListener(), this);
		pm.registerEvents(new PlayerListener(), this);
		pm.registerEvents(new PingListener(), this);
		
		new hu.Ricsi.BlockParty.Commands.blockparty(null, null);
		new hu.Ricsi.BlockParty.Commands.Vote(null, null);
		new gen(null, null);
		new gui(null, null); 
		new leave(null, null);
		
		Config.DefaConfig();
		Lang.DefaLang();
		Arena.saveArena();
		Templates.saveTemplates();
		Blocks.saveBlocks();
		
		sendVoteState(); 
		
		Timer.tTimer();
	}
	
	public void onDisable() {
		for(HashMap.Entry<String, Command> command : Commands.entrySet()) {
		new	removeCommand(command.getKey());
		//	Commands.remove(command.getKey());
		}
		
		getServer().getScheduler().cancelTasks(this);
	}
	
	public boolean createCommand(String cmd, hu.Ricsi.BlockParty.Commands.Command command)
	{
	    if (this.Commands.containsKey(cmd)) {
	      return false;
	    }
	    this.Commands.put(cmd, command);
	    return true;
	}
    private void sendVoteState() {
        BukkitScheduler scheduler = getServer().getScheduler();
        scheduler.scheduleSyncRepeatingTask(this, new Runnable() {
            @Override
            public void run() {
            	if(State.getState() == GameState.LOBBY) {
            		new VoteStatus().sendStatusAll();
            	}
            }
        }, 0L, 20 * 30);
    }
	  
}
