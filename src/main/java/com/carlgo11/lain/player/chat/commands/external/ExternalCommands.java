package com.carlgo11.lain.player.chat.commands.external;

import com.carlgo11.lain.Lain;
import java.util.regex.Pattern;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class ExternalCommands implements Listener {

    Lain plugin;

    public ExternalCommands(Lain plug)
    {
        super();
        this.plugin = plug;
    }

    @EventHandler
    public void onCMD(AsyncPlayerChatEvent e)
    {
        String msg = e.getMessage();
        Player p = e.getPlayer();
        final String[] args = msg.split(" ");
        String argsnodot = args[0].replaceFirst(".", "");
        String cmdbuild = "cmd-" + argsnodot + "-end";
        String aliasbuild = "alias-" + argsnodot + "-end";
        String cmdprefix = ".";
        if (args[0].startsWith(".")) {
            if (!args[0].equalsIgnoreCase(cmdprefix + Pattern.compile("uptime|op|version") + "")) {
                if (args.length == 1) {
                    if (plugin.getConfig().contains(cmdbuild)) {
                        plugin.broadcastMessage(plugin.getConfig().getString(cmdbuild));
                    } else {
                        if (plugin.getConfig().contains(aliasbuild)) {
                            if (plugin.getConfig().contains(plugin.getConfig().getString(aliasbuild))) {
                                plugin.broadcastMessage(plugin.getConfig().getString(plugin.getConfig().getString(aliasbuild))); // INCEPTION! :O
                            }
                        }
                    }

                } else if (args.length > 1) {
                    if (Bukkit.getOfflinePlayer(args[1]).isOnline()) {
                        if (plugin.getConfig().contains(cmdbuild)) {
                            plugin.broadcastMessage(ChatColor.DARK_AQUA + args[1] + ": " + plugin.getConfig().getString(cmdbuild));
                            Bukkit.getPlayer(args[1]).getWorld().playSound(Bukkit.getPlayer(args[1]).getLocation(), Sound.ORB_PICKUP, 1, 0);
                        } else {
                            if (plugin.getConfig().contains(aliasbuild)) {
                                if (plugin.getConfig().contains(plugin.getConfig().getString(aliasbuild))) {
                                    plugin.broadcastMessage(ChatColor.DARK_AQUA + args[1] + ": " + plugin.getConfig().getString(plugin.getConfig().getString(aliasbuild)));
                                    Bukkit.getPlayer(args[1]).getWorld().playSound(Bukkit.getPlayer(args[1]).getLocation(), Sound.ORB_PICKUP, 1, 0);
                                }
                            }
                        }

                    } else {
                        if (plugin.getConfig().contains(cmdbuild)) {
                            if (plugin.getConfig().contains(cmdbuild)) {
                                plugin.broadcastMessage(plugin.getConfig().getString(cmdbuild));
                            } else {
                                if (plugin.getConfig().contains(aliasbuild)) {
                                    if (plugin.getConfig().contains(plugin.getConfig().getString(aliasbuild))) {
                                        plugin.broadcastMessage(plugin.getConfig().getString(plugin.getConfig().getString(aliasbuild))); // INCEPTION! :O
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
