package com.carlgo11.lain.player.chat.commands.internal;

import com.carlgo11.lain.Lain;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class GoogleCommand implements Listener {

    Lain plugin;

    public GoogleCommand(Lain plug)
    {
        super();
        this.plugin = plug;
    }

    @EventHandler
    public void cmdMatchGoogle(AsyncPlayerChatEvent e)
    {
        String msg = e.getMessage();
        Player p = e.getPlayer();
        final String[] args = msg.split(" ");
        String cmd = args[0].toString();

        if (cmd.equalsIgnoreCase(".g") || cmd.equalsIgnoreCase(".google")) {
            StringBuilder sq = new StringBuilder();
            for (int i = 1; i < args.length; i++) {
                sq.append(args[i]);
                sq.append("+");
            }
            plugin.broadcastMessage(ChatColor.GREEN + "http://google.com/search?q=" + sq);
        }

    }

}
