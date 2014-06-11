package com.carlgo11.lain.commands;

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

        String aliasbuild = "alias-" + args[0] + "-end";
        String cmdbuild = "cmd-" + args[0] + "-end";

        if (args.length == 1) {
            if (plugin.getConfig().contains(aliasbuild)) {
                if (sender.hasPermission("lain.cmd.delalias")) {
                    plugin.getConfig().set(aliasbuild, null);
                    plugin.broadcastMessage(ChatColor.YELLOW + sender.getName() + " " + ChatColor.GREEN + "Deleted the alias '." + args[0] + "'!");
                    plugin.saveConfig();
                } else {
                    plugin.badpermsSender(sender);
                }
            } else {
                plugin.errorToSender(sender, Messages.nomsgfound);
            }

        } else if (args.length == 2) {
            String cmdbuild2 = "cmd-" + args[1] + "-end";
            if (sender.hasPermission("lain.cmd.addalias")) {
                if (!plugin.getConfig().contains(aliasbuild) && !plugin.getConfig().contains(cmdbuild)) {
                    try {
                        plugin.getConfig().set(aliasbuild, cmdbuild2);
                        plugin.broadcastMessage(ChatColor.YELLOW + sender.getName() + " " + ChatColor.GREEN + "Created an alias named '." + args[0] + "' for '." + args[1] + "'.");
                        plugin.saveConfig();
                    } catch (Exception ex) {
                        plugin.errorToSender(sender, ex.toString());
                    }
                } else {
                    plugin.errorToSender(sender, "There's already a command/alias called that. You must remove the existing command/alias before you can create a new one!");
                }
            } else {
                plugin.badpermsSender(sender);
            }
        } else {
            return false;
        }
        return true;
    }
}
