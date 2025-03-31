package net.minesky.system.event;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class EventsMakers {
    private static Player PlayerStarted;
    public static List<String> getEventsMakers() {
        List<String> eventsMakers = new ArrayList<>();
        for (Player p : Bukkit.getOnlinePlayers()) {
            if (p.hasPermission("mineskyevents.command.event.start")) eventsMakers.add(p.getName());
        }
        return eventsMakers;
    }

    public static String getEventsMakersName() {
        return getEventsMakers().toString().replace("[", "").replace("]", "");
    }

    public static void setPlayerStarted(Player p) {
        PlayerStarted = p;
    }
    public static Player getPlayerStarted() {
        return PlayerStarted;
    }
}
