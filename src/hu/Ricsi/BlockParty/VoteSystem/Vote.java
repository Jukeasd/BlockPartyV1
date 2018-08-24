package hu.Ricsi.BlockParty.VoteSystem;

import java.util.Map;

import org.bukkit.entity.Player;

import hu.Ricsi.BlockParty.BlockParty;

public class Vote {

	private Map<Player, String> votes = BlockParty.getInstance().votes;

	public Vote(Player p, String s) {
		if(!votes.containsKey(p)) {
			votes.put(p, s);
		}else {
			votes.remove(p);
			votes.put(p, s);
		}
	}
			
	
}
