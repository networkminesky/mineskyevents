package minesky.msne.utils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import minesky.msne.config.DataManager;
import minesky.msne.config.Messages;
import org.bukkit.*;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;

public class Util {

    public static String serializeLocation(Location l) {
        return Objects.requireNonNull(l.getWorld()).getName() + ',' +
                l.getX() + ',' +
                l.getY() + ',' +
                l.getZ() + ',' +
                l.getYaw() + ',' +
                l.getPitch();
    }
    public static Location deserializeLocation(String s) {
        String[] location = s.split(",");
        return new Location(
                Bukkit.getWorld(location[0]),
                Double.parseDouble(location[1]),
                Double.parseDouble(location[2]),
                Double.parseDouble(location[3]),
                Float.parseFloat(location[4]),
                Float.parseFloat(location[5]));
    }

    public static String Color(String s) {
        return ChatColor.translateAlternateColorCodes('&', s);
    }

    public static void InfoC(String s) {
        Bukkit.getConsoleSender().sendMessage(Color(PrefixINFO() + " &c" + s));
    }

    public static FileConfiguration Playerdata(Player player) {
        String newPlayer = player.getName();
        File file = DataManager.getFile(newPlayer.toLowerCase(), "playerdata");
        FileConfiguration config = DataManager.getConfiguration(file);
        if (!file.exists()) {
            send(player, Messages.Player_exist);
        }
        return config;
    }

    public static File PlayerDataF(Player player) {
        String newPlayer = player.getName();
        File file = DataManager.getFile(newPlayer.toLowerCase(), "playerdata");
        if (!file.exists()) {
            send(player, Messages.Player_exist);
        }
        return file;
    }

    public static boolean PDVE(Player p) {
        return Playerdata(p).getBoolean("Event", true);
    }

    public static boolean PDVES(Player p) {
        return Playerdata(p).getBoolean("EventSpect", true);
    }

    public static List<String> PVE() {
        List<String> playersWithEvent = new ArrayList<>();
        for (Player player : Bukkit.getOnlinePlayers()) {
            File file = DataManager.getFile(player.getName().toLowerCase(), "playerdata");
            FileConfiguration config = DataManager.getConfiguration(file);
           if (config.getBoolean("Event", true)) {
               playersWithEvent.add(player.getName());
               return playersWithEvent;
           }
        }
        return playersWithEvent;
    }

    public static List<String> getOnlinePlayerNames() {
        List<String> onlinePlayerNames = new ArrayList<>();
        for (Player onlinePlayer : Bukkit.getOnlinePlayers()) {
            onlinePlayerNames.add(onlinePlayer.getName());
        }
        return onlinePlayerNames;
    }


    public static void send(Player player, String s) {
        player.sendMessage(Color(Prefix() + " &8» &c" + s));
    }

    public static String Prefix() {
        return "§6§lMineSky Events";
    }

    public static String PrefixINFO() {
        return "§8[§6MineSky Events§8]";
    }

}
