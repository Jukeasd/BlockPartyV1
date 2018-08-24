package hu.Ricsi.BlockParty.Commands;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.bukkit.command.CommandMap;
import org.bukkit.command.SimpleCommandMap;
import org.bukkit.plugin.SimplePluginManager;

import hu.Ricsi.BlockParty.BlockParty;

public class removeCommand {
	private final Map<String, Entry<Method, Object>> commandMap = new HashMap<String, Entry<Method, Object>>();
	private CommandMap map = new SimpleCommandMap(null);
	
	
	public removeCommand(String command) {
		
			unreg(command);
			unreg(BlockParty.getInstance().getDescription().getName().toLowerCase() + ":" + command);
	}
	
	
	
	 private void unreg(String command) 
	    {
	    	if (BlockParty.getInstance().getServer() != null && BlockParty.getInstance().getServer().getPluginManager() instanceof SimplePluginManager) 
	    	{
				final SimplePluginManager manager = (SimplePluginManager) BlockParty.getInstance().getServer().getPluginManager();
				try 
				{
					final Field field = SimplePluginManager.class.getDeclaredField("commandMap");
					field.setAccessible(true);
					map = (CommandMap) field.get(manager);
					final Field field2 = SimpleCommandMap.class.getDeclaredField("knownCommands");
					field2.setAccessible(true);
					@SuppressWarnings("unchecked")
					final Map<String, org.bukkit.command.Command> knownCommands = (Map<String, org.bukkit.command.Command>) field2.get(map);
					for (final Map.Entry<String, org.bukkit.command.Command> entry : knownCommands.entrySet()) 
					{
						if (entry.getKey().equals(command)) 
						{
							entry.getValue().unregister(map);
						}
					}
					knownCommands.remove(command);
				}
				catch (IllegalArgumentException | NoSuchFieldException | IllegalAccessException | SecurityException e) 
				{
					BlockParty.getInstance().getServer().getConsoleSender().sendMessage("RemoveCommand: " + e);
				}
			}
			commandMap.remove(command);
	    }
	
}
