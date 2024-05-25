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
        webhook.setAvatarUrl("https://cdn.discordapp.com/icons/672661692395814933/85e041ae10b469dcd4de85db0892d35e.png");

        webhook.setTts(false);
        switch (event) {
            case "Spleef":
                webhook.addEmbed(
                        new DiscordWebhook.EmbedObject()
                                .setColor(Color.decode("#55FFFF"))
                                .setTitle("Spleef LOGS EVENT")
                                .setDescription("**Online (Server EVENTOS): **" + Bukkit.getOnlinePlayers().size() + "\\n\\n**Ganhadores:**\\n- **" + vencedor.getName() + "** - 1º Lugar\\n- **" + vencedores[1].getName() + "** - 2º Lugar\\n- **" + vencedores[0].getName() + "** - 3º Lugar\\n\\n**Prêmios:**\\n- **" + premio1 + "** - 1º Lugar\\n- **" + premio2 + "** - 2º Lugar\\n- **" + premio3 + "** - 3º Lugar\\n\\n**Feito por:**\\n- **Plugin MINESKY-EVENTS**")
                                .setThumbnail("https://cdn.discordapp.com/icons/672661692395814933/85e041ae10b469dcd4de85db0892d35e.png")
                                .setFooter("LOG EVENTS (MINESKY NETWORK)", "https://cdn.discordapp.com/icons/672661692395814933/85e041ae10b469dcd4de85db0892d35e.png")
                );
                try {
                    webhook.execute();
                } catch (IOException e) {
                    Bukkit.getLogger().warning("[BOT DISCORD] Ocorreu um erro para executar o webhook de logs de eventos!\n" + e.getMessage());
                }
                break;
            case "TijolãoWars":
                webhook.addEmbed(
                        new DiscordWebhook.EmbedObject()
                                .setColor(Color.decode("#FFAA00"))
                                .setTitle("TijolãoWars LOGS EVENT")
                                .setDescription("**Online (Server EVENTOS): **" + Bukkit.getOnlinePlayers().size() + "\\n\\n**Ganhadores:**\\n- **" + vencedor.getName() + "** - 1º Lugar\\n- **" + vencedores[1].getName() + "** - 2º Lugar\\n- **" + vencedores[0].getName() + "** - 3º Lugar\\n\\n**Prêmios:**\\n- **" + premio1 + "** - 1º Lugar\\n- **" + premio2 + "** - 2º Lugar\\n- **" + premio3 + "** - 3º Lugar\\n\\n**Feito por:**\\n- **Plugin MINESKY-EVENTS**")
                                .setThumbnail("https://cdn.discordapp.com/icons/672661692395814933/85e041ae10b469dcd4de85db0892d35e.png")
                                .setFooter("LOG EVENTS (MINESKY NETWORK)", "https://cdn.discordapp.com/icons/672661692395814933/85e041ae10b469dcd4de85db0892d35e.png")
                );
                try {
                    webhook.execute();
                } catch (IOException e) {
                    Bukkit.getLogger().warning("[BOT DISCORD] Ocorreu um erro para executar o webhook de logs de eventos!\n" + e.getMessage());
                }
                break;
            case "Sumo":
                webhook.addEmbed(
                        new DiscordWebhook.EmbedObject()
                                .setColor(Color.decode("#AA0000"))
                                .setTitle("Sumo LOGS EVENT")
                                .setDescription("**Online (Server EVENTOS): **" + Bukkit.getOnlinePlayers().size() + "\\n\\n**Ganhadores:**\\n- **" + vencedor.getName() + "** - 1º Lugar\\n- **" + vencedores[1].getName() + "** - 2º Lugar\\n- **" + vencedores[0].getName() + "** - 3º Lugar\\n\\n**Prêmios:**\\n- **" + premio1 + "** - 1º Lugar\\n- **" + premio2 + "** - 2º Lugar\\n- **" + premio3 + "** - 3º Lugar\\n\\n**Feito por:**\\n- **Plugin MINESKY-EVENTS**")
                                .setThumbnail("https://cdn.discordapp.com/icons/672661692395814933/85e041ae10b469dcd4de85db0892d35e.png")
                                .setFooter("LOG EVENTS (MINESKY NETWORK)", "https://cdn.discordapp.com/icons/672661692395814933/85e041ae10b469dcd4de85db0892d35e.png")
                );
                try {
                    webhook.execute();
                } catch (IOException e) {
                    Bukkit.getLogger().warning("[BOT DISCORD] Ocorreu um erro para executar o webhook de logs de eventos!\n" + e.getMessage());
                }
                break;
            case "TNTRun":
                webhook.addEmbed(
                        new DiscordWebhook.EmbedObject()
                                .setColor(Color.decode("#FF5555"))
                                .setTitle("TNTRUN LOGS EVENT")
                                .setDescription("**Online (Server EVENTOS): **" + Bukkit.getOnlinePlayers().size() + "\\n\\n**Ganhadores:**\\n- **" + vencedor.getName() + "** - 1º Lugar\\n- **" + vencedores[1].getName() + "** - 2º Lugar\\n- **" + vencedores[0].getName() + "** - 3º Lugar\\n\\n**Prêmios:**\\n- **" + premio1 + "** - 1º Lugar\\n- **" + premio2 + "** - 2º Lugar\\n- **" + premio3 + "** - 3º Lugar\\n\\n**Feito por:**\\n- **Plugin MINESKY-EVENTS**")
                                .setThumbnail("https://cdn.discordapp.com/icons/672661692395814933/85e041ae10b469dcd4de85db0892d35e.png")
                                .setFooter("LOG EVENTS (MINESKY NETWORK)", "https://cdn.discordapp.com/icons/672661692395814933/85e041ae10b469dcd4de85db0892d35e.png")
                );
                try {
                    webhook.execute();
                } catch (IOException e) {
                    Bukkit.getLogger().warning("[BOT DISCORD] Ocorreu um erro para executar o webhook de logs de eventos!\n" + e.getMessage());
                }
                break;
            case "TNTTag":
                webhook.addEmbed(
                        new DiscordWebhook.EmbedObject()
                                .setColor(Color.decode("#FF5555"))
                                .setTitle("TNT-TAG LOGS EVENT")
                                .setDescription("**Online (Server EVENTOS): **" + Bukkit.getOnlinePlayers().size() + "\\n\\n**Ganhadores:**\\n- **" + vencedor.getName() + "** - 1º Lugar\\n- **" + vencedores[1].getName() + "** - 2º Lugar\\n- **" + vencedores[0].getName() + "** - 3º Lugar\\n\\n**Prêmios:**\\n- **" + premio1 + "** - 1º Lugar\\n- **" + premio2 + "** - 2º Lugar\\n- **" + premio3 + "** - 3º Lugar\\n\\n**Feito por:**\\n- **Plugin MINESKY-EVENTS**")
                                .setThumbnail("https://cdn.discordapp.com/icons/672661692395814933/85e041ae10b469dcd4de85db0892d35e.png")
                                .setFooter("LOG EVENTS (MINESKY NETWORK)", "https://cdn.discordapp.com/icons/672661692395814933/85e041ae10b469dcd4de85db0892d35e.png")
                );
                try {
                    webhook.execute();
                } catch (IOException e) {
                    Bukkit.getLogger().warning("[BOT DISCORD] Ocorreu um erro para executar o webhook de logs de eventos!\n" + e.getMessage());
                }
                break;
        }
    }

    public static void sendLogEventPLIST(String event, List<String> playerList, int premio1, int premio2, int premio3) {
        DiscordWebhook webhook = new DiscordWebhook(LINK);
        webhook.setUsername("MineSky Eventos • LOGS");
        webhook.setAvatarUrl("https://cdn.discordapp.com/icons/672661692395814933/85e041ae10b469dcd4de85db0892d35e.png");

        webhook.setTts(false);
        switch (event) {
            case "Corrida":
                StringBuilder messagec = new StringBuilder("**Ganhadores:**");
                for (int i = 0; i < 3; i++) {
                    messagec.append("\\n**").append(playerList.get(i)).append("** - ").append(i + 1).append("º lugar");
                }
                webhook.addEmbed(
                        new DiscordWebhook.EmbedObject()
                                .setColor(Color.YELLOW)
                                .setTitle("Corrida LOGS EVENT")
                                .setDescription("**Online (Server EVENTOS): **" + Bukkit.getOnlinePlayers().size() + "\\n\\n" + messagec + "\\n\\n**Prêmios:**\\n- **" + premio1 + "** - 1º Lugar\\n- **" + premio2 + "** - 2º Lugar\\n- **" + premio3 + "** - 3º Lugar\\n\\n**Feito por:**\\n- **Plugin MINESKY-EVENTS**")
                                .setThumbnail("https://cdn.discordapp.com/icons/672661692395814933/85e041ae10b469dcd4de85db0892d35e.png")
                                .setFooter("LOG EVENTS (MINESKY NETWORK)", "https://cdn.discordapp.com/icons/672661692395814933/85e041ae10b469dcd4de85db0892d35e.png")

                );
                try {
                    webhook.execute();
                } catch (IOException e) {
                    Bukkit.getLogger().warning("[BOT DISCORD] Ocorreu um erro para executar o webhook de logs de eventos!\n" + e.getMessage());
                    e.printStackTrace();
                }
                break;
            case "CorridaBoat":
                StringBuilder messagecb = new StringBuilder("**Ganhadores:**");
                for (int i = 0; i < 3; i++) {
                    messagecb.append("\\n**").append(playerList.get(i)).append("** - ").append(i + 1).append("º lugar");
                }
                webhook.addEmbed(
                        new DiscordWebhook.EmbedObject()
                                .setColor(Color.decode("#5555FF"))
                                .setTitle("Corrida de barco LOGS EVENT")
                                .setDescription("**Online (Server EVENTOS): **" + Bukkit.getOnlinePlayers().size() + "\\n\\n" + messagecb + "\\n\\n**Prêmios:**\\n- **" + premio1 + "** - 1º Lugar\\n- **" + premio2 + "** - 2º Lugar\\n- **" + premio3 + "** - 3º Lugar\\n\\n**Feito por:**\\n- **Plugin MINESKY-EVENTS**")
                                .setThumbnail("https://cdn.discordapp.com/icons/672661692395814933/85e041ae10b469dcd4de85db0892d35e.png")
                                .setFooter("LOG EVENTS (MINESKY NETWORK)", "https://cdn.discordapp.com/icons/672661692395814933/85e041ae10b469dcd4de85db0892d35e.png")
                );
                try {
                    webhook.execute();
                } catch (IOException e) {
                    Bukkit.getLogger().warning("[BOT DISCORD] Ocorreu um erro para executar o webhook de logs de eventos!\n" + e.getMessage());
                }
                break;
            case "Parapente":
                StringBuilder messagep = new StringBuilder("**Ganhadores:**");
                for (int i = 0; i < 3; i++) {
                    messagep.append("\\n**").append(playerList.get(i)).append("** - ").append(i + 1).append("º lugar");
                }
                webhook.addEmbed(
                        new DiscordWebhook.EmbedObject()
                                .setColor(Color.decode("#00A7A7"))
                                .setTitle("Corrida de Parapente LOGS EVENT")
                                .setDescription("**Online (Server EVENTOS): **" + Bukkit.getOnlinePlayers().size() + "\\n\\n" + messagep + "\\n\\n**Prêmios:**\\n- **" + premio1 + "** - 1º Lugar\\n- **" + premio2 + "** - 2º Lugar\\n- **" + premio3 + "** - 3º Lugar\\n\\n**Feito por:**\\n- **Plugin MINESKY-EVENTS**")
                                .setThumbnail("https://cdn.discordapp.com/icons/672661692395814933/85e041ae10b469dcd4de85db0892d35e.png")
                                .setFooter("LOG EVENTS (MINESKY NETWORK)", "https://cdn.discordapp.com/icons/672661692395814933/85e041ae10b469dcd4de85db0892d35e.png")

                );
                try {
                    webhook.execute();
                } catch (IOException e) {
                    Bukkit.getLogger().warning("[BOT DISCORD] Ocorreu um erro para executar o webhook de logs de eventos!\n" + e.getMessage());
                    e.printStackTrace();
                }
                break;
        }
    }
}
