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
        if (args.length == 0) {
            return false;
        }

        String aliasbuild = "alias-" + args[0] + "-end";
        String cmdbuild = "cmd-" + args[0] + "-end";

        try {
            if (args.length == 1) {
                if (p.hasPermission("lain.cmd.delcmd")) {

                    DotCommands dc = new DotCommands();
                    if (dc.containsCommand(args[0])) {
                        dc.removeCommand(args[0]);
                        plugin.broadcastMessage(ChatColor.YELLOW + p.getName() + " " + ChatColor.GREEN + "Removed '." + args[0] + "'!");
                    } else {
                        plugin.error(p, Messages.nomsgfound);
                    }

//                        plugin.getConfig().set(cmdbuild, null);
//                        for (int i = 0; i < Lain.commands.size(); i++) {
//                            if (Lain.commands.get(i).startsWith(args[0] + ":")) {
//                                Lain.commands.remove(i);
//                            }
//                        }
//                        Checkfiles.saveCommands();
//                        plugin.broadcastMessage(ChatColor.YELLOW + p.getName() + " " + ChatColor.GREEN + "Deleted the command '." + args[0] + "'!");
//                        plugin.saveConfig();
                } else {
                    plugin.badperms(p);
                }

            }
            if (args.length == 2 || args.length > 2) {
                if (p.hasPermission("lain.cmd.setcmd")) {

                    DotCommands dc = new DotCommands();

                    StringBuilder d = new StringBuilder();
                    for (int i = 1; i < args.length; i++) {
                        d.append(args[i]);
                        d.append(" ");
                    }
                    dc.setCommand(args[0].toLowerCase(), d.toString());
                    if (!dc.containsCommand(args[0].toLowerCase())) {
                        plugin.broadcastMessage(ChatColor.YELLOW + p.getName() + " " + ChatColor.GREEN + "Created the command '." + args[0].toLowerCase() + "' created! " + ChatColor.YELLOW + "Msg:'" + d.toString() + ChatColor.YELLOW + "'");
                        
                    } else {
                        plugin.broadcastMessage(ChatColor.YELLOW + p.getName() + " " + ChatColor.YELLOW + "Modifyed the command '." + args[0].toLowerCase() + "' " + ChatColor.YELLOW + "New msg:'" + d.toString() + ChatColor.YELLOW + "'");
                    }

//                    if (!plugin.getConfig().contains(aliasbuild)) {
//                        if (!plugin.getConfig().contains(cmdbuild)) {
//                            try {
//                                StringBuilder gaysex = new StringBuilder();
//                                for (int i = 1; i < args.length; i++) {
//                                    gaysex.append(args[i]).append(" ");
//                                }
//                                plugin.getConfig().set(cmdbuild, gaysex.toString());
//                                
//                                plugin.saveConfig();
//                            } catch (Exception ex) {
//                                plugin.error(p, ex.toString());
//                            }
//                            try {
//                                StringBuilder gaysex = new StringBuilder();
//                                for (int i = 1; i < args.length; i++) {
//                                    gaysex.append(args[i]).append(" ");
//                                }
//                                Lain.commands.add(args[0] + ":\t" + gaysex.toString());
//                                Checkfiles.saveCommands();
//                            } catch (Exception ex) {
//                                plugin.error(p, ex.toString());
//                            }
//                        } else {
//                            try {
//                                StringBuilder gaysex = new StringBuilder();
//                                for (int i = 1; i < args.length; i++) {
//                                    gaysex.append(args[i]).append(" ");
//                                }
//                                for (int i = 0; i < Lain.commands.size(); i++) {
//                                    if (Lain.commands.get(i).startsWith(args[0] + ":")) {
//                                        Lain.commands.set(i, args[0] + ":\t" + gaysex.toString());
//                                    }
//                                }
//                                Checkfiles.saveCommands();
//                                plugin.getConfig().set(cmdbuild, gaysex.toString());
//                               
//                                plugin.saveConfig();
//                            } catch (Exception ex) {
//                                plugin.error(p, ex.toString());
//                            }
//                        }
//                    }
                } else {
                    plugin.badperms(p);
                }
            }
        } catch (Exception ex) {
            plugin.error(p, ex.toString());
        }
        return true;
    }
}
