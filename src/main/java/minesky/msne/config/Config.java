package minesky.msne.config;

import org.bukkit.configuration.file.FileConfiguration;

public class Config {
    public static String UltimoDia;
    public static boolean Bot;
    public static String Token;
    public static String ChannelLog;

    public static void loadConfig() {
        FileConfiguration config = ConfigManager.getConfig("config.yml");
        FileConfiguration messages = ConfigManager.getConfig("messages.yml");
        UltimoDia = getString(config, "UltimoDia");
        Bot = config.getBoolean("bot.enabled");
        Token = getString(config, "bot.token");
        ChannelLog = getString(config, "bot.channel_logs");
    }

    private static String getString(FileConfiguration config, String path) {
        return config.getString(path, "§cNão foi possivel localizar a mensagem '§e" + path + "§c' do arquivo §nconfig.yml§c.").replace('&', '§');
    }
}
