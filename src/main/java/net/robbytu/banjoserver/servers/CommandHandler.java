package net.robbytu.banjoserver.servers;

import net.robbytu.banjoserver.framework.api.ServerAPI;
import net.robbytu.banjoserver.framework.api.SwitchAPI;
import net.robbytu.banjoserver.framework.interfaces.Server;
import net.robbytu.banjoserver.framework.interfaces.Switch;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class CommandHandler implements CommandExecutor {
    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("Console kan natuurlijk niet naar andere servers teleporten :-)");
            return true;
        }

        if (commandLabel.equalsIgnoreCase("server")) {
            if(args.length == 0 || args.length > 2) {
                // Build a menu
                IconMenu menu = new IconMenu("Kies een server", 9, new IconMenu.OptionClickEventHandler() {
                    @Override
                    public void onOptionClick(IconMenu.OptionClickEvent event) {
                        String server = event.getName().toLowerCase();
                        Player p = event.getPlayer();

                        ServerSwitcher.switchTo(p, server);

                        event.setWillDestroy(true);
                    }
                }, Main.plugin);

                // Fetch switcher configs
                Switch[] switches = SwitchAPI.getSwitches();
                for(int i = 0; i < switches.length; i ++) {
                    Switch switchObj = switches[i];
                    if(switchObj.type == 1) menu.setOption(i, new ItemStack(switchObj.switchMaterial, 1), switchObj.serverName, switchObj.switchDescription);
                }

                // Show the menu
                menu.open((Player) sender);
            }
            else if(args.length == 1) {
                // Check wether this server exists and is active
                for(Server server : ServerAPI.getOnlineServers()) {
                    if(server.serverName.equalsIgnoreCase(args[0])) {
                        String serverName = server.serverName.toLowerCase();
                        Player p = (Player) sender;

                        ServerSwitcher.switchTo(p, serverName);
                        break;
                    }
                }
            }
        }

        return true;
    }
}
