package net.minesky.config;

import net.minesky.MineSkyEvents;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.scheduler.BukkitRunnable;

public class Locations {
    private static Location padrao = new Location(Bukkit.getWorlds().get(0), 1.0, 250.0, 1.0, 0, 0);
    public static Location spleef = padrao;
    public static Location tijolao = padrao;
    public static Location tijolao2 = padrao;
    public static Location runners = padrao;
    public static Location corrida = padrao;
    public static Location corridaboat = padrao;
    public static Location sumo = padrao;
    public static Location tntrun = padrao;
    public static Location tntrun2 = padrao;
    public static Location tnttag = padrao;
    public static Location parapente = padrao;
    public static Location miniwars = padrao;
    public static Location miniwars2 = padrao;
    public static Location miniwars3 = padrao;
    public static Location miniwars4 = padrao;
    public static Location miniwars5 = padrao;
    public static Location copapvp = padrao;
    public static Location esconde = padrao;
    public static Location esconde2 = padrao;
    public static Location ruinas = padrao;
    public static Location spleefA = padrao;
    public static Location tijolaoA = padrao;
    public static Location tijolao2A = padrao;
    public static Location runnersA = padrao;
    public static Location corridaA = padrao;
    public static Location corridaboatA = padrao;
    public static Location sumoA = padrao;
    public static Location tntrunA = padrao;
    public static Location tntrun2A = padrao;
    public static Location tnttagA = padrao;
    public static Location parapenteA = padrao;
    public static Location parapenteC1 = padrao;
    public static Location parapenteC2 = padrao;
    public static Location parapenteC3 = padrao;

    public static void loadLocations() {
        setLocations();
        validarLocations(true);
    }

    private static void setLocations() {
        FileConfiguration config = ConfigManager.getConfig("locations.yml");
        if (config == null) return;

        spleef = getLocationFromConfig(config, "spleef.1");
        tijolao = getLocationFromConfig(config, "tijolaowars.1");
        tijolao2 = getLocationFromConfig(config, "tijolaowars.2");
        runners = getLocationFromConfig(config, "runners.1");
        corrida = getLocationFromConfig(config, "corrida.1");
        corridaboat = getLocationFromConfig(config, "corridaboat.1");
        sumo = getLocationFromConfig(config, "sumo.1");
        tntrun = getLocationFromConfig(config, "tntrun.1");
        tntrun2 = getLocationFromConfig(config, "tntrun.2");
        tnttag = getLocationFromConfig(config, "tnttag.1");
        parapente = getLocationFromConfig(config, "parapente.1");
        miniwars = getLocationFromConfig(config, "mini-wars.1");
        miniwars2 = getLocationFromConfig(config, "mini-wars.2");
        miniwars3 = getLocationFromConfig(config, "mini-wars.3");
        miniwars4 = getLocationFromConfig(config, "mini-wars.4");
        miniwars5 = getLocationFromConfig(config, "mini-wars.5");
        copapvp = getLocationFromConfig(config, "copapvp.1");
        esconde = getLocationFromConfig(config, "esconde-esconde.1");
        esconde2 = getLocationFromConfig(config, "esconde-esconde.2");
        ruinas = getLocationFromConfig(config, "ruínas.1");
        spleefA = getLocationFromConfig(config, "arena.spleef.1");
        tijolaoA = getLocationFromConfig(config, "arena.tijolaowars.1");
        tijolao2A = getLocationFromConfig(config, "arena.tijolaowars.2");
        runnersA = getLocationFromConfig(config, "arena.runners.1");
        corridaA = getLocationFromConfig(config, "arena.corrida.1");
        corridaboatA = getLocationFromConfig(config, "arena.corridaboat.1");
        sumoA = getLocationFromConfig(config, "arena.sumo.1");
        tntrunA = getLocationFromConfig(config, "arena.tntrun.1");
        tntrun2A = getLocationFromConfig(config, "arena.tntrun.2");
        tnttagA = getLocationFromConfig(config, "arena.tnttag.1");
        parapenteA = getLocationFromConfig(config, "arena.parapente.1");
        parapenteC1 = getLocationFromConfig(config, "arena.parapente.1.checkpoint.1");
        parapenteC2 = getLocationFromConfig(config, "arena.parapente.1.checkpoint.2");
        parapenteC3 = getLocationFromConfig(config, "arena.parapente.1.checkpoint.3");

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

        if (spleef == null || spleef.getWorld() == null) {
            tentarValidarNovamente = true;
            spleef = padrao;
            if (!reavaliar) {
                Bukkit.getConsoleSender().sendMessage("§8[§bMineSky Events§8] §cNão foi possivel carregar a localizacão do Spleef! Entre no jogo e use §e/event set Tijolão spawn");
            }
        }

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

        if (tntrun == null || tntrun.getWorld() == null) {
            tentarValidarNovamente = true;
            tntrun = padrao;
            if (!reavaliar) {
                Bukkit.getConsoleSender().sendMessage("§8[§bMineSky Events§8] §cNão foi possivel carregar a localizacão do Sumo! Entre no jogo e use §e/event set Sumo spawn");
            }
        }

        if (tntrun2 == null || tntrun2.getWorld() == null) {
            tentarValidarNovamente = true;
            tntrun2 = padrao;
            if (!reavaliar) {
                Bukkit.getConsoleSender().sendMessage("§8[§bMineSky Events§8] §cNão foi possivel carregar a localizacão do Sumo! Entre no jogo e use §e/event set Sumo spawn");
            }
        }

        if (tnttag == null || tnttag.getWorld() == null) {
            tentarValidarNovamente = true;
            tnttag = padrao;
            if (!reavaliar) {
                Bukkit.getConsoleSender().sendMessage("§8[§bMineSky Events§8] §cNão foi possivel carregar a localizacão do Sumo! Entre no jogo e use §e/event set Sumo spawn");
            }
        }

        if (parapente == null || parapente.getWorld() == null) {
            tentarValidarNovamente = true;
            parapente = padrao;
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

        if (spleefA == null || spleefA.getWorld() == null) {
            tentarValidarNovamente = true;
            spleefA = padrao;
            if (!reavaliar) {
                Bukkit.getConsoleSender().sendMessage("§8[§bMineSky Events§8] §cNão foi possivel carregar a localizacão do Spleef Entre no jogo e use §e/event set Tijolão arena");
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

        if (tntrunA == null || tntrunA.getWorld() == null) {
            tentarValidarNovamente = true;
            tntrunA = padrao;
            if (!reavaliar) {
                Bukkit.getConsoleSender().sendMessage("§8[§bMineSky Events§8] §cNão foi possivel carregar a localizacão do Sumo! Entre no jogo e use §e/event set Sumo arena");
            }
        }

        if (tntrun2A == null || tntrun2A.getWorld() == null) {
            tentarValidarNovamente = true;
            tntrun2A = padrao;
            if (!reavaliar) {
                Bukkit.getConsoleSender().sendMessage("§8[§bMineSky Events§8] §cNão foi possivel carregar a localizacão do Sumo! Entre no jogo e use §e/event set Sumo arena");
            }
        }

        if (tnttagA == null || tnttagA.getWorld() == null) {
            tentarValidarNovamente = true;
            tnttagA = padrao;
            if (!reavaliar) {
                Bukkit.getConsoleSender().sendMessage("§8[§bMineSky Events§8] §cNão foi possivel carregar a localizacão do Sumo! Entre no jogo e use §e/event set Sumo arena");
            }
        }

        if (parapenteA == null || parapenteA.getWorld() == null) {
            tentarValidarNovamente = true;
            parapenteA = padrao;
            if (!reavaliar) {
                Bukkit.getConsoleSender().sendMessage("§8[§bMineSky Events§8] §cNão foi possivel carregar a localizacão do arena do Parapente! Entre no jogo e use §e/event set Sumo arena");
            }
        }

        if (parapenteC1 == null || parapenteC1.getWorld() == null) {
            tentarValidarNovamente = true;
            parapenteC1 = padrao;
            if (!reavaliar) {
                Bukkit.getConsoleSender().sendMessage("§8[§bMineSky Events§8] §cNão foi possivel carregar a localizacão do Parapente (CheckPoint 1)! Entre no jogo e use §e/event set Sumo arena");
            }
        }

        if (parapenteC2 == null || parapenteC2.getWorld() == null) {
            tentarValidarNovamente = true;
            parapenteC2 = padrao;
            if (!reavaliar) {
                Bukkit.getConsoleSender().sendMessage("§8[§bMineSky Events§8] §cNão foi possivel carregar a localizacão do Parapente (CheckPoint 2)! Entre no jogo e use §e/event set Sumo arena");
            }
        }

        if (parapenteC3 == null || parapenteC3.getWorld() == null) {
            tentarValidarNovamente = true;
            parapenteC3 = padrao;
            if (!reavaliar) {
                Bukkit.getConsoleSender().sendMessage("§8[§bMineSky Events§8] §cNão foi possivel carregar a localizacão do Parapente (CheckPoint 3)! Entre no jogo e use §e/event set Sumo arena");
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