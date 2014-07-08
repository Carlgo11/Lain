package com.carlgo11.lain.player.chat.commands;

import com.carlgo11.lain.ChatCommands;
import com.carlgo11.lain.Lain;
import com.carlgo11.lain.Mysql;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class DownCommand implements ChatCommands {

    public String getCommandName()
    {
        return "down";
    }

    public void onMessage(Lain Lain, Player p, String msg, String cmd, String[] args)
    {
        String rank = Mysql.getRank(p.getName());
        if (rank != null) {
            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "manuadd " + p.getName() + " builder");
            Lain.broadcastMessage(ChatColor.YELLOW + p.getName() + " is no longer " + rank + "!");
        } else {
            Lain.badperms(p);
        }
    }
}
