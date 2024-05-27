package minesky.msne.commands.console;

import minesky.msne.MineSkyEvents;
import minesky.msne.utils.SendMessages;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class MSNECommandConsole {
    public CommandSender s;
    public Command cmd;
    public String lbl;
    public String[] args;

    public MSNECommandConsole(CommandSender s, Command cmd, String lbl, String[] args) {
        this.s = s;
        this.cmd = cmd;
        this.lbl = lbl;
        this.args = args;
        onCommandConsole();
    }

    public void onCommandConsole() {
        String version = MineSkyEvents.get().getDescription().getVersion();
        if (args.length < 1) {
            s.sendMessage("§6§lMinesky Events §8» §6v" + version + "\n§6Notification §8- §7Enable and disable notifications\n§6Reload §8- §7Reload the plugin.\n\n§6Author§8: §7zBrunoC_");
            return;
        }
        if (args[0].equalsIgnoreCase("adopt")) {
            if (args.length < 2) {
                s.sendMessage("§8[§c!§8] §cInforme um player");
                return;
            }
            Player adotar = Bukkit.getPlayer(args[1]);
            if (adotar != null && adotar.isOnline()) {
                TextComponent messageADOPT = new TextComponent("§bConsole §7adotou §b" + adotar.getName());
                SendMessages.sendMessageBCMSNE(messageADOPT);
            } else {
                s.sendMessage("§8[§c!§8] §cEste player não está online no momento");
            }
            return;
        }
        if (args[0].equalsIgnoreCase("reload")) {
            MineSkyEvents.carregarConfigs();
            s.sendMessage("§6§lMinesky Events §8» §6v" + version + "\n§6Author§8: §7zBrunoC_\n§6Version§8: §7" + version + "\n\n§6MineSky Events §7reloaded successfully.\n§7NOTE: Some changes will only take effect when the server is restarted.");
            return;
        }
        s.sendMessage("§8[§c!§8] §cVocê não informou um argumento certo.");
    }
}
