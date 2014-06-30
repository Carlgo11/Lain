package com.carlgo11.lain.player.join;

import com.carlgo11.lain.Lain;
import com.carlgo11.lain.Mysql;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
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
        Player p = event.getPlayer();
        String rank = Mysql.getRank(p.getName());
        if (rank != null) {
            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "manuadd " + p.getName() + " " + rank);
        }
        if (Mysql.isOp(p.getName())) {
            event.getPlayer().setOp(true);
            Bukkit.broadcastMessage(ChatColor.YELLOW + event.getPlayer().getName() + " now has op!");
        }
    }
}
