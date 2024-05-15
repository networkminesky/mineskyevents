package minesky.msne.system.event;

import minesky.msne.config.DataManager;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;

public class EventStopping {
    public static void Files() {
        for (Player p : Bukkit.getOnlinePlayers()) {
            File file = DataManager.getFile(p.getName().toLowerCase(), "playerdata");
            FileConfiguration config = DataManager.getConfiguration(file);
            config.set("Event", false);
            config.set("EventSpect", false);
            try {
                config.save(file);
            } catch (IOException e) {
                Bukkit.getLogger().warning("[PlayerData] Falhou a salvar os arquivos do player " + p.getName());
                e.printStackTrace();
            }
        }
    }
}
