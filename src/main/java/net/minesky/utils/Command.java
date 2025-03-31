package net.minesky.utils;

import net.minesky.MineSkyEvents;
import net.minesky.commands.tabcompleter.EventCommandTabCompleter;
import net.minesky.config.ConfigManager;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandMap;
import org.bukkit.command.PluginCommand;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.Plugin;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.List;

public class Command {
    private static final FileConfiguration CONFIG = ConfigManager.getConfig("commands.yml");

    private CommandExecutor executor;
    private String command;
    private List<String> aliases;
    private String description;
    private PluginCommand pluginCommand;

    public Command(String command, CommandExecutor executor) {
        boolean enable = CONFIG.getBoolean("commands." + command + ".enabled");
        if (enable) {
            this.executor = executor;
            this.command = command;
            this.aliases = CONFIG.getStringList("commands." + command + ".aliases");
            this.description = CONFIG.getString("commands." + command + ".description");
            this.pluginCommand = createPluginCommand();
            registerPluginCommand();
        }
    }

    private PluginCommand createPluginCommand() {
        try {
            Constructor<PluginCommand> c = PluginCommand.class.getDeclaredConstructor(String.class, Plugin.class);
            c.setAccessible(true);

            PluginCommand cmd = c.newInstance(command, MineSkyEvents.get());
            cmd.setAliases(aliases);
            cmd.setDescription(description);
            cmd.setExecutor(executor);
            if (command.equals("event")) {
                cmd.setTabCompleter(new EventCommandTabCompleter());
            }

            return cmd;
        } catch (Throwable e) {
            e.printStackTrace();
        }

        return null;
    }

    private void registerPluginCommand() {
        if (pluginCommand == null) return;
        try {
            Field f = Bukkit.getPluginManager().getClass().getDeclaredField("commandMap");
            f.setAccessible(true);
            CommandMap commandMap = (CommandMap) f.get(Bukkit.getPluginManager());
            commandMap.register("mineskyevents", pluginCommand);
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }

    @Override
    public String toString() {
        return command;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((command == null) ? 0 : command.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Command other = (Command) obj;
        if (command == null) {
            return other.command == null;
        } else return command.equals(other.command);
    }
}
