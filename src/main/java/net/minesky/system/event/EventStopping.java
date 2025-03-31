package net.minesky.system.event;

import net.minesky.MineSkyEvents;
import net.minesky.commands.EventCommand;
import net.minesky.config.DataManager;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;

import java.io.File;
import java.io.IOException;

public class EventStopping {
    public static void savePlayerData() {
        Bukkit.getOnlinePlayers().forEach(player -> {
            File file = DataManager.getFile(player.getUniqueId().toString(), "playerdata");
            FileConfiguration config = DataManager.getConfiguration(file);

            config.set("Event", false);
            config.set("Spectator", false);

            try {
                config.save(file);
                EventCommand.RevealPlayer(player);
                MineSkyEvents.l.info("Salvando playerdata: " + player.getName());
            } catch (IOException e) {
                MineSkyEvents.l.severe("[PlayerData] Falhou ao salvar a playerdata de " + player.getName());
                e.printStackTrace();
            }
        });
    }
}
