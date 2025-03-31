package net.minesky.config;


import net.minesky.MineSkyEvents;
import net.minesky.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;

public class DataManager {
    public static void createFolder(String folder) {
        try {
            File pasta = new File(MineSkyEvents.get().getDataFolder() + File.separator + folder);
            if (!pasta.exists()) {
                pasta.mkdirs();
                MineSkyEvents.l.info("Criando a pasta " + folder );
            }
        } catch (Throwable e) {
            Bukkit.getConsoleSender().sendMessage("§cNão consegui carregar a pasta " + folder);
            e.printStackTrace();
        }
    }

    public static void createFile(File file) {
        try {
            MineSkyEvents.l.info("Criando a config " + file );
            file.createNewFile();
        } catch (Throwable e) {
            Bukkit.getConsoleSender().sendMessage("§cNão consegui carregar o arquivo " + file.getName());
            e.printStackTrace();
        }
    }

    public static File getFolder(String folder) {
        return new File(MineSkyEvents.get().getDataFolder() + File.separator + folder);
    }

    public static File getFile(String file, String folder) {
        return new File(MineSkyEvents.get().getDataFolder() + File.separator + folder, file);
    }

    public static File getFile(String file) {
        return new File(MineSkyEvents.get().getDataFolder() + File.separator + file);
    }

    public static FileConfiguration getConfiguration(File file) {
        return YamlConfiguration.loadConfiguration(file);
    }

    public static void deleteFile(File file) {
        file.delete();
    }
}
