package minesky.msne.config;

import org.bukkit.configuration.file.FileConfiguration;

public class Messages {
    public static String No_permission;
    public static String No_console;
    public static String Player_Offline;
    public static String Player_exist;
    public static String Falied_to_create_the_file;
    public static String Falied_to_create_the_folder;
    public static String Falied_to_save;
    public static String Invalid_character;
    public static String Similar_nickname;
    public static String Join;
    public static String Quit;
    public static String Blacklist;

    public static void loadMessages() {
        FileConfiguration config = ConfigManager.getConfig("messages.yml");
        No_permission = getString(config, "no_permission");
        No_console = getString(config, "no_console");
        Player_Offline = getString(config, "player_offline");
        Player_exist = getString(config, "player_no_exist");
        Falied_to_create_the_file = getString(config, "falied_to_create_the_file");
        Falied_to_create_the_folder = getString(config, "falied_to_create_the_folder");
        Falied_to_save = getString(config, "falied_to_save");
        Invalid_character = getString(config, "invalid_character");
        Similar_nickname = getString(config, "similar_nickname");
        Join = getString(config, "join");
        Quit = getString(config, "quit");
        Blacklist = getString(config, "blacklist");
    }

    private static String getString(FileConfiguration config, String path) {
        return config.getString(path, "§cNão foi possivel localizar a mensagem '§e" + path + "§c' do arquivo §nmessages.yml§c.").replace('&', '§');
    }
}
