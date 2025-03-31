package net.minesky.events;

import net.md_5.bungee.api.chat.TextComponent;
import net.minesky.MineSkyEvents;
import net.minesky.config.Config;
import net.minesky.config.DataManager;
import net.minesky.config.Locations;
import net.minesky.discord.EventsMessage;
import net.minesky.hooks.Vault;
import net.minesky.system.event.EventCorridasPlayerManager;
import net.minesky.system.event.EventPlayerManager;
import net.minesky.utils.*;
import org.bukkit.*;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.bukkit.scheduler.BukkitRunnable;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class CorridaEvent {
    public static boolean contagem, contagemI = false;
    private static List<Player> mortos = new ArrayList<>();
    private static Map<Location, Material> blocksbreak = new HashMap<>();
    public static int premio1;
    public static int premio2;
    public static int premio3;
    public static BukkitRunnable temporizador, contagemtemp;

    public static void iniciarEvento() {
        MineSkyEvents.event = "Corrida";
        SendMessages.sendMessageEvent("Corrida");
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
        SendMessages.sendMessage(new TextComponent("§6§lCorrida §8| §aInfelizmente o evento não teve §l4§a players para iniciar."));
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
                player.sendTitle("§8[§eCorrida§8]", "§7INICIANDO EM§8: §e" + tempoRestante + "s", 10, 70, 20);
                player.playSound(player, Sound.BLOCK_NOTE_BLOCK_BANJO, 1.0f , 1.0f);
            }
        });
    }

    private static void teleportarJogadores() {
        Bukkit.getOnlinePlayers().forEach(player -> {
            if (Utils.isPlayerInEvent(player)) {
                player.teleport(Locations.corridaA, PlayerTeleportEvent.TeleportCause.COMMAND);
            }
        });
    }

    private static void finalizarContagem() {
        contagem = false;
        teleportarJogadores();
        Bukkit.getOnlinePlayers().forEach(player -> {
            if (Utils.isPlayerInEvent(player)) {
                player.getInventory().removeItem(EventItem.getItem("BedLeave"), EventItem.HeadEvents(player));
            }
        });
        contagemtemp.cancel();
    }

    public static void chegada(Player player) {
        if (EventCorridasPlayerManager.getPlayer(player)) {
            player.sendMessage("§8[§c!§8] §cVocê já chegou!");
            return;
        }
        EventCorridasPlayerManager.addPlayer(player);
        player.playSound(player, Sound.BLOCK_AMETHYST_BLOCK_FALL, 1.0f, 1.0f);
        player.sendMessage("§8[§a!§8] §aVocê ultrapassou a linha de chegada!");

        if (EventCorridasPlayerManager.getPlayerCount() >= 3 || EventPlayerManager.getPlayerCount() == 1) finalizarCorrida();
    }

    private static void finalizarCorrida() {
        MineSkyEvents.event = "OFF";
        StringBuilder mensagem = new StringBuilder("§e§lCorrida §8| §a");
        Random random = new Random();

        for (int i = 0; i < 3; i++) {
            String ganhador = EventCorridasPlayerManager.getPlayerManager().get(i);
            mensagem.append(i + 1).append("º lugar: §l").append(ganhador).append(" §8|§a ");

            int bound1 = Config.SPLEEF_MAX_1 - Config.SPLEEF_MIN_1 + 1;
            int bound2 = Config.SPLEEF_MAX_2 - Config.SPLEEF_MIN_2 + 1;
            int bound3 = Config.SPLEEF_MAX_3 - Config.SPLEEF_MIN_3 + 1;

            int premio = 0;
            if (i == 0) {
                if (bound1 > 0) {
                    premio = random.nextInt(bound1) + Config.SPLEEF_MIN_1;
                } else {
                    premio = Config.SPLEEF_MIN_1;
                }
                premio1 = premio;
            }
            if (i == 1) {
                if (bound2 > 0) {
                    premio = random.nextInt(bound2) + Config.SPLEEF_MIN_2;
                } else {
                    premio = Config.SPLEEF_MIN_2;
                }
                premio2 = premio;
            }
            if (i == 2) {
                if (bound3 > 0) {
                    premio = random.nextInt(bound3) + Config.SPLEEF_MIN_3;
                } else {
                    premio = Config.SPLEEF_MIN_3;
                }
                premio3 = premio;
            }
            recompensarJogador(Bukkit.getPlayer(ganhador), premio);
            PlayerDataWIN(Bukkit.getPlayer(ganhador));
        }

        mensagem = new StringBuilder(mensagem.substring(0, mensagem.length() - 2));
        TextComponent text = new TextComponent(mensagem.toString());
        SendMessages.sendMessage(text);
        EventsMessage.sendLogEvent("Corrida", EventCorridasPlayerManager.getPlayerManager(), premio1, premio2, premio3);
        resetEvento();
    }


    private static void recompensarJogador(Player player, int premio) {

        SendMessages.sendPlayerMoney(player, premio);

        SendMessages.sendPlayerMessage(player, new TextComponent("§e§lCorrida §8| §aVocê ganhou a §lcorrida §ae como prêmio você ganhou: §l$" + premio));

    }

    private static void PlayerDataWIN(Player player) {
        FileConfiguration config = Utils.getPlayerData(player);
        config.set("Events.Corrida.win", config.getInt("Events.Corrida.win") + 1);
        try {
            config.save(Utils.getPlayerDataFile(player));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void resetEvento() {
        EventCorridasPlayerManager.clearPlayerManager();
        RegionPlayerManager.clearPlayerManager();
        EventPlayerManager.clearPlayerManager();
        EventPlayerManager.clearPlayerItem();
        EventData.ForceSkip = false;
        EventData.ForceStart = false;
        contagem = true;
        contagemI = false;
        Bukkit.getOnlinePlayers().forEach(CorridaEvent::salvarConfig);
    }


}
