package com.carlgo11.lain.player;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class PlayerInteract implements Listener {

    @EventHandler(priority = EventPriority.HIGH)
    public void onPlayerUse(PlayerInteractEvent event)
    {
        Player p = event.getPlayer();

        if (p.getItemInHand().getType() == Material.SUGAR) {
            p.getWorld().playSound(p.getLocation(), Sound.CAT_MEOW, 1, 1);
            p.addPotionEffect(new PotionEffect(PotionEffectType.CONFUSION, 2000, 20));
            p.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 2000, 20));
            p.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, 2000, 20));
            p.addPotionEffect(new PotionEffect(PotionEffectType.FAST_DIGGING, 2000, 20));
            p.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 2060, 20));
            p.getItemInHand().setAmount(p.getItemInHand().getAmount() - 1);
            Bukkit.broadcastMessage(ChatColor.GREEN + p.getName() + " is now a cat on drugs");
        }
    }

}
