package net.robbytu.banjoserver.servers;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {
    static Main plugin;

    @Override
    public void onEnable() {
        this.plugin = this;
        Bukkit.getMessenger().registerOutgoingPluginChannel(this, "BungeeCord");
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        return (new CommandHandler().onCommand(sender, cmd, label, args));
    }
}
