package net.minesky.utils;

import net.md_5.bungee.api.ChatColor;
import net.minesky.MineSkyEvents;
import net.minesky.config.DataManager;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import java.io.File;
import java.util.*;

public class EventItem {
        private static final Map<String, ItemStack> items = new HashMap<>();

        public static void loadAllItems() {
            register("SpleefITEM", createItem(Material.GOLDEN_SHOVEL, "§eQuebra TUDO!", "#d1be86", "Item único do evento Spleef", Enchantment.DIG_SPEED, 2));
            register("BedLeave", createItem(Material.RED_BED, "§c§l→ Clique para sair!", "#ed5858", "Sair do evento."));
            register("SumoITEM", createItem(Material.STICK, ChatColor.of("#F44336") + "Irei te pegar!", "#EA9999", "Item único do evento SUMO", Enchantment.KNOCKBACK, 3));
            register("BarcoITEM", createItem(Material.JUNGLE_BOAT, "§dCarro esportivo", "#d5a6bd", "Item único do evento Corrida de Barco"));
            register("TNTHEAD", createItem(Material.TNT, "§cBatata Quente!", "#dc6565", "Item único do evento TNT-TAG", Enchantment.MENDING, 1));
            register("CheckPoint", createItem(Material.LIGHT_WEIGHTED_PRESSURE_PLATE, "§e→ CheckPoint", "#ffe599", "Item único do evento Corrida de Parapente"));
        }

        private static void register(String name, ItemStack item) {
            items.put(name, item);
        }

        public static ItemStack getItem(String name) {
            return items.getOrDefault(name, new ItemStack(Material.AIR));
        }

        private static ItemStack createItem(Material material, String displayName, String colorHex, String description) {
            ItemStack item = new ItemStack(material);
            ItemMeta meta = item.getItemMeta();
            if (meta != null) {
                meta.setDisplayName(displayName);
                meta.setUnbreakable(true);
                meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
                meta.setLore(Collections.singletonList(ChatColor.of(colorHex) + description));
                item.setItemMeta(meta);
            }
            return item;
        }

        private static ItemStack createItem(Material material, String displayName, String colorHex, String description, Enchantment enchantment, int level) {
            ItemStack item = createItem(material, displayName, colorHex, description);
            item.addUnsafeEnchantment(enchantment, level);
            return item;
        }

    public static ItemStack HeadEvents(Player p) {
        ItemStack head = new ItemStack(Material.PLAYER_HEAD);
        SkullMeta skullMeta = (SkullMeta) head.getItemMeta();
        assert skullMeta != null;
        skullMeta.setOwningPlayer(p);
        skullMeta.setDisplayName("§cInformações de " + p.getName());
        skullMeta.setUnbreakable(true);

        File file = DataManager.getFile(p.getUniqueId().toString(), "playerdata");
        FileConfiguration config = DataManager.getConfiguration(file);

        String event = MineSkyEvents.event;
        String eventName = switch (event) {
            case "Spleef" -> "Spleef";
            case "TijolãoWars" -> "Tijolão Wars";
            case "Corrida" -> "Corrida";
            case "CorridaBoat" -> "Corrida de barco";
            case "Sumo" -> "Sumo";
            case "TNTRun" -> "TNTRUN";
            case "TNTTag" -> "TNT-TAG";
            case "Parapente" -> "Corrida de Parapente";
            default -> null;
        };

        if (eventName != null) {
            List<String> lore = new ArrayList<>();
            lore.add(ChatColor.of("#b77777") + "Suas informações do evento " + eventName);
            lore.add("");
            lore.add("§8| " + ChatColor.of("#b77777") + "Vitórias§8: " + ChatColor.of("#c24646") + config.getInt("Events." + event + ".win"));

            if (List.of("Spleef", "TijolãoWars", "Sumo", "TNTRun", "TNTTag").contains(event)) {
                lore.add("§8| " + ChatColor.of("#b77777") + "Mortes§8: " + ChatColor.of("#c24646") + config.getInt("Events." + event + ".dead"));
            }

            skullMeta.setLore(lore);
        }

        head.setItemMeta(skullMeta);
        return head;
    }
    }
