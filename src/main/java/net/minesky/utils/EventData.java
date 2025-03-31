package net.minesky.utils;

import net.minesky.MineSkyEvents;
import net.minesky.config.Locations;

import net.minesky.events.*;
import org.bukkit.Location;

public class EventData {
    private static String selectedMap;
    public  static boolean ForceStart = false;
    public static boolean ForceSkip = false;
    public static Location getEventLocation(String event) {
        switch (event) {
            case "Spleef": return Locations.spleef;
            case "TijolãoWars": return getTijolãoWarsLocation();
            case "Corrida": return Locations.corrida;
            case "CorridaBoat": return Locations.corridaboat;
            case "Sumo": return Locations.sumo;
            case "TNTRun": return getTNTRunLocation();
            case "TNTTag": return Locations.tnttag;
            case "Parapente": return Locations.parapente;
            case "Mini-Wars": return getMiniWarsLocation();
            case "CopaPVP": return Locations.copapvp;
            case "Esconde-esconde": return getEscondeEscondeLocation();
            case "Ruínas": return Locations.ruinas;
            default: return null;
        }
    }

    private static Location getTijolãoWarsLocation() {
        switch (selectedMap) {
            case "Mapa-1": return Locations.tijolao;
            case "Mapa-2": return Locations.tijolao2;
            default: return null;
        }
    }

    private static Location getTNTRunLocation() {
        switch (selectedMap) {
            case "Mapa-1": return Locations.tntrun;
            case "Mapa-2": return Locations.tntrun2;
            default: return null;
        }
    }

    private static Location getMiniWarsLocation() {
        switch (selectedMap) {
            case "Mapa-1": return Locations.miniwars;
            case "Mapa-2": return Locations.miniwars2;
            case "Mapa-3": return Locations.miniwars3;
            case "Mapa-4": return Locations.miniwars4;
            case "Mapa-5": return Locations.miniwars5;
            default: return null;
        }
    }

    private static Location getEscondeEscondeLocation() {
        switch (selectedMap) {
            case "Mapa-1": return Locations.esconde;
            case "Mapa-2": return Locations.esconde2;
            default: return null;
        }
    }

    public static Location getEventArenaLocation(String event) {
        switch (event) {
            case "Spleef": return Locations.spleefA;
            case "TijolãoWars": return getTijolãoWarsArenaLocation();
            case "Corrida": return Locations.corridaA;
            case "CorridaBoat": return Locations.corridaboatA;
            case "Sumo": return Locations.sumoA;
            case "TNTRun": return getTNTRunArenaLocation();
            case "TNTTag": return Locations.tnttagA;
            case "Parapente": return Locations.parapenteA;
            default: return null;
        }
    }

    private static Location getTijolãoWarsArenaLocation() {
        switch (selectedMap) {
            case "Mapa-1": return Locations.tijolaoA;
            case "Mapa-2": return Locations.tijolao2A;
            default: return null;
        }
    }

    private static Location getTNTRunArenaLocation() {
        switch (selectedMap) {
            case "Mapa-1": return Locations.tntrunA;
            case "Mapa-2": return Locations.tntrun2A;
            default: return null;
        }
    }

    public static boolean isSpectator(String event) {
        switch (event) {
            case "Spleef": if (!SpleefEvent.contagem && SpleefEvent.contagemI) {
                return true;
            }
            break;
            case "TijolãoWars": if (!TijolãoWarsEvent.contagem && TijolãoWarsEvent.contagemI) {
                return true;
            }
            break;
            case "Sumo": if (!SumoEvent.contagem && SumoEvent.contagemI) {
                return true;
            }
            break;
            case "Corrida": if (!CorridaEvent.contagem && CorridaEvent.contagemI) {
                return true;
            }
            case "TNTTag": if (!TNTTagEvent.contagem && TNTTagEvent.contagemI) {
                return true;
            }
            break;
        }
        return false;
    }

    public static boolean isEventScheduled(String event) {
        return event.equals("Mini-Wars") || event.equals("CopaPVP") || event.equals("Esconde-esconde") || event.equals("Ruínas");
    }

    public static void ContagemEvent() {
        switch (MineSkyEvents.event.toLowerCase()) {
            case "spleef": if (!SpleefEvent.contagemI) SpleefEvent.comtagemEvento(); return;
            case "tijolãowars": if (!TijolãoWarsEvent.contagemI) TijolãoWarsEvent.comtagemEvento(); return;
            case "corrida": if (!CorridaEvent.contagemI) CorridaEvent.comtagemEvento(); return;
            case "sumo": if (!SumoEvent.contagemI) SumoEvent.comtagemEvento(); return;
            case "tnttag": if (!TNTTagEvent.contagemI) TNTTagEvent.comtagemEvento(); return;
        }
    }
    public static void setSelectedMap(String getselectedMap) {
        selectedMap = getselectedMap;
    }

    public static void setForceStart() {
        ForceStart = true;
    }

    public static void setForceSkip() {
        ForceSkip = true;
    }
}
