package com.carlgo11.lain;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;

public class ExternalCommands {

    public static String url = "jdbc:mysql://localhost:3306/";
    public static String username = "Lain";
    public static String password = "JAMUPsBMB7mrZNzx";
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
                        broadcast(cmd, args, p, plugin, ChatColor.DARK_AQUA + args[1] + ": " + dc.getMessage(cmd));
                        Bukkit.getPlayer(args[1]).getWorld().playSound(Bukkit.getPlayer(args[1]).getLocation(), Sound.ORB_PICKUP, 1, 0);
                    }
                } else {
                    if (dc.containsCommand(cmd) || dc.containsAlias(cmd)) {
                        broadcast(cmd, args, p, plugin, dc.getMessage(cmd));
                    }
                }
            } else {
                if (dc.containsCommand(cmd) || dc.containsAlias(cmd)) {
                    broadcast(cmd, args, p, plugin, dc.getMessage(cmd));
                }
            }
        }
    }
    
   static void broadcast(String cmd, String[] args, Player p, Lain plugin, String message){
        DotCommands dc = new DotCommands();
        String[] msg = message.split(" ");
        StringBuilder d = new StringBuilder();
        for(int i = 0; i < msg.length; i++){
            msg[i].replaceAll("{player}", p.getName());
            msg[i].replaceAll("{build}", ExternalCommands.class.getPackage().getImplementationVersion());
            msg[i].replaceAll("\n", System.getProperty("line.separator"));
            d.append(msg[i]);
            d.append(" ");
            
            
        }
        
        plugin.broadcastMessage(d.toString());
    }
}
