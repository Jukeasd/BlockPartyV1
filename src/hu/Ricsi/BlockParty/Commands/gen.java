package hu.Ricsi.BlockParty.Commands;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.command.CommandSender;

import hu.Ricsi.BlockParty.VoteSystem.Szazalek;

public class gen extends Command{

	public gen(String cmd, String perm) {
		super("gen", "blockparty.*");
		// TODO Auto-generated constructor stub
	}

	@Override
	public void execute(CommandSender sender, String cmd, ArrayList<String> args) {
		// TODO Auto-generated method stub
		sender.sendMessage(new Szazalek().sti());
	}

	@Override
	public List<String> onTabComplete(CommandSender sender, ReflectCommand Null, String label, String[] args) {
		// TODO Auto-generated method stub
		return null;
	}

}
