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

public class TNTRunEvent {
    public static void iniciarEvento() {
        MineSkyEvents.event = "TNTRun";
        Util.sendMessageBGMSNE("TNTRun");
        for (Player player : Bukkit.getOnlinePlayers()) {
            if (!player.hasPermission("mineskyevents.bypass.join")) {
                Bukkit.dispatchCommand(player, "tntrun join mapa1");
            }
        }
    }
}
