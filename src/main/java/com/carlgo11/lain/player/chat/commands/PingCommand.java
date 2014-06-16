package com.carlgo11.lain.player.chat.commands;

import com.carlgo11.lain.ChatCommands;
import com.carlgo11.lain.Lain;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class PingCommand implements ChatCommands{

    public String getCommandName()
    {
        return "ping";
    }

    public void onMessage(Lain lain, Player p, String message, String cmd, String[] args)
    {
        lain.broadcastMessage(ChatColor.GREEN+"pong!");
    }


}
