package minesky.msne.utils;

import org.bukkit.Bukkit;

public class SystemInfo {
    public static String getMinecraftVersion() {
        try {
            String info = Bukkit.getVersion();
            return info.split("MC: ")[1].split("\\)")[0];
        } catch (Throwable e) {
            return "Desconhecida";
        }
    }
}
