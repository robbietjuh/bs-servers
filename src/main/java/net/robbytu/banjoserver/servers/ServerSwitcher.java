package net.robbytu.banjoserver.servers;

import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class ServerSwitcher {
    static void switchTo(Player p, String server) {
        p.sendMessage(ChatColor.GRAY + " * Verbinden met " + server + "...");

        ByteArrayDataOutput out = ByteStreams.newDataOutput();

        out.writeUTF("Connect");
        out.writeUTF(server);

        p.sendPluginMessage(Main.plugin, "BungeeCord", out.toByteArray());

    }
}
