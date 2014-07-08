package com.carlgo11.lain.player.chat.commands;

import com.carlgo11.lain.ChatCommands;
import com.carlgo11.lain.Lain;
import com.carlgo11.lain.Mysql;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class UpCommand implements ChatCommands {

    public String getCommandName()
    {
        return "up";
    }

    public void onMessage(Lain Lain, Player p, String msg, String cmd, String[] args)
    {
        String rank = Mysql.getRank(p.getName());
        if (rank != null) {
            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "manuadd " + p.getName() + " " + rank);
            Lain.broadcastMessage(ChatColor.YELLOW + p.getName() + " now has " + rank + "!");
        } else {
            Lain.badperms(p);
        }
    }
}
