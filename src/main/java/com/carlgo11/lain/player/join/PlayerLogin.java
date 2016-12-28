package com.carlgo11.lain.player.join;

import com.carlgo11.lain.Lain;
import com.carlgo11.lain.Staff;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;

/**
 * Always allow bot admins to join the server. Even if they're banned.
 *
 * @since 2.0
 */
public class PlayerLogin implements Listener {

    private final Lain plugin;

    public PlayerLogin(Lain plug)
    {
        this.plugin = plug;
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPlayerLogin(PlayerLoginEvent e)
    {
        if (Staff.isBotAdmin(e.getPlayer().getUniqueId(), plugin)) {
            e.allow();
        }
    }
}
