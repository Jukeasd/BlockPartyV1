package hu.Ricsi.BlockParty.Commands;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import hu.Ricsi.BlockParty.VoteSystem.Gui;

public class gui extends Command{

	public gui(String cmd, String perm) {
		super("gui", "blockparty.gui");
		// TODO Auto-generated constructor stub
	}

	@Override
	public void execute(CommandSender sender, String cmd, ArrayList<String> args) {
		// TODO Auto-generated method stub
		new Gui().newInventory((Player) sender);
	}

	@Override
	public List<String> onTabComplete(CommandSender sender, ReflectCommand Null, String label, String[] args) {
		// TODO Auto-generated method stub
		return null;
	}

}
