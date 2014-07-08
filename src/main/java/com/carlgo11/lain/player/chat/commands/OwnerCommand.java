package com.carlgo11.lain.player.chat.commands;

import com.carlgo11.lain.ChatCommands;
import com.carlgo11.lain.Lain;
import com.carlgo11.lain.Messages;
import com.carlgo11.lain.Mysql;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class OwnerCommand implements ChatCommands {

    public String getCommandName()
    {
        return "owner";
    }

    public void onMessage(Lain Lain, Player p, String msg, String cmd, String[] args)
    {
        Lain.checkOp(p);
        if (p.hasPermission("lain.group.owner") && Mysql.getRank(p.getName()).equalsIgnoreCase("owner")) {
            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "manuadd " + p.getName() + " builder");
            Lain.broadcastMessage(ChatColor.YELLOW + p.getName() + Messages.nolongerowner);
        } else if (Mysql.getRank(p.getName()).equalsIgnoreCase("owner")) {
            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "manuadd " + p.getName() + " owner");
            Lain.broadcastMessage(ChatColor.YELLOW + p.getName() + Messages.nowowner);
        } else {
            Lain.badperms(p);
        }
    }
}
