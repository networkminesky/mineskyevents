package net.minesky.utils;

import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import net.md_5.bungee.api.chat.TextComponent;
import net.minesky.MineSkyEvents;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class SendMessages {

    private static void sendPluginMessage(String... data) {
        Player player = Bukkit.getOnlinePlayers().stream().findFirst().orElse(null);
        if (player != null) {
            ByteArrayDataOutput out = ByteStreams.newDataOutput();
            for (String s : data) {
                out.writeUTF(s);
            }
            player.sendPluginMessage(MineSkyEvents.get(), "minesky:proxy", out.toByteArray());
        }
    }

    public static void sendMessageEvent(String mensagem) {
        sendPluginMessage("MessageEvent", mensagem);
    }

    public static void sendMessage(TextComponent mensagem) {
        sendPluginMessage("TextEvents", TextComponent.toLegacyText(mensagem));
    }

    public static void sendMessageColor(String colorHEX, String event, TextComponent mensagem) {
        sendPluginMessage("TextEventsHEX", colorHEX, event, TextComponent.toLegacyText(mensagem));
    }

    public static void sendConnection(Player player) {
        sendPluginMessage("ConectionEvents", player.getName());
    }

    public static void sendPlayerMessageColor(Player player, String colorHEX, String event, TextComponent mensagem) {
        sendPluginMessage("PlayerMessageHEX", player.getName(), colorHEX, event, TextComponent.toLegacyText(mensagem));
    }

    public static void sendPlayerMessage(Player player, TextComponent message) {
        sendPluginMessage("PlayerMessage", player.getName(), TextComponent.toLegacyText(message));
    }

    public static void sendPlayerMoney(Player player, int prêmio) {
        sendPluginMessage("PlayerVault", player.getName(), player.getUniqueId().toString(), String.valueOf(prêmio));
    }
}
