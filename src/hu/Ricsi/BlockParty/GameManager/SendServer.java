package hu.Ricsi.BlockParty.GameManager;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import hu.Ricsi.BlockParty.BlockParty;
import hu.Ricsi.BlockParty.FileConfiguration.Config;

public class SendServer {

    public static void sendPlayer(Player p) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        DataOutputStream dataOutputStream = new DataOutputStream(byteArrayOutputStream);
        try {
            dataOutputStream.writeUTF("Connect");
            dataOutputStream.writeUTF(Config.getString("lobby_server"));
            p.sendPluginMessage(BlockParty.getInstance(), "BungeeCord", byteArrayOutputStream.toByteArray());
        }
        catch (IOException iOException) {
            BlockParty.getInstance().getServer().getScheduler().runTaskLater(BlockParty.getInstance(), new Runnable() {
                @Override
                public void run() {
                	sendPlayer(p);
                }
            }, 40);
        }
    }
	
	
    public static void sendAllPlayer() {
        for(Player p : Bukkit.getOnlinePlayers()) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        DataOutputStream dataOutputStream = new DataOutputStream(byteArrayOutputStream);
        try {
            dataOutputStream.writeUTF("Connect");
            dataOutputStream.writeUTF(Config.getString("lobby_server"));
            p.sendPluginMessage(BlockParty.getInstance(), "BungeeCord", byteArrayOutputStream.toByteArray());
        }
        catch (IOException iOException) {
        	BlockParty.getInstance().getServer().getScheduler().runTaskLater(BlockParty.getInstance(), new Runnable() {
                @Override
                public void run() {
                	sendAllPlayer();
                }
            }, 40);
        }
        
        
    }
    }
	
}
