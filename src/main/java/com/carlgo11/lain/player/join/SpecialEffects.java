package com.carlgo11.lain.player.join;

import com.carlgo11.lain.Lain;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class SpecialEffects implements Listener {

    private final Lain plugin;

    public SpecialEffects(Lain plug)
    {
        this.plugin = plug;
    }

    @EventHandler(priority = EventPriority.LOW)
    public void onPlayerJoin(PlayerJoinEvent event)
    {
        Player p = event.getPlayer();
        String uuid = p.getUniqueId().toString();
        if (plugin.getConfig().getList("immortal-users").contains(uuid)) {
            p.setMaxHealth(40);
        }
    }
}
