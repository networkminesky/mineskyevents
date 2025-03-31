package net.minesky.discord;

import net.minesky.config.Config;
import net.minesky.system.event.EventsMakers;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.awt.*;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public class EventsMessage {

    public static final String LINK = Config.Link;


    public static void sendLogEvent(String event, Player vencedor, Player[] vencedores, int premio1, int premio2, int premio3) {
        DiscordWebhook webhook = new DiscordWebhook(LINK);
        webhook.setUsername("MineSky Eventos • Logs");
        webhook.setAvatarUrl("https://i.imgur.com/9MeSO5i.png");
        webhook.setTts(false);

        String color, title;
        switch (event) {
            case "Spleef":
                color = "#55FFFF";
                title = "Spleef Event - Logs";
                break;
            case "TijolãoWars":
                color = "#FFAA00";
                title = "TijolãoWars Event - Logs";
                break;
            case "Sumo":
                color = "#AA0000";
                title = "Sumo Event - Logs";
                break;
            case "TNTRun":
                color = "#FF5555";
                title = "TNTRUN Event - Logs";
                break;
            case "TNTTag":
                color = "#FF5555";
                title = "TNT-TAG Event - Logs";
                break;
            default:
                return;
        }

        String description = "- **Informações:**\\n:busts_in_silhouette: - **`Onlines (Serivdor de eventos):`** `" + Bukkit.getOnlinePlayers().size() + "`\\n:hourglass_flowing_sand: - **`Duração:`** `Indisponivel`\\n\\n- **Ganhadores:**\\n:first_place: - **`Primeio lugar:`** `" + vencedor.getName() + "`\\n:second_place: - **`Segundo lugar:`** `" + vencedores[1].getName() + "`\\n:third_place: - **`Terceiro lugar:`** `" + vencedores[0].getName() + "`\\n\\n- **Premiações:**\\n:moneybag: - **`Primeiro lugar:`** `$" + premio1 + "`\\n:money_with_wings: - **`Segundo lugar:`** `$" + premio2 + "`\\n:money_mouth: - **`Terceiro lugar:`** `$" + premio3 + "`\\n\\n- **Feitor por:**\\n:gear: - **`Iniciado`** - `" + EventsMakers.getPlayerStarted().getName() + "`\\n:mechanical_arm: - **`Criadores de eventos (Onlines):`** `" + EventsMakers.getEventsMakersName() + "`\\n\\n:map: - **`Plugin de Eventos automáticos`**";

        webhook.addEmbed(new DiscordWebhook.EmbedObject()
                .setColor(Color.decode(color))
                .setTitle(title)
                .setDescription(description)
                .setThumbnail("https://i.imgur.com/9MeSO5i.png")
                .setFooter("Logs dos Eventos!", "https://i.imgur.com/9MeSO5i.png")
        );

        try {
            webhook.execute();
        } catch (IOException e) {
            Bukkit.getLogger().warning("[BOT DISCORD] Ocorreu um erro para executar o webhook de logs de eventos!\n" + e.getMessage());
            e.printStackTrace();
        }
    }

    public static void sendLogEvent(String event, List<String> playerList, int premio1, int premio2, int premio3) {
        DiscordWebhook webhook = new DiscordWebhook(LINK);
        webhook.setUsername("MineSky Eventos • Logs");
        webhook.setAvatarUrl("https://i.imgur.com/9MeSO5i.png");
        webhook.setTts(false);

        Map<String, String> eventos = Map.of(
                "Corrida", "#FFFF00",
                "CorridaBoat", "#5555FF",
                "Parapente", "#00A7A7"
        );

        if (!eventos.containsKey(event)) return;

        String color = eventos.get(event);
        String title = event + " Event - Logs";


        StringBuilder message = new StringBuilder("- **Ganhadores:**");
        for (int i = 0; i < 3; i++) {
            message.append("\\n:first_place: - **`").append(playerList.get(i)).append("`** - `").append(i + 1).append("º lugar`");
        }

        String description = "- **Informações:**\\n:busts_in_silhouette: - **`Onlines (Serivdor de eventos):`** `" + Bukkit.getOnlinePlayers().size() + "`\\n:hourglass_flowing_sand: - **`Duração:`** `Indisponivel`\\n\\n" + message + "\\n\\n- **Premiações:**\\n:moneybag: - **`Primeiro lugar:`** `$" + premio1 + "`\\n:money_with_wings: - **`Segundo lugar:`** `$" + premio2 + "`\\n:money_mouth: - **`Terceiro lugar:`** `$" + premio3 + "`\\n\\n- **Feitor por:**\\n:gear: - **`Iniciado`** - `" + EventsMakers.getPlayerStarted().getName() + "`\\n:mechanical_arm: - **`Criadores de eventos (Onlines):`** `" + EventsMakers.getEventsMakersName() + "`\\n\\n:map: - **`Plugin de Eventos automáticos`**";

        webhook.addEmbed(new DiscordWebhook.EmbedObject()
                .setColor(Color.decode(color))
                .setTitle(title)
                .setDescription(description)
                .setThumbnail("https://i.imgur.com/9MeSO5i.png")
                .setFooter("Logs dos Eventos!", "https://i.imgur.com/9MeSO5i.png")
        );

        try {
            webhook.execute();
        } catch (IOException e) {
            Bukkit.getLogger().warning("[BOT DISCORD] Erro ao executar o webhook de logs de eventos!\n" + e.getMessage());
            e.printStackTrace();
        }
    }

}
