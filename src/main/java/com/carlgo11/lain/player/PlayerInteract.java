package com.carlgo11.lain.player;

import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class PlayerInteract implements Listener {

    @EventHandler(priority = EventPriority.HIGH)
    public void onPlayerUse(PlayerInteractEvent event)
    {
        Player p = event.getPlayer();

        if (p.getItemInHand().getType() == Material.SUGAR) {
            p.getWorld().playSound(p.getLocation(), Sound.CAT_MEOW, 1, 1);
            p.addPotionEffect(new PotionEffect(PotionEffectType.CONFUSION, 1200, 20));
            p.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 1200, 20));
            p.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, 1200, 20));
            p.addPotionEffect(new PotionEffect(PotionEffectType.FAST_DIGGING, 1200, 20));
            p.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 1260, 20));
            if (p.getItemInHand().getAmount() > 1) {
                p.getItemInHand().setAmount(p.getItemInHand().getAmount() - 1);
            } else {
                p.getInventory().setItemInHand(new ItemStack(Material.AIR));
            }
        }
    }

}
