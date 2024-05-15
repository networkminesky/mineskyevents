package minesky.msne.commands;

import minesky.msne.MineSkyEvents;
import minesky.msne.config.*;
import minesky.msne.events.*;
import minesky.msne.system.event.EventVerification;
import minesky.msne.utils.Util;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.bukkit.inventory.PlayerInventory;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class EventCommand implements CommandExecutor, TabCompleter {
    public List<String> strings = new ArrayList<>();
    public List<String> id = new ArrayList<>();
    public List<String> names = new ArrayList<>();
    @Override
    public List<String> onTabComplete(CommandSender s, Command cmd, String lbl, String[] args) {
        id.add("anunciar");
        id.add("blacklist");
        id.add("entrar");
        id.add("kick");
        id.add("set");
        id.add("start");
        names.add("TijolãoWars");
        names.add("Corrida");
        names.add("TNTRun");
        names.add("CorridaBoat");
        names.add("Sumo");
        if (args.length == 1) {
            for (String idif : id) {
                for (int i = 1; i <= idif.length(); i++) {
                    if (args[0].equalsIgnoreCase(idif.substring(0, i))) {
                        strings.clear();
                        strings.add(idif);
                    } else if (args[0].length() == 0) {
                        strings.clear();
                        if (s.hasPermission("mineskyevents.command.event.anunciar")) strings.add("anunciar");
                        if (s.hasPermission("mineskyevents.command.event.blacklist")) strings.add("blacklist");
                        strings.add("entrar");
                        if (s.hasPermission("mineskyevents.command.event.kick")) strings.add("kick");
                        if (s.hasPermission("mineskyevents.command.event.set")) strings.add("set");
                        if (s.hasPermission("mineskyevents.command.event.start")) strings.add("start");
                    }
                }
            }
        }
        if (args.length == 2 && args[0].equalsIgnoreCase("blacklist")) {
            strings.clear();
            if (!s.hasPermission("mineskyevents.command.event.blacklist")) return strings;
            strings.add("Adicionar");
            strings.add("Remover");
        }
        if (args.length == 2 && args[0].equalsIgnoreCase("set") || args[0].equalsIgnoreCase("start") || args[0].equalsIgnoreCase("anunciar")) {
            for (String name : names) {
                for (int i = 1; i <= name.length() ; i++) {
                    if (args[1].equalsIgnoreCase(name.substring(0, i))) {
                        strings.clear();
                        if (!s.hasPermission("mineskyevents.command.event.set") || !s.hasPermission("mineskyevents.command.event.start") || !s.hasPermission("mineskyevents.command.event.anunciar")) return strings;
                        strings.add(name);
                    } else if (args[1].length() == 0) {
                        strings.clear();
                        if (!s.hasPermission("mineskyevents.command.event.set") || !s.hasPermission("mineskyevents.command.event.start") || !s.hasPermission("mineskyevents.command.event.anunciar")) return strings;
                        strings.addAll(names);
                    }
                }
            }
        }
        if (args.length == 2 && args[0].equalsIgnoreCase("kick")) {
            for (String ps : Util.PVE()) {
                for (int i = 1; i <= ps.length() ; i++) {
                    if (args[1].equalsIgnoreCase(ps.substring(0, i))) {
                        strings.clear();
                        strings.add(ps);
                    } else if (args[1].length() == 0) {
                        strings.clear();
                        if (!s.hasPermission("mineskyevents.command.event.kick")) return strings;
                        strings.addAll(Util.PVE());
                    }
                }
            }
        }
        if (args.length == 3 && args[0].equalsIgnoreCase("set")) {
            strings.clear();
            if (!s.hasPermission("mineskyevents.command.event.set")) return strings;
            strings.add("spawn");
            strings.add("arena");
        }
        if (args.length == 3 && args[0].equalsIgnoreCase("blacklist")) {
            for (String name : names) {
                for (int i = 1; i <= name.length() ; i++) {
                    if (args[2].equalsIgnoreCase(name.substring(0, i))) {
                        strings.clear();
                        if (!s.hasPermission("mineskyevents.command.event.blacklist")) return strings;
                        strings.add(name);
                    } else if (args[2].length() == 0) {
                        strings.clear();
                        if (!s.hasPermission("mineskyevents.command.event.blacklist")) return strings;
                        strings.addAll(names);
                    }
                }
            }
        }
        if (args.length == 4 && args[0].equalsIgnoreCase("blacklist")) {
            for (String ps : Util.getOnlinePlayerNames()) {
                for (int i = 1; i <= ps.length(); i++) {
                    if (args[3].equalsIgnoreCase(ps.substring(0, i))) {
                        strings.clear();
                        strings.add(ps);
                    } else if (args[3].length() == 0) {
                        strings.clear();
                        strings.addAll(Util.getOnlinePlayerNames());
                    }
                }
            }
        }
        if (args.length == 4 && args[0].equalsIgnoreCase("set")) {
            strings.clear();
            if (!s.hasPermission("mineskyevents.command.event.set")) return strings;
            strings.add("1");
            strings.add("2");
            strings.add("3");
            strings.add("4");
            strings.add("5");
            strings.add("6");
            strings.add("7");
            strings.add("8");
            strings.add("9");
            strings.add("10");
        }
        if (args.length == 5 && args[0].equalsIgnoreCase("blacklist")) {
            strings.clear();
            if (!s.hasPermission("mineskyevents.command.event.blacklist")) return strings;
            strings.add("IP");
        }
        return strings;
    }

    @Override
    public boolean onCommand(CommandSender s, Command cmd, String lbl, String[] args) {
     //   if (!(s instanceof Player))
        Player player = (Player) s;
        if (args.length < 1 || args[0].equalsIgnoreCase("entrar")) {
            if (MineSkyEvents.event.equalsIgnoreCase("OFF")) {
                s.sendMessage("§8[§c!§8] §cNenhum evento está acontecendo agora.");
                return true;
            }
            if (EventVerification.getBlacklist(player, MineSkyEvents.event)) {
                Bukkit.getLogger().warning(player.getName() + " tentou entrar mas está na blacklist do evento:" + MineSkyEvents.event);
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
                case "TijolãoWars":
                    if (!TijolãoWarsEvent.contagem && TijolãoWarsEvent.contagemI) {
                        Util.Head = Util.head(player);
                        player.getInventory().setItem(8, Util.BedLeave);
                        player.getInventory().setItem(4, Util.Head);
                        config.set("EventSpect", true);
                        try {
                            config.save(file);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        return true;
                    }
                    Util.Head = Util.head(player);
                    clearInventory(player);
                    player.getInventory().setItem(8, Util.BedLeave);
                    player.getInventory().setItem(4, Util.Head);
                    if (TijolãoWarsEvent.playerson != null && !TijolãoWarsEvent.contagemI) {
                        TijolãoWarsEvent.playerson.add(player);
                    }
                   if (!TijolãoWarsEvent.contagemI) TijolãoWarsEvent.comtagemEvento();
                    config.set("Event", true);
                    try {
                        config.save(file);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    Bukkit.getScheduler().runTaskLater(MineSkyEvents.get(), new Runnable() {
                        @Override
                        public void run() {
                            if (TijolãoWarsEvent.selectedMap.equals("Mapa1")) {
                                player.teleport(Locations.tijolao, PlayerTeleportEvent.TeleportCause.COMMAND);
                            }
                            if (TijolãoWarsEvent.selectedMap.equals("Mapa2")) {
                                player.teleport(Locations.tijolao2, PlayerTeleportEvent.TeleportCause.COMMAND);
                            }
                            s.sendMessage("§8[§a!§8] §aVocê entrou no evento!");
                        }
                    }, 20L);
                    for (Player p : Bukkit.getOnlinePlayers()) {
                        if (!Util.PDVE(p)) return true;
                        int playersone = TijolãoWarsEvent.playerson.size();
                        p.sendMessage("§7" + player.getName() + " §eentrou no evento. (§b" + playersone + "§e/§b4§e)");
                    }

                    break;
                case "Corrida":
                    Util.Head = Util.head(player);
                    clearInventory(player);
                    player.getInventory().setItem(8, Util.BedLeave);
                    player.getInventory().setItem(4, Util.Head);
                    if (CorridaEvent.playerson != null && !CorridaEvent.contagemI) {
                        CorridaEvent.playerson.add(player);
                    }
                    if (!CorridaEvent.contagemI) CorridaEvent.comtagemEvento();
                    config.set("Event", true);
                    try {
                        config.save(file);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    Bukkit.getScheduler().runTaskLater(MineSkyEvents.get(), new Runnable() {
                        @Override
                        public void run() {
                            player.teleport(Locations.corrida, PlayerTeleportEvent.TeleportCause.COMMAND);
                            s.sendMessage("§8[§a!§8] §aVocê entrou no evento!");
                        }
                    }, 20L);
                    for (Player p : Bukkit.getOnlinePlayers()) {
                        if (!Util.PDVE(p)) return true;
                        int playersone = CorridaEvent.playerson.size();
                        p.sendMessage("§7" + player.getName() + " §eentrou no evento. (§b" + playersone + "§e/§b4§e)");
                    }
                    break;
                case "CorridaBoat":
                    Util.Head = Util.head(player);
                    clearInventory(player);
                    player.getInventory().setItem(8, Util.BedLeave);
                    player.getInventory().setItem(4, Util.Head);
                    if (CorridaBoatEvent.playerson != null && !CorridaBoatEvent.contagemI) {
                        CorridaBoatEvent.playerson.add(player);
                    }
                    if (!CorridaBoatEvent.contagemI) CorridaBoatEvent.comtagemEvento();
                    config.set("Event", true);
                    try {
                        config.save(file);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    Bukkit.getScheduler().runTaskLater(MineSkyEvents.get(), new Runnable() {
                        @Override
                        public void run() {
                            player.teleport(Locations.corridaboat, PlayerTeleportEvent.TeleportCause.COMMAND);
                            s.sendMessage("§8[§a!§8] §aVocê entrou no evento!");
                        }
                    }, 20L);
                    for (Player p : Bukkit.getOnlinePlayers()) {
                        if (!Util.PDVE(p)) return true;
                        int playersone = CorridaBoatEvent.playerson.size();
                        p.sendMessage("§7" + player.getName() + " §eentrou no evento. (§b" + playersone + "§e/§b4§e)");
                    }
                    break;
                case "Sumo":
                    if (!SumoEvent.contagem && SumoEvent.contagemI) {
                        player.teleport(Locations.sumo, PlayerTeleportEvent.TeleportCause.COMMAND);
                        Util.Head = Util.head(player);
                        player.getInventory().setItem(8, Util.BedLeave);
                        player.getInventory().setItem(4, Util.Head);
                        config.set("EventSpect", true);
                        try {
                            config.save(file);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        return true;
                    }
                    Util.Head = Util.head(player);
                    clearInventory(player);
                    player.getInventory().setItem(8, Util.BedLeave);
                    player.getInventory().setItem(4, Util.Head);
                    if (SumoEvent.playerson != null && !SumoEvent.contagemI) {
                        SumoEvent.playerson.add(player);
                    }
                    if (!SumoEvent.contagemI) SumoEvent.comtagemEvento();
                    config.set("Event", true);
                    try {
                        config.save(file);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    Bukkit.getScheduler().runTaskLater(MineSkyEvents.get(), new Runnable() {
                        @Override
                        public void run() {
                            player.teleport(Locations.sumo, PlayerTeleportEvent.TeleportCause.COMMAND);
                            s.sendMessage("§8[§a!§8] §aVocê entrou no evento!");
                        }
                    }, 20L);
                    for (Player p : Bukkit.getOnlinePlayers()) {
                        if (!Util.PDVE(p)) return true;
                        int playersone = SumoEvent.playerson.size();
                        p.sendMessage("§7" + player.getName() + " §eentrou no evento. (§b" + playersone + "§e/§b4§e)");
                    }

                    break;
            }
        }
        if (args[0].equalsIgnoreCase("anunciar")) {
            if (!s.hasPermission("mineskyevents.command.event.anunciar")) {
                s.sendMessage("§8[§c!§8] §cVocê não pode executar esse comando.");
                return true;
            }
            switch (MineSkyEvents.event) {
                case "TijolãoWars":
                    Util.sendMessageBGMSNE("TijolãoWars");
                    break;
                case "Corrida":
                    Util.sendMessageBGMSNE("Corrida");
                    break;
                case "TNTRun":
                    Util.sendMessageBGMSNE("TNTRun");
                    break;
                case "CorridaBoat":
                    Util.sendMessageBGMSNE("CorridaBoat");
                    break;
                case "Sumo":
                    Util.sendMessageBGMSNE("Sumo");
                    break;
            }
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
            switch (args[1]) {
                case "Spleef":
                    //SpleefEvent.iniciarEvento();
                    s.sendMessage("§8[§c!§8] §cEvento indisponível.");
                    break;
                case "TijolãoWars":
                    TijolãoWarsEvent.iniciarEvento();
                    break;
                case "Runners":
                    //RunnersEvent.iniciarEvento();
                    s.sendMessage("§8[§c!§8] §cEvento indisponível.");
                    break;
                case "Corrida":
                    CorridaEvent.iniciarEvento();
                    break;
                case "CorridaBoat":
                    CorridaBoatEvent.iniciarEvento();
                    break;
                case "PegaBandeira":
                    //orridaEvent.iniciarEvento();
                    s.sendMessage("§8[§c!§8] §cEvento indisponível.");
                    break;
                case "TNTRun":
                    TNTRunEvent.iniciarEvento();
                    break;
                case "Sumo":
                    SumoEvent.iniciarEvento();
                    break;
            }
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
                    if (args[1].equalsIgnoreCase("TijolãoWars")) {
                        if (args[3].equalsIgnoreCase("1")) {
                            if (args[2].equalsIgnoreCase("spawn")) {
                                Location loc = ((Player) s).getLocation();
                                File file = DataManager.getFile("locations.yml");
                                FileConfiguration config = DataManager.getConfiguration(file);

                                Locations.tijolao = loc;
                                config.set("Tijolão", loc);
                                config.set("Tijolão.world", loc.getWorld().getName());
                                config.set("Tijolão.x", Double.valueOf(loc.getX()));
                                config.set("Tijolão.y", Double.valueOf(loc.getY()));
                                config.set("Tijolão.z", Double.valueOf(loc.getZ()));
                                config.set("Tijolão.yaw", Float.valueOf(loc.getYaw()));
                                config.set("Tijolão.pitch", Float.valueOf(loc.getPitch()));
                                try {
                                    config.save(file);
                                    s.sendMessage("§8[§a!§8] §aSpawn de Tijolão Wars setado para os eventos com sucesso.");
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
                                config.set("arena.Tijolão", loc);
                                config.set("arena.Tijolão.world", loc.getWorld().getName());
                                config.set("arena.Tijolão.x", Double.valueOf(loc.getX()));
                                config.set("arena.Tijolão.y", Double.valueOf(loc.getY()));
                                config.set("arena.Tijolão.z", Double.valueOf(loc.getZ()));
                                config.set("arena.Tijolão.yaw", Float.valueOf(loc.getYaw()));
                                config.set("arena.Tijolão.pitch", Float.valueOf(loc.getPitch()));
                                try {
                                    config.save(file);
                                    s.sendMessage("§8[§a!§8] §aArena de TijolãoWars setado para os eventos com sucesso.");
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
                                config.set("Tijolão2", loc);
                                config.set("Tijolão2.world", loc.getWorld().getName());
                                config.set("Tijolão2.x", Double.valueOf(loc.getX()));
                                config.set("Tijolão2.y", Double.valueOf(loc.getY()));
                                config.set("Tijolão2.z", Double.valueOf(loc.getZ()));
                                config.set("Tijolão2.yaw", Float.valueOf(loc.getYaw()));
                                config.set("Tijolão2.pitch", Float.valueOf(loc.getPitch()));
                                try {
                                    config.save(file);
                                    s.sendMessage("§8[§a!§8] §aSpawn de Tijolão Wars setado para os eventos com sucesso.");
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
                                config.set("arena.Tijolão2", loc);
                                config.set("arena.Tijolão2.world", loc.getWorld().getName());
                                config.set("arena.Tijolão2.x", Double.valueOf(loc.getX()));
                                config.set("arena.Tijolão2.y", Double.valueOf(loc.getY()));
                                config.set("arena.Tijolão2.z", Double.valueOf(loc.getZ()));
                                config.set("arena.Tijolão2.yaw", Float.valueOf(loc.getYaw()));
                                config.set("arena.Tijolão2.pitch", Float.valueOf(loc.getPitch()));
                                try {
                                    config.save(file);
                                    s.sendMessage("§8[§a!§8] §aArena de TijolãoWars setado para os eventos com sucesso.");
                                } catch (IOException e) {
                                    Bukkit.getConsoleSender().sendMessage(Messages.Falied_to_save.replace("%arquivo%", "locations.yml"));
                                }
                                return true;
                            }
                        } else {
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
                            s.sendMessage("§8[§a!§8] §aSpawn de Corrida setado para os eventos com sucesso.");
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
                            s.sendMessage("§8[§a!§8] §aArena de Corrida setado para os eventos com sucesso.");
                        } catch (IOException e) {
                            Bukkit.getConsoleSender().sendMessage(Messages.Falied_to_save.replace("%arquivo%", "locations.yml"));
                        }
                        return true;
                    }
                } else {
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
                            s.sendMessage("§8[§a!§8] §aSpawn de Corrida de barco setado para os eventos com sucesso.");
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
                            s.sendMessage("§8[§a!§8] §aArena de Corrida de barco setado para os eventos com sucesso.");
                        } catch (IOException e) {
                            Bukkit.getConsoleSender().sendMessage(Messages.Falied_to_save.replace("%arquivo%", "locations.yml"));
                        }
                        return true;
                    }
                } else {
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
                            s.sendMessage("§8[§a!§8] §aSpawn de Sumo setado para os eventos com sucesso.");
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
                            s.sendMessage("§8[§a!§8] §aArena de Sumo setado para os eventos com sucesso.");
                        } catch (IOException e) {
                            Bukkit.getConsoleSender().sendMessage(Messages.Falied_to_save.replace("%arquivo%", "locations.yml"));
                        }
                        return true;
                    }
                } else {
                    s.sendMessage("§8[§c!§8] §cEste evento só tem 1 mapa.");
                }
            }
                }
        if (args[0].equalsIgnoreCase("kick")) {
                    if (!s.hasPermission("mineskyevents.command.event.kick")) {
                        s.sendMessage(Messages.No_permission);
                        return true;
                    }
                    if (args.length < 2) {
                        s.sendMessage("§8[§c!§8] §cUso incorreto. Use /event kick (Player) [Motivo]");
                        return true;
                    }
                    Player j = Bukkit.getPlayer(args[1]);
                    if (!j.isOnline()) {
                        s.sendMessage("§8[§c!§8] §cO jogador não foi encontrado");
                        return true;
                    }
                    if (!Util.PDVE(j)) {
                        s.sendMessage("§8[§c!§8] §cO jogador não está online no evento.");
                        return true;
                    }
                    String reason = "Sem motivo informado.";
                    String[] argsSubset = Arrays.copyOfRange(args, 2, args.length);
                    String result = String.join(" ", argsSubset).replace("&", "§");
                    if (args.length == 3) reason = result;
                    Util.sendConectionBCMSNE(j);
                    TextComponent message = new TextComponent("§8[§c!§8] §cVocê foi kickado do evento. Motivo: " + reason);
                    Util.sendPlayermessage(j, message);

                    s.sendMessage("§8[§a!§8] §aO jogador foi kickado com sucesso do evento.");
            for (Player player1 : Bukkit.getOnlinePlayers()) {
                File file = DataManager.getFile(player1.getName().toLowerCase(), "playerdata");
                FileConfiguration configfor = DataManager.getConfiguration(file);
                if (!player1.hasPermission("mineskyevents.notify.moderation")) return true;
                if (!configfor.getBoolean("Notification")) return true;
                player1.sendMessage("§c§lNotificação §7» §7O jogador §b" + j.getName() + "§7 foi kickado do evento por§b " + s.getName() + "§7. Motivo: §b" + reason);
            }
                    File file = DataManager.getFile(j.getName().toLowerCase(), "playerdata");
                    FileConfiguration config = DataManager.getConfiguration(file);

                    config.set("Event", false);

                    try {
                        config.save(file);
                        switch (MineSkyEvents.event) {
                            case "TijolãoWars":
                               TijolãoWarsEvent.playerson.remove(player);
                                if (TijolãoWarsEvent.playerson.size() == 1 && !TijolãoWarsEvent.contagem && TijolãoWarsEvent.contagemI) TijolãoWarsEvent.finalizar();
                                break;
                            case "Corrida":
                                CorridaEvent.playerson.remove(j);
                                break;
                            case "CorridaBoat":
                                CorridaBoatEvent.playerson.remove(j);
                                break;
                            case "Sumo":
                                SumoEvent.playerson.remove(j);
                                break;
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    return true;
                }
        if (args[0].equalsIgnoreCase("blacklist")) {
            if (!s.hasPermission("mineskyevents.command.event.blacklist")) {
                s.sendMessage("§8[§c!§8] §cVocê não pode executar esse comando.");
                return true;
            }
            File file = DataManager.getFile("config.yml");
            FileConfiguration config = DataManager.getConfiguration(file);
            String event = args[2];
            Player pb = Bukkit.getPlayer(args[3]);
            switch (event) {
                case "TijolãoWars":
                    event = "tijolãowars";
                    break;
                case "TNTRun":
                    event = "tntrun";
                    break;
                case "Corrida":
                    event = "corrida";
                    break;
                case "CorridaBoat":
                    event = "corridaboat";
                    break;
                case "Sumo":
                    event = "sumo";
                    break;
            }
            if (args[1].equalsIgnoreCase("adicionar")) {
                if (!(args.length == 5)) {
                    List<String> blacklistname = config.getStringList("blacklist." + event + ".list");
                    if (blacklistname.contains(pb.getName())) {
                        s.sendMessage("§8[§c!§8] §cEste player já está na blacklist.");
                        return true;
                    }
                    blacklistname.add(pb.getName());
                    config.set("blacklist." + event + ".list", blacklistname);
                    try {
                        config.save(file);
                        s.sendMessage("§8[§a!§8] §aVocê adicionar o " + pb.getName() + " na blacklist do evento " + event + " com sucesso!");
                    } catch (IOException e) {
                        s.sendMessage("§8[§c!§8] §cOcorreu um erro ao adicionar o player á blacklist");
                    }
                } else {
                    if (!args[4].equalsIgnoreCase("ip")) {
                        s.sendMessage("§8[§c!§8] §cUsage: /event blacklist adicionar (event) (player) ip");
                        return true;
                    }
                    List<String> blacklistname = config.getStringList("blacklistip." + event + ".list");
                    if (blacklistname.contains(pb.getAddress().getAddress().getHostAddress())) {
                        s.sendMessage("§8[§c!§8] §cEste player já está na blacklist.");
                        return true;
                    }
                    blacklistname.add(pb.getAddress().getAddress().getHostAddress());
                    config.set("blacklistip." + event + ".list", blacklistname);
                    try {
                        config.save("config.yml");
                        s.sendMessage("§8[§a!§8] §aVocê adicionar o " + pb.getName() + " na blacklist por ip do evento " + event + " com sucesso!");
                    } catch (IOException e) {
                        s.sendMessage("§8[§c!§8] §cOcorreu um erro ao adicionar o player á blacklist por ip");
                    }
                }
            }
            if (args[1].equalsIgnoreCase("remover")) {
                if (!(args.length == 5)) {
                    List<String> blacklistname = config.getStringList("blacklist." + event + ".list");
                    if (!blacklistname.contains(pb.getName())) {
                        s.sendMessage("§8[§c!§8] §cEste player não está na blacklist.");
                        return true;
                    }
                    blacklistname.remove(pb.getName());
                    config.set("blacklist." + event + ".list", blacklistname);
                    try {
                        config.save("config.yml");
                        s.sendMessage("§8[§a!§8] §aVocê removeu o " + pb.getName() + " na blacklist do evento " + event + " com sucesso!");
                    } catch (IOException e) {
                        s.sendMessage("§8[§c!§8] §cOcorreu um erro ao remover o player á blacklist");
                    }
                } else {
                    if (!args[4].equalsIgnoreCase("ip")) {
                        s.sendMessage("§8[§c!§8] §cUsage: /event blacklist remover (event) (player) ip");
                        return true;
                    }
                    List<String> blacklistname = config.getStringList("blacklistip." + event + ".list");
                    if (!blacklistname.contains(pb.getAddress().getAddress().getHostAddress())) {
                        s.sendMessage("§8[§c!§8] §cEste player não está na blacklist por ip.");
                        return true;
                    }
                    blacklistname.remove(pb.getAddress().getAddress().getHostAddress());
                    config.set("blacklistip." + event + ".list", blacklistname);
                    try {
                        config.save("config.yml");
                        s.sendMessage("§8[§a!§8] §aVocê removeu o " + pb.getName() + " na blacklist por ip do evento " + event + " com sucesso!");
                    } catch (IOException e) {
                        s.sendMessage("§8[§c!§8] §cOcorreu um erro ao remover o player á blacklist por ip");
                    }
                }
            }
        }

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
        }
