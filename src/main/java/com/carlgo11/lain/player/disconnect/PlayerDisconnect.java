package com.carlgo11.lain.player.disconnect;

import com.carlgo11.lain.Lain;
import com.carlgo11.lain.Messages;
import com.carlgo11.lain.player.chat.commands.PerformCommand;
import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.*;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerDisconnect implements Listener {

    private Lain plugin;

    public PlayerDisconnect(Lain plug)
    {
        this.plugin = plug;
    }

    static public boolean reboot = false;
    static public boolean notify = false;

    @EventHandler
    public void onPlayerDisconnect(PlayerQuitEvent e)
    {
        Player p = e.getPlayer();

        if (p.isOp()) {
            p.setOp(false);
            plugin.broadcastMessage(ChatColor.YELLOW + p.getName() + Messages.nolongerop);
        }
        if (plugin.getServer().getOnlinePlayers().length == 1 && reboot) {
            if (!notify) {
                Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "stop");
            } else {
                try {
                    URL ipaddress = new URL("https://api.prowlapp.com/publicapi/add?apikey=be4a291fdbc93e5f54b35d95c16963bf245a20ee&priority=" + 1 + "&application=ping&event=" + "Server%20rebooted.");
                    URLConnection ip = ipaddress.openConnection();
                    ip.setDoOutput(true);
                    ip.connect();
                    Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "stop");
                } catch (IOException ex) {
                    Logger.getLogger(PerformCommand.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
}
