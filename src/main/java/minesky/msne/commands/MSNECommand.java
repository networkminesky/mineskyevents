package minesky.msne.commands;

import minesky.msne.MineSkyEvents;
import minesky.msne.addons.MMOItem;
import minesky.msne.addons.Vault;
import minesky.msne.commands.console.EventCommandConsole;
import minesky.msne.commands.console.MSNECommandConsole;
import minesky.msne.config.DataManager;
import minesky.msne.config.Locations;
import minesky.msne.discord.EventsMessage;
import minesky.msne.events.CaptureBandeiraEvent;
import minesky.msne.events.CorridaBoatEvent;
import minesky.msne.events.CorridaEvent;
import minesky.msne.events.ParapenteEvent;
import minesky.msne.system.event.EventGUIS;
import minesky.msne.system.event.EventPlayerManager;
import minesky.msne.utils.EventItem;
import minesky.msne.utils.SendMessages;
import minesky.msne.utils.Util;
import net.md_5.bungee.api.ChatColor;
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
            player.getInventory().addItem(EventItem.FlagORANGE);
            player.getInventory().addItem(EventItem.FlagLIME);
            player.getInventory().addItem(EventItem.FlagITEM_WOODEN_SWORD);
            player.getInventory().addItem(EventItem.FlagITEM_STONE_SWORD);
            player.getInventory().addItem(EventItem.FlagITEM_IRON_SWORD);
            player.getInventory().addItem(EventItem.FlagITEM_DIAMOND_SWORD);
            player.getInventory().addItem(EventItem.FlagITEM_WOODEN_AXE);
            player.getInventory().addItem(EventItem.FlagITEM_IRON_AXE);
            player.getInventory().addItem(EventItem.FlagITEM_GOLDEN_AXE);
            player.getInventory().addItem(EventItem.FlagITEM_DIAMOND_AXE);
            player.getInventory().addItem(EventItem.FlagITEM_WOODEN_PICKAXE);
            player.getInventory().addItem(EventItem.FlagITEM_IRON_PICKAXE);
            player.getInventory().addItem(EventItem.FlagITEM_GOLDEN_PICKAXE);
            player.getInventory().addItem(EventItem.FlagITEM_DIAMOND_PICKAXE);
            player.getInventory().addItem(EventItem.FlagITEM_ARMOR_HELMET_LIME);
            player.getInventory().addItem(EventItem.FlagITEM_ARMOR_CHESTPLATE_LIME);
            player.getInventory().addItem(EventItem.FlagITEM_ARMOR_LEGGING_LIME);
            player.getInventory().addItem(EventItem.FlagITEM_ARMOR_BOTTS_LIME);
            player.getInventory().addItem(EventItem.FlagITEM_ARMOR_HELMET_ORANGE);
            player.getInventory().addItem(EventItem.FlagITEM_ARMOR_CHESTPLATE_ORANGE);
            player.getInventory().addItem(EventItem.FlagITEM_ARMOR_LEGGING_ORANGE);
            player.getInventory().addItem(EventItem.FlagITEM_ARMOR_BOTTS_ORANGE);
            player.getInventory().addItem(EventItem.FlagITEM_SHIELD);
            player.getInventory().addItem(EventItem.FlagITEM_SHEARS);
            player.getInventory().addItem(EventItem.FlagITEM_FLINT_AND_STEEL);
            player.getInventory().addItem(EventItem.FlagITEM_BOW);
            player.getInventory().addItem(EventItem.FlagITEM_BOW_POWER);
            player.getInventory().addItem(EventItem.FlagITEM_BOW_POWER_PUNCH);
            player.getInventory().addItem(EventItem.FlagITEM_TOWER);
            player.getInventory().addItem(EventItem.FlagITEM_CHAILMALL_LEGGING);
            MMOItem.darParaglider(player);
            player.playSound(player, Sound.BLOCK_NOTE_BLOCK_BIT, 1.0f , 1.0f);
            s.sendMessage("§8[§a!§8] §aVocê recebeu todos os itens de eventos.");
            return true;
        }
        if (args[0].equalsIgnoreCase("cb_shopI")) {
            player.openInventory(EventGUIS.getInventorySHOP_INICIAL());
            return true;
        }
        if (args[0].equalsIgnoreCase("cb_shopB")) {
            player.openInventory(EventGUIS.getInventorySHOP_BLOCOS());
            return true;
        }
        if (args[0].equalsIgnoreCase("cb_shopC")) {
            player.openInventory(EventGUIS.getInventorySHOP_COMBATE());
            return true;
        }
        if (args[0].equalsIgnoreCase("cb_shopP")) {
            player.openInventory(EventGUIS.getInventorySHOP_POÇOES());
            return true;
        }
        if (args[0].equalsIgnoreCase("cb_shopF")) {
            player.openInventory(EventGUIS.getInventorySHOP_FERRAMENTAS());
            return true;
        }
        if (args[0].equalsIgnoreCase("cb_shopA")) {
            player.openInventory(EventGUIS.getInventorySHOP_ATAQUE());
            return true;
        }
        if (args[0].equalsIgnoreCase("cb_shopO")) {
            player.openInventory(EventGUIS.getInventorySHOP_OUTROS());
            return true;
        }
        if (args[0].equalsIgnoreCase("TESTE")) {
            if (s.hasPermission("mineskyevents.admin")) {
                EventGUIS.upgradeSWORD.put(player, 0);
                EventGUIS.upgradeArmor.put(player, 0);
                EventGUIS.upgradePICKAXE.put(player, 0);
                EventGUIS.upgradeAXE.put(player, 0);
                if (args[1].equalsIgnoreCase("1")) {
                    CaptureBandeiraEvent.time.put(player, "Green");
                }
                if (args[1].equals("2")) {
                    CaptureBandeiraEvent.time.put(player, "Orange");
                }
            }
        }
        if (args[0].equalsIgnoreCase("tester")) {
            if (s.hasPermission("mineskyevents.admin")) {
                CaptureBandeiraEvent.restaurar();
            }
        }
        if (args[0].equalsIgnoreCase("testec")) {
            if (s.hasPermission("mineskyevents.admin")) {
                TextComponent text = new TextComponent("§8| §aTestando color hex VELOCITY");
                SendMessages.sendMessageCHMSNE("#2493b4", "Capture a Bandeira ", text);
            }
        }
        if (args[0].equalsIgnoreCase("testem")) {
            if (s.hasPermission("mineskyevents.admin")) {
                TextComponent text = new TextComponent("§8| §aMensagem teste!");
                SendMessages.sendPlayerCHMSNE(player, "#2493b4", "Capture a Bandeira ", text);
            }
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
