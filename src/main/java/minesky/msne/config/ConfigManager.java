package minesky.msne.config;

import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

import minesky.msne.MineSkyEvents;
import minesky.msne.utils.Util;
import org.bukkit.Bukkit;
import org.bukkit.configuration.Configuration;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

public class ConfigManager {
    public static YamlConfiguration yml;
    public static void createConfig(String file) {
        if (!new File(MineSkyEvents.get().getDataFolder(), file).exists()) {
            Util.InfoC("Creating a " + file + " config...");
            MineSkyEvents.get().saveResource(file, false);
        }
    }

    public static void createConfigFolder(String file, String folder) {
        File dataFolder = new File(MineSkyEvents.get().getDataFolder(), folder);
        if (!dataFolder.exists()) {
            Bukkit.getConsoleSender().sendMessage("§8[§cSecurityX§8] §cCreating a " + folder + " folder...");
            dataFolder.mkdirs();
        }
        File configFolder = new File(dataFolder, file);
        yml = YamlConfiguration.loadConfiguration(configFolder);
        if (!configFolder.exists()) {
            InputStreamReader defConfigFolder = new InputStreamReader(MineSkyEvents.get().getResource(file));
            YamlConfiguration defaultConfig = YamlConfiguration.loadConfiguration(defConfigFolder);
            yml.setDefaults((Configuration) defaultConfig);
            try {
                Util.InfoC("Creating a " + file + " config...");
                defaultConfig.save(configFolder);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static FileConfiguration getConfig(String file) {
        File arquivo = new File(MineSkyEvents.get().getDataFolder() + File.separator + file);
        return YamlConfiguration.loadConfiguration(arquivo);
    }

    public static FileConfiguration getConfigFolder(String file, String folder) {
        File dataFolder = new File(MineSkyEvents.get().getDataFolder(), folder);
        File configFolder = new File(dataFolder, file);
        return YamlConfiguration.loadConfiguration(configFolder);
    }
}
