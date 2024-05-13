package minesky.msne.commands;

import minesky.msne.MineSkyEvents;
import minesky.msne.addons.Vault;
import minesky.msne.config.DataManager;
import minesky.msne.discord.EventsMessage;
import minesky.msne.events.CorridaBoatEvent;
import minesky.msne.events.CorridaEvent;
import minesky.msne.utils.Util;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.*;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class MSNECommand implements CommandExecutor, TabCompleter {

    public static List<String> playerList = new ArrayList<>();
    public static Map<String, Integer> playerBOATLIST = new HashMap<>();
    private static final long TEMPO_PADRAO = 60000;
    public static Map<String, Long> ultimoRenovacao = new HashMap<>();
    public static int premio1;
    public static int premio2;
    public static int premio3;

    @Override
    public List<String> onTabComplete(CommandSender s, Command cmd, String lbl, String[] args) {
        List<String> strings = new ArrayList<>();
        if (args.length == 1) {
            strings.add("notification");
            if (s.hasPermission("mineskyevents.reload")) strings.add("reload");
            return strings;
        }
        return strings;
    }

    @Override
    public boolean onCommand(CommandSender s, Command cmd, String lbl, String[] args) {
        Player player = (Player) s;
        String version = MineSkyEvents.get().getDescription().getVersion();
        if (args.length < 1){
            player.sendMessage("§6§lMinesky Events §8» §6v" + version + "\n§6Notification §8- §7Enable and disable notifications\n§6Reload §8- §7Reload the plugin.\n\n§6Author§8: §7zBrunoC_");
            return true;
        }
        if (args[0].equalsIgnoreCase("adopt")) {
            if (args.length < 2) {
                player.sendMessage("§8[§c!§8] §cInforme um player");
                return true;
            }
            Player adotar = Bukkit.getPlayer(args[1]);
            if (adotar != null && adotar.isOnline()){
                TextComponent messageADOPT = new TextComponent("§b" + player.getName() + " §7adotou §b" + adotar.getName());
                    Util.sendMessageBCMSNE(messageADOPT);
                } else {
                    player.sendMessage("§8[§c!§8] §cEste player não está online no momento");
            }
            return true;
        }
        if (args[0].equalsIgnoreCase("give")) {
            player.getInventory().addItem(Util.StoneShovel);
            player.getInventory().addItem(Util.SumoItem);
            s.sendMessage("§8[§a!§8] §aVocê pegou a pá de ouro do evento §lSPLEEF §ae também pegou o stick do evento §lSUMO");
            return true;
        }
        if (args[0].equalsIgnoreCase("racefinal")) {
            String playerName = player.getName();
            if (playerList.contains(playerName)) {
                player.sendMessage("§8[§c!§8] §cVocê já chegou!");
                return true;
            }
            playerList.add(playerName);
            player.sendMessage("§8[§a!§8] §aVocê ultrappasou a linha de chegada!");

            if (playerList.size() >= 3) {
                MineSkyEvents.event = "OFF";
                String message = "§e§lCorrida §8| §a";
                for (int i = 0; i < 3; i++) {
                    String ganhador = playerList.get(i);
                    Player ganhadorPlayer = player.getServer().getPlayer(ganhador);
                    message += (i + 1) + "º lugar: §l" + playerList.get(i) + " §8|§a ";
                    int premio = 0;
                    Random random = new Random();
                    if (i == 0) {
                        premio =  random.nextInt(5500 - 4500 + 1) + 4500;
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
                    OfflinePlayer poff = Bukkit.getOfflinePlayer(playerList.get(i));
                    Player p = Bukkit.getPlayer(playerList.get(i));
                    Vault.economy.depositPlayer(poff, premio);
                    TextComponent text1 = new TextComponent("§e§lCorrida §8| §aVocê ganhou a §lCorrida §ae como prêmio você ganhou: §l$" + premio);
                    Util.sendPlayermessage(p, text1);
                    File file = DataManager.getFile(ganhadorPlayer.getName().toLowerCase(), "playerdata");
                    FileConfiguration config = DataManager.getConfiguration(file);

                    config.set("Events.Corrida.win", config.getInt("Events.Corrida.win")+1);
                    try {
                        config.save(file);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }

                }
                message = message.substring(0, message.length() - 2);
                TextComponent text = new TextComponent(message);
                Util.sendMessageBCMSNE(text);
                EventsMessage.sendLogEventPLIST("Corrida", playerList, premio1, premio2, premio3);
                playerList.clear();
                CorridaEvent.playerson.clear();
                for (Player p : Bukkit.getOnlinePlayers()) {
                    if (Util.PDVE(p) || Util.PDVES(p)) {
                        Util.sendConectionBCMSNE(p);
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
            return true;
        }
        if (args[0].equalsIgnoreCase("raceboatfinal")) {
            if (!podeRenovar(player)) return true;
            String playerName = player.getName();
            if (playerBOATLIST.getOrDefault(playerName, 0) >= 3) {
                player.sendMessage("§8[§c!§8] §cVocê já completou as 3 voltas!");
                return true;
            }
            int lapCount = playerBOATLIST.getOrDefault(playerName, 0);
            int lapCountADD = lapCount+1;
            playerBOATLIST.put(playerName, lapCount + 1);
            player.sendMessage("§8[§a!§8] §aVocê completou a sua §l" + lapCountADD + "º volta!");
            renovarTempo(player);
            if (lapCount + 1 == 3) {
                playerList.add(playerName);
                player.sendMessage("§8[§a!§8] §aVocê completou as§l 3 §avoltas!");
                if (playerList.size() >= 3) {
                    MineSkyEvents.event = "OFF";
                    String message = "§9§lCorrida de barco §8| §a";
                    for (int i = 0; i < 3; i++) {
                        String ganhador = playerList.get(i);
                        Player ganhadorPlayer = player.getServer().getPlayer(ganhador);
                        message += (i + 1) + "º lugar: §l" + playerList.get(i) + " §8|§a ";
                        int premio = 0;
                        Random random = new Random();
                        if (i == 0) {
                            premio =  random.nextInt(5500 - 4500 + 1) + 4500;
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
                        OfflinePlayer poff = Bukkit.getOfflinePlayer(playerList.get(i));
                        Player p = Bukkit.getPlayer(playerList.get(i));
                        Vault.economy.depositPlayer(poff, premio);
                        TextComponent text1 = new TextComponent("§9§lCorrida de barco §8| §aVocê ganhou a §lCorrida de barco §ae como prêmio você ganhou: §l$" + premio);
                        Util.sendPlayermessage(p, text1);
                        File file = DataManager.getFile(ganhadorPlayer.getName().toLowerCase(), "playerdata");
                        FileConfiguration config = DataManager.getConfiguration(file);

                        config.set("Events.CorridaBoat.win", config.getInt("Events.CorridaBoat.win")+1);
                        try {
                            config.save(file);
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }

                    }
                    message = message.substring(0, message.length() - 2);
                    TextComponent text = new TextComponent(message);
                    Util.sendMessageBCMSNE(text);
                    EventsMessage.sendLogEventPLIST("CorridaBoat", playerList, premio1, premio2, premio3);
                    playerList.clear();
                    playerBOATLIST.clear();
                    CorridaBoatEvent.playerson.clear();
                    for (Player p : Bukkit.getOnlinePlayers()) {
                        if (Util.PDVE(p) || Util.PDVES(p)) {
                            Util.sendConectionBCMSNE(p);
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
            return true;
        }
        if (args[0].equalsIgnoreCase("reload")) {
            MineSkyEvents.carregarConfigs();
            player.sendMessage("§6§lMinesky Events §8» §6v" + version + "\n§6Author§8: §7zBrunoC_\n§6Version§8: §7" + version + "\n\n§6MineSky Events §7reloaded successfully.\n§7NOTE: Some changes will only take effect when the server is restarted.");
            return true;
        }
        player.sendMessage("§8[§c!§8] §cVocê não informou um argumento certo.");
        return true;
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
