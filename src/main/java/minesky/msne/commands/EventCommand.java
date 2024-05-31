package minesky.msne.commands;

import minesky.msne.MineSkyEvents;
import minesky.msne.commands.console.EventCommandConsole;
import minesky.msne.config.*;
import minesky.msne.events.*;
import minesky.msne.system.event.EventCorridasPlayerManager;
import minesky.msne.system.event.EventPlayerManager;
import minesky.msne.system.event.EventVerification;
import minesky.msne.utils.EventItem;
import minesky.msne.utils.SendMessages;
import minesky.msne.utils.Util;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.command.*;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.w3c.dom.Text;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;


public class EventCommand implements CommandExecutor {
    public static String selectedMap = "Mapa-0";
    public static boolean Spectator;
    public static Location location = null;
    public static int PlayerSize = 0;
    public static int RequestPlayerSize = 1;
    public static boolean EventsAgendados = false;
    public static TextComponent textfinalizar = new TextComponent("EVENTO FINALIZADO");

    @Override
    public boolean onCommand(CommandSender s, Command cmd, String lbl, String[] args) {
        if (s instanceof ConsoleCommandSender || s instanceof RemoteConsoleCommandSender || s instanceof BlockCommandSender) {
            new EventCommandConsole(s, cmd, lbl, args);
            return true;
        }
        Player player = (Player) s;
        if (args.length < 1 || args[0].equalsIgnoreCase("entrar")) {
            if (MineSkyEvents.event.equalsIgnoreCase("OFF")) {
                s.sendMessage("§8[§c!§8] §cNenhum evento está acontecendo agora.");
                return true;
            }
            if (EventVerification.getBlacklist(player, MineSkyEvents.event)) {
                Bukkit.getLogger().warning("[MineSky-Events] " + player.getName() + " Tentou entrar mas está na blacklist do evento: " + MineSkyEvents.event);
                return true;
            }
            if (Util.PDVE(((Player) s).getPlayer())) {
                s.sendMessage("§8[§c!§8] §cVocê já entrou no evento");
                return true;
            }
            if (Util.PDVES(player)) {
                s.sendMessage("§8[§c!§8] §cVocê já está assistindo o evento");
                return true;
            }
            File file = DataManager.getFile(player.getName().toLowerCase(), "playerdata");
            FileConfiguration config = DataManager.getConfiguration(file);
            switch (MineSkyEvents.event) {
                case "Spleef":
                    if (!SpleefEvent.contagem && SpleefEvent.contagemI) {
                        Spectator = true;
                        location = Locations.spleefA;
                        break;
                    }
                    Spectator = false;
                    EventsAgendados = false;
                    EventPlayerManager.addPlayer(player);
                    if (!SpleefEvent.contagemI) SpleefEvent.comtagemEvento();
                    location = Locations.spleef;
                    PlayerSize = EventPlayerManager.getPlayerCount();
                    RequestPlayerSize = 4;
                break;
                case "TijolãoWars":
                    if (!TijolãoWarsEvent.contagem && TijolãoWarsEvent.contagemI) {
                        Spectator = true;
                        if (TijolãoWarsEvent.selectedMap.equals("Mapa1")) {
                            location = Locations.tijolaoA;
                        }
                        if (TijolãoWarsEvent.selectedMap.equals("Mapa2")) {
                            location = Locations.tijolao2A;
                        }
                        break;
                    }
                    Spectator = false;
                    EventsAgendados = false;
                    EventPlayerManager.addPlayer(player);
                    if (!TijolãoWarsEvent.contagemI) TijolãoWarsEvent.comtagemEvento();
                    if (TijolãoWarsEvent.selectedMap.equals("Mapa1")) {
                        location = Locations.tijolao;
                    }
                    if (TijolãoWarsEvent.selectedMap.equals("Mapa2")) {
                        location = Locations.tijolao2;
                    }
                    PlayerSize = EventPlayerManager.getPlayerCount();
                    RequestPlayerSize = 4;
                break;
                case "Corrida":
                    if (!CorridaEvent.contagem && CorridaEvent.contagemI) {
                        Spectator = true;
                        location = Locations.corridaA;
                        break;
                    }
                    Spectator = false;
                    EventsAgendados = false;
                    EventPlayerManager.addPlayer(player);
                    if (!CorridaEvent.contagemI) CorridaEvent.comtagemEvento();
                    location = Locations.corrida;
                    PlayerSize = EventPlayerManager.getPlayerCount();
                    RequestPlayerSize = 4;
                break;
                case "CorridaBoat":
                    if (!CorridaBoatEvent.contagem && CorridaBoatEvent.contagemI) {
                        Spectator = true;
                        location = Locations.corridaboatA;
                        break;
                    }
                    Spectator = false;
                    EventsAgendados = false;
                    EventPlayerManager.addPlayer(player);
                    if (!CorridaBoatEvent.contagemI) CorridaBoatEvent.comtagemEvento();
                    location = Locations.corridaboat;
                    PlayerSize = EventPlayerManager.getPlayerCount();
                    RequestPlayerSize = 4;
                break;
                case "Sumo":
                    if (!SumoEvent.contagem && SumoEvent.contagemI) {
                        Spectator = true;
                        location = Locations.sumoA;
                        break;
                    }
                    Spectator = false;
                    EventsAgendados = false;
                    EventPlayerManager.addPlayer(player);
                    if (!SumoEvent.contagemI) SumoEvent.comtagemEvento();
                    location = Locations.sumo;
                    PlayerSize = EventPlayerManager.getPlayerCount();
                    RequestPlayerSize = 4;
                break;
                case "TNTRun":
                    if (!TNTRunEvent.contagem && TNTRunEvent.contagemI) {
                        Spectator = true;
                        if (TNTRunEvent.selectedMap.equals("Mapa1")) {
                            location = Locations.tntrunA;
                        }
                        if (TNTRunEvent.selectedMap.equals("Mapa2")) {
                            location = Locations.tntrun2A;
                        }
                        break;
                    }
                    Spectator = false;
                    EventsAgendados = false;
                    EventPlayerManager.addPlayer(player);
                    if (!TNTRunEvent.contagemI) TNTRunEvent.comtagemEvento();
                    if (TNTRunEvent.selectedMap.equals("Mapa1")) {
                        location = Locations.tntrun2;
                    }
                    if (TNTRunEvent.selectedMap.equals("Mapa2")) {
                        location = Locations.tntrun;
                    }
                    PlayerSize = EventPlayerManager.getPlayerCount();
                    RequestPlayerSize = 4;
                break;
                case "TNTTag":
                    if (!TNTTagEvent.contagem && TNTTagEvent.contagemI) {
                        Spectator = true;
                        location = Locations.tnttagA;
                        break;
                    }
                    Spectator = false;
                    EventsAgendados = false;
                    EventPlayerManager.addPlayer(player);
                    if (!TNTTagEvent.contagemI) TNTTagEvent.comtagemEvento();
                    location = Locations.tnttag;
                    PlayerSize = EventPlayerManager.getPlayerCount();
                    RequestPlayerSize = 8;
                break;
                case "Parapente":
                    if (!ParapenteEvent.contagem && ParapenteEvent.contagemI) {
                        Spectator = true;
                        location = Locations.parapenteA;
                        break;
                    }
                    Spectator = false;
                    EventsAgendados = false;
                    EventPlayerManager.addPlayer(player);
                    if (!ParapenteEvent.contagemI) ParapenteEvent.comtagemEvento();
                    location = Locations.parapente;
                    PlayerSize = EventPlayerManager.getPlayerCount();
                    RequestPlayerSize = 4;
                break;
                case "Mini-Wars":
                    EventsAgendados = true;
                    Spectator = false;
                    if (selectedMap.equalsIgnoreCase("Mapa-1")) {
                        location = Locations.miniwars;
                    }
                    if (selectedMap.equalsIgnoreCase("Mapa-2")) {
                        location = Locations.miniwars2;
                    }
                    if (selectedMap.equalsIgnoreCase("Mapa-3")) {
                        location = Locations.miniwars3;
                    }
                    if (selectedMap.equalsIgnoreCase("Mapa-4")) {
                        location = Locations.miniwars4;
                    }
                    if (selectedMap.equalsIgnoreCase("Mapa-5")) {
                        location = Locations.miniwars5;
                    }
                break;
                case "CopaPVP":
                    EventsAgendados = true;
                    Spectator = false;
                    if (selectedMap.equalsIgnoreCase("Mapa-1")) {
                        location = Locations.copapvp;
                    }
                break;
                case "Esconde-esconde":
                    EventsAgendados = true;
                    Spectator = false;
                    if (selectedMap.equalsIgnoreCase("Mapa-1")) {
                        location = Locations.esconde;
                    }
                    if (selectedMap.equalsIgnoreCase("Mapa-2")) {
                        location = Locations.esconde2;
                    }
                break;
                case "Ruínas":
                    EventsAgendados = true;
                    Spectator = false;
                    if (selectedMap.equalsIgnoreCase("Mapa-1")) {
                        location = Locations.ruinas;
                    }
                break;
            }
            if (EventsAgendados) {
                Bukkit.getScheduler().runTaskLater(MineSkyEvents.get(), () -> {
                    player.teleport(location, PlayerTeleportEvent.TeleportCause.COMMAND);
                    player.playSound(player, Sound.BLOCK_NOTE_BLOCK_BIT, 1.0f , 1.0f);
                    s.sendMessage("§8[§a!§8] §aVocê entrou no evento!");
                }, 20L);
                for (Player p : Bukkit.getOnlinePlayers()) {
                    p.sendMessage("§7" + player.getName() + " §eentrou no evento.");
                }
                return true;
            }
            if (Spectator) {
                PlayerSpectator(player);
                clearInventory(player);
                player.getInventory().setItem(8, EventItem.BedLeave);
                player.getInventory().setItem(4, EventItem.HeadEvents(player));
                Bukkit.getScheduler().runTaskLater(MineSkyEvents.get(), () -> {
                    player.teleport(location, PlayerTeleportEvent.TeleportCause.COMMAND);
                    player.sendMessage("§8[§a!§8] §aVocê começou a assistir o evento!");
                    player.playSound(player, Sound.BLOCK_NOTE_BLOCK_BIT, 1.0f , 1.0f);
                }, 20L);
                config.set("EventSpect", true);
                try {
                    config.save(file);
                } catch (IOException e) {
                    Bukkit.getLogger().warning("[MineSky-Events] Ocorreu um erro ao salvar a player do jogador " + player.getName());
                    SendMessages.sendConectionBCMSNE(player);
                    TextComponent erro = new TextComponent("§8[§c!§8] §cOcorreu um erro ao salvar sua playerdata para o evento!");
                    SendMessages.sendPlayermessage(player, erro);
                    e.printStackTrace();
                }
                return true;
            }
            config.set("Event", true);
            try {
                config.save(file);
                clearInventory(player);
                player.getInventory().setItem(8, EventItem.BedLeave);
                player.getInventory().setItem(4, EventItem.HeadEvents(player));
                Bukkit.getScheduler().runTaskLater(MineSkyEvents.get(), () -> {
                    player.teleport(location, PlayerTeleportEvent.TeleportCause.COMMAND);
                    player.playSound(player, Sound.BLOCK_NOTE_BLOCK_BIT, 1.0f , 1.0f);
                    s.sendMessage("§8[§a!§8] §aVocê entrou no evento!");
                }, 20L);
                for (Player p : Bukkit.getOnlinePlayers()) {
                    p.sendMessage("§7" + player.getName() + " §eentrou no evento. (§b" + PlayerSize + "§e/§b" + RequestPlayerSize + "§e)");
                }
            } catch (IOException e) {
                Bukkit.getLogger().warning("[MineSky-Events] Ocorreu um erro ao salvar a player do jogador " + player.getName());
                SendMessages.sendConectionBCMSNE(player);
                TextComponent erro = new TextComponent("§8[§c!§8] §cOcorreu um erro ao salvar sua playerdata para o evento!");
                SendMessages.sendPlayermessage(player, erro);
                e.printStackTrace();
            }
            return true;
        }
        if (args[0].equalsIgnoreCase("anunciar")) {
            if (!s.hasPermission("mineskyevents.command.event.anunciar")) {
                s.sendMessage("§8[§c!§8] §cVocê não pode executar esse comando.");
                return true;
            }
            if (MineSkyEvents.event.equals("OFF") && args.length < 2) {
                s.sendMessage("§8[§c!§8] §cNenhum evento esta acontecendo agora.");
                return true;
            }
            switch (MineSkyEvents.event) {
                case "Spleef":
                    SendMessages.sendMessageBGMSNE("Spleef");
                    break;
                case "TijolãoWars":
                    SendMessages.sendMessageBGMSNE("TijolãoWars");
                    break;
                case "Corrida":
                    SendMessages.sendMessageBGMSNE("Corrida");
                    break;
                case "TNTRun":
                    SendMessages.sendMessageBGMSNE("TNTRun");
                    break;
                case "CorridaBoat":
                    SendMessages.sendMessageBGMSNE("CorridaBoat");
                    break;
                case "Sumo":
                    SendMessages.sendMessageBGMSNE("Sumo");
                    break;
                case "TNTTag":
                    SendMessages.sendMessageBGMSNE("TNTTag");
                    break;
                case "Parapente":
                    SendMessages.sendMessageBGMSNE("Parapente");
                    break;
            }
            switch (args[1].toLowerCase()) {
                case "mini-wars":
                    if (args.length < 3) {
                        s.sendMessage("§8[§c!§8] §cVocê deve informar um mapa!");
                        return true;
                    }
                    int selectMap = Integer.parseInt(args[2]);
                    if (selectMap == 1) {
                        selectedMap = "Mapa-1";
                    } else if (selectMap == 2) {
                        selectedMap = "Mapa-2";
                    } else if (selectMap == 3) {
                        selectedMap = "Mapa-3";
                    } else if (selectMap == 4) {
                        selectedMap = "Mapa-4";
                    } else if (selectMap == 5) {
                        selectedMap = "Mapa-5";
                    }
                    if (selectMap > 5) {
                        s.sendMessage("§8[§c!§8] §cEste evento só tem 5 mapas!");
                        return true;
                    }
                    MineSkyEvents.event = "Mini-Wars";
                    SendMessages.sendMessageBGMSNE("Mini-Wars");
                    break;
                case "copapvp":
                    if (args.length < 3) return true;
                    int selectMapC = Integer.parseInt(args[2]);
                    if (selectMapC == 1) {
                        selectedMap = "Mapa-1";
                    }
                    if (selectMapC > 1) {
                        s.sendMessage("§8[§c!§8] §cEste evento só tem 1 mapas!");
                        return true;
                    }
                    MineSkyEvents.event = "CopaPVP";
                    SendMessages.sendMessageBGMSNE("CopaPVP");
                    break;
                case "esconde-esconde":
                    if (args.length < 3) return true;
                    int selectMapE = Integer.parseInt(args[2]);
                    if (selectMapE == 1) {
                        selectedMap = "Mapa-1";
                    } else if (selectMapE == 2) {
                        selectedMap = "Mapa-2";
                    }
                    if (selectMapE > 2) {
                        s.sendMessage("§8[§c!§8] §cEste evento só tem 2 mapas!");
                        return true;
                    }
                    MineSkyEvents.event = "Esconde-esconde";
                    SendMessages.sendMessageBGMSNE("Esconde-esconde");
                    break;
                case "ruínas":
                    if (args.length < 3) return true;
                    int selectMapR = Integer.parseInt(args[2]);
                    if (selectMapR == 1) {
                        selectedMap = "Mapa-1";
                    }
                        if (selectMapR > 1) {
                        s.sendMessage("§8[§c!§8] §cEste evento só tem 1 mapas!");
                        return true;
                    }
                    MineSkyEvents.event = "Ruínas";
                    SendMessages.sendMessageBGMSNE("Ruínas");
                    break;
            }
            Bukkit.getLogger().warning("[MineSky-Events] " + player.getName() + " anúnciou o evento: " + MineSkyEvents.event);
            player.playSound(player, Sound.BLOCK_NOTE_BLOCK_BIT, 1.0f , 1.0f);
            return true;
        }
        if (args[0].equalsIgnoreCase("finalizar")) {
            if (!player.hasPermission("mineskyevents.command.event.finalizar")) {
                s.sendMessage("§8[§c!§8] §cVocê não pode executar esse comando.");
                return true;
            }
            if (MineSkyEvents.event.equalsIgnoreCase("OFF")) {
                s.sendMessage("§8[§c!§8] §cNenhum evento acontecendo agora.");
                return true;
            }
            String event = MineSkyEvents.event;

            switch (MineSkyEvents.event) {
                case "Spleef":
                    MineSkyEvents.event = "OFF";
                    textfinalizar = new TextComponent("§b§lSpleef §8| §aEvento finalizado!");
                    for (Player j : Bukkit.getOnlinePlayers()) {
                        if (Util.PDVE(j) || Util.PDVES(j)) {
                            SendMessages.sendConectionBCMSNE(j);
                            RevealPlayer(j);
                            File file = DataManager.getFile(j.getName().toLowerCase(), "playerdata");
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
                    SpleefEvent.mortos.clear();
                    EventPlayerManager.clearPlayerManager();
                    EventPlayerManager.clearPlayerItem();
                    SpleefEvent.contagem = true;
                    SpleefEvent.contagemI = false;
                    SpleefEvent.temporizador.cancel();
                    SpleefEvent.restaurar();
                    if (SpleefEvent.contagemI) {
                        SpleefEvent.contagemtemp.cancel();
                    }
                    break;
                case "TijolãoWars":
                    MineSkyEvents.event = "OFF";
                    textfinalizar = new TextComponent("§6§lTijolãoWars §8| §aEvento finalizado!");
                    for (Player j : Bukkit.getOnlinePlayers()) {
                        if (Util.PDVE(j) || Util.PDVES(j)) {
                            SendMessages.sendConectionBCMSNE(j);
                            RevealPlayer(j);
                            File file = DataManager.getFile(j.getName().toLowerCase(), "playerdata");
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
                    TijolãoWarsEvent.mortos.clear();
                    EventPlayerManager.clearPlayerManager();
                    EventPlayerManager.clearPlayerItem();
                    TijolãoWarsEvent.contagem = true;
                    TijolãoWarsEvent.contagemI = false;
                    TijolãoWarsEvent.temporizador.cancel();
                    if (TijolãoWarsEvent.contagemI) {
                        TijolãoWarsEvent.contagemtemp.cancel();
                    }
                    break;
                case "Corrida":
                    MineSkyEvents.event = "OFF";
                    textfinalizar = new TextComponent("§e§lCorrida §8| §aEvento finalizado!");
                    for (Player j : Bukkit.getOnlinePlayers()) {
                        if (Util.PDVE(j) || Util.PDVES(j)) {
                            SendMessages.sendConectionBCMSNE(j);
                            RevealPlayer(j);
                            File file = DataManager.getFile(j.getName().toLowerCase(), "playerdata");
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
                    EventCorridasPlayerManager.clearPlayerManager();
                    EventPlayerManager.clearPlayerManager();
                    EventPlayerManager.clearPlayerItem();
                    CorridaEvent.contagem = true;
                    CorridaEvent.contagemI = false;
                    CorridaEvent.temporizador.cancel();
                    if (CorridaEvent.contagemI) {
                        CorridaEvent.contagemtemp.cancel();
                    }
                    break;
                case "CorridaBoat":
                    MineSkyEvents.event = "OFF";
                    textfinalizar = new TextComponent("§9§lCorrida de barco §8| §aEvento finalizado!");
                    for (Player j : Bukkit.getOnlinePlayers()) {
                        if (Util.PDVE(j) || Util.PDVES(j)) {
                            SendMessages.sendConectionBCMSNE(j);
                            RevealPlayer(j);
                            File file = DataManager.getFile(j.getName().toLowerCase(), "playerdata");
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
                    EventCorridasPlayerManager.clearPlayerManager();
                    EventPlayerManager.clearPlayerItem();
                    CorridaBoatEvent.playerBOATLIST.clear();
                    EventPlayerManager.clearPlayerManager();
                    CorridaBoatEvent.contagem = true;
                    CorridaBoatEvent.contagemI = false;
                    CorridaBoatEvent.temporizador.cancel();
                    if (CorridaBoatEvent.contagemI) {
                        CorridaBoatEvent.contagemtemp.cancel();
                    }
                    break;
                case "Sumo":
                    MineSkyEvents.event = "OFF";
                    textfinalizar = new TextComponent("§4§lSumo §8| §aEvento finalizado!");
                    for (Player j : Bukkit.getOnlinePlayers()) {
                        if (Util.PDVE(j) || Util.PDVES(j)) {
                            SendMessages.sendConectionBCMSNE(j);
                            RevealPlayer(j);
                            File file = DataManager.getFile(j.getName().toLowerCase(), "playerdata");
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
                    SumoEvent.mortos.clear();
                    EventPlayerManager.clearPlayerManager();
                    EventPlayerManager.clearPlayerItem();
                    SumoEvent.contagem = true;
                    SumoEvent.contagemI = false;
                    SumoEvent.temporizador.cancel();
                    if (SumoEvent.contagemI) {
                        SumoEvent.contagemtemp.cancel();
                    }
                    break;
                case "TNTRun":
                    MineSkyEvents.event = "OFF";
                    textfinalizar = new TextComponent("§c§lTNTRUN §8| §aEvento finalizado!");
                    for (Player j : Bukkit.getOnlinePlayers()) {
                        if (Util.PDVE(j) || Util.PDVES(j)) {
                            SendMessages.sendConectionBCMSNE(j);
                            RevealPlayer(j);
                            File file = DataManager.getFile(j.getName().toLowerCase(), "playerdata");
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
                    TNTRunEvent.mortos.clear();
                    EventPlayerManager.clearPlayerManager();
                    EventPlayerManager.clearPlayerItem();
                    TNTRunEvent.contagem = true;
                    TNTRunEvent.contagemI = false;
                    TNTRunEvent.temporizador.cancel();
                    TNTRunEvent.restaurar();
                    if (TNTRunEvent.contagemI) {
                        TNTRunEvent.contagemtemp.cancel();
                    }
                    break;
                case "TNTTag":
                    MineSkyEvents.event = "OFF";
                    textfinalizar = new TextComponent("§c§lTNT-TAG §8| §aEvento finalizado!");
                    for (Player j : Bukkit.getOnlinePlayers()) {
                        if (Util.PDVE(j) || Util.PDVES(j)) {
                            SendMessages.sendConectionBCMSNE(j);
                            RevealPlayer(j);
                            File file = DataManager.getFile(j.getName().toLowerCase(), "playerdata");
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
                    TNTTagEvent.mortos.clear();
                    EventPlayerManager.clearPlayerManager();
                    EventPlayerManager.clearPlayerItem();
                    TNTTagEvent.jogadores.clear();
                    TNTTagEvent.tnt.clear();
                    TNTTagEvent.contagem = true;
                    TNTTagEvent.contagemI = false;
                    TNTTagEvent.temporizador.cancel();
                    if (TNTTagEvent.contagemI) {
                        TNTTagEvent.contagemtemp.cancel();
                        if (!TNTTagEvent.contagem && TNTTagEvent.contagemI) {
                            TNTTagEvent.temp1.cancel();
                            TNTTagEvent.temp2.cancel();
                        }
                    }
                    break;
                case "Parapente":
                    MineSkyEvents.event = "OFF";
                    textfinalizar = new TextComponent("§3§lCorrida de parapente §8| §aEvento finalizado!");
                    for (Player j : Bukkit.getOnlinePlayers()) {
                        if (Util.PDVE(j) || Util.PDVES(j)) {
                            SendMessages.sendConectionBCMSNE(j);
                            RevealPlayer(j);
                            File file = DataManager.getFile(j.getName().toLowerCase(), "playerdata");
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
                    EventCorridasPlayerManager.clearPlayerManager();
                    EventPlayerManager.clearPlayerItem();
                    ParapenteEvent.playerCHECKPOINT.clear();
                    ParapenteEvent.playerARCOLIST.clear();
                    EventPlayerManager.clearPlayerManager();
                    ParapenteEvent.contagem = true;
                    ParapenteEvent.contagemI = false;
                    ParapenteEvent.temporizador.cancel();
                    if (ParapenteEvent.contagemI) {
                        ParapenteEvent.contagemtemp.cancel();
                    }
                    break;
                case "Mini-Wars":
                    EventsAgendados = false;
                    MineSkyEvents.event = "OFF";
                    textfinalizar = new TextComponent("§6§lMINI-WARS §8| §aEvento finalizado!");
                    for (Player j : Bukkit.getOnlinePlayers()) {
                        if (Util.PDVE(j) || Util.PDVES(j)) {
                            SendMessages.sendConectionBCMSNE(j);
                            RevealPlayer(j);
                            File file = DataManager.getFile(j.getName().toLowerCase(), "playerdata");
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
                    break;
                case "CopaPVP":
                    EventsAgendados = false;
                    MineSkyEvents.event = "OFF";
                    textfinalizar = new TextComponent("§6§lCopaPVP §8| §aEvento finalizado!");
                    for (Player j : Bukkit.getOnlinePlayers()) {
                        if (Util.PDVE(j) || Util.PDVES(j)) {
                            SendMessages.sendConectionBCMSNE(j);
                            RevealPlayer(j);
                            File file = DataManager.getFile(j.getName().toLowerCase(), "playerdata");
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
                    break;
                case "Esconde-esconde":
                    EventsAgendados = false;
                    MineSkyEvents.event = "OFF";
                    textfinalizar = new TextComponent("§d§lEsconde-esconde §8| §aEvento finalizado!");
                    for (Player j : Bukkit.getOnlinePlayers()) {
                        if (Util.PDVE(j) || Util.PDVES(j)) {
                            SendMessages.sendConectionBCMSNE(j);
                            RevealPlayer(j);
                            File file = DataManager.getFile(j.getName().toLowerCase(), "playerdata");
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
                    break;
                case "Ruínas":
                    EventsAgendados = false;
                    MineSkyEvents.event = "OFF";
                    textfinalizar = new TextComponent("§5§lRuínas §8| §aEvento finalizado!");
                    for (Player j : Bukkit.getOnlinePlayers()) {
                        if (Util.PDVE(j) || Util.PDVES(j)) {
                            SendMessages.sendConectionBCMSNE(j);
                            RevealPlayer(j);
                            File file = DataManager.getFile(j.getName().toLowerCase(), "playerdata");
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
                    break;
            }
            SendMessages.sendMessageBCMSNE(textfinalizar);
            player.playSound(player, Sound.BLOCK_NOTE_BLOCK_BIT, 1.0f , 1.0f);
            Bukkit.getLogger().warning("[MineSky-Events] " + player.getName() + " finalizou o evento: " + event);
            s.sendMessage("§8[§a!§8] §aVocê finalizou o evento: " + event);
            return true;
        }
        if (args[0].equalsIgnoreCase("start")) {
            if (!s.hasPermission("mineskyevents.command.event.start")) {
                s.sendMessage("§8[§c!§8] §cVocê não pode executar esse comando.");
                return true;
            }
            if (args.length < 2) {
                s.sendMessage("§8[§c!§8] §cInforme um evento");
                return true;
            }
            Bukkit.getLogger().warning("[MineSky-Events] " + player.getName() + " iniciou o evento: " + args[1]);
            switch (args[1].toLowerCase()) {
                case "spleef":
                    SpleefEvent.iniciarEvento();
                    break;
                case "tijolãowars":
                    TijolãoWarsEvent.iniciarEvento();
                    break;
                case "runners":
                    //RunnersEvent.iniciarEvento();
                    s.sendMessage("§8[§c!§8] §cEvento indisponível.");
                    break;
                case "corrida":
                    CorridaEvent.iniciarEvento();
                    break;
                case "corridaboat":
                    CorridaBoatEvent.iniciarEvento();
                    break;
                case "pegabandeira":
                    //orridaEvent.iniciarEvento();
                    s.sendMessage("§8[§c!§8] §cEvento indisponível.");
                    break;
                case "tntrun":
                    TNTRunEvent.iniciarEvento();
                    break;
                case "sumo":
                    SumoEvent.iniciarEvento();
                    break;
                case "tnttag":
                    TNTTagEvent.iniciarEvento();
                    break;
                case "parapente":
                    ParapenteEvent.iniciarEvento();
                    break;
                case "mini-wars":
                    s.sendMessage("§8[§c!§8] §cEste evento não pode ser iniciado. Usage: /event anunciar Mini-Wars (mapa)");
                    break;
                case "copapvp":
                    s.sendMessage("§8[§c!§8] §cEste evento não pode ser iniciado. Usage: /event anunciar CopaPVP (mapa)");
                    break;
                case "esconde-esconde":
                    s.sendMessage("§8[§c!§8] §cEste evento não pode ser iniciado. Usage: /event anunciar Esconde-esconde (mapa)");
                    break;
                case "ruínas":
                    s.sendMessage("§8[§c!§8] §cEste evento não pode ser iniciado. Usage: /event anunciar Ruínas (mapa)");
                    break;
            }
            player.playSound(player, Sound.BLOCK_NOTE_BLOCK_BIT, 1.0f , 1.0f);
            return true;
        }
        if (args[0].equalsIgnoreCase("set")) {
            if (!s.hasPermission("mineskyevents.command.event.set")) {
                s.sendMessage("§8[§c!§8] §cVocê não pode executar esse comando.");
                return true;
            }
            if (args.length < 3) {
                s.sendMessage("§8[§c!§8] §cUso incorreto. Use /event set (Evento) (Arena/Spawn)");
                return true;
            }
            int arg = Integer.parseInt(args[3]);
            if (args[1].equalsIgnoreCase("Spleef")) {
                if (args[3].equalsIgnoreCase("1")) {
                    if (args[2].equalsIgnoreCase("spawn")) {
                        Location loc = ((Player) s).getLocation();
                        File file = DataManager.getFile("locations.yml");
                        FileConfiguration config = DataManager.getConfiguration(file);

                        Locations.spleef = loc;
                        config.set("Spleef", loc);
                        config.set("Spleef.world", loc.getWorld().getName());
                        config.set("Spleef.x", Double.valueOf(loc.getX()));
                        config.set("Spleef.y", Double.valueOf(loc.getY()));
                        config.set("Spleef.z", Double.valueOf(loc.getZ()));
                        config.set("Spleef.yaw", Float.valueOf(loc.getYaw()));
                        config.set("Spleef.pitch", Float.valueOf(loc.getPitch()));
                        try {
                            config.save(file);
                            player.playSound(player, Sound.BLOCK_NOTE_BLOCK_BIT, 1.0f , 1.0f);
                            s.sendMessage("§8[§a!§8] §aSpawn de §lSpleef §asetado para os eventos com sucesso.");
                        } catch (IOException e) {
                            Bukkit.getConsoleSender().sendMessage(Messages.Falied_to_save.replace("%arquivo%", "locations.yml"));
                        }
                        return true;
                    }
                    if (args[2].equalsIgnoreCase("arena")) {
                        Location loc = ((Player) s).getLocation();
                        File file = DataManager.getFile("locations.yml");
                        FileConfiguration config = DataManager.getConfiguration(file);

                        Locations.spleefA = loc;
                        config.set("arena.Spleef", loc);
                        config.set("arena.Spleef.world", loc.getWorld().getName());
                        config.set("arena.Spleef.x", Double.valueOf(loc.getX()));
                        config.set("arena.Spleef.y", Double.valueOf(loc.getY()));
                        config.set("arena.Spleef.z", Double.valueOf(loc.getZ()));
                        config.set("arena.Spleef.yaw", Float.valueOf(loc.getYaw()));
                        config.set("arena.Spleef.pitch", Float.valueOf(loc.getPitch()));
                        try {
                            config.save(file);
                            player.playSound(player, Sound.BLOCK_NOTE_BLOCK_BIT, 1.0f , 1.0f);
                            s.sendMessage("§8[§a!§8] §aArena de §lSpleef §asetado para os eventos com sucesso.");
                        } catch (IOException e) {
                            Bukkit.getConsoleSender().sendMessage(Messages.Falied_to_save.replace("%arquivo%", "locations.yml"));
                        }
                        return true;
                    }
                } else {
                    player.playSound(player, Sound.BLOCK_NOTE_BLOCK_BASS, 1.0f , 1.0f);
                    s.sendMessage("§8[§c!§8] §cEste evento só tem 1 mapa.");
                }
            }
            if (args[1].equalsIgnoreCase("TijolãoWars")) {
                if (args[3].equalsIgnoreCase("1")) {
                    if (args[2].equalsIgnoreCase("spawn")) {
                        Location loc = ((Player) s).getLocation();
                        File file = DataManager.getFile("locations.yml");
                        FileConfiguration config = DataManager.getConfiguration(file);

                        Locations.tijolao = loc;
                        config.set("TijolãoWars.1", loc);
                        config.set("TijolãoWars.1.world", loc.getWorld().getName());
                        config.set("TijolãoWars.1.x", Double.valueOf(loc.getX()));
                        config.set("TijolãoWars.1.y", Double.valueOf(loc.getY()));
                        config.set("TijolãoWars.1.z", Double.valueOf(loc.getZ()));
                        config.set("TijolãoWars.1.yaw", Float.valueOf(loc.getYaw()));
                        config.set("TijolãoWars.1.pitch", Float.valueOf(loc.getPitch()));
                        try {
                            config.save(file);
                            player.playSound(player, Sound.BLOCK_NOTE_BLOCK_BIT, 1.0f , 1.0f);
                            s.sendMessage("§8[§a!§8] §aSpawn de §lTijolão Wars §8(§aMapa 1§8) §asetado para os eventos com sucesso.");
                        } catch (IOException e) {
                            Bukkit.getConsoleSender().sendMessage(Messages.Falied_to_save.replace("%arquivo%", "locations.yml"));
                        }
                        return true;
                    }
                    if (args[2].equalsIgnoreCase("arena")) {
                        Location loc = ((Player) s).getLocation();
                        File file = DataManager.getFile("locations.yml");
                        FileConfiguration config = DataManager.getConfiguration(file);

                        Locations.tijolaoA = loc;
                        config.set("arena.TijolãoWars.1", loc);
                        config.set("arena.TijolãoWars.1.world", loc.getWorld().getName());
                        config.set("arena.TijolãoWars.1.x", Double.valueOf(loc.getX()));
                        config.set("arena.TijolãoWars.1.y", Double.valueOf(loc.getY()));
                        config.set("arena.TijolãoWars.1.z", Double.valueOf(loc.getZ()));
                        config.set("arena.TijolãoWars.1.yaw", Float.valueOf(loc.getYaw()));
                        config.set("arena.TijolãoWars.1.pitch", Float.valueOf(loc.getPitch()));
                        try {
                            config.save(file);
                            player.playSound(player, Sound.BLOCK_NOTE_BLOCK_BIT, 1.0f , 1.0f);
                            s.sendMessage("§8[§a!§8] §aArena de §lTijolãoWars §8(§aMapa 1§8) §asetado para os eventos com sucesso.");
                        } catch (IOException e) {
                            Bukkit.getConsoleSender().sendMessage(Messages.Falied_to_save.replace("%arquivo%", "locations.yml"));
                        }
                        return true;
                    }
                } else if (args[3].equalsIgnoreCase("2")) {
                    if (args[2].equalsIgnoreCase("spawn")) {
                        Location loc = ((Player) s).getLocation();
                        File file = DataManager.getFile("locations.yml");
                        FileConfiguration config = DataManager.getConfiguration(file);

                        Locations.tijolao2 = loc;
                        config.set("TijolãoWars.2", loc);
                        config.set("TijolãoWars.2.world", loc.getWorld().getName());
                        config.set("TijolãoWars.2.x", Double.valueOf(loc.getX()));
                        config.set("TijolãoWars.2.y", Double.valueOf(loc.getY()));
                        config.set("TijolãoWars.2.z", Double.valueOf(loc.getZ()));
                        config.set("TijolãoWars.2.yaw", Float.valueOf(loc.getYaw()));
                        config.set("TijolãoWars.2.pitch", Float.valueOf(loc.getPitch()));
                        try {
                            config.save(file);
                            player.playSound(player, Sound.BLOCK_NOTE_BLOCK_BIT, 1.0f , 1.0f);
                            s.sendMessage("§8[§a!§8] §aSpawn de §lTijolão Wars §8(§aMapa 2§8) §asetado para os eventos com sucesso.");
                        } catch (IOException e) {
                            Bukkit.getConsoleSender().sendMessage(Messages.Falied_to_save.replace("%arquivo%", "locations.yml"));
                        }
                        return true;
                    }
                    if (args[2].equalsIgnoreCase("arena")) {
                        Location loc = ((Player) s).getLocation();
                        File file = DataManager.getFile("locations.yml");
                        FileConfiguration config = DataManager.getConfiguration(file);

                        Locations.tijolao2A = loc;
                        config.set("arena.TijolãoWars.2", loc);
                        config.set("arena.TijolãoWars.2.world", loc.getWorld().getName());
                        config.set("arena.TijolãoWars.2.x", Double.valueOf(loc.getX()));
                        config.set("arena.TijolãoWars.2.y", Double.valueOf(loc.getY()));
                        config.set("arena.TijolãoWars.2.z", Double.valueOf(loc.getZ()));
                        config.set("arena.TijolãoWars.2.yaw", Float.valueOf(loc.getYaw()));
                        config.set("arena.TijolãoWars.2.pitch", Float.valueOf(loc.getPitch()));
                        try {
                            config.save(file);
                            player.playSound(player, Sound.BLOCK_NOTE_BLOCK_BIT, 1.0f , 1.0f);
                            s.sendMessage("§8[§a!§8] §aArena de §lTijolãoWars §8(§aMapa 2§8) §asetado para os eventos com sucesso.");
                        } catch (IOException e) {
                            Bukkit.getConsoleSender().sendMessage(Messages.Falied_to_save.replace("%arquivo%", "locations.yml"));
                        }
                        return true;
                    }
                } else {
                    player.playSound(player, Sound.BLOCK_NOTE_BLOCK_BASS, 1.0f , 1.0f);
                    s.sendMessage("§8[§c!§8] §cEste evento só tem 2 mapa.");
                }
            }
            if (args[1].equalsIgnoreCase("Corrida")) {
                if (args[3].equalsIgnoreCase("1")) {
                    if (args[2].equalsIgnoreCase("spawn")) {
                        Location loc = ((Player) s).getLocation();
                        File file = DataManager.getFile("locations.yml");
                        FileConfiguration config = DataManager.getConfiguration(file);

                        Locations.corrida = loc;
                        config.set("Corrida", loc);
                        config.set("Corrida.world", loc.getWorld().getName());
                        config.set("Corrida.x", Double.valueOf(loc.getX()));
                        config.set("Corrida.y", Double.valueOf(loc.getY()));
                        config.set("Corrida.z", Double.valueOf(loc.getZ()));
                        config.set("Corrida.yaw", Float.valueOf(loc.getYaw()));
                        config.set("Corrida.pitch", Float.valueOf(loc.getPitch()));
                        try {
                            config.save(file);
                            player.playSound(player, Sound.BLOCK_NOTE_BLOCK_BIT, 1.0f , 1.0f);
                            s.sendMessage("§8[§a!§8] §aSpawn de §lCorrida §asetado para os eventos com sucesso.");
                        } catch (IOException e) {
                            Bukkit.getConsoleSender().sendMessage(Messages.Falied_to_save.replace("%arquivo%", "locations.yml"));
                        }
                        return true;
                    }
                    if (args[2].equalsIgnoreCase("arena")) {
                        Location loc = ((Player) s).getLocation();
                        File file = DataManager.getFile("locations.yml");
                        FileConfiguration config = DataManager.getConfiguration(file);

                        Locations.corridaA = loc;
                        config.set("arena.Corrida", loc);
                        config.set("arena.Corrida.world", loc.getWorld().getName());
                        config.set("arena.Corrida.x", Double.valueOf(loc.getX()));
                        config.set("arena.Corrida.y", Double.valueOf(loc.getY()));
                        config.set("arena.Corrida.z", Double.valueOf(loc.getZ()));
                        config.set("arena.Corrida.yaw", Float.valueOf(loc.getYaw()));
                        config.set("arena.Corrida.pitch", Float.valueOf(loc.getPitch()));
                        try {
                            config.save(file);
                            player.playSound(player, Sound.BLOCK_NOTE_BLOCK_BIT, 1.0f , 1.0f);
                            s.sendMessage("§8[§a!§8] §aArena de §lCorrida §asetado para os eventos com sucesso.");
                        } catch (IOException e) {
                            Bukkit.getConsoleSender().sendMessage(Messages.Falied_to_save.replace("%arquivo%", "locations.yml"));
                        }
                        return true;
                    }
                } else {
                    player.playSound(player, Sound.BLOCK_NOTE_BLOCK_BASS, 1.0f , 1.0f);
                    s.sendMessage("§8[§c!§8] §cEste evento só tem 1 mapa.");
                }
            }
            if (args[1].equalsIgnoreCase("CorridaBoat")) {
                if (args[3].equalsIgnoreCase("1")) {
                    if (args[2].equalsIgnoreCase("spawn")) {
                        Location loc = ((Player) s).getLocation();
                        File file = DataManager.getFile("locations.yml");
                        FileConfiguration config = DataManager.getConfiguration(file);

                        Locations.corridaboat = loc;
                        config.set("CorridaBoat", loc);
                        config.set("CorridaBoat.world", loc.getWorld().getName());
                        config.set("CorridaBoat.x", Double.valueOf(loc.getX()));
                        config.set("CorridaBoat.y", Double.valueOf(loc.getY()));
                        config.set("CorridaBoat.z", Double.valueOf(loc.getZ()));
                        config.set("CorridaBoat.yaw", Float.valueOf(loc.getYaw()));
                        config.set("CorridaBoat.pitch", Float.valueOf(loc.getPitch()));
                        try {
                            config.save(file);
                            player.playSound(player, Sound.BLOCK_NOTE_BLOCK_BIT, 1.0f , 1.0f);
                            s.sendMessage("§8[§a!§8] §aSpawn de §lCorrida de barco §asetado para os eventos com sucesso.");
                        } catch (IOException e) {
                            Bukkit.getConsoleSender().sendMessage(Messages.Falied_to_save.replace("%arquivo%", "locations.yml"));
                        }
                        return true;
                    }
                    if (args[2].equalsIgnoreCase("arena")) {
                        Location loc = ((Player) s).getLocation();
                        File file = DataManager.getFile("locations.yml");
                        FileConfiguration config = DataManager.getConfiguration(file);

                        Locations.corridaboatA = loc;
                        config.set("arena.CorridaBoat", loc);
                        config.set("arena.CorridaBoat.world", loc.getWorld().getName());
                        config.set("arena.CorridaBoat.x", Double.valueOf(loc.getX()));
                        config.set("arena.CorridaBoat.y", Double.valueOf(loc.getY()));
                        config.set("arena.CorridaBoat.z", Double.valueOf(loc.getZ()));
                        config.set("arena.CorridaBoat.yaw", Float.valueOf(loc.getYaw()));
                        config.set("arena.CorridaBoat.pitch", Float.valueOf(loc.getPitch()));
                        try {
                            config.save(file);
                            player.playSound(player, Sound.BLOCK_NOTE_BLOCK_BIT, 1.0f , 1.0f);
                            s.sendMessage("§8[§a!§8] §aArena de §lCorrida de barco §asetado para os eventos com sucesso.");
                        } catch (IOException e) {
                            Bukkit.getConsoleSender().sendMessage(Messages.Falied_to_save.replace("%arquivo%", "locations.yml"));
                        }
                        return true;
                    }
                } else {
                    player.playSound(player, Sound.BLOCK_NOTE_BLOCK_BASS, 1.0f , 1.0f);
                    s.sendMessage("§8[§c!§8] §cEste evento só tem 1 mapa.");
                }
            }
            if (args[1].equalsIgnoreCase("Sumo")) {
                if (args[3].equalsIgnoreCase("1")) {
                    if (args[2].equalsIgnoreCase("spawn")) {
                        Location loc = ((Player) s).getLocation();
                        File file = DataManager.getFile("locations.yml");
                        FileConfiguration config = DataManager.getConfiguration(file);

                        Locations.sumo = loc;
                        config.set("Sumo", loc);
                        config.set("Sumo.world", loc.getWorld().getName());
                        config.set("Sumo.x", Double.valueOf(loc.getX()));
                        config.set("Sumo.y", Double.valueOf(loc.getY()));
                        config.set("Sumo.z", Double.valueOf(loc.getZ()));
                        config.set("Sumo.yaw", Float.valueOf(loc.getYaw()));
                        config.set("Sumo.pitch", Float.valueOf(loc.getPitch()));
                        try {
                            config.save(file);
                            player.playSound(player, Sound.BLOCK_NOTE_BLOCK_BIT, 1.0f , 1.0f);
                            s.sendMessage("§8[§a!§8] §aSpawn de §lSumo §asetado para os eventos com sucesso.");
                        } catch (IOException e) {
                            Bukkit.getConsoleSender().sendMessage(Messages.Falied_to_save.replace("%arquivo%", "locations.yml"));
                        }
                        return true;
                    }
                    if (args[2].equalsIgnoreCase("arena")) {
                        Location loc = ((Player) s).getLocation();
                        File file = DataManager.getFile("locations.yml");
                        FileConfiguration config = DataManager.getConfiguration(file);

                        Locations.sumoA = loc;
                        config.set("arena.Sumo", loc);
                        config.set("arena.Sumo.world", loc.getWorld().getName());
                        config.set("arena.Sumo.x", Double.valueOf(loc.getX()));
                        config.set("arena.Sumo.y", Double.valueOf(loc.getY()));
                        config.set("arena.Sumo.z", Double.valueOf(loc.getZ()));
                        config.set("arena.Sumo.yaw", Float.valueOf(loc.getYaw()));
                        config.set("arena.Sumo.pitch", Float.valueOf(loc.getPitch()));
                        try {
                            config.save(file);
                            player.playSound(player, Sound.BLOCK_NOTE_BLOCK_BIT, 1.0f , 1.0f);
                            s.sendMessage("§8[§a!§8] §aArena de §lSumo §asetado para os eventos com sucesso.");
                        } catch (IOException e) {
                            Bukkit.getConsoleSender().sendMessage(Messages.Falied_to_save.replace("%arquivo%", "locations.yml"));
                        }
                        return true;
                    }
                } else {
                    player.playSound(player, Sound.BLOCK_NOTE_BLOCK_BASS, 1.0f , 1.0f);
                    s.sendMessage("§8[§c!§8] §cEste evento só tem 1 mapa.");
                }
            }
            if (args[1].equalsIgnoreCase("TNTRun")) {
                if (args[3].equalsIgnoreCase("1")) {
                    if (args[2].equalsIgnoreCase("spawn")) {
                        Location loc = ((Player) s).getLocation();
                        File file = DataManager.getFile("locations.yml");
                        FileConfiguration config = DataManager.getConfiguration(file);

                        Locations.tntrun = loc;
                        config.set("TNTRun.1", loc);
                        config.set("TNTRun.1.world", loc.getWorld().getName());
                        config.set("TNTRun.1.x", Double.valueOf(loc.getX()));
                        config.set("TNTRun.1.y", Double.valueOf(loc.getY()));
                        config.set("TNTRun.1.z", Double.valueOf(loc.getZ()));
                        config.set("TNTRun.1.yaw", Float.valueOf(loc.getYaw()));
                        config.set("TNTRun.1.pitch", Float.valueOf(loc.getPitch()));
                        try {
                            config.save(file);
                            player.playSound(player, Sound.BLOCK_NOTE_BLOCK_BIT, 1.0f , 1.0f);
                            s.sendMessage("§8[§a!§8] §aSpawn de §lTNTRUN §8(§aMapa 1§8) §asetado para os eventos com sucesso.");
                        } catch (IOException e) {
                            Bukkit.getConsoleSender().sendMessage(Messages.Falied_to_save.replace("%arquivo%", "locations.yml"));
                        }
                        return true;
                    }
                    if (args[2].equalsIgnoreCase("arena")) {
                        Location loc = ((Player) s).getLocation();
                        File file = DataManager.getFile("locations.yml");
                        FileConfiguration config = DataManager.getConfiguration(file);

                        Locations.tntrunA = loc;
                        config.set("arena.TNTRun.1", loc);
                        config.set("arena.TNTRun.1.world", loc.getWorld().getName());
                        config.set("arena.TNTRun.1.x", Double.valueOf(loc.getX()));
                        config.set("arena.TNTRun.1.y", Double.valueOf(loc.getY()));
                        config.set("arena.TNTRun.1.z", Double.valueOf(loc.getZ()));
                        config.set("arena.TNTRun.1.yaw", Float.valueOf(loc.getYaw()));
                        config.set("arena.TNTRun.1.pitch", Float.valueOf(loc.getPitch()));
                        try {
                            config.save(file);
                            player.playSound(player, Sound.BLOCK_NOTE_BLOCK_BIT, 1.0f , 1.0f);
                            s.sendMessage("§8[§a!§8] §aArena de §lTNTRUN §8(§aMapa 1§8) §asetado para os eventos com sucesso.");
                        } catch (IOException e) {
                            Bukkit.getConsoleSender().sendMessage(Messages.Falied_to_save.replace("%arquivo%", "locations.yml"));
                        }
                        return true;
                    }
                } else if (args[3].equalsIgnoreCase("2")) {
                    if (args[2].equalsIgnoreCase("spawn")) {
                        Location loc = ((Player) s).getLocation();
                        File file = DataManager.getFile("locations.yml");
                        FileConfiguration config = DataManager.getConfiguration(file);

                        Locations.tntrun2 = loc;
                        config.set("TNTRun.2", loc);
                        config.set("TNTRun.2.world", loc.getWorld().getName());
                        config.set("TNTRun.2.x", Double.valueOf(loc.getX()));
                        config.set("TNTRun.2.y", Double.valueOf(loc.getY()));
                        config.set("TNTRun.2.z", Double.valueOf(loc.getZ()));
                        config.set("TNTRun.2.yaw", Float.valueOf(loc.getYaw()));
                        config.set("TNTRun.2.pitch", Float.valueOf(loc.getPitch()));
                        try {
                            config.save(file);
                            player.playSound(player, Sound.BLOCK_NOTE_BLOCK_BIT, 1.0f , 1.0f);
                            s.sendMessage("§8[§a!§8] §aSpawn de §lTNTRUN §8(§aMapa 2§8) §asetado para os eventos com sucesso.");
                        } catch (IOException e) {
                            Bukkit.getConsoleSender().sendMessage(Messages.Falied_to_save.replace("%arquivo%", "locations.yml"));
                        }
                        return true;
                    }
                    if (args[2].equalsIgnoreCase("arena")) {
                        Location loc = ((Player) s).getLocation();
                        File file = DataManager.getFile("locations.yml");
                        FileConfiguration config = DataManager.getConfiguration(file);

                        Locations.tntrun2A = loc;
                        config.set("arena.TNTRun.2", loc);
                        config.set("arena.TNTRun.2.world", loc.getWorld().getName());
                        config.set("arena.TNTRun.2.x", Double.valueOf(loc.getX()));
                        config.set("arena.TNTRun.2.y", Double.valueOf(loc.getY()));
                        config.set("arena.TNTRun.2.z", Double.valueOf(loc.getZ()));
                        config.set("arena.TNTRun.2.yaw", Float.valueOf(loc.getYaw()));
                        config.set("arena.TNTRun.2.pitch", Float.valueOf(loc.getPitch()));
                        try {
                            config.save(file);
                            player.playSound(player, Sound.BLOCK_NOTE_BLOCK_BIT, 1.0f , 1.0f);
                            s.sendMessage("§8[§a!§8] §aArena de §lTNTRUN §8(§aMapa 2§8) §asetado para os eventos com sucesso.");
                        } catch (IOException e) {
                            Bukkit.getConsoleSender().sendMessage(Messages.Falied_to_save.replace("%arquivo%", "locations.yml"));
                        }
                        return true;
                    }
                } else {
                    player.playSound(player, Sound.BLOCK_NOTE_BLOCK_BASS, 1.0f , 1.0f);
                    s.sendMessage("§8[§c!§8] §cEste evento só tem 2 mapa.");
                }
            }
            if (args[1].equalsIgnoreCase("TNTTag")) {
                if (args[3].equalsIgnoreCase("1")) {
                    if (args[2].equalsIgnoreCase("spawn")) {
                        Location loc = ((Player) s).getLocation();
                        File file = DataManager.getFile("locations.yml");
                        FileConfiguration config = DataManager.getConfiguration(file);

                        Locations.tnttag = loc;
                        config.set("TNTTag", loc);
                        config.set("TNTTag.world", loc.getWorld().getName());
                        config.set("TNTTag.x", Double.valueOf(loc.getX()));
                        config.set("TNTTag.y", Double.valueOf(loc.getY()));
                        config.set("TNTTag.z", Double.valueOf(loc.getZ()));
                        config.set("TNTTag.yaw", Float.valueOf(loc.getYaw()));
                        config.set("TNTTag.pitch", Float.valueOf(loc.getPitch()));
                        try {
                            config.save(file);
                            player.playSound(player, Sound.BLOCK_NOTE_BLOCK_BIT, 1.0f , 1.0f);
                            s.sendMessage("§8[§a!§8] §aSpawn de §lTNTTag §asetado para os eventos com sucesso.");
                        } catch (IOException e) {
                            Bukkit.getConsoleSender().sendMessage(Messages.Falied_to_save.replace("%arquivo%", "locations.yml"));
                        }
                        return true;
                    }
                    if (args[2].equalsIgnoreCase("arena")) {
                        Location loc = ((Player) s).getLocation();
                        File file = DataManager.getFile("locations.yml");
                        FileConfiguration config = DataManager.getConfiguration(file);

                        Locations.tnttagA = loc;
                        config.set("arena.TNTTag", loc);
                        config.set("arena.TNTTag.world", loc.getWorld().getName());
                        config.set("arena.TNTTag.x", Double.valueOf(loc.getX()));
                        config.set("arena.TNTTag.y", Double.valueOf(loc.getY()));
                        config.set("arena.TNTTag.z", Double.valueOf(loc.getZ()));
                        config.set("arena.TNTTag.yaw", Float.valueOf(loc.getYaw()));
                        config.set("arena.TNTTag.pitch", Float.valueOf(loc.getPitch()));
                        try {
                            config.save(file);
                            player.playSound(player, Sound.BLOCK_NOTE_BLOCK_BIT, 1.0f , 1.0f);
                            s.sendMessage("§8[§a!§8] §aArena de §lTNTTag §asetado para os eventos com sucesso.");
                        } catch (IOException e) {
                            Bukkit.getConsoleSender().sendMessage(Messages.Falied_to_save.replace("%arquivo%", "locations.yml"));
                        }
                        return true;
                    }
                } else {
                    player.playSound(player, Sound.BLOCK_NOTE_BLOCK_BASS, 1.0f , 1.0f);
                    s.sendMessage("§8[§c!§8] §cEste evento só tem 1 mapa.");
                }
            }
            if (args[1].equalsIgnoreCase("Parapente")) {
                if (args[2].equalsIgnoreCase("check")) {
                    Location loc = ((Player) s).getLocation();
                    File file = DataManager.getFile("locations.yml");
                    FileConfiguration config = DataManager.getConfiguration(file);
                    if (args[3].equalsIgnoreCase("1")) {
                        Locations.parapenteC1 = loc;
                        config.set("arena.Parapente.checkpoint.1", loc);
                        config.set("arena.Parapente.checkpoint.1.world", loc.getWorld().getName());
                        config.set("arena.Parapente.checkpoint.1.x", Double.valueOf(loc.getX()));
                        config.set("arena.Parapente.checkpoint.1.y", Double.valueOf(loc.getY()));
                        config.set("arena.Parapente.checkpoint.1.z", Double.valueOf(loc.getZ()));
                        config.set("arena.Parapente.checkpoint.1.yaw", Float.valueOf(loc.getYaw()));
                        config.set("arena.Parapente.checkpoint.1.pitch", Float.valueOf(loc.getPitch()));
                        try {
                            config.save(file);
                            player.playSound(player, Sound.BLOCK_NOTE_BLOCK_BIT, 1.0f , 1.0f);
                            s.sendMessage("§8[§a!§8] §aCheckPoint de §lParapente §8(§a§l1§8) §asetado para os eventos com sucesso.");
                        } catch (IOException e) {
                            Bukkit.getConsoleSender().sendMessage(Messages.Falied_to_save.replace("%arquivo%", "locations.yml"));
                        }
                        return true;
                    } else if (args[3].equalsIgnoreCase("2")) {
                        Locations.parapenteC2 = loc;
                        config.set("arena.Parapente.checkpoint.2", loc);
                        config.set("arena.Parapente.checkpoint.2.world", loc.getWorld().getName());
                        config.set("arena.Parapente.checkpoint.2.x", Double.valueOf(loc.getX()));
                        config.set("arena.Parapente.checkpoint.2.y", Double.valueOf(loc.getY()));
                        config.set("arena.Parapente.checkpoint.2.z", Double.valueOf(loc.getZ()));
                        config.set("arena.Parapente.checkpoint.2.yaw", Float.valueOf(loc.getYaw()));
                        config.set("arena.Parapente.checkpoint.2.pitch", Float.valueOf(loc.getPitch()));
                        try {
                            config.save(file);
                            player.playSound(player, Sound.BLOCK_NOTE_BLOCK_BIT, 1.0f , 1.0f);
                            s.sendMessage("§8[§a!§8] §aCheckPoint de §lParapente §8(§a§l2§8) §asetado para os eventos com sucesso.");
                        } catch (IOException e) {
                            Bukkit.getConsoleSender().sendMessage(Messages.Falied_to_save.replace("%arquivo%", "locations.yml"));
                        }
                        return true;
                    } else if (args[3].equalsIgnoreCase("3")) {
                        Locations.parapenteC3 = loc;
                        config.set("arena.Parapente.checkpoint.3", loc);
                        config.set("arena.Parapente.checkpoint.3.world", loc.getWorld().getName());
                        config.set("arena.Parapente.checkpoint.3.x", Double.valueOf(loc.getX()));
                        config.set("arena.Parapente.checkpoint.3.y", Double.valueOf(loc.getY()));
                        config.set("arena.Parapente.checkpoint.3.z", Double.valueOf(loc.getZ()));
                        config.set("arena.Parapente.checkpoint.3.yaw", Float.valueOf(loc.getYaw()));
                        config.set("arena.Parapente.checkpoint.3.pitch", Float.valueOf(loc.getPitch()));
                        try {
                            config.save(file);
                            player.playSound(player, Sound.BLOCK_NOTE_BLOCK_BIT, 1.0f , 1.0f);
                            s.sendMessage("§8[§a!§8] §aCheckPoint de §lParapente §8(§a§l3§8) §asetado para os eventos com sucesso.");
                        } catch (IOException e) {
                            Bukkit.getConsoleSender().sendMessage(Messages.Falied_to_save.replace("%arquivo%", "locations.yml"));
                        }
                        return true;
                    } else {
                        player.playSound(player, Sound.BLOCK_NOTE_BLOCK_BASS, 1.0f , 1.0f);
                        s.sendMessage("§8[§c!§8] §cEste evento só tem 3 checkpoints.");
                    }
                }
                if (args[3].equalsIgnoreCase("1")) {
                    if (args[2].equalsIgnoreCase("spawn")) {
                        Location loc = ((Player) s).getLocation();
                        File file = DataManager.getFile("locations.yml");
                        FileConfiguration config = DataManager.getConfiguration(file);

                        Locations.parapente = loc;
                        config.set("Parapente", loc);
                        config.set("Parapente.world", loc.getWorld().getName());
                        config.set("Parapente.x", Double.valueOf(loc.getX()));
                        config.set("Parapente.y", Double.valueOf(loc.getY()));
                        config.set("Parapente.z", Double.valueOf(loc.getZ()));
                        config.set("Parapente.yaw", Float.valueOf(loc.getYaw()));
                        config.set("Parapente.pitch", Float.valueOf(loc.getPitch()));
                        try {
                            config.save(file);
                            player.playSound(player, Sound.BLOCK_NOTE_BLOCK_BIT, 1.0f , 1.0f);
                            s.sendMessage("§8[§a!§8] §aSpawn de §lParapente §asetado para os eventos com sucesso.");
                        } catch (IOException e) {
                            Bukkit.getConsoleSender().sendMessage(Messages.Falied_to_save.replace("%arquivo%", "locations.yml"));
                        }
                        return true;
                    }
                    if (args[2].equalsIgnoreCase("arena")) {
                        Location loc = ((Player) s).getLocation();
                        File file = DataManager.getFile("locations.yml");
                        FileConfiguration config = DataManager.getConfiguration(file);

                        Locations.parapenteA = loc;
                        config.set("arena.Parapente", loc);
                        config.set("arena.Parapente.world", loc.getWorld().getName());
                        config.set("arena.Parapente.x", Double.valueOf(loc.getX()));
                        config.set("arena.Parapente.y", Double.valueOf(loc.getY()));
                        config.set("arena.Parapente.z", Double.valueOf(loc.getZ()));
                        config.set("arena.Parapente.yaw", Float.valueOf(loc.getYaw()));
                        config.set("arena.Parapente.pitch", Float.valueOf(loc.getPitch()));
                        try {
                            config.save(file);
                            player.playSound(player, Sound.BLOCK_NOTE_BLOCK_BIT, 1.0f , 1.0f);
                            s.sendMessage("§8[§a!§8] §aArena de §lParapente §asetado para os eventos com sucesso.");
                        } catch (IOException e) {
                            Bukkit.getConsoleSender().sendMessage(Messages.Falied_to_save.replace("%arquivo%", "locations.yml"));
                        }
                        return true;
                    }
                } else {
                    player.playSound(player, Sound.BLOCK_NOTE_BLOCK_BASS, 1.0f , 1.0f);
                    s.sendMessage("§8[§c!§8] §cEste evento só tem 1 mapa.");
                }
            }
            if (args[1].equalsIgnoreCase("Mini-Wars")) {
                if (arg == 1) {
                    if (args[2].equalsIgnoreCase("spawn")) {
                        Location loc = ((Player) s).getLocation();
                        File file = DataManager.getFile("locations.yml");
                        FileConfiguration config = DataManager.getConfiguration(file);

                        Locations.miniwars = loc;
                        config.set("Mini-Wars.1", loc);
                        config.set("Mini-Wars.1.world", loc.getWorld().getName());
                        config.set("Mini-Wars.1.x", Double.valueOf(loc.getX()));
                        config.set("Mini-Wars.1.y", Double.valueOf(loc.getY()));
                        config.set("Mini-Wars.1.z", Double.valueOf(loc.getZ()));
                        config.set("Mini-Wars.1.yaw", Float.valueOf(loc.getYaw()));
                        config.set("Mini-Wars.1.pitch", Float.valueOf(loc.getPitch()));
                        try {
                            config.save(file);
                            player.playSound(player, Sound.BLOCK_NOTE_BLOCK_BIT, 1.0f , 1.0f);
                            s.sendMessage("§8[§a!§8] §aSpawn de §lMini-Wars §8(§aMapa 1§8)§a setado para os eventos com sucesso.");
                        } catch (IOException e) {
                            Bukkit.getConsoleSender().sendMessage(Messages.Falied_to_save.replace("%arquivo%", "locations.yml"));
                        }
                        return true;
                    }
                } else if (arg == 2) {
                    if (args[2].equalsIgnoreCase("spawn")) {
                        Location loc = ((Player) s).getLocation();
                        File file = DataManager.getFile("locations.yml");
                        FileConfiguration config = DataManager.getConfiguration(file);

                        Locations.miniwars2 = loc;
                        config.set("Mini-Wars.2", loc);
                        config.set("Mini-Wars.2.world", loc.getWorld().getName());
                        config.set("Mini-Wars.2.x", Double.valueOf(loc.getX()));
                        config.set("Mini-Wars.2.y", Double.valueOf(loc.getY()));
                        config.set("Mini-Wars.2.z", Double.valueOf(loc.getZ()));
                        config.set("Mini-Wars.2.yaw", Float.valueOf(loc.getYaw()));
                        config.set("Mini-Wars.2.pitch", Float.valueOf(loc.getPitch()));
                        try {
                            config.save(file);
                            player.playSound(player, Sound.BLOCK_NOTE_BLOCK_BIT, 1.0f , 1.0f);
                            s.sendMessage("§8[§a!§8] §aSpawn de §lMini-Wars §8(§aMapa 2§8) §asetado para os eventos com sucesso.");
                        } catch (IOException e) {
                            Bukkit.getConsoleSender().sendMessage(Messages.Falied_to_save.replace("%arquivo%", "locations.yml"));
                        }
                        return true;
                    }
                } else if (arg == 3) {
                    if (args[2].equalsIgnoreCase("spawn")) {
                        Location loc = ((Player) s).getLocation();
                        File file = DataManager.getFile("locations.yml");
                        FileConfiguration config = DataManager.getConfiguration(file);

                        Locations.miniwars3 = loc;
                        config.set("Mini-Wars.3", loc);
                        config.set("Mini-Wars.3.world", loc.getWorld().getName());
                        config.set("Mini-Wars.3.x", Double.valueOf(loc.getX()));
                        config.set("Mini-Wars.3.y", Double.valueOf(loc.getY()));
                        config.set("Mini-Wars.3.z", Double.valueOf(loc.getZ()));
                        config.set("Mini-Wars.3.yaw", Float.valueOf(loc.getYaw()));
                        config.set("Mini-Wars.3.pitch", Float.valueOf(loc.getPitch()));
                        try {
                            config.save(file);
                            player.playSound(player, Sound.BLOCK_NOTE_BLOCK_BIT, 1.0f , 1.0f);
                            s.sendMessage("§8[§a!§8] §aSpawn de §lMini-Wars §8(§aMapa 3§8)§a setado para os eventos com sucesso.");
                        } catch (IOException e) {
                            Bukkit.getConsoleSender().sendMessage(Messages.Falied_to_save.replace("%arquivo%", "locations.yml"));
                        }
                        return true;
                    }
                } else if (arg == 4) {
                    if (args[2].equalsIgnoreCase("spawn")) {
                        Location loc = ((Player) s).getLocation();
                        File file = DataManager.getFile("locations.yml");
                        FileConfiguration config = DataManager.getConfiguration(file);

                        Locations.miniwars4 = loc;
                        config.set("Mini-Wars.4", loc);
                        config.set("Mini-Wars.4.world", loc.getWorld().getName());
                        config.set("Mini-Wars.4.x", Double.valueOf(loc.getX()));
                        config.set("Mini-Wars.4.y", Double.valueOf(loc.getY()));
                        config.set("Mini-Wars.4.z", Double.valueOf(loc.getZ()));
                        config.set("Mini-Wars.4.yaw", Float.valueOf(loc.getYaw()));
                        config.set("Mini-Wars.4.pitch", Float.valueOf(loc.getPitch()));
                        try {
                            config.save(file);
                            player.playSound(player, Sound.BLOCK_NOTE_BLOCK_BIT, 1.0f , 1.0f);
                            s.sendMessage("§8[§a!§8] §aSpawn de §lMini-Wars §8(§aMapa 4§8) §asetado para os eventos com sucesso.");
                        } catch (IOException e) {
                            Bukkit.getConsoleSender().sendMessage(Messages.Falied_to_save.replace("%arquivo%", "locations.yml"));
                        }
                        return true;
                    }
                } else if (arg == 5) {
                    if (args[2].equalsIgnoreCase("spawn")) {
                        Location loc = ((Player) s).getLocation();
                        File file = DataManager.getFile("locations.yml");
                        FileConfiguration config = DataManager.getConfiguration(file);

                        Locations.miniwars5 = loc;
                        config.set("Mini-Wars.5", loc);
                        config.set("Mini-Wars.5.world", loc.getWorld().getName());
                        config.set("Mini-Wars.5.x", Double.valueOf(loc.getX()));
                        config.set("Mini-Wars.5.y", Double.valueOf(loc.getY()));
                        config.set("Mini-Wars.5.z", Double.valueOf(loc.getZ()));
                        config.set("Mini-Wars.5.yaw", Float.valueOf(loc.getYaw()));
                        config.set("Mini-Wars.5.pitch", Float.valueOf(loc.getPitch()));
                        try {
                            config.save(file);
                            player.playSound(player, Sound.BLOCK_NOTE_BLOCK_BIT, 1.0f , 1.0f);
                            s.sendMessage("§8[§a!§8] §aSpawn de §lMini-Wars §8(§aMapa 5§8) §asetado para os eventos com sucesso.");
                        } catch (IOException e) {
                            Bukkit.getConsoleSender().sendMessage(Messages.Falied_to_save.replace("%arquivo%", "locations.yml"));
                        }
                        return true;
                    }
                } else if (arg > 5) {
                    player.playSound(player, Sound.BLOCK_NOTE_BLOCK_BASS, 1.0f , 1.0f);
                    s.sendMessage("§8[§c!§8] §cEste evento só tem 5 mapa.");
                }
            }
            if (args[1].equalsIgnoreCase("CopaPVP")) {
                if (args[3].equalsIgnoreCase("1")) {
                    if (args[2].equalsIgnoreCase("spawn")) {
                        Location loc = ((Player) s).getLocation();
                        File file = DataManager.getFile("locations.yml");
                        FileConfiguration config = DataManager.getConfiguration(file);

                        Locations.copapvp = loc;
                        config.set("CopaPVP", loc);
                        config.set("CopaPVP.world", loc.getWorld().getName());
                        config.set("CopaPVP.x", Double.valueOf(loc.getX()));
                        config.set("CopaPVP.y", Double.valueOf(loc.getY()));
                        config.set("CopaPVP.z", Double.valueOf(loc.getZ()));
                        config.set("CopaPVP.yaw", Float.valueOf(loc.getYaw()));
                        config.set("CopaPVP.pitch", Float.valueOf(loc.getPitch()));
                        try {
                            config.save(file);
                            player.playSound(player, Sound.BLOCK_NOTE_BLOCK_BIT, 1.0f , 1.0f);
                            s.sendMessage("§8[§a!§8] §aSpawn da §lCopaPVP §asetado para os eventos com sucesso.");
                        } catch (IOException e) {
                            Bukkit.getConsoleSender().sendMessage(Messages.Falied_to_save.replace("%arquivo%", "locations.yml"));
                        }
                        return true;
                    }
                } else {
                    player.playSound(player, Sound.BLOCK_NOTE_BLOCK_BASS, 1.0f , 1.0f);
                    s.sendMessage("§8[§c!§8] §cEste evento só tem 1 mapa.");
                }
            }
            if (args[1].equalsIgnoreCase("Esconde-esconde")) {
                if (arg == 1) {
                    if (args[2].equalsIgnoreCase("spawn")) {
                        Location loc = ((Player) s).getLocation();
                        File file = DataManager.getFile("locations.yml");
                        FileConfiguration config = DataManager.getConfiguration(file);

                        Locations.esconde = loc;
                        config.set("Esconde-esconde.1", loc);
                        config.set("Esconde-esconde.1.world", loc.getWorld().getName());
                        config.set("Esconde-esconde.1.x", Double.valueOf(loc.getX()));
                        config.set("Esconde-esconde.1.y", Double.valueOf(loc.getY()));
                        config.set("Esconde-esconde.1.z", Double.valueOf(loc.getZ()));
                        config.set("Esconde-esconde.1.yaw", Float.valueOf(loc.getYaw()));
                        config.set("Esconde-esconde.1.pitch", Float.valueOf(loc.getPitch()));
                        try {
                            config.save(file);
                            player.playSound(player, Sound.BLOCK_NOTE_BLOCK_BIT, 1.0f , 1.0f);
                            s.sendMessage("§8[§a!§8] §aSpawn de §lEsconde-esconde §8(§aMapa 1§8) §asetado para os eventos com sucesso.");
                        } catch (IOException e) {
                            Bukkit.getConsoleSender().sendMessage(Messages.Falied_to_save.replace("%arquivo%", "locations.yml"));
                        }
                        return true;
                    }
                } else if (arg == 2) {
                    if (args[2].equalsIgnoreCase("spawn")) {
                        Location loc = ((Player) s).getLocation();
                        File file = DataManager.getFile("locations.yml");
                        FileConfiguration config = DataManager.getConfiguration(file);

                        Locations.esconde2 = loc;
                        config.set("Esconde-esconde.2", loc);
                        config.set("Esconde-esconde.2.world", loc.getWorld().getName());
                        config.set("Esconde-esconde.2.x", Double.valueOf(loc.getX()));
                        config.set("Esconde-esconde.2.y", Double.valueOf(loc.getY()));
                        config.set("Esconde-esconde.2.z", Double.valueOf(loc.getZ()));
                        config.set("Esconde-esconde.2.yaw", Float.valueOf(loc.getYaw()));
                        config.set("Esconde-esconde.2.pitch", Float.valueOf(loc.getPitch()));
                        try {
                            config.save(file);
                            player.playSound(player, Sound.BLOCK_NOTE_BLOCK_BIT, 1.0f , 1.0f);
                            s.sendMessage("§8[§a!§8] §aSpawn de §lEsconde-esconde §8(§aMapa 2§8) §asetado para os eventos com sucesso.");
                        } catch (IOException e) {
                            Bukkit.getConsoleSender().sendMessage(Messages.Falied_to_save.replace("%arquivo%", "locations.yml"));
                        }
                        return true;
                    }
                } else if (arg > 2) {
                    s.sendMessage("§8[§c!§8] §cEste evento só tem 2 mapa.");
                }
            }
            if (args[1].equalsIgnoreCase("Ruínas")) {
                if (args[3].equalsIgnoreCase("1")) {
                    if (args[2].equalsIgnoreCase("spawn")) {
                        Location loc = ((Player) s).getLocation();
                        File file = DataManager.getFile("locations.yml");
                        FileConfiguration config = DataManager.getConfiguration(file);

                        Locations.ruinas = loc;
                        config.set("Ruínas", loc);
                        config.set("Ruínas.world", loc.getWorld().getName());
                        config.set("Ruínas.x", Double.valueOf(loc.getX()));
                        config.set("Ruínas.y", Double.valueOf(loc.getY()));
                        config.set("Ruínas.z", Double.valueOf(loc.getZ()));
                        config.set("Ruínas.yaw", Float.valueOf(loc.getYaw()));
                        config.set("Ruínas.pitch", Float.valueOf(loc.getPitch()));
                        try {
                            config.save(file);
                            player.playSound(player, Sound.BLOCK_NOTE_BLOCK_BIT, 1.0f , 1.0f);
                            s.sendMessage("§8[§a!§8] §aSpawn de §lRuínas§a setado para os eventos com sucesso.");
                        } catch (IOException e) {
                            Bukkit.getConsoleSender().sendMessage(Messages.Falied_to_save.replace("%arquivo%", "locations.yml"));
                        }
                        return true;
                    }
                } else {
                    player.playSound(player, Sound.BLOCK_NOTE_BLOCK_BASS, 1.0f , 1.0f);
                    s.sendMessage("§8[§c!§8] §cEste evento só tem 1 mapa.");
                }
            }
            return true;
        }
        if (args[0].equalsIgnoreCase("kick")) {
            if (!s.hasPermission("mineskyevents.command.event.kick")) {
                s.sendMessage(Messages.No_permission);
                player.playSound(player, Sound.BLOCK_NOTE_BLOCK_BASS, 1.0f , 1.0f);
                return true;
            }
            if (args.length < 2) {
                s.sendMessage("§8[§c!§8] §cUso incorreto. Use /event kick (Player) [Motivo]");
                player.playSound(player, Sound.BLOCK_NOTE_BLOCK_BASS, 1.0f , 1.0f);
                return true;
            }
            Player j = Bukkit.getPlayer(args[1]);
            if (!j.isOnline()) {
                s.sendMessage("§8[§c!§8] §cO jogador não foi encontrado");
                player.playSound(player, Sound.BLOCK_NOTE_BLOCK_BASS, 1.0f , 1.0f);
                return true;
            }
            if (!Util.PDVE(j)) {
                s.sendMessage("§8[§c!§8] §cO jogador não está online no evento.");
                player.playSound(player, Sound.BLOCK_NOTE_BLOCK_BASS, 1.0f , 1.0f);
                return true;
            }
            String reason = "Sem motivo informado.";
            String[] argsSubset = Arrays.copyOfRange(args, 2, args.length);
            String result = String.join(" ", argsSubset).replace("&", "§");
            if (args.length >= 3) reason = result;

            File file = DataManager.getFile(j.getName().toLowerCase(), "playerdata");
            FileConfiguration config = DataManager.getConfiguration(file);

            config.set("Event", false);

            try {
                config.save(file);
                SendMessages.sendConectionBCMSNE(j);
                TextComponent message = new TextComponent("§8[§c!§8] §cVocê foi expulso do evento. Motivo: " + reason);
                SendMessages.sendPlayermessage(j, message);
                player.playSound(player, Sound.BLOCK_NOTE_BLOCK_BIT, 1.0f , 1.0f);
                s.sendMessage("§8[§a!§8] §aO jogador foi expulso com sucesso do evento.");
                for (Player player1 : Bukkit.getOnlinePlayers()) {
                    File filefor = DataManager.getFile(player1.getName().toLowerCase(), "playerdata");
                    FileConfiguration configfor = DataManager.getConfiguration(filefor);
                    if (!player1.hasPermission("mineskyevents.notify.moderation")) return true;
                    if (!configfor.getBoolean("Notification")) return true;
                    player1.sendMessage("§c" + j.getName() + "§f foi expulso do evento por§c " + s.getName() + "§f. Motivo: §c" + reason);
                }
                switch (MineSkyEvents.event) {
                    case "Spleef":
                        EventPlayerManager.removePlayer(j);
                        if (EventPlayerManager.getPlayerCount()== 1 && !SpleefEvent.contagem && SpleefEvent.contagemI) SpleefEvent.finalizar();
                        break;
                    case "TijolãoWars":
                        EventPlayerManager.removePlayer(j);
                        if (EventPlayerManager.getPlayerCount() == 1 && !TijolãoWarsEvent.contagem && TijolãoWarsEvent.contagemI) TijolãoWarsEvent.finalizar();
                        break;
                    case "Corrida":
                        EventPlayerManager.removePlayer(j);
                        break;
                    case "CorridaBoat":
                        EventPlayerManager.removePlayer(j);
                        break;
                    case "Sumo":
                        EventPlayerManager.removePlayer(j);
                        if (EventPlayerManager.getPlayerCount() == 1 && !SumoEvent.contagem && SumoEvent.contagemI) SumoEvent.finalizar();
                        break;
                    case "TNTRun":
                        EventPlayerManager.removePlayer(j);
                        if (EventPlayerManager.getPlayerCount() == 1 && !TNTRunEvent.contagem && TNTRunEvent.contagemI) TNTRunEvent.finalizar();
                        break;
                    case "TNTTag":
                        EventPlayerManager.removePlayer(j);
                        TNTTagEvent.jogadores.remove(j);
                        if (EventPlayerManager.getPlayerCount() == 1 && !TNTTagEvent.contagem && TNTTagEvent.contagemI) TNTTagEvent.finalizar();
                        break;
                    case "Parapente":
                        EventPlayerManager.removePlayer(j);
                        break;
                }
            } catch (IOException e) {
                Bukkit.getLogger().warning("[MineSky-Events] Ocorreu um erro ao tentar salvar a playerdata do jogador: " + j.getName());
                s.sendMessage("§8[§c!§8] §cOcorreu um erro ao tentar salvar a playerdata do jogador: " + j.getName());
                player.playSound(player, Sound.BLOCK_NOTE_BLOCK_BASS, 1.0f , 1.0f);
                e.printStackTrace();
            }
            return true;
        }
        if (args[0].equalsIgnoreCase("blacklist")) {
            if (!s.hasPermission("mineskyevents.command.event.blacklist")) {
                s.sendMessage("§8[§c!§8] §cVocê não pode executar esse comando.");
                player.playSound(player, Sound.BLOCK_NOTE_BLOCK_BASS, 1.0f , 1.0f);
                return true;
            }
            File file = DataManager.getFile("config.yml");
            FileConfiguration config = DataManager.getConfiguration(file);
            String event = args[2].toLowerCase();
            Player pb = Bukkit.getPlayer(args[3]);
            if (args[1].equalsIgnoreCase("adicionar")) {
                if (!(args.length == 5)) {
                    List<String> blacklistname = config.getStringList("blacklist." + event + ".list");
                    if (blacklistname.contains(pb.getName())) {
                        s.sendMessage("§8[§c!§8] §cEste player já está na blacklist.");
                        player.playSound(player, Sound.BLOCK_NOTE_BLOCK_BASS, 1.0f , 1.0f);
                        return true;
                    }
                    blacklistname.add(pb.getName());
                    config.set("blacklist." + event + ".list", blacklistname);
                    try {
                        config.save(file);
                        player.playSound(player, Sound.BLOCK_NOTE_BLOCK_BIT, 1.0f , 1.0f);
                        s.sendMessage("§8[§a!§8] §aVocê adicionar o " + pb.getName() + " na blacklist do evento " + event + " com sucesso!");
                    } catch (IOException e) {
                        player.playSound(player, Sound.BLOCK_NOTE_BLOCK_BASS, 1.0f , 1.0f);
                        s.sendMessage("§8[§c!§8] §cOcorreu um erro ao adicionar o player à blacklist");
                    }
                } else {
                    if (!args[4].equalsIgnoreCase("ip")) {
                        player.playSound(player, Sound.BLOCK_NOTE_BLOCK_BASS, 1.0f , 1.0f);
                        s.sendMessage("§8[§c!§8] §cUsage: /event blacklist adicionar (event) (player) ip");
                        return true;
                    }
                    List<String> blacklistname = config.getStringList("blacklistip." + event + ".list");
                    if (blacklistname.contains(pb.getAddress().getAddress().getHostAddress())) {
                        player.playSound(player, Sound.BLOCK_NOTE_BLOCK_BASS, 1.0f , 1.0f);
                        s.sendMessage("§8[§c!§8] §cEste player já está na blacklist.");
                        return true;
                    }
                    blacklistname.add(pb.getAddress().getAddress().getHostAddress());
                    config.set("blacklistip." + event + ".list", blacklistname);
                    try {
                        config.save(file);
                        player.playSound(player, Sound.BLOCK_NOTE_BLOCK_BIT, 1.0f , 1.0f);
                        s.sendMessage("§8[§a!§8] §aVocê adicionou o " + pb.getName() + " na blacklist por ip do evento " + event + " com sucesso!");
                    } catch (IOException e) {
                        player.playSound(player, Sound.BLOCK_NOTE_BLOCK_BASS, 1.0f , 1.0f);
                        s.sendMessage("§8[§c!§8] §cOcorreu um erro ao adicionar o player à blacklist por ip");
                    }
                }
            }
            if (args[1].equalsIgnoreCase("remover")) {
                if (!(args.length == 5)) {
                    List<String> blacklistname = config.getStringList("blacklist." + event + ".list");
                    if (!blacklistname.contains(pb.getName())) {
                        player.playSound(player, Sound.BLOCK_NOTE_BLOCK_BASS, 1.0f , 1.0f);
                        s.sendMessage("§8[§c!§8] §cEste player não está na blacklist.");
                        return true;
                    }
                    blacklistname.remove(pb.getName());
                    config.set("blacklist." + event + ".list", blacklistname);
                    try {
                        config.save(file);
                        player.playSound(player, Sound.BLOCK_NOTE_BLOCK_BIT, 1.0f , 1.0f);
                        s.sendMessage("§8[§a!§8] §aVocê removeu o " + pb.getName() + " na blacklist do evento " + event + " com sucesso!");
                    } catch (IOException e) {
                        player.playSound(player, Sound.BLOCK_NOTE_BLOCK_BASS, 1.0f , 1.0f);
                        s.sendMessage("§8[§c!§8] §cOcorreu um erro ao remover o player à blacklist");
                    }
                } else {
                    if (!args[4].equalsIgnoreCase("ip")) {
                        player.playSound(player, Sound.BLOCK_NOTE_BLOCK_BASS, 1.0f , 1.0f);
                        s.sendMessage("§8[§c!§8] §cUsage: /event blacklist remover (event) (player) ip");
                        return true;
                    }
                    List<String> blacklistname = config.getStringList("blacklistip." + event + ".list");
                    if (!blacklistname.contains(pb.getAddress().getAddress().getHostAddress())) {
                        player.playSound(player, Sound.BLOCK_NOTE_BLOCK_BASS, 1.0f , 1.0f);
                        s.sendMessage("§8[§c!§8] §cEste player não está na blacklist por ip.");
                        return true;
                    }
                    blacklistname.remove(pb.getAddress().getAddress().getHostAddress());
                    config.set("blacklistip." + event + ".list", blacklistname);
                    try {
                        config.save(file);
                        player.playSound(player, Sound.BLOCK_NOTE_BLOCK_BIT, 1.0f , 1.0f);
                        s.sendMessage("§8[§a!§8] §aVocê removeu o " + pb.getName() + " na blacklist por ip do evento " + event + " com sucesso!");
                    } catch (IOException e) {
                        player.playSound(player, Sound.BLOCK_NOTE_BLOCK_BASS, 1.0f , 1.0f);
                        s.sendMessage("§8[§c!§8] §cOcorreu um erro ao remover o player à blacklist por ip");
                    }
                }
            }
            return true;
        }
        player.playSound(player, Sound.BLOCK_NOTE_BLOCK_BASS, 1.0f , 1.0f);
        s.sendMessage("§8[§c!§8] §cVocê não informou um agurmento valido.");
        return true;
    }

    private void clearInventory(Player p) {
        PlayerInventory inv = p.getInventory();
        p.setItemOnCursor(null);
        inv.clear();
        inv.setHelmet(null);
        inv.setChestplate(null);
        inv.setLeggings(null);
        inv.setBoots(null);
    }

    private void PlayerSpectator(Player p) {
        for (Player jogador : Bukkit.getOnlinePlayers()) {
            jogador.hidePlayer(MineSkyEvents.get(), p);
        }
        p.setAllowFlight(true);
        p.setFlying(true);
        p.setInvisible(true);
        p.setGameMode(GameMode.ADVENTURE);
        p.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, Integer.MAX_VALUE, 1, false, false));
        p.addPotionEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE, Integer.MAX_VALUE, 1, false, false));
        p.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, Integer.MAX_VALUE, 4, false, false));
        p.addPotionEffect(new PotionEffect(PotionEffectType.WATER_BREATHING, Integer.MAX_VALUE, 1, false, false));
        p.addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION, Integer.MAX_VALUE, 1, false, false));
    }

    public static void RevealPlayer(Player p) {
        for (Player jogador : Bukkit.getOnlinePlayers()) {
            jogador.showPlayer(MineSkyEvents.get(), p);
        }
        p.setAllowFlight(false);
        p.setFlying(false);
        p.setInvisible(false);
        p.setGameMode(GameMode.SURVIVAL);
        p.removePotionEffect(PotionEffectType.INVISIBILITY);
        p.removePotionEffect(PotionEffectType.FIRE_RESISTANCE);
        p.removePotionEffect(PotionEffectType.DAMAGE_RESISTANCE);
        p.removePotionEffect(PotionEffectType.WATER_BREATHING);
        p.removePotionEffect(PotionEffectType.NIGHT_VISION);
    }
        }
