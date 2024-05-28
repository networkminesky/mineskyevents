package minesky.msne.system;

import com.sk89q.worldedit.bukkit.BukkitAdapter;
import com.sk89q.worldguard.WorldGuard;
import com.sk89q.worldguard.protection.ApplicableRegionSet;
import com.sk89q.worldguard.protection.flags.StateFlag;
import com.sk89q.worldguard.protection.managers.RegionManager;
import com.sk89q.worldguard.protection.regions.ProtectedRegion;
import minesky.msne.MineSkyEvents;
import minesky.msne.events.CorridaBoatEvent;
import minesky.msne.events.CorridaEvent;
import minesky.msne.events.ParapenteEvent;
import minesky.msne.events.TNTRunEvent;
import minesky.msne.utils.RegionPlayerManager;
import minesky.msne.utils.Util;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashMap;
import java.util.UUID;

public class PlayerMove {
    private static final HashMap<UUID, BukkitRunnable> playerIdleTasks = new HashMap<>();
    private static HashMap<UUID, Location> lastPlayerLocation = new HashMap<>();
    public static BukkitRunnable tempNOTMOVE;
    public static BukkitRunnable PlayerMoveCheck = new BukkitRunnable() {
        @Override
        public void run() {
            for (Player player : Bukkit.getOnlinePlayers()) {
                UUID playerId = player.getUniqueId();
                Location pl = player.getLocation();
                Location block1 = pl.clone().subtract(0, 1, 0);
                Location block2 = pl.clone().subtract(0, 2, 0);
                if (MineSkyEvents.event.equals("TNTRun")) {
                    if (!Util.PDVE(player)) return;
                    if (TNTRunEvent.contagem) return;
                    if(!TNTRunEvent.contagemI) return;

                    if (playerIdleTasks.containsKey(playerId)) {
                        playerIdleTasks.get(playerId).cancel();
                    }

                    lastPlayerLocation.put(playerId, pl);

                    if (block1.getBlock().getType() == Material.AIR || block1.getBlock().getType() == Material.LIGHT) return;
                    TNTRunEvent.blocksbreak.put(block1, block1.getBlock().getType());
                    TNTRunEvent.blocksbreak.put(block2, block2.getBlock().getType());
                    block2.getBlock().setType(Material.AIR);
                    block1.getBlock().setType(Material.AIR);
                    onNotMovePlayer(player);
                }
                if (MineSkyEvents.event.equals("Corrida") || MineSkyEvents.event.equals("CorridaBoat") || MineSkyEvents.event.equals("Parapente")) {
                    if (!Util.PDVE(player)) return;

                    RegionManager regionManager = WorldGuard.getInstance().getPlatform().getRegionContainer().get(BukkitAdapter.adapt(player.getWorld()));
                    if (regionManager == null) return;
                    ApplicableRegionSet regions = regionManager.getApplicableRegions(BukkitAdapter.asBlockVector(player.getLocation()));
                    for (ProtectedRegion region : regions) {
                        if (region.getFlag(minesky.msne.addons.WorldGuard.CORRIDA_FINAL) == StateFlag.State.ALLOW) {
                            if (!RegionPlayerManager.getPlayer(player)) {
                                CorridaEvent.chegada(player);
                                RegionPlayerManager.addPlayer(player);
                            }
                        }
                        if (region.getFlag(minesky.msne.addons.WorldGuard.CORRIDA_BOAT_FINAL) == StateFlag.State.ALLOW) {
                            CorridaBoatEvent.chegada(player);
                        }
                        if (region.getFlag(minesky.msne.addons.WorldGuard.CORRIDA_PARAGLIDER_FINAL) == StateFlag.State.ALLOW) {
                            if (!RegionPlayerManager.getPlayer(player)) {
                                ParapenteEvent.chegada(player);
                                RegionPlayerManager.addPlayer(player);
                            }
                        }
                        Integer checkpoint = region.getFlag(minesky.msne.addons.WorldGuard.CORRIDA_PARAGLIDER_CHECKPOINT);
                        if (checkpoint != null) {
                            ParapenteEvent.CheckPoint(player, checkpoint);
                        }
                        Integer arcos = region.getFlag(minesky.msne.addons.WorldGuard.CORRIDA_PARAGLIDER_ARCO);
                        if (arcos != null) {
                            ParapenteEvent.Arcos(player, arcos);
                        }
                    }
                }
            }
        }
    };
    public static void onNotMovePlayer(Player player) {
        Location pl = player.getLocation();
        Location block1 = pl.clone().subtract(0, 1, 0);
        Location block2 = pl.clone().subtract(0, 2, 0);
        UUID playerId = player.getUniqueId();

        tempNOTMOVE = new BukkitRunnable() {
            int tempoRestante = 3;
            @Override
            public void run() {
                if (lastPlayerLocation.get(playerId).distance(pl) < 0.1) {
                    tempoRestante--;
                } else {
                    tempoRestante = 3;
                }
                if (tempoRestante == 0) {
                    if (block1.getBlock().getType() == Material.AIR || block1.getBlock().getType() == Material.LIGHT) return;
                    TNTRunEvent.blocksbreak.put(block1, block1.getBlock().getType());
                    TNTRunEvent.blocksbreak.put(block2, block2.getBlock().getType());
                    block2.getBlock().setType(Material.AIR);
                    block1.getBlock().setType(Material.AIR);
                    this.cancel();
                }
                lastPlayerLocation.put(playerId, player.getLocation());
            }
        };

        playerIdleTasks.put(playerId, tempNOTMOVE);
        tempNOTMOVE.runTaskTimer(MineSkyEvents.get(), 0, 20);
    }
}
