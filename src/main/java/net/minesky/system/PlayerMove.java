package net.minesky.system;

import com.sk89q.worldedit.bukkit.BukkitAdapter;
import com.sk89q.worldguard.WorldGuard;
import com.sk89q.worldguard.protection.ApplicableRegionSet;
import com.sk89q.worldguard.protection.flags.StateFlag;
import com.sk89q.worldguard.protection.managers.RegionManager;
import com.sk89q.worldguard.protection.regions.ProtectedRegion;
import net.minesky.MineSkyEvents;
import net.minesky.events.CorridaEvent;
import net.minesky.utils.RegionPlayerManager;
import net.minesky.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashMap;
import java.util.UUID;

public class PlayerMove {
    private static final HashMap<UUID, BukkitRunnable> playerIdleTasks = new HashMap<>();
    private static final HashMap<UUID, Location> lastPlayerLocation = new HashMap<>();

    public static final BukkitRunnable PlayerMoveCheck = new BukkitRunnable() {
        @Override
        public void run() {
            for (Player player : Bukkit.getOnlinePlayers()) {
                if (!Utils.isPlayerInEvent(player)) continue;
                UUID playerId = player.getUniqueId();
                Location pl = player.getLocation();

              /*  if (MineSkyEvents.event.equals("TNTRun") && TNTRunEvent.contagem && TNTRunEvent.contagemI) {
                    handleTNTRun(player, playerId, pl);
                }*/

                handleRegions(player);
            }
        }
    };

    private static void handleTNTRun(Player player, UUID playerId, Location pl) {
        cancelIdleTask(playerId);
        lastPlayerLocation.put(playerId, pl);
        checkMorte(player);

        Location block1 = pl.clone().subtract(0, 1, 0);
        Location block2 = pl.clone().subtract(0, 2, 0);
        if (block1.getBlock().getType() == Material.AIR || block1.getBlock().getType() == Material.LIGHT) return;

        //TNTRunEvent.blocksbreak.put(block1, block1.getBlock().getType());
        //TNTRunEvent.blocksbreak.put(block2, block2.getBlock().getType());
        block1.getBlock().setType(Material.AIR);
        block2.getBlock().setType(Material.AIR);
        onNotMovePlayer(player);
    }

    private static void handleRegions(Player player) {
        RegionManager regionManager = WorldGuard.getInstance().getPlatform().getRegionContainer().get(BukkitAdapter.adapt(player.getWorld()));
        if (regionManager == null) return;

        ApplicableRegionSet regions = regionManager.getApplicableRegions(BukkitAdapter.asBlockVector(player.getLocation()));
        for (ProtectedRegion region : regions) {
            if (!Utils.isPlayerInEvent(player)) return;
            if (region.getFlag(net.minesky.hooks.WorldGuard.MORTE) == StateFlag.State.ALLOW) {
                player.setHealth(0);
                player.damage(999999999);
            }

            if (isRaceEvent()) {
                handleRaceEvents(player, region);
            }
        }
    }

    private static boolean isRaceEvent() {
        return MineSkyEvents.event.matches("Corrida|CorridaBoat|Parapente");
    }

    private static void handleRaceEvents(Player player, ProtectedRegion region) {
        if (region.getFlag(net.minesky.hooks.WorldGuard.CORRIDA_FINAL) == StateFlag.State.ALLOW && !RegionPlayerManager.getPlayer(player)) {
            CorridaEvent.chegada(player);
            RegionPlayerManager.addPlayer(player);
        }
        if (region.getFlag(net.minesky.hooks.WorldGuard.CORRIDA_BOAT_FINAL) == StateFlag.State.ALLOW) {
           // CorridaBoatEvent.chegada(player);
        }
        if (region.getFlag(net.minesky.hooks.WorldGuard.CORRIDA_PARAGLIDER_FINAL) == StateFlag.State.ALLOW && !RegionPlayerManager.getPlayer(player)) {
            //ParapenteEvent.chegada(player);
            RegionPlayerManager.addPlayer(player);
        }
        Integer checkpoint = region.getFlag(net.minesky.hooks.WorldGuard.CORRIDA_PARAGLIDER_CHECKPOINT);
        if (checkpoint != null) {
           // ParapenteEvent.CheckPoint(player, checkpoint);
        }
        Integer arcos = region.getFlag(net.minesky.hooks.WorldGuard.CORRIDA_PARAGLIDER_ARCO);
        if (arcos != null) {
           // ParapenteEvent.Arcos(player, arcos);
        }
    }

    private static void cancelIdleTask(UUID playerId) {
        if (playerIdleTasks.containsKey(playerId)) {
            playerIdleTasks.get(playerId).cancel();
        }
    }

    private static void checkMorte(Player player) {
        RegionManager regionManager = WorldGuard.getInstance().getPlatform().getRegionContainer().get(BukkitAdapter.adapt(player.getWorld()));
        if (regionManager == null) return;

        ApplicableRegionSet regions = regionManager.getApplicableRegions(BukkitAdapter.asBlockVector(player.getLocation()));
        for (ProtectedRegion region : regions) {
            if (!Utils.isPlayerInEvent(player)) return;
            if (region.getFlag(net.minesky.hooks.WorldGuard.MORTE) == StateFlag.State.ALLOW) {
                player.setHealth(0);
                player.damage(999999999);
            }
        }
    }

    public static void onNotMovePlayer(Player player) {
        UUID playerId = player.getUniqueId();
        Location pl = player.getLocation();

        BukkitRunnable tempNOTMOVE = new BukkitRunnable() {
            int tempoRestante = 3;

            @Override
            public void run() {
                if (lastPlayerLocation.get(playerId).distance(pl) < 0.1) {
                    if (--tempoRestante == 0) {
                        Location block1 = pl.clone().subtract(0, 1, 0);
                        Location block2 = pl.clone().subtract(0, 2, 0);
                        if (block1.getBlock().getType() != Material.AIR && block1.getBlock().getType() != Material.LIGHT) {
                            //TNTRunEvent.blocksbreak.put(block1, block1.getBlock().getType());
                            //TNTRunEvent.blocksbreak.put(block2, block2.getBlock().getType());
                            block1.getBlock().setType(Material.AIR);
                            block2.getBlock().setType(Material.AIR);
                        }
                        this.cancel();
                    }
                } else {
                    tempoRestante = 3;
                }
                lastPlayerLocation.put(playerId, player.getLocation());
            }
        };

        playerIdleTasks.put(playerId, tempNOTMOVE);
        tempNOTMOVE.runTaskTimer(MineSkyEvents.get(), 0, 20);
    }
}

