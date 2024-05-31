package minesky.msne.events;

import minesky.msne.MineSkyEvents;
import minesky.msne.addons.Vault;
import minesky.msne.commands.EventCommand;
import minesky.msne.config.DataManager;
import minesky.msne.config.Locations;
import minesky.msne.discord.EventsMessage;
import minesky.msne.system.event.EventPlayerManager;
import minesky.msne.utils.EventItem;
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
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class TNTTagEvent {
    public static boolean contagem;
    public static boolean contagemI = false;
    public static List<Player> mortos = new ArrayList<>();
    public static BukkitRunnable temporizador;
    public static BukkitRunnable contagemtemp;
    public static BukkitRunnable temp1;
    public static BukkitRunnable temp2;
    public static int TIME;
    public static List<Player> jogadores = new ArrayList<>();
    public static List<Player> tnt = new ArrayList<>();
    public static Player PEGOS;
    public static Player PEGOS2;
    public static final Random random = new Random();
    public static void iniciarEvento() {
        MineSkyEvents.event = "TNTTag";
        SendMessages.sendMessageBGMSNE("TNTTag");
        TIME = getRandomTime();
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
                    TextComponent menorplayer = new TextComponent("§c§lTNT-TAG §8| §aInfelizmente o evento não teve §l8§a players para iniciar.");
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
        if (!contagemI && EventPlayerManager.getPlayerCount() >= 8) {
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
                                player.sendTitle("§8[§cTNT-TAG§8]", "§7INICIANDO EM§8: §c" + tempoRestante + "s", 10, 70, 20);
                                player.playSound(player, Sound.BLOCK_NOTE_BLOCK_BANJO, 1.0f , 1.0f);
                                if (tempoRestante == 180) {
                                    player.sendTitle("§8[§cTNT-TAG§8]", "§7INICIANDO EM§8: §c3m", 10, 70, 20);
                                    player.playSound(player, Sound.BLOCK_NOTE_BLOCK_BANJO, 1.0f , 1.0f);
                                }
                            }
                        }
                    }

                    if (tempoRestante == 5) {
                        for (Player p : Bukkit.getOnlinePlayers()) {
                            if (Util.PDVE(p)) {
                                p.teleport(Locations.tnttagA, PlayerTeleportEvent.TeleportCause.COMMAND);
                            }
                        }
                    }

                    if (tempoRestante == 0) {
                        contagem = false;
                        for (Player p : Bukkit.getOnlinePlayers()) {
                            if (Util.PDVE(p)) {
                                p.getInventory().removeItem(EventItem.BedLeave);
                                p.getInventory().removeItem(EventItem.HeadEvents(p));
                                jogadores.add(p);
                            }
                        }
                        Player p1 = jogadores.get(random.nextInt(jogadores.size()));
                        Player p2;
                        do {
                            p2 = jogadores.get(random.nextInt(jogadores.size()));
                        } while (p1.equals(p2));
                        tnt.add(p1);
                        tnt.add(p2);
                        p1.getInventory().setHelmet(EventItem.TNTHEAD);
                        p2.getInventory().setHelmet(EventItem.TNTHEAD);
                        secoundsTNTTAGP1(p1);
                        secoundsTNTTAGP2(p2);
                        p2.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, Integer.MAX_VALUE, 0, false, false));
                        p1.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, Integer.MAX_VALUE, 0, false, false));
                        this.cancel();
                    }
                    tempoRestante--;
                }
            };
            contagemtemp.runTaskTimer(MineSkyEvents.get(), 0, 20);
        }
    }
    public static void finalizar() {
        Player vencedor = EventPlayerManager.getPlayerManager().stream()
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
        assert vencedor != null;
        TextComponent encerrar = new TextComponent("§c§lTNT-TAG §8| §a1º Lugar §8- §a§l" + vencedor.getName() + " §8| §a2º Lugar §8- §a§l" + vencedores[1].getName() + " §8| §a3º Lugar §8- §a§l" + vencedores[0].getName());
        SendMessages.sendMessageBCMSNE(encerrar);
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
        TextComponent text1 = new TextComponent("§c§lTNT-TAG §8| §aVocê ganhou o §lTNT-TAG §ae como prêmio você ganhou: §l" + premio1);
        TextComponent text2 = new TextComponent("§c§lTNT-TAG §8| §aVocê ganhou o §lTNT-TAG §ae como prêmio você ganhou: §l" + premio2);
        TextComponent text3 = new TextComponent("§c§lTNT-TAG §8| §aVocê ganhou o §lTNT-TAG §ae como prêmio você ganhou: §l" + premio3);
        SendMessages.sendPlayermessage(vencedor, text1);
        SendMessages.sendPlayermessage(vencedores[1], text2);
        SendMessages.sendPlayermessage(vencedores[0], text3);
        EventsMessage.sendLogEvent("TNTTag", vencedor, vencedores, premio1, premio2, premio3);
        EventPlayerManager.clearPlayerManager();
        EventPlayerManager.clearPlayerItem();
        mortos.clear();
        jogadores.clear();
        tnt.clear();
        temp2.cancel();
        temp1.cancel();
        contagem = true;
        contagemI = false;
        File fileV = DataManager.getFile(vencedor.getName().toLowerCase(), "playerdata");
        FileConfiguration configV = DataManager.getConfiguration(fileV);
        configV.set("Events.TNTTag.win", configV.getInt("Events.TNTTTag.win")+1);
        try {
            configV.save(fileV);
            for (Player player1 : vencedores) {
                File file = DataManager.getFile(player1.getName().toLowerCase(), "playerdata");
                FileConfiguration config = DataManager.getConfiguration(file);

                config.set("Events.TNTTag.win", config.getInt("Events.TNTTTag.win")+1);
                try {
                    config.save(file);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public static void secoundsTNTTAGP1(Player p) {
        PEGOS = p;
        temp1 = new BukkitRunnable() {
            int tempoRestante = TIME;
            @Override
            public void run() {
                if (tempoRestante == 30 || tempoRestante == 25 || tempoRestante == 20 || tempoRestante == 15 || tempoRestante == 10 || tempoRestante == 5 || tempoRestante == 4 || tempoRestante == 3 || tempoRestante == 2 || tempoRestante == 1) {
                    for (Player player : Bukkit.getOnlinePlayers()) {
                        if (Util.PDVE(player)) {
                            if (tnt.contains(player) && PEGOS.getName().equalsIgnoreCase(player.getName())) {
                                player.sendTitle(ChatColor.RED + String.valueOf(tempoRestante) + "s", "", 10, 70, 20);
                            }
                        }
                    }
                }
                if (tempoRestante == 0) {
                    PEGOS.damage(999999999);
                    PEGOS.setHealth(0);
                    this.cancel();
                }
                tempoRestante--;
            }
        };
        temp1.runTaskTimer(MineSkyEvents.get(), 0, 20);
    }

    public static void secoundsTNTTAGP2(Player p) {
        PEGOS2 = p;
        temp2 = new BukkitRunnable() {
            int tempoRestante = TIME;
            @Override
            public void run() {
                if (tempoRestante == 30 || tempoRestante == 25 || tempoRestante == 20 || tempoRestante == 15 || tempoRestante == 10 || tempoRestante == 5 || tempoRestante == 4 || tempoRestante == 3 || tempoRestante == 2 || tempoRestante == 1) {
                    for (Player player : Bukkit.getOnlinePlayers()) {
                        if (Util.PDVE(player)) {
                            if (tnt.contains(player) && PEGOS2.getName().equalsIgnoreCase(player.getName())) {
                                player.sendTitle(ChatColor.RED + String.valueOf(tempoRestante) + "s", "", 10, 70, 20);
                            }
                        }
                    }
                }
                if (tempoRestante == 0) {
                    PEGOS2.damage(999999999);
                    PEGOS2.setHealth(0);
                    this.cancel();
                }
                tempoRestante--;
            }
        };
        temp2.runTaskTimer(MineSkyEvents.get(), 0, 20);
    }


    private static int getRandomTime() {
        Random random = new Random();
        int minSeconds = 15;
        int maxSeconds = 30;
        int randomSeconds = minSeconds + random.nextInt(maxSeconds - minSeconds + 1);
        TIME = randomSeconds;
        return randomSeconds;
    }
}
