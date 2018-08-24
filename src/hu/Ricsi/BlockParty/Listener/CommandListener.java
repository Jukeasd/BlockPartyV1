package hu.Ricsi.BlockParty.Listener;

import java.util.ArrayList;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import hu.Ricsi.BlockParty.BlockParty;
import hu.Ricsi.BlockParty.Commands.Command;
import hu.Ricsi.BlockParty.FileConfiguration.Lang;


public class CommandListener implements Listener{

	
	 @EventHandler
	  public void onPlayerCommand(final PlayerCommandPreprocessEvent event)
	  {
	    Player p = event.getPlayer();
	    
		String PName = BlockParty.getInstance().getDescription().getName().toLowerCase() + ":";
	    String msg = event.getMessage(); 
	    if(msg.startsWith("/" + PName)) {
	    	msg = msg.replace(PName, "");
	    }
	    String cmd = msg;
	    ArrayList<String> args = new ArrayList<String>();
	    if (msg.contains(" "))
	    {
	      String[] s = msg.split(" ");
	      cmd = s[0];
	      for (int i = 1; i < s.length; i++) {
	        args.add(s[i]);
	      }
	    }
	    cmd = cmd.toLowerCase().substring(1);
	    if (BlockParty.getInstance().Commands.containsKey(cmd))
	    {			
	      
	      Command c = BlockParty.getInstance().Commands.get(cmd);
	      if(p.hasPermission(c.getPerm())) {	
	      	event.setCancelled(true);
	      	c.execute(p, cmd, args);
	      }else {
	    	  p.sendMessage(Lang.getString("no_perm"));
	      }
	    }
	  }





	
}
