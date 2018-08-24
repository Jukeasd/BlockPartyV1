package hu.Ricsi.BlockParty.VoteSystem;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import hu.Ricsi.BlockParty.BlockParty;
import hu.Ricsi.BlockParty.FileConfiguration.Config;
import hu.Ricsi.BlockParty.FileConfiguration.Lang;

public class Gui {
	
	private Map<String, Integer> sz = BlockParty.getInstance().sz1;
	
	@SuppressWarnings("unchecked")
	public void newInventory(Player player) {
		Inventory i = BlockParty.getInstance().getServer().createInventory(null, 27, Lang.getString("Vote.gui_name"));
		new VoteStatus().si();
				for(String key : Config.getSelection("vote.theme").getKeys(false)) {

					String theme1 = theme(key);
					String a = sz.get(theme1) == null ? 0 + "" : sz.get(theme1) + ""; 
					
					String name = Config.getString("vote.theme." + key + ".name");
					int id = Config.getInt("vote.theme." + key + ".item_id");
					int meta = Config.getInt("vote.theme." + key + ".meta");
					int slot = Config.getInt("vote.theme." + key + ".slot");
					
					
					@SuppressWarnings("deprecation")
					ItemStack item = new ItemStack(Material.getMaterial(id), 1, (short)meta);
					ItemMeta meta1 = item.getItemMeta();
					meta1.setDisplayName(name);
					List<String> lore = new ArrayList<String>();
					for(String l : (List<String>) Config.getConfig().getList("vote.theme." + key + ".lore")) {
						lore.add(l.replace("(votes)", a).replace("&", "§"));
					}
					
//				    for (int ii = 0; ii < lore.size(); ii++) {
//				        if (lore.get(ii).contains("(votes)")) {
//				            lore.get(ii).replace("(votes)", a);
//				        }
//				    }
					
					
					meta1.setLore(lore);
					item.setItemMeta(meta1);
					i.setItem(slot,item);
				}

		player.openInventory(i);
	}
	
	public String theme(String s) {
		return Config.getString("vote.theme." + s + ".name");
	}
}
