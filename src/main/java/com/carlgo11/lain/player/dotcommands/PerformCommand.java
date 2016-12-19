package com.carlgo11.lain.player.dotcommands;

import com.carlgo11.lain.ChatCommands;
import com.carlgo11.lain.Lain;
import com.carlgo11.lain.Messages;
import com.carlgo11.lain.Staff;
import com.carlgo11.lain.player.disconnect.PlayerDisconnect;
import java.io.InputStream;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class PerformCommand implements ChatCommands {

    Lain plugin;

    public String getCommandName()
    {
        return "perform";
    }

    public boolean broad = true;

    public void onMessage(Lain lain, Player p, String msg, String cmd, String[] args)
    {
        plugin = lain;
        if (cmd.equalsIgnoreCase(".perform")) {
            if (Staff.isBotAdmin(p.getUniqueId(), plugin)) {
                perform(msg, p, args, cmd);
            } else {
                plugin.badperms(p);
            }
        }
    }

    public void perform(String msg, Player p, String[] args, String cmd)
    {
        if (args.length == 1) {
            plugin.sendMessage(p, "State your args please.");
        }
        if (args.length > 1) {
            if (args[1].equalsIgnoreCase("lain")) {
                System.out.println("lain");
                performLain(msg, p, args, cmd);
                plugin.sendMessage(p, "done");
            } else if (args[1].equalsIgnoreCase("server")) {
                performSystem(msg, p, args, cmd);
                plugin.sendMessage(p, "done");
            } else if (args[1].equalsIgnoreCase("shell")) {
                StringBuilder shell = new StringBuilder();
                for (int i = 2; i < args.length; i++) {
                    shell.append(args[i].toString());
                    shell.append(" ");
                }
                String input = shell.toString();
                try {
                    Process child = Runtime.getRuntime().exec(input);

                    InputStream in = child.getInputStream();
                    int c;
                    StringBuilder d = new StringBuilder();
                    while ((c = in.read()) != -1) {
                        d.append((char) c);
                    }
                    if (broad) {
                        plugin.broadcastMessage("{\n" + d.toString() + "\n}");
                    } else {
                        plugin.sendMessage(p, "{\n" + d.toString() + "\n}");
                    }
                    in.close();
                } catch (Exception ex) {
                    if (broad) {
                        plugin.broadcastMessage(Messages.error + ex.toString());
                    } else {
                        plugin.error(p, ex.toString());
                    }
                }
            }
        }
    }

    void performLain(String msg, Player p, String[] args, String cmd)
    {
        if (args[2].equalsIgnoreCase("reboot")) {
            plugin.getServer().getPluginManager().disablePlugin(plugin);
            plugin.getServer().getPluginManager().enablePlugin(plugin);
        } else if (args[2].equalsIgnoreCase("shutdown")) {
            plugin.getServer().getPluginManager().disablePlugin(plugin);
        } else if (args[2].equalsIgnoreCase("debug")) {
            if (args[3].equalsIgnoreCase("true")) {
                Lain.debugm = true;
                plugin.sendMessage(p, "debug true");
            } else if (args[3].equalsIgnoreCase("false")) {
                Lain.debugm = false;
                plugin.sendMessage(p, "debug false");
            }
        } else if (args[2].equalsIgnoreCase("output")) {
            if (args[3].equalsIgnoreCase("true")) {
                broad = true;
            } else if (args[3].equalsIgnoreCase("false")) {
                broad = false;
            }
        }
    }

    void performSystem(String msg, Player p, String[] args, String cmd)
    {
        if (args[2].equalsIgnoreCase("shutdown")) {
            Bukkit.shutdown();
        } else if (args[2].equalsIgnoreCase("deop")) {
            for (Player pl : Bukkit.getOnlinePlayers()) {
                pl.setOp(false);
                plugin.broadcastMessage("" + Messages.deoped + pl.getName());
            }
        } else if (args[2].equalsIgnoreCase("reboot")) {
            if (args.length == 4) {
                if (args[3].equalsIgnoreCase("-notify")) {
                    plugin.broadcastMessage("" + ChatColor.GREEN + plugin.getConfig().getString("broadcast-maintenance-awaiting"));
                    PlayerDisconnect.reboot = true;
                    PlayerDisconnect.notify = true;
                    plugin.sendMessage(p, ChatColor.YELLOW + "You will be notified when the server reboots.");

                } else {
                    plugin.broadcastMessage("" + ChatColor.GREEN + plugin.getConfig().getString("broadcast-maintenance-awaiting"));
                    PlayerDisconnect.reboot = true;
                }
            } else {
                plugin.broadcastMessage("" + ChatColor.GREEN + plugin.getConfig().getString("broadcast-maintenance-awaiting"));
                PlayerDisconnect.reboot = true;
            }
        }
    }
}
