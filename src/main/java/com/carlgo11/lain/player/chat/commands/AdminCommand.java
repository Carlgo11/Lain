package com.carlgo11.lain.player.chat.commands;

import com.carlgo11.lain.ChatCommands;
import com.carlgo11.lain.Lain;
import com.carlgo11.lain.Messages;
import com.carlgo11.lain.Mysql;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class AdminCommand implements ChatCommands{

    public String getCommandName()
    {
        return "admin";
    }

    public void onMessage(Lain Lain, Player p, String msg, String cmd, String[] args)
    {
         Lain.checkOp(p);
        if (p.hasPermission("lain.group.admin") && Mysql.getRank(p.getName()).equalsIgnoreCase("admin")) {
            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "manuadd " + p.getName() + " builder");
            Lain.broadcastMessage(ChatColor.YELLOW + p.getName() + Messages.nolongeradmin);
        } else if (Mysql.getRank(p.getName()).equalsIgnoreCase("admin")) {
            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "manuadd " + p.getName() + " admin");
            Lain.broadcastMessage(ChatColor.YELLOW + p.getName() + Messages.nowadmin);
        } else {
            Lain.badperms(p);
        }
    }

}
