package com.carlgo11.lain.commands;

import com.carlgo11.lain.Lain;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class BroadcastCommand implements CommandExecutor {

    private Lain lain;

    public BroadcastCommand(Lain plug)
    {
        this.lain = plug;
    }

    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args)
    {
        if (sender.hasPermission("lain.cmd.lain.broadcast")) {
            String help = ChatColor.RED + "Available messages: " + "attack, " + "busy-staff, " + "busy-server, " + "maintenance-awaiting, " + "currently-maintaining";
            if (args.length == 1) {
                String brbuild = "broadcast-" + args[0].toString();

                if (lain.getConfig().contains(brbuild)) {
                    lain.broadcastMessage(ChatColor.GREEN + lain.getConfig().getString(brbuild));
                } else {
                    lain.error(sender, help);
                }
            } else {
                lain.error(sender, help);
            }
        } else {
            lain.badperms(sender);
        }

        return true;
    }
}
