package com.carlgo11.lain.player.dotcommands;

import com.carlgo11.lain.ChatCommands;
import com.carlgo11.lain.Lain;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class GoogleCommand implements ChatCommands {

    public String getCommandName()
    {
        return "google";
    }

    public void onMessage(Lain Lain, Player p, String msg, String cmd, String[] args)
    {
        if (args.length > 1) {
            StringBuilder sq = new StringBuilder();
            for (int i = 1; i < args.length; i++) {
                sq.append(args[i]);
                sq.append("+");
            }
            Lain.broadcastMessage(ChatColor.GREEN + "https://google.com/search?q=" + sq);
        } else {
            Lain.error(p, "Usage: .g [search query]");
        }
    }
}
