package minesky.msne.events;

import minesky.msne.MineSkyEvents;
import minesky.msne.utils.Util;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.chat.hover.content.Text;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class TNTRunEvent {
    private static final List<String> mapas = Arrays.asList("Mapa1", "Mapa2");
    public static String selectedMap;
    private static final Random random = new Random();
    public static void iniciarEvento() {
        MineSkyEvents.event = "TNTRun";
        Util.sendMessageBGMSNE("TNTRun");
        selectedMap = selectMapa();
        for (Player player : Bukkit.getOnlinePlayers()) {
            if (!player.hasPermission("mineskyevents.bypass.join")) {
                if (selectedMap.equals("Mapa1")) {
                    Bukkit.dispatchCommand(player, "tntrun join mapa1");
                }
                if (selectedMap.equals("Mapa2")) {
                    Bukkit.dispatchCommand(player, "tntrun join mapa2");
                }
            }
        }
    }
    public static String selectMapa() {
        return mapas.get(random.nextInt(mapas.size()));
    }
}
