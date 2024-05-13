package minesky.msne.config;

import org.bukkit.configuration.file.FileConfiguration;

public class Config {
    public static String Link;

    public static void loadConfig() {
        FileConfiguration config = ConfigManager.getConfig("config.yml");
        FileConfiguration messages = ConfigManager.getConfig("messages.yml");
        Link = getString(config, "Webhook.link");
    }

    private static String getString(FileConfiguration config, String path) {
        return config.getString(path, "§cNão foi possivel localizar a mensagem '§e" + path + "§c' do arquivo §nconfig.yml§c.").replace('&', '§');
    }
}
