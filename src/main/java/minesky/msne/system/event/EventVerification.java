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
            case "Spleef":
                event = "spleef";
                break;
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
            case "TNTTag":
                event = "tnttag";
                break;
            case "Parapente":
                event = "parapente";
                break;
        }
        File file = DataManager.getFile("config.yml");
        FileConfiguration config = DataManager.getConfiguration(file);
        List<String> Blacklist = config.getStringList("blacklist." + event + ".list");
        List<String> BlacklistIP = config.getStringList("blacklistip." + event + ".list");
        if (Blacklist.contains(player.getName()) || BlacklistIP.contains(player.getAddress().getAddress().getHostAddress())) {
            player.sendMessage("§8[§c!§8] §cVocê está na blacklist do evento: " + event);
            for (Player player1 : Bukkit.getOnlinePlayers()) {
                File filefor = DataManager.getFile(player1.getName().toLowerCase(), "playerdata");
                FileConfiguration configfor = DataManager.getConfiguration(filefor);
                if (player1.hasPermission("mineskyevents.notify.blacklist")) {
                    if (configfor.getBoolean("Notification")) {
                        player1.sendMessage("§c" + player.getName() + " §fTentou entrar no evento. §8(§fBlackList§8)");
                    }
                }
            }
            return true;
        }
        return false;
    }
}
