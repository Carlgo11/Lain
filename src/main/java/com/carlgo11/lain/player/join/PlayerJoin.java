package com.carlgo11.lain.player.join;

import com.carlgo11.lain.Lain;
import com.carlgo11.lain.Messages;
import com.carlgo11.lain.Staff;
import java.util.UUID;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoin implements Listener {

    private final Lain plugin;

    public PlayerJoin(Lain plug)
    {
        this.plugin = plug;
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event)
    {
        Player player = event.getPlayer();
        UUID uuid = player.getUniqueId();

        if (Staff.isOp(uuid)) {
            event.getPlayer().setOp(true);
            Bukkit.broadcastMessage(ChatColor.YELLOW + event.getPlayer().getName() + Messages.nowop);
        }

        if (plugin.getConfig().getList("immortal-users").contains(uuid.toString())) {
            player.setMaxHealth(40);
        }
    }
}
