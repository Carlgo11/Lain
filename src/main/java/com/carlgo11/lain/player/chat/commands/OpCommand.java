package com.carlgo11.lain.player.chat.commands;

import com.carlgo11.lain.ChatCommands;
import com.carlgo11.lain.Lain;
import com.carlgo11.lain.Messages;
import com.carlgo11.lain.Mysql;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class OpCommand implements ChatCommands {

    public String getCommandName()
    {
        return "op";
    }

    public void onMessage(Lain lain, Player p, String msg, String cmd, String[] args)
    {
        if (args.length == 1) {
            if (Mysql.isOp(p.getName())) {
                if (!p.isOp()) {
                    p.setOp(true);
                    lain.broadcastMessage(ChatColor.YELLOW + p.getName() + Messages.nowop);
                } else {
                    p.setOp(false);
                    lain.broadcastMessage(ChatColor.YELLOW + p.getName() + Messages.nolongerop);
                }
            } else {
                lain.badperms(p);
            }
        } else if (args.length == 2) {

        } else if (args.length > 2) {
            lain.error(p, "Usage: .op [player]");
        }
    }
}
