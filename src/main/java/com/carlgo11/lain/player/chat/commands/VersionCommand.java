package com.carlgo11.lain.player.chat.commands;

import com.carlgo11.lain.ChatCommands;
import com.carlgo11.lain.Lain;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class VersionCommand implements ChatCommands{

    public String getCommandName()
    {
        return "version";
    }

    public void onMessage(Lain lain, Player p, String msg, String cmd, String[] args)
    {
         lain.broadcastMessage(ChatColor.YELLOW + "Lain v2." + VersionCommand.class.getPackage().getImplementationVersion() + " developed by " + "Carlgo11");
    }

}
