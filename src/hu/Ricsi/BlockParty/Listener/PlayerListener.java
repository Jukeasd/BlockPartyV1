package hu.Ricsi.BlockParty.Listener;

import java.util.List;
import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.player.PlayerLoginEvent.Result;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import hu.Mark.BestPerms.Api.BestPermsAPI;
import hu.Ricsi.BlockParty.BlockParty;
import hu.Ricsi.BlockParty.FileConfiguration.Arena;
import hu.Ricsi.BlockParty.FileConfiguration.Config;
import hu.Ricsi.BlockParty.FileConfiguration.Lang;
import hu.Ricsi.BlockParty.GameManager.ArenaManager;
import hu.Ricsi.BlockParty.GameState.GameState;
import hu.Ricsi.BlockParty.GameState.State;
import hu.Ricsi.BlockParty.ScoreBoard.LobbyScore;


public class PlayerListener implements Listener {
private Map<Player , String> votes = BlockParty.getInstance().votes;
private List<Player> InGame = BlockParty.getInstance().InGame;
public List<Player> Spectator = BlockParty.getInstance().Spectator;
public List<Player> kieset = BlockParty.getInstance().kieset;


	
	@EventHandler
	public void PlayerLogin(PlayerLoginEvent e) 
	{
		Player p = e.getPlayer();
		if(State.getState() == GameState.LOBBY || State.getState() == GameState.STARTING) {

			if(InGame.size() < Arena.getInt("player.max")) {
				e.allow();
			}else if(!BestPermsAPI.hasPermission(p, Arena.getString("player.join_full_perm"))){
				e.disallow(Result.KICK_OTHER, Lang.getString("Arena.full"));  
			}else {
				e.allow();
			}
		}else if(State.getState() == GameState.INGAME || State.getState() == GameState.STOPPING){
			if(BestPermsAPI.hasPermission(p, Arena.getString("player.join_full_perm"))) {
				e.allow();
			}else {
				e.disallow(Result.KICK_OTHER, Lang.getString("Arena.started")); 
			}
		}
	}

	
	@EventHandler
	public void PlayerMove(PlayerMoveEvent e) {
		if(!State.getState().equals(GameState.NULL))  {
			Player p = e.getPlayer();
			Location loc = e.getTo();
			int i = loc.getBlockY();
			int ii = Arena.getInt("void.pos_y");
			if(InGame.contains(p) && ii > i) {
				InGame.remove(p);
				kieset.add(p);
				for(Player o : Bukkit.getOnlinePlayers()) {
					o.sendMessage(Lang.getString("").replace("(player)", p.getName()));
				}
			}else if(ii > i){
				p.teleport(loc.add(0, 1.2, 0));
				p.sendMessage(Lang.getString(""));
			}
		}
	}

	@SuppressWarnings("deprecation")
	@EventHandler
	public void PlayerJoin(PlayerJoinEvent e) 
	{
		e.setJoinMessage(null);
		Player p = e.getPlayer();
		p.setGameMode(GameMode.ADVENTURE);
		p.getInventory().clear();
		if(State.getState() == GameState.LOBBY || State.getState() == GameState.STARTING) {
			LobbyScore.lobby(p);
			if(!InGame.contains(p)) {
				InGame.add(p);
				ArenaManager.joinMSG(p);
			}
			ItemStack item = new ItemStack(Material.getMaterial(Config.getInt("vote.item.item_id")), 1, (short)Config.getInt("vote.item.meta"));
			ItemMeta meta = item.getItemMeta();
			meta.setDisplayName(Config.getString("vote.item.name"));
			item.setItemMeta(meta);
			p.getInventory().setItem(Config.getInt("vote.item.slot"), item);
			
			
		}else if(State.getState() == GameState.INGAME || State.getState() == GameState.STOPPING) {
			
			if(!Spectator.contains(p)) {
				Spectator.add(p);
				ArenaManager.joinSp(p);
			}
			
		}

		
	}

	@EventHandler
	public void onleave(PlayerQuitEvent e) {
		e.setQuitMessage(null);
		Player p = e.getPlayer();
		if(votes.containsKey(p)) {
			votes.remove(p);
		}
		p.getInventory().clear();
		
		if(InGame.contains(p)) {
			InGame.remove(p);
			ArenaManager.leaveMSG(p);
		}
		
		if(Spectator.contains(p)) {
			Spectator.remove(p);
		}
		
		
      	for (PotionEffect ef : p.getActivePotionEffects()) {
      		if (ef.getType().equals(PotionEffectType.INVISIBILITY)) {
      			p.getPlayer().removePotionEffect(ef.getType());
      		}
      	}
	}
	
	@EventHandler
	public void onPlayerInteract(PlayerInteractEvent e) {
		Player player = e.getPlayer();
		if(!State.getState().equals(GameState.NULL)) {
			if(e.getAction() == (Action.RIGHT_CLICK_BLOCK) || e.getAction() == (Action.RIGHT_CLICK_AIR)) {
				if (e.getItem() != null && e.getItem().getItemMeta().getDisplayName() != null && e.getItem().getItemMeta().getDisplayName().equals(Config.getString("vote.item.name"))) {
					player.chat("/gui");
					e.setCancelled(true);
				}
			}	
		}
	}
	
	@EventHandler
	public void PlayerDrop(PlayerDropItemEvent e) 
	{
		if(!State.getState().equals(GameState.NULL)) {
			e.setCancelled(true);
		}
	}
	
	@EventHandler
	public void PlayerDamage(EntityDamageEvent e) 
	{
		if(!State.getState().equals(GameState.NULL)) {
			e.setCancelled(true);
		}
	}
	
	@EventHandler
	public void onBlockPlaceEvent(BlockPlaceEvent e)
	{
		if(!State.getState().equals(GameState.NULL)) {
	    	e.setCancelled(true);
		}
	}
	
	@EventHandler
	public void onFeed(FoodLevelChangeEvent e)
	{
	   e.setFoodLevel(20);
	}
	
	@EventHandler
	public void onClick(InventoryClickEvent e)
	{
		if(!State.getState().equals(GameState.NULL)) {
		  e.setCancelled(true);
		  
		  if(e.getClickedInventory() == null || e.getCurrentItem() == null || !e.getCurrentItem().hasItemMeta()) {
			  return;
		  }
		  Player player = (Player) e.getWhoClicked();
		  if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(Config.getString("vote.item.name"))) {
			  player.chat("/gui");
			  e.setCancelled(true);
		  }
			for(String key : Config.getSelection("vote.theme").getKeys(false)) {
				String loc = Config.getString("vote.theme." + key + ".name");
				String cmd = "/szavaz " + Config.getString("vote.theme." + key + ".loc");
				if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(loc)) {
					
					player.chat(cmd);
					player.closeInventory();
					player.updateInventory();
				}
			}
		}
	}
	
	@EventHandler
	public void onBlockBreak(BlockBreakEvent e)
	{
		if(!State.getState().equals(GameState.NULL)) {
			e.setCancelled(true);
		}
	}
}
