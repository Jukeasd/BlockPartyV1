package hu.Ricsi.BlockParty.Commands;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandMap;
import org.bukkit.command.CommandSender;
import hu.Ricsi.BlockParty.BlockParty;

public abstract class Command {	
	
	protected static CommandMap cmap;
	public String perm;
	
	  public Command(String cmd, String perm)
	  {
	    BlockParty.getInstance().createCommand(cmd, this);
	    register(cmd);
	    this.perm = perm;
	  }
	  
	  public Command(String[] cmds, String perm)
	  {
		  for(String s : cmds) {
			  BlockParty.getInstance().createCommand(s, this);  
			  register(s);
		  }
		  this.perm = perm;
	  }
	  public String getPerm() {
		  return this.perm;
	  }
	  
	  public abstract void execute(CommandSender sender,String cmd, ArrayList<String> args);

	  public abstract List<String> onTabComplete(CommandSender sender, ReflectCommand Null, String label, String[] args);



		public void register(String command) {
			ReflectCommand cmd = new ReflectCommand(command);
			getCommandMap1().register(BlockParty.getInstance().getDescription().getName().toLowerCase(), cmd);
			cmd.setExecutor(this);
		}

		final CommandMap getCommandMap1() {
		if (cmap == null) {
			try {
				final Field f = Bukkit.getServer().getClass().getDeclaredField("commandMap");
				f.setAccessible(true);
					cmap = (CommandMap) f.get(Bukkit.getServer());
					return getCommandMap1();
			} catch (Exception e) {
				e.printStackTrace(); 
				}
		} else if (cmap != null) {
			return cmap; 
			}
		return getCommandMap1();
		}

		final class ReflectCommand extends org.bukkit.command.Command {
			private Command exe = null;
			protected ReflectCommand(String command) { super(command); }
			public void setExecutor(Command command) { this.exe = command; }
			@Override public boolean execute(CommandSender sender, String commandLabel, String[] args) {
				return false;
			}

			@Override  public List<String> tabComplete(CommandSender sender, String alais, String[] args) {
				if (exe != null) { return exe.onTabComplete(sender, this, alais, args); }
				return null;
			}
		}


}
