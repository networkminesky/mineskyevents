package minesky.msne.utils;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.google.common.collect.Iterables;
import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import minesky.msne.MineSkyEvents;
import minesky.msne.config.DataManager;
import minesky.msne.config.Messages;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.*;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

public class Util {
    public static ItemStack StoneShovel;
    public static ItemStack BedLeave;
    public static ItemStack Head;
    public static ItemStack SumoItem;
    public static ItemStack Barco;
    public static ItemStack TNT;


    public static String serializeLocation(Location l) {
        return String.valueOf(l.getWorld().getName()) + ',' +
                l.getX() + ',' +
                l.getY() + ',' +
                l.getZ() + ',' +
                l.getYaw() + ',' +
                l.getPitch();
    }
    public static Location deserializeLocation(String s) {
        String[] location = s.split(",");
        return new Location(
                Bukkit.getWorld(location[0]),
                Double.parseDouble(location[1]),
                Double.parseDouble(location[2]),
                Double.parseDouble(location[3]),
                Float.parseFloat(location[4]),
                Float.parseFloat(location[5]));
    }

    public static String Color(String s) {
        return ChatColor.translateAlternateColorCodes('&', s);
    }

    public static void InfoC(String s) {
        Bukkit.getConsoleSender().sendMessage(Color(PrefixC() + " &c" + s));
    }
    public static FileConfiguration Playerdata(Player player) {
        String newPlayer = player.getName();
        File file = DataManager.getFile(newPlayer.toLowerCase(), "playerdata");
        FileConfiguration config = DataManager.getConfiguration(file);
        if (!file.exists()) {
            send(player, Messages.Player_exist);
        }
        return config;
    }
    public static File PlayerDataF(Player player) {
        String newPlayer = player.getName();
        File file = DataManager.getFile(newPlayer.toLowerCase(), "playerdata");
        if (!file.exists()) {
            send(player, Messages.Player_exist);
        }
        return file;
    }

    public static void PVC(Player p, Player s) {
        String newPlayer = p.getName();
        File file = DataManager.getFile(newPlayer.toLowerCase(), "playerdata");
        if (!file.exists()) {
            s.sendMessage(Messages.Player_exist);
        }
    }

    public static boolean PDVE(Player p) {
            if (Playerdata(p).getBoolean("Event", true)) {
                return true;
        }
        return false;
    }

    public static boolean PDVES(Player p) {
        if (Playerdata(p).getBoolean("EventSpect", true)) {
            return true;
        }
        return false;
    }
    public static List<String> PVE() {
        List<String> playersWithEvent = new ArrayList<>();
        for (Player player : Bukkit.getOnlinePlayers()) {
            File file = DataManager.getFile(player.getName().toLowerCase(), "playerdata");
            FileConfiguration config = DataManager.getConfiguration(file);
           if (config.getBoolean("Event", true)) {
               playersWithEvent.add(player.getName());
               return playersWithEvent;
           }
        }
        return playersWithEvent;
    }
    public static List<String> getOnlinePlayerNames() {
        List<String> onlinePlayerNames = new ArrayList<>();
        for (Player onlinePlayer : Bukkit.getOnlinePlayers()) {
            onlinePlayerNames.add(onlinePlayer.getName());
        }
        return onlinePlayerNames;
    }

    public static ItemStack shovel() {
        ItemStack pa = new ItemStack(Material.GOLDEN_SHOVEL);
        ItemMeta meta = pa.getItemMeta();

        if (meta != null) {
            meta.setDisplayName("§e§lQuebra TUDO!");
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

    public static ItemStack sumo() {
        ItemStack stick = new ItemStack(Material.STICK);
        ItemMeta meta = stick.getItemMeta();

        if (meta != null) {
            meta.setDisplayName("§4§lIrei te pegar!");
            meta.setUnbreakable(true);
            meta.addEnchant(Enchantment.KNOCKBACK, 2, true);

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
    public static ItemStack barco() {
        ItemStack stick = new ItemStack(Material.JUNGLE_BOAT);
        ItemMeta meta = stick.getItemMeta();

        if (meta != null) {
            meta.setDisplayName("§d§lCarro esportivo");
            meta.setUnbreakable(true);

            List<String> lore = new ArrayList<>();
            lore.add("§7Barco da corrida de barco.");
            lore.add("");
            lore.add("§6| §7Descrição§6:");
            lore.add("  §7Utilize esse barco para ganhar a corrida.");
            meta.setLore(lore);

            stick.setItemMeta(meta);
        }

        return stick;
    }
    public static ItemStack tnt() {
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

    public static void sendMessageBGMSNE(String mensagem) {
        ByteArrayDataOutput out = ByteStreams.newDataOutput();
        out.writeUTF("MessageEvent");
        out.writeUTF(mensagem);

        Player player = Iterables.getFirst(Bukkit.getOnlinePlayers(), null);
        if (player != null) {
            player.sendPluginMessage(MineSkyEvents.get(), "minesky:proxy", out.toByteArray());
        }
    }

    public static void sendMessageBCMSNE(TextComponent mensagem) {
        ByteArrayDataOutput out = ByteStreams.newDataOutput();
        out.writeUTF("TextEvents");
        out.writeUTF(TextComponent.toLegacyText(mensagem));

        Player player = Iterables.getFirst(Bukkit.getOnlinePlayers(), null);
        if (player != null) {
            player.sendPluginMessage(MineSkyEvents.get(), "minesky:proxy", out.toByteArray());
        }
    }

    public static void sendConectionBCMSNE(Player player) {
        ByteArrayDataOutput out = ByteStreams.newDataOutput();
        out.writeUTF("ConectionEvents");
        out.writeUTF(player.getName());

        if (player != null) {
            player.sendPluginMessage(MineSkyEvents.get(), "minesky:proxy", out.toByteArray());
        }
    }

    public static void sendPlayermessage(Player player, TextComponent message) {
        ByteArrayDataOutput out = ByteStreams.newDataOutput();
        out.writeUTF("PlayerMessage");
        out.writeUTF(player.getName());
        out.writeUTF(TextComponent.toLegacyText(message));

        if (player != null) {
            player.sendPluginMessage(MineSkyEvents.get(), "minesky:proxy", out.toByteArray());
        }
    }

    public static ItemStack head(Player p) {
        ItemStack head = new ItemStack(Material.PLAYER_HEAD);
        SkullMeta skullMeta = (SkullMeta) head.getItemMeta();
        skullMeta.setOwningPlayer(p);
        ItemMeta meta = (ItemMeta) skullMeta;
        File file = DataManager.getFile(p.getName().toLowerCase(), "playerdata");
        FileConfiguration config = DataManager.getConfiguration(file);
        if (MineSkyEvents.event == "TijolãoWars") {
            if (meta != null) {
                meta.setDisplayName("§c§lInformações " + p.getName());
                meta.setUnbreakable(true);

                List<String> lore = new ArrayList<>();
                lore.add("§7Suas informações do evento Tijolão Wars");
                lore.add("");
                lore.add("§6| §7Vitórias§6: " + config.getInt("Events.Tijolao.win"));
                lore.add("§6| §7Mortes§6: " + config.getInt("Events.Tijolao.dead"));
                meta.setLore(lore);

                head.setItemMeta(meta);
                return head;
            }
        }
        if (MineSkyEvents.event == "Corrida") {
            if (meta != null) {
                meta.setDisplayName("§c§lInformações " + p.getName());
                meta.setUnbreakable(true);

                List<String> lore = new ArrayList<>();
                lore.add("§7Suas informações do evento Corrida");
                lore.add("");
                lore.add("§6| §7Vitórias§6: " + config.getInt("Events.Corrida.win"));
                meta.setLore(lore);

                head.setItemMeta(meta);
                return head;
            }
        }
        if (MineSkyEvents.event == "CorridaBoat") {
            if (meta != null) {
                meta.setDisplayName("§c§lInformações " + p.getName());
                meta.setUnbreakable(true);

                List<String> lore = new ArrayList<>();
                lore.add("§7Suas informações do evento Corrida de barco");
                lore.add("");
                lore.add("§6| §7Vitórias§6: " + config.getInt("Events.CorridaBoat.win"));
                meta.setLore(lore);

                head.setItemMeta(meta);
                return head;
            }
        }
        if (MineSkyEvents.event == "Sumo") {
            if (meta != null) {
                meta.setDisplayName("§c§lInformações " + p.getName());
                meta.setUnbreakable(true);

                List<String> lore = new ArrayList<>();
                lore.add("§7Suas informações do evento Sumo");
                lore.add("");
                lore.add("§6| §7Vitórias§6: " + config.getInt("Events.Sumo.win"));
                lore.add("§6| §7Mortes§6: " + config.getInt("Events.Sumo.dead"));
                meta.setLore(lore);

                head.setItemMeta(meta);
                return head;
            }
        }
        return head;
    }

    public static void send(Player player, String s) {
        player.sendMessage(Color(Prefix() + " &8» &c" + s));
    }

    public static String Prefix() {
        return "§6§lMineSky Events";
    }

    public static String PrefixC() {
        return "§8[§6MineSky Events§8]";
    }

}
