package com.carlgo11.lain.commands;

import com.carlgo11.lain.Lain;
import com.carlgo11.lain.Messages;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class LainCommand implements CommandExecutor {

    private Lain lain;

    public LainCommand(Lain plug)
    {
        this.lain = plug;
    }

    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args)
    {
        if (args.length == 0) {
            sender.sendMessage("Why do you use this command? use /stop instead! :D");
        } else {
            if (args[0].equalsIgnoreCase("say")) {
                say(sender, cmd, commandLabel, args);
            } else if (args[0].equalsIgnoreCase("moo")) {

            } else {

            }
        }
        return true;
    }

    public void say(CommandSender sender, Command cmd, String commandLabel, String[] args)
    {
        if (sender.hasPermission("lain.cmd.lain.say")) {
            if (args.length == 1) {
                lain.sendMessageToSender(sender, Messages.usagelainsay);
            } else {
                StringBuilder knark = new StringBuilder();
                for (int i = 1; i < args.length; i++) {
                    knark.append(args[i]).append(" ");
                }
                lain.broadcastMessage(ChatColor.LIGHT_PURPLE + knark.toString());
            }
        } else {
            lain.badperms(sender);
        }
    }
}
