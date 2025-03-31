package net.minesky.utils;

import net.minesky.config.DataManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class Utils {

    public static String serializeLocation(Location l) {
        return Objects.requireNonNull(l.getWorld()).getName() + ',' +
                l.getX() + ',' +
                l.getY() + ',' +
                l.getZ() + ',' +
                l.getYaw() + ',' +
                l.getPitch();
    }
    public static Location deserializeLocation(String s) {
        String[] loc = s.split(",");
        return new Location(Bukkit.getWorld(loc[0]),
                Double.parseDouble(loc[1]),
                Double.parseDouble(loc[2]),
                Double.parseDouble(loc[3]),
                Float.parseFloat(loc[4]),
                Float.parseFloat(loc[5]));
    }

    public static String color(String s) {
        return ChatColor.translateAlternateColorCodes('&', s);
    }

    public static File getPlayerDataFile(Player player) {
        return DataManager.getFile(player.getUniqueId().toString(), "playerdata");
    }

    public static FileConfiguration getPlayerData(Player player) {
        File file = getPlayerDataFile(player);
        if (!file.exists()) send(player, "&cVocê não pode user esse comando.");
        return DataManager.getConfiguration(file);
    }

    public static boolean isPlayerInEvent(Player player) {
        return getPlayerData(player).getBoolean("Event", true);
    }

    public static boolean isPlayerSpectating(Player player) {
        return getPlayerData(player).getBoolean("Spectator", true);
    }

    public static List<String> getPlayersInEvent() {
        return Bukkit.getOnlinePlayers().stream()
                .filter(Utils::isPlayerInEvent)
                .map(Player::getName)
                .collect(Collectors.toList());
    }

    public static List<String> getOnlinePlayerNames() {
        return Bukkit.getOnlinePlayers().stream()
                .map(Player::getName)
                .collect(Collectors.toList());
    }

    public static String getMinecraftVersion() {
        try {
            String info = Bukkit.getVersion();
            return info.split("MC: ")[1].split("\\)")[0];
        } catch (Throwable e) {
            return "Desconhecida";
        }
    }

    public static void send(Player player, String message) {
        player.sendMessage(color(prefix() + " &c" + message));
    }

    public static String prefix() {
        return "§6§lMineSky Events";
    }

    public static String prefixInfo() {
        return "[MineSkyEvents]";
    }
}
