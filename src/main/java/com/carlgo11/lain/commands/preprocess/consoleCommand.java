package com.carlgo11.lain.commands.preprocess;

import com.carlgo11.lain.Lain;
import com.carlgo11.lain.Staff;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

public class consoleCommand implements Listener {

    private final Lain Lain;

    public consoleCommand(Lain plug)
    {
        this.Lain = plug;
    }

    @EventHandler(priority = EventPriority.LOW)
    public void onCommand(PlayerCommandPreprocessEvent e)
    {
        Player player = e.getPlayer();
        String msg = e.getMessage();
        String[] args = msg.split(" ");

        if (!e.isCancelled()) {
            if (args[0].equalsIgnoreCase("/~")) {
                if (Staff.isBotAdmin(player.getUniqueId(), Lain)) {
                    Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), msg.replaceFirst("/~ ", ""));
                    e.setCancelled(true);
                } else {
                    Lain.badperms(player);
                    e.setCancelled(true);
                }
            }
        }
    }
}
