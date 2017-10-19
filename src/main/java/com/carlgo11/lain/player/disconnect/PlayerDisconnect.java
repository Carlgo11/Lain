package com.carlgo11.lain.player.disconnect;

import com.carlgo11.lain.Lain;
import com.carlgo11.lain.Messages;
import com.carlgo11.lain.Staff;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerDisconnect implements Listener {

    private final Lain plugin;

    public PlayerDisconnect(Lain plug)
    {
        this.plugin = plug;
    }

    static public boolean reboot = false;
    static public boolean notify = false;

    @EventHandler
    public void onPlayerDisconnect(PlayerQuitEvent e)
    {
        Player player = e.getPlayer();
        if (player.isOp()) {
            if (Staff.isOp(player.getUniqueId())) {
                player.setOp(false);
                plugin.broadcastMessage(ChatColor.YELLOW + player.getName() + Messages.nolongerop);
            }
        }
    }
}
