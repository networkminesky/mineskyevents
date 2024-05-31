package minesky.msne.commands.console;

import minesky.msne.MineSkyEvents;
import minesky.msne.commands.MSNECommand;
import minesky.msne.config.DataManager;
import minesky.msne.config.Messages;
import minesky.msne.events.*;
import minesky.msne.system.event.EventCorridasPlayerManager;
import minesky.msne.system.event.EventPlayerManager;
import minesky.msne.utils.SendMessages;
import minesky.msne.utils.Util;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffectType;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class EventCommandConsole {
    public CommandSender s;
    public Command cmd;
    public String lbl;
    public String[] args;
    public static String selectedMap = "Mapa-0";
    public static boolean EventsAgendados = false;
    public static TextComponent textfinalizar = new TextComponent("EVENTO FINALIZADO");

    public EventCommandConsole(CommandSender s, Command cmd, String lbl, String[] args) {
        this.s = s;
        this.cmd = cmd;
        this.lbl = lbl;
        this.args = args;
        onCommandConsole();
    }

    public void onCommandConsole() {
        if (args.length < 1 || args[0].equalsIgnoreCase("entrar")) {
            s.sendMessage("§8[§c!§8] §cEsse comando é apenas para jogadores.");
            return;
        }
        if (args[0].equalsIgnoreCase("anunciar")) {
            if (MineSkyEvents.event.equals("OFF") && args.length < 2) {
                s.sendMessage("§8[§c!§8] §cNenhum evento esta acontecendo agora.");
                return;
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
                        return;
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
                        return;
                    }
                    MineSkyEvents.event = "Mini-Wars";
                    SendMessages.sendMessageBGMSNE("Mini-Wars");
                    break;
                case "copapvp":
                    if (args.length < 3) return;
                    int selectMapC = Integer.parseInt(args[2]);
                    if (selectMapC == 1) {
                        selectedMap = "Mapa-1";
                    }
                    if (selectMapC > 1) {
                        s.sendMessage("§8[§c!§8] §cEste evento só tem 1 mapas!");
                        return;
                    }
                    MineSkyEvents.event = "CopaPVP";
                    SendMessages.sendMessageBGMSNE("CopaPVP");
                    break;
                case "esconde-esconde":
                    if (args.length < 3) return;
                    int selectMapE = Integer.parseInt(args[2]);
                    if (selectMapE == 1) {
                        selectedMap = "Mapa-1";
                    } else if (selectMapE == 2) {
                        selectedMap = "Mapa-2";
                    }
                    if (selectMapE > 2) {
                        s.sendMessage("§8[§c!§8] §cEste evento só tem 2 mapas!");
                        return;
                    }
                    MineSkyEvents.event = "Esconde-esconde";
                    SendMessages.sendMessageBGMSNE("Esconde-esconde");
                    break;
                case "ruínas":
                    if (args.length < 3) return;
                    int selectMapR = Integer.parseInt(args[2]);
                    if (selectMapR == 1) {
                        selectedMap = "Mapa-1";
                    }
                    if (selectMapR > 1) {
                        s.sendMessage("§8[§c!§8] §cEste evento só tem 1 mapas!");
                        return;
                    }
                    MineSkyEvents.event = "Ruínas";
                    SendMessages.sendMessageBGMSNE("Ruínas");
                    break;
            }
            Bukkit.getLogger().warning("[MineSky-Events] Você anúnciou o evento: " + MineSkyEvents.event);
            return;
        }
        if (args[0].equalsIgnoreCase("finalizar")) {
            if (MineSkyEvents.event.equalsIgnoreCase("OFF")) {
                s.sendMessage("§8[§c!§8] §cNenhum evento acontecendo agora.");
                return;
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
                    CorridaBoatEvent.playerBOATLIST.clear();
                    EventPlayerManager.clearPlayerManager();
                    EventPlayerManager.clearPlayerItem();
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
            Bukkit.getLogger().warning("[MineSky-Events] Você finalizou o evento: " + event);
            s.sendMessage("§8[§a!§8] §aVocê finalizou o evento: " + event);
            return;
        }
        if (args[0].equalsIgnoreCase("start")) {
            if (args.length < 2) {
                s.sendMessage("§8[§c!§8] §cInforme um evento");
                return;
            }
            switch (args[1].toLowerCase()) {
                case "spleef":
                    SpleefEvent.iniciarEvento();
                    break;
                case "tijolãowars":
                    TijolãoWarsEvent.iniciarEvento();
                    break;
                case "runners":
                    s.sendMessage("§8[§c!§8] §cEvento indisponível.");
                    break;
                case "corrida":
                    CorridaEvent.iniciarEvento();
                    break;
                case "corridaboat":
                    CorridaBoatEvent.iniciarEvento();
                    break;
                case "pegabandeira":
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
            Bukkit.getLogger().warning("[MineSky-Events] Você iniciou o evento: " + args[1]);
            return;
        }
        if (args[0].equalsIgnoreCase("set")) {
            s.sendMessage("§8[§c!§8] §cEsse comando é apenas para jogadores.");
            return;
        }
        if (args[0].equalsIgnoreCase("kick")) {
            if (args.length < 2) {
                s.sendMessage("§8[§c!§8] §cUso incorreto. Use /event kick (Player) [Motivo]");
                return;
            }
            Player j = Bukkit.getPlayer(args[1]);
            if (!j.isOnline()) {
                s.sendMessage("§8[§c!§8] §cO jogador não foi encontrado");
                return;
            }
            if (!Util.PDVE(j)) {
                s.sendMessage("§8[§c!§8] §cO jogador não está online no evento.");
                return;
            }
            String reason = "Sem motivo informado.";
            String[] argsSubset = Arrays.copyOfRange(args, 2, args.length);
            String result = String.join(" ", argsSubset).replace("&", "§");
            if (args.length == 3) reason = result;

            File file = DataManager.getFile(j.getName().toLowerCase(), "playerdata");
            FileConfiguration config = DataManager.getConfiguration(file);

            config.set("Event", false);

            try {
                config.save(file);
                SendMessages.sendConectionBCMSNE(j);
                TextComponent message = new TextComponent("§8[§c!§8] §cVocê foi expulso do evento. Motivo: " + reason);
                SendMessages.sendPlayermessage(j, message);
                s.sendMessage("§8[§a!§8] §aO jogador foi expulso com sucesso do evento.");
                s.sendMessage("§8[§a!§8] §aO jogador foi expulso com sucesso do evento.");
                for (Player player1 : Bukkit.getOnlinePlayers()) {
                    File filefor = DataManager.getFile(player1.getName().toLowerCase(), "playerdata");
                    FileConfiguration configfor = DataManager.getConfiguration(filefor);
                    if (!player1.hasPermission("mineskyevents.notify.moderation")) return;
                    if (!configfor.getBoolean("Notification")) return;
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
                e.printStackTrace();
            }
            return;
        }
        if (args[0].equalsIgnoreCase("blacklist")) {
            File file = DataManager.getFile("config.yml");
            FileConfiguration config = DataManager.getConfiguration(file);
            String event = args[2].toLowerCase();
            Player pb = Bukkit.getPlayer(args[3]);
            if (args[1].equalsIgnoreCase("adicionar")) {
                if (!(args.length == 5)) {
                    List<String> blacklistname = config.getStringList("blacklist." + event + ".list");
                    if (blacklistname.contains(pb.getName())) {
                        s.sendMessage("§8[§c!§8] §cEste player já está na blacklist.");
                        return;
                    }
                    blacklistname.add(pb.getName());
                    config.set("blacklist." + event + ".list", blacklistname);
                    try {
                        config.save(file);
                        s.sendMessage("§8[§a!§8] §aVocê adicionar o " + pb.getName() + " na blacklist do evento " + event + " com sucesso!");
                    } catch (IOException e) {
                        s.sendMessage("§8[§c!§8] §cOcorreu um erro ao adicionar o player à blacklist");
                    }
                } else {
                    if (!args[4].equalsIgnoreCase("ip")) {
                        s.sendMessage("§8[§c!§8] §cUsage: /event blacklist adicionar (event) (player) ip");
                        return;
                    }
                    List<String> blacklistname = config.getStringList("blacklistip." + event + ".list");
                    if (blacklistname.contains(pb.getAddress().getAddress().getHostAddress())) {
                        s.sendMessage("§8[§c!§8] §cEste player já está na blacklist.");
                        return;
                    }
                    blacklistname.add(pb.getAddress().getAddress().getHostAddress());
                    config.set("blacklistip." + event + ".list", blacklistname);
                    try {
                        config.save(file);
                        s.sendMessage("§8[§a!§8] §aVocê adicionou o " + pb.getName() + " na blacklist por ip do evento " + event + " com sucesso!");
                    } catch (IOException e) {
                        s.sendMessage("§8[§c!§8] §cOcorreu um erro ao adicionar o player à blacklist por ip");
                    }
                }
            }
            if (args[1].equalsIgnoreCase("remover")) {
                if (!(args.length == 5)) {
                    List<String> blacklistname = config.getStringList("blacklist." + event + ".list");
                    if (!blacklistname.contains(pb.getName())) {
                        s.sendMessage("§8[§c!§8] §cEste player não está na blacklist.");
                        return;
                    }
                    blacklistname.remove(pb.getName());
                    config.set("blacklist." + event + ".list", blacklistname);
                    try {
                        config.save(file);
                        s.sendMessage("§8[§a!§8] §aVocê removeu o " + pb.getName() + " na blacklist do evento " + event + " com sucesso!");
                    } catch (IOException e) {
                        s.sendMessage("§8[§c!§8] §cOcorreu um erro ao remover o player à blacklist");
                    }
                } else {
                    if (!args[4].equalsIgnoreCase("ip")) {
                        s.sendMessage("§8[§c!§8] §cUsage: /event blacklist remover (event) (player) ip");
                        return;
                    }
                    List<String> blacklistname = config.getStringList("blacklistip." + event + ".list");
                    if (!blacklistname.contains(pb.getAddress().getAddress().getHostAddress())) {
                        s.sendMessage("§8[§c!§8] §cEste player não está na blacklist por ip.");
                        return;
                    }
                    blacklistname.remove(pb.getAddress().getAddress().getHostAddress());
                    config.set("blacklistip." + event + ".list", blacklistname);
                    try {
                        config.save(file);
                        s.sendMessage("§8[§a!§8] §aVocê removeu o " + pb.getName() + " na blacklist por ip do evento " + event + " com sucesso!");
                    } catch (IOException e) {
                        s.sendMessage("§8[§c!§8] §cOcorreu um erro ao remover o player à blacklist por ip");
                    }
                }
            }
            return;
        }

        s.sendMessage("§8[§c!§8] §cVocê não informou um agurmento valido.");
        return;
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
