package net.minesky.commands;

import net.minesky.MineSkyEvents;
import net.minesky.config.DataManager;
import net.minesky.utils.EventItem;
import net.minesky.utils.SendMessages;
import net.minesky.utils.Utils;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.command.*;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class MSNECommand implements CommandExecutor, TabCompleter {

    @Override
    public List<String> onTabComplete(CommandSender s, Command cmd, String lbl, String[] args) {
        List<String> completions = new ArrayList<>();
        if (args.length == 1) {
            if (s.hasPermission("mineskyevents.reload")) completions.add("notificação");
            if (s.hasPermission("mineskyevents.reload")) completions.add("reload");
            if (s.hasPermission("mineskyevents.give")) completions.add("give");
            if (s.hasPermission("mineskyevents.vault")) completions.add("money");
            if (s.hasPermission("mineskyevents.clear")) completions.add("clear");
            return completions;
        }
        if (args[0].equalsIgnoreCase("money")) {
            completions.clear();
            completions.addAll(Utils.getOnlinePlayerNames());
            return completions;
        }
        return Collections.emptyList();
    }

    @Override
    public boolean onCommand(CommandSender s, Command cmd, String lbl, String[] args) {
        if (!(s instanceof Player)) {
            s.sendMessage(Utils.color("&cComando desativado temporáriamente"));
            return true;
        }

        Player player = (Player) s;

        if (args.length < 1) {
            player.playSound(player, Sound.BLOCK_NOTE_BLOCK_BIT, 1.0f, 1.0f);
            player.sendMessage(Utils.color("&e/msne reload &7- Recarregar as configs do plugin.\n&e/msne clear &7- Apague o inventário de todos os players.\n&e/msne money (Player) (Valor) &7- Mandar um skyes para o jogador.\n&e/msne notificação &7- Habilite e desabilite as notificações.\n&e/msne give &7- Receber todos os itens dos eventos.\n&e/msne adopt (Player) &7- \"Adotar\" um player\n&e/msne give &7- Receber todos os itens dos eventos."));
            return true;
        }

        switch (args[0].toLowerCase()) {
            case "adopt":
                if (args.length < 2) {
                    sendError(player, "Informe um player");
                    return true;
                }
                Player target = Bukkit.getPlayer(args[1]);
                if (target != null && target.isOnline()) {
                    sendBroadcast(player, target);
                } else {
                    sendError(player, "Este player não está online no momento");
                }
                return true;

            case "give":
                if (!player.hasPermission("mineskyevents.give")) {
                    sendError(player, "Você não pode executar esse comando.");
                    return true;
                }
                giveEventItems(player);
                return true;
            case "notificação":
                if (!s.hasPermission("mineskyevents.notify.enabled")) {
                    player.playSound(player, Sound.BLOCK_NOTE_BLOCK_BASS, 1.0f , 1.0f);
                    s.sendMessage("§cVocê não pode executar esse comando");
                    return true;
                }
                File file = DataManager.getFile(player.getUniqueId().toString(), "playerdata");
                FileConfiguration config = DataManager.getConfiguration(file);
                if (config.getBoolean("Notification")) {
                    config.set("Notification", false);
                    try {
                        config.save(file);
                        s.sendMessage("§cVocê desativou as notificações!");
                        player.playSound(player, Sound.BLOCK_NOTE_BLOCK_BIT, 1.0f , 1.0f);
                    } catch (IOException e) {
                        player.playSound(player, Sound.BLOCK_NOTE_BLOCK_BASS, 1.0f , 1.0f);
                        s.sendMessage("§cOcorreu um erro ao salvar sua playerdata!");
                        Bukkit.getLogger().warning("[PlayerData] Ocorreu um erro ao tentar salvar a playerdata do " + player.getName() + e);
                    }
                } else {
                    config.set("Notification", true);
                    try {
                        config.save(file);
                        s.sendMessage("§aVocê ativou as notificações!");
                        player.playSound(player, Sound.BLOCK_NOTE_BLOCK_BIT, 1.0f , 1.0f);
                    } catch (IOException e) {
                        player.playSound(player, Sound.BLOCK_NOTE_BLOCK_BASS, 1.0f , 1.0f);
                        s.sendMessage("§cOcorreu um erro ao salvar sua playerdata!");
                        Bukkit.getLogger().warning("[PlayerData] Ocorreu um erro ao tentar salvar a playerdata do " + player.getName() + e);
                    }
                }
                return true;
            case "clear":
                if (!s.hasPermission("mineskyevents.clear")) {
                    sendError(player, "Você não pode executar esse comando.");
                    return true;
                }
                Bukkit.getOnlinePlayers().forEach(p -> {
                    EventCommand.RevealPlayer(p);
                    EventCommand.clearInventory(p);
                });
                s.sendMessage(Utils.color("&aVocê apagou o inventário de todos os jogadores."));
                return true;
            case "money":
                if (!s.hasPermission("mineskyevents.money")) {
                    sendError(player, "Você não pode executar esse comando.");
                    return true;
                }
                if (args[1] == null) {
                    s.sendMessage(Utils.color("&cVocê deve indicar um jogador."));
                    return true;
                }

                Player player1 = Bukkit.getPlayer(args[1]);
                if (player1 == null) {
                    s.sendMessage(Utils.color("&cO jogador não foi encontrado."));
                    return true;
                }
                if (args[2] == null) {
                    s.sendMessage(Utils.color("&cVocê deve indicar um valor."));
                    return true;
                }

                SendMessages.sendPlayerMoney(player1, Integer.parseInt(args[2]));
                s.sendMessage(Utils.color("&aEnviando o valor de $" + args[2] + " para o jogador " + player1.getName()));
                return true;

            case "reload":
                if (!player.hasPermission("mineskyevents.reload")) {
                    sendError(player, "Você não pode executar esse comando.");
                    return true;
                }
                player.playSound(player, Sound.BLOCK_NOTE_BLOCK_BIT, 1.0f, 1.0f);
                MineSkyEvents.get().setupConfigs();
                player.sendMessage(Utils.color("&aMineskyEvents recarregado com sucesso!"));
                return true;

            /*case "cb_shopi": openShop(player, EventGUIS.getInventorySHOP_INICIAL()); return true;
            case "cb_shopb": openShop(player, EventGUIS.getInventorySHOP_BLOCOS()); return true;
            case "cb_shopc": openShop(player, EventGUIS.getInventorySHOP_COMBATE()); return true;
            case "cb_shopp": openShop(player, EventGUIS.getInventorySHOP_POÇOES()); return true;
            case "cb_shopf": openShop(player, EventGUIS.getInventorySHOP_FERRAMENTAS()); return true;
            case "cb_shopa": openShop(player, EventGUIS.getInventorySHOP_ATAQUE()); return true;
            case "cb_shopo": openShop(player, EventGUIS.getInventorySHOP_OUTROS()); return true;*/
        }
        return false;
    }

    private void sendError(Player player, String message) {
        player.playSound(player, Sound.BLOCK_NOTE_BLOCK_BASS, 1.0f, 1.0f);
        player.sendMessage("§c" + message);
    }

    private void sendBroadcast(Player player, Player target) {
        player.playSound(player, Sound.BLOCK_NOTE_BLOCK_BIT, 1.0f, 1.0f);
        SendMessages.sendMessage(new TextComponent("§b" + player.getName() + " §7adotou §b" + target.getName()));
    }

    private void giveEventItems(Player player) {
        List<ItemStack> eventItems = Arrays.asList(
                EventItem.getItem("SpleefITEM"), EventItem.getItem("SumoITEM"), EventItem.getItem("TNTHEAD"), EventItem.getItem("BarcoITEM"),
                EventItem.getItem("CheckPoint")
        );

        eventItems.forEach(item -> {
            player.getInventory().addItem(item);
        });
        player.playSound(player, Sound.BLOCK_NOTE_BLOCK_BIT, 1.0f, 1.0f);
        player.sendMessage("§aVocê recebeu todos os itens de eventos.");
    }

    private void openShop(Player player, Object shopInventory) {
        if (shopInventory instanceof org.bukkit.inventory.Inventory) {
            player.openInventory((org.bukkit.inventory.Inventory) shopInventory);
        }
    }
}
