package net.minesky.config;

import org.bukkit.configuration.file.FileConfiguration;

public class Config {
    public static String Link;
    public static int SPLEEF_MIN_1;
    public static int SPLEEF_MAX_1;
    public static int SPLEEF_MIN_2;
    public static int SPLEEF_MAX_2;
    public static int SPLEEF_MIN_3;
    public static int SPLEEF_MAX_3;
    public static int Tijolao_MIN_1;
    public static int Tijolao_MAX_1;
    public static int Tijolao_MIN_2;
    public static int Tijolao_MAX_2;
    public static int Tijolao_MIN_3;
    public static int Tijolao_MAX_3;
    public static int CORRIDA_MIN_1;
    public static int CORRIDA_MAX_1;
    public static int CORRIDA_MIN_2;
    public static int CORRIDA_MAX_2;
    public static int CORRIDA_MIN_3;
    public static int CORRIDA_MAX_3;
    public static int CORRIDABOAT_MIN_1;
    public static int CORRIDABOAT_MAX_1;
    public static int CORRIDABOAT_MIN_2;
    public static int CORRIDABOAT_MAX_2;
    public static int CORRIDABOAT_MIN_3;
    public static int CORRIDABOAT_MAX_3;
    public static int SUMO_MIN_1;
    public static int SUMO_MAX_1;
    public static int SUMO_MIN_2;
    public static int SUMO_MAX_2;
    public static int SUMO_MIN_3;
    public static int SUMO_MAX_3;
    public static int TNTRUN_MIN_1;
    public static int TNTRUN_MAX_1;
    public static int TNTRUN_MIN_2;
    public static int TNTRUN_MAX_2;
    public static int TNTRUN_MIN_3;
    public static int TNTRUN_MAX_3;
    public static int TNTTAG_MIN_1;
    public static int TNTTAG_MAX_1;
    public static int PARAPENTE_MIN_1;
    public static int PARAPENTE_MAX_1;
    public static int PARAPENTE_MIN_2;
    public static int PARAPENTE_MAX_2;
    public static int PARAPENTE_MIN_3;
    public static int PARAPENTE_MAX_3;

    public static void loadConfig() {
        FileConfiguration config = ConfigManager.getConfig("config.yml");
        FileConfiguration messages = ConfigManager.getConfig("messages.yml");
        Link = getString(config, "Webhook.link");
        SPLEEF_MIN_1 = config.getInt("Spleef.1-ganhador.min");
        SPLEEF_MIN_1 = config.getInt("Spleef.1-ganhador.max");
        SPLEEF_MIN_2 = config.getInt("Spleef.2-ganhador.min");
        SPLEEF_MAX_2 = config.getInt("Spleef.2-ganhador.max");
        SPLEEF_MIN_3 = config.getInt("Spleef.3-ganhador.min");
        SPLEEF_MAX_3 = config.getInt("Spleef.3-ganhador.max");
        Tijolao_MIN_1 = config.getInt("TijolãoWars.1-ganhador.min");
        Tijolao_MAX_1 = config.getInt("TijolãoWars.1-ganhador.max");
        Tijolao_MIN_2 = config.getInt("TijolãoWars.2-ganhador.min");
        Tijolao_MAX_2 = config.getInt("TijolãoWars.2-ganhador.max");
        Tijolao_MIN_3 = config.getInt("TijolãoWars.3-ganhador.min");
        Tijolao_MAX_3 = config.getInt("TijolãoWars.3-ganhador.max");
        CORRIDA_MIN_1 = config.getInt("Corrida.1-ganhador.min");
        CORRIDA_MAX_1 = config.getInt("Corrida.1-ganhador.max");
        CORRIDA_MIN_2 = config.getInt("Corrida.2-ganhador.min");
        CORRIDA_MAX_2 = config.getInt("Corrida.2-ganhador.max");
        CORRIDA_MIN_3 = config.getInt("Corrida.3-ganhador.min");
        CORRIDA_MAX_3 = config.getInt("Corrida.3-ganhador.max");
        CORRIDABOAT_MIN_1 = config.getInt("CorridaBoat.1-ganhador.min");
        CORRIDABOAT_MAX_1 = config.getInt("CorridaBoat.1-ganhador.max");
        CORRIDABOAT_MIN_2 = config.getInt("CorridaBoat.2-ganhador.min");
        CORRIDABOAT_MAX_2 = config.getInt("CorridaBoat.2-ganhador.max");
        CORRIDABOAT_MIN_3 = config.getInt("CorridaBoat.3-ganhador.min");
        CORRIDABOAT_MAX_3 = config.getInt("CorridaBoat.3-ganhador.max");
        SUMO_MIN_1 = config.getInt("Sumo.1-ganhador.min");
        SUMO_MAX_1 = config.getInt("Sumo.1-ganhador.max");
        SUMO_MIN_2 = config.getInt("Sumo.2-ganhador.min");
        SUMO_MAX_2 = config.getInt("Sumo.2-ganhador.max");
        SUMO_MIN_3 = config.getInt("Sumo.3-ganhador.min");
        SUMO_MAX_3 = config.getInt("Sumo.3-ganhador.max");
        TNTRUN_MIN_1 = config.getInt("TNTRun.1-ganhador.min");
        TNTRUN_MAX_1 = config.getInt("TNTRun.1-ganhador.max");
        TNTRUN_MIN_2 = config.getInt("TNTRun.2-ganhador.min");
        TNTRUN_MAX_2 = config.getInt("TNTRun.2-ganhador.max");
        TNTRUN_MIN_3 = config.getInt("TNTRun.3-ganhador.min");
        TNTRUN_MAX_3 = config.getInt("TNTRun.3-ganhador.max");
        TNTTAG_MIN_1 = config.getInt("TNTTag.1-ganhador.min");
        TNTTAG_MAX_1 = config.getInt("TNTTag.1-ganhador.max");
        PARAPENTE_MIN_1 = config.getInt("Parapente.1-ganhador.min");
        PARAPENTE_MAX_1 = config.getInt("Parapente.1-ganhador.max");
        PARAPENTE_MIN_2 = config.getInt("Parapente.2-ganhador.min");
        PARAPENTE_MAX_2 = config.getInt("Parapente.2-ganhador.max");
        PARAPENTE_MIN_3 = config.getInt("Parapente.3-ganhador.min");
        PARAPENTE_MAX_3 = config.getInt("Parapente.3-ganhador.max");
    }

    private static String getString(FileConfiguration config, String path) {
        return config.getString(path, "§cNão foi possivel localizar a mensagem '§e" + path + "§c' do arquivo §nconfig.yml§c.").replace('&', '§');
    }
}
