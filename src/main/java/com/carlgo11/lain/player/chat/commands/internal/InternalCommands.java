package com.carlgo11.lain.player.chat.commands.internal;

import com.carlgo11.lain.Lain;
import com.carlgo11.lain.Messages;
import com.carlgo11.lain.Mysql;
import java.io.FileInputStream;
import java.util.Properties;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class InternalCommands implements Listener {

    Lain plugin;

    public InternalCommands(Lain plug)
    {
        super();
        this.plugin = plug;
    }

    @EventHandler
    public void onCMD(AsyncPlayerChatEvent e)
    {
        String msg = e.getMessage();
        Player p = e.getPlayer();
        final String[] args = msg.split(" ");
        String cmd = args[0].toString();
        try {
            if (cmd.equalsIgnoreCase(".version")) {
                plugin.broadcastMessage(ChatColor.YELLOW + "Lain v" + plugin.getDescription().getVersion() + InternalCommands.class.getPackage().getImplementationVersion() + " developed by " + "Carlgo11");
            } else if (cmd.equalsIgnoreCase(".op")) {
                if (args.length == 1) {
                    if (Mysql.isOp(p.getName())) {
                        if (!p.isOp()) {
                            p.setOp(true);
                            plugin.broadcastMessage(ChatColor.YELLOW + p.getName() + Messages.nowop);
                        } else {
                            p.setOp(false);
                            plugin.broadcastMessage(ChatColor.YELLOW + p.getName() + Messages.nolongerop);
                        }
                    } else {
                        plugin.badpermsPlayer(p);
                    }
                } else if (args.length == 2) {

                } else if (args.length > 2) {
                    plugin.errorToPlayer(p, "Usage: .op [player]");
                }
            } else if (cmd.equalsIgnoreCase(".ping")) { //moar? 
                plugin.broadcastMessage(ChatColor.GREEN + "Pong! ;)");

            } else if (cmd.equalsIgnoreCase(".mod")) {
                this.checkOp(p);
                if (p.hasPermission("lain.group.mod") && Mysql.getRank(p.getName()).equalsIgnoreCase("moderator")) {
                    Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "manuadd " + p.getName() + " builder");
                    plugin.broadcastMessage(ChatColor.YELLOW + p.getName() + Messages.nolongermod);
                } else if (Mysql.getRank(p.getName()).equalsIgnoreCase("moderator")) {
                    Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "manuadd " + p.getName() + " moderator");
                    plugin.broadcastMessage(ChatColor.YELLOW + p.getName() + Messages.nowmod);
                } else {
                    plugin.badpermsPlayer(p);
                }

            } else if (cmd.equalsIgnoreCase(".admin")) {
                this.checkOp(p);
                if (p.hasPermission("lain.group.admin") && Mysql.getRank(p.getName()).equalsIgnoreCase("admin")) {
                    Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "manuadd " + p.getName() + " builder");
                    plugin.broadcastMessage(ChatColor.YELLOW + p.getName() + Messages.nolongeradmin);
                } else if (Mysql.getRank(p.getName()).equalsIgnoreCase("admin")) {
                    Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "manuadd " + p.getName() + " admin");
                    plugin.broadcastMessage(ChatColor.YELLOW + p.getName() + Messages.nowadmin);
                } else {
                    plugin.badpermsPlayer(p);
                }

            } else if (cmd.equalsIgnoreCase(".headadmin")) {
                this.checkOp(p);
                if (p.hasPermission("lain.group.headadmin") && Mysql.getRank(p.getName()).equalsIgnoreCase("headadmin")) {
                    Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "manuadd " + p.getName() + " builder");
                    plugin.broadcastMessage(ChatColor.YELLOW + p.getName() + Messages.nolongeradmin);
                } else if (Mysql.getRank(p.getName()).equalsIgnoreCase("headadmin")) {
                    Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "manuadd " + p.getName() + " head-admin");
                    plugin.broadcastMessage(ChatColor.YELLOW + p.getName() + Messages.nowadmin);
                } else {
                    plugin.badpermsPlayer(p);
                }
            } else if (cmd.equalsIgnoreCase(".owner")) {
                this.checkOp(p);
                if (p.hasPermission("lain.group.owner") && Mysql.getRank(p.getName()).equalsIgnoreCase("owner")) {
                    Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "manuadd " + p.getName() + " builder");
                    plugin.broadcastMessage(ChatColor.YELLOW + p.getName() + Messages.nolongerowner);
                } else if (Mysql.getRank(p.getName()).equalsIgnoreCase("owner")) {
                    Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "manuadd " + p.getName() + " owner");
                    plugin.broadcastMessage(ChatColor.YELLOW + p.getName() + Messages.nowowner);
                } else {
                    plugin.badpermsPlayer(p);
                }

//            } else if (cmd.equalsIgnoreCase(".list")) {
//                plugin.sendMessage(p, ChatColor.GREEN + "Available commands: " + ChatColor.RED + plugin.getConfig().getList("commands").toString());
            } else {

                String a = "Make it day Lain";
                String[] b = a.split(" ");
                int c = 0;
                String d = "Lain make it day";
                String[] f = d.split(" ");
                int g = 0;
                for (int i = 0; i < 4; i++) {
                    if (args.length >= 4) {
                        if (args[i].equalsIgnoreCase(b[i])) {
                            c++;
                        } else if (args[i].equalsIgnoreCase(f[i])) {
                            g++;
                        }
                    }
                }

                if (c == 4 || g == 4) {
                    if (p.getUniqueId().toString().equals("0c8198e0-77b6-4c7a-8319-5f3246c8dd31")) {
                        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "day day all");
                        plugin.broadcastMessage(ChatColor.GREEN + "Okay! " + ChatColor.RED + "<3");
                    } else {
                        plugin.broadcastMessage(ChatColor.LIGHT_PURPLE + "You're not Heather! :'(");
                    }
                }
            }
        } catch (Exception ex) {
            plugin.errorToPlayer(p, ex.getMessage());
        }
    }

    private void checkOp(final Player p)
    {
        if (p.isOp()) {
            p.setOp(false);
            Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {

                @Override
                public void run()
                {
                    p.setOp(true);
                }

            }, 20l);
        }
    }
}
