package com.carlgo11.lain.player.chat.commands;

import com.carlgo11.lain.ChatCommands;
import com.carlgo11.lain.Lain;
import com.carlgo11.lain.Messages;
import com.carlgo11.lain.Mysql;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class HeadadminCommand implements ChatCommands {

    public String getCommandName()
    {
        return "headadmin";
    }

    public void onMessage(Lain Lain, Player p, String msg, String cmd, String[] args)
    {
        Lain.checkOp(p);
        if (p.hasPermission("lain.group.headadmin") && Mysql.getRank(p.getName()).equalsIgnoreCase("headadmin")) {
            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "manuadd " + p.getName() + " builder");
            Lain.broadcastMessage(ChatColor.YELLOW + p.getName() + Messages.nolongeradmin);
        } else if (Mysql.getRank(p.getName()).equalsIgnoreCase("headadmin")) {
            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "manuadd " + p.getName() + " head-admin");
            Lain.broadcastMessage(ChatColor.YELLOW + p.getName() + Messages.nowadmin);
        } else {
            Lain.badperms(p);
        }
    }
}
