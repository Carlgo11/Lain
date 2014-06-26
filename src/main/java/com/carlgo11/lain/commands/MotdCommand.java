package com.carlgo11.lain.commands;

import com.carlgo11.lain.Lain;
import com.carlgo11.lain.Mysql;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class MotdCommand implements CommandExecutor {

    private Lain plugin;

    public MotdCommand(Lain plug)
    {
        this.plugin = plug;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args)
    {
        if(sender.hasPermission("Lain.motd")){
            if(args.length > 1){
                StringBuilder d = new StringBuilder();
                for(int i = 0; i < args.length; i++){
                    d.append(args[i]);
                    d.append(" ");
                }
                Mysql.setMOTD(d.toString());
                plugin.sendMessage(sender,  ChatColor.GREEN+"MOTD changed to \""+ChatColor.RESET+d.toString()+ChatColor.GREEN+"\"");
            }else{
                return false;
            }
        }else{
            plugin.badperms(sender);
        }
        return true;
    }

}
