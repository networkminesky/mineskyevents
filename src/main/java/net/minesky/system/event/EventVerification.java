package net.minesky.system.event;

import net.minesky.MineSkyEvents;
import net.minesky.config.DataManager;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import java.util.List;
import java.util.Objects;

public class EventVerification {

    private static final String[] EVENTS = {
            "spleef", "tijolãowars", "tntrun", "corrida", "corridaboat", "sumo", "tnttag", "parapente"
    };

    public static boolean getBlacklist(Player player) {
        String event = MineSkyEvents.event.toLowerCase();
        if (!isValidEvent(event)) return false;

        FileConfiguration config = DataManager.getConfiguration(DataManager.getFile("config.yml"));
        List<String> blacklist = config.getStringList("blacklist." + event + ".list");
        List<String> blacklistIP = config.getStringList("blacklistip." + event + ".list");

        if (blacklist.contains(player.getName()) || blacklistIP.contains(Objects.requireNonNull(player.getAddress()).getAddress().getHostAddress())) {
            notifyPlayers(player, event);
            return true;
        }
        return false;
    }

    private static boolean isValidEvent(String event) {
        for (String validEvent : EVENTS) {
            if (validEvent.equals(event)) return true;
        }
        return false;
    }

    private static void notifyPlayers(Player player, String event) {
        player.sendMessage("§8[§c!§8] §cVocê está na blacklist do evento: " + event);
        for (Player player1 : Bukkit.getOnlinePlayers()) {
            FileConfiguration configfor = DataManager.getConfiguration(DataManager.getFile(player1.getUniqueId().toString(), "playerdata"));
            if (player1.hasPermission("mineskyevents.notify.blacklist") && configfor.getBoolean("Notification")) {
                player.playSound(player, Sound.BLOCK_NOTE_BLOCK_BASS, 1.0f, 1.0f);
                player1.sendMessage("§c" + player.getName() + " §fTentou entrar no evento. §8(§fBlackList§8)");
            }
        }
    }
}
