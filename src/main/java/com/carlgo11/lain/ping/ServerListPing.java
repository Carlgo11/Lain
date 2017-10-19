package com.carlgo11.lain.ping;

import com.carlgo11.lain.Lain;
import com.carlgo11.lain.Mysql;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.server.ServerListPingEvent;

/**
 * Set server MOTD.
 *
 * @since 2.0
 */
public class ServerListPing implements Listener {

    Lain plugin;

    public ServerListPing(Lain plug)
    {
        super();
        this.plugin = plug;
    }

    @EventHandler
    public void onServerListPing(ServerListPingEvent e)
    {
        String plainMessage = Mysql.getMOTD();
        if (plainMessage.contains("&")) {
            e.setMotd(ChatColor.translateAlternateColorCodes('&', plainMessage));
        } else {
            e.setMotd(plainMessage);
        }
    }
}
