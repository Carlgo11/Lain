package com.carlgo11.lain;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;

/**
 * Handling of 'dotcommands' stored in the MySQL database.
 */
public class ExternalCommands {

    @EventHandler
    public static void Main(String msg, Player p, Lain plugin)
    {
        final String[] args = msg.split(" ");
        String cmd = args[0].toLowerCase().replaceFirst(".", "");

        DotCommands dc = new DotCommands();
        if (args[0].startsWith(".")) {
            if (args.length > 1) {
                if (Bukkit.getOfflinePlayer(args[1]).isOnline()) {
                    if (dc.containsCommand(cmd) || dc.containsAlias(cmd)) {
                        broadcast(cmd, args, p, plugin, ChatColor.DARK_AQUA + args[1] + ": " + dc.getMessage(cmd));
                        Bukkit.getPlayer(args[1]).getWorld().playSound(Bukkit.getPlayer(args[1]).getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1, 0);
                    }
                } else if (dc.containsCommand(cmd) || dc.containsAlias(cmd)) {
                    broadcast(cmd, args, p, plugin, dc.getMessage(cmd));
                }
            } else if (dc.containsCommand(cmd) || dc.containsAlias(cmd)) {
                broadcast(cmd, args, p, plugin, dc.getMessage(cmd));
            }
        }
    }

    private static void broadcast(String cmd, String[] args, Player p, Lain plugin, String message)
    {
        String[] msg = message.split(" ");
        StringBuilder d = new StringBuilder();
        for (String m : msg) {
            if (m.contains("<player>")) {
                m = m.replaceAll("<player>", p.getName());
            }
            if (m.contains("\n")) {
                m = m.replaceAll("\n", System.getProperty("line.separator"));
            }
            d.append(m);
            d.append(" ");
        }
        plugin.broadcastMessage(d.toString());
    }
}
