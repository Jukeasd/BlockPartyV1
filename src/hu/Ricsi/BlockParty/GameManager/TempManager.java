package hu.Ricsi.BlockParty.GameManager;

import java.util.List;
import java.util.Random;

import hu.Ricsi.BlockParty.BlockParty;
import hu.Ricsi.BlockParty.BlockEditor.placeTemplates;

public class TempManager {
	
	static Random rand = new Random();
	static int n;
	static int b;
	static int t = 10;
	public static List<String> blocks = BlockParty.getInstance().blocks;
	public static List<String> themes = BlockParty.getInstance().themes;
	private static boolean first = true;
	public static void setplace() {
		if(first) {
			if(t == 10) {
				placeTemplates.placeTemp(themes.get(0));
				b = rand.nextInt(blocks.size());
			}else if(t > 0 && t < 10) {
				
			}else if (t < 1) {
				
				
				first = false;
			}
			
			
			
			
		}else {
			b = rand.nextInt(blocks.size());
			n = rand.nextInt(themes.size()) + 1;
			
			
			
			
			
			
		}
	}
	
	
	
}
