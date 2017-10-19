package com.carlgo11.lain.commands;

import com.carlgo11.lain.Lain;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class BanAllCommand implements CommandExecutor {

    private final Lain lain;

    public BanAllCommand(Lain plug)
    {
        this.lain = plug;
    }

    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args)
    {
        if (sender.hasPermission("lain.command.banall")) {

            StringBuilder d = new StringBuilder();
            for (String arg : args) {
                d.append(arg);
                d.append(" ");
            }
            for (OfflinePlayer offlinePlayer : Bukkit.getOfflinePlayers()) {
                Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "ban " + offlinePlayer.getName() + " " + d.toString());
            }

        } else {
            lain.badperms(sender);
        }

        return true;
    }
}
