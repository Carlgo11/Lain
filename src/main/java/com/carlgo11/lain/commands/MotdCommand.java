package com.carlgo11.lain.commands;

import com.carlgo11.lain.Lain;
import com.carlgo11.lain.Mysql;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class MotdCommand implements CommandExecutor {

    private final Lain plugin;

    public MotdCommand(Lain plug)
    {
        this.plugin = plug;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args)
    {
        if (sender.hasPermission("Lain.command.motd")) {
            if (args.length >= 1) {
                StringBuilder d = new StringBuilder();
                for (String arg : args) {
                    d.append(arg);
                    d.append(" ");
                }
                Mysql.setMOTD(d.toString());
                plugin.broadcastMessage(ChatColor.GREEN + sender.getName() + " changed the MOTD to \"" + ChatColor.RESET + d.toString() + ChatColor.GREEN + "\"");
            } else {
                return false;
            }
        } else {
            plugin.badperms(sender);
        }
        return true;
    }
}
