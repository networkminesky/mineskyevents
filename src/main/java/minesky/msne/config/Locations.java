package minesky.msne.config;

import minesky.msne.MineSkyEvents;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.scheduler.BukkitRunnable;

public class Locations {
    private static Location padrao = new Location(Bukkit.getWorlds().get(0), 1.0, 250.0, 1.0, 0, 0);
    public static Location tijolao = padrao;
    public static Location tijolao2 = padrao;
    public static Location runners = padrao;
    public static Location corrida = padrao;
    public static Location corridaboat = padrao;
    public static Location sumo = padrao;
    public static Location miniwars = padrao;
    public static Location copapvp = padrao;
    public static Location esconde = padrao;
    public static Location tijolaoA = padrao;
    public static Location tijolao2A = padrao;
    public static Location runnersA = padrao;
    public static Location corridaA = padrao;
    public static Location corridaboatA = padrao;
    public static Location sumoA = padrao;

    public static void loadLocations() {
        setLocations();
        validarLocations(true);
    }

    private static void setLocations() {
        FileConfiguration config = ConfigManager.getConfig("locations.yml");
        if (config == null) return;

        tijolao = getLocationFromConfig(config, "Tijolão");
        tijolao2 = getLocationFromConfig(config, "Tijolão2");
        runners = getLocationFromConfig(config, "Runners");
        corrida = getLocationFromConfig(config, "Corrida");
        corridaboat = getLocationFromConfig(config, "CorridaBoat");
        sumo = getLocationFromConfig(config, "Sumo");
        tijolaoA = getLocationFromConfig(config, "arena.Tijolão");
        tijolao2A = getLocationFromConfig(config, "arena.Tijolão2");
        runnersA = getLocationFromConfig(config, "arena.Runners");
        corridaA = getLocationFromConfig(config, "arena.Corrida");
        corridaboatA = getLocationFromConfig(config, "arena.CorridaBoat");
        sumoA = getLocationFromConfig(config, "arena.Sumo");
    }

    private static Location getLocationFromConfig(FileConfiguration config, String path) {
        if (!config.contains(path + ".world")) return padrao;

        World world = Bukkit.getWorld(config.getString(path + ".world"));
        double x = config.getDouble(path + ".x");
        double y = config.getDouble(path + ".y");
        double z = config.getDouble(path + ".z");
        float yaw = (float) config.getDouble(path + ".yaw");
        float pitch = (float) config.getDouble(path + ".pitch");

        return new Location(world, x, y, z, yaw, pitch);
    }


    private static void validarLocations(boolean reavaliar) {
        boolean tentarValidarNovamente = false;

        if (tijolao == null || tijolao.getWorld() == null) {
            tentarValidarNovamente = true;
            tijolao = padrao;
            if (!reavaliar) {
                Bukkit.getConsoleSender().sendMessage("§8[§bMineSky Events§8] §cNão foi possivel carregar a localizacão do Tijolão Wars! Entre no jogo e use §e/event set Tijolão spawn");
            }
        }

        if (runners == null || runners.getWorld() == null) {
            tentarValidarNovamente = true;
            runners = padrao;
            if (!reavaliar) {
                Bukkit.getConsoleSender().sendMessage("§8[§bMineSky Events§8] §cNão foi possivel carregar a localizacão do Runners! Entre no jogo e use §e/event set Runners spawn");
            }
        }

        if (corrida == null || corrida.getWorld() == null) {
            tentarValidarNovamente = true;
            corrida = padrao;
            if (!reavaliar) {
                Bukkit.getConsoleSender().sendMessage("§8[§bMineSky Events§8] §cNão foi possivel carregar a localizacão do Corrida! Entre no jogo e use §e/event set Corrida spawn");
            }
        }

        if (corridaboat == null || corridaboat.getWorld() == null) {
            tentarValidarNovamente = true;
            corridaboat = padrao;
            if (!reavaliar) {
                Bukkit.getConsoleSender().sendMessage("§8[§bMineSky Events§8] §cNão foi possivel carregar a localizacão do CorridaBoat! Entre no jogo e use §e/event set CorridaBoat spawn");
            }
        }

        if (sumo == null || sumo.getWorld() == null) {
            tentarValidarNovamente = true;
            sumo = padrao;
            if (!reavaliar) {
                Bukkit.getConsoleSender().sendMessage("§8[§bMineSky Events§8] §cNão foi possivel carregar a localizacão do Sumo! Entre no jogo e use §e/event set Sumo spawn");
            }
        }

        if (miniwars == null || miniwars.getWorld() == null) {
            tentarValidarNovamente = true;
            miniwars = padrao;
            if (!reavaliar) {
                Bukkit.getConsoleSender().sendMessage("§8[§bMineSky Events§8] §cNão foi possivel carregar a localizacão do MiniWars! Entre no jogo e use §e/event set MiniWars spawn");
            }
        }

        if (copapvp == null || copapvp.getWorld() == null) {
            tentarValidarNovamente = true;
            copapvp = padrao;
            if (!reavaliar) {
                Bukkit.getConsoleSender().sendMessage("§8[§bMineSky Events§8] §cNão foi possivel carregar a localizacão do CopaPVP! Entre no jogo e use §e/event set CopaPVP spawn");
            }
        }

        if (esconde == null || esconde.getWorld() == null) {
            tentarValidarNovamente = true;
            esconde = padrao;
            if (!reavaliar) {
                Bukkit.getConsoleSender().sendMessage("§8[§bMineSky Events§8] §cNão foi possivel carregar a localizacão do Esconde-esconde! Entre no jogo e use §e/event set Esconde-esconde spawn");
            }
        }

        if (tijolaoA == null || tijolaoA.getWorld() == null) {
            tentarValidarNovamente = true;
            tijolaoA = padrao;
            if (!reavaliar) {
                Bukkit.getConsoleSender().sendMessage("§8[§bMineSky Events§8] §cNão foi possivel carregar a localizacão do Tijolão Wars! Entre no jogo e use §e/event set Tijolão arena");
            }
        }

        if (tijolao2A == null || tijolao2A.getWorld() == null) {
            tentarValidarNovamente = true;
            tijolao2A = padrao;
            if (!reavaliar) {
                Bukkit.getConsoleSender().sendMessage("§8[§bMineSky Events§8] §cNão foi possivel carregar a localizacão do Tijolão Wars 2! Entre no jogo e use §e/event set Tijolão2 arena");
            }
        }

        if (runnersA == null || runnersA.getWorld() == null) {
            tentarValidarNovamente = true;
            runnersA = padrao;
            if (!reavaliar) {
                Bukkit.getConsoleSender().sendMessage("§8[§bMineSky Events§8] §cNão foi possivel carregar a localizacão do Runners! Entre no jogo e use §e/event set Runners arena");
            }
        }

        if (corridaA == null || corridaA.getWorld() == null) {
            tentarValidarNovamente = true;
            corridaA = padrao;
            if (!reavaliar) {
                Bukkit.getConsoleSender().sendMessage("§8[§bMineSky Events§8] §cNão foi possivel carregar a localizacão do Corrida! Entre no jogo e use §e/event set Corrida arena");
            }
        }

        if (corridaboatA == null || corridaboatA.getWorld() == null) {
            tentarValidarNovamente = true;
            corridaboatA = padrao;
            if (!reavaliar) {
                Bukkit.getConsoleSender().sendMessage("§8[§bMineSky Events§8] §cNão foi possivel carregar a localizacão do CorridaBoat! Entre no jogo e use §e/event set CorridaBoat arena");
            }
        }

        if (sumoA == null || sumoA.getWorld() == null) {
            tentarValidarNovamente = true;
            sumoA = padrao;
            if (!reavaliar) {
                Bukkit.getConsoleSender().sendMessage("§8[§bMineSky Events§8] §cNão foi possivel carregar a localizacão do Sumo! Entre no jogo e use §e/event set Sumo arena");
            }
        }

        if (tentarValidarNovamente && reavaliar) tentarValidarNovamente();
    }

    private static void tentarValidarNovamente() {
        new BukkitRunnable() {
            @Override
            public void run() {
                setLocations();
                validarLocations(false);
            }
        }.runTaskLater(MineSkyEvents.get(), 20L * 25L);
    }
}