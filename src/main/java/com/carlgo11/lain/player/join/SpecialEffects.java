package com.carlgo11.lain.player.join;

import com.carlgo11.lain.Lain;
import org.bukkit.Achievement;
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
        if (pn.equals("carlgo11") || pn.equals("jonkanon03")) {
            p.setMaxHealth(40);
        }
    }
}
