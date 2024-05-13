package minesky.msne.events;

import minesky.msne.MineSkyEvents;
import minesky.msne.addons.Vault;
import minesky.msne.bot.MineSkyBot;
import minesky.msne.config.Config;
import minesky.msne.config.DataManager;
import minesky.msne.config.Locations;
import minesky.msne.utils.Util;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.scheduler.BukkitRunnable;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class SumoEvent {
    private Map<Player, Integer> contagemRegressiva = new HashMap<>();
    public static boolean contagem;
    public static boolean contagemI = false;
    public static Set<Player> playerson = new HashSet<>();
    public static List<Player> mortos = new ArrayList<>();
    public static BukkitRunnable temporizador;
    public static void iniciarEvento() {
        MineSkyEvents.event = "Sumo";
        Util.sendMessageBGMSNE("Sumo");
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
                            Util.sendConectionBCMSNE(player);
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
                    TextComponent menorplayer = new TextComponent("§4§lSumo §8| §aInfelizmente o evento não teve §l4§a players para iniciar.");
                    Util.sendMessageBCMSNE(menorplayer);
                }
                tempoRestante--;
            }
        };
        temporizador.runTaskTimer(MineSkyEvents.get(), 0, 20);
    }
    public static void comtagemEvento() {
        if (!contagemI && playerson.size() >= 4) {
            temporizador.cancel();
            new BukkitRunnable() {
                int tempoRestante = 180;

                @Override
                public void run() {
                    contagemI = true;
                    contagem = true;
                    if (tempoRestante == 180 ||tempoRestante == 60 || tempoRestante == 30 || tempoRestante == 15 || tempoRestante == 10 || tempoRestante == 5 || tempoRestante == 4 || tempoRestante == 3 || tempoRestante == 2 || tempoRestante == 1) {
                        for (Player player : Bukkit.getOnlinePlayers()) {
                            if (!Util.PDVE(player)) return;
                            if (tempoRestante == 180) {
                                player.sendTitle("§a3m", "", 10, 70, 20);
                            }
                            player.sendTitle(ChatColor.RED + String.valueOf(tempoRestante) + "s", "", 10, 70, 20);
                        }
                    }

                    if (tempoRestante == 0) {
                        contagem = false;
                        for (Player p : Bukkit.getOnlinePlayers()) {
                            if (!Util.PDVE(p)) return;
                            p.teleport(Locations.sumoA, PlayerTeleportEvent.TeleportCause.COMMAND);
                            p.getInventory().removeItem(Util.BedLeave);
                            p.getInventory().removeItem(Util.Head);
                            p.getInventory().addItem(Util.SumoItem);
                            this.cancel();
                        }
                        this.cancel();
                    }

                    tempoRestante--;
                }
            }.runTaskTimer(MineSkyEvents.get(), 0, 20);
        }
    }
    public static void finalizar() {
        Player vencedor = playerson.stream()
                .filter(player -> !mortos.contains(player))
                .findFirst()
                .orElse(null);
        Player[] vencedores = new Player[2];
        int lastIndex = mortos.size() - 1;
        if (lastIndex >= 1) {
            vencedores[0] = mortos.get(lastIndex - 1);
            vencedores[1] = mortos.get(lastIndex);
        } else if (lastIndex == 0) {
            vencedores[1] = mortos.get(0);
        }
        MineSkyEvents.event = "OFF";
        for (Player player : Bukkit.getOnlinePlayers()) {
            if (Util.PDVE(player) || Util.PDVES(player)) {
                Util.sendConectionBCMSNE(player);
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
            TextComponent encerrar = new TextComponent("§4§lSumo §8| §a1º Lugar §8- §a§l" + vencedor.getName() + " §8| §a2º Lugar §8- §a§l" + vencedores[1].getName() + " §8| §a3º Lugar §8- §a§l" + vencedores[0].getName());
            Util.sendMessageBCMSNE(encerrar);
            Random random = new Random();
            int premio1 = random.nextInt(5500 - 4500 + 1) + 4500;
            int premio2 = random.nextInt(3500 - 2500 + 1) + 2500;
            int premio3 = random.nextInt(2500 - 1500 + 1) + 1500;
            OfflinePlayer p1 = Bukkit.getOfflinePlayer(vencedor.getName());
            OfflinePlayer p2 = Bukkit.getOfflinePlayer(vencedores[0].getName());
            OfflinePlayer p3 = Bukkit.getOfflinePlayer(vencedores[1].getName());
            Vault.economy.depositPlayer(p1, premio1);
            Vault.economy.depositPlayer(p2, premio2);
            Vault.economy.depositPlayer(p3, premio3);
            TextComponent text1 = new TextComponent("§4§lSumo §8| §aVocê ganhou o §lSumo §ae como prêmio você ganhou: §l" + premio1);
            TextComponent text2 = new TextComponent("§4§lSumo §8| §aVocê ganhou o §lSumo §ae como prêmio você ganhou: §l" + premio2);
            TextComponent text3 = new TextComponent("§4§lSumo §8| §aVocê ganhou o §lSumo §ae como prêmio você ganhou: §l" + premio3);
            Util.sendPlayermessage(vencedor, text1);
            Util.sendPlayermessage(vencedores[1], text2);
            Util.sendPlayermessage(vencedores[0], text3);
            if (Config.Bot) {
                MineSkyBot.sendLogEvent("Sumo", vencedor, vencedores, premio1, premio2, premio3);
            }
            playerson.clear();
            mortos.clear();
            for (Player player1 : vencedores) {
                File file = DataManager.getFile(player1.getName().toLowerCase(), "playerdata");
                FileConfiguration config = DataManager.getConfiguration(file);

                config.set("Events.Sumo.win", config.getInt("Events.Sumo.win")+1);
                try {
                    config.save(file);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
