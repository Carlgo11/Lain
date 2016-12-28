package com.carlgo11.lain.player.dotcommands;

import com.carlgo11.lain.ChatCommands;
import com.carlgo11.lain.Lain;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class VersionCommand implements ChatCommands {

    public String getCommandName()
    {
        return "version";
    }

    public void onMessage(Lain Lain, Player p, String msg, String cmd, String[] args)
    {
        if (p.hasPermission("lain.dotcommand.version")) {
            Lain.broadcastMessage(ChatColor.YELLOW + "Lain v" + Lain.getDescription().getVersion() + " developed by " + "Carlgo11");
        } else {
            Lain.badperms(p);
        }
    }
}
