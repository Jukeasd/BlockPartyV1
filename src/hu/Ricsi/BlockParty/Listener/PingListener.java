package hu.Ricsi.BlockParty.Listener;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.server.ServerListPingEvent;

import hu.Ricsi.BlockParty.FileConfiguration.Arena;
import hu.Ricsi.BlockParty.FileConfiguration.Lang;
import hu.Ricsi.BlockParty.GameState.GameState;
import hu.Ricsi.BlockParty.GameState.State;


public class PingListener implements Listener {

	
    @EventHandler
    public void onServerPing(ServerListPingEvent e) {
    	
        if (State.getState() == GameState.LOBBY) {
            e.setMotd((String)Lang.getString("server_ping.LOBBY"));
        }
        if (State.getState() == GameState.STARTING) {
            e.setMotd((String)Lang.getString("server_ping.STARTING"));
        }
        if (State.getState() == GameState.INGAME) {
            e.setMotd((String)Lang.getString("server_ping.INGAME"));
        }
        if (State.getState() == GameState.STOPPING) {
            e.setMotd((String)Lang.getString("server_ping.STOPPING"));
        }
        e.setMaxPlayers(Arena.getInt("player.max"));
    }
    
	
}
