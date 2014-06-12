package com.carlgo11.lain.player.chat.commands;

import com.carlgo11.lain.Lain;
import com.carlgo11.lain.Messages;
import com.carlgo11.lain.Mysql;
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
                versionCommand(args);
            } else if (cmd.equalsIgnoreCase(".op")) {
                opCommand(p, cmd, args);
            } else if (cmd.equalsIgnoreCase(".ping")) {
                plugin.broadcastMessage(ChatColor.GREEN + "Pong! :)");
            } else if (cmd.equalsIgnoreCase(".mod")) {
                modCommand(p, cmd, args);
            } else if (cmd.equalsIgnoreCase(".admin")) {
                adminCommand(p, cmd, args);
            } else if (cmd.equalsIgnoreCase(".headadmin")) {
                headadminCommand(p, cmd, args);
            } else if (cmd.equalsIgnoreCase(".owner")) {
                ownerCommand(p, cmd, args);
            } else if (cmd.equalsIgnoreCase(".g") || cmd.equalsIgnoreCase(".google")) {
                googleCommand(p, cmd, args);
            }else{
                ExternalCommands.Main(msg, p, plugin);
            }

        } catch (Exception ex) {
            plugin.error(p, ex.getMessage());
        }
    }

    void versionCommand(String args[])
    {
        if(args.length == 2 && args[1].equalsIgnoreCase("-all")){
            plugin.broadcastMessage(ChatColor.YELLOW + "Lain v2." + InternalCommands.class.getPackage().getImplementationVersion() + " (" + InternalCommands.class.getPackage().getImplementationTitle() + ") developed by " + "Carlgo11");
        }else{
          plugin.broadcastMessage(ChatColor.YELLOW + "Lain v2." + InternalCommands.class.getPackage().getImplementationVersion() + " developed by " + "Carlgo11");  
        }
    }

    void opCommand(Player p, String cmd, String[] args)
    {
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
                plugin.badperms(p);
            }
        } else if (args.length == 2) {

        } else if (args.length > 2) {
            plugin.error(p, "Usage: .op [player]");
        }
    }

    void modCommand(Player p, String cmd, String[] args)
    {
        this.checkOp(p);
        if (p.hasPermission("lain.group.mod") && Mysql.getRank(p.getName()).equalsIgnoreCase("moderator")) {
            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "manuadd " + p.getName() + " builder");
            plugin.broadcastMessage(ChatColor.YELLOW + p.getName() + Messages.nolongermod);
        } else if (Mysql.getRank(p.getName()).equalsIgnoreCase("moderator")) {
            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "manuadd " + p.getName() + " moderator");
            plugin.broadcastMessage(ChatColor.YELLOW + p.getName() + Messages.nowmod);
        } else {
            plugin.badperms(p);
        }
    }

    void adminCommand(Player p, String cmd, String[] args)
    {
        this.checkOp(p);
        if (p.hasPermission("lain.group.admin") && Mysql.getRank(p.getName()).equalsIgnoreCase("admin")) {
            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "manuadd " + p.getName() + " builder");
            plugin.broadcastMessage(ChatColor.YELLOW + p.getName() + Messages.nolongeradmin);
        } else if (Mysql.getRank(p.getName()).equalsIgnoreCase("admin")) {
            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "manuadd " + p.getName() + " admin");
            plugin.broadcastMessage(ChatColor.YELLOW + p.getName() + Messages.nowadmin);
        } else {
            plugin.badperms(p);
        }
    }

    void headadminCommand(Player p, String cmd, String[] args)
    {
        this.checkOp(p);
        if (p.hasPermission("lain.group.headadmin") && Mysql.getRank(p.getName()).equalsIgnoreCase("headadmin")) {
            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "manuadd " + p.getName() + " builder");
            plugin.broadcastMessage(ChatColor.YELLOW + p.getName() + Messages.nolongeradmin);
        } else if (Mysql.getRank(p.getName()).equalsIgnoreCase("headadmin")) {
            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "manuadd " + p.getName() + " head-admin");
            plugin.broadcastMessage(ChatColor.YELLOW + p.getName() + Messages.nowadmin);
        } else {
            plugin.badperms(p);
        }
    }

    void ownerCommand(Player p, String cmd, String[] args)
    {
        this.checkOp(p);
        if (p.hasPermission("lain.group.owner") && Mysql.getRank(p.getName()).equalsIgnoreCase("owner")) {
            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "manuadd " + p.getName() + " builder");
            plugin.broadcastMessage(ChatColor.YELLOW + p.getName() + Messages.nolongerowner);
        } else if (Mysql.getRank(p.getName()).equalsIgnoreCase("owner")) {
            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "manuadd " + p.getName() + " owner");
            plugin.broadcastMessage(ChatColor.YELLOW + p.getName() + Messages.nowowner);
        } else {
            plugin.badperms(p);
        }
    }

    void googleCommand(Player p, String cmd, String[] args)
    {
        StringBuilder sq = new StringBuilder();
        for (int i = 1; i < args.length; i++) {
            sq.append(args[i]);
            sq.append("+");
        }
        plugin.broadcastMessage(ChatColor.GREEN + "http://google.com/search?q=" + sq);
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
