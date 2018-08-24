package hu.Ricsi.BlockParty.BlockEditor;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.block.Block;
import hu.Ricsi.BlockParty.FileConfiguration.Arena;
import hu.Ricsi.BlockParty.FileConfiguration.Templates;

public class saveTemplates {

	@SuppressWarnings("deprecation")
	public static void saveTemp(String name)
	{
		World w = Bukkit.getWorld(Arena.getString("arena.world"));
 		Location loc1 = new Location(w, Arena.getInt("arena.pos1.x"), Arena.getInt("arena.pos1.y"), Arena.getInt("arena.pos1.z"));
 		Location loc2 = new Location(w, Arena.getInt("arena.pos2.x"), Arena.getInt("arena.pos2.y"), Arena.getInt("arena.pos2.z"));
		int i = 0;
		  
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
					i++;
					Block b = new Location(w, x, y, z).getBlock();
					Templates.setTemplates("templates." + name + "." + i + ".id", b.getType().getId());
					Templates.setTemplates("templates." + name + "." + i + ".data", b.getData());
                }
            }
        }
		Templates.saveTemplates();
	 }
}
