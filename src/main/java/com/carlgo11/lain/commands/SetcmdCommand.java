package com.carlgo11.lain.commands;

import com.carlgo11.lain.*;
import org.bukkit.ChatColor;
import org.bukkit.command.*;

public class SetcmdCommand implements CommandExecutor {

    private Lain plugin;

    public SetcmdCommand(Lain plug)
    {
        this.plugin = plug;
    }

    @Override
    public boolean onCommand(CommandSender p, Command cmd, String commandLabel, String[] args)
    {
        if (p.hasPermission("lain.command.setcmd")) {
            if (args.length == 0) {
                return false;
            }

            if (!ChatCommandHandler.internalCommandExists(args[0])) {
                try {
                    if (args.length == 1) {
                        if (p.hasPermission("lain.command.setcmd.delcmd")) {

                            DotCommands dc = new DotCommands();
                            if (dc.containsCommand(args[0])) {
                                dc.removeCommand(args[0]);
                                plugin.broadcastMessage(ChatColor.YELLOW + p.getName() + " " + ChatColor.GREEN + "Removed '" + ChatColor.RESET + "." + args[0] + ChatColor.GREEN + "'!");
                            } else {
                                plugin.error(p, Messages.nomsgfound);
                            }
                        } else {
                            plugin.badperms(p);
                        }
                    }
                    if (args.length == 2 || args.length > 2) {
                        DotCommands dc = new DotCommands();
                        StringBuilder d = new StringBuilder();
                        for (int i = 1; i < args.length; i++) {
                            d.append(args[i]);
                            d.append(" ");
                        }
                        dc.setCommand(args[0].toLowerCase(), d.toString());
                        if (!dc.containsCommand(args[0].toLowerCase())) {
                            plugin.broadcastMessage(ChatColor.YELLOW + p.getName() + " " + ChatColor.GREEN + "Created the command '" + ChatColor.RESET + "." + args[0].toLowerCase() + ChatColor.YELLOW + "' created! Msg:'" + ChatColor.RESET + d.toString() + ChatColor.YELLOW + "'");

                        } else {
                            plugin.broadcastMessage(ChatColor.YELLOW + p.getName() + " " + ChatColor.YELLOW + "Modifyed the command '" + ChatColor.RESET + "." + args[0].toLowerCase() + ChatColor.YELLOW + "' New msg:'" + ChatColor.RESET + d.toString() + ChatColor.YELLOW + "'");
                        }
                    }
                } catch (Exception ex) {
                    plugin.error(p, ex.toString());
                }
            } else {
                plugin.error(p, "There's already a internal command named that.");
            }
        } else {
            plugin.badperms(p);
        }
        return true;
    }
}
