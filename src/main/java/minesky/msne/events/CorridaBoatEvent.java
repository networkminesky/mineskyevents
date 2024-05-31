package minesky.msne.events;

import minesky.msne.MineSkyEvents;
import minesky.msne.addons.Vault;
import minesky.msne.commands.EventCommand;
import minesky.msne.commands.MSNECommand;
import minesky.msne.config.DataManager;
import minesky.msne.config.Locations;
import minesky.msne.discord.EventsMessage;
import minesky.msne.system.event.EventCorridasPlayerManager;
import minesky.msne.system.event.EventPlayerManager;
import minesky.msne.utils.EventItem;
import minesky.msne.utils.RegionPlayerManager;
import minesky.msne.utils.SendMessages;
import minesky.msne.utils.Util;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.Sound;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.bukkit.scheduler.BukkitRunnable;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class CorridaBoatEvent {
    public static boolean contagem;
    public static boolean contagemI = false;
    public static BukkitRunnable temporizador;
    public static BukkitRunnable contagemtemp;
    public static Map<String, Integer> playerBOATLIST = new HashMap<>();
    private static final long TEMPO_PADRAO = 60000;
    public static Map<String, Long> ultimoRenovacao = new HashMap<>();
    public static int premio1;
    public static int premio2;
    public static int premio3;
    public static void iniciarEvento() {
        MineSkyEvents.event = "CorridaBoat";
        SendMessages.sendMessageBGMSNE("CorridaBoat");
        temporizador = new BukkitRunnable() {
            int tempoRestante = 600;
            @Override
            public void run() {
                if (tempoRestante == 0) {
                    MineSkyEvents.event = "OFF";
                    for (Player player : Bukkit.getOnlinePlayers()) {
                        if (Util.PDVE(player) || Util.PDVES(player)) {
                            SendMessages.sendConectionBCMSNE(player);
                            File file = DataManager.getFile(player.getName().toLowerCase(), "playerdata");
                            FileConfiguration config = DataManager.getConfiguration(file);
                            config.set("Event", false);
                            config.set("EventSpect", false);
                            try {
                                config.save(file);
                            } catch (IOException e) {
                                throw new RuntimeException(e);
                            }
                        }
                    }
                    TextComponent menorplayer = new TextComponent("§9§lCorrida de barco §8| §aInfelizmente o evento não teve §l4§a players para iniciar.");
                    SendMessages.sendMessageBCMSNE(menorplayer);
                }
                tempoRestante--;
            }
        };
        temporizador.runTaskTimer(MineSkyEvents.get(), 0, 20);
        for (Player player : Bukkit.getOnlinePlayers()) {
            if (!player.hasPermission("mineskyevents.bypass.join")) {
                Bukkit.dispatchCommand(player, "event entrar");
            }
        }
    }
    public static void comtagemEvento() {
        if (!contagemI && EventPlayerManager.getPlayerCount() >= 4) {
            temporizador.cancel();
           contagemtemp = new BukkitRunnable() {
                int tempoRestante = 180;

                @Override
                public void run() {
                    contagemI = true;
                    contagem = true;
                    if (tempoRestante == 180 ||tempoRestante == 60 || tempoRestante == 30 || tempoRestante == 15 || tempoRestante == 10 || tempoRestante == 5 || tempoRestante == 4 || tempoRestante == 3 || tempoRestante == 2 || tempoRestante == 1) {
                        for (Player player : Bukkit.getOnlinePlayers()) {
                            if (Util.PDVE(player)) {
                                player.sendTitle("§8[§9Corrida de Barco§8]", "§7INICIANDO EM§8: §9" + tempoRestante + "s", 10, 70, 20);
                                player.playSound(player, Sound.BLOCK_NOTE_BLOCK_BANJO, 1.0f , 1.0f);
                                if (tempoRestante == 180) {
                                    player.sendTitle("§8[§9Corrida de Barco§8]", "§7INICIANDO EM§8: §93m", 10, 70, 20);
                                    player.playSound(player, Sound.BLOCK_NOTE_BLOCK_BANJO, 1.0f , 1.0f);
                                }
                            }
                        }
                    }

                    if (tempoRestante == 0) {
                        contagem = false;
                        for (Player p : Bukkit.getOnlinePlayers()) {
                            if (Util.PDVE(p)) {
                                p.teleport(Locations.corridaboatA, PlayerTeleportEvent.TeleportCause.COMMAND);
                                if (!EventPlayerManager.getPlayerCheck(p)) {
                                    p.getInventory().removeItem(EventItem.BedLeave);
                                    p.getInventory().addItem(EventItem.BarcoITEM);
                                    p.getInventory().addItem(EventItem.BarcoITEM);
                                    p.getInventory().addItem(EventItem.BarcoITEM);
                                    p.getInventory().addItem(EventItem.BarcoITEM);
                                    p.getInventory().addItem(EventItem.BarcoITEM);
                                    p.getInventory().addItem(EventItem.BarcoITEM);
                                    p.getInventory().addItem(EventItem.BarcoITEM);
                                    p.getInventory().addItem(EventItem.BarcoITEM);
                                    EventPlayerManager.addPlayerITEM(p, true);
                                }
                                renovarTempo(p);
                                this.cancel();
                            }
                        }
                        this.cancel();
                    }

                    tempoRestante--;
                }
            };
           contagemtemp.runTaskTimer(MineSkyEvents.get(), 0, 20);
        }
    }

    public static void chegada(Player player) {
        if (!podeRenovar(player)) return;
        String playerName = player.getName();
        if (playerBOATLIST.getOrDefault(playerName, 0) >= 3) {
            player.playSound(player, Sound.BLOCK_NOTE_BLOCK_BASS, 1.0f , 1.0f);
            player.sendMessage("§8[§c!§8] §cVocê já completou as 3 voltas!");
            return;
        }
        int lapCount = playerBOATLIST.getOrDefault(playerName, 0);
        int lapCountADD = lapCount + 1;
        playerBOATLIST.put(playerName, lapCount + 1);
        player.playSound(player, Sound.BLOCK_AMETHYST_BLOCK_FALL, 1.0f , 1.0f);
        player.sendMessage("§8[§a!§8] §aVocê completou a sua §l" + lapCountADD + "º volta!");
        renovarTempo(player);
        if (lapCount + 1 == 3) {
            EventCorridasPlayerManager.addPlayer(player);
            player.playSound(player, Sound.BLOCK_AMETHYST_BLOCK_FALL, 1.0f , 1.0f);
            player.sendMessage("§8[§a!§8] §aVocê completou as§l 3 §avoltas!");
            if (EventCorridasPlayerManager.getPlayerCount() >= 3) {
                MineSkyEvents.event = "OFF";
                StringBuilder message = new StringBuilder("§9§lCorrida de barco §8| §a");
                for (int i = 0; i < 3; i++) {
                    String ganhador = EventCorridasPlayerManager.getPlayerManager().get(i);
                    Player ganhadorPlayer = player.getServer().getPlayer(ganhador);
                    message.append((i + 1)).append("º lugar: §l").append(EventCorridasPlayerManager.getPlayerManager().get(i)).append(" §8|§a ");
                    int premio = 0;
                    Random random = new Random();
                    if (i == 0) {
                        premio = random.nextInt(5500 - 4500 + 1) + 4500;
                        premio1 = premio;
                    }
                    if (i == 1) {
                        premio = random.nextInt(3500 - 2500 + 1) + 2500;
                        premio2 = premio;
                    }
                    if (i == 2) {
                        premio = random.nextInt(2500 - 1500 + 1) + 1500;
                        premio3 = premio;
                    }
                    OfflinePlayer poff = Bukkit.getOfflinePlayer(EventCorridasPlayerManager.getPlayerManager().get(i));
                    Player p = Bukkit.getPlayer(EventCorridasPlayerManager.getPlayerManager().get(i));
                    Vault.economy.depositPlayer(poff, premio);
                    TextComponent text1 = new TextComponent("§9§lCorrida de barco §8| §aVocê ganhou a §lCorrida de barco §ae como prêmio você ganhou: §l$" + premio);
                    assert p != null;
                    SendMessages.sendPlayermessage(p, text1);
                    assert ganhadorPlayer != null;
                    File file = DataManager.getFile(ganhadorPlayer.getName().toLowerCase(), "playerdata");
                    FileConfiguration config = DataManager.getConfiguration(file);

                    config.set("Events.CorridaBoat.win", config.getInt("Events.CorridaBoat.win") + 1);
                    try {
                        config.save(file);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }

                }
                message = new StringBuilder(message.substring(0, message.length() - 2));
                TextComponent text = new TextComponent(message.toString());
                SendMessages.sendMessageBCMSNE(text);
                EventsMessage.sendLogEventPLIST("CorridaBoat", EventCorridasPlayerManager.getPlayerManager(), premio1, premio2, premio3);
                EventCorridasPlayerManager.clearPlayerManager();
                playerBOATLIST.clear();
                EventPlayerManager.clearPlayerManager();
                RegionPlayerManager.clearPlayerManager();
                EventPlayerManager.clearPlayerItem();
                CorridaBoatEvent.contagem = true;
                CorridaBoatEvent.contagemI = false;
                for (Player p : Bukkit.getOnlinePlayers()) {
                    if (Util.PDVE(p) || Util.PDVES(p)) {
                        EventCommand.RevealPlayer(p);
                        SendMessages.sendConectionBCMSNE(p);
                        File file = DataManager.getFile(p.getName().toLowerCase(), "playerdata");
                        FileConfiguration config = DataManager.getConfiguration(file);
                        config.set("Event", false);
                        config.set("EventSpect", false);
                        try {
                            config.save(file);
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    }
                }
            }
        }
    }

    public static boolean podeRenovar(Player player) {
        String playerName = player.getName();
        if (!ultimoRenovacao.containsKey(playerName)) {
            return true;
        }

        long tempoAtual = System.currentTimeMillis();
        long ultimoRenovacaoTempo = ultimoRenovacao.get(playerName);
        return tempoAtual - ultimoRenovacaoTempo >= TEMPO_PADRAO;
    }

    public static void renovarTempo(Player player) {
        String playerName = player.getName();
        long tempoAtual = System.currentTimeMillis();
        ultimoRenovacao.put(playerName, tempoAtual);
    }
}
