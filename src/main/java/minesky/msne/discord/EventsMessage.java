package minesky.msne.discord;

import minesky.msne.config.Config;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.awt.*;
import java.io.IOException;
import java.util.List;

public class EventsMessage {

    public static final String LINK = Config.Link;


    public static void sendLogEvent(String event, Player vencedor, Player[] vencedores, int premio1, int premio2, int premio3) {
        DiscordWebhook webhook = new DiscordWebhook(LINK);
        webhook.setUsername("MineSky Eventos • LOGS");
        webhook.setAvatarUrl("https://cdn.discordapp.com/attachments/860609150321164302/1239705035018080377/a_3d432ead7c55544e63d6262aba3169e6.png?ex=6643e4c8&is=66429348&hm=06ebe1a636805a316d61f1bc289ff3db4ad0a04b98b66bc0a2ba60d373a17819&");

        switch (event) {
            case "TijolãoWars":
                DiscordWebhook.EmbedObject embedt = new DiscordWebhook.EmbedObject();
                embedt.setColor(Color.decode("#FFAA00"));
                embedt.setTitle("TijolãoWars LOGS EVENT");
                embedt.setDescription("**Online (Server EVENTOS): **" + Bukkit.getOnlinePlayers().size() + "\n\n**Ganhadores:**\n" + vencedor.getName() + " - 1º Lugar\n" + vencedores[1].getName() + " - 2º Lugar\n" + vencedores[0].getName() + " - 3º Lugar\n\n**Prêmios:**\n- **" + premio1 + "** - 1º Lugar\n- **" + premio2 + "** - 2º Lugar\n- **" + premio3 + "** - 3º Lugar\n\n**Feito por:**\n- **Plugin MINESKY-EVENTS**");
                embedt.setFooter("LOG EVENTS (MINESKY NETWORK)", "https://cdn.discordapp.com/icons/672661692395814933/85e041ae10b469dcd4de85db0892d35e.png");
                embedt.setThumbnail("https://cdn.discordapp.com/attachments/860609150321164302/1237218921971580978/TIJOlAOWARS.png?ex=663ad969&is=663987e9&hm=79be173217f24409bf6e2711f9b2f25bd8ad9491bfce9c0d1f8e14221e67ba8e&");
                webhook.addEmbed(embedt);
                try {
                    webhook.execute();
                } catch (IOException e) {
                    Bukkit.getLogger().warning("[BOT DISCORD] Ocorreu um erro para executar o webhook de logs de eventos!");
                }
                break;
            case "Sumo":
                DiscordWebhook.EmbedObject embeds = new DiscordWebhook.EmbedObject();
                embeds.setColor(Color.decode("#AA0000"));
                embeds.setTitle("Sumo LOGS EVENT");
                embeds.setDescription("**Online (Server EVENTOS): **" + Bukkit.getOnlinePlayers().size() + "\n\n**Ganhadores:**\n" + vencedor.getName() + " - 1º Lugar\n" + vencedores[1].getName() + " - 2º Lugar\n" + vencedores[0].getName() + " - 3º Lugar\n\n**Prêmios:**\n- **" + premio1 + "** - 1º Lugar\n- **" + premio2 + "** - 2º Lugar\n- **" + premio3 + "** - 3º Lugar\n\n**Feito por:**\n- **Plugin MINESKY-EVENTS**");
                embeds.setFooter("LOG EVENTS (MINESKY NETWORK)", "https://cdn.discordapp.com/icons/672661692395814933/85e041ae10b469dcd4de85db0892d35e.png");
                embeds.setThumbnail("https://cdn.discordapp.com/attachments/860609150321164302/1237218921971580978/TIJOlAOWARS.png?ex=663ad969&is=663987e9&hm=79be173217f24409bf6e2711f9b2f25bd8ad9491bfce9c0d1f8e14221e67ba8e&");
                webhook.addEmbed(embeds);
                try {
                    webhook.execute();
                } catch (IOException e) {
                    Bukkit.getLogger().warning("[BOT DISCORD] Ocorreu um erro para executar o webhook de logs de eventos!");
                }
                break;
        }
    }

    public static void sendLogEventPLIST(String event, List<String> playerList, int premio1, int premio2, int premio3) {
        DiscordWebhook webhook = new DiscordWebhook(LINK);
        webhook.setUsername("MineSky Eventos • LOGS");
        webhook.setAvatarUrl("https://cdn.discordapp.com/attachments/860609150321164302/1239705035018080377/a_3d432ead7c55544e63d6262aba3169e6.png?ex=6643e4c8&is=66429348&hm=06ebe1a636805a316d61f1bc289ff3db4ad0a04b98b66bc0a2ba60d373a17819&");

        switch (event) {
            case "Corrida":
                DiscordWebhook.EmbedObject embedc = new DiscordWebhook.EmbedObject();
                String messagec = "**Ganhadores:**";
                for (int i = 0; i < 3; i++) {
                    messagec += "\n**" + playerList.get(i) + "** - " + (i + 1) + "º lugar";
                    embedc.setColor(Color.YELLOW);
                    embedc.setTitle("Corrida LOGS EVENT");
                    embedc.setDescription("**Online (Server EVENTOS): **" + Bukkit.getOnlinePlayers().size() + "\n\n" + messagec + "\n\n**Prêmios:**\n**" + premio1 + "** - 1º Lugar\n**" + premio2 + "** - 2º Lugar\n**" + premio3 + "** - 3º Lugar\n\n**Feito por:**\n- **Plugin MINESKY-EVENTS**");
                    embedc.setFooter("LOG EVENTS (MINESKY NETWORK)", "https://cdn.discordapp.com/icons/672661692395814933/85e041ae10b469dcd4de85db0892d35e.png");
                    embedc.setThumbnail("https://cdn.discordapp.com/attachments/860609150321164302/1237218921489367161/TIJOlAOWARS_2.png?ex=663b8229&is=663a30a9&hm=974f3e565600d526e52ff6ea398cb08fb3b9172611543706f0842a0d57ab1f6e&");
                    webhook.addEmbed(embedc);
                    try {
                        webhook.execute();
                    } catch (IOException e) {
                        Bukkit.getLogger().warning("[BOT DISCORD] Ocorreu um erro para executar o webhook de logs de eventos!");
                    }
                }
                break;
            case "CorridaBoat":
                DiscordWebhook.EmbedObject embedcb = new DiscordWebhook.EmbedObject();
                String messagecb = "**Ganhadores:**";
                for (int i = 0; i < 3; i++) {
                    messagecb += "\n**" + playerList.get(i) + "** - " + (i + 1) + "º lugar";
                    embedcb.setColor(Color.decode("#5555FF"));
                    embedcb.setTitle("Corrida LOGS EVENT");
                    embedcb.setDescription("**Online (Server EVENTOS): **" + Bukkit.getOnlinePlayers().size() + "\n\n" + messagecb + "\n\n**Prêmios:**\n**" + premio1 + "** - 1º Lugar\n**" + premio2 + "** - 2º Lugar\n**" + premio3 + "** - 3º Lugar\n\n**Feito por:**\n- **Plugin MINESKY-EVENTS**");
                    embedcb.setFooter("LOG EVENTS (MINESKY NETWORK)", "https://cdn.discordapp.com/icons/672661692395814933/85e041ae10b469dcd4de85db0892d35e.png");
                    embedcb.setThumbnail("https://cdn.discordapp.com/attachments/860609150321164302/1237218921489367161/TIJOlAOWARS_2.png?ex=663b8229&is=663a30a9&hm=974f3e565600d526e52ff6ea398cb08fb3b9172611543706f0842a0d57ab1f6e&");
                    webhook.addEmbed(embedcb);
                    try {
                        webhook.execute();
                    } catch (IOException e) {
                        Bukkit.getLogger().warning("[BOT DISCORD] Ocorreu um erro para executar o webhook de logs de eventos!");
                    }
                }
                break;
        }
    }
}
