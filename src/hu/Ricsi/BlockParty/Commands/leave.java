package hu.Ricsi.BlockParty.Commands;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import hu.Ricsi.BlockParty.GameManager.SendServer;

public class leave extends Command{

	public leave(String[] cmds, String perm) {
		super(new String[] {"leave", "kilep", "kilép"}, "blockparty,leave");
		// TODO Auto-generated constructor stub
	}

	@Override
	public void execute(CommandSender sender, String cmd, ArrayList<String> args) {
		Player p = (Player) sender;
		SendServer.sendPlayer(p);
		
	}

	@Override
	public List<String> onTabComplete(CommandSender sender, ReflectCommand Null, String label, String[] args) {
		// TODO Auto-generated method stub
		return null;
	}

}
