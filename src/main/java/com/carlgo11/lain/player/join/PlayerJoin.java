package com.carlgo11.lain.player.join;

import com.carlgo11.lain.Lain;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoin implements Listener {

    private Lain plugin;

    public PlayerJoin(Lain plug)
    {
        this.plugin = plug;
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event)
    {
        int a = 0;
        int b = 1;
        for (int i = 1; a != b; i++) {
            if (plugin.getConfig().contains("owner-op" + i)) {
                if (plugin.getConfig().getString("owner-op" + i).equalsIgnoreCase(event.getPlayer().getName())) {
                    event.getPlayer().setOp(true);
                    Bukkit.broadcastMessage(ChatColor.YELLOW + event.getPlayer().getName() + " now has op!");
                }
            } else {
                a++;
            }
        }
    }
}
