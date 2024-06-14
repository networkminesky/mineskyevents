package minesky.msne.utils;

import com.google.common.collect.Iterables;
import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import minesky.msne.MineSkyEvents;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class SendMessages {
    public static void sendMessageBGMSNE(String mensagem) {
        ByteArrayDataOutput out = ByteStreams.newDataOutput();
        out.writeUTF("MessageEvent");
        out.writeUTF(mensagem);

        Player player = Iterables.getFirst(Bukkit.getOnlinePlayers(), null);
        if (player != null) {
            player.sendPluginMessage(MineSkyEvents.get(), "minesky:proxy", out.toByteArray());
        }
    }

    public static void sendMessageBCMSNE(TextComponent mensagem) {
        ByteArrayDataOutput out = ByteStreams.newDataOutput();
        out.writeUTF("TextEvents");
        out.writeUTF(TextComponent.toLegacyText(mensagem));

        Player player = Iterables.getFirst(Bukkit.getOnlinePlayers(), null);
        if (player != null) {
            player.sendPluginMessage(MineSkyEvents.get(), "minesky:proxy", out.toByteArray());
        }
    }

    public static void sendMessageCHMSNE(String ColorHEX, String Event,TextComponent mensagem) {
        ByteArrayDataOutput out = ByteStreams.newDataOutput();
        out.writeUTF("TextEventsHEX");
        out.writeUTF(ColorHEX);
        out.writeUTF(Event);
        out.writeUTF(TextComponent.toLegacyText(mensagem));

        Player player = Iterables.getFirst(Bukkit.getOnlinePlayers(), null);
        if (player != null) {
            player.sendPluginMessage(MineSkyEvents.get(), "minesky:proxy", out.toByteArray());
        }
    }

    public static void sendConectionBCMSNE(Player player) {
        ByteArrayDataOutput out = ByteStreams.newDataOutput();
        out.writeUTF("ConectionEvents");
        out.writeUTF(player.getName());

        player.sendPluginMessage(MineSkyEvents.get(), "minesky:proxy", out.toByteArray());
    }

    public static void sendPlayerCHMSNE(Player player, String ColorHEX, String Event,TextComponent mensagem) {
        ByteArrayDataOutput out = ByteStreams.newDataOutput();
        out.writeUTF("PlayerMessageHEX");
        out.writeUTF(player.getName());
        out.writeUTF(ColorHEX);
        out.writeUTF(Event);
        out.writeUTF(TextComponent.toLegacyText(mensagem));

        player.sendPluginMessage(MineSkyEvents.get(), "minesky:proxy", out.toByteArray());
    }

    public static void sendPlayermessage(Player player, TextComponent message) {
        ByteArrayDataOutput out = ByteStreams.newDataOutput();
        out.writeUTF("PlayerMessage");
        out.writeUTF(player.getName());
        out.writeUTF(TextComponent.toLegacyText(message));
        player.sendPluginMessage(MineSkyEvents.get(), "minesky:proxy", out.toByteArray());
    }
}
