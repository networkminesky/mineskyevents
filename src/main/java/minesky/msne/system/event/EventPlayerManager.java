package minesky.msne.system.event;

import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class EventPlayerManager {
    private static final Set<Player> PlayerManager  = new HashSet<>();
    private static final Map<Player, Boolean> PlayerItem = new HashMap<>();

    public static void addPlayer(Player player) {
        PlayerManager.add(player);
    }

    public static void addPlayerITEM(Player player, Boolean check) {
        PlayerItem.put(player, check);
    }

    public static void removePlayer(Player player) {
        PlayerManager.remove(player);
    }

    public static void clearPlayerManager() {
        PlayerManager.clear();
    }

    public static void clearPlayerItem() {
        PlayerItem.clear();
    }

    public static int getPlayerCount() {
        return PlayerManager.size();
    }

    public static Set<Player> getPlayerManager() {
        return PlayerManager;
    }

    public static Boolean getPlayerCheck(Player player) {
        return PlayerItem.getOrDefault(player, false);
    }
}
