package minesky.msne.system;


import java.io.File;
import java.io.IOException;

import minesky.msne.config.DataManager;
import minesky.msne.config.Messages;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;


public class PlayerData implements Listener {
    @EventHandler
    public void aoLogar(PlayerLoginEvent e) {
        String newPlayer = e.getPlayer().getName();
        File file = DataManager.getFile(newPlayer.toLowerCase(), "playerdata");
        FileConfiguration config = DataManager.getConfiguration(file);
        if (file.exists() && file.length() > 10L) {
            String oldPlayer = config.getString("Nick");
            if (!newPlayer.equals(oldPlayer)) {
                e.setResult(PlayerLoginEvent.Result.KICK_OTHER);
                e.setKickMessage(Messages.Similar_nickname.replace("%old%", oldPlayer).replace("%new%", newPlayer));
            }
        } else if (e.getResult() == PlayerLoginEvent.Result.ALLOWED) {
            DataManager.createFile(file);
            config.set("Nick", newPlayer);
            config.set("Address", "No Info");
            config.set("Event", false);
            config.set("EventSpect", false);
            config.set("Notification", true);
            config.set("Localizer", "Localizer OFF");
            config.set("Events.Spleef.win", 0);
            config.set("Events.Spleef.dead", 0);
            config.set("Events.Tijolao.win", 0);
            config.set("Events.Tijolao.dead", 0);
            config.set("Events.Corrida.win", 0);
            config.set("Events.CorridaBoat.win", 0);
            config.set("Events.Sumo.win", 0);
            config.set("Events.Sumo.dead", 0);
            config.set("Events.TNTRun.win", 0);
            config.set("Events.TNTRun.dead", 0);
            config.set("Events.TNTTag.win", 0);
            config.set("Events.TNTTag.dead", 0);
            config.set("Events.Parapente.win", 0);
            try {
                config.save(file);
            } catch (IOException ex) {
                Bukkit.getConsoleSender().sendMessage(Messages.Falied_to_save.replace("%file%", file.getName()));
            }
        }
    }
}
