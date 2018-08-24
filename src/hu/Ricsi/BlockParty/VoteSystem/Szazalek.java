package hu.Ricsi.BlockParty.VoteSystem;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import hu.Ricsi.BlockParty.BlockParty;

public class Szazalek {
	private String strin = "Senki sem szavazot";
	private Map<Player, String> votes = BlockParty.getInstance().votes;
	private Map<String, Integer> sz = BlockParty.getInstance().sz;
	
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
	
	public static <Key, Value extends Comparable<? super Value>> LinkedHashMap<Key, Value> sortByValue(Map<Key, Value> map)
	  {

		List<Map.Entry<Key, Value>> list = new LinkedList<Entry<Key, Value>>(map.entrySet());
	    Collections.sort(list, new Comparator<Object>()
	    {
		@SuppressWarnings("unchecked")
		@Override
		public int compare(Object o1, Object o2) {
			return ((Comparable<Integer>)((Entry<String, Integer>) o2).getValue()).compareTo(((Entry<String, Integer>) o1).getValue());
		}

		

	    });
	    LinkedHashMap<Key, Value> result = new LinkedHashMap<Key, Value>();
	    for (Map.Entry<Key, Value> entry : list)
	    {
	      result.put(entry.getKey(), entry.getValue());
	      if (result.size() > 20) {
	        break;
	      }
	    }
	    return result;
	  }
	
	public int as() {
		si();
		sz = sortByValue(sz);
		int a = 0;
		for (Entry<String, Integer> e : sz.entrySet())
	 	{
			a = e.getValue();
			strin = e.getKey();

			break;
	 	}
		return a;
	}
	
	public String sti() {
		int a = as();
		if(a != 0) {
			//(5*100)/6
		String q = strin + " nyert " + (a*100)/Bukkit.getOnlinePlayers().size() + "%";
		//clear();
		return q;
		}
	//	clear();
		return strin;
	}
	
	public void clear() {
		for (Entry<String, Integer> e : sz.entrySet())
	 	{
			sz.remove(e.getKey());
	 	}
		
		for (Entry<Player, String> e : votes.entrySet())
	 	{
			votes.remove(e.getKey());
	 	}
		
	}
}
