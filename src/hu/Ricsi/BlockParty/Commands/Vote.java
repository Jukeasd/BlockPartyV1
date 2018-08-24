package hu.Ricsi.BlockParty.Commands;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitScheduler;

import com.google.common.collect.Lists;

import hu.Ricsi.BlockParty.BlockParty;
import hu.Ricsi.BlockParty.FileConfiguration.Config;
import hu.Ricsi.BlockParty.FileConfiguration.Lang;
import hu.Ricsi.BlockParty.VoteSystem.VoteStatus;

public class Vote extends Command{

	public Vote(String[] cmds, String perm) {
		super(new String[] {"vote" , "v" , "szavaz" , "szavazas", "szavazás"}, "blockparty.vote");
		// TODO Auto-generated constructor stub
	}

	private List<Player> cd = BlockParty.getInstance().cd;
	
	@Override
	public void execute(CommandSender sender, String cmd, ArrayList<String> args) {
		Player p = (Player) sender;
		if(args.size() == 1) {
			String s = ch(args.get(0));
			if(s != "Error") {
				if(!cd.contains(p)) {
					//sender.sendMessage(lang().getString("Vote.msg_succes").replace("(song)", s));
					new hu.Ricsi.BlockParty.VoteSystem.Vote(p, s);
					new VoteStatus().sendStatus(p, s);
					if(!p.hasPermission("*")) {
						cd.add(p);
						cd(p);
					}
				}else {
					sender.sendMessage(Lang.getString("Vote.cooldown_msg").replace("(time)", "" + Config.getInt("vote.cooldown_seconds")));
				}
			}else {
				sender.sendMessage(Lang.getString("Vote.msg_invaild_song"));
			}
		}else {
			sender.sendMessage(Lang.getString("Vote.msg_invaild_cmd"));
		}
		
	}

	
	public String theme(int s) {
		return Config.getString("vote.theme_" + s);
	}
	
	public String ch(String s) {
		
		String a = "Error";
		for(String key : Config.getSelection("vote.theme").getKeys(false)) {
			String loc = Config.getString("vote.theme." + key + ".loc");
			if(s.equalsIgnoreCase(loc)) {
				a = Config.getString("vote.theme." + key + ".name");;
			}
		}
		return a;
	}
	
//	public String ch(String s) {
//		if(s.equalsIgnoreCase(theme(1))) {
//			return theme(1);
//		}
//		else if(s.equalsIgnoreCase(theme(2))){
//			return theme(2);
//		}
//		else if(s.equalsIgnoreCase(theme(3))){
//			return theme(3);
//		}
//		else if(s.equalsIgnoreCase(theme(4))){
//			return theme(4);
//		}
//		else if(s.equalsIgnoreCase(theme(5))){
//			return theme(5);
//		}
//		else if(s.equalsIgnoreCase(theme(6))){
//			return theme(6);
//		}
//		else if(s.equalsIgnoreCase(theme(7))){
//			return theme(7);
//		}
//		else if(s.equalsIgnoreCase(theme(8))){
//			return theme(8);
//		}
//		return "Error";
//
//	}
	
	@Override
	public List<String> onTabComplete(CommandSender sender, ReflectCommand Null, String label, String[] args) {
		//List<String> tab = new ArrayList<String>();
		List<String> tab = Lists.newArrayList();
		if(args.length == 1)
			
		{
			for(String key : Config.getSelection("vote.theme").getKeys(false)) {
				String loc = Config.getString("vote.theme." + key + ".loc");
				tab.add(loc + "");
			}
			return tab;
		}
		return null;
	}

    private void cd(Player p) {
        BukkitScheduler scheduler = BlockParty.getInstance().getServer().getScheduler();
        scheduler.runTaskLater(BlockParty.getInstance(), new Runnable() {
            @Override
            public void run() {
            	cd.remove(p);
            }
        }, 20 * Config.getInt("vote.cooldown_seconds"));
    }
	
}
