package net.minesky.system;

import net.minesky.MineSkyEvents;
import net.minesky.commands.EventCommand;
import net.minesky.config.DataManager;
import net.minesky.events.SpleefEvent;
import net.minesky.events.SumoEvent;
import net.minesky.events.TNTTagEvent;
import net.minesky.events.TijolãoWarsEvent;
import net.minesky.system.event.EventPlayerManager;
import net.minesky.utils.EventItem;
import net.minesky.utils.SendMessages;
import net.minesky.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Banner;
import org.bukkit.block.Block;
import org.bukkit.block.banner.Pattern;
import org.bukkit.block.banner.PatternType;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
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
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class PlayerInfo implements Listener {
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e) {
        Player p = e.getPlayer();
        EventCommand.RevealPlayer(p);
        File file = DataManager.getFile(p.getUniqueId().toString(), "playerdata");
        FileConfiguration config = DataManager.getConfiguration(file);
        if (!file.exists()) {
            PlayerData.initializePlayerData(file, config, e.getPlayer().getUniqueId().toString(), e.getPlayer());
        }

        config.set("Event", false);
        config.set("Spectator", false);
        saveConfig(p, config);

        if (!p.hasPermission("mineskyevents.bypass.join")) {
            Bukkit.dispatchCommand(p, "event entrar");
        }
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent e) {
        Player p = e.getPlayer();
        EventCommand.RevealPlayer(p);

        if (Utils.isPlayerInEvent(p) || Utils.isPlayerSpectating(p)) {
            if (!"OFF".equals(MineSkyEvents.event)) {
                handleEventQuit(p);
            }
        }
    }

    private void saveConfig(Player p, FileConfiguration config) {
        try {
            config.save(Utils.getPlayerDataFile(p));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private void handleEventQuit(Player p) {
        String event = MineSkyEvents.event;
        File file = DataManager.getFile(p.getUniqueId().toString(), "playerdata");
        FileConfiguration config = DataManager.getConfiguration(file);
        boolean isActive = false;
        switch (event) {
            case "Spleef": if (!SpleefEvent.contagem && SpleefEvent.contagemI) isActive = true;
            case "TijolãoWars": if (!TijolãoWarsEvent.contagem && TijolãoWarsEvent.contagemI) isActive = true;
            case "Sumo": if (!SumoEvent.contagem && SumoEvent.contagemI) isActive = true;
            default: isActive = false;
        }

        if (isActive && EventPlayerManager.getPlayerCount() == 1) {
            finalizeEvent(event);
        }

        if (Utils.isPlayerInEvent(p)) {
            config.set("Event", false);
            config.set("Spectator", false);
            if (isActive) config.set("Events." + event + ".dead", config.getInt("Events." + event + ".dead") + 1);
        }

        EventPlayerManager.removePlayer(p);
        broadcastEventMessage(p, isActive);
    }

    private void finalizeEvent(String event) {
        switch (event) {
            case "Spleef": SpleefEvent.finalizar(); break;
            case "TijolãoWars": TijolãoWarsEvent.finalizar(); break;
            case "Sumo": SumoEvent.finalizar(); break;
            //case "TNTRun": TNTRunEvent.finalizar(); break;
            // case "TNTTag": TNTTagEvent.finalizar(); break;
            default: break;
        }
    }

    private void broadcastEventMessage(Player p, boolean isActive) {
        String message = isActive ? "§8[§f☠§8] §c" + p.getName() + " §7Morreu!" :
                "§7" + p.getName() + " §esaiu do evento. (§b" + EventPlayerManager.getPlayerCount() + "§e/§b4§e)";
        Bukkit.getOnlinePlayers().forEach(player -> player.sendMessage(message));
    }

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        if (event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK) {
            handleRightClick(event);
        }
    }

    private void handleRightClick(PlayerInteractEvent event) {
        Player player = event.getPlayer();

        ItemStack itemInHand = player.getInventory().getItemInMainHand();
        if (EventItem.getItem("BedLeave").isSimilar(itemInHand)) {
            handleBedLeave(event, player);
        } else if (EventItem.getItem("CheckPoint").isSimilar(itemInHand)) {
            //handleCheckpoint(player);
        } else if (EventItem.HeadEvents(player).isSimilar(itemInHand)) {
            displayPlayerStats(player);
        }
    }

    private void handleBedLeave(PlayerInteractEvent event, Player player) {
        if (Utils.isPlayerSpectating(player)) {
            leaveEvent(event, player, "Spectator");
        } else if (Utils.isPlayerInEvent(player)) {
            leaveEvent(event, player, "Event");
        }
    }

    private void leaveEvent(PlayerInteractEvent event, Player player, String configKey) {
        EventCommand.RevealPlayer(player);
        if (!player.hasPermission("mineskyevents.bypass.join")) SendMessages.sendConnection(player);
        File file = DataManager.getFile(player.getUniqueId().toString(), "playerdata");
        FileConfiguration config = DataManager.getConfiguration(file);
        config.set(configKey, false);
        try {
            config.save(file);
            player.sendMessage("§8[§c!§8] §7Você saiu do evento §c" + MineSkyEvents.event);
        } catch (IOException e) {
            e.printStackTrace();
        }
        EventPlayerManager.removePlayer(player);
        Bukkit.getOnlinePlayers().forEach(p ->
                p.sendMessage("§7" + player.getName() + " §esaiu do evento. (§b" + EventPlayerManager.getPlayerCount() + "§e/§b4§e)")
        );
    }

   /* private void handleCheckpoint(Player player) {
        int checkpoint = ParapenteEvent.playerCHECKPOINT.getOrDefault(player, 0);
        Location location = switch (checkpoint) {
            case 1 -> Locations.parapenteC1;
            case 2 -> Locations.parapenteC2;
            case 3 -> Locations.parapenteC3;
            default -> Locations.parapenteA;
        };
        player.teleport(location, PlayerTeleportEvent.TeleportCause.COMMAND);
        player.sendMessage("§8[§a!§8] §aVocê voltou para o seu checkpoint!");
    }*/

    private void displayPlayerStats(Player player) {
        FileConfiguration config = DataManager.getConfiguration(DataManager.getFile(player.getUniqueId().toString(), "playerdata"));
        String event = MineSkyEvents.event;
        String stats = switch (event) {
            case "Spleef" -> "§b§lSpleef §8| §aVitórias: §7" + config.getInt("Events.Spleef.win") + " §aDerrotas: §7" + config.getInt("Events.Spleef.dead");
            case "TijolãoWars" -> "§6§lTijolãoWars §8| §aVitórias: §7" + config.getInt("Events.TijolãoWars.win") + " §aDerrotas: §7" + config.getInt("Events.TijolãoWars.dead");
            case "Corrida" -> "§e§lCorrida §8| §aVitórias: §7" + config.getInt("Events.Corrida.win");
            case "CorridaBoat" -> "§9§lCorrida de Barco §8| §aVitórias: §7" + config.getInt("Events.CorridaBoat.win");
            case "Sumo" -> "§4§lSumo §8| §aVitórias: §7" + config.getInt("Events.Sumo.win") + " §aDerrotas: §7" + config.getInt("Events.Sumo.dead");
            case "TNTRun" -> "§c§lTNTRUN §8| §aVitórias: §7" + config.getInt("Events.TNTRun.win") + " §aDerrotas: §7" + config.getInt("Events.TNTRun.dead");
            case "TNTTag" -> "§c§lTNT-TAG §8| §aVitórias: §7" + config.getInt("Events.TNTTag.win") + " §aDerrotas: §7" + config.getInt("Events.TNTTag.dead");
            case "Parapente" -> "§3§lCorrida de Parapente §8| §aVitórias: §7" + config.getInt("Events.Parapente.win");
            default -> "Evento desconhecido";
        };
        player.sendMessage(stats);
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        ItemStack currentItem = event.getCurrentItem();

        if (currentItem != null && (
                currentItem.isSimilar(EventItem.getItem("BedLeave")) ||
                        currentItem.isSimilar(EventItem.getItem("SpleefITEM")) ||
                        currentItem.getType() == Material.PLAYER_HEAD ||
                        currentItem.isSimilar(EventItem.getItem("BarcoITEM")) ||
                        currentItem.isSimilar(EventItem.getItem("SumoITEM")) ||
                        currentItem.isSimilar(EventItem.getItem("TNTHEAD")) ||
                        currentItem.isSimilar(EventItem.getItem("CheckPoint"))
        )) {

            if (Utils.isPlayerSpectating((Player) event.getWhoClicked())) {
                event.setCancelled(true);
                return;
            }

            if (!Utils.isPlayerInEvent((Player) event.getWhoClicked())) return;
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onPlayerDropItem(PlayerDropItemEvent event) {
        ItemStack droppedItem = event.getItemDrop().getItemStack();
        if (droppedItem.isSimilar(EventItem.getItem("SpleefITEM")) || droppedItem.isSimilar(EventItem.getItem("BedLeave")) || droppedItem.getType() == Material.BRICK || droppedItem.getType() == Material.PLAYER_HEAD || droppedItem.isSimilar(EventItem.getItem("BarcoITEM")) || droppedItem.isSimilar(EventItem.getItem("SumoITEM")) || droppedItem.isSimilar(EventItem.getItem("TNTHEAD")) || droppedItem.isSimilar(EventItem.getItem("CheckPoint"))
            /*|| droppedItem.isSimilar(MMOItem.getParaglider())*/) {
            if (Utils.isPlayerSpectating(event.getPlayer())) {
                event.setCancelled(true);
                event.getPlayer().sendMessage("§8[§c!§8] §cVocê não pode dropar esse item.");
                return;
            }
            if (!Utils.isPlayerInEvent(event.getPlayer())) return;
            event.setCancelled(true);
            event.getPlayer().sendMessage("§8[§c!§8] §cVocê não pode dropar esse item.");
        }
    }

    @EventHandler
    public void onPlayerPickItem(PlayerPickupItemEvent event) {
        if (Utils.isPlayerSpectating(event.getPlayer())) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event) {
        Player p = event.getEntity();
        p.spigot().respawn();

        if (Utils.isPlayerSpectating(p)) {
            atualizarPlayerData(p, false, false);
            EventCommand.RevealPlayer(p);
            return;
        }

        if (!Utils.isPlayerInEvent(p)) return;

        String eventoAtual = MineSkyEvents.event;
        EventPlayerManager.removePlayer(p);
        event.setDeathMessage(null);

        switch (eventoAtual) {
            case "Spleef":
                processarMorteEvento(p, SpleefEvent.mortos, "Events.Spleef.dead", SpleefEvent::finalizar);
                break;
            case "TijolãoWars":
                processarMorteEvento(p, TijolãoWarsEvent.mortos, "Events.TijolãoWars.dead", TijolãoWarsEvent::finalizar);
                break;
            case "Corrida":
                processarMorteEvento(p, null, null, null);
                break;
            case "Sumo":
                processarMorteEvento(p, SumoEvent.mortos, "Events.Sumo.dead", SumoEvent::finalizar);
                break;
            case "TNTRun":
                //processarMorteEvento(p, TNTRunEvent.mortos, "Events.TNTRun.dead", TNTRunEvent::finalizar);
                break;
        }

        notificarMorte(p);
        Bukkit.dispatchCommand(p, "event entrar");
    }

    private void atualizarPlayerData(Player p, boolean event, boolean eventSpect) {
        File file = DataManager.getFile(p.getUniqueId().toString(), "playerdata");
        FileConfiguration config = DataManager.getConfiguration(file);
        config.set("Event", event);
        config.set("Spectator", eventSpect);

        try {
            config.save(Utils.getPlayerDataFile(p));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void processarMorteEvento(Player p, List<Player> listaMortos, String configPath, Runnable finalizarEvento) {
        if (listaMortos != null) listaMortos.add(p);

        if (EventPlayerManager.getPlayerCount() == 1 && finalizarEvento != null) finalizarEvento.run();

        atualizarPlayerData(p, false, false);
        if (configPath != null) {
            incrementarEstatistica(p, configPath);
        }

        p.sendMessage("§8[§f☠§8] §cVocê §7morreu.");
    }

    private void incrementarEstatistica(Player p, String path) {
        File file = DataManager.getFile(p.getUniqueId().toString(), "playerdata");
        FileConfiguration config = DataManager.getConfiguration(file);
        config.set(path, config.getInt(path) + 1);

        try {
            config.save(Utils.getPlayerDataFile(p));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void adicionarNovaTNT() {
        List<Player> jogadores = TNTTagEvent.jogadores;
        Random random = TNTTagEvent.random;

        if (EventPlayerManager.getPlayerCount() < 8) {
            Player p1 = jogadores.get(random.nextInt(jogadores.size()));
            atribuirTNT(p1);
        } else {
            Player p1 = jogadores.get(random.nextInt(jogadores.size()));
            Player p2;
            do {
                p2 = jogadores.get(random.nextInt(jogadores.size()));
            } while (p1.equals(p2));

            atribuirTNT(p1);
            atribuirTNT(p2);
        }
    }

    private static void atribuirTNT(Player p) {
        TNTTagEvent.tnt.add(p);
        p.getInventory().setHelmet(EventItem.getItem("TNTHEAD"));
        p.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, Integer.MAX_VALUE, 0, false, false));
        p.sendMessage("§8[§c!§8] §cVocê está com a TNT na cabeça! Você tem apenas §l" + TNTTagEvent.TIME + " §csegundos.");

        if (TNTTagEvent.PEGOS != null && TNTTagEvent.PEGOS.getName().equalsIgnoreCase(p.getName())) {
            TNTTagEvent.temp1.cancel();
            iniciarContagemTNT(p);
        }
        if (TNTTagEvent.PEGOS2 != null && TNTTagEvent.PEGOS2.getName().equalsIgnoreCase(p.getName())) {
            TNTTagEvent.temp2.cancel();
            iniciarContagemTNT(p);
        }
    }

    private static void iniciarContagemTNT(Player p) {
        BukkitRunnable temp = new BukkitRunnable() {
            int tempoRestante = TNTTagEvent.TIME;
            @Override
            public void run() {
                if (Arrays.asList(30, 25, 20, 15, 10, 5, 4, 3, 2, 1).contains(tempoRestante)) {
                    Bukkit.getOnlinePlayers().stream()
                            .filter(Utils::isPlayerInEvent)
                            .filter(player -> TNTTagEvent.tnt.contains(player) && p.getName().equalsIgnoreCase(player.getName()))
                            .forEach(player -> player.sendTitle(Utils.color("&c" + tempoRestante + "s"), "", 10, 70, 20));
                }
                if (tempoRestante-- == 0) {
                    p.damage(999999999);
                    p.setHealth(0);
                    this.cancel();
                }
            }
        };
        temp.runTaskTimer(MineSkyEvents.get(), 0, 20);
    }

    private static void salvarDadosMorte(Player p) {
        File file = DataManager.getFile(p.getUniqueId().toString(), "playerdata");
        FileConfiguration config = DataManager.getConfiguration(file);
        config.set("Event", false);
        config.set("Events.TNTTag.dead", config.getInt("Events.TNTTag.dead") + 1);
        try {
            config.save(Utils.getPlayerDataFile(p));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void notificarMorte(Player p) {
        for (Player player : Bukkit.getOnlinePlayers()) {
            if (!player.getName().equals(p.getName())) {
                player.sendMessage("§8[§f☠§8] §c" + p.getName() + " §7morreu.");
            }
        }
    }

    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        Player player = event.getPlayer();
        Block block = event.getBlock();
        Location location = block.getLocation();
        Material material = block.getType();

        if (Utils.isPlayerSpectating(player)) {
            event.setCancelled(true);
            return;
        }

        switch (MineSkyEvents.event) {
            case "Spleef":
                if (SpleefEvent.contagem) {
                    player.sendMessage("§8[§c!§8] §cEvento não iniciado.");
                    event.setCancelled(true);
                    return;
                }
                SpleefEvent.blocksbreak.put(location, material);
                break;
        }
    }

    @EventHandler
    public void onBlockPlace(BlockPlaceEvent event) {
        Player player = event.getPlayer();
        Block block = event.getBlock();
        Location location = block.getLocation();
        Material material = block.getType();
            ItemStack currentItem = event.getItemInHand();

            if (currentItem != null && (
                    currentItem.isSimilar(EventItem.getItem("BedLeave")) ||
                            currentItem.isSimilar(EventItem.getItem("SpleefITEM")) ||
                            currentItem.getType() == Material.PLAYER_HEAD ||
                            currentItem.isSimilar(EventItem.getItem("BarcoITEM")) ||
                            currentItem.isSimilar(EventItem.getItem("SumoITEM")) ||
                            currentItem.isSimilar(EventItem.getItem("TNTHEAD")) ||
                            currentItem.isSimilar(EventItem.getItem("CheckPoint"))
            )) {
                event.setCancelled(true);
                return;
        }

        if (Utils.isPlayerSpectating(player)) {
            event.setCancelled(true);
            return;
        }
    }

    @EventHandler
    public void onEntityDamage(EntityDamageEvent event) {
        if (!(event.getEntity() instanceof Player player)) return;

        if (Utils.isPlayerSpectating(player) || (MineSkyEvents.event.equals("TNTTag") && Utils.isPlayerInEvent(player))) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onEntityDamageByEntity(EntityDamageByEntityEvent event) {
        Entity damager = event.getDamager();
        Entity entity = event.getEntity();

        if (!(damager instanceof Player playerDamager)) return;

        if (Utils.isPlayerSpectating(playerDamager)) {
            event.setCancelled(true);
            return;
        }

        switch (MineSkyEvents.event) {
            case "TNTTag" -> {
               /* if (TNTTagEvent.contagem) {
                    playerDamager.sendMessage("§8[§c!§8] §cEvento não iniciado.");
                    event.setCancelled(true);
                    return;
                }
                if (entity instanceof Player playerEntity && Util.isPlayerInEvent(playerDamager)) {
                    if (TNTTagEvent.tnt.contains(playerDamager)) {
                        if (TNTTagEvent.tnt.contains(playerEntity)) {
                            playerDamager.sendMessage("§8[§c!§8] §cVocê não pode pegar outra pessoa que está com a TNT na cabeça.");
                            return;
                        }
                        transferTNT(playerDamager, playerEntity);
                    }
                }*/
                return;
            }
            case "Sumo" -> {
                if (SumoEvent.contagem) {
                    playerDamager.sendMessage("§8[§c!§8] §cEvento não iniciado.");
                    event.setCancelled(true);
                }
                return;
            }
        }
    }

    /*private void transferTNT(Player damager, Player entity) {
        TNTTagEvent.tnt.remove(damager);
        TNTTagEvent.tnt.add(entity);

        if (TNTTagEvent.PEGOS.getName().equalsIgnoreCase(damager.getName())) {
            TNTTagEvent.temp1.cancel();
            TNTTagEvent.secoundsTNTTAGP1(entity);
        } else if (TNTTagEvent.PEGOS2.getName().equalsIgnoreCase(damager.getName())) {
            TNTTagEvent.temp2.cancel();
            TNTTagEvent.secoundsTNTTAGP2(entity);
        }

        entity.sendMessage("§8[§c!§8] §cVocê foi pego e tem §l" + TNTTagEvent.TIME + " §csegundos para pegar alguém.");
        damager.getInventory().setHelmet(null);
        entity.getInventory().setHelmet(EventItem.TNTHEAD);
        entity.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, Integer.MAX_VALUE, 0, false, false));
        damager.removePotionEffect(PotionEffectType.SPEED);
        damager.sendMessage("§8[§a!§8] §aVocê pegou o jogador §l" + entity.getName());
    }*/

    @EventHandler
    public void onEntityDamageBlock(EntityDamageByBlockEvent event) {
        if (event.getEntity() instanceof Player player && Utils.isPlayerSpectating(player)) {
            event.setCancelled(true);
        }
    }
}
