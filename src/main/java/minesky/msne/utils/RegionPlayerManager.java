package minesky.msne.utils;

import org.bukkit.entity.Player;

import java.util.HashSet;
import java.util.Set;

public class RegionPlayerManager {
    private static final Set<Player> PlayerManager = new HashSet<>();

    public static void addPlayer(Player player) {
        PlayerManager.add(player);
    }

    public static void removePlayer(Player player) {
        PlayerManager.remove(player);
    }

    public static void clearPlayerManager() {
        PlayerManager.clear();
    }

    public static int getPlayerCount() {
        return PlayerManager.size();
    }

    public static boolean getPlayer(Player player) {
        return PlayerManager.contains(player);
    }
}
