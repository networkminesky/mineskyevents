package net.minesky.commands;

import net.md_5.bungee.api.chat.TextComponent;
import net.minesky.MineSkyEvents;
import net.minesky.config.ConfigManager;
import net.minesky.config.DataManager;
import net.minesky.config.Locations;
import net.minesky.events.*;
import net.minesky.system.event.EventCorridasPlayerManager;
import net.minesky.system.event.EventPlayerManager;
import net.minesky.system.event.EventVerification;
import net.minesky.system.event.EventsMakers;
import net.minesky.utils.*;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.command.*;
import org.bukkit.command.Command;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class EventCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender s, Command cmd, String lbl, String[] args) {
        if (s instanceof ConsoleCommandSender || s instanceof RemoteConsoleCommandSender || s instanceof BlockCommandSender) {
            // new MSNECommandConsole(s, cmd, lbl, args);
            s.sendMessage(Utils.color("&cComando desativado temporáriamente para console"));
            return true;
        }

        Player player = (Player) s;
        if (args.length < 1 || args[0].equalsIgnoreCase("entrar")) {
            if (MineSkyEvents.event.equalsIgnoreCase("OFF")) {
                s.sendMessage("§8[§c!§8] §cNenhum evento está acontecendo agora.");
                return true;
            }
            if (EventVerification.getBlacklist(player)) {
                Bukkit.getLogger().warning("[MineSky-Events] " + player.getName() + " Tentou entrar mas está na blacklist do evento: " + MineSkyEvents.event);
                return true;
            }
            if (Utils.isPlayerInEvent(player) || Utils.isPlayerSpectating(player)) {
                s.sendMessage("§8[§c!§8] §cVocê já está no evento ou assistindo.");
                return true;
            }

            FileConfiguration config = Utils.getPlayerData(player);

            Location location = EventData.getEventLocation(MineSkyEvents.event);
            boolean Spectator = EventData.isSpectator(MineSkyEvents.event);
            boolean EventsAgendados = EventData.isEventScheduled(MineSkyEvents.event);

            if (EventsAgendados) {
                teleportPlayer(player, location, "§8[§a!§8] §aVocê entrou no evento!");
                broadcastEntry(player, 0);
                return true;
            }

            if (Spectator) {
                PlayerSpectator(player);
                location = EventData.getEventArenaLocation(MineSkyEvents.event);
                teleportPlayer(player, location, "§8[§a!§8] §aVocê começou a assistir o evento!");
                config.set("Spectator", true);
                savePlayerConfig(config, player);
                return true;
            }

            EventPlayerManager.addPlayer(player);
            EventData.ContagemEvent();
            config.set("Event", true);
            savePlayerConfig(config, player);
            teleportPlayer(player, location, "§8[§a!§8] §aVocê entrou no evento!");
            int RequestPlayerSize = 4;
            if (MineSkyEvents.event.equalsIgnoreCase("TNTTAG")) RequestPlayerSize = 8;
            broadcastEntry(player, RequestPlayerSize);
            return true;
        }

        if (args[0].equalsIgnoreCase("anunciar")) {
            if (!s.hasPermission("mineskyevents.command.event.anunciar")) {
                s.sendMessage("§8[§c!§8] §cVocê não pode executar esse comando.");
                return true;
            }
            if (MineSkyEvents.event.equals("OFF") && args.length < 2) {
                s.sendMessage("§8[§c!§8] §cNenhum evento está acontecendo agora.");
                return true;
            }
            if (args.length < 2) {
                if (MineSkyEvents.event.equals("OFF")) {
                    s.sendMessage("§8[§c!§8] §cNenhum evento está acontecendo agora.");
                    return true;
                }

                String eventName = MineSkyEvents.event;
                SendMessages.sendMessageEvent(eventName);
                Bukkit.getLogger().warning("[MineSky-Events] " + player.getName() + " anunciou o evento: " + MineSkyEvents.event);
                player.playSound(player, Sound.BLOCK_NOTE_BLOCK_BIT, 1.0f, 1.0f);
                return true;
            }

            Set<String> validEvents = new HashSet<>(Arrays.asList("Mini-Wars", "CopaPVP", "Esconde-esconde", "Ruínas"));
            if (args.length > 2 && !validEvents.contains(args[1])) {
                s.sendMessage(Utils.color("&cUtilize /event start (Nome) ou /event anunciar (Para iniciar ou anunciar algum evento automático)"));
                return true;
            }
            if (validEvents.contains(args[1])) {
                if (args.length < 3) {
                    s.sendMessage("§cVocê deve informar um mapa!");
                    return true;
                }

                switch (args[1].toLowerCase()) {
                    case "mini-wars":
                        MineSkyEvents.event = "Mini-Wars";
                        break;
                    case "copapvp":
                        MineSkyEvents.event = "CopaPVP";
                        break;
                    case "esconde-esconde":
                        MineSkyEvents.event = "Esconde-esconde";
                        break;
                    case "ruínas":
                        MineSkyEvents.event = "Ruínas";
                        break;
                    default:
                        return true;
                }
                String selectedMap = selectMap(player, args);

                if (selectedMap == null) return true;
                EventData.setSelectedMap(selectedMap);
                SendMessages.sendMessageEvent(MineSkyEvents.event);
                Bukkit.getLogger().warning("[MineSky-Events] " + player.getName() + " anunciou o evento: " + MineSkyEvents.event);
                player.playSound(player, Sound.BLOCK_NOTE_BLOCK_BIT, 1.0f, 1.0f);
                return true;
            }
            String eventName = MineSkyEvents.event;
            SendMessages.sendMessageEvent(eventName);
            Bukkit.getLogger().warning("[MineSky-Events] " + player.getName() + " anunciou o evento: " + MineSkyEvents.event);
            player.playSound(player, Sound.BLOCK_NOTE_BLOCK_BIT, 1.0f, 1.0f);
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

            String evento = args[1].toLowerCase();
            Bukkit.getLogger().warning("[MineSky-Events] " + player.getName() + " iniciou o evento: " + evento);

            Map<String, Runnable> eventos = Map.of(
                    "spleef", SpleefEvent::iniciarEvento,
                    "tijolãowars", TijolãoWarsEvent::iniciarEvento,
                    "corrida", CorridaEvent::iniciarEvento,
                    //"corridaboat", CorridaBoatEvent::iniciarEvento,
                    //"tntrun", TNTRunEvent::iniciarEvento,
                    "sumo", SumoEvent::iniciarEvento
                    //"tnttag", TNTTagEvent::iniciarEvento
                    //"parapente", ParapenteEvent::iniciarEvento
            );

            if (eventos.containsKey(evento)) {
                eventos.get(evento).run();
            } else {
                Map<String, String> eventosIndisponiveis = Map.of(
                        "runners", "Evento indisponível.",
                        "corridaboat", "Evento indisponível temporáriamente.",
                        "tntrun", "Evento indisponível temporáriamente.",
                        "tnttag", "Evento indisponível temporáriamente.",
                        "parapente", "Evento indisponível temporáriamente.",
                        "capturebandeira", "Evento indisponível.",
                        "mini-wars", "Este evento não pode ser iniciado. Usage: /event anunciar Mini-Wars (mapa)",
                        "copapvp", "Este evento não pode ser iniciado. Usage: /event anunciar CopaPVP (mapa)",
                        "esconde-esconde", "Este evento não pode ser iniciado. Usage: /event anunciar Esconde-esconde (mapa)",
                        "ruínas", "Este evento não pode ser iniciado. Usage: /event anunciar Ruínas (mapa)"
                );

                s.sendMessage("§8[§c!§8] §c" + eventosIndisponiveis.getOrDefault(evento, "Evento não encontrado."));
            }
            EventsMakers.setPlayerStarted(player);
            player.playSound(player, Sound.BLOCK_NOTE_BLOCK_BIT, 1.0f, 1.0f);
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

            Map<String, String> mensagens = new HashMap<>();
            mensagens.put("Spleef", "§b§lSpleef §8| §aEvento finalizado!");
            mensagens.put("TijolãoWars", "§6§lTijolãoWars §8| §aEvento finalizado!");
            mensagens.put("Corrida", "§e§lCorrida §8| §aEvento finalizado!");
            mensagens.put("CorridaBoat", "§9§lCorrida de barco §8| §aEvento finalizado!");
            mensagens.put("Sumo", "§4§lSumo §8| §aEvento finalizado!");
            mensagens.put("TNTRun", "§c§lTNTRUN §8| §aEvento finalizado!");
            mensagens.put("TNTTag", "§c§lTNT-TAG §8| §aEvento finalizado!");
            mensagens.put("Parapente", "§3§lCorrida de parapente §8| §aEvento finalizado!");
            mensagens.put("Mini-Wars", "§6§lMini-Wars §8| §aEvento finalizado!");
            mensagens.put("CopaPVP", "§6§lCopaPVP §8| §aEvento finalizado!");
            mensagens.put("Esconde-esconde", "§d§lEsconde-Esconde §8| §aEvento finalizado!");
            mensagens.put("Ruínas", "§5§lRuínas §8| §aEvento finalizado!");

            TextComponent text = new TextComponent(mensagens.getOrDefault(MineSkyEvents.event, "§fEvento finalizado!"));
            limparEvento(MineSkyEvents.event);
            MineSkyEvents.event = "OFF";
            SendMessages.sendMessage(text);
            for (Player j : Bukkit.getOnlinePlayers()) {
                if (Utils.isPlayerInEvent(j) || Utils.isPlayerSpectating(j)) {
                    SendMessages.sendConnection(j);
                    RevealPlayer(j);
                    File file = DataManager.getFile(j.getUniqueId().toString(), "playerdata");
                    FileConfiguration config = DataManager.getConfiguration(file);
                    config.set("Event", false);
                    config.set("Spectator", false);
                    try {
                        config.save(file);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
            return true;
        }

        if (args[0].equalsIgnoreCase("force")) {
            if (args.length < 2) {
                s.sendMessage(Utils.color("&cUsage: /event force start/skip"));
                return true;
            }
            if (args[1].equalsIgnoreCase("start")) {
                EventData.setForceStart();
                EventData.ContagemEvent();
                s.sendMessage(Utils.color("&aForçando start..."));
                return true;
            }
            if (args[1].equalsIgnoreCase("skip")) {
                EventData.setForceSkip();
                s.sendMessage(Utils.color("&aForçando skip..."));
                return true;
            }
        }

        if (args[0].equalsIgnoreCase("set")) {
            if (!s.hasPermission("mineskyevents.command.event.set")) {
                s.sendMessage("§cVocê não pode executar esse comando.");
                return true;
            }
            if (args.length < 3) {
                s.sendMessage("§cUso incorreto. Use /event set (Evento) (Arena/Spawn) (ID_MAP EX: 1,2,3)");
                return true;
            }

            if (args.length < 4) {
                s.sendMessage("§cUso incorreto. Use /event set (Evento) (Arena/Spawn) (ID_MAP EX: 1, 2,3)");
                return true;
            }
            int arg = Integer.parseInt(args[3]);
            String eventName = args[1].toLowerCase();
            String locationType = args[2].toLowerCase();

            Location loc = ((Player) s).getLocation();
            File file = DataManager.getFile("locations.yml");
            FileConfiguration config = ConfigManager.getConfig("locations.yml");

            boolean isValidEvent = setEventLocation(player, eventName, locationType, arg, loc, config, file);
            if (!isValidEvent) {
                s.sendMessage("§cEste evento não possui esse número de mapas.");
            }
            return true;
        }

        if (args[0].equalsIgnoreCase("kick")) {
            if (!s.hasPermission("mineskyevents.command.event.kick")) {
                s.sendMessage(Utils.color("&cVocê não pode user esse comando."));
                player.playSound(player, Sound.BLOCK_NOTE_BLOCK_BASS, 1.0f, 1.0f);
                return true;
            }

            if (args.length < 2) {
                s.sendMessage("§cUso incorreto. Use /event kick (Player) [Motivo]");
                player.playSound(player, Sound.BLOCK_NOTE_BLOCK_BASS, 1.0f, 1.0f);
                return true;
            }

            Player j = Bukkit.getPlayer(args[1]);
            if (j == null || !j.isOnline()) {
                s.sendMessage("§cO jogador não foi encontrado");
                player.playSound(player, Sound.BLOCK_NOTE_BLOCK_BASS, 1.0f, 1.0f);
                return true;
            }

            if (!Utils.isPlayerInEvent(j)) {
                s.sendMessage("§cO jogador não está online no evento.");
                player.playSound(player, Sound.BLOCK_NOTE_BLOCK_BASS, 1.0f, 1.0f);
                return true;
            }

            String reason = args.length >= 3 ? String.join(" ", Arrays.copyOfRange(args, 2, args.length)).replace("&", "§") : "Sem motivo informado.";

            try {
                File file = DataManager.getFile(j.getUniqueId().toString(), "playerdata");
                FileConfiguration config = DataManager.getConfiguration(file);
                config.set("Event", false);
                config.save(file);

                SendMessages.sendConnection(j);
                SendMessages.sendPlayerMessage(j, new TextComponent("§cVocê foi expulso do evento. Motivo: " + reason));
                player.playSound(player, Sound.BLOCK_NOTE_BLOCK_BIT, 1.0f, 1.0f);
                s.sendMessage("§aO jogador foi expulso com sucesso do evento.");

                Bukkit.getOnlinePlayers().stream()
                        .filter(p -> p.hasPermission("mineskyevents.notify.moderation"))
                        .forEach(p -> p.sendMessage("§c" + j.getName() + "§f foi expulso do evento por§c " + s.getName() + "§f. Motivo: §c" + reason));

                EventPlayerManager.removePlayer(j);

                switch (MineSkyEvents.event) {
                    case "Spleef":
                        if (EventPlayerManager.getPlayerCount() == 1 && !SpleefEvent.contagem && SpleefEvent.contagemI)
                            SpleefEvent.finalizar();
                        break;
                    case "TijolãoWars":
                        if (EventPlayerManager.getPlayerCount() == 1 && !TijolãoWarsEvent.contagem && TijolãoWarsEvent.contagemI)
                            TijolãoWarsEvent.finalizar();
                        break;
                    case "Sumo":
                        if (EventPlayerManager.getPlayerCount() == 1 && !SumoEvent.contagem && SumoEvent.contagemI) SumoEvent.finalizar();
                        break;
                    case "TNTRun":
                        //if (EventPlayerManager.getPlayerCount() == 1 && !TNTRunEvent.contagem && TNTRunEvent.contagemI) TNTRunEvent.finalizar();
                        break;
                    case "TNTTag":
                        TNTTagEvent.jogadores.remove(j);
                         if (EventPlayerManager.getPlayerCount() == 1 && !TNTTagEvent.contagem && TNTTagEvent.contagemI) TNTTagEvent.finalizar();
                        break;
                }
            } catch (IOException e) {
                String errorMsg = "§cOcorreu um erro ao tentar salvar a playerdata do jogador: " + j.getName();
                Bukkit.getLogger().warning("[MineSky-Events] " + errorMsg);
                s.sendMessage(errorMsg);
                player.playSound(player, Sound.BLOCK_NOTE_BLOCK_BASS, 1.0f, 1.0f);
                e.printStackTrace();
            }
            return true;
        }


        if (args[0].equalsIgnoreCase("blacklist")) {
            if (!s.hasPermission("mineskyevents.command.event.blacklist")) {
                s.sendMessage("§cVocê não pode executar esse comando.");
                player.playSound(player, Sound.BLOCK_NOTE_BLOCK_BASS, 1.0f, 1.0f);
                return true;
            }

            if (args.length < 4) {
                s.sendMessage("§cUso incorreto do comando.");
                return true;
            }

            File file = DataManager.getFile("config.yml");
            FileConfiguration config = DataManager.getConfiguration(file);
            String event = args[2].toLowerCase();
            Player pb = Bukkit.getPlayer(args[3]);
            boolean isIP = args.length == 5 && args[4].equalsIgnoreCase("ip");
            String path = "blacklist" + (isIP ? "ip" : "") + "." + event + ".list";
            List<String> blacklist = config.getStringList(path);
            String identifier = isIP ? pb.getAddress().getAddress().getHostAddress() : pb.getName();

            if (args[1].equalsIgnoreCase("adicionar")) {
                if (blacklist.contains(identifier)) {
                    s.sendMessage("§cEste player já está na blacklist.");
                    player.playSound(player, Sound.BLOCK_NOTE_BLOCK_BASS, 1.0f, 1.0f);
                    return true;
                }
                blacklist.add(identifier);
            } else if (args[1].equalsIgnoreCase("remover")) {
                if (!blacklist.contains(identifier)) {
                    s.sendMessage("§cEste player não está na blacklist.");
                    player.playSound(player, Sound.BLOCK_NOTE_BLOCK_BASS, 1.0f, 1.0f);
                    return true;
                }
                blacklist.remove(identifier);
            } else {
                s.sendMessage("§cUso incorreto do comando.");
                return true;
            }

            config.set(path, blacklist);
            try {
                config.save(file);
                player.playSound(player, Sound.BLOCK_NOTE_BLOCK_BIT, 1.0f, 1.0f);
                s.sendMessage("§aVocê " + (args[1].equalsIgnoreCase("adicionar") ? "adicionou" : "removeu") +
                        " " + pb.getName() + " na blacklist " + (isIP ? "por IP " : "") + "do evento " + event + " com sucesso!");
            } catch (IOException e) {
                player.playSound(player, Sound.BLOCK_NOTE_BLOCK_BASS, 1.0f, 1.0f);
                s.sendMessage("§cOcorreu um erro ao modificar a blacklist.");
            }
            return true;
        }
        player.playSound(player, Sound.BLOCK_NOTE_BLOCK_BASS, 1.0f, 1.0f);
        s.sendMessage("§cVocê não informou um agurmento valido.");
        return true;
    }

    private void teleportPlayer(Player player, Location location, String message) {
        clearInventory(player);
        boolean EventsAgendados = EventData.isEventScheduled(MineSkyEvents.event);
        if (!EventsAgendados) {
            player.getInventory().setItem(8, EventItem.getItem("BedLeave"));
            player.getInventory().setItem(4, EventItem.HeadEvents(player));
        }
        Bukkit.getScheduler().runTaskLater(MineSkyEvents.get(), () -> {
            player.teleport(location);
            player.sendMessage(message);
            player.playSound(player, Sound.BLOCK_NOTE_BLOCK_BIT, 1.0f, 1.0f);
        }, 20L);

    }

    private void broadcastEntry(Player player, int RequestPlayerSize) {
        for (Player p : Bukkit.getOnlinePlayers()) {
            p.sendMessage("§7" + player.getName() + " §eentrou no evento. " + ((RequestPlayerSize == 0) ? "" : "(§b" + EventPlayerManager.getPlayerCount() + "§e/§b" + RequestPlayerSize + "§e)"));
        }
    }

    private void PlayerSpectator(Player p) {
        for (Player jogador : Bukkit.getOnlinePlayers()) {
            jogador.hidePlayer(MineSkyEvents.get(), p);
        }
        p.setAllowFlight(true);
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

    private void savePlayerConfig(FileConfiguration config, Player player) {
        try {
            config.save(Utils.getPlayerDataFile(player));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void clearInventory(Player p) {
        PlayerInventory inv = p.getInventory();
        p.setItemOnCursor(null);
        inv.clear();
        inv.setHelmet(null);
        inv.setChestplate(null);
        inv.setLeggings(null);
        inv.setBoots(null);
    }

    private String selectMap(Player player, String[] args) {
        if (args.length < 3) return null;

        int selectMap = Integer.parseInt(args[2]);
        int maxMaps = getMaxMaps(MineSkyEvents.event);

        if (selectMap < 1 || selectMap > maxMaps) {
            player.sendMessage("§8[§c!§8] §cEste evento só tem " + maxMaps + " mapas!");
            return null;
        }

        return "Mapa-" + selectMap;
    }

    private int getMaxMaps(String event) {
        switch (event) {
            case "Mini-Wars":
                return 5;
            case "CopaPVP":
                return 1;
            case "Esconde-esconde":
                return 2;
            case "Ruínas":
                return 1;
            default:
                return 0;
        }
    }

    private boolean setEventLocation(Player player, String eventName, String locationType, int mapNumber, Location loc, FileConfiguration config, File file) {
        String eventPath = eventName + "." + mapNumber;
        String arenaPath = "arena." + eventName + "." + mapNumber;
        String checkPath = "arena." + eventName + "." + mapNumber + ".checkpoint";

        Set<String> validEvents = new HashSet<>(Arrays.asList("spleef", "tijolãowars", "corrida", "corridaboat", "sumo", "tntrun", "tnttag", "parapente", "mini-wars", "copapvp", "esconde-esconde", "ruínas"));

        if (validEvents.contains(eventName)) {
            if (locationType.equalsIgnoreCase("spawn")) {
                return setLocation(player, config, eventPath, loc, file, eventName, mapNumber, "Spawn");
            } else if (locationType.equalsIgnoreCase("arena")) {
                return setLocation(player, config, arenaPath, loc, file, eventName, mapNumber, "Arena");
            } else if (locationType.equalsIgnoreCase("checkpoint")) {
                return setLocation(player, config, checkPath, loc, file, eventName, mapNumber, "Checkpoint");
            }
        }
        return false;
    }

    private boolean setLocation(Player player, FileConfiguration config, String path, Location loc, File file, String eventName, int mapNumber, String locationType) {
        config.set(path, loc);
        config.set(path + ".world", loc.getWorld().getName());
        config.set(path + ".x", loc.getX());
        config.set(path + ".y", loc.getY());
        config.set(path + ".z", loc.getZ());
        config.set(path + ".yaw", loc.getYaw());
        config.set(path + ".pitch", loc.getPitch());

        try {
            config.save(file);
            Locations.loadLocations();
            player.playSound(player, Sound.BLOCK_NOTE_BLOCK_BIT, 1.0f, 1.0f);
            player.sendMessage("§8[§a!§8] §a" + locationType + " de §l" + eventName + " §8(§aMapa " + mapNumber + "§8) §asetado para os eventos com sucesso.");
            return true;
        } catch (IOException e) {
            String falied = "&cNão foi possível salvar o arquivo %file%";
            MineSkyEvents.l.severe(Utils.color(falied).replace("%arquivo%", "locations.yml"));
            return false;
        }

    }

    public void limparEvento(String evento) {
        switch (evento) {
            case "Spleef":
                SpleefEvent.mortos.clear();
                SpleefEvent.restaurar();
                if (SpleefEvent.temporizador != null) SpleefEvent.temporizador.cancel();
                if (SpleefEvent.contagemtemp != null) SpleefEvent.contagemtemp.cancel();
                SpleefEvent.contagemI = false;
                SpleefEvent.contagem = false;
                break;
            case "TijolãoWars":
                TijolãoWarsEvent.mortos.clear();
                if (TijolãoWarsEvent.temporizador != null) TijolãoWarsEvent.temporizador.cancel();
                if (TijolãoWarsEvent.contagemtemp != null) TijolãoWarsEvent.contagemtemp.cancel();
                TijolãoWarsEvent.contagemI = false;
                TijolãoWarsEvent.contagem = false;
                break;
            case "Sumo":
                SumoEvent.mortos.clear();
                if (SumoEvent.temporizador != null) SumoEvent.temporizador.cancel();
                if (SumoEvent.contagemtemp != null) SumoEvent.contagemtemp.cancel();
                SumoEvent.contagemI = false;
                SumoEvent.contagem = false;
                break;
            case "Corrida":
                EventCorridasPlayerManager.clearPlayerManager();
                RegionPlayerManager.clearPlayerManager();
                if (CorridaEvent.temporizador != null) {
                    CorridaEvent.temporizador.cancel();
                }
                if (CorridaEvent.contagemtemp != null) {
                    CorridaEvent.contagemtemp.cancel();
                }
                CorridaEvent.contagemI = false;
                CorridaEvent.contagem = false;
                break;
                case "TNTTag":
                    TNTTagEvent.mortos.clear();
                    TNTTagEvent.tnt.clear();
                    TNTTagEvent.temporizador.cancel();
                    if (TNTTagEvent.contagemI) {
                        TNTTagEvent.contagemtemp.cancel();
                        TNTTagEvent.temp1.cancel();
                        TNTTagEvent.temp2.cancel();
                        TNTTagEvent.contagem = true;
                        TNTTagEvent.contagemI = false;
                    }
                    break;
            default:
                break;
        }
        EventPlayerManager.clearPlayerManager();
        EventPlayerManager.clearPlayerItem();
        EventData.ForceSkip = false;
        EventData.ForceStart = false;
    }
}
