package minesky.msne.system.event;

import minesky.msne.commands.EventCommand;
import minesky.msne.config.DataManager;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;

public class EventStopping {
    public static void Files() {
        if (Bukkit.getOnlinePlayers().isEmpty()) return;
        if (Bukkit.getOnlinePlayers().size() == 1) return;
        for (Player p : Bukkit.getOnlinePlayers()) {
            File file = DataManager.getFile(p.getName().toLowerCase(), "playerdata");
            FileConfiguration config = DataManager.getConfiguration(file);
            config.set("Event", false);
            config.set("EventSpect", false);
            try {
                config.save(file);
                EventCommand.RevealPlayer(p);
                Bukkit.getConsoleSender().sendMessage("§8[§6MineSky Events§8] §7Saving player information: " + p.getName());
            } catch (IOException e) {
                Bukkit.getLogger().warning("[PlayerData] Falhou a salvar os arquivos do player " + p.getName());
                e.printStackTrace();
            }
        }
    }
}
