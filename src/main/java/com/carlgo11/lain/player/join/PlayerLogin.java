package com.carlgo11.lain.player.join;

import com.carlgo11.lain.Lain;
import com.carlgo11.lain.Mysql;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;

public class PlayerLogin implements Listener {

    private Lain plugin;

    public PlayerLogin(Lain plug)
    {
        this.plugin = plug;
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPlayerLogin(PlayerLoginEvent e)
    {
        if (Bukkit.hasWhitelist()) {
            if (Mysql.getRank(e.getPlayer().getName()) != null) {
                e.allow();
            }
        }
    }
}