package net.minesky.system;

import net.minesky.MineSkyEvents;
import net.minesky.config.DataManager;
import net.minesky.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;

import java.io.File;
import java.io.IOException;

public class PlayerData implements Listener {

    @EventHandler
    public void aoLogar(PlayerLoginEvent e) {
        String newPlayer = e.getPlayer().getUniqueId().toString();
        File file = DataManager.getFile(newPlayer, "playerdata");
        FileConfiguration config = DataManager.getConfiguration(file);

        if (file.exists() && file.length() > 10L) {
            String oldPlayer = config.getString("Nick");
            String oldPlayerUUID = config.getString("UUID");
            if (e.getPlayer().getName().equals(oldPlayer)) {
                if (!e.getPlayer().getUniqueId().toString().equals(oldPlayerUUID)) {
                    e.setResult(PlayerLoginEvent.Result.KICK_OTHER);
                    String similar = "&cO nick %nick% não pode ser usado pois já existe um cadastro com o UUID %old%";
                    e.setKickMessage(Utils.color(similar.replace("%old%", oldPlayerUUID).replace("%nick%", oldPlayer)));
                }
            }
        } else if (e.getResult() == PlayerLoginEvent.Result.ALLOWED) {
            initializePlayerData(file, config, newPlayer, e.getPlayer());
        }
    }

    public static void initializePlayerData(File file, FileConfiguration config, String newPlayer, Player player) {
        DataManager.createFile(file);
        config.set("Nick", player.getName());
        config.set("UUID", newPlayer);
        config.set("Event", false);
        config.set("Spectator", false);
        config.set("Notification", true);

        // Set all event wins and deaths to 0
        String[] events = {"Spleef", "TijolãoWars", "Corrida", "CorridaBoat", "Sumo", "TNTRun", "TNTTag", "Parapente"};
        for (String event : events) {
            config.set("Events." + event + ".win", 0);
            config.set("Events." + event + ".dead", 0);
        }

        try {
            config.save(file);
        } catch (IOException ex) {
            String falied = "&cNão foi possível salvar o arquivo %file%";
            MineSkyEvents.l.severe(Utils.color(falied));
        }
    }
}
