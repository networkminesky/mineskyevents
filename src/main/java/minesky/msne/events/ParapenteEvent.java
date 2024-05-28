package minesky.msne.events;

import minesky.msne.MineSkyEvents;
import minesky.msne.addons.MMOItem;
import minesky.msne.addons.Vault;
import minesky.msne.commands.EventCommand;
import minesky.msne.commands.MSNECommand;
import minesky.msne.config.DataManager;
import minesky.msne.config.Locations;
import minesky.msne.discord.EventsMessage;
import minesky.msne.system.event.EventCorridasPlayerManager;
import minesky.msne.system.event.EventPlayerManager;
import minesky.msne.utils.EventItem;
import minesky.msne.utils.SendMessages;
import minesky.msne.utils.Util;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.bukkit.scheduler.BukkitRunnable;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class ParapenteEvent {
    public static boolean contagem;
    public static boolean contagemI = false;
    public static BukkitRunnable temporizador;
    public static BukkitRunnable contagemtemp;
    public static Map<Player, Integer> playerARCOLIST = new HashMap<>();
    public static Map<Player, Integer> playerCHECKPOINT = new HashMap<>();
    public static int premio1;
    public static int premio2;
    public static int premio3;
    public static void iniciarEvento() {
        MineSkyEvents.event = "Parapente";
        SendMessages.sendMessageBGMSNE("Parapente");
        for (Player player : Bukkit.getOnlinePlayers()) {
            if (!player.hasPermission("mineskyevents.bypass.join")) {
                Bukkit.dispatchCommand(player, "event entrar");
            }
        }
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
                    TextComponent menorplayer = new TextComponent("§3§lCorrida de Parapente §8| §aInfelizmente o evento não teve §l4§a players para iniciar.");
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
                                if (tempoRestante == 180) {
                                    player.sendTitle("§a3m", "", 10, 70, 20);
                                }
                                player.sendTitle(ChatColor.RED + String.valueOf(tempoRestante) + "s", "", 10, 70, 20);
                            }
                        }
                    }

                    if (tempoRestante == 0) {
                        contagem = false;
                        for (Player p : Bukkit.getOnlinePlayers()) {
                            if (!Util.PDVE(p)) return;
                            p.teleport(Locations.parapenteA, PlayerTeleportEvent.TeleportCause.COMMAND);
                            p.getInventory().removeItem(EventItem.BedLeave);
                            p.getInventory().setItem(3, EventItem.CheckPoint);
                            MMOItem.darParaglider(p);
                            this.cancel();
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
        if (EventCorridasPlayerManager.getPlayer(player)) {
            player.sendMessage("§8[§c!§8] §cVocê já chegou!");
            return;
        }
        if (playerARCOLIST.getOrDefault(player, 0) < 22) {
            player.sendMessage("§8[§c!§8] §cVocê não completou todos os arcos! §8(§c§l" + playerARCOLIST.getOrDefault(player, 0) + "§8/§c§l22§8)");
            int listC = playerCHECKPOINT.getOrDefault(player, 0);
            if (!(listC <= 0)) {
                switch (listC) {
                    case 1:
                        player.teleport(Locations.parapenteC1, PlayerTeleportEvent.TeleportCause.COMMAND);
                        player.sendMessage("§8[§a!§8] §aVocê voltou para o seu checkpoint!");
                        break;
                    case 2:
                        player.teleport(Locations.parapenteC2, PlayerTeleportEvent.TeleportCause.COMMAND);
                        player.sendMessage("§8[§a!§8] §aVocê voltou para o seu checkpoint!");
                        break;
                    case 3:
                        player.teleport(Locations.parapenteC3, PlayerTeleportEvent.TeleportCause.COMMAND);
                        player.sendMessage("§8[§a!§8] §aVocê voltou para o seu checkpoint!");
                        break;
                }
            } else {
                player.teleport(Locations.parapenteA, PlayerTeleportEvent.TeleportCause.COMMAND);
                player.sendMessage("§8[§a!§8] §aVocê voltou para o seu checkpoint!");
            }
            return;
        }
        if (playerARCOLIST.getOrDefault(player, 0) >= 22) {
            EventCorridasPlayerManager.addPlayer(player);
            player.sendMessage("§8[§a!§8] §aVocê completou todos os arcos!");
        }

        if (EventPlayerManager.getPlayerCount() >= 3) {
            MineSkyEvents.event = "OFF";
            StringBuilder message = new StringBuilder("§3§lCorrida de Parapente §8| §a");
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
                TextComponent text1 = new TextComponent("§3§lCorrida de Parapente §8| §aVocê ganhou a §lCorrida de Parapente §ae como prêmio você ganhou: §l$" + premio);
                assert p != null;
                SendMessages.sendPlayermessage(p, text1);
                assert ganhadorPlayer != null;
                File file = DataManager.getFile(ganhadorPlayer.getName().toLowerCase(), "playerdata");
                FileConfiguration config = DataManager.getConfiguration(file);

                config.set("Events.Parapente.win", config.getInt("Events.Parapente.win") + 1);
                try {
                    config.save(file);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

            }
            message = new StringBuilder(message.substring(0, message.length() - 2));
            TextComponent text = new TextComponent(message.toString());
            SendMessages.sendMessageBCMSNE(text);
            EventsMessage.sendLogEventPLIST("Parapente", EventCorridasPlayerManager.getPlayerManager(), premio1, premio2, premio3);
            EventCorridasPlayerManager.clearPlayerManager();
            playerARCOLIST.clear();
            playerCHECKPOINT.clear();
            EventPlayerManager.clearPlayerManager();
            ParapenteEvent.contagem = true;
            ParapenteEvent.contagemI = false;
            for (Player p : Bukkit.getOnlinePlayers()) {
                if (Util.PDVE(p) || Util.PDVES(p)) {
                    EventCommand.RevealPlayer(player);
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

    public static void CheckPoint(Player player, Integer id) {
        if (id > 3) {
            player.sendMessage("§8[§c!§8] §cSó existe apenas §l3 §ccheckpoints");
            return;
        }

        if (playerCHECKPOINT.getOrDefault(player, 0) >= 3) {
            Bukkit.getLogger().warning("[MineSky-Events] O jogador " + player.getName() + " já marcou todos os checkpoints");
            return;
        }
        int listC = playerCHECKPOINT.getOrDefault(player, 0);
        if (id == 1) {
            if (listC > 1 || listC == id) return;
            playerCHECKPOINT.put(player, 1);
            player.sendMessage("§8[§a!§8] §aVocê marcou o checkpoint!");
        }
        if (id == 2) {
            if (listC > 2|| listC == id) return;
            playerCHECKPOINT.put(player, 2);
            player.sendMessage("§8[§a!§8] §aVocê marcou o checkpoint!");
        }
        if (id == 3) {
            if (listC > 3 || listC == id) return;
            playerCHECKPOINT.put(player, 3);
            player.sendMessage("§8[§a!§8] §aVocê marcou o checkpoint!");
        }
    }

    public static void Arcos(Player player, Integer id) {
        if (playerARCOLIST.getOrDefault(player, 0) >= 22) {
            Bukkit.getLogger().warning("[MineSky-Events] O jogador " + player.getName() + " completou os 22 arcos");
            return;
        }
        int listA = playerARCOLIST.getOrDefault(player, 0);
        int listC = playerCHECKPOINT.getOrDefault(player, 0);
        if (id < listA+1) {
            Bukkit.getLogger().warning("[MineSky-Events] O jogador " + player.getName() + " já completou o arco: " + id);
            return;
        }
        if (listA+1 < id) {
            if (!(listC <= 0)) {
                switch (listC) {
                    case 1:
                        player.teleport(Locations.parapenteC1, PlayerTeleportEvent.TeleportCause.COMMAND);
                        player.sendMessage("§8[§c!§8] §cVocê não completou um arco!");
                        break;
                    case 2:
                        player.teleport(Locations.parapenteC2, PlayerTeleportEvent.TeleportCause.COMMAND);
                        player.sendMessage("§8[§c!§8] §cVocê não completou um arco!");
                        break;
                    case 3:
                        player.teleport(Locations.parapenteC3, PlayerTeleportEvent.TeleportCause.COMMAND);
                        player.sendMessage("§8[§c!§8] §cVocê não completou um arco!");
                        break;
                }
                return;
            }
            player.teleport(Locations.parapenteA, PlayerTeleportEvent.TeleportCause.COMMAND);
            player.sendMessage("§8[§c!§8] §cVocê não completou um arco!");
            return;
        }
        playerARCOLIST.put(player, listA + 1);
        int listAadd = listA+1;
        player.sendMessage("§eVocê completou mais um arco! (§b" + listAadd + "§e/§b22§e)");
    }
}
