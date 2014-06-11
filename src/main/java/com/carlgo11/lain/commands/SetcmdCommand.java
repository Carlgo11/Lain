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
                if (plugin.getConfig().contains(cmdbuild)) {
                    if (p.hasPermission("lain.cmd.delcmd")) {
                        plugin.getConfig().set(cmdbuild, null);
                        for(int i = 0; i < Lain.commands.size(); i++){
                                    if(Lain.commands.get(i).startsWith(args[0]+":")){
                                        Lain.commands.remove(i);
                                    }
                                }
                        Checkfiles.saveCommands();
                        plugin.broadcastMessage(ChatColor.YELLOW + p.getName() + " " + ChatColor.GREEN + "Deleted the command '." + args[0] + "'!");
                        plugin.saveConfig();
                    } else {
                        plugin.badpermsSender(p);
                    }
                } else {
                    plugin.errorToSender(p, Messages.nomsgfound);
                }

            }
            if (args.length == 2 || args.length > 2) {
                if (p.hasPermission("lain.cmd.setcmd")) {
                    if (!plugin.getConfig().contains(aliasbuild)) {
                        if (!plugin.getConfig().contains(cmdbuild)) {
                            try {
                                StringBuilder gaysex = new StringBuilder();
                                for (int i = 1; i < args.length; i++) {
                                    gaysex.append(args[i]).append(" ");
                                }
                                plugin.getConfig().set(cmdbuild, gaysex.toString());
                                plugin.broadcastMessage(ChatColor.YELLOW + p.getName() + " " + ChatColor.GREEN + "Created the command '." + args[0] + "' created! " + ChatColor.YELLOW + "Msg:'" + gaysex.toString() + ChatColor.YELLOW + "'");
                                plugin.saveConfig();
                            } catch (Exception ex) {
                                plugin.errorToSender(p, ex.toString());
                            }
                            try{
                                StringBuilder gaysex = new StringBuilder();
                                for (int i = 1; i < args.length; i++) {
                                    gaysex.append(args[i]).append(" ");
                                }
                                Lain.commands.add(args[0]+":\t"+gaysex.toString());
                            Checkfiles.saveCommands();
                            }catch(Exception ex){
                                    plugin.errorToSender(p, ex.toString());
                                }
                        } else {
                            try {
                                StringBuilder gaysex = new StringBuilder();
                                for (int i = 1; i < args.length; i++) {
                                    gaysex.append(args[i]).append(" ");
                                }
                                for(int i = 0; i < Lain.commands.size(); i++){
                                    if(Lain.commands.get(i).startsWith(args[0]+":")){
                                        Lain.commands.set(i, args[0]+":\t"+gaysex.toString());
                                    }
                                }
                                Checkfiles.saveCommands();
                                plugin.getConfig().set(cmdbuild, gaysex.toString());
                                plugin.broadcastMessage(ChatColor.YELLOW + p.getName() + " " + ChatColor.YELLOW + "Modifyed the command '." + args[0].toString() + "' " + ChatColor.YELLOW + "New msg:'" + gaysex.toString() + ChatColor.YELLOW + "'");
                                plugin.saveConfig();
                            } catch (Exception ex) {
                                plugin.errorToSender(p, ex.toString());
                            }
                            
                           /* int si = plugin.getConfig().getStringList("commands").size();
                            si++;
                            plugin.getConfig().getStringList("commands").set(si, args[0].toString());
                                   */
                        }
                    } else {

                    }
                } else {
                    plugin.badpermsSender(p);
                }
            }
        } catch (Exception ex) {
            plugin.errorToSender(p, ex.toString());
        }
        return true;
    }
}
