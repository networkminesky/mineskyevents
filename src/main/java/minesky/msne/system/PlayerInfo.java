package minesky.msne.system;

import minesky.msne.MineSkyEvents;
import minesky.msne.commands.EventCommand;
import minesky.msne.commands.MSNECommand;
import minesky.msne.config.DataManager;
import minesky.msne.config.Locations;
import minesky.msne.config.Messages;
import minesky.msne.events.*;
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

import java.io.File;
import java.io.IOException;


public class PlayerInfo implements Listener {
    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        Player p = e.getPlayer();
        EventCommand.RevealPlayer(p);
        File file = DataManager.getFile(p.getName().toLowerCase(), "playerdata");
        FileConfiguration config = DataManager.getConfiguration(file);

        config.set("Event", false);
        config.set("EventSpect", false);
        config.set("Address", p.getAddress().getAddress().getHostAddress());
        try {
            config.save(Util.PlayerDataF(p));
        } catch (IOException ex) {
            Bukkit.getConsoleSender().sendMessage(Messages.Falied_to_save.replace("%file%", p.getName() + " (PlayerData)"));
        }
        if (!p.hasPermission("mineskyevents.bypass.join")) {
            if (MineSkyEvents.event == "TNTRun") {
                if (TNTRunEvent.selectedMap.equals("Mapa1")) {
                    Bukkit.dispatchCommand(p, "tntrun join mapa1");
                }
                if (TNTRunEvent.selectedMap.equals("Mapa2")) {
                    Bukkit.dispatchCommand(p, "tntrun join mapa2");
                }
                return;
            }
            Bukkit.dispatchCommand(e.getPlayer(), "event entrar");
        }
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent e) {
        Player p = e.getPlayer();
        EventCommand.RevealPlayer(p);
        Location location = p.getLocation();
        String loc = Util.serializeLocation(location);
        if (!(MineSkyEvents.event == "OFF")) {
            switch (MineSkyEvents.event) {
                case "Spleef":
                    if (!SpleefEvent.contagem && SpleefEvent.contagemI) {
                        File file = DataManager.getFile(p.getName().toLowerCase(), "playerdata");
                        FileConfiguration config = DataManager.getConfiguration(file);
                        if (config.getBoolean("Event")) {
                            if (SpleefEvent.playerson.size() == 1) SpleefEvent.finalizar();
                            config.set("Events.Spleef.dead", config.getInt("Events.Spleef.dead")+1);
                            SpleefEvent.playerson.remove(p);
                        }
                    } else {
                        for (Player player2 : Bukkit.getOnlinePlayers()) {
                            if (Util.PDVE(player2)) {
                                SpleefEvent.playerson.remove(p);
                                int playersone = SpleefEvent.playerson.size();
                                player2.sendMessage("§7" + p.getName() + " §esaiu do evento. (§b" + playersone + "§e/§b4§e)");
                            }
                        }
                    }
                    break;
                case "TijolãoWars":
                    if (!TijolãoWarsEvent.contagem && TijolãoWarsEvent.contagemI) {
                        File file = DataManager.getFile(p.getName().toLowerCase(), "playerdata");
                        FileConfiguration config = DataManager.getConfiguration(file);
                        if (config.getBoolean("Event")) {
                            if (TijolãoWarsEvent.playerson.size() == 1) TijolãoWarsEvent.finalizar();
                            config.set("Events.Tijolao.dead", config.getInt("Events.Tijolao.dead")+1);
                            TijolãoWarsEvent.playerson.remove(p);
                        }
                    } else {
                        for (Player player2 : Bukkit.getOnlinePlayers()) {
                            if (Util.PDVE(player2)) {
                                TijolãoWarsEvent.playerson.remove(p);
                               int playersone = TijolãoWarsEvent.playerson.size();
                                player2.sendMessage("§7" + p.getName() + " §esaiu do evento. (§b" + playersone + "§e/§b4§e)");
                            }
                        }
                    }
                    break;
                case "Corrida":
                    if (!CorridaEvent.contagem && CorridaEvent.contagemI) {
                        File file = DataManager.getFile(p.getName().toLowerCase(), "playerdata");
                        FileConfiguration config = DataManager.getConfiguration(file);
                        if (config.getBoolean("Event")) {
                            CorridaEvent.playerson.remove(p);
                        }
                    } else {
                        for (Player player2 : Bukkit.getOnlinePlayers()) {
                            if (Util.PDVE(player2)) {
                                CorridaEvent.playerson.remove(p);
                                int playersone = CorridaEvent.playerson.size();
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
                            CorridaBoatEvent.playerson.remove(p);
                        }
                    } else {
                        for (Player player2 : Bukkit.getOnlinePlayers()) {
                            if (Util.PDVE(player2)) {
                                CorridaBoatEvent.playerson.remove(p);
                                int playersone = CorridaBoatEvent.playerson.size();
                                player2.sendMessage("§7" + p.getName() + " §esaiu do evento. (§b" + playersone + "§e/§b4§e)");
                            }
                        }
                    }
                    break;
                case "Sumo":
                    if (!SumoEvent.contagem && SumoEvent.contagemI) {
                        File file = DataManager.getFile(p.getName().toLowerCase(), "playerdata");
                        FileConfiguration config = DataManager.getConfiguration(file);
                        if (config.getBoolean("Event")) {
                            if (SumoEvent.playerson.size() == 1) SumoEvent.finalizar();
                            config.set("Events.Sumo.dead", config.getInt("Events.Sumo.dead")+1);
                            SumoEvent.playerson.remove(p);
                        }
                    } else {
                        for (Player player2 : Bukkit.getOnlinePlayers()) {
                            if (Util.PDVE(player2)) {
                                SumoEvent.playerson.remove(p);
                                int playersone = SumoEvent.playerson.size();
                                player2.sendMessage("§7" + p.getName() + " §esaiu do evento. (§b" + playersone + "§e/§b4§e)");
                            }
                        }
                    }
                    break;
                    case "TNTTag":
                    if (!TNTTagEvent.contagem && TNTTagEvent.contagemI && !TNTTagEvent.tnt.contains(p)) {
                        TNTTagEvent.playerson.remove(p);
                        TNTTagEvent.mortos.add(p);
                        TNTTagEvent.tnt.remove(p);
                        TNTTagEvent.jogadores.remove(p);
                        p.getInventory().setHelmet(null);
                        File file = DataManager.getFile(p.getName().toLowerCase(), "playerdata");
                        FileConfiguration config = DataManager.getConfiguration(file);
                        if (config.getBoolean("Event")) {
                            if (SumoEvent.playerson.size() == 1) SumoEvent.finalizar();
                            config.set("Events.TNTTag.dead", config.getInt("Events.TNTTag.dead")+1);
                        }
                    } else if (!TNTTagEvent.contagem && TNTTagEvent.contagemI && TNTTagEvent.tnt.contains(p)) {
                        TNTTagEvent.playerson.remove(p);
                        TNTTagEvent.mortos.add(p);
                        TNTTagEvent.jogadores.remove(p);
                        p.getInventory().setHelmet(null);
                        if (TNTTagEvent.playerson.size() == 1) TNTTagEvent.finalizar();
                        if (TNTTagEvent.tnt.contains(p)) {
                            TNTTagEvent.tnt.remove(p);
                            if (TNTTagEvent.playerson.size() < 8) {
                                if (TNTTagEvent.tnt.isEmpty()) {
                                    Player p1 = TNTTagEvent.jogadores.get(TNTTagEvent.random.nextInt(TNTTagEvent.jogadores.size()));
                                    TNTTagEvent.tnt.add(p1);
                                    TNTTagEvent.temp1.cancel();
                                    TNTTagEvent.secoundsTNTTAGP1(p1);
                                    p1.getInventory().setHelmet(Util.TNT);
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
                                    p1.getInventory().setHelmet(Util.TNT);
                                    p2.getInventory().setHelmet(Util.TNT);
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
                                    if (TNTTagEvent.PEGOS2.getName().equalsIgnoreCase(p.getName())){
                                        TNTTagEvent.temp2.cancel();
                                        TNTTagEvent.secoundsTNTTAGP2(p1);
                                    }
                                    p1.getInventory().setHelmet(Util.TNT);
                                    p1.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, Integer.MAX_VALUE, 0, false, false));
                                }
                            }
                        }
                        File file = DataManager.getFile(p.getName().toLowerCase(), "playerdata");
                        FileConfiguration config = DataManager.getConfiguration(file);

                        config.set("Event", false);
                        config.set("Events.TNTTag.dead", config.getInt("Events.TNTTag.dead")+1);
                        try {
                            config.save(Util.PlayerDataF(p));
                        } catch (IOException er) {
                            er.printStackTrace();
                        }
                        for (Player player : Bukkit.getOnlinePlayers()) {
                            if (player.getName().equals(p.getName())) return;
                            player.sendMessage("§8[§f☠§8] §c" + p.getName() + " §7Saiu do servidor.");
                        }
                        return;
                    } else {
                        for (Player player2 : Bukkit.getOnlinePlayers()) {
                            if (Util.PDVE(player2)) {
                                TNTTagEvent.playerson.remove(p);
                                int playersone = TNTTagEvent.playerson.size();
                                player2.sendMessage("§7" + p.getName() + " §esaiu do evento. (§b" + playersone + "§e/§b4§e)");
                            }
                        }
                    }
                    break;
                case "Parapente":
                    if (!ParapenteEvent.contagem && ParapenteEvent.contagemI) {
                        File file = DataManager.getFile(p.getName().toLowerCase(), "playerdata");
                        FileConfiguration config = DataManager.getConfiguration(file);
                        if (config.getBoolean("Event")) {
                            ParapenteEvent.playerson.remove(p);
                        }
                    } else {
                        for (Player player2 : Bukkit.getOnlinePlayers()) {
                            if (Util.PDVE(player2)) {
                                ParapenteEvent.playerson.remove(p);
                                int playersone = ParapenteEvent.playerson.size();
                                player2.sendMessage("§7" + p.getName() + " §esaiu do evento. (§b" + playersone + "§e/§b4§e)");
                            }
                        }
                    }
                    break;
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

        if (Util.BedLeave.isSimilar(player.getInventory().getItemInMainHand())) {
            if (Util.PDVES(player)) {
                EventCommand.RevealPlayer(player);
                Util.sendConectionBCMSNE(player);
                File file = DataManager.getFile(player.getName().toLowerCase(), "playerdata");
                FileConfiguration config = DataManager.getConfiguration(file);
                config.set("EventSpect", false);
                try {
                    config.save(file);
                    event.getPlayer().sendMessage("§8[§c!§8] §7Você parou de assistir o evento §c" + MineSkyEvents.event);
                    TijolãoWarsEvent.playerson.remove(player);
                    CorridaEvent.playerson.remove(player);
                    CorridaBoatEvent.playerson.remove(player);
                    SumoEvent.playerson.remove(player);
                    TNTTagEvent.playerson.remove(player);
                    ParapenteEvent.playerson.remove(player);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return;
            }

            if (!Util.PDVE(player)) return;
            EventCommand.RevealPlayer(player);
            Util.sendConectionBCMSNE(player);

            File file = DataManager.getFile(player.getName().toLowerCase(), "playerdata");
            FileConfiguration config = DataManager.getConfiguration(file);

            config.set("Event", false);

            try {
                config.save(file);
                event.getPlayer().sendMessage("§8[§c!§8] §7Você saiu do evento §c" + MineSkyEvents.event);
                TijolãoWarsEvent.playerson.remove(player);
                CorridaEvent.playerson.remove(player);
                CorridaBoatEvent.playerson.remove(player);
                SumoEvent.playerson.remove(player);
                TNTTagEvent.playerson.remove(player);
                ParapenteEvent.playerson.remove(player);
            } catch (IOException e) {
                e.printStackTrace();
            }

            for (Player p : Bukkit.getOnlinePlayers()) {
                if (Util.PDVE(p)) {
                    int playersone = 0;
                    switch (MineSkyEvents.event) {
                        case "TijolãoWars":
                            playersone =
                                    TijolãoWarsEvent.playerson.size();
                            TijolãoWarsEvent.playerson.remove(p);
                            break;
                        case "Corrida":
                            playersone =
                                    CorridaEvent.playerson.size();
                            CorridaEvent.playerson.remove(p);
                            break;
                        case "CorridaBoat":
                            playersone = CorridaBoatEvent.playerson.size();
                            CorridaBoatEvent.playerson.remove(p);
                            break;
                        case "Sumo":
                            playersone = SumoEvent.playerson.size();
                            SumoEvent.playerson.remove(p);
                            break;
                        case "TNTTag":
                            playersone = TNTTagEvent.playerson.size();
                            TNTTagEvent.playerson.remove(p);
                            break;
                        case "Parapente":
                            playersone = ParapenteEvent.playerson.size();
                            ParapenteEvent.playerson.remove(p);
                            break;
                    }
                    p.sendMessage("§7" + player.getName() + " §esaiu do evento. (§b" + playersone + "§e/§b4§e)");
                } else {
                    return;
                }
            }
        } else if (Util.CheckP.isSimilar(player.getInventory().getItemInMainHand())) {
            int listC = MSNECommand.playerCHECKPOINT.getOrDefault(player, 0);
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
        } else {
            if (Util.PDVES(player)) {
                event.setCancelled(true);
            }
        }
    }


    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {

        if (event.getClickedInventory() != null && event.getClickedInventory().getHolder() == event.getWhoClicked()) {

            if (event.getCurrentItem() != null && event.getCurrentItem().isSimilar(Util.BedLeave) || event.getCurrentItem().isSimilar(Util.StoneShovel) || event.getCurrentItem().getType() == Material.PLAYER_HEAD || event.getCurrentItem().isSimilar(Util.Barco) || event.getCurrentItem().isSimilar(Util.SumoItem) || event.getCurrentItem().isSimilar(Util.TNT) || event.getCurrentItem().isSimilar(Util.CheckP)) {
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
        if (droppedItem.isSimilar(Util.StoneShovel) || droppedItem.isSimilar(Util.BedLeave) || droppedItem.getType() == Material.BRICK || droppedItem.getType() == Material.PLAYER_HEAD || droppedItem.isSimilar(Util.Barco) || droppedItem.isSimilar(Util.SumoItem) || droppedItem.isSimilar(Util.TNT) || droppedItem.isSimilar(Util.CheckP)) {
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
        if (MineSkyEvents.event == "Spleef") {
            SpleefEvent.playerson.remove(p);
            SpleefEvent.mortos.add(p);
            if (SpleefEvent.playerson.size() == 1) SpleefEvent.finalizar();
            p.sendMessage("§8[§f☠§8] §cVocê §7morreu.");
            event.setDeathMessage(null);
            File file = DataManager.getFile(p.getName().toLowerCase(), "playerdata");
            FileConfiguration config = DataManager.getConfiguration(file);

            config.set("Event", false);
            config.set("Events.Spleef.dead", config.getInt("Events.Spleef.dead")+1);
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
        if (MineSkyEvents.event == "TijolãoWars") {
            TijolãoWarsEvent.playerson.remove(p);
            TijolãoWarsEvent.mortos.add(p);
            if (TijolãoWarsEvent.playerson.size() == 1) TijolãoWarsEvent.finalizar();
            p.sendMessage("§8[§f☠§8] §cVocê §7morreu.");
            event.setDeathMessage(null);
            File file = DataManager.getFile(p.getName().toLowerCase(), "playerdata");
            FileConfiguration config = DataManager.getConfiguration(file);

            config.set("Event", false);
            config.set("Events.Tijolao.dead", config.getInt("Events.Tijolao.dead")+1);
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
        if (MineSkyEvents.event == "Corrida") {
            CorridaEvent.playerson.remove(p);
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
        if (MineSkyEvents.event == "Sumo") {
            SumoEvent.playerson.remove(p);
            SumoEvent.mortos.add(p);
            if (SumoEvent.playerson.size() == 1) SumoEvent.finalizar();
            p.sendMessage("§8[§f☠§8] §cVocê §7morreu.");
            event.setDeathMessage(null);
            File file = DataManager.getFile(p.getName().toLowerCase(), "playerdata");
            FileConfiguration config = DataManager.getConfiguration(file);

            config.set("Event", false);
            config.set("Events.Sumo.dead", config.getInt("Events.Sumo.dead")+1);
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
        if (MineSkyEvents.event == "TNTTag") {
            TNTTagEvent.playerson.remove(p);
            TNTTagEvent.mortos.add(p);
            TNTTagEvent.jogadores.remove(p);
            p.getInventory().setHelmet(null);
            if (TNTTagEvent.playerson.size() == 1) TNTTagEvent.finalizar();
            if (TNTTagEvent.tnt.contains(p)) {
                TNTTagEvent.tnt.remove(p);
                if (TNTTagEvent.playerson.size() < 8) {
                    if (TNTTagEvent.tnt.isEmpty()) {
                        Player p1 = TNTTagEvent.jogadores.get(TNTTagEvent.random.nextInt(TNTTagEvent.jogadores.size()));
                        TNTTagEvent.tnt.add(p1);
                        TNTTagEvent.temp1.cancel();
                        TNTTagEvent.secoundsTNTTAGP1(p1);
                        p1.getInventory().setHelmet(Util.TNT);
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
                        p1.getInventory().setHelmet(Util.TNT);
                        p2.getInventory().setHelmet(Util.TNT);
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
                        if (TNTTagEvent.PEGOS2.getName().equalsIgnoreCase(p.getName())){
                            TNTTagEvent.temp2.cancel();
                            TNTTagEvent.secoundsTNTTAGP2(p1);
                        }
                        p1.getInventory().setHelmet(Util.TNT);
                        p1.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, Integer.MAX_VALUE, 0, false, false));
                    }
                }
                p.sendMessage("§8[§f☠§8] §cVocê §7foi explodido.");
            }
            event.setDeathMessage(null);
            File file = DataManager.getFile(p.getName().toLowerCase(), "playerdata");
            FileConfiguration config = DataManager.getConfiguration(file);

            config.set("Event", false);
            config.set("Events.TNTTag.dead", config.getInt("Events.TNTTag.dead")+1);
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
            return;
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
            return;
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
            if (MineSkyEvents.event == "TNTTag") {
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
                            entity.getInventory().setHelmet(Util.TNT);
                            entity.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, Integer.MAX_VALUE, 0, false, false));
                            damager.removePotionEffect(PotionEffectType.SPEED);
                            damager.sendMessage("§8[§a!§8] §aVocê pegou o jogador §l" + entity.getName());
                            return;
                        }
                    }
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
                return;
            }
        }
    }
}
