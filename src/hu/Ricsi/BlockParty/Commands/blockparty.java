package hu.Ricsi.BlockParty.Commands;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.bukkit.Location;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.google.common.collect.Lists;

import hu.Ricsi.BlockParty.BlockEditor.airTemplates;
import hu.Ricsi.BlockParty.BlockEditor.placeTemplates;
import hu.Ricsi.BlockParty.BlockEditor.saveTemplates;
import hu.Ricsi.BlockParty.FileConfiguration.Arena;
import hu.Ricsi.BlockParty.FileConfiguration.Config;
import hu.Ricsi.BlockParty.FileConfiguration.Lang;
import hu.Ricsi.BlockParty.FileConfiguration.Templates;
import hu.Ricsi.BlockParty.GameState.GameState;
import hu.Ricsi.BlockParty.GameState.State;
import hu.Ricsi.BlockParty.ScoreBoard.InGameScore;
import hu.Ricsi.BlockParty.ScoreBoard.LobbyScore;
import hu.Ricsi.BlockParty.ScoreBoard.ScoreBoard;

public class blockparty extends Command{

	public blockparty(String[] cmds, String perm) {
		super(new String[] {"blockparty", "ebp", "sbp", "bparty", "blockp"}, "blockparty.*");
		// TODO Auto-generated constructor stub
	}

	@Override
	public void execute(CommandSender sender, String cmd, ArrayList<String> args) {
		if(args.size() < 1) {
			return;
		}
		Player p = (Player) sender;
		if(args.get(0).equalsIgnoreCase("reload")) {
//			new Lang();
//			new Config();
			Lang.reload();
			Config.reload();
			Arena.reload();
			Templates.reload();
			sender.sendMessage(Lang.getString("reload_conf_lang"));
		}
		
		if(args.size() > 1) {
			if (args.get(0).equalsIgnoreCase("setname")) {
				Arena.setArena("arena.name", args.get(1));
				sender.sendMessage(Lang.getString(""));
			}
		}

		if (args.get(0).equalsIgnoreCase("setlobby")) {

			Location loc = p.getLocation().add(0, 0, 0);
			Arena.setArena("lobby.pos.x", loc.getBlockX());
			Arena.setArena("lobby.pos.y", loc.getBlockY());
			Arena.setArena("lobby.pos.z", loc.getBlockZ());
			sender.sendMessage(Lang.getString(""));
		}
		if (args.get(0).equalsIgnoreCase("setvoid")) {

			Location loc = p.getLocation().add(0, 0, 0);
			Arena.setArena("void.pos_y", loc.getBlockY());
			sender.sendMessage(Lang.getString(""));
		}
		if (args.get(0).equalsIgnoreCase("setpos1")) {

			Location loc = p.getLocation().add(0, 0, 0);
			Arena.setArena("arena.pos1.x", loc.getBlockX());
			Arena.setArena("arena.pos1.y", loc.getBlockY());
			Arena.setArena("arena.pos1.z", loc.getBlockZ());
			Arena.setArena("arena.world", p.getWorld().getName());
			sender.sendMessage(Lang.getString(""));
		}
		if (args.get(0).equalsIgnoreCase("setpos2")) {

			Location loc = p.getLocation().add(0, 0, 0);
			Arena.setArena("arena.pos2.x", loc.getBlockX());
			Arena.setArena("arena.pos2.y", loc.getBlockY());
			Arena.setArena("arena.pos2.z", loc.getBlockZ());
			Arena.setArena("arena.world", p.getWorld().getName());
			sender.sendMessage(Lang.getString(""));
		}
		
		if (args.get(0).equalsIgnoreCase("saveTemplates")) {
			String name = args.get(1);
			if(name != null) {
				saveTemplates.saveTemp(name);
				sender.sendMessage(Lang.getString(""));
			}else {
				sender.sendMessage(Lang.getString(""));
			}
		}
		
		if (args.get(0).equalsIgnoreCase("placeTemplates")) {
			String name = args.get(1);
			if(name != null) {
				placeTemplates.placeTemp(name);
				sender.sendMessage(Lang.getString(""));
			}else {
				sender.sendMessage(Lang.getString(""));
			}
		}
		
		
		if (args.get(0).equalsIgnoreCase("airTemplates")) {
			String id = args.size() > 0 ? args.get(0) : null;
			if(id != null) {
				airTemplates.airTemp(id);
				sender.sendMessage(Lang.getString(""));
			}else {
				sender.sendMessage(Lang.getString(""));
			}
		}
		
		
		if (args.get(0).equalsIgnoreCase("setState")) {
			String name = args.get(1);
			if(name != null) {
				State.setState(st(name));
				sender.sendMessage(Lang.getString(""));
			}else {
				sender.sendMessage(Lang.getString(""));
			}
		}
		
		
		if (args.get(0).equalsIgnoreCase("getState")) {

				sender.sendMessage(State.getState() + "");
		}
		
		if (args.get(0).equalsIgnoreCase("LobbyScore")) {

			LobbyScore.lobby(p);
	}
		
		if (args.get(0).equalsIgnoreCase("InGameScore")) {

			InGameScore.setInGameScore();
	}
		
		if (args.get(0).equalsIgnoreCase("updateScore")) {

			ScoreBoard.updateGameScore();
	}
		
	}
	

		
	public GameState st(String s) {
		if(s.equalsIgnoreCase("LOBBY")) {
			return GameState.LOBBY;
		}else if(s.equalsIgnoreCase("STARTING")) {
			return GameState.STARTING;
		}else if(s.equalsIgnoreCase("INGAME")) {
			return GameState.INGAME;
		}else if(s.equalsIgnoreCase("STOPPING")) {
			return GameState.STOPPING;
		}	
		return GameState.NULL;
	}

	
	@Override
	public List<String> onTabComplete(CommandSender sender, ReflectCommand Null, String label, String[] args) {
		if(!sender.hasPermission("blockparty.*"))
		{
			return null;
		}
		List<String> tab = Arrays.asList("reload" , "LobbyScore", "InGameScore", "updateScore", "setState", "getState", "setLobby" , "setVoid", "setPos1", "setPos2", "setName", "saveTemplates", "placeTemplates", "airTemplates");
		List<String> tab1 = Lists.newArrayList();
		if(args.length == 1)
		
		{
			for(String s: tab)
			{
				if(s.toLowerCase().startsWith(args[0].toLowerCase())) tab1.add(s);
			}
			return tab1;

		}
		return null;
	}

}
