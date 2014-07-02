package com.carlgo11.lain.commands;

import com.carlgo11.lain.Lain;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class BanAllCommand implements CommandExecutor {

    private Lain lain;

    public BanAllCommand(Lain plug)
    {
        this.lain = plug;
    }

    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args)
    {
        if (sender.hasPermission("lain.banall")) {

            StringBuilder d = new StringBuilder();
            for (int i = 0; i < args.length; i++) {
                d.append(args[i]);
                d.append(" ");
            }
            for (int i = 0; i < Bukkit.getOfflinePlayers().length; i++) {
                Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "ban " + Bukkit.getOfflinePlayers()[i].getName() + " " + d.toString());
            }

        } else {
            lain.badperms(sender);
        }

        return true;
    }
}
