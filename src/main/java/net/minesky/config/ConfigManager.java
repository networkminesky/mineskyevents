package net.minesky.config;

import net.minesky.MineSkyEvents;
import net.minesky.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Objects;

public class ConfigManager {
    public static YamlConfiguration yml;
    public static void createConfig(String file) {
        if (!new File(MineSkyEvents.get().getDataFolder(), file).exists()) {
            MineSkyEvents.l.info("Criando a config " + file );
            MineSkyEvents.get().saveResource(file, false);
        }
    }

    public static void createConfigFolder(String file, String folder) {
        File dataFolder = new File(MineSkyEvents.get().getDataFolder(), folder);
        if (!dataFolder.exists()) {
            MineSkyEvents.l.info("Criando a pasta " + folder );
            dataFolder.mkdirs();
        }
        File configFolder = new File(dataFolder, file);
        yml = YamlConfiguration.loadConfiguration(configFolder);
        if (!configFolder.exists()) {
            InputStreamReader defConfigFolder = new InputStreamReader(Objects.requireNonNull(MineSkyEvents.get().getResource(file)));
            YamlConfiguration defaultConfig = YamlConfiguration.loadConfiguration(defConfigFolder);
            yml.setDefaults(defaultConfig);
            try {
                MineSkyEvents.l.info("Criando a config " + file );
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

    public static void createConfigs(String... file) {
        Arrays.stream(file).forEach(ConfigManager::createConfig);
    }
}
