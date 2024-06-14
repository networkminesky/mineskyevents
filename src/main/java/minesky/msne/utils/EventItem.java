package minesky.msne.utils;

import minesky.msne.MineSkyEvents;
import minesky.msne.config.DataManager;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Color;
import org.bukkit.DyeColor;
import org.bukkit.Material;
import org.bukkit.block.banner.Pattern;
import org.bukkit.block.banner.PatternType;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.*;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class EventItem {
    public static ItemStack SpleefITEM;
    public static ItemStack BedLeave;
    public static ItemStack SumoITEM;
    public static ItemStack BarcoITEM;
    public static ItemStack TNTHEAD;
    public static ItemStack CheckPoint;
    public static ItemStack FlagORANGE;
    public static ItemStack FlagLIME;
    public static ItemStack FlagITEM_WOODEN_SWORD;
    public static ItemStack FlagITEM_STONE_SWORD;
    public static ItemStack FlagITEM_IRON_SWORD;
    public static ItemStack FlagITEM_DIAMOND_SWORD;
    public static ItemStack FlagITEM_WOODEN_AXE;
    public static ItemStack FlagITEM_IRON_AXE;
    public static ItemStack FlagITEM_GOLDEN_AXE;
    public static ItemStack FlagITEM_DIAMOND_AXE;
    public static ItemStack FlagITEM_WOODEN_PICKAXE;
    public static ItemStack FlagITEM_IRON_PICKAXE;
    public static ItemStack FlagITEM_GOLDEN_PICKAXE;
    public static ItemStack FlagITEM_DIAMOND_PICKAXE;
    public static ItemStack FlagITEM_ARMOR_HELMET_LIME;
    public static ItemStack FlagITEM_ARMOR_CHESTPLATE_LIME;
    public static ItemStack FlagITEM_ARMOR_LEGGING_LIME;
    public static ItemStack FlagITEM_ARMOR_BOTTS_LIME;
    public static ItemStack FlagITEM_ARMOR_HELMET_ORANGE;
    public static ItemStack FlagITEM_ARMOR_CHESTPLATE_ORANGE;
    public static ItemStack FlagITEM_ARMOR_LEGGING_ORANGE;
    public static ItemStack FlagITEM_ARMOR_BOTTS_ORANGE;
    public static ItemStack FlagITEM_CHAILMALL_BOOTS;
    public static ItemStack FlagITEM_CHAILMALL_LEGGING;
    public static ItemStack FlagITEM_IRON_BOOTS;
    public static ItemStack FlagITEM_IRON_LEGGING;
    public static ItemStack FlagITEM_DIAMOND_BOOTS;
    public static ItemStack FlagITEM_DIAMOND_LEGGING;
    public static ItemStack FlagITEM_BOW;
    public static ItemStack FlagITEM_BOW_POWER;
    public static ItemStack FlagITEM_BOW_POWER_PUNCH;
    public static ItemStack FlagITEM_FLINT_AND_STEEL;
    public static ItemStack FlagITEM_SHEARS;
    public static ItemStack FlagITEM_SHIELD;
    public static ItemStack FlagITEM_TOWER;

    public static ItemStack Shovel() {
        ItemStack pa = new ItemStack(Material.GOLDEN_SHOVEL);
        ItemMeta meta = pa.getItemMeta();

        if (meta != null) {
            meta.setDisplayName("§eQuebra TUDO!");
            meta.setUnbreakable(true);
            meta.addEnchant(Enchantment.DIG_SPEED, 2, true);

            List<String> lore = new ArrayList<>();
            lore.add(ChatColor.of("#d1be86") + "Item único do evento Spleef");
            lore.add("");
            lore.add("§8| " + ChatColor.of("#d1be86") + "Descrição§8:");
            lore.add(ChatColor.of("#dfb32e") + "  Quebre os blocos de neve do evento.");
            meta.setLore(lore);

            pa.setItemMeta(meta);
        }

        return pa;
    }

    public static ItemStack Stick() {
        ItemStack stick = new ItemStack(Material.STICK);
        ItemMeta meta = stick.getItemMeta();

        if (meta != null) {
            meta.setDisplayName(ChatColor.of("#F44336") + "Irei te pegar!");
            meta.setUnbreakable(true);
            meta.addEnchant(Enchantment.KNOCKBACK, 3, true);

            List<String> lore = new ArrayList<>();
            lore.add(ChatColor.of("#EA9999") + "Item único do evento SUMO");
            lore.add("");
            lore.add("§8| " + ChatColor.of("#EA9999") + "Descrição§8:");
            lore.add(ChatColor.of("#E06666") + "  Empurre os jogadores da plataforma de gelo.");
            meta.setLore(lore);

            stick.setItemMeta(meta);
        }

        return stick;
    }

    public static ItemStack Boat() {
        ItemStack boat = new ItemStack(Material.JUNGLE_BOAT);
        ItemMeta meta = boat.getItemMeta();

        if (meta != null) {
            meta.setDisplayName("§dCarro esportivo");
            meta.setUnbreakable(true);

            List<String> lore = new ArrayList<>();
            lore.add(ChatColor.of("#d5a6bd") + "Item único do evento Corrida de Barco");
            lore.add("");
            lore.add("§8| " + ChatColor.of("#d5a6bd") + "Descrição§8:");
            lore.add(ChatColor.of("#c27ba0") + "  Utilize esse barco para ganhar a corrida.");
            meta.setLore(lore);

            boat.setItemMeta(meta);
        }

        return boat;
    }

    public static ItemStack Tnt() {
        ItemStack tnt = new ItemStack(Material.TNT);
        ItemMeta meta = tnt.getItemMeta();

        if (meta != null) {
            meta.setDisplayName("§c§lBatata Quente!");
            meta.addEnchant(Enchantment.MENDING, 1, true);
            meta.setUnbreakable(true);

            List<String> lore = new ArrayList<>();
            lore.add(ChatColor.of("#dc6565") + "Item único do evento TNT-TAG");
            lore.add("");
            lore.add("§8| "+ ChatColor.of("#dc6565") + "Descrição§8:");
            lore.add(ChatColor.of("#bc3b3b") + "  Caso tenha ela na sua cabeça e passar entre 15 a 30 segundos ela explode.");
            meta.setLore(lore);

            tnt.setItemMeta(meta);
        }

        return tnt;
    }

    public static ItemStack Plate() {
        ItemStack plate = new ItemStack(Material.LIGHT_WEIGHTED_PRESSURE_PLATE);
        ItemMeta meta = plate.getItemMeta();

        if (meta != null) {
            meta.setDisplayName("§e→ CheckPoint");
            meta.setUnbreakable(true);

            List<String> lore = new ArrayList<>();
            lore.add(ChatColor.of("#ffe599") + "Item únido do evento Corrida de Parapente");
            lore.add("");
            lore.add("§8| " + ChatColor.of("#ffe599")  + "Descrição§8:");
            lore.add(ChatColor.of("#ffd966") + "  Use ele quando quiser voltar para o checkpoint do Parapente");
            meta.setLore(lore);

            plate.setItemMeta(meta);
        }

        return plate;
    }

    public static ItemStack createBed() {
        ItemStack bed = new ItemStack(Material.RED_BED);
        ItemMeta meta = bed.getItemMeta();

        if (meta != null) {
            meta.setDisplayName("§c§l→ Clique para sair!");
            meta.setUnbreakable(true);

            List<String> lore = new ArrayList<>();
            lore.add("");
            lore.add("§8| " + ChatColor.of("#ed5858") + "Descrição§8:");
            lore.add(ChatColor.of("#ae4141") + "  Sair do evento.");
            meta.setLore(lore);

            bed.setItemMeta(meta);
        }

        return bed;
    }

    public static ItemStack flagITEM_Wooden_sword() {
        ItemStack ws = new ItemStack(Material.WOODEN_SWORD);
        ItemMeta meta = ws.getItemMeta();

        if (meta != null) {
            meta.setDisplayName(ChatColor.of("#2493b4") + "\uD83D\uDDE1 Espada de Madeira!");
            meta.setUnbreakable(true);

            List<String> lore = new ArrayList<>();
            lore.add(ChatColor.of("#a2c4c9") +"Item único do evento Capture a Bandeira");
            lore.add("");
            lore.add("§8| " + ChatColor.of("#a2c4c9") + "Descrição§8:");
            lore.add("  " + ChatColor.of("#639da6") + "Mate players do outro time para chegar até a bandeira.");
            meta.setLore(lore);

            ws.setItemMeta(meta);
        }

        return ws;
    }

    public static ItemStack flagITEM_Stone_sword() {
        ItemStack ws = new ItemStack(Material.STONE_SWORD);
        ItemMeta meta = ws.getItemMeta();

        if (meta != null) {
            meta.setDisplayName(ChatColor.of("#2493b4") + "\uD83D\uDDE1 Espada de Pedra!");
            meta.setUnbreakable(true);

            List<String> lore = new ArrayList<>();
            lore.add(ChatColor.of("#a2c4c9") +"Item único do evento Capture a Bandeira");
            lore.add("");
            lore.add("§8| " + ChatColor.of("#a2c4c9") + "Descrição§8:");
            lore.add("  " + ChatColor.of("#639da6") + "Mate players do outro time para chegar até a bandeira.");
            meta.setLore(lore);

            ws.setItemMeta(meta);
        }

        return ws;
    }

    public static ItemStack flagITEM_Iron_sword() {
        ItemStack ws = new ItemStack(Material.IRON_SWORD);
        ItemMeta meta = ws.getItemMeta();

        if (meta != null) {
            meta.setDisplayName(ChatColor.of("#2493b4") + "\uD83D\uDDE1 Espada de Ferro!");
            meta.setUnbreakable(true);

            List<String> lore = new ArrayList<>();
            lore.add(ChatColor.of("#a2c4c9") +"Item único do evento Capture a Bandeira");
            lore.add("");
            lore.add("§8| " + ChatColor.of("#a2c4c9") + "Descrição§8:");
            lore.add("  " + ChatColor.of("#639da6") + "Mate players do outro time para chegar até a bandeira.");
            meta.setLore(lore);

            ws.setItemMeta(meta);
        }

        return ws;
    }

    public static ItemStack flagITEM_Diamond_sword() {
        ItemStack ws = new ItemStack(Material.DIAMOND_SWORD);
        ItemMeta meta = ws.getItemMeta();

        if (meta != null) {
            meta.setDisplayName(ChatColor.of("#2493b4") + "\uD83D\uDDE1 Espada de Diamante!");
            meta.setUnbreakable(true);

            List<String> lore = new ArrayList<>();
            lore.add(ChatColor.of("#a2c4c9") +"Item único do evento Capture a Bandeira");
            lore.add("");
            lore.add("§8| " + ChatColor.of("#a2c4c9") + "Descrição§8:");
            lore.add("  " + ChatColor.of("#639da6") + "Mate players do outro time para chegar até a bandeira.");
            meta.setLore(lore);

            ws.setItemMeta(meta);
        }

        return ws;
    }

    public static ItemStack flagITEM_Wooden_axe() {
        ItemStack ws = new ItemStack(Material.WOODEN_AXE);
        ItemMeta meta = ws.getItemMeta();

        if (meta != null) {
            meta.setDisplayName(ChatColor.of("#2493b4") + "\uD83E\uDE93 Machado de Madeira!");
            meta.setUnbreakable(true);

            List<String> lore = new ArrayList<>();
            lore.add(ChatColor.of("#a2c4c9") +"Item único do evento Capture a Bandeira");
            lore.add("");
            lore.add("§8| " + ChatColor.of("#a2c4c9") + "Descrição§8:");
            lore.add("  " + ChatColor.of("#639da6") + "Quebra blocos de madeira com ele. Também pode ser usado nas lutas contra jogadores");
            meta.setLore(lore);

            ws.setItemMeta(meta);
        }

        return ws;
    }

    public static ItemStack flagITEM_Iron_axe() {
        ItemStack ws = new ItemStack(Material.IRON_AXE);
        ItemMeta meta = ws.getItemMeta();

        if (meta != null) {
            meta.setDisplayName(ChatColor.of("#2493b4") + "\uD83E\uDE93 Machado de Ferro!");
            meta.setUnbreakable(true);

            List<String> lore = new ArrayList<>();
            lore.add(ChatColor.of("#a2c4c9") +"Item único do evento Capture a Bandeira");
            lore.add("");
            lore.add("§8| " + ChatColor.of("#a2c4c9") + "Descrição§8:");
            lore.add("  " + ChatColor.of("#639da6") + "Quebra blocos de madeira com ele. Também pode ser usado nas lutas contra jogadores");
            meta.setLore(lore);

            ws.setItemMeta(meta);
        }

        return ws;
    }

    public static ItemStack flagITEM_Golden_axe() {
        ItemStack ws = new ItemStack(Material.GOLDEN_AXE);
        ItemMeta meta = ws.getItemMeta();

        if (meta != null) {
            meta.setDisplayName(ChatColor.of("#2493b4") + "\uD83E\uDE93 Machado de Ouro!");
            meta.setUnbreakable(true);

            List<String> lore = new ArrayList<>();
            lore.add(ChatColor.of("#a2c4c9") +"Item único do evento Capture a Bandeira");
            lore.add("");
            lore.add("§8| " + ChatColor.of("#a2c4c9") + "Descrição§8:");
            lore.add("  " + ChatColor.of("#639da6") + "Quebra blocos de madeira com ele. Também pode ser usado nas lutas contra jogadores");
            meta.setLore(lore);

            ws.setItemMeta(meta);
        }

        return ws;
    }

    public static ItemStack flagITEM_Diamond_axe() {
        ItemStack ws = new ItemStack(Material.DIAMOND_AXE);
        ItemMeta meta = ws.getItemMeta();

        if (meta != null) {
            meta.setDisplayName(ChatColor.of("#2493b4") + "\uD83E\uDE93 Machado de Diamante!");
            meta.setUnbreakable(true);

            List<String> lore = new ArrayList<>();
            lore.add(ChatColor.of("#a2c4c9") +"Item único do evento Capture a Bandeira");
            lore.add("");
            lore.add("§8| " + ChatColor.of("#a2c4c9") + "Descrição§8:");
            lore.add("  " + ChatColor.of("#639da6") + "Quebra blocos de madeira com ele. Também pode ser usado nas lutas contra jogadores");
            meta.setLore(lore);

            ws.setItemMeta(meta);
        }

        return ws;
    }

    public static ItemStack flagITEM_Wooden_pickaxe() {
        ItemStack ws = new ItemStack(Material.WOODEN_PICKAXE);
        ItemMeta meta = ws.getItemMeta();

        if (meta != null) {
            meta.setDisplayName(ChatColor.of("#2493b4") + "⛏ Picareta de Madeira!");
            meta.setUnbreakable(true);

            List<String> lore = new ArrayList<>();
            lore.add(ChatColor.of("#a2c4c9") +"Item único do evento Capture a Bandeira");
            lore.add("");
            lore.add("§8| " + ChatColor.of("#a2c4c9") + "Descrição§8:");
            lore.add("  " + ChatColor.of("#639da6") + "Quebra blocos como pedra. Destrua a proteção do outro time com ela.");
            meta.setLore(lore);

            ws.setItemMeta(meta);
        }

        return ws;
    }

    public static ItemStack flagITEM_Iron_pickaxe() {
        ItemStack ws = new ItemStack(Material.IRON_PICKAXE);
        ItemMeta meta = ws.getItemMeta();

        if (meta != null) {
            meta.setDisplayName(ChatColor.of("#2493b4") + "⛏ Picareta de Ferro!");
            meta.setUnbreakable(true);

            List<String> lore = new ArrayList<>();
            lore.add(ChatColor.of("#a2c4c9") +"Item único do evento Capture a Bandeira");
            lore.add("");
            lore.add("§8| " + ChatColor.of("#a2c4c9") + "Descrição§8:");
            lore.add("  " + ChatColor.of("#639da6") + "Quebra blocos como pedra. Destrua a proteção do outro time com ela.");
            meta.setLore(lore);

            ws.setItemMeta(meta);
        }

        return ws;
    }

    public static ItemStack flagITEM_Golden_pickaxe() {
        ItemStack ws = new ItemStack(Material.GOLDEN_PICKAXE);
        ItemMeta meta = ws.getItemMeta();

        if (meta != null) {
            meta.setDisplayName(ChatColor.of("#2493b4") + "⛏ Picareta de Ouro!");
            meta.setUnbreakable(true);

            List<String> lore = new ArrayList<>();
            lore.add(ChatColor.of("#a2c4c9") +"Item único do evento Capture a Bandeira");
            lore.add("");
            lore.add("§8| " + ChatColor.of("#a2c4c9") + "Descrição§8:");
            lore.add("  " + ChatColor.of("#639da6") + "Quebra blocos como pedra. Destrua a proteção do outro time com ela.");
            meta.setLore(lore);

            ws.setItemMeta(meta);
        }

        return ws;
    }

    public static ItemStack flagITEM_Diamond_pickaxe() {
        ItemStack ws = new ItemStack(Material.DIAMOND_PICKAXE);
        ItemMeta meta = ws.getItemMeta();

        if (meta != null) {
            meta.setDisplayName(ChatColor.of("#2493b4") + "⛏ Picareta de Diamante!");
            meta.setUnbreakable(true);

            List<String> lore = new ArrayList<>();
            lore.add(ChatColor.of("#a2c4c9") +"Item único do evento Capture a Bandeira");
            lore.add("");
            lore.add("§8| " + ChatColor.of("#a2c4c9") + "Descrição§8:");
            lore.add("  " + ChatColor.of("#639da6") + "Quebra blocos como pedra. Destrua a proteção do outro time com ela.");
            meta.setLore(lore);

            ws.setItemMeta(meta);
        }

        return ws;
    }

    public static ItemStack flagITEM_bow() {
        ItemStack ws = new ItemStack(Material.BOW);
        ItemMeta meta = ws.getItemMeta();

        if (meta != null) {
            meta.setDisplayName(ChatColor.of("#2493b4") + "\uD83C\uDFF9 Arco normal!");
            meta.setUnbreakable(true);

            List<String> lore = new ArrayList<>();
            lore.add(ChatColor.of("#a2c4c9") +"Item único do evento Capture a Bandeira");
            lore.add("");
            lore.add("§8| " + ChatColor.of("#a2c4c9") + "Descrição§8:");
            lore.add("  " + ChatColor.of("#639da6") + "Use para atingir jogadores de longe.");
            meta.setLore(lore);

            ws.setItemMeta(meta);
        }

        return ws;
    }

    public static ItemStack flagITEM_bow_power() {
        ItemStack ws = new ItemStack(Material.BOW);
        ItemMeta meta = ws.getItemMeta();

        if (meta != null) {
            meta.setDisplayName(ChatColor.of("#2493b4") + "\uD83C\uDFF9 Arco com força I!");
            meta.setUnbreakable(true);
            meta.addEnchant(Enchantment.ARROW_DAMAGE, 1, true);

            List<String> lore = new ArrayList<>();
            lore.add(ChatColor.of("#a2c4c9") +"Item único do evento Capture a Bandeira");
            lore.add("");
            lore.add("§8| " + ChatColor.of("#a2c4c9") + "Descrição§8:");
            lore.add("  " + ChatColor.of("#639da6") + "Use para atingir jogadores de longe.");
            meta.setLore(lore);

            ws.setItemMeta(meta);
        }

        return ws;
    }

    public static ItemStack flagITEM_bow_power_punch() {
        ItemStack ws = new ItemStack(Material.BOW);
        ItemMeta meta = ws.getItemMeta();

        if (meta != null) {
            meta.setDisplayName(ChatColor.of("#2493b4") + "\uD83C\uDFF9 Arco com impacto I e força I!");
            meta.setUnbreakable(true);
            meta.addEnchant(Enchantment.ARROW_DAMAGE, 1, true);
            meta.addEnchant(Enchantment.ARROW_KNOCKBACK, 1, true);

            List<String> lore = new ArrayList<>();
            lore.add(ChatColor.of("#a2c4c9") +"Item único do evento Capture a Bandeira");
            lore.add("");
            lore.add("§8| " + ChatColor.of("#a2c4c9") + "Descrição§8:");
            lore.add("  " + ChatColor.of("#639da6") + "Use para atingir jogadores de longe.");
            meta.setLore(lore);

            ws.setItemMeta(meta);
        }

        return ws;
    }

    public static ItemStack flagITEM_shield() {
        ItemStack ws = new ItemStack(Material.SHIELD);
        ItemMeta meta = ws.getItemMeta();

        if (meta != null) {
            meta.setDisplayName(ChatColor.of("#2493b4") + "\uD83D\uDEE1 Escudo!");
            meta.setUnbreakable(true);

            List<String> lore = new ArrayList<>();
            lore.add(ChatColor.of("#a2c4c9") +"Item único do evento Capture a Bandeira");
            lore.add("");
            lore.add("§8| " + ChatColor.of("#a2c4c9") + "Descrição§8:");
            lore.add("  " + ChatColor.of("#639da6") + "Defenda-se de ataques dos jogadores.");
            meta.setLore(lore);

            ws.setItemMeta(meta);
        }

        return ws;
    }

    public static ItemStack flagITEM_flint_and_steel() {
        ItemStack ws = new ItemStack(Material.FLINT_AND_STEEL);
        ItemMeta meta = ws.getItemMeta();

        if (meta != null) {
            meta.setDisplayName(ChatColor.of("#2493b4") + "⚔ Isqueiro!");
            meta.setUnbreakable(true);

            List<String> lore = new ArrayList<>();
            lore.add(ChatColor.of("#a2c4c9") +"Item único do evento Capture a Bandeira");
            lore.add("");
            lore.add("§8| " + ChatColor.of("#a2c4c9") + "Descrição§8:");
            lore.add("  " + ChatColor.of("#639da6") + "Coloque fogo nos blocos.");
            meta.setLore(lore);

            ws.setItemMeta(meta);
        }

        return ws;
    }

    public static ItemStack flagITEM_shears() {
        ItemStack ws = new ItemStack(Material.SHEARS);
        ItemMeta meta = ws.getItemMeta();

        if (meta != null) {
            meta.setDisplayName(ChatColor.of("#2493b4") + "✂ Tesoura!");
            meta.setUnbreakable(true);

            List<String> lore = new ArrayList<>();
            lore.add(ChatColor.of("#a2c4c9") +"Item único do evento Capture a Bandeira");
            lore.add("");
            lore.add("§8| " + ChatColor.of("#a2c4c9") + "Descrição§8:");
            lore.add("  " + ChatColor.of("#639da6") + "Quebre lã com facilidade");
            meta.setLore(lore);

            ws.setItemMeta(meta);
        }

        return ws;
    }

    public static ItemStack flagITEM_tower() {
        ItemStack ws = new ItemStack(Material.BLUE_WOOL);
        ItemMeta meta = ws.getItemMeta();

        if (meta != null) {
            meta.setDisplayName(ChatColor.of("#2493b4") + "🌊 Torre!");
            meta.setUnbreakable(true);

            List<String> lore = new ArrayList<>();
            lore.add(ChatColor.of("#a2c4c9") +"Item único do evento Capture a Bandeira");
            lore.add("");
            lore.add("§8| " + ChatColor.of("#a2c4c9") + "Descrição§8:");
            lore.add("  " + ChatColor.of("#639da6") + "Clique direito para criar uma torre.");
            meta.setLore(lore);

            ws.setItemMeta(meta);
        }

        return ws;
    }

    public static ItemStack flagITEM_Armor_helmet_lime() {
        ItemStack ws = new ItemStack(Material.LEATHER_HELMET);
        LeatherArmorMeta meta = (LeatherArmorMeta) ws.getItemMeta();

        if (meta != null) {
            meta.setDisplayName(ChatColor.of("#8fce00") + "♟ Armadura de couro Verde!");
            meta.setUnbreakable(true);
            meta.setColor(Color.fromRGB(126, 255,0));

            List<String> lore = new ArrayList<>();
            lore.add(ChatColor.of("#b6d7a8") +"Item único do evento Capture a Bandeira");
            lore.add("");
            lore.add("§8| " + ChatColor.of("#b6d7a8") + "Descrição§8:");
            lore.add("  " + ChatColor.of("#83a673") + "  Armadura do time Verde");
            meta.setLore(lore);

            ws.setItemMeta(meta);
        }

        return ws;
    }

    public static ItemStack flagITEM_Armor_chestplate_lime() {
        ItemStack ws = new ItemStack(Material.LEATHER_CHESTPLATE);
        LeatherArmorMeta meta = (LeatherArmorMeta) ws.getItemMeta();

        if (meta != null) {
            meta.setDisplayName(ChatColor.of("#8fce00") + "♟ Armadura de couro Verde!");
            meta.setUnbreakable(true);
            meta.setColor(Color.fromRGB(126, 255,0));

            List<String> lore = new ArrayList<>();
            lore.add(ChatColor.of("#b6d7a8") +"Item único do evento Capture a Bandeira");
            lore.add("");
            lore.add("§8| " + ChatColor.of("#b6d7a8") + "Descrição§8:");
            lore.add("  " + ChatColor.of("#83a673") + "  Armadura do time Verde");
            meta.setLore(lore);

            ws.setItemMeta(meta);
        }

        return ws;
    }

    public static ItemStack flagITEM_Armor_legging_lime() {
        ItemStack ws = new ItemStack(Material.LEATHER_LEGGINGS);
        LeatherArmorMeta meta = (LeatherArmorMeta) ws.getItemMeta();

        if (meta != null) {
            meta.setDisplayName(ChatColor.of("#8fce00") + "♟ Armadura de couro Verde!");
            meta.setUnbreakable(true);
            meta.setColor(Color.fromRGB(126, 255,0));

            List<String> lore = new ArrayList<>();
            lore.add(ChatColor.of("#b6d7a8") +"Item único do evento Capture a Bandeira");
            lore.add("");
            lore.add("§8| " + ChatColor.of("#b6d7a8") + "Descrição§8:");
            lore.add("  " + ChatColor.of("#83a673") + "  Armadura do time Verde");
            meta.setLore(lore);

            ws.setItemMeta(meta);
        }

        return ws;
    }

    public static ItemStack flagITEM_Armor_boots_lime() {
        ItemStack ws = new ItemStack(Material.LEATHER_BOOTS);
        LeatherArmorMeta meta = (LeatherArmorMeta) ws.getItemMeta();

        if (meta != null) {
            meta.setDisplayName(ChatColor.of("#8fce00") + "♟ Armadura de couro Verde!");
            meta.setUnbreakable(true);
            meta.setColor(Color.fromRGB(126, 255,0));

            List<String> lore = new ArrayList<>();
            lore.add(ChatColor.of("#b6d7a8") +"Item único do evento Capture a Bandeira");
            lore.add("");
            lore.add("§8| " + ChatColor.of("#b6d7a8") + "Descrição§8:");
            lore.add("  " + ChatColor.of("#83a673") + "  Armadura do time Verde");
            meta.setLore(lore);

            ws.setItemMeta(meta);
        }

        return ws;
    }

    public static ItemStack flagITEM_Armor_helmet_orange() {
        ItemStack ws = new ItemStack(Material.LEATHER_HELMET);
        LeatherArmorMeta meta = (LeatherArmorMeta) ws.getItemMeta();

        if (meta != null) {
            meta.setDisplayName(ChatColor.of("#ff6600") + "♟ Armadura de couro Laranja!");
            meta.setUnbreakable(true);
            meta.setColor(Color.fromRGB(255, 114,0));

            List<String> lore = new ArrayList<>();
            lore.add(ChatColor.of("#f6b26b") +"Item único do evento Capture a Bandeira");
            lore.add("");
            lore.add("§8| " + ChatColor.of("#f6b26b") + "Descrição§8:");
            lore.add("  " + ChatColor.of("#cd8334") + "  Armadura do time Laranja");
            meta.setLore(lore);

            ws.setItemMeta(meta);
        }

        return ws;
    }

    public static ItemStack flagITEM_Armor_chestplate_orange() {
        ItemStack ws = new ItemStack(Material.LEATHER_CHESTPLATE);
        LeatherArmorMeta meta = (LeatherArmorMeta) ws.getItemMeta();

        if (meta != null) {
            meta.setDisplayName(ChatColor.of("#ff6600") + "♟ Armadura de couro Laranja!");
            meta.setUnbreakable(true);
            meta.setColor(Color.fromRGB(255, 114,0));

            List<String> lore = new ArrayList<>();
            lore.add(ChatColor.of("#f6b26b") +"Item único do evento Capture a Bandeira");
            lore.add("");
            lore.add("§8| " + ChatColor.of("#f6b26b") + "Descrição§8:");
            lore.add("  " + ChatColor.of("#cd8334") + "  Armadura do time Laranja");
            meta.setLore(lore);

            ws.setItemMeta(meta);
        }

        return ws;
    }

    public static ItemStack flagITEM_Armor_legging_orange() {
        ItemStack ws = new ItemStack(Material.LEATHER_LEGGINGS);
        LeatherArmorMeta meta = (LeatherArmorMeta) ws.getItemMeta();

        if (meta != null) {
            meta.setDisplayName(ChatColor.of("#ff6600") + "♟ Armadura de couro Laranja!");
            meta.setUnbreakable(true);
            meta.setColor(Color.fromRGB(255, 114,0));

            List<String> lore = new ArrayList<>();
            lore.add(ChatColor.of("#f6b26b") +"Item único do evento Capture a Bandeira");
            lore.add("");
            lore.add("§8| " + ChatColor.of("#f6b26b") + "Descrição§8:");
            lore.add("  " + ChatColor.of("#cd8334") + "  Armadura do time Laranja");
            meta.setLore(lore);

            ws.setItemMeta(meta);
        }

        return ws;
    }

    public static ItemStack flagITEM_Armor_boots_orange() {
        ItemStack ws = new ItemStack(Material.LEATHER_BOOTS);
        LeatherArmorMeta meta = (LeatherArmorMeta) ws.getItemMeta();

        if (meta != null) {
            meta.setDisplayName(ChatColor.of("#ff6600") + "♟ Armadura de couro Laranja!");
            meta.setUnbreakable(true);
            meta.setColor(Color.fromRGB(255, 114,0));

            List<String> lore = new ArrayList<>();
            lore.add(ChatColor.of("#f6b26b") +"Item único do evento Capture a Bandeira");
            lore.add("");
            lore.add("§8| " + ChatColor.of("#f6b26b") + "Descrição§8:");
            lore.add("  " + ChatColor.of("#cd8334") + "  Armadura do time Laranja");
            meta.setLore(lore);

            ws.setItemMeta(meta);
        }

        return ws;
    }

    public static ItemStack flagITEM_Chailmal_legging() {
        ItemStack ws = new ItemStack(Material.CHAINMAIL_LEGGINGS);
        ArmorMeta meta = (ArmorMeta) ws.getItemMeta();

        if (meta != null) {
            meta.setDisplayName(ChatColor.of("#2493b4") + "♟ Armadura de Cota de Malha!");
            meta.setUnbreakable(true);

            List<String> lore = new ArrayList<>();
            lore.add(ChatColor.of("#a2c4c9") +"Item único do evento Capture a Bandeira");
            lore.add("");
            lore.add("§8| " + ChatColor.of("#a2c4c9") + "Descrição§8:");
            lore.add("  " + ChatColor.of("#639da6") + "  Armadura de cota de malha.");
            meta.setLore(lore);

            ws.setItemMeta(meta);
        }

        return ws;
    }

    public static ItemStack flagITEM_Chailmal_BOOTS() {
        ItemStack ws = new ItemStack(Material.CHAINMAIL_BOOTS);
        ArmorMeta meta = (ArmorMeta) ws.getItemMeta();

        if (meta != null) {
            meta.setDisplayName(ChatColor.of("#2493b4") + "♟ Armadura de Cota de Malha!");
            meta.setUnbreakable(true);

            List<String> lore = new ArrayList<>();
            lore.add(ChatColor.of("#a2c4c9") +"Item único do evento Capture a Bandeira");
            lore.add("");
            lore.add("§8| " + ChatColor.of("#a2c4c9") + "Descrição§8:");
            lore.add("  " + ChatColor.of("#639da6") + "  Armadura de cota de malha.");
            meta.setLore(lore);

            ws.setItemMeta(meta);
        }

        return ws;
    }

    public static ItemStack flagITEM_Iron_legging() {
        ItemStack ws = new ItemStack(Material.IRON_LEGGINGS);
        ArmorMeta meta = (ArmorMeta) ws.getItemMeta();

        if (meta != null) {
            meta.setDisplayName(ChatColor.of("#2493b4") + "♟ Armadura de Ferro!");
            meta.setUnbreakable(true);

            List<String> lore = new ArrayList<>();
            lore.add(ChatColor.of("#a2c4c9") +"Item único do evento Capture a Bandeira");
            lore.add("");
            lore.add("§8| " + ChatColor.of("#a2c4c9") + "Descrição§8:");
            lore.add("  " + ChatColor.of("#639da6") + "  Armadura de ferro.");
            meta.setLore(lore);

            ws.setItemMeta(meta);
        }

        return ws;
    }

    public static ItemStack flagITEM_Iron_boots() {
        ItemStack ws = new ItemStack(Material.IRON_BOOTS);
        ArmorMeta meta = (ArmorMeta) ws.getItemMeta();

        if (meta != null) {
            meta.setDisplayName(ChatColor.of("#2493b4") + "♟ Armadura de Ferro!");
            meta.setUnbreakable(true);

            List<String> lore = new ArrayList<>();
            lore.add(ChatColor.of("#a2c4c9") +"Item único do evento Capture a Bandeira");
            lore.add("");
            lore.add("§8| " + ChatColor.of("#a2c4c9") + "Descrição§8:");
            lore.add("  " + ChatColor.of("#639da6") + "  Armadura de ferro.");
            meta.setLore(lore);

            ws.setItemMeta(meta);
        }

        return ws;
    }

    public static ItemStack flagITEM_Diamond_legging() {
        ItemStack ws = new ItemStack(Material.DIAMOND_LEGGINGS);
        ArmorMeta meta = (ArmorMeta) ws.getItemMeta();

        if (meta != null) {
            meta.setDisplayName(ChatColor.of("#2493b4") + "♟ Armadura de Diamante!");
            meta.setUnbreakable(true);

            List<String> lore = new ArrayList<>();
            lore.add(ChatColor.of("#a2c4c9") +"Item único do evento Capture a Bandeira");
            lore.add("");
            lore.add("§8| " + ChatColor.of("#a2c4c9") + "Descrição§8:");
            lore.add("  " + ChatColor.of("#639da6") + "  Armadura de diamante.");
            meta.setLore(lore);

            ws.setItemMeta(meta);
        }

        return ws;
    }

    public static ItemStack flagITEM_Diamond_boots() {
        ItemStack ws = new ItemStack(Material.DIAMOND_BOOTS);
        ArmorMeta meta = (ArmorMeta) ws.getItemMeta();

        if (meta != null) {
            meta.setDisplayName(ChatColor.of("#2493b4") + "♟ Armadura de Diamante!");
            meta.setUnbreakable(true);

            List<String> lore = new ArrayList<>();
            lore.add(ChatColor.of("#a2c4c9") +"Item único do evento Capture a Bandeira");
            lore.add("");
            lore.add("§8| " + ChatColor.of("#a2c4c9") + "Descrição§8:");
            lore.add("  " + ChatColor.of("#639da6") + "  Armadura de diamante.");
            meta.setLore(lore);

            ws.setItemMeta(meta);
        }

        return ws;
    }

    public static ItemStack flagLARANJA() {
        ItemStack flag = new ItemStack(Material.ORANGE_BANNER);
        BannerMeta bannerMeta = (BannerMeta) flag.getItemMeta();

        if (bannerMeta != null) {
            bannerMeta.setDisplayName(ChatColor.of("#ff6600") + "Bandeira do §lTIME LARANJA");
            bannerMeta.addPattern(new Pattern(DyeColor.BLACK, PatternType.GLOBE));
            bannerMeta.setUnbreakable(true);

            List<String> lore = new ArrayList<>();
            lore.add(ChatColor.of("#f6b26b") +"Bandeira do evento de Capture a Bandeira");
            lore.add("");
            lore.add("§8| " + ChatColor.of("#f6b26b") + "Descrição§8:");
            lore.add(ChatColor.of("#cd8334") + "  Leve até a base do seu time!");
            bannerMeta.setLore(lore);

            flag.setItemMeta(bannerMeta);
        }

        return flag;
    }

    public static ItemStack flagVERDE() {
        ItemStack flag = new ItemStack(Material.LIME_BANNER);
        BannerMeta bannerMeta = (BannerMeta) flag.getItemMeta();

        if (bannerMeta != null) {
            bannerMeta.setDisplayName(ChatColor.of("#8fce00") + "Bandeira do §lTIME VERDE");
            bannerMeta.addPattern(new Pattern(DyeColor.BLACK, PatternType.CREEPER));
            bannerMeta.setUnbreakable(true);

            List<String> lore = new ArrayList<>();
            lore.add(ChatColor.of("#b6d7a8") + "Bandeira do evento de Capture a Bandeira");
            lore.add("");
            lore.add("§8| " + ChatColor.of("#b6d7a8") + "Descrição§8:");
            lore.add(ChatColor.of("#83a673") + "  Leve até a base do seu time!");
            bannerMeta.setLore(lore);

            flag.setItemMeta(bannerMeta);
        }

        return flag;
    }


    public static ItemStack HeadEvents(Player p) {
        ItemStack head = new ItemStack(Material.PLAYER_HEAD);
        SkullMeta skullMeta = (SkullMeta) head.getItemMeta();
        assert skullMeta != null;
        skullMeta.setOwningPlayer(p);
        File file = DataManager.getFile(p.getName().toLowerCase(), "playerdata");
        FileConfiguration config = DataManager.getConfiguration(file);
        switch (MineSkyEvents.event) {
            case "Spleef": {
                skullMeta.setDisplayName("§cInformações de " + p.getName());
                skullMeta.setUnbreakable(true);

                List<String> lore = new ArrayList<>();
                lore.add(ChatColor.of("#b77777") + "Suas informações do evento Spleef");
                lore.add("");
                lore.add("§8| " + ChatColor.of("#b77777") + "Vitórias§8: " + ChatColor.of("#c24646") + config.getInt("Events.Spleef.win"));
                lore.add("§8| " + ChatColor.of("#b77777") + "Mortes§8: " + ChatColor.of("#c24646") + config.getInt("Events.Spleef.dead"));
                skullMeta.setLore(lore);

                head.setItemMeta(skullMeta);
                return head;
            }
            case "TijolãoWars": {
                skullMeta.setDisplayName("§cInformações de " + p.getName());
                skullMeta.setUnbreakable(true);

                List<String> lore = new ArrayList<>();
                lore.add(ChatColor.of("#b77777") + "Suas informações do evento Tijolão Wars");
                lore.add("");
                lore.add("§8| " + ChatColor.of("#b77777") + "Vitórias§8: " + ChatColor.of("#c24646") + config.getInt("Events.TijolãoWars.win"));
                lore.add("§8| " + ChatColor.of("#b77777") + "Mortes§8: " + ChatColor.of("#c24646") + config.getInt("Events.TijolãoWars.dead"));
                skullMeta.setLore(lore);

                head.setItemMeta(skullMeta);
                return head;
            }
            case "Corrida": {
                skullMeta.setDisplayName("§cInformações de " + p.getName());
                skullMeta.setUnbreakable(true);

                List<String> lore = new ArrayList<>();
                lore.add(ChatColor.of("#b77777") + "Suas informações do evento Corrida");
                lore.add("");
                lore.add("§8| " + ChatColor.of("#b77777") + "Vitórias§8: " + ChatColor.of("#c24646") + config.getInt("Events.Corrida.win"));
                skullMeta.setLore(lore);

                head.setItemMeta(skullMeta);
                return head;
            }
            case "CorridaBoat": {
                skullMeta.setDisplayName("§cInformações de " + p.getName());
                skullMeta.setUnbreakable(true);

                List<String> lore = new ArrayList<>();
                lore.add(ChatColor.of("#b77777") + "Suas informações do evento Corrida de barco");
                lore.add("");
                lore.add("§8| " + ChatColor.of("#b77777") + "Vitórias§8: " + ChatColor.of("#c24646") + config.getInt("Events.CorridaBoat.win"));
                skullMeta.setLore(lore);

                head.setItemMeta(skullMeta);
                return head;
            }
            case "Sumo": {
                skullMeta.setDisplayName("§cInformações de " + p.getName());
                skullMeta.setUnbreakable(true);

                List<String> lore = new ArrayList<>();
                lore.add(ChatColor.of("#b77777") + "Suas informações do evento Sumo");
                lore.add("");
                lore.add("§8| " + ChatColor.of("#b77777") + "Vitórias§8: " + ChatColor.of("#c24646") + config.getInt("Events.Sumo.win"));
                lore.add("§8| " + ChatColor.of("#b77777") + "Mortes§8: " + ChatColor.of("#c24646") + config.getInt("Events.Sumo.dead"));
                skullMeta.setLore(lore);

                head.setItemMeta(skullMeta);
                return head;
            }
            case "TNTRun": {
                skullMeta.setDisplayName("§cInformações de " + p.getName());
                skullMeta.setUnbreakable(true);

                List<String> lore = new ArrayList<>();
                lore.add(ChatColor.of("#b77777") + "Suas informações do evento TNTRUN");
                lore.add("");
                lore.add("§8| " + ChatColor.of("#b77777") + "Vitórias§8: " + ChatColor.of("#c24646") + config.getInt("Events.TNTRun.win"));
                lore.add("§8| " + ChatColor.of("#b77777") + "Mortes§8: " + ChatColor.of("#c24646") + config.getInt("Events.TNTRun.dead"));
                skullMeta.setLore(lore);

                head.setItemMeta(skullMeta);
                return head;
            }
            case "TNTTag": {
                skullMeta.setDisplayName("§cInformações de " + p.getName());
                skullMeta.setUnbreakable(true);

                List<String> lore = new ArrayList<>();
                lore.add(ChatColor.of("#b77777") + "Suas informações do evento TNT-TAG");
                lore.add("");
                lore.add("§8| " + ChatColor.of("#b77777") + "Vitórias§8: " + ChatColor.of("#c24646") + config.getInt("Events.TNTTag.win"));
                lore.add("§8| " + ChatColor.of("#b77777") + "Mortes§8: " + ChatColor.of("#c24646") + config.getInt("Events.TNTTag.dead"));
                skullMeta.setLore(lore);

                head.setItemMeta(skullMeta);
                return head;
            }
            case "Parapente": {
                skullMeta.setDisplayName("§cInformações de " + p.getName());
                skullMeta.setUnbreakable(true);

                List<String> lore = new ArrayList<>();
                lore.add(ChatColor.of("#b77777") + "Suas informações do evento Corrida de Parapente");
                lore.add("");
                lore.add("§8| " + ChatColor.of("#b77777") + "Vitórias§8: " + ChatColor.of("#c24646") + config.getInt("Events.Parapente.win"));
                skullMeta.setLore(lore);

                head.setItemMeta(skullMeta);
                return head;
            }
        }
        return head;
    }
}
