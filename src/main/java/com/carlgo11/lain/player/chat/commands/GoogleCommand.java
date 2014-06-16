package com.carlgo11.lain.player.chat.commands;

import com.carlgo11.lain.ChatCommands;
import com.carlgo11.lain.Lain;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class GoogleCommand implements ChatCommands{

    public String getCommandName()
    {
        return "g";
    }

    public void onMessage(Lain Lain, Player p, String msg, String cmd, String[] args)
    {
        StringBuilder sq = new StringBuilder();
        for (int i = 1; i < args.length; i++) {
            sq.append(args[i]);
            sq.append("+");
        }
        Lain.broadcastMessage(ChatColor.GREEN + "http://google.com/search?q=" + sq);
    }

}
