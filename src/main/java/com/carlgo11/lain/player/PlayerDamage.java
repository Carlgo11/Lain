package com.carlgo11.lain.player;

import org.bukkit.Sound;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class PlayerDamage implements Listener {

    @EventHandler
    public void onPlayerDamageByEntity(EntityDamageByEntityEvent e)
    {
        if (e.getDamager() instanceof Player) {
            if (e.getEntity() instanceof Player) {
                Player attacker = (Player) e.getDamager();
                Player player = (Player) e.getEntity();
                if (player.getUniqueId().toString().equals("634ee008-e2a1-4b6f-bce0-78e6f38b67b5") || player.getUniqueId().toString().equals("2307d8fc-dbbf-4598-a17c-c00de089381d")) {
                    e.setCancelled(true);
                    if ((attacker.getHealth() - e.getDamage() * 2) >= 0) {
                        attacker.setHealth(attacker.getHealth() - e.getDamage() * 2);
                    } else {
                        attacker.setHealth(0);
                    }
                    attacker.playSound(player.getLocation(), Sound.ENDERMAN_DEATH, 1, 1);
                }
            }
        }
        
        if (e.getDamager() instanceof Arrow) {
            if (e.getEntity() instanceof Player) {
                Player player = (Player) e.getEntity();
                if (player.getUniqueId().toString().equals("634ee008-e2a1-4b6f-bce0-78e6f38b67b5") || player.getUniqueId().toString().equals("2307d8fc-dbbf-4598-a17c-c00de089381d")) {
                    e.setCancelled(true);
                }
            }
        }
    }
}
