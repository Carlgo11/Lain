package com.carlgo11.lain.player;

import com.carlgo11.lain.Lain;
import java.util.UUID;
import org.bukkit.Sound;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class PlayerDamage implements Listener {

    Lain plugin;

    public PlayerDamage(Lain plug)
    {
        super();
        this.plugin = plug;
    }

    @EventHandler
    public void onPlayerDamageByEntity(EntityDamageByEntityEvent e)
    {
        if (e.getDamager() instanceof Player) {
            if (e.getEntity() instanceof Player) {
                Player attacker = (Player) e.getDamager();
                Player player = (Player) e.getEntity();
                UUID uuid = player.getUniqueId();
                if (plugin.getConfig().getList("immortal-users").contains(uuid)) {
                    if (!attacker.hasPermission("lain.immortal.ignore")) {
                        e.setCancelled(true);
                        if ((attacker.getHealth() - e.getDamage() * 2) >= 0) {
                            attacker.setHealth(attacker.getHealth() - e.getDamage() * 2);
                        } else {
                            attacker.setHealth(0);
                        }
                        attacker.playSound(player.getLocation(), Sound.ENTITY_ENDERMEN_DEATH, 1, 1);
                    }
                }
            }
        }

        if (e.getDamager() instanceof Arrow) {
            UUID uuid = e.getEntity().getUniqueId();
            Arrow arrow = (Arrow) e.getDamager();
            if (arrow.getShooter() instanceof Player && e.getEntity() instanceof Player) {
                if (plugin.getConfig().getList("immortal-users").contains(uuid));
                e.setCancelled(true);
            }
        }
    }
}
