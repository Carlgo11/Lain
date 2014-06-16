package com.carlgo11.lain.player.chat.commands;

import com.carlgo11.lain.ChatCommands;
import com.carlgo11.lain.Lain;
import com.carlgo11.lain.Messages;
import com.carlgo11.lain.Mysql;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class ModCommand implements ChatCommands{

    public String getCommandName()
    {
        return "mod";
    }

    public void onMessage(Lain Lain, Player p, String msg, String cmd, String[] args)
    {
       Lain.checkOp(p);
        if (p.hasPermission("Lain.group.mod") && Mysql.getRank(p.getName()).equalsIgnoreCase("moderator")) {
            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "manuadd " + p.getName() + " builder");
            Lain.broadcastMessage(ChatColor.YELLOW + p.getName() + Messages.nolongermod);
        } else if (Mysql.getRank(p.getName()).equalsIgnoreCase("moderator")) {
            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "manuadd " + p.getName() + " moderator");
            Lain.broadcastMessage(ChatColor.YELLOW + p.getName() + Messages.nowmod);
        } else {
            Lain.badperms(p);
        }
    }

}
