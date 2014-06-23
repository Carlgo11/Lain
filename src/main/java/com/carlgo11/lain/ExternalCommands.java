package com.carlgo11.lain;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;

public class ExternalCommands {

    @EventHandler
    static public void Main(String msg, Player p, Lain plugin)
    {
        final String[] args = msg.split(" ");
        String cmd = args[0].toLowerCase().replaceFirst(".", "");

        DotCommands dc = new DotCommands();
        if (args[0].startsWith(".")) {
            if (args.length > 1) {
                if (Bukkit.getOfflinePlayer(args[1]).isOnline()) {
                    if (dc.containsCommand(cmd) || dc.containsAlias(cmd)) {
                        plugin.broadcastMessage(ChatColor.DARK_AQUA + args[1] + ": " + dc.getMessage(cmd));
                        Bukkit.getPlayer(args[1]).getWorld().playSound(Bukkit.getPlayer(args[1]).getLocation(), Sound.ORB_PICKUP, 1, 0);
                    }
                } else {
                    if (dc.containsCommand(cmd) || dc.containsAlias(cmd)) {
                        plugin.broadcastMessage(dc.getMessage(cmd));
                    }
                }
            } else {
                if (dc.containsCommand(cmd) || dc.containsAlias(cmd)) {
                    plugin.broadcastMessage(dc.getMessage(cmd));
                }
            }
        }
    }
}
