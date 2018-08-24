package hu.Ricsi.BlockParty.BlockEditor;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;

import hu.Ricsi.BlockParty.BlockParty;
import hu.Ricsi.BlockParty.FileConfiguration.Arena;

public class airTemplates {
	public static List<String> blocks = BlockParty.getInstance().blocks;
	 @SuppressWarnings("deprecation")
		public static void airTemp(String s)
		{
		 String[] ss = s.split(":");
		 int id = Integer.parseInt(ss[0]);
		 int data = Integer.parseInt(ss[1]);
			if(!blocks.isEmpty()) {
				blocks.clear();
			}
			List<Location> l = new ArrayList<Location>();
			World w = Bukkit.getWorld(Arena.getString("arena.world"));
			Location loc1 = new Location(w, Arena.getInt("arena.pos1.x"), Arena.getInt("arena.pos1.y"), Arena.getInt("arena.pos1.z"));
			Location loc2 = new Location(w, Arena.getInt("arena.pos2.x"), Arena.getInt("arena.pos2.y"), Arena.getInt("arena.pos2.z"));
		
			int x1 = loc1.getBlockX();
			int y1 = loc1.getBlockY();
			int z1 = loc1.getBlockZ();
		
			int x2 = loc2.getBlockX();
			int y2 = loc2.getBlockY();
			int z2 = loc2.getBlockZ();
		
			int xMin, yMin, zMin;
			int xMax, yMax, zMax;
		
			int x, y, z;
		
			if(x1 > x2) { 
			    xMin = x2;
			    xMax = x1;
			}else{
			    xMin = x1;
			    xMax = x2;
			}
			
			if(y1 > y2) {
			    yMin = y2;
			    yMax = y1;
			}else{
			    yMin = y1;
			    yMax = y2;
			} 
			
			if(z1 > z2) {
			    zMin = z2;
			    zMax = z1;
			}else{
			    zMin = z1;
			    zMax = z2;
			} 
			
			for(x = xMin; x <= xMax; x ++) {
			    for(y = yMin; y <= yMax; y ++) {
			        for(z = zMin; z <= zMax; z ++) {
						 Location loc = new Location(w, x, y, z);
						 l.add(loc);
			        }
			    }
			}
			Iterator<Location> itr = l.iterator();
			
			while(itr.hasNext()) {
				Location ll = itr.next();
				 Block b = ll.getBlock();
				 int id1 = b.getTypeId();
				 int data1 = b.getData();
				 if(id != id1 || data != data1 ) {
					 b.setType(Material.AIR);
				 }		        	
			}
		 }
}
