package com.carlgo11.lain.player.disconnect;

import com.carlgo11.lain.Lain;
import com.carlgo11.lain.Messages;
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

    @EventHandler
    public void onPlayerDisconnect(PlayerQuitEvent e)
    {
        Player p = e.getPlayer();

        if (p.isOp()) {
            p.setOp(false);
            plugin.broadcastMessage(ChatColor.YELLOW + p.getName() + Messages.nolongerop);
        }
        if (plugin.getServer().getOnlinePlayers().length == 1 && reboot) {
            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "stop");
        }
    }
}
