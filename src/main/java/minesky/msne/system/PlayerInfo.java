package minesky.msne.system;

import com.sk89q.worldedit.bukkit.BukkitAdapter;
import com.sk89q.worldguard.WorldGuard;
import com.sk89q.worldguard.bukkit.WorldGuardPlugin;
import com.sk89q.worldguard.protection.ApplicableRegionSet;
import com.sk89q.worldguard.protection.flags.StateFlag;
import com.sk89q.worldguard.protection.managers.RegionManager;
import com.sk89q.worldguard.protection.regions.ProtectedRegion;
import minesky.msne.MineSkyEvents;
import minesky.msne.addons.MMOItem;
import minesky.msne.commands.EventCommand;
import minesky.msne.commands.MSNECommand;
import minesky.msne.config.DataManager;
import minesky.msne.config.Locations;
import minesky.msne.config.Messages;
import minesky.msne.events.*;
import minesky.msne.system.event.EventPlayerManager;
import minesky.msne.utils.EventItem;
import minesky.msne.utils.RegionPlayerManager;
import minesky.msne.utils.SendMessages;
import minesky.msne.utils.Util;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageByBlockEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.*;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Objects;
import java.util.UUID;


public class PlayerInfo implements Listener {
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
        }else {
            if (Util.PDVES(player)) {
                event.setCancelled(true);
            }
        }
    }


    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {

        if (event.getClickedInventory() != null && event.getClickedInventory().getHolder() == event.getWhoClicked()) {

            if (event.getCurrentItem() != null && event.getCurrentItem().isSimilar(EventItem.BedLeave) || Objects.requireNonNull(event.getCurrentItem()).isSimilar(EventItem.SpleefITEM) || event.getCurrentItem().getType() == Material.PLAYER_HEAD || event.getCurrentItem().isSimilar(EventItem.BarcoITEM) || event.getCurrentItem().isSimilar(EventItem.SumoITEM) || event.getCurrentItem().isSimilar(EventItem.TNTHEAD) || event.getCurrentItem().isSimilar(EventItem.CheckPoint) || event.getCurrentItem().isSimilar(MMOItem.getParaglider())) {
                if (Util.PDVES((Player) event.getWhoClicked())) {
                    event.setCancelled(true);
                    return;
                }
                if (!Util.PDVE((Player) event.getWhoClicked())) return;
                event.setCancelled(true);
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
}
