package com.carlgo11.lain.player.chat.commands.internal;

import com.carlgo11.lain.API;
import com.carlgo11.lain.Lain;
import com.carlgo11.lain.Messages;
import com.carlgo11.lain.player.disconnect.PlayerDisconnect;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class PerformCommand implements Listener {

    Lain plugin;

    public PerformCommand(Lain plug)
    {
        super();
        this.plugin = plug;
    }
    public boolean broad = true;

    @EventHandler
    public void onCMD(AsyncPlayerChatEvent e)
    {
        String msg = e.getMessage();
        Player p = e.getPlayer();
        final String[] args = msg.split(" ");
        String cmd = args[0].toString();

        if (cmd.equalsIgnoreCase(".perform")) {
            if (API.isAdmin(p, plugin)) {
                perform(msg, p, args, cmd);
            } else {
                plugin.badperms(p);
                e.setCancelled(true);
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
            plugin.sendMessage(p, "done");
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
                plugin.sendMessage(p, "done");
            }
        } else if (args[2].equalsIgnoreCase("reboot")) {
            plugin.broadcastMessage("" + ChatColor.GREEN + plugin.getConfig().getString("broadcast-maintenance-awaiting"));
            PlayerDisconnect.reboot = true;
        }
    }

    private String performShell(String s)
    {

        StringBuffer output = new StringBuffer();

        Process p;
        try {
            p = Runtime.getRuntime().exec(s);
            p.waitFor();
            BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()));

            String line = "";
            while ((line = reader.readLine()) != null) {
                output.append(line + "\n");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return output.toString();
    }
}
