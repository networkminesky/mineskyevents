package minesky.msne.utils;

import minesky.msne.MineSkyEvents;
import minesky.msne.config.DataManager;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

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

    public static ItemStack Shovel() {
        ItemStack pa = new ItemStack(Material.GOLDEN_SHOVEL);
        ItemMeta meta = pa.getItemMeta();

        if (meta != null) {
            meta.setDisplayName("§eQuebra TUDO!");
            meta.setUnbreakable(true);
            meta.addEnchant(Enchantment.DIG_SPEED, 2, true);

            List<String> lore = new ArrayList<>();
            lore.add("§7Pá do evento de Spleef");
            lore.add("");
            lore.add("§6| §7Descrição§6:");
            lore.add("  §7Quebre os blocos de neve do evento.");
            meta.setLore(lore);

            pa.setItemMeta(meta);
        }

        return pa;
    }

    public static ItemStack Stick() {
        ItemStack stick = new ItemStack(Material.STICK);
        ItemMeta meta = stick.getItemMeta();

        if (meta != null) {
            meta.setDisplayName("§4Irei te pegar!");
            meta.setUnbreakable(true);
            meta.addEnchant(Enchantment.KNOCKBACK, 3, true);

            List<String> lore = new ArrayList<>();
            lore.add("§7Stick do evento de Sumo");
            lore.add("");
            lore.add("§6| §7Descrição§6:");
            lore.add("  §7Empurre os jogadores das plataformas.");
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
            lore.add("§7Barco da corrida de barco.");
            lore.add("");
            lore.add("§6| §7Descrição§6:");
            lore.add("  §7Utilize esse barco para ganhar a corrida.");
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
            lore.add("§7Batata quente (TNT) do TNTTAG");
            lore.add("");
            lore.add("§6| §7Descrição§6:");
            lore.add("  §7Caso tenha ela na sua cabeça e passar entre 15 a 30 segundos ela explode.");
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
            lore.add("§7Item próprio do evento Parapente");
            lore.add("");
            lore.add("§6| §7Descrição§6:");
            lore.add("  §7Use ele quando quiser voltar para o checkpoint do Parapente");
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
            lore.add("§6| §7Descrição§6:");
            lore.add("  §7Sair do evento.");
            meta.setLore(lore);

            bed.setItemMeta(meta);
        }

        return bed;
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
                lore.add("§7Suas informações do evento Spleef");
                lore.add("");
                lore.add("§6| §7Vitórias§6: " + config.getInt("Events.Spleef.win"));
                lore.add("§6| §7Mortes§6: " + config.getInt("Events.Spleef.dead"));
                skullMeta.setLore(lore);

                head.setItemMeta(skullMeta);
                return head;
            }
            case "TijolãoWars": {
                skullMeta.setDisplayName("§cInformações de " + p.getName());
                skullMeta.setUnbreakable(true);

                List<String> lore = new ArrayList<>();
                lore.add("§7Suas informações do evento Tijolão Wars");
                lore.add("");
                lore.add("§6| §7Vitórias§6: " + config.getInt("Events.TijolãoWars.win"));
                lore.add("§6| §7Mortes§6: " + config.getInt("Events.TijolãoWars.dead"));
                skullMeta.setLore(lore);

                head.setItemMeta(skullMeta);
                return head;
            }
            case "Corrida": {
                skullMeta.setDisplayName("§cInformações de " + p.getName());
                skullMeta.setUnbreakable(true);

                List<String> lore = new ArrayList<>();
                lore.add("§7Suas informações do evento Corrida");
                lore.add("");
                lore.add("§6| §7Vitórias§6: " + config.getInt("Events.Corrida.win"));
                skullMeta.setLore(lore);

                head.setItemMeta(skullMeta);
                return head;
            }
            case "CorridaBoat": {
                skullMeta.setDisplayName("§cInformações de " + p.getName());
                skullMeta.setUnbreakable(true);

                List<String> lore = new ArrayList<>();
                lore.add("§7Suas informações do evento Corrida de barco");
                lore.add("");
                lore.add("§6| §7Vitórias§6: " + config.getInt("Events.CorridaBoat.win"));
                skullMeta.setLore(lore);

                head.setItemMeta(skullMeta);
                return head;
            }
            case "Sumo": {
                skullMeta.setDisplayName("§cInformações de " + p.getName());
                skullMeta.setUnbreakable(true);

                List<String> lore = new ArrayList<>();
                lore.add("§7Suas informações do evento Sumo");
                lore.add("");
                lore.add("§6| §7Vitórias§6: " + config.getInt("Events.Sumo.win"));
                lore.add("§6| §7Mortes§6: " + config.getInt("Events.Sumo.dead"));
                skullMeta.setLore(lore);

                head.setItemMeta(skullMeta);
                return head;
            }
            case "TNTRun": {
                skullMeta.setDisplayName("§cInformações de " + p.getName());
                skullMeta.setUnbreakable(true);

                List<String> lore = new ArrayList<>();
                lore.add("§7Suas informações do evento TNTRUN");
                lore.add("");
                lore.add("§6| §7Vitórias§6: " + config.getInt("Events.TNTRun.win"));
                lore.add("§6| §7Mortes§6: " + config.getInt("Events.TNTRun.dead"));
                skullMeta.setLore(lore);

                head.setItemMeta(skullMeta);
                return head;
            }
            case "TNTTag": {
                skullMeta.setDisplayName("§cInformações de " + p.getName());
                skullMeta.setUnbreakable(true);

                List<String> lore = new ArrayList<>();
                lore.add("§7Suas informações do evento TNT-TAG");
                lore.add("");
                lore.add("§6| §7Vitórias§6: " + config.getInt("Events.TNTTag.win"));
                lore.add("§6| §7Mortes§6: " + config.getInt("Events.TNTTag.dead"));
                skullMeta.setLore(lore);

                head.setItemMeta(skullMeta);
                return head;
            }
            case "Parapente": {
                skullMeta.setDisplayName("§cInformações de " + p.getName());
                skullMeta.setUnbreakable(true);

                List<String> lore = new ArrayList<>();
                lore.add("§7Suas informações do evento Corrida de Parapente");
                lore.add("");
                lore.add("§6| §7Vitórias§6: " + config.getInt("Events.Parapente.win"));
                skullMeta.setLore(lore);

                head.setItemMeta(skullMeta);
                return head;
            }
        }
        return head;
    }
}
