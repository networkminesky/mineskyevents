package minesky.msne.system;

import minesky.msne.MineSkyEvents;
import minesky.msne.config.DataManager;
import minesky.msne.config.Messages;
import minesky.msne.events.CorridaBoatEvent;
import minesky.msne.events.CorridaEvent;
import minesky.msne.events.SumoEvent;
import minesky.msne.events.TijolãoWarsEvent;
import minesky.msne.utils.Util;
import org.bukkit.*;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.*;
import org.bukkit.inventory.ItemStack;

import java.io.File;
import java.io.IOException;


public class PlayerInfo implements Listener {
    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        Player p = e.getPlayer();
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
                Bukkit.dispatchCommand(e.getPlayer(), "tntrun join mapa1");
                return;
            }
            Bukkit.dispatchCommand(e.getPlayer(), "event entrar");
        }
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent e) {
        Player p = e.getPlayer();
        Location location = p.getLocation();
        String loc = Util.serializeLocation(location);
        if (!(MineSkyEvents.event == "OFF")) {
            switch (MineSkyEvents.event) {
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
                            if (!Util.PDVE(p)) return;

                            int playersone = 0;
                            switch (MineSkyEvents.event) {
                                case "TijolãoWars": playersone = TijolãoWarsEvent.playerson.size(); break;
                                case "Corrida": playersone = CorridaEvent.playerson.size(); break;
                                case "CorridaBoat": playersone = CorridaBoatEvent.playerson.size(); break;
                                case "Sumo": playersone = SumoEvent.playerson.size(); break;
                            }
                            TijolãoWarsEvent.playerson.remove(p);
                            CorridaEvent.playerson.remove(p);
                            CorridaBoatEvent.playerson.remove(p);
                            SumoEvent.playerson.remove(p);
                            player2.sendMessage("§7" + p.getName() + " §esaiu do evento. (§b" + playersone + "§e/§b4§e)");
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
                            if (!Util.PDVE(p)) return;

                            int playersone = 0;
                            switch (MineSkyEvents.event) {
                                case "TijolãoWars": playersone = TijolãoWarsEvent.playerson.size(); break;
                                case "Corrida": playersone = CorridaEvent.playerson.size(); break;
                                case "CorridaBoat": playersone = CorridaBoatEvent.playerson.size(); break;
                                case "Sumo": playersone = SumoEvent.playerson.size(); break;
                            }
                            TijolãoWarsEvent.playerson.remove(p);
                            CorridaEvent.playerson.remove(p);
                            CorridaBoatEvent.playerson.remove(p);
                            SumoEvent.playerson.remove(p);
                            player2.sendMessage("§7" + p.getName() + " §esaiu do evento. (§b" + playersone + "§e/§b4§e)");
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
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return;
            }

            if (!Util.PDVE(player)) return;

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
            } catch (IOException e) {
                e.printStackTrace();
            }

            for (Player p : Bukkit.getOnlinePlayers()) {
                if (!Util.PDVE(p)) return;

                int playersone = 0;
                switch (MineSkyEvents.event) {
                    case "TijolãoWars": playersone = TijolãoWarsEvent.playerson.size(); break;
                    case "Corrida": playersone = CorridaEvent.playerson.size(); break;
                    case "CorridaBoat": playersone = CorridaBoatEvent.playerson.size(); break;
                    case "Sumo": playersone = SumoEvent.playerson.size(); break;
                }
                TijolãoWarsEvent.playerson.remove(p);
                CorridaEvent.playerson.remove(p);
                CorridaBoatEvent.playerson.remove(p);
                SumoEvent.playerson.remove(p);
                p.sendMessage("§7" + player.getName() + " §esaiu do evento. (§b" + playersone + "§e/§b4§e)");
            }
        }
    }


    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {

        if (event.getClickedInventory() != null && event.getClickedInventory().getHolder() == event.getWhoClicked()) {

            if (event.getCurrentItem() != null && event.getCurrentItem().isSimilar(Util.BedLeave) || event.getCurrentItem().isSimilar(Util.StoneShovel) || event.getCurrentItem().getType() == Material.PLAYER_HEAD) {
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
        if (droppedItem.isSimilar(Util.StoneShovel) || droppedItem.isSimilar(Util.BedLeave) || droppedItem.getType() == Material.BRICK || droppedItem.getType() == Material.PLAYER_HEAD) {
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
        if (!Util.PDVE(p)) return;
        if (MineSkyEvents.event == "TijolãoWars") {
            TijolãoWarsEvent.playerson.remove(p);
            TijolãoWarsEvent.mortos.add(p);
            if (TijolãoWarsEvent.playerson.size() == 1) TijolãoWarsEvent.finalizar();
            p.sendMessage("§8[§f☠§8] §cVocê §7morreu.");
            File file = DataManager.getFile(p.getName().toLowerCase(), "playerdata");
            FileConfiguration config = DataManager.getConfiguration(file);

            config.set("Event", false);
            config.set("Events.Tijolao.dead", config.getInt("Events.Tijolao.dead")+1);
            try {
                config.save(Util.PlayerDataF(p));
            } catch (IOException e) {
                e.printStackTrace();
            }
            for (Player player : Bukkit.getOnlinePlayers()) {
                if (!Util.PDVE(player)) return;
                player.sendMessage("§8[§f☠§8] §c" + p.getName() + " §7morreu.");
            }
            return;
        }
        if (MineSkyEvents.event == "Corrida") {
            CorridaEvent.playerson.remove(p);
            p.sendMessage("§8[§f☠§8] §cVocê §7morreu.");
            File file = DataManager.getFile(p.getName().toLowerCase(), "playerdata");
            FileConfiguration config = DataManager.getConfiguration(file);

            config.set("Event", false);
            try {
                config.save(Util.PlayerDataF(p));
            } catch (IOException e) {
                e.printStackTrace();
            }
            for (Player player : Bukkit.getOnlinePlayers()) {
                if (!Util.PDVE(player)) return;
                player.sendMessage("§8[§f☠§8] §c" + p.getName() + " §7morreu.");
            }
            return;
        }
        if (MineSkyEvents.event == "Sumo") {
            SumoEvent.playerson.remove(p);
            SumoEvent.mortos.add(p);
            if (SumoEvent.playerson.size() == 1) SumoEvent.finalizar();
            p.sendMessage("§8[§f☠§8] §cVocê §7morreu.");
            File file = DataManager.getFile(p.getName().toLowerCase(), "playerdata");
            FileConfiguration config = DataManager.getConfiguration(file);

            config.set("Event", false);
            config.set("Events.Sumo.dead", config.getInt("Events.Sumo.dead")+1);
            try {
                config.save(Util.PlayerDataF(p));
            } catch (IOException e) {
                e.printStackTrace();
            }
            for (Player player : Bukkit.getOnlinePlayers()) {
                if (!Util.PDVE(player)) return;
                player.sendMessage("§8[§f☠§8] §c" + p.getName() + " §7morreu.");
            }
            return;
        }
    }
}
