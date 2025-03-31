package net.minesky;

import net.minesky.commands.*;
import net.minesky.hooks.Vault;
import net.minesky.hooks.WorldGuard;
import net.minesky.config.*;
import net.minesky.enums.JarType;
import net.minesky.enums.Version;
import net.minesky.system.*;
import net.minesky.system.event.*;
import net.minesky.utils.Command;
import net.minesky.utils.EventItem;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Logger;

public final class MineSkyEvents extends JavaPlugin {

    public static FileConfiguration Blacklist;
    public static Logger l;
    public static String event = "OFF";
    private static Version version;
    private static JarType jarType;

    @Override
    public void onLoad() {
        WorldGuard.Registry();
    }

    @Override
    public void onEnable() {
        l = this.getLogger();
        l.info("    __  ___    _                           __               ______                        __         ");
        l.info("   /  |/  /   (_)   ____   ___    _____   / /__   __  __   / ____/ _   __  ___    ____   / /_   _____");
        l.info("  / /|_/ /   / /   / __ \\ / _ \\  / ___/  / //_/  / / / /  / __/   | | / / / _ \\  / __ \\ / __/  / ___/");
        l.info(" / /  / /   / /   / / / //  __/ (__  )  / ,<    / /_/ /  / /___   | |/ / /  __/ / / / // /_   (__  )");
        l.info("/_/  /_/   /_/   /_/ /_/ \\___/ /____/  /_/|_|   \\__, /  /_____/   |___/  \\___/ /_/ /_/ \\__/  /____/  ");
        l.info("                                               /____/");
        l.info("");
        l.info("Carregando plugin...");
        enablePlugin();
        setupConfigs();
        registerCommands();
        registerSystems();
        Vault.setupEconomy();
        loadItems();
        loadGUIs();
        getServer().getMessenger().registerOutgoingPluginChannel(this, "minesky:proxy");
        l.info("Plugin habilitado com sucesso!");
    }

    @Override
    public void onDisable() {
        l.info("Desligando plugin...");
        EventStopping.savePlayerData();
        l.info("GoodBye!");
    }

    private void enablePlugin() {
        version = Version.getServerVersion();
        jarType = JarType.getJarType();
    }

    private void registerCommands() {
        new Command("msne", new MSNECommand());
        new Command("sair", new SairCommand());
        new Command("event", new EventCommand());
    }

    private void registerSystems() {
        l.info("Server version: " + jarType + " " + version.toString().replace("_", ".").replace("v", ""));
        PluginManager pm = getServer().getPluginManager();
        pm.registerEvents(new PlayerData(), this);
        pm.registerEvents(new PlayerInfo(), this);
        PlayerMove.PlayerMoveCheck.runTaskTimer(this, 0, 3);
    }

    private void loadItems() {
        l.info("Loading items...");
        EventItem.loadAllItems();
        l.info("All items successfully loaded!");
    }

    private void loadGUIs() {
        l.info("Loading GUIs...");
        //EventGUIS.loadAllGUIs();
        l.info("All GUIs successfully loaded!");
    }

    public void setupConfigs() {
        DataManager.createFolder("playerdata");
        ConfigManager.createConfigs("config.yml", "commands.yml", "locations.yml");
        Config.loadConfig();
        Locations.loadLocations();
        Blacklist = ConfigManager.getConfig("config.yml");
    }

    public static MineSkyEvents get() {
        return MineSkyEvents.getPlugin(MineSkyEvents.class);
    }
}
