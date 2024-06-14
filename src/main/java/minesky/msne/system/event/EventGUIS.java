package minesky.msne.system.event;

import minesky.msne.events.CaptureBandeiraEvent;
import minesky.msne.utils.EventItem;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.potion.PotionData;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionType;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class EventGUIS {
    private static Inventory inventorySHOP_INICIAL = null;
    private static Inventory inventorySHOP_BLOCOS = null;
    private static Inventory inventorySHOP_COMBATE = null;
    private static Inventory inventorySHOP_POÇOES = null;
    private static Inventory inventorySHOP_FERRAMENTAS = null;
    private static Inventory inventorySHOP_ATAQUE = null;
    private static Inventory inventorySHOP_OUTROS = null;
    private static Inventory inventoryUPGRADE = null;
    private static final Map<Enchantment, Integer> enchantmentIntegerMap = new HashMap<>();
    public static final Map<Player, Integer> upgradeArmor = new HashMap<>();
    public static final Map<Player, Integer> upgradeSWORD = new HashMap<>();
    public static final Map<Player, Integer> upgradePICKAXE = new HashMap<>();
    public static final Map<Player, Integer> upgradeAXE = new HashMap<>();

    public static void ShopGUI_INICIAL() {
        inventorySHOP_INICIAL = Bukkit.createInventory(null, 54, "Loja - Menu Principal");
        addItem(inventorySHOP_INICIAL, Material.GREEN_STAINED_GLASS_PANE, 1, "", 37, "");
        addItem(inventorySHOP_INICIAL, Material.BLACK_STAINED_GLASS_PANE, 1, "", 38, "");
        addItem(inventorySHOP_INICIAL, Material.BLACK_STAINED_GLASS_PANE, 1, "", 39, "");
        addItem(inventorySHOP_INICIAL, Material.BLACK_STAINED_GLASS_PANE, 1, "", 40, "");
        addItem(inventorySHOP_INICIAL, Material.BLACK_STAINED_GLASS_PANE, 1, "", 41, "");
        addItem(inventorySHOP_INICIAL, Material.BLACK_STAINED_GLASS_PANE, 1, "", 42, "");
        addItem(inventorySHOP_INICIAL, Material.BLACK_STAINED_GLASS_PANE, 1, "", 43, "");
        addItem(inventorySHOP_INICIAL, Material.NETHER_STAR, 1, "§e→ Menu principal", 46, "§7Você está visualizando esse menu!");
        addItem(inventorySHOP_INICIAL, Material.BRICKS, 1, "§d→ Blocos", 47, "§7Clique para abrir o menu de Blocos!");
        addItem(inventorySHOP_INICIAL, Material.GOLDEN_SWORD, 1, "§9→ Combate", 48, "§7Clique para abrir o menu de Combate!");
        addItem(inventorySHOP_INICIAL, Material.BREWING_STAND, 1, "§a→ Poções", 49, "§7Clique para abrir o menu de Poções!");
        addItem(inventorySHOP_INICIAL, Material.GOLDEN_PICKAXE, 1, "§c→ Ferramentas", 50, "§7Clique para abrir o menu de Ferramentas!");
        addItem(inventorySHOP_INICIAL, Material.TNT, 1, "§b→ Ataque", 51, "§7Clique para abrir o menu de Ataque!");
        addItem(inventorySHOP_INICIAL, Material.CHEST_MINECART, 1, "§9→ Outros", 52, "§7Clique para abrir o menu de Outros!");
        addItem(inventorySHOP_INICIAL, Material.WHITE_WOOL, 16, "§f→ Lã Branca", 10, ChatColor.of("#b4a7d6") + "Quantidade§8: " + ChatColor.of("#b4a7d6") + "16", "", ChatColor.of("#a2c4c9") + "Preço§8: §f4 FERROS", "", ChatColor.of("#ffd966") + "› Clique para comprar!");
        addItem(inventorySHOP_INICIAL, Material.GOLDEN_APPLE, 1, "§6→ Maçã Dourada", 19, ChatColor.of("#b4a7d6") + "Quantidade§8: " + ChatColor.of("#b4a7d6") + "1", "", ChatColor.of("#a2c4c9") + "Preço§8: §63 OUROS", "", ChatColor.of("#ffd966") + "› Clique para comprar!");
        addItem(inventorySHOP_INICIAL, Material.OAK_PLANKS, 16, "§e→ Madeira", 28, ChatColor.of("#b4a7d6") + "Quantidade§8: " + ChatColor.of("#b4a7d6") + "16", "", ChatColor.of("#a2c4c9") + "Preço§8: §64 OUROS", "", ChatColor.of("#ffd966") + "› Clique para comprar!");
        enchantmentIntegerMap.clear();
        enchantmentIntegerMap.put(Enchantment.DIG_SPEED, 1);
        addItem(inventorySHOP_INICIAL, Material.WOODEN_PICKAXE, 1, "§e→ Picareta de Madeira", enchantmentIntegerMap, 11, ChatColor.of("#b4a7d6") + "Quantidade§8: " + ChatColor.of("#b4a7d6") + "1", "", ChatColor.of("#a2c4c9") + "Preço§8: §f10 FERROS", "", ChatColor.of("#ffd966") + "› Clique para comprar!");
        addItem(inventorySHOP_INICIAL, Material.TNT, 1, "§c→ TNT", 20, ChatColor.of("#b4a7d6") + "Quantidade§8: " + ChatColor.of("#b4a7d6") + "1", "", ChatColor.of("#a2c4c9") + "Preço§8: §64 OUROS", "", ChatColor.of("#ffd966") + "› Clique para comprar!");
        addItem(inventorySHOP_INICIAL, Material.END_STONE, 12, "§e→ Pedra do END", 29, ChatColor.of("#b4a7d6") + "Quantidade§8: " + ChatColor.of("#b4a7d6") + "12", "", ChatColor.of("#a2c4c9") + "Preço§8: §f10 FERROS", "", ChatColor.of("#ffd966") + "› Clique para comprar!");
        enchantmentIntegerMap.clear();
        enchantmentIntegerMap.put(Enchantment.DIG_SPEED, 1);
        addItem(inventorySHOP_INICIAL, Material.WOODEN_AXE, 1, "§e→ Machado de Madeira", enchantmentIntegerMap, 12, ChatColor.of("#b4a7d6") + "Quantidade§8: " + ChatColor.of("#b4a7d6") + "1", "", ChatColor.of("#a2c4c9") + "Preço§8: §f10 FERROS", "", ChatColor.of("#ffd966") + "› Clique para comprar!");
        addItem(inventorySHOP_INICIAL, Material.LEGACY_FIREBALL, 1, "§c→ Bola de Fogo", 21, ChatColor.of("#b4a7d6") + "Quantidade§8: " + ChatColor.of("#b4a7d6") + "1", "", ChatColor.of("#a2c4c9") + "Preço§8: §f40 FERROS", "", ChatColor.of("#ffd966") + "› Clique para comprar!");
        addItem(inventorySHOP_INICIAL, Material.LADDER, 8, "§d→ Escadas", 30, ChatColor.of("#b4a7d6") + "Quantidade§8: " + ChatColor.of("#b4a7d6") + "8", "", ChatColor.of("#a2c4c9") + "Preço§8: §f4 FERROS", "", ChatColor.of("#ffd966") + "› Clique para comprar!");
        addItem(inventorySHOP_INICIAL, Material.STONE_SWORD, 1, "§8→ Espada de Pedra", 13, ChatColor.of("#b4a7d6") + "Quantidade§8: " + ChatColor.of("#b4a7d6") + "1", "", ChatColor.of("#a2c4c9") + "Preço§8: §f10 FERROS", "", ChatColor.of("#ffd966") + "› Clique para comprar!");
        addItem(inventorySHOP_INICIAL, Material.CHAINMAIL_BOOTS, 1, "§7→ Armadura de Cota de Malha", 22, ChatColor.of("#b4a7d6") + "Quantidade§8: " + ChatColor.of("#b4a7d6") + "1", "", ChatColor.of("#a2c4c9") + "Preço§8: §f24 FERROS", "", ChatColor.of("#ffd966") + "› Clique para comprar!");
        addItem(inventorySHOP_INICIAL, Material.POTION, 1, PotionType.SPEED, 45 , "§9→ Poção de Speed (0:45)", 31, ChatColor.of("#b4a7d6") + "Quantidade§8: " + ChatColor.of("#b4a7d6") + "1", "", ChatColor.of("#a2c4c9") + "Preço§8: §a1 ESMERALDAS", "", ChatColor.of("#ffd966") + "› Clique para comprar!");
        addItem(inventorySHOP_INICIAL, Material.IRON_SWORD, 1, "§7→ Espeda de Ferro", 14, ChatColor.of("#b4a7d6") + "Quantidade§8: " + ChatColor.of("#b4a7d6") + "1", "", ChatColor.of("#a2c4c9") + "Preço§8: §67 OUROS", "", ChatColor.of("#ffd966") + "› Clique para comprar!");
        addItem(inventorySHOP_INICIAL, Material.IRON_BOOTS, 1, "§7→ Armadura de Ferro", 23, ChatColor.of("#b4a7d6") + "Quantidade§8: " + ChatColor.of("#b4a7d6") + "1", "", ChatColor.of("#a2c4c9") + "Preço§8: §612 OUROS", "", ChatColor.of("#ffd966") + "› Clique para comprar!");
        addItem(inventorySHOP_INICIAL, Material.POTION, 1, PotionType.JUMP, 45 , "§a→ Poção de Pulo (0:45)", 32, ChatColor.of("#b4a7d6") + "Quantidade§8: " + ChatColor.of("#b4a7d6") + "1", "", ChatColor.of("#a2c4c9") + "Preço§8: §a1 ESMERALDAS", "", ChatColor.of("#ffd966") + "› Clique para comprar!");
        addItem(inventorySHOP_INICIAL, Material.BOW, 1, "§e→ Arco normal", 15, ChatColor.of("#b4a7d6") + "Quantidade§8: " + ChatColor.of("#b4a7d6") + "1", "", ChatColor.of("#a2c4c9") + "Preço§8: §612 OUROS", "", ChatColor.of("#ffd966") + "› Clique para comprar!");
        addItem(inventorySHOP_INICIAL, Material.DIAMOND_BOOTS, 1, "§9→ Armadura de Diamante", 24, ChatColor.of("#b4a7d6") + "Quantidade§8: " + ChatColor.of("#b4a7d6") + "1", "", ChatColor.of("#a2c4c9") + "Preço§8: §a6 ESMERALDAS", "", ChatColor.of("#ffd966") + "› Clique para comprar!");
        addItem(inventorySHOP_INICIAL, Material.POTION, 1, PotionType.INVISIBILITY, 30 ,"§9→ Poção de Invisibilidade (0:30)", 33, ChatColor.of("#b4a7d6") + "Quantidade§8: " + ChatColor.of("#b4a7d6") + "1", "", ChatColor.of("#a2c4c9") + "Preço§8: §a2 ESMERALDAS", "", ChatColor.of("#ffd966") + "› Clique para comprar!");
        addItem(inventorySHOP_INICIAL, Material.ARROW, 8, "§f→ Flechas", 16, ChatColor.of("#b4a7d6") + "Quantidade§8: " + ChatColor.of("#b4a7d6") + "8", "", ChatColor.of("#a2c4c9") + "Preço§8: §62 OUROS", "", ChatColor.of("#ffd966") + "› Clique para comprar!");
        enchantmentIntegerMap.clear();
        enchantmentIntegerMap.put(Enchantment.KNOCKBACK, 1);
        addItem(inventorySHOP_INICIAL, Material.STICK, 1, "§d→ Graveto de repulsão I", enchantmentIntegerMap,25, ChatColor.of("#b4a7d6") + "Quantidade§8: " + ChatColor.of("#b4a7d6") + "1", "", ChatColor.of("#a2c4c9") + "Preço§8: §65 OUROS", "", ChatColor.of("#ffd966") + "› Clique para comprar!");
        addItem(inventorySHOP_INICIAL, Material.ENDER_PEARL, 1, "§9→ Pérola do ender", 34, ChatColor.of("#b4a7d6") + "Quantidade§8: " + ChatColor.of("#b4a7d6") + "1", "", ChatColor.of("#a2c4c9") + "Preço§8: §a4 ESMERALDAS", "", ChatColor.of("#ffd966") + "› Clique para comprar!");
    }

    public static void ShopGUI_BLOCOS() {
        inventorySHOP_BLOCOS = Bukkit.createInventory(null, 54, "Loja - Blocos");
        addItem(inventorySHOP_BLOCOS, Material.BLACK_STAINED_GLASS_PANE, 1, "", 37, "");
        addItem(inventorySHOP_BLOCOS, Material.GREEN_STAINED_GLASS_PANE, 1, "", 38, "");
        addItem(inventorySHOP_BLOCOS, Material.BLACK_STAINED_GLASS_PANE, 1, "", 39, "");
        addItem(inventorySHOP_BLOCOS, Material.BLACK_STAINED_GLASS_PANE, 1, "", 40, "");
        addItem(inventorySHOP_BLOCOS, Material.BLACK_STAINED_GLASS_PANE, 1, "", 41, "");
        addItem(inventorySHOP_BLOCOS, Material.BLACK_STAINED_GLASS_PANE, 1, "", 42, "");
        addItem(inventorySHOP_BLOCOS, Material.BLACK_STAINED_GLASS_PANE, 1, "", 43, "");
        addItem(inventorySHOP_BLOCOS, Material.NETHER_STAR, 1, "§e→ Menu principal", 46, "§7Clique para abrir o menu Principal!");
        addItem(inventorySHOP_BLOCOS, Material.BRICKS, 1, "§d→ Blocos", 47, "§7Você está visualizando esse menu!");
        addItem(inventorySHOP_BLOCOS, Material.GOLDEN_SWORD, 1, "§9→ Combate", 48, "§7Clique para abrir o menu de Combate!");
        addItem(inventorySHOP_BLOCOS, Material.BREWING_STAND, 1, "§a→ Poções", 49, "§7Clique para abrir o menu de Poções!");
        addItem(inventorySHOP_BLOCOS, Material.GOLDEN_PICKAXE, 1, "§c→ Ferramentas", 50, "§7Clique para abrir o menu de Ferramentas!");
        addItem(inventorySHOP_BLOCOS, Material.TNT, 1, "§b→ Ataque", 51, "§7Clique para abrir o menu de Ataque!");
        addItem(inventorySHOP_BLOCOS, Material.CHEST_MINECART, 1, "§9→ Outros", 52, "§7Clique para abrir o menu de Outros!");
        addItem(inventorySHOP_BLOCOS, Material.WHITE_WOOL, 16, "§f→ Lã Branca", 10, ChatColor.of("#b4a7d6") + "Quantidade§8: " + ChatColor.of("#b4a7d6") + "16", "", ChatColor.of("#a2c4c9") + "Preço§8: §f4 FERROS", "", ChatColor.of("#ffd966") + "› Clique para comprar!");
        addItem(inventorySHOP_BLOCOS, Material.TERRACOTTA, 8, "§6→ Terracota", 11, ChatColor.of("#b4a7d6") + "Quantidade§8: " + ChatColor.of("#b4a7d6") + "8", "", ChatColor.of("#a2c4c9") + "Preço§8: §f18 FERROS", "", ChatColor.of("#ffd966") + "› Clique para comprar!");
        addItem(inventorySHOP_BLOCOS, Material.END_STONE, 12, "§e→ Pedra do END", 12, ChatColor.of("#b4a7d6") + "Quantidade§8: " + ChatColor.of("#b4a7d6") + "12", "", ChatColor.of("#a2c4c9") + "Preço§8: §f24 FERROS", "", ChatColor.of("#ffd966") + "› Clique para comprar!");
        addItem(inventorySHOP_BLOCOS, Material.GLASS, 8, "§7→ Vidro", 13, ChatColor.of("#b4a7d6") + "Quantidade§8: " + ChatColor.of("#b4a7d6") + "8", "", ChatColor.of("#a2c4c9") + "Preço§8: §f15 FERROS", "", ChatColor.of("#ffd966") + "› Clique para comprar!");
        addItem(inventorySHOP_BLOCOS, Material.OAK_PLANKS, 16, "§e→ Madeira", 14, ChatColor.of("#b4a7d6") + "Quantidade§8: " + ChatColor.of("#b4a7d6") + "16", "", ChatColor.of("#a2c4c9") + "Preço§8: §64 OUROS", "", ChatColor.of("#ffd966") + "› Clique para comprar!");
        addItem(inventorySHOP_BLOCOS, Material.LADDER, 8, "§d→ Escada", 15, ChatColor.of("#b4a7d6") + "Quantidade§8: " + ChatColor.of("#b4a7d6") + "8", "", ChatColor.of("#a2c4c9") + "Preço§8: §f4 FERROS", "", ChatColor.of("#ffd966") + "› Clique para comprar!");
        addItem(inventorySHOP_BLOCOS, Material.OBSIDIAN, 4, "§5→ Obisidiana", 16, ChatColor.of("#b4a7d6") + "Quantidade§8: " + ChatColor.of("#b4a7d6") + "4", "", ChatColor.of("#a2c4c9") + "Preço§8: §a4 ESMERALDAS", "", ChatColor.of("#ffd966") + "› Clique para comprar!");
    }

    public static void ShopGUI_COMBATE() {
        inventorySHOP_COMBATE = Bukkit.createInventory(null, 54, "Loja - Combate");
        addItem(inventorySHOP_COMBATE, Material.BLACK_STAINED_GLASS_PANE, 1, "", 37, "");
        addItem(inventorySHOP_COMBATE, Material.BLACK_STAINED_GLASS_PANE, 1, "", 38, "");
        addItem(inventorySHOP_COMBATE, Material.GREEN_STAINED_GLASS_PANE, 1, "", 39, "");
        addItem(inventorySHOP_COMBATE, Material.BLACK_STAINED_GLASS_PANE, 1, "", 40, "");
        addItem(inventorySHOP_COMBATE, Material.BLACK_STAINED_GLASS_PANE, 1, "", 41, "");
        addItem(inventorySHOP_COMBATE, Material.BLACK_STAINED_GLASS_PANE, 1, "", 42, "");
        addItem(inventorySHOP_COMBATE, Material.BLACK_STAINED_GLASS_PANE, 1, "", 43, "");
        addItem(inventorySHOP_COMBATE, Material.NETHER_STAR, 1, "§e→ Menu principal", 46, "§7Clique para abrir o menu Principal!");
        addItem(inventorySHOP_COMBATE, Material.BRICKS, 1, "§d→ Blocos", 47, "§7Clique para abrir o menu de Blocos!");
        addItem(inventorySHOP_COMBATE, Material.GOLDEN_SWORD, 1, "§9→ Combate", 48, "§7Você está visualizando esse menu!");
        addItem(inventorySHOP_COMBATE, Material.BREWING_STAND, 1, "§a→ Poções", 49, "§7Clique para abrir o menu de Poções!");
        addItem(inventorySHOP_COMBATE, Material.GOLDEN_PICKAXE, 1, "§c→ Ferramentas", 50, "§7Clique para abrir o menu de Ferramentas!");
        addItem(inventorySHOP_COMBATE, Material.TNT, 1, "§b→ Ataque", 51, "§7Clique para abrir o menu de Ataque!");
        addItem(inventorySHOP_COMBATE, Material.CHEST_MINECART, 1, "§9→ Outros", 52, "§7Clique para abrir o menu de Outros!");
        addItem(inventorySHOP_COMBATE, Material.CHAINMAIL_BOOTS, 1, "§f→ Armadura de Cota de Malha", 10, ChatColor.of("#b4a7d6") + "Quantidade§8: " + ChatColor.of("#b4a7d6") + "1", "", ChatColor.of("#a2c4c9") + "Preço§8: §f24 FERROS", "", ChatColor.of("#ffd966") + "› Clique para comprar!");
        addItem(inventorySHOP_COMBATE, Material.IRON_BOOTS, 1, "§7→ Armadura de Ferro", 11, ChatColor.of("#b4a7d6") + "Quantidade§8: " + ChatColor.of("#b4a7d6") + "1", "", ChatColor.of("#a2c4c9") + "Preço§8: §612 OUROS", "", ChatColor.of("#ffd966") + "› Clique para comprar!");
        addItem(inventorySHOP_COMBATE, Material.DIAMOND_BOOTS, 1, "§b→ Armadura de Diamante", 12, ChatColor.of("#b4a7d6") + "Quantidade§8: " + ChatColor.of("#b4a7d6") + "1", "", ChatColor.of("#a2c4c9") + "Preço§8: §a6 ESMERALDAS", "", ChatColor.of("#ffd966") + "› Clique para comprar!");
        addItem(inventorySHOP_COMBATE, Material.STONE_SWORD, 1, "§8→ Espada de Pedra", 19, ChatColor.of("#b4a7d6") + "Quantidade§8: " + ChatColor.of("#b4a7d6") + "1", "", ChatColor.of("#a2c4c9") + "Preço§8: §f10 FERROS", "", ChatColor.of("#ffd966") + "› Clique para comprar!");
        addItem(inventorySHOP_COMBATE, Material.IRON_SWORD, 1, "§7→ Espada de Ferro", 20, ChatColor.of("#b4a7d6") + "Quantidade§8: " + ChatColor.of("#b4a7d6") + "1", "", ChatColor.of("#a2c4c9") + "Preço§8: §67 OUROS", "", ChatColor.of("#ffd966") + "› Clique para comprar!");
        addItem(inventorySHOP_COMBATE, Material.DIAMOND_SWORD, 1, "§b→ Espada de Diamante", 21, ChatColor.of("#b4a7d6") + "Quantidade§8: " + ChatColor.of("#b4a7d6") + "1", "", ChatColor.of("#a2c4c9") + "Preço§8: §a4 ESMERALDAS", "", ChatColor.of("#ffd966") + "› Clique para comprar!");
        addItem(inventorySHOP_COMBATE, Material.BOW, 1, "§f→ Arco normal", 16, ChatColor.of("#b4a7d6") + "Quantidade§8: " + ChatColor.of("#b4a7d6") + "1", "", ChatColor.of("#a2c4c9") + "Preço§8: §612 OUROS", "", ChatColor.of("#ffd966") + "› Clique para comprar!");
        enchantmentIntegerMap.clear();
        enchantmentIntegerMap.put(Enchantment.ARROW_DAMAGE, 1);
        addItem(inventorySHOP_COMBATE, Material.BOW, 1, "§d→ Arco com força I", enchantmentIntegerMap,15, ChatColor.of("#b4a7d6") + "Quantidade§8: " + ChatColor.of("#b4a7d6") + "1", "", ChatColor.of("#a2c4c9") + "Preço§8: §624 OUROS", "", ChatColor.of("#ffd966") + "› Clique para comprar!");
        enchantmentIntegerMap.clear();
        enchantmentIntegerMap.put(Enchantment.ARROW_DAMAGE, 1);
        enchantmentIntegerMap.put(Enchantment.ARROW_KNOCKBACK, 1);
        addItem(inventorySHOP_COMBATE, Material.BOW, 1, "§3→ Arco com impacto I e força I", enchantmentIntegerMap,24, ChatColor.of("#b4a7d6") + "Quantidade§8: " + ChatColor.of("#b4a7d6") + "1", "", ChatColor.of("#a2c4c9") + "Preço§8: §a4 ESMERALDAS", "", ChatColor.of("#ffd966") + "› Clique para comprar!");
        addItem(inventorySHOP_COMBATE, Material.ARROW, 8, "§f→ Flechas", 25, ChatColor.of("#b4a7d6") + "Quantidade§8: " + ChatColor.of("#b4a7d6") + "8", "", ChatColor.of("#a2c4c9") + "Preço§8: §62 OUROS", "", ChatColor.of("#ffd966") + "› Clique para comprar!");
    }

    public static void ShopGUI_POÇOES() {
        inventorySHOP_POÇOES = Bukkit.createInventory(null, 54, "Loja - Poções");
        addItem(inventorySHOP_POÇOES, Material.BLACK_STAINED_GLASS_PANE, 1, "", 37, "");
        addItem(inventorySHOP_POÇOES, Material.BLACK_STAINED_GLASS_PANE, 1, "", 38, "");
        addItem(inventorySHOP_POÇOES, Material.BLACK_STAINED_GLASS_PANE, 1, "", 39, "");
        addItem(inventorySHOP_POÇOES, Material.GREEN_STAINED_GLASS_PANE, 1, "", 40, "");
        addItem(inventorySHOP_POÇOES, Material.BLACK_STAINED_GLASS_PANE, 1, "", 41, "");
        addItem(inventorySHOP_POÇOES, Material.BLACK_STAINED_GLASS_PANE, 1, "", 42, "");
        addItem(inventorySHOP_POÇOES, Material.BLACK_STAINED_GLASS_PANE, 1, "", 43, "");
        addItem(inventorySHOP_POÇOES, Material.NETHER_STAR, 1, "§e→ Menu principal", 46, "§7Clique para abrir o menu Principal!");
        addItem(inventorySHOP_POÇOES, Material.BRICKS, 1, "§d→ Blocos", 47, "§7Clique para abrir o menu de Blocos!");
        addItem(inventorySHOP_POÇOES, Material.GOLDEN_SWORD, 1, "§9→ Combate", 48, "§7Clique para abrir o menu de Combate!");
        addItem(inventorySHOP_POÇOES, Material.BREWING_STAND, 1, "§a→ Poções", 49, "§7Você está visualizando esse menu!");
        addItem(inventorySHOP_POÇOES, Material.GOLDEN_PICKAXE, 1, "§c→ Ferramentas", 50, "§7Clique para abrir o menu de Ferramentas!");
        addItem(inventorySHOP_POÇOES, Material.TNT, 1, "§b→ Ataque", 51, "§7Clique para abrir o menu de Ataque!");
        addItem(inventorySHOP_POÇOES, Material.CHEST_MINECART, 1, "§9→ Outros", 52, "§7Clique para abrir o menu de Outros!");
        addItem(inventorySHOP_POÇOES, Material.POTION, 1, PotionType.SPEED, 45, "§9→ Poção de Speed (0:45)", 10, ChatColor.of("#b4a7d6") + "Quantidade§8: " + ChatColor.of("#b4a7d6") + "1", "", ChatColor.of("#a2c4c9") + "Preço§8: §a1 ESMERALDAS", "", ChatColor.of("#ffd966") + "› Clique para comprar!");
        addItem(inventorySHOP_POÇOES, Material.POTION, 1, PotionType.JUMP, 45, "§a→ Poção de Pulo (0:45)", 11, ChatColor.of("#b4a7d6") + "Quantidade§8: " + ChatColor.of("#b4a7d6") + "1", "", ChatColor.of("#a2c4c9") + "Preço§8: §a1 ESMERALDAS", "", ChatColor.of("#ffd966") + "› Clique para comprar!");
        addItem(inventorySHOP_POÇOES, Material.POTION, 1, PotionType.INVISIBILITY, 30, "§9→ Poção de Invisibilidade (0:30)", 12, ChatColor.of("#b4a7d6") + "Quantidade§8: " + ChatColor.of("#b4a7d6") + "1", "", ChatColor.of("#a2c4c9") + "Preço§8: §a2 ESMERALDAS", "", ChatColor.of("#ffd966") + "› Clique para comprar!");
        addItem(inventorySHOP_POÇOES, Material.POTION, 1, PotionType.FIRE_RESISTANCE, 30, "§d→ MIX de Poções (0:30)", 14, ChatColor.of("#b4a7d6") + "Quantidade§8: " + ChatColor.of("#b4a7d6") + "1", "", ChatColor.of("#a2c4c9") + "Preço§8: §a4 ESMERALDAS", "", ChatColor.of("#ffd966") + "› Clique para comprar!");
        addItem(inventorySHOP_POÇOES, Material.MILK_BUCKET, 1, "§f→ Balde de Leite", 15, ChatColor.of("#b4a7d6") + "Quantidade§8: " + ChatColor.of("#b4a7d6") + "1", "", ChatColor.of("#a2c4c9") + "Preço§8: §f6 FERROS", "", ChatColor.of("#ffd966") + "› Clique para comprar!");
    }

    public static void ShopGUI_FERRAMENTAS() {
        inventorySHOP_FERRAMENTAS = Bukkit.createInventory(null, 54, "Loja - Ferramentas");
        addItem(inventorySHOP_FERRAMENTAS, Material.BLACK_STAINED_GLASS_PANE, 1, "", 37, "");
        addItem(inventorySHOP_FERRAMENTAS, Material.BLACK_STAINED_GLASS_PANE, 1, "", 38, "");
        addItem(inventorySHOP_FERRAMENTAS, Material.BLACK_STAINED_GLASS_PANE, 1, "", 39, "");
        addItem(inventorySHOP_FERRAMENTAS, Material.BLACK_STAINED_GLASS_PANE, 1, "", 40, "");
        addItem(inventorySHOP_FERRAMENTAS, Material.GREEN_STAINED_GLASS_PANE, 1, "", 41, "");
        addItem(inventorySHOP_FERRAMENTAS, Material.BLACK_STAINED_GLASS_PANE, 1, "", 42, "");
        addItem(inventorySHOP_FERRAMENTAS, Material.BLACK_STAINED_GLASS_PANE, 1, "", 43, "");
        addItem(inventorySHOP_FERRAMENTAS, Material.NETHER_STAR, 1, "§e→ Menu principal", 46, "§7Clique para abrir o menu Principal!");
        addItem(inventorySHOP_FERRAMENTAS, Material.BRICKS, 1, "§d→ Blocos", 47, "§7Clique para abrir o menu de Blocos!");
        addItem(inventorySHOP_FERRAMENTAS, Material.GOLDEN_SWORD, 1, "§9→ Combate", 48, "§7Clique para abrir o menu de Combate!");
        addItem(inventorySHOP_FERRAMENTAS, Material.BREWING_STAND, 1, "§a→ Poções", 49, "§7Clique para abrir o menu de Poções!");
        addItem(inventorySHOP_FERRAMENTAS, Material.GOLDEN_PICKAXE, 1, "§c→ Ferramentas", 50, "§7Você está visualizando esse menu!");
        addItem(inventorySHOP_FERRAMENTAS, Material.TNT, 1, "§b→ Ataque", 51, "§7Clique para abrir o menu de Ataque!");
        addItem(inventorySHOP_FERRAMENTAS, Material.CHEST_MINECART, 1, "§9→ Outros", 52, "§7Clique para abrir o menu de Outros!");
        enchantmentIntegerMap.clear();
        enchantmentIntegerMap.put(Enchantment.DIG_SPEED, 1);
        addItem(inventorySHOP_FERRAMENTAS, Material.WOODEN_PICKAXE, 1, "§e→ Picareta de Madeira", enchantmentIntegerMap, 10, ChatColor.of("#b4a7d6") + "Quantidade§8: " + ChatColor.of("#b4a7d6") + "1", "", ChatColor.of("#a2c4c9") + "Preço§8: §f10 FERROS", "", ChatColor.of("#ffd966") + "› Clique para comprar!");
        enchantmentIntegerMap.clear();
        enchantmentIntegerMap.put(Enchantment.DIG_SPEED, 2);
        addItem(inventorySHOP_FERRAMENTAS, Material.IRON_PICKAXE, 1, "§7→ Picareta de Ferro", enchantmentIntegerMap, 11, ChatColor.of("#b4a7d6") + "Quantidade§8: " + ChatColor.of("#b4a7d6") + "1", "", ChatColor.of("#a2c4c9") + "Preço§8: §f20 FERROS", "", ChatColor.of("#ffd966") + "› Clique para comprar!");
        enchantmentIntegerMap.clear();
        enchantmentIntegerMap.put(Enchantment.DIG_SPEED, 3);
        addItem(inventorySHOP_FERRAMENTAS, Material.GOLDEN_PICKAXE, 1, "§6→ Picareta de Ouro", enchantmentIntegerMap, 12, ChatColor.of("#b4a7d6") + "Quantidade§8: " + ChatColor.of("#b4a7d6") + "1", "", ChatColor.of("#a2c4c9") + "Preço§8: §68 OUROS", "", ChatColor.of("#ffd966") + "› Clique para comprar!");
        enchantmentIntegerMap.clear();
        enchantmentIntegerMap.put(Enchantment.DIG_SPEED, 3);
        addItem(inventorySHOP_FERRAMENTAS, Material.DIAMOND_PICKAXE, 1, "§9→ Picareta de Diamante", enchantmentIntegerMap, 13, ChatColor.of("#b4a7d6") + "Quantidade§8: " + ChatColor.of("#b4a7d6") + "1", "", ChatColor.of("#a2c4c9") + "Preço§8: §616 OUROS", "", ChatColor.of("#ffd966") + "› Clique para comprar!");
        enchantmentIntegerMap.clear();
        enchantmentIntegerMap.put(Enchantment.DIG_SPEED, 1);
        addItem(inventorySHOP_FERRAMENTAS, Material.WOODEN_AXE, 1, "§e→ Machado de Madeira", enchantmentIntegerMap, 19, ChatColor.of("#b4a7d6") + "Quantidade§8: " + ChatColor.of("#b4a7d6") + "1", "", ChatColor.of("#a2c4c9") + "Preço§8: §f10 FERROS", "", ChatColor.of("#ffd966") + "› Clique para comprar!");
        enchantmentIntegerMap.clear();
        enchantmentIntegerMap.put(Enchantment.DIG_SPEED, 2);
        addItem(inventorySHOP_FERRAMENTAS, Material.IRON_AXE, 1, "§7→ Machado de Ferro", enchantmentIntegerMap, 20, ChatColor.of("#b4a7d6") + "Quantidade§8: " + ChatColor.of("#b4a7d6") + "1", "", ChatColor.of("#a2c4c9") + "Preço§8: §f20 FERROS", "", ChatColor.of("#ffd966") + "› Clique para comprar!");
        enchantmentIntegerMap.clear();
        enchantmentIntegerMap.put(Enchantment.DIG_SPEED, 3);
        addItem(inventorySHOP_FERRAMENTAS, Material.GOLDEN_AXE, 1, "§6→ Machado de Ouro", enchantmentIntegerMap, 21, ChatColor.of("#b4a7d6") + "Quantidade§8: " + ChatColor.of("#b4a7d6") + "1", "", ChatColor.of("#a2c4c9") + "Preço§8: §68 OUROS", "", ChatColor.of("#ffd966") + "› Clique para comprar!");
        enchantmentIntegerMap.clear();
        enchantmentIntegerMap.put(Enchantment.DIG_SPEED, 3);
        enchantmentIntegerMap.put(Enchantment.DAMAGE_ALL, 1);
        addItem(inventorySHOP_FERRAMENTAS, Material.DIAMOND_AXE, 1, "§9→ Machado de Diamante", enchantmentIntegerMap, 22, ChatColor.of("#b4a7d6") + "Quantidade§8: " + ChatColor.of("#b4a7d6") + "1", "", ChatColor.of("#a2c4c9") + "Preço§8: §616 OUROS", "", ChatColor.of("#ffd966") + "› Clique para comprar!");
        addItem(inventorySHOP_FERRAMENTAS, Material.SHIELD, 1, "§3→ Escudo", 15, ChatColor.of("#b4a7d6") + "Quantidade§8: " + ChatColor.of("#b4a7d6") + "1", "", ChatColor.of("#a2c4c9") + "Preço§8: §618 OUROS", "", ChatColor.of("#ffd966") + "› Clique para comprar!");
        addItem(inventorySHOP_FERRAMENTAS, Material.STICK, 1, "§d→ Graveto de repulsão I", enchantmentIntegerMap, 16, ChatColor.of("#b4a7d6") + "Quantidade§8: " + ChatColor.of("#b4a7d6") + "1", "", ChatColor.of("#a2c4c9") + "Preço§8: §65 OUROS", "", ChatColor.of("#ffd966") + "› Clique para comprar!");
        addItem(inventorySHOP_FERRAMENTAS, Material.FLINT_AND_STEEL, 1, "§8→ Isqueiro", 24, ChatColor.of("#b4a7d6") + "Quantidade§8: " + ChatColor.of("#b4a7d6") + "1", "", ChatColor.of("#a2c4c9") + "Preço§8: §65 OUROS", "", ChatColor.of("#ffd966") + "› Clique para comprar!");
        addItem(inventorySHOP_FERRAMENTAS, Material.SHEARS, 1, "§7→ Tesoura", 25, ChatColor.of("#b4a7d6") + "Quantidade§8: " + ChatColor.of("#b4a7d6") + "1", "", ChatColor.of("#a2c4c9") + "Preço§8: §f20 FERROS", "", ChatColor.of("#ffd966") + "› Clique para comprar!");
    }

    public static void ShopGUI_ATAQUE() {
        inventorySHOP_ATAQUE = Bukkit.createInventory(null, 54, "Loja - Ataque");
        addItem(inventorySHOP_ATAQUE, Material.BLACK_STAINED_GLASS_PANE, 1, "", 37, "");
        addItem(inventorySHOP_ATAQUE, Material.BLACK_STAINED_GLASS_PANE, 1, "", 38, "");
        addItem(inventorySHOP_ATAQUE, Material.BLACK_STAINED_GLASS_PANE, 1, "", 39, "");
        addItem(inventorySHOP_ATAQUE, Material.BLACK_STAINED_GLASS_PANE, 1, "", 40, "");
        addItem(inventorySHOP_ATAQUE, Material.BLACK_STAINED_GLASS_PANE, 1, "", 41, "");
        addItem(inventorySHOP_ATAQUE, Material.GREEN_STAINED_GLASS_PANE, 1, "", 42, "");
        addItem(inventorySHOP_ATAQUE, Material.BLACK_STAINED_GLASS_PANE, 1, "", 43, "");
        addItem(inventorySHOP_ATAQUE, Material.NETHER_STAR, 1, "§e→ Menu principal", 46, "§7Clique para abrir o menu Principal!");
        addItem(inventorySHOP_ATAQUE, Material.BRICKS, 1, "§d→ Blocos", 47, "§7Clique para abrir o menu de Blocos!");
        addItem(inventorySHOP_ATAQUE, Material.GOLDEN_SWORD, 1, "§9→ Combate", 48, "§7Clique para abrir o menu de Combate!");
        addItem(inventorySHOP_ATAQUE, Material.BREWING_STAND, 1, "§a→ Poções", 49, "§7Clique para abrir o menu de Poções!");
        addItem(inventorySHOP_ATAQUE, Material.GOLDEN_PICKAXE, 1, "§c→ Ferramentas", 50, "§7Clique para abrir o menu de Ferramentas!");
        addItem(inventorySHOP_ATAQUE, Material.TNT, 1, "§b→ Ataque", 51, "§7Você está visualizando esse menu!");
        addItem(inventorySHOP_ATAQUE, Material.CHEST_MINECART, 1, "§9→ Outros", 52, "§7Clique para abrir o menu de Outros!");
        addItem(inventorySHOP_ATAQUE, Material.LEGACY_FIREBALL, 1, "§c→ Bola de Fogo", 10, ChatColor.of("#b4a7d6") + "Quantidade§8: " + ChatColor.of("#b4a7d6") + "1", "", ChatColor.of("#a2c4c9") + "Preço§8: §f40 FERROS", "", ChatColor.of("#ffd966") + "› Clique para comprar!");
        addItem(inventorySHOP_ATAQUE, Material.TNT, 1, "§c→ TNT", 11, ChatColor.of("#b4a7d6") + "Quantidade§8: " + ChatColor.of("#b4a7d6") + "1", "", ChatColor.of("#a2c4c9") + "Preço§8: §64 OUROS", "", ChatColor.of("#ffd966") + "› Clique para comprar!");
        addItem(inventorySHOP_ATAQUE, Material.IRON_GOLEM_SPAWN_EGG, 1, "§f→ Golem de Ferro (5:00)", 12, ChatColor.of("#b4a7d6") + "Quantidade§8: " + ChatColor.of("#b4a7d6") + "1", "", ChatColor.of("#a2c4c9") + "Preço§8: §f120 FERROS", "", ChatColor.of("#ffd966") + "› Clique para comprar!");
        addItem(inventorySHOP_ATAQUE, Material.SILVERFISH_SPAWN_EGG, 1, "§7→ Traças (0:20)", 13, ChatColor.of("#b4a7d6") + "Quantidade§8: " + ChatColor.of("#b4a7d6") + "1", "", ChatColor.of("#a2c4c9") + "Preço§8: §f30 FERROS", "", ChatColor.of("#ffd966") + "› Clique para comprar!");
    }

    public static void ShopGUI_OUTROS() {
        inventorySHOP_OUTROS = Bukkit.createInventory(null, 54, "Loja - Outros");
        addItem(inventorySHOP_OUTROS, Material.BLACK_STAINED_GLASS_PANE, 1, "", 37, "");
        addItem(inventorySHOP_OUTROS, Material.BLACK_STAINED_GLASS_PANE, 1, "", 38, "");
        addItem(inventorySHOP_OUTROS, Material.BLACK_STAINED_GLASS_PANE, 1, "", 39, "");
        addItem(inventorySHOP_OUTROS, Material.BLACK_STAINED_GLASS_PANE, 1, "", 40, "");
        addItem(inventorySHOP_OUTROS, Material.BLACK_STAINED_GLASS_PANE, 1, "", 41, "");
        addItem(inventorySHOP_OUTROS, Material.BLACK_STAINED_GLASS_PANE, 1, "", 42, "");
        addItem(inventorySHOP_OUTROS, Material.GREEN_STAINED_GLASS_PANE, 1, "", 43, "");
        addItem(inventorySHOP_OUTROS, Material.NETHER_STAR, 1, "§e→ Menu principal", 46, "§7Clique para abrir o menu Principal!");
        addItem(inventorySHOP_OUTROS, Material.BRICKS, 1, "§d→ Blocos", 47, "§7Clique para abrir o menu de Blocos!");
        addItem(inventorySHOP_OUTROS, Material.GOLDEN_SWORD, 1, "§9→ Combate", 48, "§7Clique para abrir o menu de Combate!");
        addItem(inventorySHOP_OUTROS, Material.BREWING_STAND, 1, "§a→ Poções", 49, "§7Clique para abrir o menu de Poções!");
        addItem(inventorySHOP_OUTROS, Material.GOLDEN_PICKAXE, 1, "§c→ Ferramentas", 50, "§7Clique para abrir o menu de Ferramentas!");
        addItem(inventorySHOP_OUTROS, Material.TNT, 1, "§b→ Ataque", 51, "§7Clique para abrir o menu de Outros!");
        addItem(inventorySHOP_OUTROS, Material.CHEST_MINECART, 1, "§9→ Outros", 52, "§7Você está visualizando esse menu!");
        addItem(inventorySHOP_OUTROS, Material.WATER_BUCKET, 1, "§b→ Água", 10, ChatColor.of("#b4a7d6") + "Quantidade§8: " + ChatColor.of("#b4a7d6") + "1", "", ChatColor.of("#a2c4c9") + "Preço§8: §63 OUROS", "", ChatColor.of("#ffd966") + "› Clique para comprar!");
        addItem(inventorySHOP_OUTROS, Material.SPONGE, 1, "§3→ Esponja", 11, ChatColor.of("#b4a7d6") + "Quantidade§8: " + ChatColor.of("#b4a7d6") + "1", "", ChatColor.of("#a2c4c9") + "Preço§8: §63 OUROS", "", ChatColor.of("#ffd966") + "› Clique para comprar!");
        addItem(inventorySHOP_OUTROS, Material.ENDER_PEARL, 1, "§5→ Pérola do Ender", 12, ChatColor.of("#b4a7d6") + "Quantidade§8: " + ChatColor.of("#b4a7d6") + "1", "", ChatColor.of("#a2c4c9") + "Preço§8: §a4 ESMERALDAS", "", ChatColor.of("#ffd966") + "› Clique para comprar!");
        addItem(inventorySHOP_OUTROS, Material.ENDER_CHEST, 1, "§5→ Baú do Fim (0:05)", 13, ChatColor.of("#b4a7d6") + "Quantidade§8: " + ChatColor.of("#b4a7d6") + "1", "", ChatColor.of("#a2c4c9") + "Preço§8: §a2 ESMERALDAS", "", ChatColor.of("#ffd966") + "› Clique para comprar!");
        addItem(inventorySHOP_OUTROS, Material.BLUE_WOOL, 1, "§d→ Torre", 14, ChatColor.of("#b4a7d6") + "Quantidade§8: " + ChatColor.of("#b4a7d6") + "1", "", ChatColor.of("#a2c4c9") + "Preço§8: §f24 FERROS", "", ChatColor.of("#ffd966") + "› Clique para comprar!");
        addItem(inventorySHOP_OUTROS, Material.GOLDEN_APPLE, 1, "§d→ Maçã dourada", 15, ChatColor.of("#b4a7d6") + "Quantidade§8: " + ChatColor.of("#b4a7d6") + "1", "", ChatColor.of("#a2c4c9") + "Preço§8: §63 OUROS", "", ChatColor.of("#ffd966") + "› Clique para comprar!");
        addItem(inventorySHOP_OUTROS, Material.ENCHANTED_GOLDEN_APPLE, 1, "§d→ Maçã dourada Encantada", 16, ChatColor.of("#b4a7d6") + "Quantidade§8: " + ChatColor.of("#b4a7d6") + "1", "", ChatColor.of("#a2c4c9") + "Preço§8: §a3 ESMERALDAS", "", ChatColor.of("#ffd966") + "› Clique para comprar!");
    }

    public static void UpgradeGUI() {
        inventoryUPGRADE = Bukkit.createInventory(null, 27, "Upgrade Capture a Bandeira");
    }

    private static void addItem(Inventory inventory, Material material, int quantidade, String name, int slot, String... lore) {
        ItemStack itemStack = new ItemStack(material, quantidade);
        ItemMeta itemMeta = itemStack.getItemMeta();
        if (itemMeta != null) {
            itemMeta.setDisplayName(name);
            itemMeta.setLore(Arrays.asList(lore));

            itemStack.setItemMeta(itemMeta);
        }
        inventory.setItem(slot, itemStack);
    }

    private static void addItem(Inventory inventory, Material material, int quantidade, PotionType potionType, int duração, String name, int slot, String... lore) {
        ItemStack itemStack = new ItemStack(material, quantidade);
        PotionMeta itemMeta = (PotionMeta) itemStack.getItemMeta();
        if (itemMeta != null) {
            itemMeta.setDisplayName(name);
            itemMeta.setLore(Arrays.asList(lore));
            itemMeta.clearCustomEffects();
            itemMeta.removeCustomEffect(potionType.getEffectType());
            itemMeta.setBasePotionData(new PotionData(potionType, false, false));
            if (itemMeta.getBasePotionData().getType() == PotionType.FIRE_RESISTANCE) {
                itemMeta.addCustomEffect(new PotionEffect(PotionType.SPEED.getEffectType(), duração * 20, 0), true);
                itemMeta.addCustomEffect(new PotionEffect(PotionType.INVISIBILITY.getEffectType(), duração * 20, 0), true);
                itemMeta.addCustomEffect(new PotionEffect(PotionType.JUMP.getEffectType(), duração * 20, 0), true);
                itemMeta.addCustomEffect(new PotionEffect(potionType.getEffectType(), duração * 20, 0), true);
            } else {
                itemMeta.addCustomEffect(new PotionEffect(potionType.getEffectType(), duração * 20, 0), true);
            }
            itemStack.setItemMeta(itemMeta);
        }
        inventory.setItem(slot, itemStack);
    }

    private static void addItem(Inventory inventory, Material material, int quantidade, String name, Map<Enchantment, Integer> mapa , int slot, String... lore) {
        ItemStack itemStack = new ItemStack(material, quantidade);
        ItemMeta itemMeta = itemStack.getItemMeta();
        if (itemMeta != null) {
            itemMeta.setDisplayName(name);
            itemMeta.setLore(Arrays.asList(lore));
            for (Map.Entry<Enchantment, Integer> entry : mapa.entrySet()) {
                itemMeta.addEnchant(entry.getKey(), entry.getValue(), true);
            }
            itemStack.setItemMeta(itemMeta);
        }
        inventory.setItem(slot, itemStack);
    }

    public static void buyItem(InventoryClickEvent event, Material pag, int pagINT, Material item, int quantidade) {
        String din = "ERRO ITEM DE PAGAR";
        String colordin = "COLOR ERROR";
        Player player = (Player) event.getWhoClicked();
        String itemNAME = "ERRO ITEM NAME";
        boolean setItemMeta = false;
        switch (pag) {
            case IRON_INGOT: {
                din = "FERROS";
                colordin = "§f";
                break;
            }
            case GOLD_INGOT: {
                din = "OUROS";
                colordin = "§6";
                break;
            }
            case DIAMOND: {
                din = "DIAMANTE";
                colordin = "§b";
                break;
            }
            case EMERALD: {
                din = "ESMERALDA";
                colordin = "§2";
                break;
            }
        }
        switch (item) {
            case WHITE_WOOL: {
                itemNAME = "Lã Branca";
                break;
            }
            case TERRACOTTA: {
                itemNAME = "Terracota";
                break;
            }
            case END_STONE: {
                itemNAME = "Pedra do END";
                break;
            }
            case GLASS: {
                itemNAME = "Vidro";
                break;
            }
            case OAK_PLANKS: {
                itemNAME = "Madeira";
                break;
            }
            case LADDER: {
                itemNAME = "Escada";
                break;
            }
            case OBSIDIAN: {
                itemNAME = "Obisidiana";
                break;
            }
            case BOW: {
                if (!event.getCurrentItem().hasItemMeta() || !event.getCurrentItem().getItemMeta().hasEnchants()) {
                    itemNAME = "Arco normal";
                    setItemMeta = true;
                    break;
                }
                if (event.getCurrentItem().getItemMeta().hasEnchant(Enchantment.ARROW_DAMAGE) && !event.getCurrentItem().getItemMeta().hasEnchant(Enchantment.ARROW_KNOCKBACK)) {
                    itemNAME = "Arco com força I";
                    if (event.getRawSlot() > 35) return;
                    if (!player.getInventory().contains(pag, pagINT)) {
                        player.playSound(player, Sound.BLOCK_NOTE_BLOCK_BASS, 1.0f , 1.0f);
                        player.sendMessage("§8[§c!§8] §cVocê não tem §l" + pagINT + " " + din);
                        return;
                    }
                    ItemStack itemStack = new ItemStack(item, quantidade);
                    ItemStack itemIRON = new ItemStack(pag, pagINT);
                    ItemMeta meta = EventItem.flagITEM_bow_power().getItemMeta();
                    if (meta != null) {
                        meta.addEnchant(Enchantment.ARROW_DAMAGE, 1, true);
                        itemStack.setItemMeta(meta);
                    }
                    player.getInventory().removeItem(itemIRON);
                    player.getInventory().addItem(itemStack);
                    player.sendMessage("§8[§a!§8] §aVocê comprou §l" + itemNAME + " §apor " + colordin + pagINT + " " + din);
                    player.playSound(player, Sound.BLOCK_NOTE_BLOCK_BIT, 1.0f , 1.0f);
                    return;
                }
                if (event.getCurrentItem().getItemMeta().hasEnchant(Enchantment.ARROW_DAMAGE) && event.getCurrentItem().getItemMeta().hasEnchant(Enchantment.ARROW_KNOCKBACK)) {
                    itemNAME = "Arco com impacto I e força I";
                    if (event.getRawSlot() > 35) return;
                    if (!player.getInventory().contains(pag, pagINT)) {
                        player.playSound(player, Sound.BLOCK_NOTE_BLOCK_BASS, 1.0f , 1.0f);
                        player.sendMessage("§8[§c!§8] §cVocê não tem §l" + pagINT + " " + din);
                        return;
                    }
                    ItemStack itemStack = new ItemStack(item, quantidade);
                    ItemStack itemIRON = new ItemStack(pag, pagINT);
                    ItemMeta meta = EventItem.flagITEM_bow_power_punch().getItemMeta();
                    if (meta != null) {
                        meta.addEnchant(Enchantment.ARROW_DAMAGE, 1, true);
                        meta.addEnchant(Enchantment.ARROW_KNOCKBACK, 1, true);
                        itemStack.setItemMeta(meta);
                    }
                    player.getInventory().removeItem(itemIRON);
                    player.getInventory().addItem(itemStack);
                    player.playSound(player, Sound.BLOCK_NOTE_BLOCK_BIT, 1.0f , 1.0f);
                    player.sendMessage("§8[§a!§8] §aVocê comprou §l" + itemNAME + " §apor " + colordin + pagINT + " " + din);
                    return;
                }
                break;
            }
            case ARROW: {
                itemNAME = "Flechas";
                break;
            }
            case POTION: {
                PotionMeta meta = (PotionMeta) event.getCurrentItem().getItemMeta();
                if (meta != null) {
                    if (meta.getBasePotionData().getType() == PotionType.SPEED) {
                        itemNAME = "Poção de Speed (0:45)";
                        if (event.getRawSlot() > 35) return;
                        if (!player.getInventory().contains(pag, pagINT)) {
                            player.playSound(player, Sound.BLOCK_NOTE_BLOCK_BASS, 1.0f , 1.0f);
                            player.sendMessage("§8[§c!§8] §cVocê não tem §l" + pagINT + " " + din);
                            return;
                        }
                        ItemStack itemStack = new ItemStack(item, quantidade);
                        ItemStack itemIRON = new ItemStack(pag, pagINT);
                        PotionMeta itemMeta = (PotionMeta) itemStack.getItemMeta();
                        if (itemMeta != null) {
                            itemMeta.setBasePotionData(new PotionData(PotionType.SPEED, false, false));
                            itemMeta.addCustomEffect(new PotionEffect(PotionType.SPEED.getEffectType(), 45 * 20, 1), true);
                            itemStack.setItemMeta(itemMeta);
                        }
                        player.getInventory().removeItem(itemIRON);
                        player.getInventory().addItem(itemStack);
                        player.playSound(player, Sound.BLOCK_NOTE_BLOCK_BIT, 1.0f , 1.0f);
                        player.sendMessage("§8[§a!§8] §aVocê comprou §l" + itemNAME + " §apor " + colordin + pagINT + " " + din);
                        return;
                    } else if (meta.getBasePotionData().getType() == PotionType.JUMP) {
                        itemNAME = "Poção de Pulo (0:45)";
                        if (event.getRawSlot() > 35) return;
                        if (!player.getInventory().contains(pag, pagINT)) {
                            player.playSound(player, Sound.BLOCK_NOTE_BLOCK_BASS, 1.0f , 1.0f);
                            player.sendMessage("§8[§c!§8] §cVocê não tem §l" + pagINT + " " + din);
                            return;
                        }
                        ItemStack itemStack = new ItemStack(item, quantidade);
                        ItemStack itemIRON = new ItemStack(pag, pagINT);
                        PotionMeta itemMeta = (PotionMeta) itemStack.getItemMeta();
                        if (itemMeta != null) {
                            itemMeta.setBasePotionData(new PotionData(PotionType.JUMP, false, false));
                            itemMeta.addCustomEffect(new PotionEffect(PotionType.JUMP.getEffectType(), 45 * 20, 1), true);
                            itemStack.setItemMeta(itemMeta);
                        }
                        player.getInventory().removeItem(itemIRON);
                        player.getInventory().addItem(itemStack);
                        player.playSound(player, Sound.BLOCK_NOTE_BLOCK_BIT, 1.0f , 1.0f);
                        player.sendMessage("§8[§a!§8] §aVocê comprou §l" + itemNAME + " §apor " + colordin + pagINT + " " + din);
                        return;
                    } else if (meta.getBasePotionData().getType() == PotionType.INVISIBILITY) {
                        itemNAME = "Poção de Invisibilidade (0:30)";
                        if (event.getRawSlot() > 35) return;
                        if (!player.getInventory().contains(pag, pagINT)) {
                            player.playSound(player, Sound.BLOCK_NOTE_BLOCK_BASS, 1.0f , 1.0f);
                            player.sendMessage("§8[§c!§8] §cVocê não tem §l" + pagINT + " " + din);
                            return;
                        }
                        ItemStack itemStack = new ItemStack(item, quantidade);
                        ItemStack itemIRON = new ItemStack(pag, pagINT);
                        PotionMeta itemMeta = (PotionMeta) itemStack.getItemMeta();
                        if (itemMeta != null) {
                            itemMeta.setBasePotionData(new PotionData(PotionType.INVISIBILITY, false, false));
                            itemMeta.addCustomEffect(new PotionEffect(PotionType.INVISIBILITY.getEffectType(), 30 * 20, 1), true);
                            itemStack.setItemMeta(itemMeta);
                        }
                        player.getInventory().removeItem(itemIRON);
                        player.getInventory().addItem(itemStack);
                        player.playSound(player, Sound.BLOCK_NOTE_BLOCK_BIT, 1.0f , 1.0f);
                        player.sendMessage("§8[§a!§8] §aVocê comprou §l" + itemNAME + " §apor " + colordin + pagINT + " " + din);
                        return;
                    } else if (meta.getBasePotionData().getType() == PotionType.FIRE_RESISTANCE) {
                        itemNAME = "MIX de Poções (0:30)";
                        if (event.getRawSlot() > 35) return;
                        if (!player.getInventory().contains(pag, pagINT)) {
                            player.playSound(player, Sound.BLOCK_NOTE_BLOCK_BASS, 1.0f , 1.0f);
                            player.sendMessage("§8[§c!§8] §cVocê não tem §l" + pagINT + " " + din);
                            return;
                        }
                        ItemStack itemStack = new ItemStack(item, quantidade);
                        ItemStack itemIRON = new ItemStack(pag, pagINT);
                        PotionMeta itemMeta = (PotionMeta) itemStack.getItemMeta();
                        if (itemMeta != null) {
                            itemMeta.setDisplayName("MIX de Poções");
                            itemMeta.setBasePotionData(new PotionData(PotionType.FIRE_RESISTANCE, false, false));
                            itemMeta.clearCustomEffects();
                            itemMeta.addCustomEffect(new PotionEffect(PotionType.SPEED.getEffectType(), 30 * 20, 0), true);
                            itemMeta.addCustomEffect(new PotionEffect(PotionType.INVISIBILITY.getEffectType(), 30 * 20, 0), true);
                            itemMeta.addCustomEffect(new PotionEffect(PotionType.JUMP.getEffectType(), 30 * 20, 0), true);
                            itemMeta.addCustomEffect(new PotionEffect(PotionType.FIRE_RESISTANCE.getEffectType(), 30 * 20, 0), true);

                            itemStack.setItemMeta(itemMeta);
                        }
                        player.getInventory().removeItem(itemIRON);
                        player.getInventory().addItem(itemStack);
                        player.playSound(player, Sound.BLOCK_NOTE_BLOCK_BIT, 1.0f , 1.0f);
                        player.sendMessage("§8[§a!§8] §aVocê comprou §l" + itemNAME + " §apor " + colordin + pagINT + " " + din);
                        return;
                    }
                }
                break;
            }
            case MILK_BUCKET: {
                itemNAME = "Balde de Leite";
                break;
            }
            case STICK: {
                itemNAME = "Graveto de repulsão I";
                if (event.getRawSlot() > 35) return;
                if (!player.getInventory().contains(pag, pagINT)) {
                    player.playSound(player, Sound.BLOCK_NOTE_BLOCK_BASS, 1.0f , 1.0f);
                    player.sendMessage("§8[§c!§8] §cVocê não tem §l" + pagINT + " " + din);
                    return;
                }
                ItemStack itemStack = new ItemStack(item, quantidade);
                ItemStack itemIRON = new ItemStack(pag, pagINT);
                ItemMeta itemMeta = itemStack.getItemMeta();
                if (itemMeta != null) {
                    itemMeta.addEnchant(Enchantment.KNOCKBACK, 1, true);

                    itemStack.setItemMeta(itemMeta);
                }
                player.getInventory().removeItem(itemIRON);
                player.getInventory().addItem(itemStack);
                player.playSound(player, Sound.BLOCK_NOTE_BLOCK_BIT, 1.0f , 1.0f);
                player.sendMessage("§8[§a!§8] §aVocê comprou §l" + itemNAME + " §apor " + colordin + pagINT + " " + din);
                return;
            }
            case FLINT_AND_STEEL: {
                itemNAME = "Isqueiro";
                setItemMeta = true;
                break;
            }
            case SHEARS: {
                itemNAME = "Tesoura";
                setItemMeta = true;
                break;
            }
            case SHIELD: {
                itemNAME = "Escudo";
                setItemMeta = true;
                break;
            }
            case FIRE_CHARGE: {
                itemNAME = "Bola de Fogo";
                break;
            }
            case TNT: {
                itemNAME = "TNT";
                break;
            }
            case IRON_GOLEM_SPAWN_EGG: {
                itemNAME = "Spawn do Golem de Ferro (5:00)";
                break;
            }
            case SILVERFISH_SPAWN_EGG: {
                itemNAME = "Spawn de Traças (0:20)";
                break;
            }
            case WATER_BUCKET: {
                itemNAME = "Balde de Água";
                break;
            }
            case SPONGE: {
                itemNAME = "Esponja";
                break;
            }
            case ENDER_PEARL: {
                itemNAME = "Pérola do Ender";
                break;
            }
            case ENDER_CHEST: {
                itemNAME = "Baú do Fim";
                break;
            }
            case BLUE_WOOL: {
                itemNAME = "Torre";
                setItemMeta = true;
                break;
            }
            case GOLDEN_APPLE: {
                itemNAME = "Maçã dourada";
                break;
            }
            case ENCHANTED_GOLDEN_APPLE: {
                itemNAME = "Maçã dourada Encantada";
                break;
            }
        }
        if (event.getRawSlot() > 35) return;
        if (!player.getInventory().contains(pag, pagINT)) {
            player.playSound(player, Sound.BLOCK_NOTE_BLOCK_BASS, 1.0f , 1.0f);
            player.sendMessage("§8[§c!§8] §cVocê não tem §l" + pagINT + " " + din);
            return;
        }
        ItemStack itemStack = new ItemStack(item, quantidade);
        if (itemNAME.equalsIgnoreCase("Lã Branca")) {
            itemStack = new ItemStack(Material.ORANGE_WOOL, quantidade);
            if (CaptureBandeiraEvent.time.get(player).equals("Green")) {
                itemStack = new ItemStack(Material.LIME_WOOL, quantidade);
            }
        }
        ItemStack itemIRON = new ItemStack(pag, pagINT);
        if (setItemMeta) {
            ItemMeta meta = null;
            switch (itemNAME) {
                case "Isqueiro": {
                    meta = EventItem.flagITEM_flint_and_steel().getItemMeta();
                    break;
                }
                case "Tesoura": {
                    meta = EventItem.flagITEM_shears().getItemMeta();
                    break;
                }
                case "Arco normal": {
                    meta = EventItem.flagITEM_bow().getItemMeta();
                    break;
                }
                case "Escudo": {
                    meta = EventItem.flagITEM_shield().getItemMeta();
                    break;
                }
                case "Torre": {
                    meta = EventItem.flagITEM_tower().getItemMeta();
                    break;
                }
            }
            if (meta != null) {
                itemStack.setItemMeta(meta);
            }
        }
        player.getInventory().removeItem(itemIRON);
        player.getInventory().addItem(itemStack);
        player.playSound(player, Sound.BLOCK_NOTE_BLOCK_BIT, 1.0f , 1.0f);
        player.sendMessage("§8[§a!§8] §aVocê comprou §l" + itemNAME + " §apor " + colordin + pagINT + " " + din);

    }

    public static void buyItem(InventoryClickEvent event, Material pag, int pagINT, Material item, int quantidade, boolean equip) {
        String din = "ERRO ITEM DE PAGAR";
        String colordin = "COLOR ERROR";
        Player player = (Player) event.getWhoClicked();
        if (!equip) return;
        String itemNAME = "ERRO ITEM NAME";
        switch (pag) {
            case IRON_INGOT: {
                din = "FERROS";
                colordin = "§f";
                break;
            }
            case GOLD_INGOT: {
                din = "OUROS";
                colordin = "§6";
                break;
            }
            case DIAMOND: {
                din = "DIAMANTE";
                colordin = "§b";
                break;
            }
            case EMERALD: {
                din = "ESMERALDA";
                colordin = "§2";
                break;
            }
        }
        switch (item) {
            case STONE_SWORD: {
                itemNAME = "Espada de Pedra";
                break;
            }
            case IRON_SWORD: {
                itemNAME = "Espada de Ferro";
                break;
            }
            case DIAMOND_SWORD: {
                itemNAME = "Espada de Diamante";
                break;
            }
            case WOODEN_PICKAXE: {
                itemNAME = "Picareta de Madeira";
                break;
            }
            case IRON_PICKAXE: {
                itemNAME = "Picareta de Ferro";
                break;
            }
            case GOLDEN_PICKAXE: {
                itemNAME = "Picareta de Ouro";
                break;
            }
            case DIAMOND_PICKAXE: {
                itemNAME = "Picareta de Diamante";
                break;
            }
            case WOODEN_AXE: {
                itemNAME = "Machado de Madeira";
                break;
            }
            case IRON_AXE: {
                itemNAME = "Machado de Ferro";
                break;
            }
            case GOLDEN_AXE: {
                itemNAME = "Machado de Ouro";
                break;
            }
            case DIAMOND_AXE: {
                itemNAME = "Machado de Diamante";
                break;
            }
            case CHAINMAIL_BOOTS: {
                itemNAME = "Armadura de Cota de Malha";
                break;
            }
            case IRON_BOOTS: {
                itemNAME = "Armadura de Ferro";
                break;
            }
            case DIAMOND_BOOTS: {
                itemNAME = "Armadura de Diamante";
                break;
            }
        }
        if (item.equals(Material.STONE_SWORD) || item.equals(Material.IRON_SWORD) || item.equals(Material.DIAMOND_SWORD)) {
            if (event.getRawSlot() > 35) return;
            if (!player.getInventory().contains(pag, pagINT)) {
                player.playSound(player, Sound.BLOCK_NOTE_BLOCK_BASS, 1.0f , 1.0f);
                player.sendMessage("§8[§c!§8] §cVocê não tem §l" + pagINT + " " + din);
                return;
            }
            boolean addITEM = false;
            switch (item) {
                case STONE_SWORD: {
                    if (upgradeSWORD.get(player) >= 1) {
                        addITEM = true;
                        break;
                    } else {
                        addITEM= false;
                    }
                    break;
                }
                case IRON_SWORD: {
                    if (upgradeSWORD.get(player) >= 2) {
                        addITEM = true;
                        break;
                    } else {
                        addITEM= false;
                    }
                    break;
                }
                case DIAMOND_SWORD: {
                    if (upgradeSWORD.get(player) >= 3) {
                        addITEM = true;
                        break;
                    } else {
                        addITEM = false;
                    }
                    break;
                }
            }
            if (player.getInventory().contains(item) || addITEM) {
                ItemStack itemStack = new ItemStack(item, quantidade);
                ItemStack itemIRON = new ItemStack(pag, pagINT);
                switch (item) {
                    case STONE_SWORD: {
                        ItemMeta meta = EventItem.flagITEM_Stone_sword().getItemMeta();
                        if (meta != null) {
                            itemStack.setItemMeta(meta);
                        }
                        break;
                    }
                    case IRON_SWORD: {
                        ItemMeta meta = EventItem.flagITEM_Iron_sword().getItemMeta();
                        if (meta != null) {
                            itemStack.setItemMeta(meta);
                        }
                        break;
                    }
                    case DIAMOND_SWORD: {
                        ItemMeta meta = EventItem.flagITEM_Diamond_sword().getItemMeta();
                        if (meta != null) {
                            itemStack.setItemMeta(meta);
                        }
                        break;
                    }
                }
                player.getInventory().removeItem(itemIRON);
                player.getInventory().addItem(itemStack);
                player.playSound(player, Sound.BLOCK_NOTE_BLOCK_BIT, 1.0f , 1.0f);
                player.sendMessage("§8[§a!§8] §aVocê comprou §l" + itemNAME + " §apor " + colordin + pagINT + " " + din);
                return;
            }
            ItemStack itemStack = new ItemStack(item, quantidade);
            ItemStack itemIRON = new ItemStack(pag, pagINT);
            player.getInventory().removeItem(itemIRON);
            switch (item) {
                case STONE_SWORD: {
                    upgradeSWORD.put(player, 1);
                    ItemMeta meta = EventItem.flagITEM_Stone_sword().getItemMeta();
                    if (meta != null) {
                        itemStack.setItemMeta(meta);
                    }
                    break;
                }
                case IRON_SWORD: {
                    upgradeSWORD.put(player, 2);
                    ItemMeta meta = EventItem.flagITEM_Iron_sword().getItemMeta();
                    if (meta != null) {
                        itemStack.setItemMeta(meta);
                    }
                    break;
                }
                case DIAMOND_SWORD: {
                    upgradeSWORD.put(player, 3);
                    ItemMeta meta = EventItem.flagITEM_Diamond_sword().getItemMeta();
                    if (meta != null) {
                        itemStack.setItemMeta(meta);
                    }
                    break;
                }
            }
            player.getInventory().setItem(0, itemStack);
            player.playSound(player, Sound.BLOCK_NOTE_BLOCK_BIT, 1.0f , 1.0f);
            player.sendMessage("§8[§a!§8] §aVocê comprou §l" + itemNAME + " §apor " + colordin + pagINT + " " + din);
        }
        if (item.equals(Material.WOODEN_AXE) || item.equals(Material.IRON_AXE) || item.equals(Material.GOLDEN_AXE) || item.equals(Material.DIAMOND_AXE)) {
            if (event.getRawSlot() > 35) return;
            if (!player.getInventory().contains(pag, pagINT)) {
                player.playSound(player, Sound.BLOCK_NOTE_BLOCK_BASS, 1.0f , 1.0f);
                player.sendMessage("§8[§c!§8] §cVocê não tem §l" + pagINT + " " + din);
                return;
            }
            boolean addITEM = false;
            switch (item) {
                case WOODEN_AXE: {
                    if (upgradeAXE.get(player) >= 1) {
                        addITEM = true;
                        break;
                    } else {
                        addITEM= false;
                    }
                    break;
                }
                case IRON_AXE: {
                    if (upgradeAXE.get(player) >= 2) {
                        addITEM = true;
                        break;
                    } else {
                        addITEM= false;
                    }
                    break;
                }
                case GOLDEN_AXE: {
                    if (upgradeAXE.get(player) >= 3) {
                        addITEM = true;
                        break;
                    } else {
                        addITEM= false;
                    }
                    break;
                }
                case DIAMOND_AXE: {
                    if (upgradeAXE.get(player) >= 4) {
                        addITEM = true;
                        break;
                    } else {
                        addITEM = false;
                    }
                    break;
                }
            }
            if (player.getInventory().contains(item) || addITEM) {
                player.playSound(player, Sound.BLOCK_NOTE_BLOCK_BASS, 1.0f , 1.0f);
                player.sendMessage("§8[§c!§8] §cVocê já tem um machado melhor.");
                return;
            }
            ItemStack itemStack = new ItemStack(item, quantidade);
            ItemStack itemIRON = new ItemStack(pag, pagINT);
            ItemMeta meta = null;
            switch (item) {
                case WOODEN_AXE: {
                    meta = EventItem.flagITEM_Wooden_axe().getItemMeta();
                    upgradeAXE.put(player, 1);
                    if (meta != null) {
                        meta.addEnchant(Enchantment.DIG_SPEED, 1, true);
                        itemStack.setItemMeta(meta);
                    }
                    break;
                }
                case IRON_AXE: {
                    meta = EventItem.flagITEM_Iron_axe().getItemMeta();
                    upgradeAXE.put(player, 2);
                    if (meta != null) {
                        meta.addEnchant(Enchantment.DIG_SPEED, 2, true);
                        itemStack.setItemMeta(meta);
                    }
                    break;
                }
                case GOLDEN_AXE: {
                    meta = EventItem.flagITEM_Golden_axe().getItemMeta();
                    upgradeAXE.put(player, 3);
                    if (meta != null) {
                        meta.addEnchant(Enchantment.DIG_SPEED, 3, true);
                        itemStack.setItemMeta(meta);
                    }
                    break;
                }
                case DIAMOND_AXE: {
                    meta = EventItem.flagITEM_Diamond_axe().getItemMeta();
                    upgradeAXE.put(player, 4);
                    if (meta != null) {
                        meta.addEnchant(Enchantment.DIG_SPEED, 3, true);
                        meta.addEnchant(Enchantment.DAMAGE_ALL, 1, true);
                        itemStack.setItemMeta(meta);
                    }
                    break;
                }
            }
            if (player.getInventory().contains(Material.DIAMOND_AXE) || player.getInventory().contains(Material.GOLDEN_AXE) || player.getInventory().contains(Material.IRON_AXE) || player.getInventory().contains(Material.WOODEN_AXE)) {
                for (int slot = 0; slot < player.getInventory().getSize(); slot++) {
                    ItemStack items = player.getInventory().getItem(slot);
                    if (items != null &&
                            (items.getType() == Material.DIAMOND_AXE ||
                                    items.getType() == Material.GOLDEN_AXE ||
                                    items.getType() == Material.IRON_AXE ||
                                    items.getType() == Material.WOODEN_AXE)) {
                        player.getInventory().removeItem(itemIRON);
                        player.getInventory().setItem(slot, itemStack);
                        player.playSound(player, Sound.BLOCK_NOTE_BLOCK_BIT, 1.0f , 1.0f);
                        player.sendMessage("§8[§a!§8] §aVocê comprou §l" + itemNAME + " §apor " + colordin + pagINT + " " + din);
                        return;
                    }
                }
                return;
            }
            player.getInventory().removeItem(itemIRON);
            player.getInventory().addItem(itemStack);
            player.playSound(player, Sound.BLOCK_NOTE_BLOCK_BIT, 1.0f , 1.0f);
            player.sendMessage("§8[§a!§8] §aVocê comprou §l" + itemNAME + " §apor " + colordin + pagINT + " " + din);
        }
        if (item.equals(Material.WOODEN_PICKAXE) || item.equals(Material.IRON_PICKAXE) || item.equals(Material.GOLDEN_PICKAXE) || item.equals(Material.DIAMOND_PICKAXE)) {
            if (event.getRawSlot() > 35) return;
            if (!player.getInventory().contains(pag, pagINT)) {
                player.playSound(player, Sound.BLOCK_NOTE_BLOCK_BASS, 1.0f , 1.0f);
                player.sendMessage("§8[§c!§8] §cVocê não tem §l" + pagINT + " " + din);
                return;
            }
            boolean addITEM = false;
            switch (item) {
                case WOODEN_PICKAXE: {
                    if (upgradePICKAXE.get(player) >= 1) {
                        addITEM = true;
                        break;
                    } else {
                        addITEM= false;
                    }
                    break;
                }
                case IRON_PICKAXE: {
                    if (upgradePICKAXE.get(player) >= 2) {
                        addITEM = true;
                        break;
                    } else {
                        addITEM= false;
                    }
                    break;
                }
                case GOLDEN_PICKAXE: {
                    if (upgradePICKAXE.get(player) >= 3) {
                        addITEM = true;
                        break;
                    } else {
                        addITEM= false;
                    }
                    break;
                }
                case DIAMOND_PICKAXE: {
                    if (upgradePICKAXE.get(player) >= 4) {
                        addITEM = true;
                        break;
                    } else {
                        addITEM = false;
                    }
                    break;
                }
            }
            if (player.getInventory().contains(item) || addITEM) {
                player.playSound(player, Sound.BLOCK_NOTE_BLOCK_BASS, 1.0f , 1.0f);
                player.sendMessage("§8[§c!§8] §cVocê já tem uma picareta melhor.");
                return;
            }
            ItemStack itemStack = new ItemStack(item, quantidade);
            ItemStack itemIRON = new ItemStack(pag, pagINT);
            ItemMeta meta = null;
            switch (item) {
                case WOODEN_PICKAXE: {
                    meta = EventItem.flagITEM_Wooden_pickaxe().getItemMeta();
                    upgradePICKAXE.put(player, 1);
                    if (meta != null) {
                        meta.addEnchant(Enchantment.DIG_SPEED, 1, true);
                        itemStack.setItemMeta(meta);
                    }
                    break;
                }
                case IRON_PICKAXE: {
                    meta = EventItem.flagITEM_Iron_pickaxe().getItemMeta();
                    upgradePICKAXE.put(player, 2);
                    if (meta != null) {
                        meta.addEnchant(Enchantment.DIG_SPEED, 2, true);
                        itemStack.setItemMeta(meta);
                    }
                    break;
                }
                case GOLDEN_PICKAXE: {
                    meta = EventItem.flagITEM_Golden_pickaxe().getItemMeta();
                    upgradePICKAXE.put(player, 3);
                    if (meta != null) {
                        meta.addEnchant(Enchantment.DIG_SPEED, 3, true);
                        itemStack.setItemMeta(meta);
                    }
                    break;
                }
                case DIAMOND_PICKAXE: {
                    meta = EventItem.flagITEM_Diamond_pickaxe().getItemMeta();
                    upgradePICKAXE.put(player, 4);
                    if (meta != null) {
                        meta.addEnchant(Enchantment.DIG_SPEED, 3, true);
                        itemStack.setItemMeta(meta);
                    }
                    break;
                }
            }
            if (player.getInventory().contains(Material.DIAMOND_PICKAXE) || player.getInventory().contains(Material.GOLDEN_PICKAXE) || player.getInventory().contains(Material.IRON_PICKAXE) || player.getInventory().contains(Material.WOODEN_PICKAXE)) {
                for (int slot = 0; slot < player.getInventory().getSize(); slot++) {
                    ItemStack items = player.getInventory().getItem(slot);
                    if (items != null &&
                            (items.getType() == Material.DIAMOND_PICKAXE ||
                            items.getType() == Material.GOLDEN_PICKAXE ||
                            items.getType() == Material.IRON_PICKAXE ||
                            items.getType() == Material.WOODEN_PICKAXE)) {
                        player.getInventory().removeItem(itemIRON);
                        player.getInventory().setItem(slot, itemStack);
                        player.playSound(player, Sound.BLOCK_NOTE_BLOCK_BIT, 1.0f , 1.0f);
                        player.sendMessage("§8[§a!§8] §aVocê comprou §l" + itemNAME + " §apor " + colordin + pagINT + " " + din);
                        return;
                    }
                }
                return;
            }
            player.getInventory().removeItem(itemIRON);
            player.getInventory().addItem(itemStack);
            player.playSound(player, Sound.BLOCK_NOTE_BLOCK_BIT, 1.0f , 1.0f);
            player.sendMessage("§8[§a!§8] §aVocê comprou §l" + itemNAME + " §apor " + colordin + pagINT + " " + din);
        }
        if (item.equals(Material.CHAINMAIL_BOOTS) || item.equals(Material.IRON_BOOTS) || item.equals(Material.DIAMOND_BOOTS)) {
            if (event.getRawSlot() > 35) return;
            if (!player.getInventory().contains(pag, pagINT)) {
                player.playSound(player, Sound.BLOCK_NOTE_BLOCK_BASS, 1.0f , 1.0f);
                player.sendMessage("§8[§c!§8] §cVocê não tem §l" + pagINT + " " + din);
                return;
            }
            boolean addITEM = false;
            switch (item) {
                case CHAINMAIL_BOOTS: {
                    if (upgradeArmor.get(player) >= 1) {
                        addITEM = true;
                        break;
                    } else {
                        addITEM= false;
                    }
                    break;
                }
                case IRON_BOOTS: {
                    if (upgradeArmor.get(player) >= 2) {
                        addITEM = true;
                        break;
                    } else {
                        addITEM= false;
                    }
                    break;
                }
                case DIAMOND_BOOTS: {
                    if (upgradeArmor.get(player) >= 3) {
                        addITEM = true;
                        break;
                    } else {
                        addITEM = false;
                    }
                    break;
                }
            }
            if (player.getInventory().contains(item) || addITEM) {
                player.playSound(player, Sound.BLOCK_NOTE_BLOCK_BASS, 1.0f , 1.0f);
                player.sendMessage("§8[§c!§8] §cVocê já tem uma armadura melhor.");
                return;
            }
            ItemStack itemIRON = new ItemStack(pag, pagINT);
            player.getInventory().removeItem(itemIRON);
            switch (item) {
                case CHAINMAIL_BOOTS: {
                    upgradeArmor.put(player, 1);
                    player.getInventory().setLeggings(EventItem.FlagITEM_CHAILMALL_LEGGING);
                    player.getInventory().setBoots(EventItem.FlagITEM_CHAILMALL_BOOTS);
                    break;
                }
                case IRON_BOOTS: {
                    upgradeArmor.put(player, 2);
                    player.getInventory().setLeggings(EventItem.FlagITEM_IRON_LEGGING);
                    player.getInventory().setBoots(EventItem.FlagITEM_IRON_BOOTS);
                    break;
                }
                case DIAMOND_BOOTS: {
                    upgradeArmor.put(player, 3);
                    player.getInventory().setLeggings(EventItem.FlagITEM_DIAMOND_LEGGING);
                    player.getInventory().setBoots(EventItem.FlagITEM_DIAMOND_BOOTS);
                    break;
                }
            }
            player.playSound(player, Sound.BLOCK_NOTE_BLOCK_BIT, 1.0f , 1.0f);
            player.sendMessage("§8[§a!§8] §aVocê comprou §l" + itemNAME + " §apor " + colordin + pagINT + " " + din);

        }
    }

    public static Inventory getInventorySHOP_INICIAL() {
        return inventorySHOP_INICIAL;
    }

    public static Inventory getInventorySHOP_BLOCOS() {
        return inventorySHOP_BLOCOS;
    }

    public static Inventory getInventorySHOP_COMBATE() {
        return inventorySHOP_COMBATE;
    }

    public static Inventory getInventorySHOP_POÇOES() {
        return inventorySHOP_POÇOES;
    }

    public static Inventory getInventorySHOP_FERRAMENTAS() {
        return inventorySHOP_FERRAMENTAS;
    }

    public static Inventory getInventorySHOP_ATAQUE() {
        return inventorySHOP_ATAQUE;
    }

    public static Inventory getInventorySHOP_OUTROS() {
        return inventorySHOP_OUTROS;
    }

    public static Inventory getInventoryUPGRADE() {
        return inventoryUPGRADE;
    }
}
