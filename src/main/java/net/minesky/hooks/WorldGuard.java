package net.minesky.hooks;

import com.sk89q.worldguard.protection.flags.Flag;
import com.sk89q.worldguard.protection.flags.IntegerFlag;
import com.sk89q.worldguard.protection.flags.StateFlag;
import com.sk89q.worldguard.protection.flags.registry.FlagConflictException;
import com.sk89q.worldguard.protection.flags.registry.FlagRegistry;
import net.minesky.MineSkyEvents;
import org.bukkit.Bukkit;

public class WorldGuard {
    public static StateFlag MORTE = new StateFlag("morte", false);
    public static StateFlag CORRIDA_FINAL = new StateFlag("corrida-final", false);
    public static StateFlag CORRIDA_BOAT_FINAL = new StateFlag("corrida-boat-final", false);
    public static StateFlag CORRIDA_PARAGLIDER_FINAL = new StateFlag("corrida-paraglider-final", false);
    public static IntegerFlag CORRIDA_PARAGLIDER_CHECKPOINT = new IntegerFlag("corrida-paraglider-checkpoint");
    public static IntegerFlag CORRIDA_PARAGLIDER_ARCO = new IntegerFlag("corrida-paraglider-arco");
    public static StateFlag CAPTUREBANDEIRA_SAFEZONE = new StateFlag("capture-bandeira-safezone", false);

    public static void Registry() {
        FlagRegistry registry = com.sk89q.worldguard.WorldGuard.getInstance().getFlagRegistry();
        try {
            registry.register(MORTE);
            registry.register(CORRIDA_FINAL);
            registry.register(CORRIDA_BOAT_FINAL);
            registry.register(CORRIDA_PARAGLIDER_FINAL);
            registry.register(CORRIDA_PARAGLIDER_CHECKPOINT);
            registry.register(CORRIDA_PARAGLIDER_ARCO);
            registry.register(CAPTUREBANDEIRA_SAFEZONE);
            Bukkit.getLogger().info("[MineSkyEvents] Flags registradas!");
        } catch (FlagConflictException e) {
            Flag<?> existingM = registry.get("morte");
            Flag<?> existingC = registry.get("corrida-final");
            Flag<?> existingB = registry.get("corrida-boat_-final");
            Flag<?> existingP = registry.get("corrida-paraglider-final");
            Flag<?> existingPC = registry.get("corrida-paraglider-checkpoint");
            Flag<?> existingPA = registry.get("corrida-paraglider-arco");
            Flag<?> existingCB = registry.get("capture-bandeira-safezone");
            if (existingM instanceof StateFlag) {
                MORTE = (StateFlag) existingM;
                return;
            }
            if (existingC instanceof StateFlag) {
                CORRIDA_FINAL = (StateFlag) existingC;
                return;
            }
            if (existingB instanceof StateFlag) {
                CORRIDA_BOAT_FINAL = (StateFlag) existingB;
                return;
            }
            if (existingP instanceof StateFlag) {
                CORRIDA_PARAGLIDER_FINAL = (StateFlag) existingP;
                return;
            }
            if (existingPC instanceof IntegerFlag) {
                CORRIDA_PARAGLIDER_CHECKPOINT = (IntegerFlag) existingPC;
                return;
            }
            if (existingPA instanceof IntegerFlag) {
                CORRIDA_PARAGLIDER_ARCO = (IntegerFlag) existingPA;
                return;
            }
            if (existingCB instanceof StateFlag) {
                CAPTUREBANDEIRA_SAFEZONE = (StateFlag) existingCB;
                return;
            }
            Bukkit.getLogger().warning("[MineSky-Events] Conflito de flag detectado: " + e.getMessage());
        }
    }
}
