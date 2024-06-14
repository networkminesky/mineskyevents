package minesky.msne;

import minesky.msne.addons.Vault;
import minesky.msne.addons.WorldGuard;
import minesky.msne.commands.EventCommand;
import minesky.msne.commands.MSNECommand;
import minesky.msne.config.*;
import minesky.msne.enums.JarType;
import minesky.msne.enums.Version;
import minesky.msne.system.PlayerData;
import minesky.msne.system.PlayerInfo;
import minesky.msne.system.PlayerMove;
import minesky.msne.system.event.EventGUIS;
import minesky.msne.system.event.EventStopping;
import minesky.msne.utils.Command;
import minesky.msne.utils.EventItem;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;


public final class MineSkyEvents extends JavaPlugin {

    public static FileConfiguration Blacklist;
    public static String event = "OFF";
    private static Version version;
    private static JarType jarType;

    @Override
    public void onLoad() {
        WorldGuard.Registry();
    }

    @Override
    public void onEnable() {
        Bukkit.getConsoleSender().sendMessage("§8[§6MineSky Events§8] §7-------------------------------------------------");
        Bukkit.getConsoleSender().sendMessage("§8[§6MineSky Events§8] §7Initializing plugin...");
        enablePlugin();
        gerarConfigs();
        carregarConfigs();
        Commands();
        System();
        Vault.setupEconomy();
        ItemsLoad();
        GUISLoad();
        getServer().getMessenger().registerOutgoingPluginChannel(this, "minesky:proxy");
        Bukkit.getConsoleSender().sendMessage("§8[§6MineSky Events§8] §aConfig loaded.");
        Bukkit.getConsoleSender().sendMessage("§8[§6MineSky Events§8] §aPlugin successfully initialized!");
        Bukkit.getConsoleSender().sendMessage("§8[§6MineSky Events§8] §7-------------------------------------------------");
    }

    private void enablePlugin() {
        version = Version.getServerVersion();
        jarType = JarType.getJarType();
    }

    @Override
    public void onDisable() {
        Bukkit.getConsoleSender().sendMessage("§8[§6MineSky Events§8] §7-------------------------------------------------");
        EventStopping.Files();
        Bukkit.getConsoleSender().sendMessage("§8[§6MineSky Events§8] §cPlugin successfully disabled!");
        Bukkit.getConsoleSender().sendMessage("§8[§6MineSky Events§8] §7-------------------------------------------------");
    }

    private void Commands() {
        new Command("msne", new MSNECommand());
        new Command("event", new EventCommand());
    }

    private void System() {
        Bukkit.getConsoleSender().sendMessage("§8[§6Minesky Events§8] §7Identified server version: " + jarType + " " + version.toString().replace("_", ".").replace("v", ""));
        if (!isRecomendedVersion()) {
            getServer().getConsoleSender().sendMessage("§8[§6Minesky Events§8] §cAttention! You are using a version of Minecraft that does not support all the features of MineSky-Events!");
        }
        PluginManager system = Bukkit.getServer().getPluginManager();
        system.registerEvents(new PlayerData(), this);
        system.registerEvents(new PlayerInfo(), this);
        PlayerMove.PlayerMoveCheck.runTaskTimer(this, 0, 3);
    }

    private void ItemsLoad() {
        Bukkit.getConsoleSender().sendMessage("§8[§6MineSky Events§8] §7Loading all items...");
        EventItem.SpleefITEM = EventItem.Shovel();
        EventItem.BedLeave = EventItem.createBed();
        EventItem.SumoITEM = EventItem.Stick();
        EventItem.BarcoITEM = EventItem.Boat();
        EventItem.TNTHEAD = EventItem.Tnt();
        EventItem.CheckPoint = EventItem.Plate();
        EventItem.FlagORANGE = EventItem.flagLARANJA();
        EventItem.FlagLIME = EventItem.flagVERDE();
        EventItem.FlagITEM_WOODEN_SWORD = EventItem.flagITEM_Wooden_sword();
        EventItem.FlagITEM_STONE_SWORD = EventItem.flagITEM_Stone_sword();
        EventItem.FlagITEM_IRON_SWORD = EventItem.flagITEM_Iron_sword();
        EventItem.FlagITEM_DIAMOND_SWORD = EventItem.flagITEM_Diamond_sword();
        EventItem.FlagITEM_WOODEN_AXE = EventItem.flagITEM_Wooden_axe();
        EventItem.FlagITEM_GOLDEN_AXE = EventItem.flagITEM_Iron_axe();
        EventItem.FlagITEM_IRON_AXE = EventItem.flagITEM_Golden_axe();
        EventItem.FlagITEM_DIAMOND_AXE = EventItem.flagITEM_Diamond_axe();
        EventItem.FlagITEM_WOODEN_PICKAXE = EventItem.flagITEM_Wooden_pickaxe();
        EventItem.FlagITEM_IRON_PICKAXE = EventItem.flagITEM_Iron_pickaxe();
        EventItem.FlagITEM_GOLDEN_PICKAXE = EventItem.flagITEM_Golden_pickaxe();
        EventItem.FlagITEM_DIAMOND_PICKAXE = EventItem.flagITEM_Diamond_pickaxe();
        EventItem.FlagITEM_BOW = EventItem.flagITEM_bow();
        EventItem.FlagITEM_BOW_POWER = EventItem.flagITEM_bow_power();
        EventItem.FlagITEM_BOW_POWER_PUNCH = EventItem.flagITEM_bow_power_punch();
        EventItem.FlagITEM_SHEARS = EventItem.flagITEM_shears();
        EventItem.FlagITEM_SHIELD = EventItem.flagITEM_shield();
        EventItem.FlagITEM_FLINT_AND_STEEL = EventItem.flagITEM_flint_and_steel();
        EventItem.FlagITEM_TOWER = EventItem.flagITEM_tower();
        EventItem.FlagITEM_ARMOR_HELMET_LIME = EventItem.flagITEM_Armor_helmet_lime();
        EventItem.FlagITEM_ARMOR_CHESTPLATE_LIME = EventItem.flagITEM_Armor_chestplate_lime();
        EventItem.FlagITEM_ARMOR_LEGGING_LIME = EventItem.flagITEM_Armor_legging_lime();
        EventItem.FlagITEM_ARMOR_BOTTS_LIME = EventItem.flagITEM_Armor_boots_lime();
        EventItem.FlagITEM_ARMOR_HELMET_ORANGE = EventItem.flagITEM_Armor_helmet_orange();
        EventItem.FlagITEM_ARMOR_CHESTPLATE_ORANGE = EventItem.flagITEM_Armor_chestplate_orange();
        EventItem.FlagITEM_ARMOR_LEGGING_ORANGE = EventItem.flagITEM_Armor_legging_orange();
        EventItem.FlagITEM_ARMOR_BOTTS_ORANGE = EventItem.flagITEM_Armor_boots_orange();
        EventItem.FlagITEM_CHAILMALL_LEGGING = EventItem.flagITEM_Chailmal_legging();
        EventItem.FlagITEM_CHAILMALL_BOOTS = EventItem.flagITEM_Chailmal_BOOTS();
        EventItem.FlagITEM_IRON_LEGGING = EventItem.flagITEM_Iron_legging();
        EventItem.FlagITEM_IRON_BOOTS = EventItem.flagITEM_Iron_boots();
        EventItem.FlagITEM_DIAMOND_LEGGING = EventItem.flagITEM_Diamond_legging();
        EventItem.FlagITEM_DIAMOND_BOOTS = EventItem.flagITEM_Diamond_boots();
        Bukkit.getConsoleSender().sendMessage("§8[§6MineSky Events§8] §aAll items have been successfully loaded!");
    }

    private void GUISLoad() {
        Bukkit.getConsoleSender().sendMessage("§8[§6MineSky Events§8] §7Loading all guis...");
        EventGUIS.ShopGUI_INICIAL();
        EventGUIS.ShopGUI_BLOCOS();
        EventGUIS.ShopGUI_COMBATE();
        EventGUIS.ShopGUI_POÇOES();
        EventGUIS.ShopGUI_FERRAMENTAS();
        EventGUIS.ShopGUI_ATAQUE();
        EventGUIS.ShopGUI_OUTROS();
        Bukkit.getConsoleSender().sendMessage("§8[§6MineSky Events§8] §aAll guis have been successfully loaded!");
    }

    private void gerarConfigs() {
        DataManager.createFolder("playerdata");
        ConfigManager.createConfig("config.yml");
        ConfigManager.createConfig("commands.yml");
        ConfigManager.createConfig("messages.yml");
        ConfigManager.createConfig("locations.yml");
    }

    public static void carregarConfigs() {
        Messages.loadMessages();
        Config.loadConfig();
        Locations.loadLocations();
        Blacklist = ConfigManager.getConfig("config.yml");
    }


    static boolean isRecomendedVersion() {
        if (version == Version.v1_17)
            return true;
        if (version == Version.v1_18)
            return true;
        if (version == Version.v1_19)
            return true;
        return version == Version.v1_20;
    }

    public static MineSkyEvents get() {
        return (MineSkyEvents) Bukkit.getServer().getPluginManager().getPlugin("MineSky-Events");
    }
}
