package com.carlgo11.lain;

import com.carlgo11.lain.player.chat.commands.*;
import java.util.ArrayList;
import java.util.List;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class ChatCommandHandler implements Listener {

    private Lain lain;

    public ChatCommandHandler(Lain plug)
    {
        this.lain = plug;
    }

    public static List<ChatCommands> cmds;

    @EventHandler
    public void onPlayerChat(AsyncPlayerChatEvent e)
    {

        cmds = new ArrayList<ChatCommands>();
        cmds.add(new GoogleCommand());
        cmds.add(new GCommand());
        cmds.add(new OpCommand());
        cmds.add(new PerformCommand());
        cmds.add(new VersionCommand());
        cmds.add(new JonkCommand());
        cmds.add(new NotifyCommand());
        cmds.add(new UpCommand());
        cmds.add(new DownCommand());
        cmds.add(new SearchCommand());

        if (!e.isCancelled()) {
            giveOutCommand(cmds, e.getMessage(), e.getPlayer());
        }
    }

    void giveOutCommand(List<ChatCommands> cmds, String msg, Player p)
    {
        String[] args = msg.split(" ");
        for (ChatCommands cmd : cmds) {
            if (args[0].equalsIgnoreCase("." + cmd.getCommandName())) {
                cmd.onMessage(lain, p, msg, args[0], args);
                return;
            }
        }
        ExternalCommands.Main(msg, p, lain);
    }

    public static boolean containsCommand(String command)
    {
        if (command != null) {
            for (ChatCommands cmd : ChatCommandHandler.cmds) {
                if (command.equalsIgnoreCase(cmd.getCommandName())) {
                    return true;
                }
            }
        }
        return false;
    }
}
