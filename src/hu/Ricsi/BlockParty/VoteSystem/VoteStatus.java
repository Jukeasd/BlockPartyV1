package hu.Ricsi.BlockParty.VoteSystem;

import java.util.HashMap;
import java.util.Map;
import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.v1_12_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;

import hu.Ricsi.BlockParty.BlockParty;
import hu.Ricsi.BlockParty.FileConfiguration.Config;
import hu.Ricsi.BlockParty.FileConfiguration.Lang;
import net.minecraft.server.v1_12_R1.IChatBaseComponent;
import net.minecraft.server.v1_12_R1.PacketPlayOutChat;
import net.minecraft.server.v1_12_R1.IChatBaseComponent.ChatSerializer;

public class VoteStatus {

	private Map<Player , String> votes = BlockParty.getInstance().votes;
		
	public String theme(String s) {
		return Config.getString("vote.theme." + s + ".name");
	}
	
	public String themel(String s) {
		return Config.getString("vote.theme." + s + ".loc");
	}
	
	private Map<String, Integer> sz = BlockParty.getInstance().sz1;
	
	public void si() {
		if(!sz.isEmpty()) {
			sz.clear();
		}
			
		for (HashMap.Entry<Player, String> e : votes.entrySet())
	 	{

			if(!sz.containsKey(e.getValue())) {
				sz.put(e.getValue(), 1);
			}else {
				int a = sz.get(e.getValue());
				sz.remove(e.getValue());
				sz.put(e.getValue(), a + 1);
			}
	 	}
	}
	
	public void sendStatus(Player p, String s) {
		si();
		p.sendMessage(Lang.getString("Vote.msg_header"));
		p.sendMessage(Lang.getString("Vote.msg_succes"));
		for(String key : Config.getSelection("vote.theme").getKeys(false)) 
	 	{
			String themel = themel(key);
			String theme1 = theme(key);
			String theme = theme1;
			String a = sz.get(theme1) == null ? 0 + "" : sz.get(theme1) + ""; 
			String b = theme(key).equalsIgnoreCase(s) ? Lang.getString("Vote.voted") : Lang.getString("Vote.!voted");
			msg(p, "§7/szavaz §f" + themel, Lang.getString("Vote.msg_format").replace("(theme)", theme).replace("(votes)", a).replace("(s)", b), "/szavaz " + themel);
	 	}
		p.sendMessage(Lang.getString("Vote.msg_footer2"));
	}
	
	public void msg(Player player , String hover, String msg , String command)
	{
		IChatBaseComponent comp = ChatSerializer
				.a("{\"text\":\"" + msg + "\",\"hoverEvent\":{\"action\":\"show_text\",\"value\":\"" + hover + "\"},\"clickEvent\":{\"action\":\"run_command\",\"value\":\"" + command + "\"}}");
		PacketPlayOutChat chat = new PacketPlayOutChat(comp);
		((CraftPlayer)player).getHandle().playerConnection.sendPacket(chat);
	}
	
	public void sendStatusAll() {
		si();
		for(Player p : Bukkit.getOnlinePlayers()) {
			p.sendMessage(Lang.getString("Vote.msg_header"));
			p.sendMessage(Lang.getString("Vote.msg_succes"));
			for(String key : Config.getSelection("vote.theme").getKeys(false)) 
		 	{
				String themel = themel(key);
				String theme1 = theme(key);
				String s = votes.get(p);
				String theme = theme1;
				String a = sz.get(theme1) == null ? 0 + "" : sz.get(theme1) + ""; 
				String b = theme(key).equalsIgnoreCase(s) ? Lang.getString("Vote.voted") : Lang.getString("Vote.!voted");
				msg(p, "§7/szavaz §f" + themel, Lang.getString("Vote.msg_format").replace("(theme)", theme).replace("(votes)", a).replace("(s)", b), "/szavaz " + themel);
		 	}
			p.sendMessage(Lang.getString("Vote.msg_footer2"));
		}
	}
	
}
