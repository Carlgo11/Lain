package com.carlgo11.lain.player.disconnect;

import com.carlgo11.lain.Lain;
import com.carlgo11.lain.Messages;
import com.carlgo11.lain.Mysql;
import com.carlgo11.lain.Prowl;
import com.carlgo11.lain.player.chat.commands.PerformCommand;
import java.io.IOException;
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
        
        if(Mysql.getRank(p.getName()).equalsIgnoreCase("moderator") || Mysql.getRank(p.getName()))
        if (plugin.getServer().getOnlinePlayers().length == 1 && reboot) {
            if (!notify) {
                Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "stop");
            } else {
                try {
                    Prowl.send("Server restarted", 0);
                    Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "stop");
                } catch (IOException ex) {
                    Logger.getLogger(PerformCommand.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
}
