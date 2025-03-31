package net.minesky.commands;

import net.minesky.utils.SendMessages;
import org.bukkit.ChatColor;
import org.bukkit.command.*;
import org.bukkit.entity.Player;

public class SairCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender s, Command cmd, String lbl, String[] args) {
        if (s instanceof ConsoleCommandSender || s instanceof RemoteConsoleCommandSender || s instanceof BlockCommandSender) {
            s.sendMessage(ChatColor.RED + "Somente players!");
            return true;
        }
        Player player = (Player) s;

        SendMessages.sendConnection(player);
        return true;
    }
}
