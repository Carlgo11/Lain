package com.carlgo11.lain.commands;

import com.carlgo11.lain.ChatCommandHandler;
import com.carlgo11.lain.DotCommands;
import com.carlgo11.lain.Lain;
import com.carlgo11.lain.Messages;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SetaliasCommand implements CommandExecutor {

    private Lain plugin;

    public SetaliasCommand(Lain plug)
    {
        this.plugin = plug;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args)
    {
        if (args.length == 0) {
            return false;
        }
        Player p = Bukkit.getPlayer(sender.getName());



        DotCommands dc = new DotCommands();
        if(ChatCommandHandler.containsCommand(args[0])){
        if (args.length == 1) {
            if (dc.containsAlias(args[0])) {
                if (sender.hasPermission("lain.cmd.delalias")) {
                    dc.removeAlias(args[0], "");
                    plugin.broadcastMessage(ChatColor.YELLOW + sender.getName() + " " + ChatColor.GREEN + "Deleted the alias '." + args[0] + "'!");
                } else {
                    plugin.badperms(sender);
                }
            } else {
                plugin.error(sender, Messages.nomsgfound);
            }

        } else if (args.length == 2) {
            if (sender.hasPermission("lain.cmd.addalias")) {
                if (!dc.containsAlias(args[0])) {
                    if(dc.containsCommand(args[1])){
                    try {
                        dc.setAlias(args[0], args[1]);
                        plugin.broadcastMessage(ChatColor.YELLOW + sender.getName() + " " + ChatColor.GREEN + "Created an alias named '." + args[0] + "' for '." + args[1] + "'.");
                        
                    } catch (Exception ex) {
                        plugin.error(sender, ex.toString());
                    }
                    }else{
                     plugin.error(sender, "No commanded named "+args[1]+" found.");
                    }
                } else {
                    plugin.error(sender, "There's already a command/alias called that. You must remove the existing command/alias before you can create a new one!");
                }
            } else {
                plugin.badperms(sender);
            }
        } else {
            return false;
        }
        }else{
            plugin.error(sender, "There's already a internal command named that.");
        }
        return true;
    }
}
