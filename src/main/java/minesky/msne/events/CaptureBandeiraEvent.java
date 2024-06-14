package minesky.msne.events;

import minesky.msne.MineSkyEvents;
import minesky.msne.addons.Vault;
import minesky.msne.commands.EventCommand;
import minesky.msne.config.Config;
import minesky.msne.config.DataManager;
import minesky.msne.config.Locations;
import minesky.msne.discord.EventsMessage;
import minesky.msne.system.event.EventPlayerManager;
import minesky.msne.utils.EventItem;
import minesky.msne.utils.SendMessages;
import minesky.msne.utils.Util;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.*;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.bukkit.scheduler.BukkitRunnable;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class CaptureBandeiraEvent {
    public static boolean contagem;
    public static boolean contagemI = false;
    public static BukkitRunnable temporizador;
    public static BukkitRunnable contagemtemp;
    public static Map<Player, String> time = new HashMap<>();
    public static List<Player> select = new ArrayList<>();
    public static Map<Location, Material> blocksplace = new HashMap<>();
    public static void iniciarEvento() {
        MineSkyEvents.event = "CaptureBandeira";
        SendMessages.sendMessageBGMSNE("CaptureBandeira");
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
                    TextComponent menorplayer = new TextComponent("§8| §aInfelizmente o evento não teve §l4§a players para iniciar.");
                    SendMessages.sendMessageCHMSNE("#2493b4", "Capture a Bandeira ", menorplayer);
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
                    if (tempoRestante == 180 || tempoRestante == 120 ||tempoRestante == 60 || tempoRestante == 30 || tempoRestante == 15 || tempoRestante == 10 || tempoRestante == 5 || tempoRestante == 4 || tempoRestante == 3 || tempoRestante == 2 || tempoRestante == 1) {
                        for (Player player : Bukkit.getOnlinePlayers()) {
                            if (Util.PDVE(player)) {
                                player.sendTitle("§8[" + ChatColor.of("#2493b4") + "Capture a Bandeira§8]", "§7INICIANDO EM§8: " + ChatColor.of("#2493b4") + tempoRestante + "s", 10, 70, 20);
                                player.playSound(player, Sound.BLOCK_NOTE_BLOCK_BANJO, 1.0f , 1.0f);
                                if (tempoRestante == 180) {
                                    player.sendTitle("§8[" + ChatColor.of("#2493b4") + "Capture a Bandeira§8]", "§7INICIANDO EM§8: " + ChatColor.of("#2493b4") + "3m", 10, 70, 20);
                                    player.playSound(player, Sound.BLOCK_NOTE_BLOCK_BANJO, 1.0f , 1.0f);
                                }
                                if (tempoRestante == 120) {
                                    player.sendTitle("§8[" + ChatColor.of("#2493b4") + "Capture a Bandeira§8]", "§7INICIANDO EM§8: " + ChatColor.of("#2493b4") + "2m", 10, 70, 20);
                                    player.playSound(player, Sound.BLOCK_NOTE_BLOCK_BANJO, 1.0f , 1.0f);
                                }
                            }
                        }
                    }


                    if (tempoRestante == 0) {
                        contagem = false;
                        for (Player p : Bukkit.getOnlinePlayers()) {
                            if (Util.PDVE(p)) {
                                p.getInventory().removeItem(EventItem.BedLeave);
                                p.getInventory().removeItem(EventItem.HeadEvents(p));
                                select.add(p);
                                Collections.shuffle(select);
                                for (int i = 0; i < select.size(); i++) {
                                    if (i % 2 == 0) {
                                        time.put(select.get(i), "Green");
                                    } else {
                                        time.put(select.get(i), "Orange");
                                    }
                                }
                                if (!EventPlayerManager.getPlayerCheck(p)) {
                                    p.getInventory().addItem(EventItem.FlagITEM_WOODEN_SWORD);
                                    if (time.get(p).equals("Green")) {
                                        //p.teleport()
                                        p.getInventory().setHelmet(EventItem.FlagITEM_ARMOR_HELMET_LIME);
                                        p.getInventory().setChestplate(EventItem.FlagITEM_ARMOR_CHESTPLATE_LIME);
                                        p.getInventory().setLeggings(EventItem.FlagITEM_ARMOR_LEGGING_LIME);
                                        p.getInventory().setBoots(EventItem.FlagITEM_ARMOR_BOTTS_LIME);
                                    } else if (time.get(p).equals("Orange")) {
                                        p.getInventory().setHelmet(EventItem.FlagITEM_ARMOR_HELMET_ORANGE);
                                        p.getInventory().setChestplate(EventItem.FlagITEM_ARMOR_CHESTPLATE_ORANGE);
                                        p.getInventory().setLeggings(EventItem.FlagITEM_ARMOR_LEGGING_ORANGE);
                                        p.getInventory().setBoots(EventItem.FlagITEM_ARMOR_BOTTS_ORANGE);
                                    }
                                    EventPlayerManager.addPlayerITEM(p, true);
                                }
                                this.cancel();
                            }
                            this.cancel();
                        }
                    }

                    tempoRestante--;
                }
            };
            contagemtemp.runTaskTimer(MineSkyEvents.get(), 0, 20);
        }
    }
    public static void finalizar() {
        MineSkyEvents.event = "OFF";
        for (Player player : Bukkit.getOnlinePlayers()) {
            if (Util.PDVE(player) || Util.PDVES(player)) {
                EventCommand.RevealPlayer(player);
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
    }

    public static void restaurar() {
        for (Map.Entry<Location, Material> entry : blocksplace.entrySet()) {
            Location location = entry.getKey();
            location.getBlock().setType(Material.AIR);
        }
        Bukkit.getLogger().warning("[MineSky-Events] Capture a Bandeira | A arena foi restaurada com sucesso!");
        for (Player player : Bukkit.getOnlinePlayers()) {
            if (player.hasPermission("mineskyevents.notify.restored")) {
                File filefor = DataManager.getFile(player.getName().toLowerCase(), "playerdata");
                FileConfiguration configfor = DataManager.getConfiguration(filefor);
                if (configfor.getBoolean("Notification")) {
                    TextComponent text = new TextComponent("§8| §aA arena foi restaurada com sucesso!");
                    SendMessages.sendPlayerCHMSNE(player,"#2493b4", "Capture a Bandeira ", text);
                } else {
                    return;
                }
            } else {
                return;
            }
        }
        blocksplace.clear();
    }
}
