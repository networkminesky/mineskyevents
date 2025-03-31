package net.minesky.events;

import net.md_5.bungee.api.chat.TextComponent;
import net.minesky.MineSkyEvents;
import net.minesky.commands.EventCommand;
import net.minesky.config.Config;
import net.minesky.config.DataManager;
import net.minesky.config.Locations;
import net.minesky.discord.EventsMessage;
import net.minesky.hooks.Vault;
import net.minesky.system.event.EventPlayerManager;
import net.minesky.utils.EventData;
import net.minesky.utils.EventItem;
import net.minesky.utils.SendMessages;
import net.minesky.utils.Utils;
import org.bukkit.Bukkit;
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
    public static boolean contagem, contagemI = false;
    public static List<Player> mortos = new ArrayList<>(), jogadores = new ArrayList<>(), tnt = new ArrayList<>();
    public static BukkitRunnable temporizador, contagemtemp, temp1, temp2;
    public static Player PEGOS, PEGOS2;
    public static Random random = new Random();
    public static int TIME;

    public static void iniciarEvento() {
        MineSkyEvents.event = "TNTTag";
        SendMessages.sendMessageEvent("TNTTag");
        temporizador = new BukkitRunnable() {
            int tempoRestante = 600;
            @Override
            public void run() {
                if (tempoRestante-- == 0) encerrarEvento();
            }
        };
        temporizador.runTaskTimer(MineSkyEvents.get(), 0, 20);
        Bukkit.getOnlinePlayers().forEach(player -> {
            if (!player.hasPermission("mineskyevents.bypass.join")) Bukkit.dispatchCommand(player, "event entrar");
        });
    }

    private static void encerrarEvento() {
        MineSkyEvents.event = "OFF";
        Bukkit.getOnlinePlayers().forEach(TNTTagEvent::salvarConfig);
        SendMessages.sendMessage(new TextComponent("§c§lTNT-TAG §8| §aInfelizmente o evento não teve §l8§a players para iniciar."));
    }

    private static void salvarConfig(Player player) {
        if (Utils.isPlayerInEvent(player) || Utils.isPlayerSpectating(player)) {
            SendMessages.sendConnection(player);
            File file = DataManager.getFile(player.getName().toLowerCase(), "playerdata");
            FileConfiguration config = DataManager.getConfiguration(file);
            config.set("Event", false);
            config.set("EventSpect", false);
            try { config.save(file); } catch (IOException e) { Bukkit.getLogger().warning("[PlayerData] Falhou a salvar: " + player.getName()); }
        }
    }

    public static void comtagemEvento() {
        if (!contagemI && EventPlayerManager.getPlayerCount() >= 8 || EventData.ForceStart) {
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
                if (tempoRestante == 0 || EventData.ForceSkip) iniciarJogo();
                else if (isTimeToNotify(tempoRestante)) notificacaoContagem(tempoRestante);
                tempoRestante--;
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
                player.sendTitle("§8[§cTNT-TAG§8]", "§7INICIANDO EM§8: §c" + tempoRestante + "s", 10, 70, 20);
                player.playSound(player, Sound.BLOCK_NOTE_BLOCK_BANJO, 1.0f, 1.0f);
            }
        });
        if (tempoRestante == 5) teleportarJogadores();
    }

    private static void teleportarJogadores() {
        Bukkit.getOnlinePlayers().forEach(player -> {
            if (Utils.isPlayerInEvent(player)) player.teleport(Locations.tnttagA, PlayerTeleportEvent.TeleportCause.COMMAND);
        });
    }

    private static void iniciarJogo() {
        contagem = false;
        if (EventData.ForceSkip) teleportarJogadores();
        jogadores.addAll(EventPlayerManager.getPlayerManager());
        if (jogadores.size() >= 2) {
            PEGOS = jogadores.get(random.nextInt(jogadores.size()));
            do { PEGOS2 = jogadores.get(random.nextInt(jogadores.size())); } while (PEGOS.equals(PEGOS2));
            tnt.add(PEGOS);
            tnt.add(PEGOS2);
            aplicarEfeitos(PEGOS);
            aplicarEfeitos(PEGOS2);
        }
        contagemtemp.cancel();
    }

    private static void aplicarEfeitos(Player player) {
        player.getInventory().setHelmet(EventItem.getItem("TNTHEAD"));
        player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, Integer.MAX_VALUE, 0, false, false));
    }

    public static void finalizar() {
        Player vencedor = EventPlayerManager.getPlayerManager().stream().filter(player -> !mortos.contains(player)).findFirst().orElse(null);
        if (vencedor == null) return;
        MineSkyEvents.event = "OFF";
        Bukkit.getOnlinePlayers().forEach(player -> {
            if (Utils.isPlayerInEvent(player) || Utils.isPlayerSpectating(player)) {
                EventCommand.RevealPlayer(player);
                if (!player.hasPermission("mineskyevents.bypass.join")) SendMessages.sendConnection(player);
                salvarConfig(player);
            }
        });

        random = new Random();

        int bound1 = Config.SPLEEF_MAX_1 - Config.SPLEEF_MIN_1 + 1;


        int premio1;


        if (bound1 > 0) {
            premio1 = random.nextInt(bound1) + Config.SPLEEF_MIN_1;
        } else {
            premio1 = Config.SPLEEF_MIN_1; // Ou algum valor padrão
        }



        Vault.economy.depositPlayer(Bukkit.getOfflinePlayer(vencedor.getName()), premio1);
        SendMessages.sendPlayerMessage(vencedor, new TextComponent("§c§lTNT-TAG §8| §aVocê ganhou o §lTNTTag §ae como prêmio você ganhou: §l$" + premio1));
        SendMessages.sendMessage(new TextComponent("§c§lTNT-TAG §8| §aVencedor: §a§l" + vencedor.getName()));
        EventPlayerManager.clearPlayerManager();
        EventPlayerManager.clearPlayerItem();
        mortos.clear();
        jogadores.clear();
        tnt.clear();
    }
}
