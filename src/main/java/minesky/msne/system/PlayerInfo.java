package minesky.msne.system;

import minesky.msne.MineSkyEvents;
import minesky.msne.addons.MMOItem;
import minesky.msne.commands.EventCommand;
import minesky.msne.config.DataManager;
import minesky.msne.config.Locations;
import minesky.msne.config.Messages;
import minesky.msne.events.*;
import minesky.msne.system.event.EventGUIS;
import minesky.msne.system.event.EventPlayerManager;
import minesky.msne.utils.EventItem;
import minesky.msne.utils.SendMessages;
import minesky.msne.utils.Util;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.*;
import org.bukkit.block.Banner;
import org.bukkit.block.Block;
import org.bukkit.block.BlockState;
import org.bukkit.block.banner.Pattern;
import org.bukkit.block.banner.PatternType;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockExplodeEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.*;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.event.player.*;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BannerMeta;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.potion.*;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import java.io.File;
import java.io.IOException;
import java.util.*;


public class PlayerInfo implements Listener {
    private Map<IronGolem, Player> golemOwners = new HashMap<>();
    private Map<Silverfish, Player> silverOwners = new HashMap<>();
    private final Random random = new Random();
    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        Player p = e.getPlayer();
        EventCommand.RevealPlayer(p);
        File file = DataManager.getFile(p.getName().toLowerCase(), "playerdata");
        FileConfiguration config = DataManager.getConfiguration(file);

        config.set("Event", false);
        config.set("EventSpect", false);
        config.set("Address", Objects.requireNonNull(p.getAddress()).getAddress().getHostAddress());
        try {
            config.save(Util.PlayerDataF(p));
        } catch (IOException ex) {
            Bukkit.getConsoleSender().sendMessage(Messages.Falied_to_save.replace("%file%", p.getName() + " (PlayerData)"));
        }
        if (!p.hasPermission("mineskyevents.bypass.join")) {
            Bukkit.dispatchCommand(e.getPlayer(), "event entrar");
        }
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent e) {
        Player p = e.getPlayer();
        EventCommand.RevealPlayer(p);
        Location location = p.getLocation();
        String loc = Util.serializeLocation(location);
        if(Util.PDVE(p) || Util.PDVES(p)) {
            if (!(MineSkyEvents.event.equals("OFF"))) {
                switch (MineSkyEvents.event) {
                    case "Spleef":
                        if (!SpleefEvent.contagem && SpleefEvent.contagemI) {
                            File file = DataManager.getFile(p.getName().toLowerCase(), "playerdata");
                            FileConfiguration config = DataManager.getConfiguration(file);
                            if (config.getBoolean("Event")) {
                                if (EventPlayerManager.getPlayerCount() == 1) SpleefEvent.finalizar();
                                config.set("Events.Spleef.dead", config.getInt("Events.Spleef.dead") + 1);
                                EventPlayerManager.removePlayer(p);
                                for (Player player2 : Bukkit.getOnlinePlayers()) {
                                    player2.sendMessage("§8[§f☠§8] §c" + p.getName() + " §7Morreu!");
                                }
                            }
                        } else {
                            for (Player player2 : Bukkit.getOnlinePlayers()) {
                                EventPlayerManager.removePlayer(p);
                                int playersone = EventPlayerManager.getPlayerCount();
                                player2.sendMessage("§7" + p.getName() + " §esaiu do evento. (§b" + playersone + "§e/§b4§e)");
                            }
                        }
                        break;
                    case "TijolãoWars":
                        if (!TijolãoWarsEvent.contagem && TijolãoWarsEvent.contagemI) {
                            File file = DataManager.getFile(p.getName().toLowerCase(), "playerdata");
                            FileConfiguration config = DataManager.getConfiguration(file);
                            if (config.getBoolean("Event")) {
                                if (EventPlayerManager.getPlayerCount() == 1) TijolãoWarsEvent.finalizar();
                                config.set("Events.TijolãoWars.dead", config.getInt("Events.TijolãoWars.dead") + 1);
                                EventPlayerManager.removePlayer(p);
                                for (Player player2 : Bukkit.getOnlinePlayers()) {
                                    player2.sendMessage("§8[§f☠§8] §c" + p.getName() + " §7Morreu!");
                                }
                            }
                        } else {
                            for (Player player2 : Bukkit.getOnlinePlayers()) {
                                EventPlayerManager.removePlayer(p);
                                int playersone = EventPlayerManager.getPlayerCount();
                                player2.sendMessage("§7" + p.getName() + " §esaiu do evento. (§b" + playersone + "§e/§b4§e)");
                            }
                        }
                        break;
                    case "Corrida":
                        if (!CorridaEvent.contagem && CorridaEvent.contagemI) {
                            File file = DataManager.getFile(p.getName().toLowerCase(), "playerdata");
                            FileConfiguration config = DataManager.getConfiguration(file);
                            if (config.getBoolean("Event")) {
                                EventPlayerManager.removePlayer(p);
                                for (Player player2 : Bukkit.getOnlinePlayers()) {
                                    player2.sendMessage("§8[§f☠§8]" + p.getName() + " §Saiu do evento!");
                                }
                            } else {
                                for (Player player2 : Bukkit.getOnlinePlayers()) {
                                    EventPlayerManager.removePlayer(p);
                                    int playersone = EventPlayerManager.getPlayerCount();
                                    player2.sendMessage("§7" + p.getName() + " §esaiu do evento. (§b" + playersone + "§e/§b4§e)");
                                }
                            }
                        }
                        break;
                    case "CorridaBoat":
                        if (!CorridaBoatEvent.contagem && CorridaBoatEvent.contagemI) {
                            File file = DataManager.getFile(p.getName().toLowerCase(), "playerdata");
                            FileConfiguration config = DataManager.getConfiguration(file);
                            if (config.getBoolean("Event")) {
                                EventPlayerManager.removePlayer(p);
                                for (Player player2 : Bukkit.getOnlinePlayers()) {
                                    player2.sendMessage("§8[§f☠§8] §c" + p.getName() + " §7Saiu do evento!");
                                }
                            }
                        } else {
                            for (Player player2 : Bukkit.getOnlinePlayers()) {
                                EventPlayerManager.removePlayer(p);
                                int playersone = EventPlayerManager.getPlayerCount();
                                player2.sendMessage("§7" + p.getName() + " §esaiu do evento. (§b" + playersone + "§e/§b4§e)");
                            }
                        }
                        break;
                    case "Sumo":
                        if (!SumoEvent.contagem && SumoEvent.contagemI) {
                            File file = DataManager.getFile(p.getName().toLowerCase(), "playerdata");
                            FileConfiguration config = DataManager.getConfiguration(file);
                            if (config.getBoolean("Event")) {
                                if (EventPlayerManager.getPlayerCount() == 1) SumoEvent.finalizar();
                                config.set("Events.Sumo.dead", config.getInt("Events.Sumo.dead") + 1);
                                EventPlayerManager.removePlayer(p);
                                for (Player player2 : Bukkit.getOnlinePlayers()) {
                                    player2.sendMessage("§8[§f☠§8] §c" + p.getName() + " §7Morreu!");
                                }
                            }
                        } else {
                            for (Player player2 : Bukkit.getOnlinePlayers()) {
                                EventPlayerManager.removePlayer(p);
                                int playersone = EventPlayerManager.getPlayerCount();
                                player2.sendMessage("§7" + p.getName() + " §esaiu do evento. (§b" + playersone + "§e/§b4§e)");
                            }
                        }
                        break;
                    case "TNTRun":
                        if (!TNTRunEvent.contagem && TNTRunEvent.contagemI) {
                            File file = DataManager.getFile(p.getName().toLowerCase(), "playerdata");
                            FileConfiguration config = DataManager.getConfiguration(file);
                            if (config.getBoolean("Event")) {
                                if (EventPlayerManager.getPlayerCount() == 1) TNTRunEvent.finalizar();
                                config.set("Events.TNTRun.dead", config.getInt("Events.TNTRun.dead") + 1);
                                EventPlayerManager.removePlayer(p);
                                for (Player player2 : Bukkit.getOnlinePlayers()) {
                                    player2.sendMessage("§8[§f☠§8] §c" + p.getName() + " §7Morreu!");
                                }
                            }
                        } else {
                            for (Player player2 : Bukkit.getOnlinePlayers()) {
                                EventPlayerManager.removePlayer(p);
                                int playersone = EventPlayerManager.getPlayerCount();
                                player2.sendMessage("§7" + p.getName() + " §esaiu do evento. (§b" + playersone + "§e/§b4§e)");
                            }
                        }
                        break;
                    case "TNTTag":
                        if (!TNTTagEvent.contagem && TNTTagEvent.contagemI && !TNTTagEvent.tnt.contains(p)) {
                            EventPlayerManager.removePlayer(p);
                            TNTTagEvent.mortos.add(p);
                            TNTTagEvent.tnt.remove(p);
                            TNTTagEvent.jogadores.remove(p);
                            p.getInventory().setHelmet(null);
                            File file = DataManager.getFile(p.getName().toLowerCase(), "playerdata");
                            FileConfiguration config = DataManager.getConfiguration(file);
                            if (config.getBoolean("Event")) {
                                if (EventPlayerManager.getPlayerCount() == 1) TNTTagEvent.finalizar();
                                config.set("Events.TNTTag.dead", config.getInt("Events.TNTTag.dead") + 1);
                                for (Player player2 : Bukkit.getOnlinePlayers()) {
                                    player2.sendMessage("§8[§f☠§8] §c" + p.getName() + " §7Saiu do evento!");
                                }
                            }
                        } else if (!TNTTagEvent.contagem && TNTTagEvent.contagemI && TNTTagEvent.tnt.contains(p)) {
                            EventPlayerManager.removePlayer(p);
                            TNTTagEvent.mortos.add(p);
                            TNTTagEvent.jogadores.remove(p);
                            p.getInventory().setHelmet(null);
                            if (EventPlayerManager.getPlayerCount() == 1) TNTTagEvent.finalizar();
                            if (TNTTagEvent.tnt.contains(p)) {
                                TNTTagEvent.tnt.remove(p);
                                if (EventPlayerManager.getPlayerCount() < 8) {
                                    if (TNTTagEvent.tnt.isEmpty()) {
                                        Player p1 = TNTTagEvent.jogadores.get(TNTTagEvent.random.nextInt(TNTTagEvent.jogadores.size()));
                                        TNTTagEvent.tnt.add(p1);
                                        TNTTagEvent.temp1.cancel();
                                        TNTTagEvent.secoundsTNTTAGP1(p1);
                                        p1.getInventory().setHelmet(EventItem.TNTHEAD);
                                        p1.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, Integer.MAX_VALUE, 0, false, false));
                                        p1.sendMessage("§8[§c!§8] §cVocê está com a TNT na cabeça! Você tem apenas §l" + TNTTagEvent.TIME + " §csegundos.");
                                    } else {
                                        Bukkit.getLogger().warning("O TNT-TAG ainda está com 1 player com tnt na cabeça");
                                    }
                                } else {
                                    if (TNTTagEvent.tnt.isEmpty()) {
                                        Player p1 = TNTTagEvent.jogadores.get(TNTTagEvent.random.nextInt(TNTTagEvent.jogadores.size()));
                                        Player p2;
                                        do {
                                            p2 = TNTTagEvent.jogadores.get(TNTTagEvent.random.nextInt(TNTTagEvent.jogadores.size()));
                                        } while (p1.equals(p2));
                                        TNTTagEvent.tnt.add(p1);
                                        TNTTagEvent.tnt.add(p2);
                                        TNTTagEvent.temp1.cancel();
                                        TNTTagEvent.temp2.cancel();
                                        TNTTagEvent.secoundsTNTTAGP1(p1);
                                        TNTTagEvent.secoundsTNTTAGP2(p2);
                                        p1.getInventory().setHelmet(EventItem.TNTHEAD);
                                        p2.getInventory().setHelmet(EventItem.TNTHEAD);
                                        p2.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, Integer.MAX_VALUE, 0, false, false));
                                        p1.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, Integer.MAX_VALUE, 0, false, false));
                                    } else {
                                        Player p1;
                                        do {
                                            p1 = TNTTagEvent.jogadores.get(TNTTagEvent.random.nextInt(TNTTagEvent.jogadores.size()));
                                        } while (p1.equals(TNTTagEvent.tnt.get(0)));
                                        TNTTagEvent.tnt.add(p1);
                                        if (TNTTagEvent.PEGOS.getName().equalsIgnoreCase(p.getName())) {
                                            TNTTagEvent.temp1.cancel();
                                            TNTTagEvent.secoundsTNTTAGP1(p1);
                                        }
                                        if (TNTTagEvent.PEGOS2.getName().equalsIgnoreCase(p.getName())) {
                                            TNTTagEvent.temp2.cancel();
                                            TNTTagEvent.secoundsTNTTAGP2(p1);
                                        }
                                        p1.getInventory().setHelmet(EventItem.TNTHEAD);
                                        p1.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, Integer.MAX_VALUE, 0, false, false));
                                    }
                                }
                            }
                            File file = DataManager.getFile(p.getName().toLowerCase(), "playerdata");
                            FileConfiguration config = DataManager.getConfiguration(file);

                            config.set("Event", false);
                            config.set("Events.TNTTag.dead", config.getInt("Events.TNTTag.dead") + 1);
                            try {
                                config.save(Util.PlayerDataF(p));
                            } catch (IOException er) {
                                er.printStackTrace();
                            }
                            for (Player player : Bukkit.getOnlinePlayers()) {
                                if (player.getName().equals(p.getName())) return;
                                player.sendMessage("§8[§f☠§8] §c" + p.getName() + " §7Saiu do evento.");
                            }
                            return;
                        } else {
                            for (Player player2 : Bukkit.getOnlinePlayers()) {
                                EventPlayerManager.removePlayer(p);
                                int playersone = EventPlayerManager.getPlayerCount();
                                player2.sendMessage("§7" + p.getName() + " §esaiu do evento. (§b" + playersone + "§e/§b4§e)");
                            }
                        }
                        break;
                    case "Parapente":
                        if (!ParapenteEvent.contagem && ParapenteEvent.contagemI) {
                            File file = DataManager.getFile(p.getName().toLowerCase(), "playerdata");
                            FileConfiguration config = DataManager.getConfiguration(file);
                            if (config.getBoolean("Event")) {
                                EventPlayerManager.removePlayer(p);
                                for (Player player2 : Bukkit.getOnlinePlayers()) {
                                    if (Util.PDVE(player2)) {
                                        player2.sendMessage("§8[§f☠§8] §c" + p.getName() + " §7Saiu do evento!");
                                    }
                                }
                            }
                        } else {
                            for (Player player2 : Bukkit.getOnlinePlayers()) {
                                EventPlayerManager.removePlayer(p);
                                int playersone = EventPlayerManager.getPlayerCount();
                                player2.sendMessage("§7" + p.getName() + " §esaiu do evento. (§b" + playersone + "§e/§b4§e)");
                            }
                        }
                        break;
                }
            }
        }
        File file = DataManager.getFile(p.getName().toLowerCase(), "playerdata");
        FileConfiguration config = DataManager.getConfiguration(file);

        config.set("Localizer", loc);
        config.set("Event", false);
        config.set("EventSpect", false);
        try {
            config.save(Util.PlayerDataF(p));
        } catch (IOException ex) {
            Bukkit.getConsoleSender().sendMessage(Messages.Falied_to_save.replace("%file%", p.getName() + " (PlayerData)" + ex.getCause()));
        }
    }

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        Action action = event.getAction();

        if ((action == Action.RIGHT_CLICK_AIR || action == Action.RIGHT_CLICK_BLOCK)) {

            handleRightClick(event);

        }
    }

    private void handleRightClick(PlayerInteractEvent event) {
        Player player = event.getPlayer();

        Block clickedBlock = event.getClickedBlock();

        if (clickedBlock != null && clickedBlock.getType() == Material.LIME_BANNER) {
                Banner meta = (Banner) clickedBlock.getState();
                for (Pattern pattern : meta.getPatterns()) {
                    if (pattern.getPattern().equals(PatternType.CREEPER)) {
                        player.addPotionEffect(new PotionEffect(PotionEffectType.GLOWING, Integer.MAX_VALUE, 1));
                        player.getInventory().setHelmet(EventItem.FlagLIME);
                        clickedBlock.setType(Material.AIR);
                        break;
                    }
            }
        }

        if (clickedBlock != null && clickedBlock.getType() == Material.ORANGE_BANNER) {
            Banner meta = (Banner) clickedBlock.getState();
            for (Pattern pattern : meta.getPatterns()) {
                if (pattern.getPattern().equals(PatternType.GLOBE)) {
                    player.addPotionEffect(new PotionEffect(PotionEffectType.GLOWING, Integer.MAX_VALUE, 1));
                    player.getInventory().setHelmet(EventItem.FlagORANGE);
                    clickedBlock.setType(Material.AIR);
                    break;
                }
            }
        }

        if (EventItem.BedLeave.isSimilar(player.getInventory().getItemInMainHand())) {
            if (Util.PDVES(player)) {
                EventCommand.RevealPlayer(player);
                SendMessages.sendConectionBCMSNE(player);
                File file = DataManager.getFile(player.getName().toLowerCase(), "playerdata");
                FileConfiguration config = DataManager.getConfiguration(file);
                config.set("EventSpect", false);
                try {
                    config.save(file);
                    event.getPlayer().sendMessage("§8[§c!§8] §7Você parou de assistir o evento §c" + MineSkyEvents.event);
                    EventPlayerManager.removePlayer(player);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return;
            }

            if (!Util.PDVE(player)) return;
            EventCommand.RevealPlayer(player);
            SendMessages.sendConectionBCMSNE(player);

            File file = DataManager.getFile(player.getName().toLowerCase(), "playerdata");
            FileConfiguration config = DataManager.getConfiguration(file);

            config.set("Event", false);

            try {
                config.save(file);
                event.getPlayer().sendMessage("§8[§c!§8] §7Você saiu do evento §c" + MineSkyEvents.event);
            } catch (IOException e) {
                e.printStackTrace();
            }

            for (Player p : Bukkit.getOnlinePlayers()) {
                int playersone = 0;
                EventPlayerManager.removePlayer(player);
                playersone = EventPlayerManager.getPlayerCount();
                p.sendMessage("§7" + player.getName() + " §esaiu do evento. (§b" + playersone + "§e/§b4§e)");
            }
        } else if (EventItem.CheckPoint.isSimilar(player.getInventory().getItemInMainHand())) {
            int listC = ParapenteEvent.playerCHECKPOINT.getOrDefault(player, 0);
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
        } else if (EventItem.HeadEvents(player).isSimilar(player.getInventory().getItemInMainHand())) {
            File file = DataManager.getFile(player.getName().toLowerCase(), "playerdata");
            FileConfiguration config = DataManager.getConfiguration(file);
            switch (MineSkyEvents.event) {
                case "Spleef":
                    player.sendMessage("§b§lSpleef §8| §aSua informações:\n§b| §aVitórias§8: §7" + config.getInt("Events.Spleef.win") + "\n§b| §aDerrotas§8: §7" + config.getInt("Events.Spleef.dead"));
                    break;
                case "TijolãoWars":
                    player.sendMessage("§6§lTijolãoWars §8| §aSua informações:\n§6| §aVitórias§8: §7" + config.getInt("Events.TijolãoWars.win") + "\n§6| §aDerrotas§8: §7" + config.getInt("Events.TijolãoWars.dead"));
                    break;
                case "Corrida":
                    player.sendMessage("§e§lCorrida §8| §aSua informações:\n§e| §aVitórias§8: §7" + config.getInt("Events.Corrida.win"));
                    break;
                case "CorridaBoat":
                    player.sendMessage("§9§lCorrida de Barco §8| §aSua informações:\n§9| §aVitórias§8: §7" + config.getInt("Events.CorridaBoat.win"));
                    break;
                case "Sumo":
                    player.sendMessage("§4§lSumo §8| §aSua informações:\n§4| §aVitórias§8: §7" + config.getInt("Events.Sumo.win") + "\n§4| §aDerrotas§8: §7" + config.getInt("Events.Sumo.dead"));
                    break;
                case "TNTRun":
                    player.sendMessage("§c§lTNTRUN §8| §aSua informações:\n§c| §aVitórias§8: §7" + config.getInt("Events.TNTRun.win") + "\n§c| §aDerrotas§8: §7" + config.getInt("Events.TNTRun.dead"));
                    break;
                case "TNTTag":
                    player.sendMessage("§c§lTNT-TAG §8| §aSua informações:\n§c| §aVitórias§8: §7" + config.getInt("Events.TNTTag.win") + "\n§c| §aDerrotas§8: §7" + config.getInt("Events.TNTTag.dead"));
                    break;
                case "Parapente":
                    player.sendMessage("§3§lCorrida de Parapente §8| §aSua informações:\n§3| §aVitórias§8: §7" + config.getInt("Events.Parapente.win"));
                    break;
            }
        } else if (new ItemStack(Material.IRON_GOLEM_SPAWN_EGG).isSimilar(event.getPlayer().getInventory().getItemInMainHand())) {
            if (MineSkyEvents.event.equalsIgnoreCase("CaptureBandeira")) {
                event.setCancelled(true);
            ItemStack item = new ItemStack(Material.IRON_GOLEM_SPAWN_EGG, 1);
            event.getPlayer().getInventory().removeItem(item);
            Location location = player.getLocation();
            IronGolem golem = (IronGolem) location.getWorld().spawnEntity(location, EntityType.IRON_GOLEM);
            golemOwners.put(golem, player);
        }
        } else if (new ItemStack(Material.SILVERFISH_SPAWN_EGG).isSimilar(event.getPlayer().getInventory().getItemInMainHand())) {
            if (MineSkyEvents.event.equalsIgnoreCase("CaptureBandeira")) {
                event.setCancelled(true);
                ItemStack item = new ItemStack(Material.SILVERFISH_SPAWN_EGG, 1);
                event.getPlayer().getInventory().removeItem(item);
                Location location = player.getLocation();
                Silverfish sivel = (Silverfish) location.getWorld().spawnEntity(location, EntityType.SILVERFISH);
                silverOwners.put(sivel, player);
            }
        } else if (EventItem.FlagITEM_TOWER.isSimilar(player.getInventory().getItemInMainHand())) {
            World world = player.getWorld();
            int x = player.getLocation().getBlockX();
            int y = player.getLocation().getBlockY();
            int z = player.getLocation().getBlockZ();
            player.getInventory().removeItem(EventItem.FlagITEM_TOWER);

            for (int i = -1; i <= 1; i++) {
                for (int j = -1; j <= 1; j++) {
                    if (world.getBlockAt(x + i, y, z + j).getType() == Material.AIR) {
                        Material set = getRandomOrangeBlock(player);
                        world.getBlockAt(x + i, y, z + j).setType(set);
                        Location loca = new Location(Bukkit.getWorld(world.getName()), x + i, y, z + j);
                        CaptureBandeiraEvent.blocksplace.put(loca, set);
                    }
                }
            }

            int[][] circleOffsets = {
                    {-2, 0}, {-2, 1}, {-2, -1},
                    {2, 0}, {2, 1}, {2, -1},
                    {0, 2}, {1, 2}, {-1, 2},
                    {0, -2}, {1, -2}, {-1, -2}
            };

            for (int[] offset : circleOffsets) {
                int dx = offset[0];
                int dz = offset[1];
                if (world.getBlockAt(x + dx, y, z + dz).getType() == Material.AIR) {
                    Material set = getRandomOrangeBlock(player);
                    world.getBlockAt(x + dx, y, z + dz).setType(set);
                    Location loca = new Location(Bukkit.getWorld(world.getName()), x + dx, y, z + dz);
                    CaptureBandeiraEvent.blocksplace.put(loca, set);
                }
            }

            int[][] wallOffsets = {
                    {-2, 0}, {-2, 1}, {-2, -1},
                    {2, 0}, {2, 1}, {2, -1},
                    {0, 2}, {1, 2}, {-1, 2},
                    {0, -2}, {1, -2}, {-1, -2}
            };

            int[][] tOffsets = {
                    {0, 4, 1}, {0, 4, -1}, {-1, 4, 0},
                    {1, 4, 0}, {1, 4, 1}, {1, 4, -1},
                    {-1, 4, 1}, {-1, 4, -1},
                    {-2, 4, 0}, {-2, 4, 1}, {-2, 4, -1},
                    {2, 4, 0}, {2, 4, 1}, {2, 4, -1},
                    {0, 4, 2}, {1, 4, 2}, {-1, 4, 2},
                    {0, 4, -2}, {1, 4, -2}, {-1, 4, -2},
                    {0, 4, -3}, {1, 4, -3}, {-1, 4, -3},
                    {0, 4, 3}, {1, 4, 3}, {-1, 4, 3},
                    {-3, 4, 0}, {-3, 4, 1}, {-3, 4, -1},
                    {3, 4, 0}, {3, 4, 1}, {3, 4, -1},
                    {-2, 4, -2}, {-2, 4, 2}, {2, 4, -2}, {2, 4, 2},
                    {0, 5, -3}, {1, 5, -3}, {-1, 5, -3},
                    {0, 5, 3}, {1, 5, 3}, {-1, 5, 3},
                    {-3, 5, 0}, {-3, 5, 1}, {-3, 5, -1},
                    {3, 5, 0}, {3, 5, 1}, {3, 5, -1},
                    {-2, 5, -2}, {-2, 5, 2}, {2, 5, -2}, {2, 5, 2},
                    {3, 6, 1}, {3, 6, -1},
                    {1, 6, 3}, {-1, 6, 3},
                    {1, 6, -3}, {-1, 6, -3},
                    {-3, 6, 1}, {-3, 6, -1},
                    {0, 1, 1}, {0, 2, 1}, {0, 3, 1},
                    {0, 3, -2},
                    {-2, 1, 0}, {2, 1, 0}
            };

            int[][] eOffsets = {
                    {0, 1, 0}, {0, 2, 0}, {0, 3, 0},
                    {0, 4, 0}
            };

            int[][] doorOffsets = {
                    {0, -2}, {0, -1}
            };

            int[][] windowOffsets = {
                    {-2, 0}, {2, 0},
            };

            for (int height = 1; height <= 3; height++) {
                for (int[] offset : wallOffsets) {
                    int dx = offset[0];
                    int dz = offset[1];
                    if (!isOffsetInArray(dx, dz, doorOffsets) && !isOffsetInArray(dx, dz, windowOffsets)) {
                        Material set = getRandomOrangeBlock(player);
                        if (world.getBlockAt(x + dx, y + height, z + dz).getType() == Material.AIR) {
                            world.getBlockAt(x + dx, y + height, z + dz).setType(set);
                            Location loca = new Location(Bukkit.getWorld(world.getName()), x + dx, y + height, z + dz);
                            CaptureBandeiraEvent.blocksplace.put(loca, set);
                        }
                    }
                }
            }


            for (int[] offset : tOffsets) {
                int dx = offset[0];
                int dy = offset[1];
                int dz = offset[2];
                Block windowBlocks = world.getBlockAt(x + dx, y + dy, z + dz);
                if (windowBlocks.getType() == Material.AIR) {
                    Material set = getRandomOrangeBlock(player);
                    windowBlocks.setType(set);
                    Location loca = new Location(Bukkit.getWorld(world.getName()), x + dx, y + dy, z + dz);
                    CaptureBandeiraEvent.blocksplace.put(loca, set);
                }
            }

            for (int[] offset : eOffsets) {
                int dx = offset[0];
                int dy = offset[1];
                int dz = offset[2];
                Block windowBlocks = world.getBlockAt(x + dx, y + dy, z + dz);
                if (windowBlocks.getType() == Material.AIR) {
                    Material set = Material.LADDER;
                    windowBlocks.setType(set);
                    Location loca = new Location(Bukkit.getWorld(world.getName()), x + dx, y + dy, z + dz);
                    CaptureBandeiraEvent.blocksplace.put(loca, set);
                }
            }

        } else if (event.getItem() != null && event.getItem().getType() == Material.FIRE_CHARGE) {
            Fireball fireball = event.getPlayer().launchProjectile(Fireball.class);
            fireball.setVelocity(event.getPlayer().getLocation().getDirection().multiply(2));
            fireball.setFireTicks(0);
        } else {
            if (Util.PDVES(player)) {
                event.setCancelled(true);
            }
        }
    }


    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        if (event.getInventory().equals(EventGUIS.getInventorySHOP_INICIAL()) || event.getInventory().equals(EventGUIS.getInventorySHOP_BLOCOS()) || event.getInventory().equals(EventGUIS.getInventorySHOP_COMBATE()) || event.getInventory().equals(EventGUIS.getInventorySHOP_POÇOES()) || event.getInventory().equals(EventGUIS.getInventorySHOP_FERRAMENTAS()) || event.getInventory().equals(EventGUIS.getInventorySHOP_ATAQUE()) || event.getInventory().equals(EventGUIS.getInventorySHOP_OUTROS())) {
            event.setCancelled(true);
            final ItemStack clickedItem = event.getCurrentItem();

            if (clickedItem == null || clickedItem.getType().isAir()) return;
            Player player = (Player) event.getWhoClicked();
            Material material = event.getCurrentItem().getType();
            Material material1 = null;
            int pagINT = 0;
            int quantidade = 0;
            if (event.getRawSlot() <= 35) {
                switch (material) {
                    case WHITE_WOOL: {
                        material1 = Material.IRON_INGOT;
                        pagINT = 4;
                        quantidade = 16;
                        break;
                    }
                    case TERRACOTTA: {
                        material1 = Material.IRON_INGOT;
                        pagINT = 18;
                        quantidade = 8;
                        break;
                    }
                    case END_STONE: {
                        material1 = Material.IRON_INGOT;
                        pagINT = 24;
                        quantidade = 12;
                        break;
                    }
                    case GLASS: {
                        material1 = Material.IRON_INGOT;
                        pagINT = 15;
                        quantidade = 8;
                        break;
                    }
                    case OAK_PLANKS: {
                        material1 = Material.GOLD_INGOT;
                        pagINT = 4;
                        quantidade = 16;
                        break;
                    }
                    case LADDER: {
                        material1 = Material.IRON_INGOT;
                        pagINT = 4;
                        quantidade = 8;
                        break;
                    }
                    case OBSIDIAN: {
                        material1 = Material.EMERALD;
                        pagINT = 4;
                        quantidade = 4;
                        break;
                    }
                    case STONE_SWORD: {
                        material1 = Material.IRON_INGOT;
                        pagINT = 10;
                        quantidade = 1;
                        EventGUIS.buyItem(event, material1, pagINT, material, quantidade, true);
                        return;
                    }
                    case IRON_SWORD: {
                        material1 = Material.GOLD_INGOT;
                        pagINT = 7;
                        quantidade = 1;
                        EventGUIS.buyItem(event, material1, pagINT, material, quantidade, true);
                        return;
                    }
                    case DIAMOND_SWORD: {
                        material1 = Material.EMERALD;
                        pagINT = 4;
                        quantidade = 1;
                        EventGUIS.buyItem(event, material1, pagINT, material, quantidade, true);
                        return;
                    }
                    case CHAINMAIL_BOOTS: {
                        material1 = Material.IRON_INGOT;
                        pagINT = 24;
                        quantidade = 1;
                        EventGUIS.buyItem(event, material1, pagINT, material, quantidade, true);
                        return;
                    }
                    case IRON_BOOTS: {
                        material1 = Material.GOLD_INGOT;
                        pagINT = 12;
                        quantidade = 1;
                        EventGUIS.buyItem(event, material1, pagINT, material, quantidade, true);
                        return;
                    }
                    case DIAMOND_BOOTS: {
                        material1 = Material.EMERALD;
                        pagINT = 6;
                        quantidade = 1;
                        EventGUIS.buyItem(event, material1, pagINT, material, quantidade, true);
                        return;
                    }
                    case BOW: {
                        if (!event.getCurrentItem().hasItemMeta() || !event.getCurrentItem().getItemMeta().hasEnchants()) {
                            material1 = Material.GOLD_INGOT;
                            pagINT = 12;
                            quantidade = 1;
                            break;
                        }
                        if (event.getCurrentItem().getItemMeta().hasEnchant(Enchantment.ARROW_DAMAGE) && !event.getCurrentItem().getItemMeta().hasEnchant(Enchantment.ARROW_KNOCKBACK)) {
                            material1 = Material.GOLD_INGOT;
                            pagINT = 24;
                            quantidade = 1;
                            break;
                        }
                        if (event.getCurrentItem().getItemMeta().hasEnchant(Enchantment.ARROW_DAMAGE) && event.getCurrentItem().getItemMeta().hasEnchant(Enchantment.ARROW_KNOCKBACK)) {
                            material1 = Material.EMERALD;
                            pagINT = 4;
                            quantidade = 1;
                            break;
                        }
                        break;
                    }
                    case ARROW: {
                        material1 = Material.GOLD_INGOT;
                        pagINT = 2;
                        quantidade = 8;
                        break;
                    }
                    case POTION: {
                        PotionMeta meta = (PotionMeta) event.getCurrentItem().getItemMeta();
                        if (meta != null) {
                            if (meta.getBasePotionData().getType() == PotionType.SPEED || meta.getBasePotionData().getType() == PotionType.JUMP) {
                                material1 = Material.EMERALD;
                                pagINT = 1;
                                quantidade = 1;
                            } else if (meta.getBasePotionData().getType() == PotionType.INVISIBILITY) {
                                material1 = Material.EMERALD;
                                pagINT = 2;
                                quantidade = 1;
                            } else if (meta.getBasePotionData().getType() == PotionType.FIRE_RESISTANCE) {
                                material1 = Material.EMERALD;
                                pagINT = 4;
                                quantidade = 1;
                            }
                        }
                        break;
                    }
                    case MILK_BUCKET: {
                        material1 = Material.IRON_INGOT;
                        pagINT = 6;
                        quantidade = 1;
                        break;
                    }
                    case WOODEN_PICKAXE: {
                        material1 = Material.IRON_INGOT;
                        pagINT = 10;
                        quantidade = 1;
                        EventGUIS.buyItem(event, material1, pagINT, material, quantidade, true);
                        return;
                    }
                    case IRON_PICKAXE: {
                        material1 = Material.IRON_INGOT;
                        pagINT = 20;
                        quantidade = 1;
                        EventGUIS.buyItem(event, material1, pagINT, material, quantidade, true);
                        return;
                    }
                    case GOLDEN_PICKAXE: {
                        material1 = Material.GOLD_INGOT;
                        pagINT = 8;
                        quantidade = 1;
                        EventGUIS.buyItem(event, material1, pagINT, material, quantidade, true);
                        return;
                    }
                    case DIAMOND_PICKAXE: {
                        material1 = Material.GOLD_INGOT;
                        pagINT = 16;
                        quantidade = 1;
                        EventGUIS.buyItem(event, material1, pagINT, material, quantidade, true);
                        return;
                    }
                    case WOODEN_AXE: {
                        material1 = Material.IRON_INGOT;
                        pagINT = 10;
                        quantidade = 1;
                        EventGUIS.buyItem(event, material1, pagINT, material, quantidade, true);
                        return;
                    }
                    case IRON_AXE: {
                        material1 = Material.IRON_INGOT;
                        pagINT = 20;
                        quantidade = 1;
                        EventGUIS.buyItem(event, material1, pagINT, material, quantidade, true);
                        return;
                    }
                    case GOLDEN_AXE: {
                        material1 = Material.GOLD_INGOT;
                        pagINT = 8;
                        quantidade = 1;
                        EventGUIS.buyItem(event, material1, pagINT, material, quantidade, true);
                        return;
                    }
                    case DIAMOND_AXE: {
                        material1 = Material.GOLD_INGOT;
                        pagINT = 16;
                        quantidade = 1;
                        EventGUIS.buyItem(event, material1, pagINT, material, quantidade, true);
                        return;
                    }
                    case STICK: {
                        material1 = Material.GOLD_INGOT;
                        pagINT = 5;
                        quantidade = 1;
                        break;
                    }
                    case FLINT_AND_STEEL: {
                        material1 = Material.GOLD_INGOT;
                        pagINT = 5;
                        quantidade = 1;
                        break;
                    }
                    case SHEARS: {
                        material1 = Material.IRON_INGOT;
                        pagINT = 20;
                        quantidade = 1;
                        break;
                    }
                    case SHIELD: {
                        material1 = Material.GOLD_INGOT;
                        pagINT = 18;
                        quantidade = 1;
                        break;
                    }
                    case FIRE_CHARGE: {
                        material1 = Material.IRON_INGOT;
                        pagINT = 40;
                        quantidade = 1;
                        break;
                    }
                    case TNT: {
                        material1 = Material.GOLD_INGOT;
                        pagINT = 4;
                        quantidade = 1;
                        break;
                    }
                    case IRON_GOLEM_SPAWN_EGG: {
                        material1 = Material.IRON_INGOT;
                        pagINT = 120;
                        quantidade = 1;
                        break;
                    }
                    case SILVERFISH_SPAWN_EGG: {
                        material1 = Material.IRON_INGOT;
                        pagINT = 30;
                        quantidade = 1;
                        break;
                    }
                    case WATER_BUCKET: {
                        material1 = Material.GOLD_INGOT;
                        pagINT = 3;
                        quantidade = 1;
                        break;
                    }
                    case SPONGE: {
                        material1 = Material.GOLD_INGOT;
                        pagINT = 3;
                        quantidade = 1;
                        break;
                    }
                    case ENDER_PEARL: {
                        material1 = Material.EMERALD;
                        pagINT = 4;
                        quantidade = 1;
                        break;
                    }
                    case ENDER_CHEST: {
                        material1 = Material.EMERALD;
                        pagINT = 2;
                        quantidade = 1;
                        break;
                    }
                    case BLUE_WOOL: {
                        material1 = Material.IRON_INGOT;
                        pagINT = 24;
                        quantidade = 1;
                        break;
                    }
                    case GOLDEN_APPLE: {
                        material1 = Material.GOLD_INGOT;
                        pagINT = 3;
                        quantidade = 1;
                        break;
                    }
                    case ENCHANTED_GOLDEN_APPLE: {
                        material1 = Material.EMERALD;
                        pagINT = 3;
                        quantidade = 1;
                        break;
                    }
                    default: {
                        player.playSound(player, Sound.BLOCK_NOTE_BLOCK_BASS, 1.0f , 1.0f);
                        player.sendMessage("§8[§c!§8] §cOcorreu um erro para localizar seu item.");
                        return;
                    }
                }
                EventGUIS.buyItem(event, material1, pagINT, material, quantidade);
            } else {
                if (event.getRawSlot() >  53) return;
                switch (event.getRawSlot()) {
                    case 46: {
                        Bukkit.dispatchCommand(player, "msne cb_shopI");
                        player.playSound(player, Sound.BLOCK_NOTE_BLOCK_FLUTE, 1.0f , 1.0f);
                        break;
                    }
                    case 47: {
                        Bukkit.dispatchCommand(player, "msne cb_shopB");
                        player.playSound(player, Sound.BLOCK_NOTE_BLOCK_FLUTE, 1.0f , 1.0f);
                        break;
                    }
                    case 48: {
                        Bukkit.dispatchCommand(player, "msne cb_shopC");
                        player.playSound(player, Sound.BLOCK_NOTE_BLOCK_FLUTE, 1.0f , 1.0f);
                        break;
                    }
                    case 49: {
                        Bukkit.dispatchCommand(player, "msne cb_shopP");
                        player.playSound(player, Sound.BLOCK_NOTE_BLOCK_FLUTE, 1.0f , 1.0f);
                        break;
                    }
                    case 50: {
                        Bukkit.dispatchCommand(player, "msne cb_shopF");
                        player.playSound(player, Sound.BLOCK_NOTE_BLOCK_FLUTE, 1.0f , 1.0f);
                        break;
                    }
                    case 51: {
                        Bukkit.dispatchCommand(player, "msne cb_shopA");
                        player.playSound(player, Sound.BLOCK_NOTE_BLOCK_FLUTE, 1.0f , 1.0f);
                        break;
                    }
                    case 52: {
                        Bukkit.dispatchCommand(player, "msne cb_shopO");
                        player.playSound(player, Sound.BLOCK_NOTE_BLOCK_FLUTE, 1.0f , 1.0f);
                        break;
                    }
                    default: {
                        player.sendMessage("§8[§c!§8] §cOcorreu um erro para localizar seu item.");
                        player.playSound(player, Sound.BLOCK_NOTE_BLOCK_BASS, 1.0f , 1.0f);
                        return;
                    }
                }
            }
        }

        if (event.getClickedInventory() != null && event.getClickedInventory().getHolder() == event.getWhoClicked()) {

            if (event.getCurrentItem() != null && event.getCurrentItem().isSimilar(EventItem.BedLeave) || Objects.requireNonNull(event.getCurrentItem()).isSimilar(EventItem.SpleefITEM) || event.getCurrentItem().getType() == Material.PLAYER_HEAD || event.getCurrentItem().isSimilar(EventItem.BarcoITEM) || event.getCurrentItem().isSimilar(EventItem.SumoITEM) || event.getCurrentItem().isSimilar(EventItem.TNTHEAD) || event.getCurrentItem().isSimilar(EventItem.CheckPoint) || event.getCurrentItem().isSimilar(MMOItem.getParaglider())) {
                if (Util.PDVES((Player) event.getWhoClicked())) {
                    event.setCancelled(true);
                    return;
                }
                if (!Util.PDVE((Player) event.getWhoClicked())) return;
                event.setCancelled(true);
                return;
            }
        }
    }

    @EventHandler
    public void onInventoryClick(InventoryDragEvent e) {
        if (e.getInventory().equals(EventGUIS.getInventorySHOP_INICIAL()) || e.getInventory().equals(EventGUIS.getInventorySHOP_BLOCOS()) || e.getInventory().equals(EventGUIS.getInventorySHOP_COMBATE()) || e.getInventory().equals(EventGUIS.getInventorySHOP_POÇOES()) || e.getInventory().equals(EventGUIS.getInventorySHOP_FERRAMENTAS()) || e.getInventory().equals(EventGUIS.getInventorySHOP_ATAQUE()) || e.getInventory().equals(EventGUIS.getInventorySHOP_OUTROS())) {
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void onPlayerConsume(PlayerItemConsumeEvent event) {
        if (MineSkyEvents.event.equalsIgnoreCase("CaptureBandeira")) {
            if (event.getItem().getType() == Material.POTION) {
                event.setCancelled(true);
                event.getPlayer().getInventory().setItem(event.getHand(), new ItemStack(Material.AIR));
                PotionMeta potionMeta = (PotionMeta) event.getItem().getItemMeta();
                if (potionMeta.getBasePotionData().getType() == PotionType.INVISIBILITY) {
                    event.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, 30 * 20, 0, false, false));
                } else if (potionMeta.getBasePotionData().getType() == PotionType.SPEED) {
                    event.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 45 * 20, 1, false, false));
                } else if (potionMeta.getBasePotionData().getType() == PotionType.JUMP) {
                    event.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.JUMP, 45 * 20, 3, false, false));
                } else if (potionMeta.getBasePotionData().getType() == PotionType.FIRE_RESISTANCE) {
                    event.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, 30 * 20, 0, false, false));
                    event.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 30 * 20, 1, false, false));
                    event.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.JUMP, 30 * 20, 3, false, false));
                }
            }
        }
    }

    @EventHandler
    public void onPlayerDropItem(PlayerDropItemEvent event) {
        ItemStack droppedItem = event.getItemDrop().getItemStack();
        if (droppedItem.isSimilar(EventItem.SpleefITEM) || droppedItem.isSimilar(EventItem.BedLeave) || droppedItem.getType() == Material.BRICK || droppedItem.getType() == Material.PLAYER_HEAD || droppedItem.isSimilar(EventItem.BarcoITEM) || droppedItem.isSimilar(EventItem.SumoITEM) || droppedItem.isSimilar(EventItem.TNTHEAD) || droppedItem.isSimilar(EventItem.CheckPoint) || droppedItem.isSimilar(MMOItem.getParaglider())) {
            if (Util.PDVES(event.getPlayer())) {
                event.setCancelled(true);
                event.getPlayer().sendMessage("§8[§c!§8] §cVocê não pode dropar esse item.");
                return;
            }
            if (!Util.PDVE(event.getPlayer())) return;
            event.setCancelled(true);
            event.getPlayer().sendMessage("§8[§c!§8] §cVocê não pode dropar esse item.");
        }
    }

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event) {
        Player p = event.getEntity();
        p.spigot().respawn();
        if (Util.PDVES(p)) {
            File file = DataManager.getFile(p.getName().toLowerCase(), "playerdata");
            FileConfiguration config = DataManager.getConfiguration(file);

            config.set("Event", false);
            config.set("EventSpect", false);
            try {
                config.save(Util.PlayerDataF(p));
                EventCommand.RevealPlayer(p);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return;
        }
        if (!Util.PDVE(p)) return;
        switch (MineSkyEvents.event) {
            case "Spleef": {
                EventPlayerManager.removePlayer(p);
                SpleefEvent.mortos.add(p);
                if (EventPlayerManager.getPlayerCount() == 1) SpleefEvent.finalizar();
                p.sendMessage("§8[§f☠§8] §cVocê §7morreu.");
                event.setDeathMessage(null);
                File file = DataManager.getFile(p.getName().toLowerCase(), "playerdata");
                FileConfiguration config = DataManager.getConfiguration(file);

                config.set("Event", false);
                config.set("Events.Spleef.dead", config.getInt("Events.Spleef.dead") + 1);
                try {
                    config.save(Util.PlayerDataF(p));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Bukkit.dispatchCommand(p, "event entrar");
                for (Player player : Bukkit.getOnlinePlayers()) {
                    if (player.getName().equals(p.getName())) return;
                    player.sendMessage("§8[§f☠§8] §c" + p.getName() + " §7morreu.");
                }
                return;
            }
            case "TijolãoWars": {
                EventPlayerManager.removePlayer(p);
                TijolãoWarsEvent.mortos.add(p);
                if (EventPlayerManager.getPlayerCount() == 1) TijolãoWarsEvent.finalizar();
                p.sendMessage("§8[§f☠§8] §cVocê §7morreu.");
                event.setDeathMessage(null);
                File file = DataManager.getFile(p.getName().toLowerCase(), "playerdata");
                FileConfiguration config = DataManager.getConfiguration(file);

                config.set("Event", false);
                config.set("Events.TijolãoWars.dead", config.getInt("Events.TijolãoWars.dead") + 1);
                try {
                    config.save(Util.PlayerDataF(p));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Bukkit.dispatchCommand(p, "event entrar");
                for (Player player : Bukkit.getOnlinePlayers()) {
                    if (player.getName().equals(p.getName())) return;
                    player.sendMessage("§8[§f☠§8] §c" + p.getName() + " §7morreu.");
                }
                return;
            }
            case "Corrida": {
                EventPlayerManager.removePlayer(p);
                p.sendMessage("§8[§f☠§8] §cVocê §7morreu.");
                event.setDeathMessage(null);
                File file = DataManager.getFile(p.getName().toLowerCase(), "playerdata");
                FileConfiguration config = DataManager.getConfiguration(file);

                config.set("Event", false);
                try {
                    config.save(Util.PlayerDataF(p));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Bukkit.dispatchCommand(p, "event entrar");
                for (Player player : Bukkit.getOnlinePlayers()) {
                    if (player.getName().equals(p.getName())) return;
                    player.sendMessage("§8[§f☠§8] §c" + p.getName() + " §7morreu.");
                }
                return;
            }
            case "Sumo": {
                EventPlayerManager.removePlayer(p);
                SumoEvent.mortos.add(p);
                if (EventPlayerManager.getPlayerCount() == 1) SumoEvent.finalizar();
                p.sendMessage("§8[§f☠§8] §cVocê §7morreu.");
                event.setDeathMessage(null);
                File file = DataManager.getFile(p.getName().toLowerCase(), "playerdata");
                FileConfiguration config = DataManager.getConfiguration(file);

                config.set("Event", false);
                config.set("Events.Sumo.dead", config.getInt("Events.Sumo.dead") + 1);
                try {
                    config.save(Util.PlayerDataF(p));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Bukkit.dispatchCommand(p, "event entrar");
                for (Player player : Bukkit.getOnlinePlayers()) {
                    if (player.getName().equals(p.getName())) return;
                    player.sendMessage("§8[§f☠§8] §c" + p.getName() + " §7morreu.");
                }
                return;
            }
            case "TNTRun": {
                EventPlayerManager.removePlayer(p);
                TNTRunEvent.mortos.add(p);
                if (EventPlayerManager.getPlayerCount() == 1) TNTRunEvent.finalizar();
                p.sendMessage("§8[§f☠§8] §cVocê §7morreu.");
                event.setDeathMessage(null);
                File file = DataManager.getFile(p.getName().toLowerCase(), "playerdata");
                FileConfiguration config = DataManager.getConfiguration(file);

                config.set("Event", false);
                config.set("Events.TNTRun.dead", config.getInt("Events.TNTRun.dead") + 1);
                try {
                    config.save(Util.PlayerDataF(p));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Bukkit.dispatchCommand(p, "event entrar");
                for (Player player : Bukkit.getOnlinePlayers()) {
                    if (player.getName().equals(p.getName())) return;
                    player.sendMessage("§8[§f☠§8] §c" + p.getName() + " §7morreu.");
                }
                return;
            }
            case "TNTTag": {
                EventPlayerManager.removePlayer(p);
                TNTTagEvent.mortos.add(p);
                TNTTagEvent.jogadores.remove(p);
                p.getInventory().setHelmet(null);
                if (EventPlayerManager.getPlayerCount() == 1) TNTTagEvent.finalizar();
                if (TNTTagEvent.tnt.contains(p)) {
                    TNTTagEvent.tnt.remove(p);
                    if (EventPlayerManager.getPlayerCount() < 8) {
                        if (TNTTagEvent.tnt.isEmpty()) {
                            Player p1 = TNTTagEvent.jogadores.get(TNTTagEvent.random.nextInt(TNTTagEvent.jogadores.size()));
                            TNTTagEvent.tnt.add(p1);
                            TNTTagEvent.temp1.cancel();
                            TNTTagEvent.secoundsTNTTAGP1(p1);
                            p1.getInventory().setHelmet(EventItem.TNTHEAD);
                            p1.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, Integer.MAX_VALUE, 0, false, false));
                            p1.sendMessage("§8[§c!§8] §cVocê está com a TNT na cabeça! Você tem apenas §l" + TNTTagEvent.TIME + " §csegundos.");
                        } else {
                            Bukkit.getLogger().warning("O TNT-TAG ainda está com 1 player com tnt na cabeça");
                        }
                    } else {
                        if (TNTTagEvent.tnt.isEmpty()) {
                            Player p1 = TNTTagEvent.jogadores.get(TNTTagEvent.random.nextInt(TNTTagEvent.jogadores.size()));
                            Player p2;
                            do {
                                p2 = TNTTagEvent.jogadores.get(TNTTagEvent.random.nextInt(TNTTagEvent.jogadores.size()));
                            } while (p1.equals(p2));
                            TNTTagEvent.tnt.add(p1);
                            TNTTagEvent.tnt.add(p2);
                            TNTTagEvent.temp1.cancel();
                            TNTTagEvent.temp2.cancel();
                            TNTTagEvent.secoundsTNTTAGP1(p1);
                            TNTTagEvent.secoundsTNTTAGP2(p2);
                            p1.getInventory().setHelmet(EventItem.TNTHEAD);
                            p2.getInventory().setHelmet(EventItem.TNTHEAD);
                            p2.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, Integer.MAX_VALUE, 0, false, false));
                            p1.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, Integer.MAX_VALUE, 0, false, false));
                        } else {
                            Player p1;
                            do {
                                p1 = TNTTagEvent.jogadores.get(TNTTagEvent.random.nextInt(TNTTagEvent.jogadores.size()));
                            } while (p1.equals(TNTTagEvent.tnt.get(0)));
                            TNTTagEvent.tnt.add(p1);
                            if (TNTTagEvent.PEGOS.getName().equalsIgnoreCase(p.getName())) {
                                TNTTagEvent.temp1.cancel();
                                TNTTagEvent.secoundsTNTTAGP1(p1);
                            }
                            if (TNTTagEvent.PEGOS2.getName().equalsIgnoreCase(p.getName())) {
                                TNTTagEvent.temp2.cancel();
                                TNTTagEvent.secoundsTNTTAGP2(p1);
                            }
                            p1.getInventory().setHelmet(EventItem.TNTHEAD);
                            p1.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, Integer.MAX_VALUE, 0, false, false));
                        }
                    }
                    p.sendMessage("§8[§f☠§8] §cVocê §7foi explodido.");
                }
                event.setDeathMessage(null);
                File file = DataManager.getFile(p.getName().toLowerCase(), "playerdata");
                FileConfiguration config = DataManager.getConfiguration(file);

                config.set("Event", false);
                config.set("Events.TNTTag.dead", config.getInt("Events.TNTTag.dead") + 1);
                try {
                    config.save(Util.PlayerDataF(p));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Bukkit.dispatchCommand(p, "event entrar");
                for (Player player : Bukkit.getOnlinePlayers()) {
                    if (player.getName().equals(p.getName())) return;
                    player.sendMessage("§8[§f☠§8] §c" + p.getName() + " §7Foi explodido.");
                }
            }
        }
    }

    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        Player player = event.getPlayer();
        if (Util.PDVES(player)) {
            event.setCancelled(true);
            return;
        }
        if (MineSkyEvents.event.equalsIgnoreCase("CaptureBandeira")) {
            if (!CaptureBandeiraEvent.blocksplace.containsKey(event.getBlock().getLocation())) {
                player.sendMessage("§8[§c!§8] §cVocê não pode fazer isso.");
                event.setCancelled(true);
                return;
            }
            if (event.getBlock().getType().equals(Material.LIME_BANNER)) {
                player.sendMessage("§8[§c!§8] §cVocê deve pegar a bandeira apertando botão direito.");
                event.setCancelled(true);
                return;
            } else if (event.getBlock().getType().equals(Material.ORANGE_BANNER)) {
                player.sendMessage("§8[§c!§8] §cVocê deve pegar a bandeira apertando botão direito.");
                event.setCancelled(true);
                return;
            }
        }
        if (MineSkyEvents.event.equals("Spleef")) {
            if (SpleefEvent.contagem) {
                player.sendMessage("§8[§c!§8] §cEvento não iniciado.");
                event.setCancelled(true);
                return;
            }
            Block block = event.getBlock();
            Location location = block.getLocation();
            Material material = block.getType();

            SpleefEvent.blocksbreak.put(location, material);
        }
    }

    @EventHandler
    public void onBlockPlace(BlockPlaceEvent event) {
        Player player = event.getPlayer();
        if (Util.PDVES(player)) {
            event.setCancelled(true);
        }

        if (MineSkyEvents.event.equalsIgnoreCase("CaptureBandeira")) {
            if (event.getBlock().getType().equals(Material.ENDER_CHEST)) {
                Location loc = event.getBlock().getLocation();
                new BukkitRunnable() {
                    int tempoRestante = 5;

                    @Override
                    public void run() {
                        if (!(tempoRestante == 0)) {
                            Location location = loc.clone().add(0, 1, 0);
                            for (Entity entity : location.getWorld().getNearbyEntities(location, 1.0, 1.0, 1.0)) {
                                if (entity.getType().equals(EntityType.ARMOR_STAND)) {
                                    entity.remove();
                                }
                            }
                            ArmorStand armorStand = (ArmorStand) location.getWorld().spawnEntity(location, EntityType.ARMOR_STAND);
                            armorStand.setVisible(false);
                            armorStand.setCustomName(ChatColor.of("#2493b4") + "⭐ Baú do Fim (" + tempoRestante + "s)");
                            armorStand.setCustomNameVisible(true);
                            armorStand.setGravity(false);
                            armorStand.setMarker(true);
                        }
                        if (tempoRestante == 0) {
                            Location location = loc.clone().add(0, 1, 0);
                            for (Entity entity : location.getWorld().getNearbyEntities(location, 1.0, 1.0, 1.0)) {
                                if (entity.getType().equals(EntityType.ARMOR_STAND)) {
                                    entity.remove();
                                }
                            }
                            loc.getBlock().setType(Material.AIR);
                            this.cancel();
                        }
                        tempoRestante--;
                    }
                }.runTaskTimer(MineSkyEvents.get(), 0, 20);
                return;
            }
            if (event.getBlock().getType().equals(Material.TNT)) {
                Block block = event.getBlock();
                block.setType(Material.AIR);
                TNTPrimed tnt = (TNTPrimed) block.getWorld().spawnEntity(block.getLocation(), EntityType.PRIMED_TNT);

                tnt.setFuseTicks(80);

                tnt.setVelocity(new Vector(0, 0.2, 0));
            }

            Block block = event.getBlock();
            Location location = block.getLocation();
            Material material = block.getType();

            CaptureBandeiraEvent.blocksplace.put(location, material);
        }
    }

    @EventHandler
    public void onEntityDamage(EntityDamageEvent event) {
        if (event.getEntity() instanceof Player) {
            Player player = (Player) event.getEntity();
            if (Util.PDVES(player)) {
                event.setCancelled(true);
                return;
            }
            if (MineSkyEvents.event.equals("TNTTag")) {
                if (Util.PDVE(player)) {
                    event.setCancelled(true);
                }
            }
        }
    }

    @EventHandler
    public void onEntityDamageByEntity(EntityDamageByEntityEvent event) {
        if (MineSkyEvents.event.equalsIgnoreCase("CaptureBandeira")) {
            if (event.getDamager().getType() == EntityType.IRON_GOLEM) {
                event.setDamage(1);
                return;
            }
            if (event.getDamager().getType() == EntityType.SILVERFISH) {
                event.setDamage(0.5);
                return;
            }
        }
        if (event.getDamager() instanceof Player) {
            Player damager = (Player) event.getDamager();
            if (Util.PDVES(damager)) {
                event.setCancelled(true);
                return;
            }
            if (MineSkyEvents.event.equals("TNTTag")) {
                if (TNTTagEvent.contagem) {
                    damager.sendMessage("§8[§c!§8] §cEvento não iniciado.");
                    event.setCancelled(true);
                    return;
                }
                if (event.getEntity() instanceof Player) {
                    Player entity = (Player) event.getEntity();
                    if (Util.PDVE(damager)) {
                        if (TNTTagEvent.tnt.contains(damager)) {
                            if (TNTTagEvent.tnt.contains(entity)) {
                                damager.sendMessage("§8[§c!§8] §cVocê não pode pegar outra pessoa que está com a TNT na cabeça.");
                                return;
                            }
                            TNTTagEvent.tnt.remove(damager);
                            TNTTagEvent.tnt.add(entity);
                            if (TNTTagEvent.PEGOS.getName().equalsIgnoreCase(damager.getName())) {
                                TNTTagEvent.temp1.cancel();
                                TNTTagEvent.secoundsTNTTAGP1(entity);
                            }
                            if (TNTTagEvent.PEGOS2.getName().equalsIgnoreCase(damager.getName())) {
                                TNTTagEvent.temp2.cancel();
                                TNTTagEvent.secoundsTNTTAGP2(entity);
                            }
                            entity.sendMessage("§8[§c!§8] §cVocê foi pego e tem §l" + TNTTagEvent.TIME + " §csegundos para pegar alguém.");
                            damager.getInventory().setHelmet(null);
                            entity.getInventory().setHelmet(EventItem.TNTHEAD);
                            entity.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, Integer.MAX_VALUE, 0, false, false));
                            damager.removePotionEffect(PotionEffectType.SPEED);
                            damager.sendMessage("§8[§a!§8] §aVocê pegou o jogador §l" + entity.getName());
                        }
                    }
                }
            }
            if (MineSkyEvents.event.equals("Sumo")) {
                if (SumoEvent.contagem) {
                    damager.sendMessage("§8[§c!§8] §cEvento não iniciado.");
                    event.setCancelled(true);
                }
            }
        }
    }

    @EventHandler
    public void onEntityDamageBlock(EntityDamageByBlockEvent event) {
        if (event.getEntity() instanceof Player) {
            Player player = (Player) event.getEntity();
            if (Util.PDVES(player)) {
                event.setCancelled(true);
            }
        }
    }

    @EventHandler
    public void onMobSpawn(CreatureSpawnEvent event) {
        if (MineSkyEvents.event.equalsIgnoreCase("CaptureBandeira")) {
            if (event.getEntityType() == EntityType.IRON_GOLEM) {
                Bukkit.getScheduler().runTaskLater(MineSkyEvents.get(), () -> {
                    IronGolem golem = (IronGolem) event.getEntity();
                    Player owner = golemOwners.get(golem);
                    golem.setCustomName("§cGolem de Ferro (5:00)");
                    golem.setCustomNameVisible(true);
                    golem.setLastDamage(2);
                    golem.setHealth(50);
                    if (owner != null) {
                        new BukkitRunnable() {
                            int tempoRestante = 300;

                            @Override
                            public void run() {
                                if (!(tempoRestante == 0)) {
                                    for (Player player : golem.getWorld().getPlayers()) {
                                        String timeOWNER = CaptureBandeiraEvent.time.get(owner);
                                        if (!CaptureBandeiraEvent.time.get(player).equals(timeOWNER)) {
                                            golem.setTarget(player);
                                            golem.setCustomName("§cGolem de Ferro (" + tempoRestante + "s)");
                                        }
                                    }
                                }
                                if (tempoRestante == 0) {
                                    golem.setHealth(0);
                                    this.cancel();
                                }
                                tempoRestante--;
                            }
                        }.runTaskTimer(MineSkyEvents.get(), 0, 20);
                    }
                }, 20L);
            }
            if (event.getEntityType() == EntityType.SILVERFISH) {
                Bukkit.getScheduler().runTaskLater(MineSkyEvents.get(), () -> {
                    Silverfish silver = (Silverfish) event.getEntity();
                    Player owner = silverOwners.get(silver);
                    silver.setCustomName("§7Traça (0:20)");
                    silver.setCustomNameVisible(true);
                    if (owner != null) {
                        new BukkitRunnable() {
                            int tempoRestante = 20;

                            @Override
                            public void run() {
                                if (!(tempoRestante == 0)) {
                                    for (Player player : silver.getWorld().getPlayers()) {
                                        String timeOWNER = CaptureBandeiraEvent.time.get(owner);
                                        if (!CaptureBandeiraEvent.time.get(player).equals(timeOWNER)) {
                                            silver.setTarget(player);
                                            silver.setCustomName("§7Traça (" + tempoRestante + "s)");
                                        }
                                    }
                                }
                                if (tempoRestante == 0) {
                                    silver.setHealth(0);
                                    this.cancel();
                                }
                                tempoRestante--;
                            }
                        }.runTaskTimer(MineSkyEvents.get(), 0, 20);
                    }
                }, 20L);
            }
        }
    }

    @EventHandler
    public void onMobInteract(PlayerInteractEntityEvent event) {
        if (MineSkyEvents.event.equalsIgnoreCase("CaptureBandeira")) {
            if (event.getRightClicked().getType() == EntityType.IRON_GOLEM) {
                if (event.getPlayer().getInventory().getItemInMainHand().getType() == Material.IRON_INGOT) {
                    event.setCancelled(true);
                    event.getPlayer().sendMessage("§8[§c!§8] §cVocê não pode dar ferro para o Iron Golem!");
                }
            }
        }
    }

    @EventHandler
    public void onEntityExplode(EntityExplodeEvent event) {
        if (MineSkyEvents.event.equalsIgnoreCase("CaptureBandeira")) {
            for (Block block : event.blockList()) {
                if (!CaptureBandeiraEvent.blocksplace.containsKey(block.getLocation())) {
                    event.blockList().remove(block);
                }
            }
            event.blockList().removeIf(block -> !CaptureBandeiraEvent.blocksplace.containsKey(block.getLocation()));
            for (Player player : Bukkit.getOnlinePlayers()) {
                Location playerLocation = player.getEyeLocation();
                for (Block block : event.blockList()) {
                    double distance = block.getLocation().distance(playerLocation);
                    if (distance <= 5) {
                        Vector dir = playerLocation.getDirection().normalize();
                        dir.setY(0.6);
                        player.setVelocity(dir.multiply(1.5));
                    }
                }
            }
        }
    }

    private boolean isOffsetInArray(int dx, int dz, int[][] array) {
        for (int[] offset : array) {
            if (offset[0] == dx && offset[1] == dz) {
                return true;
            }
        }
        return false;
    }

    private Material getRandomOrangeBlock(Player p) {
        Material randomb = random.nextBoolean() ? Material.ORANGE_WOOL : Material.ORANGE_CONCRETE;
        if (CaptureBandeiraEvent.time.get(p).equals("Green")) {
            randomb = random.nextBoolean() ? Material.LIME_WOOL : Material.LIME_CONCRETE;
        }
        return randomb;
    }
}
