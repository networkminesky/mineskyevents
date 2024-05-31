package minesky.msne.commands;

import minesky.msne.MineSkyEvents;
import minesky.msne.addons.MMOItem;
import minesky.msne.addons.Vault;
import minesky.msne.commands.console.EventCommandConsole;
import minesky.msne.commands.console.MSNECommandConsole;
import minesky.msne.config.DataManager;
import minesky.msne.config.Locations;
import minesky.msne.discord.EventsMessage;
import minesky.msne.events.CorridaBoatEvent;
import minesky.msne.events.CorridaEvent;
import minesky.msne.events.ParapenteEvent;
import minesky.msne.system.event.EventPlayerManager;
import minesky.msne.utils.EventItem;
import minesky.msne.utils.SendMessages;
import minesky.msne.utils.Util;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.Sound;
import org.bukkit.command.*;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerTeleportEvent;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class MSNECommand implements CommandExecutor, TabCompleter { ;

    @Override
    public List<String> onTabComplete(CommandSender s, Command cmd, String lbl, String[] args) {
        List<String> strings = new ArrayList<>();
        if (args.length == 1) {
            strings.add("notification");
            if (s.hasPermission("mineskyevents.reload")) strings.add("reload");
            return strings;
        }
        return strings;
    }

    @Override
    public boolean onCommand(CommandSender s, Command cmd, String lbl, String[] args) {
        if (s instanceof ConsoleCommandSender || s instanceof RemoteConsoleCommandSender || s instanceof BlockCommandSender) {
            new MSNECommandConsole(s, cmd, lbl, args);
            return true;
        }
        Player player = (Player) s;
        String version = MineSkyEvents.get().getDescription().getVersion();
        if (args.length < 1) {
            player.playSound(player, Sound.BLOCK_NOTE_BLOCK_BIT, 1.0f , 1.0f);
            player.sendMessage("§6§lMinesky Events §8» §6v" + version + "\n§6Notification §8- §7Enable and disable notifications\n§6Reload §8- §7Reload the plugin.\n\n§6Authors§8: §7BrunoC§8, §7Drawn");
            return true;
        }
        if (args[0].equalsIgnoreCase("adopt")) {
            if (args.length < 2) {
                player.playSound(player, Sound.BLOCK_NOTE_BLOCK_BASS, 1.0f , 1.0f);
                player.sendMessage("§8[§c!§8] §cInforme um player");
                return true;
            }
            Player adotar = Bukkit.getPlayer(args[1]);
            if (adotar != null && adotar.isOnline()) {
                player.playSound(player, Sound.BLOCK_NOTE_BLOCK_BIT, 1.0f , 1.0f);
                TextComponent messageADOPT = new TextComponent("§b" + player.getName() + " §7adotou §b" + adotar.getName());
                SendMessages.sendMessageBCMSNE(messageADOPT);
            } else {
                player.playSound(player, Sound.BLOCK_NOTE_BLOCK_BASS, 1.0f , 1.0f);
                player.sendMessage("§8[§c!§8] §cEste player não está online no momento");
            }
            return true;
        }
        if (args[0].equalsIgnoreCase("give")) {
            if (!player.hasPermission("mineskyevents.give")) {
                player.playSound(player, Sound.BLOCK_NOTE_BLOCK_BASS, 1.0f , 1.0f);
                player.sendMessage("§8[§c!§8] §cVocê não pode executar esse comando.");
                return true;
            }
            player.getInventory().addItem(EventItem.SpleefITEM);
            player.getInventory().addItem(EventItem.SumoITEM);
            player.getInventory().addItem(EventItem.TNTHEAD);
            player.getInventory().addItem(EventItem.BarcoITEM);
            player.getInventory().addItem(EventItem.CheckPoint);
            MMOItem.darParaglider(player);
            player.playSound(player, Sound.BLOCK_NOTE_BLOCK_BIT, 1.0f , 1.0f);
            s.sendMessage("§8[§a!§8] §aVocê recebeu todos os itens de eventos.");
            return true;
        }
        if (args[0].equalsIgnoreCase("reload")) {
            if (!s.hasPermission("mineskyevents.reload")) {
                player.playSound(player, Sound.BLOCK_NOTE_BLOCK_BASS, 1.0f , 1.0f);
                s.sendMessage("§8[§c!§8] §Você não pode executar esse comando.");
                return true;
            }
            MineSkyEvents.carregarConfigs();
            player.playSound(player, Sound.BLOCK_NOTE_BLOCK_BIT, 1.0f , 1.0f);
            player.sendMessage("§6§lMinesky Events §8» §6v" + version + "\n§6Authors§8: §7BrunoC§8, §7Drawn\n§6Version§8: §7" + version + "\n\n§6MineSky Events §7reloaded successfully.\n§7NOTE: Some changes will only take effect when the server is restarted.");
            return true;
        }
        if (args[0].equalsIgnoreCase("notification")) {
            if (!s.hasPermission("mineskyevents.notify.enabled")) {
                player.playSound(player, Sound.BLOCK_NOTE_BLOCK_BASS, 1.0f , 1.0f);
                s.sendMessage("§8[§c!§8] §cVocê não pode executar esse comando");
                return true;
            }
            File file = DataManager.getFile(player.getName().toLowerCase(), "playerdata");
            FileConfiguration config = DataManager.getConfiguration(file);
            if (config.getBoolean("Notification")) {
                config.set("Notification", false);
                try {
                    config.save(file);
                    s.sendMessage("§8[§c!§8] §cVocê desativou as notificações!");
                    player.playSound(player, Sound.BLOCK_NOTE_BLOCK_BIT, 1.0f , 1.0f);
                } catch (IOException e) {
                    player.playSound(player, Sound.BLOCK_NOTE_BLOCK_BASS, 1.0f , 1.0f);
                    s.sendMessage("§8[§c!§8] §cOcorreu um erro ao salvar sua playerdata!");
                    Bukkit.getLogger().warning("[PlayerData] Ocorreu um erro ao tentar salvar a playerdata do " + player.getName() + e);
                }
            } else {
                config.set("Notification", true);
                try {
                    config.save(file);
                    s.sendMessage("§8[§a!§8] §aVocê ativou as notificações!");
                    player.playSound(player, Sound.BLOCK_NOTE_BLOCK_BIT, 1.0f , 1.0f);
                } catch (IOException e) {
                    player.playSound(player, Sound.BLOCK_NOTE_BLOCK_BASS, 1.0f , 1.0f);
                    s.sendMessage("§8[§c!§8] §cOcorreu um erro ao salvar sua playerdata!");
                    Bukkit.getLogger().warning("[PlayerData] Ocorreu um erro ao tentar salvar a playerdata do " + player.getName() + e);
                }
            }
            return true;
        }
        player.playSound(player, Sound.BLOCK_NOTE_BLOCK_BASS, 1.0f , 1.0f);
        player.sendMessage("§8[§c!§8] §cVocê não informou um argumento certo.");
        return true;
    }
}
