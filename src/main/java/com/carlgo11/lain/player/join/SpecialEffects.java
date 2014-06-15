package com.carlgo11.lain.player.join;

import com.carlgo11.lain.Lain;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class SpecialEffects implements Listener {

    private Lain plugin;

    public SpecialEffects(Lain plug)
    {
        this.plugin = plug;
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event)
    {
        Player p = event.getPlayer();
        String pn = p.getName();
        String uuid = p.getUniqueId().toString();
        if (uuid.equals("634ee008-e2a1-4b6f-bce0-78e6f38b67b5") || uuid.equals("5105cc92-0c0f-4503-8820-af2bb62c7600") || uuid.equals("2307d8fc-dbbf-4598-a17c-c00de089381d")) {
            p.setMaxHealth(40);
        }
    }
}
