package net.robbytu.banjoserver.servers;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {
    static Main plugin;

    @Override
    public void onEnable() {
        this.plugin = this;

        getCommand("servers").setExecutor(new CommandHandler());
        Bukkit.getMessenger().registerOutgoingPluginChannel(this, "BungeeCord");


    }

    @Override
    public void onDisable() {

    }
}
