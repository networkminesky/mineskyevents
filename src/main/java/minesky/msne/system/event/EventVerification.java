package minesky.msne.system.event;

import minesky.msne.config.ConfigManager;
import minesky.msne.config.DataManager;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.util.List;

public class EventVerification {
    public static boolean getBlacklist(Player player, String event) {
        switch (event) {
            case "TijolãoWars":
                event = "tijolãowars";
                        break;
            case "TNTRun":
                event = "tntrun";
                break;
            case "Corrida":
                event = "corrida";
                break;
            case "CorridaBoat":
                event = "corridaboat";
                break;
            case "Sumo":
                event = "sumo";
                break;
        }
        FileConfiguration config = ConfigManager.getConfig("config.yml");
        List<String> Blacklist = config.getStringList("blacklist." + event + ".list");
        List<String> BlacklistIP = config.getStringList("blacklistip." + event + ".list");
        if (Blacklist.contains(player.getName()) || BlacklistIP.contains(player.getAddress().getAddress().getHostAddress())) {
            player.sendMessage("§8[§c!§8] §cVocê está na blacklist do evento: " + event);
            for (Player player1 : Bukkit.getOnlinePlayers()) {
                File file = DataManager.getFile(player1.getName().toLowerCase(), "playerdata");
                FileConfiguration configfor = DataManager.getConfiguration(file);
                if (!player1.hasPermission("mineskyevents.notify.blacklist")) return true;
                if (!configfor.getBoolean("Notification")) return true;
                player1.sendMessage("§c§lNotificação §7» §7O jogador §b" + player.getName() + " §7tentou entrar no evento. §8(§7BlackList§8)");
            }
            return true;
        }
        return false;
    }
}
