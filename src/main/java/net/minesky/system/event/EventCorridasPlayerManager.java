package net.minesky.system.event;

import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class EventCorridasPlayerManager {
    private static final List<String> PlayerManager  = new ArrayList<>();

    public static void addPlayer(Player player) {
        PlayerManager.add(player.getName());
    }

    public static void clearPlayerManager() {
        PlayerManager.clear();
    }

    public static int getPlayerCount() {
        return PlayerManager.size();
    }

    public static List<String> getPlayerManager() {
        return PlayerManager;
    }

    public static boolean getPlayer(Player player) {
        return PlayerManager.contains(player.getName());
    }
}
