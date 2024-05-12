package minesky.msne.bot;

import minesky.msne.MineSkyEvents;
import minesky.msne.config.Config;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.awt.*;
import java.time.Instant;
import java.util.List;

public class MineSkyBot {
    public static MessageEmbed embedm;
    public static void sendLogEvent(String event, Player vencedor, Player[] vencedores, double premio1, double premio2, double premio3) {
        if (Config.Bot) {
            TextChannel channel = MineSkyEvents.jda.getTextChannelById(Config.ChannelLog);
            if (channel != null) {
                switch (event) {
                    case "TijolãoWars":
                        EmbedBuilder embed = new EmbedBuilder();
                        embed.setColor(Color.decode("#FFAA00"));
                        embed.setTitle("TijolãoWars LOGS EVENT");
                        embed.setDescription("**Online (Server EVENTOS): **" + Bukkit.getOnlinePlayers().size() + "\n\n**Ganhadores:**\n" + vencedor.getName() + " - 1º Lugar\n" + vencedores[0].getName() + " - 2º Lugar\n" + vencedores[1].getName() + " - 3º Lugar\n\n**Prêmios:**\n**" + premio1 + "** - 1º Lugar\n**" + premio2 + "** - 2º Lugar\n**" + premio3 + "** - 3º Lugar\n\n**Feito por:**\n- **Plugin MINESKY-EVENTS**");
                        embed.setFooter("LOG EVENTS (MINESKY NETWORK)", channel.getGuild().getIconUrl());
                        embed.setThumbnail("https://cdn.discordapp.com/attachments/860609150321164302/1237218921971580978/TIJOlAOWARS.png?ex=663ad969&is=663987e9&hm=79be173217f24409bf6e2711f9b2f25bd8ad9491bfce9c0d1f8e14221e67ba8e&");
                        embed.setTimestamp(Instant.now());
                        MessageEmbed embedm = embed.build();
                        channel.sendMessageEmbeds(embedm).queue();
                        break;
                    case "Sumo":
                        EmbedBuilder embeds = new EmbedBuilder();
                        embeds.setColor(Color.decode("#AA0000"));
                        embeds.setTitle("Sumo LOGS EVENT");
                        embeds.setDescription("**Online (Server EVENTOS): **" + Bukkit.getOnlinePlayers().size() + "\n\n**Ganhadores:**\n" + vencedor.getName() + " - 1º Lugar\n" + vencedores[0].getName() + " - 2º Lugar\n" + vencedores[1].getName() + " - 3º Lugar\n\n**Prêmios:**\n**" + premio1 + "** - 1º Lugar\n**" + premio2 + "** - 2º Lugar\n**" + premio3 + "** - 3º Lugar\n\n**Feito por:**\n- **Plugin MINESKY-EVENTS**");
                        embeds.setFooter("LOG EVENTS (MINESKY NETWORK)", channel.getGuild().getIconUrl());
                        embeds.setThumbnail("https://cdn.discordapp.com/attachments/860609150321164302/1237218921971580978/TIJOlAOWARS.png?ex=663ad969&is=663987e9&hm=79be173217f24409bf6e2711f9b2f25bd8ad9491bfce9c0d1f8e14221e67ba8e&");
                        embeds.setTimestamp(Instant.now());
                        MessageEmbed embedsm = embeds.build();
                        channel.sendMessageEmbeds(embedsm).queue();
                        break;
                }
            } else {
                Bukkit.getLogger().warning("[BOT DISCORD] Ocorreu um erro no canal de logs de events!");
            }
        } else {
            Bukkit.getLogger().warning("[BOT DISCORD] Está com falhas!");
        }
    }
    public static void sendLogEventPLIST(String event, List<String> playerList, int premio1, int premio2, int premio3) {
        if (Config.Bot) {
            TextChannel channel = MineSkyEvents.jda.getTextChannelById(Config.ChannelLog);
            if (channel != null) {
                switch (event) {
                    case "Corrida":
                        String message = "**Ganhadores:**";
                        for (int i = 0; i < 3; i++) {
                            message += "\n**" + playerList.get(i) + "** - " + (i + 1) + "º lugar";
                            EmbedBuilder embed = new EmbedBuilder();
                            embed.setColor(Color.YELLOW);
                            embed.setTitle("Corrida LOGS EVENT");
                            embed.setDescription("**Online (Server EVENTOS): **" + Bukkit.getOnlinePlayers().size() + "\n\n" + message + "\n\n**Prêmios:**\n**" + premio1 + "** - 1º Lugar\n**" + premio2 + "** - 2º Lugar\n**" + premio3 + "** - 3º Lugar\n\n**Feito por:**\n- **Plugin MINESKY-EVENTS**");
                            embed.setFooter("LOG EVENTS (MINESKY NETWORK)", channel.getGuild().getIconUrl());
                            embed.setThumbnail("https://cdn.discordapp.com/attachments/860609150321164302/1237218921489367161/TIJOlAOWARS_2.png?ex=663b8229&is=663a30a9&hm=974f3e565600d526e52ff6ea398cb08fb3b9172611543706f0842a0d57ab1f6e&");
                            embed.setTimestamp(Instant.now());
                            embedm = embed.build();
                        }
                        channel.sendMessageEmbeds(embedm).queue();
                        break;
                    case "CorridaBoat":
                        String messageb = "**Ganhadores:**";
                        for (int i = 0; i < 3; i++) {
                            messageb += "\n- **" + playerList.get(i) + "** - " + (i + 1) + "º lugar";
                            EmbedBuilder embed = new EmbedBuilder();
                            embed.setColor(Color.decode("#5555FF"));
                            embed.setTitle("Corrida de barco LOGS EVENT");
                            embed.setDescription("**Online (Server EVENTOS): **" + Bukkit.getOnlinePlayers().size() + "\n\n" + messageb + "\n\n**Prêmios:**\n- **" + premio1 + "** - 1º Lugar\n- **" + premio2 + "** - 2º Lugar\n- **" + premio3 + "** - 3º Lugar\n\n**Feito por:**\n- **Plugin MINESKY-EVENTS**");
                            embed.setFooter("LOG EVENTS (MINESKY NETWORK)", channel.getGuild().getIconUrl());
                            embed.setThumbnail("https://cdn.discordapp.com/attachments/860609150321164302/1237218921489367161/TIJOlAOWARS_2.png?ex=663b8229&is=663a30a9&hm=974f3e565600d526e52ff6ea398cb08fb3b9172611543706f0842a0d57ab1f6e&");
                            embed.setTimestamp(Instant.now());
                            embedm = embed.build();
                        }
                        channel.sendMessageEmbeds(embedm).queue();
                        break;
                }
            } else {
                Bukkit.getLogger().warning("[BOT DISCORD] Ocorreu um erro no canal de logs de events!");
            }
        } else {
            Bukkit.getLogger().warning("[BOT DISCORD] Está com falhas!");
        }
    }
}
