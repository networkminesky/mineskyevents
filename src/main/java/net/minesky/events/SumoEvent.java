package net.minesky.events;

import net.md_5.bungee.api.chat.TextComponent;
import net.minesky.MineSkyEvents;
import net.minesky.commands.EventCommand;
import net.minesky.config.Config;
import net.minesky.config.DataManager;
import net.minesky.discord.EventsMessage;
import net.minesky.hooks.Vault;
import net.minesky.system.event.EventPlayerManager;
import net.minesky.utils.EventData;
import net.minesky.utils.EventItem;
import net.minesky.utils.SendMessages;
import net.minesky.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.bukkit.scheduler.BukkitRunnable;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class SumoEvent {
    public static boolean contagem, contagemI = false;
    public static List<Player> mortos = new ArrayList<>();
    private static Map<Location, Material> blocksbreak = new HashMap<>();
    public static BukkitRunnable temporizador, contagemtemp;

    public static void iniciarEvento() {
        MineSkyEvents.event = "Sumo";
        SendMessages.sendMessageEvent("Sumo");
        temporizador = new BukkitRunnable() {
            int tempoRestante = 600;
            @Override
            public void run() {
                if (tempoRestante == 0) {
                    encerrarEvento();
                }
                tempoRestante--;
            }
        };
        temporizador.runTaskTimer(MineSkyEvents.get(), 0, 20);
        Bukkit.getOnlinePlayers().forEach(player -> {
            if (!player.hasPermission("mineskyevents.bypass.join")) {
                Bukkit.dispatchCommand(player, "event entrar");
            }
        });
    }

    private static void encerrarEvento() {
        MineSkyEvents.event = "OFF";
        Bukkit.getOnlinePlayers().forEach(player -> {
            if (Utils.isPlayerInEvent(player) || Utils.isPlayerSpectating(player)) {
                if (!player.hasPermission("mineskyevents.bypass.join")) SendMessages.sendConnection(player);
                salvarConfig(player);
            }
        });
        SendMessages.sendMessage(new TextComponent("§4§lSumo §8| §aInfelizmente o evento não teve §l4§a players para iniciar."));
    }

    private static void salvarConfig(Player player) {
        File file = DataManager.getFile(player.getUniqueId().toString(), "playerdata");
        FileConfiguration config = DataManager.getConfiguration(file);
        config.set("Event", false);
        config.set("Spectator", false);
        try {
            config.save(file);
        } catch (IOException e) {
            Bukkit.getLogger().warning("[PlayerData] Falhou a salvar os arquivos do player " + player.getName());
        }
    }

    public static void comtagemEvento() {
        if (!contagemI && EventPlayerManager.getPlayerCount() >= 4 || EventData.ForceStart) {
            temporizador.cancel();
            iniciarContagem();
        }
    }

    private static void iniciarContagem() {
        contagemtemp = new BukkitRunnable() {
            int tempoRestante = 180;
            @Override
            public void run() {
                contagemI = true;
                contagem = true;
                if (tempoRestante == 0 || EventData.ForceSkip) {
                    finalizarContagem();
                } else {
                    if (isTimeToNotify(tempoRestante)) {
                        notificacaoContagem(tempoRestante);
                    }
                    tempoRestante--;
                }
            }
        };
        contagemtemp.runTaskTimer(MineSkyEvents.get(), 0, 20);
    }

    private static boolean isTimeToNotify(int tempoRestante) {
        return Arrays.asList(180, 120, 60, 30, 15, 10, 5, 4, 3, 2, 1).contains(tempoRestante);
    }

    private static void notificacaoContagem(int tempoRestante) {
        Bukkit.getOnlinePlayers().forEach(player -> {
            if (Utils.isPlayerInEvent(player)) {
                player.sendTitle("§8[§4Sumo§8]", "§7INICIANDO EM§8: §4" + tempoRestante + "s", 10, 70, 20);
                player.playSound(player, Sound.BLOCK_NOTE_BLOCK_BANJO, 1.0f , 1.0f);
            }
        });
        if (tempoRestante == 5) {
            teleportarJogadores();
        }
    }

    private static void teleportarJogadores() {
        Bukkit.getOnlinePlayers().forEach(player -> {
            if (Utils.isPlayerInEvent(player)) {
                player.teleport(EventData.getEventArenaLocation("Sumo"), PlayerTeleportEvent.TeleportCause.COMMAND);
            }
        });
    }

    private static void finalizarContagem() {
        contagem = false;
        if (EventData.ForceSkip) teleportarJogadores();
        Bukkit.getOnlinePlayers().forEach(player -> {
            if (Utils.isPlayerInEvent(player)) {
                player.getInventory().removeItem(EventItem.getItem("BedLeave"), EventItem.HeadEvents(player));
                if (!EventPlayerManager.getPlayerCheck(player)) {
                    player.getInventory().addItem(EventItem.getItem("SumoITEM"));
                    EventPlayerManager.addPlayerITEM(player, true);
                }
            }
        });
        contagemtemp.cancel();
    }

    public static void finalizar() {
        Player vencedor = EventPlayerManager.getPlayerManager().stream()
                .filter(player -> !mortos.contains(player))
                .findFirst().orElse(null);

        Player[] vencedores = getVencedores();
        MineSkyEvents.event = "OFF";
        Bukkit.getOnlinePlayers().forEach(player -> {
            if (Utils.isPlayerInEvent(player) || Utils.isPlayerSpectating(player)) {
                EventCommand.RevealPlayer(player);
                if (!player.hasPermission("mineskyevents.bypass.join")) SendMessages.sendConnection(player);
                salvarConfig(player);
            }
        });
        enviarMensagemVencedores(vencedor, vencedores);
        distribuirPremios(vencedor, vencedores);
        limparEventos();
    }

    private static Player[] getVencedores() {
        Player[] vencedores = new Player[2];
        int lastIndex = mortos.size() - 1;
        if (lastIndex >= 1) {
            vencedores[0] = mortos.get(lastIndex - 1);
            vencedores[1] = mortos.get(lastIndex);
        } else if (lastIndex == 0) {
            vencedores[1] = mortos.get(0);
        }
        return vencedores;
    }

    private static void enviarMensagemVencedores(Player vencedor, Player[] vencedores) {
        TextComponent encerrar = new TextComponent(String.format("§4§lSumo §8| §a1º Lugar §8- §a§l%s §8| §a2º Lugar §8- §a§l%s §8| §a3º Lugar §8- §a§l%s",
                vencedor.getName(), vencedores[1].getName(), vencedores[0].getName()));
        SendMessages.sendMessage(encerrar);
    }

    private static void distribuirPremios(Player vencedor, Player[] vencedores) {
        Random random = new Random();
        int premio1 = random.nextInt(Config.Tijolao_MAX_1 - Config.Tijolao_MIN_1 + 1) + Config.Tijolao_MIN_1;
        int premio2 = random.nextInt(Config.Tijolao_MAX_2 - Config.Tijolao_MIN_2 + 1) + Config.Tijolao_MIN_2;
        int premio3 = random.nextInt(Config.Tijolao_MAX_3 - Config.Tijolao_MIN_3 + 1) + Config.Tijolao_MIN_3;

        SendMessages.sendPlayerMoney(vencedor, premio1);
        SendMessages.sendPlayerMoney(vencedores[0], premio2);
        SendMessages.sendPlayerMoney(vencedores[1], premio3);

        SendMessages.sendPlayerMessage(vencedor, new TextComponent("§4§lSumo §8| §aVocê ganhou o §lSumo §ae como prêmio você ganhou: §l$" + premio1));
        SendMessages.sendPlayerMessage(vencedores[1], new TextComponent("§4§lSumo §8| §aVocê ganhou o §lSumo §ae como prêmio você ganhou: §l$" + premio2));
        SendMessages.sendPlayerMessage(vencedores[0], new TextComponent("§4§lSumo §8| §aVocê ganhou o §lSumo §ae como prêmio você ganhou: §l$" + premio3));
        EventsMessage.sendLogEvent("Sumo", vencedor, vencedores, premio1, premio2, premio3);
        PlayerDataWIN(vencedor);
        PlayerDataWIN(vencedores[1]);
        PlayerDataWIN(vencedores[0]);
    }

    private static void PlayerDataWIN(Player player) {
        FileConfiguration config = Utils.getPlayerData(player);
        config.set("Events.Sumo.win", config.getInt("Events.Sumo.win") + 1);
        try {
            config.save(Utils.getPlayerDataFile(player));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void limparEventos() {
        EventPlayerManager.clearPlayerManager();
        EventPlayerManager.clearPlayerItem();
        EventData.ForceSkip = false;
        EventData.ForceStart = false;
        mortos.clear();
        contagem = true;
        contagemI = false;
    }

}
