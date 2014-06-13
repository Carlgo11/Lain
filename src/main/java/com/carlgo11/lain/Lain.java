package com.carlgo11.lain;

import com.carlgo11.lain.player.chat.Phrases;
import com.carlgo11.lain.player.chat.commands.*;
import com.carlgo11.lain.commands.*;
import com.carlgo11.lain.commands.preprocess.*;
import com.carlgo11.lain.ping.*;
import com.carlgo11.lain.player.disconnect.*;
import com.carlgo11.lain.player.join.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.logging.Level;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class Lain extends JavaPlugin {

    long delay = 20;
    public static boolean debugm = false;
    public static ArrayList<String> commands = new ArrayList<String>();

    public void onEnable()
    {
        registerListeners(getServer().getPluginManager());
        commands();
        this.getLogger().log(Level.INFO, "{0} {1} is enabled!", new Object[]{getDescription().getName(), getDescription().getVersion()});
    }

    public void onDisable()
    {
        this.getLogger().log(Level.INFO, "{0} {1} is disabled!", new Object[]{getDescription().getName(), getDescription().getVersion()});
        this.saveConfig();
    }

    void registerListeners(PluginManager pm)
    {
        pm.registerEvents(new Checkfiles(this), this);
        pm.registerEvents(new InternalCommands(this), this);
        pm.registerEvents(new PerformCommand(this), this);
        pm.registerEvents(new PlayerJoin(this), this);
        pm.registerEvents(new SpecialEffects(this), this);
        pm.registerEvents(new PlayerDisconnect(this), this);
        pm.registerEvents(new consoleCommand(this), this);
        pm.registerEvents(new Phrases(this), this);
        pm.registerEvents(new ServerListPing(this), this);
        pm.registerEvents(new PlayerLogin(this), this);
    }

    public void commands()
    {
        getCommand("lain").setExecutor(new LainCommand(this));
        getCommand("setcmd").setExecutor(new SetcmdCommand(this));
        getCommand("setalias").setExecutor(new SetaliasCommand(this));
        getCommand("broadcast").setExecutor(new BroadcastCommand(this));
    }

    public void sendMessage(final Player p, final String s)
    {
        String s2s = s;
        final String s2m = ChatColor.translateAlternateColorCodes('&', s2s);
        Bukkit.getScheduler().scheduleSyncDelayedTask(this, new Runnable() {
            @Override
            public void run()
            {
                p.sendMessage(Messages.prefix + s2m);
            }
        }, delay);
    }

    public void sendMessageToSender(final CommandSender p, final String s)
    {
        String s2s = s;
        final String s2m = ChatColor.translateAlternateColorCodes('&', s2s);
        Bukkit.getScheduler().scheduleSyncDelayedTask(this, new Runnable() {
            @Override
            public void run()
            {
                p.sendMessage(Messages.prefix + s2m);
            }
        }, delay);
    }

    public void broadcastMessage(final String s)
    {
        String s2s = s;
        final String s2m = ChatColor.translateAlternateColorCodes('&', s2s);
        Bukkit.getScheduler().scheduleSyncDelayedTask(this, new Runnable() {
            @Override
            public void run()
            {
                Bukkit.broadcastMessage(Messages.prefix + s2m);
            }
        }, delay);
    }

    public void error(String s)
    {
        this.getLogger().log(Level.WARNING, Messages.error + "{0}", s);
    }

    public void error(final Player p, final String s)
    {
        Bukkit.getScheduler().scheduleSyncDelayedTask(this, new Runnable() {
            @Override
            public void run()
            {
                p.sendMessage(Messages.prefix + ChatColor.RED + Messages.error + ChatColor.RED + s);
            }
        }, delay);
    }

    public void error(final CommandSender p, final String s)
    {
        Bukkit.getScheduler().scheduleSyncDelayedTask(this, new Runnable() {
            @Override
            public void run()
            {
                p.sendMessage(Messages.prefix + ChatColor.RED + Messages.error + ChatColor.RED + s);
            }
        }, delay);
    }

    public void badperms(final Player p)
    {
        Bukkit.getScheduler().scheduleSyncDelayedTask(this, new Runnable() {
            @Override
            public void run()
            {
                p.sendMessage(Messages.prefix + Messages.badPerms);
            }
        }, delay);
    }

    public void badperms(final CommandSender p)
    {
        Bukkit.getScheduler().scheduleSyncDelayedTask(this, new Runnable() {
            @Override
            public void run()
            {
                p.sendMessage(Messages.prefix + Messages.badPerms);
            }
        }, delay);
    }

    public void toCMDLog(Player p, String s)
    {
        try {
            FileWriter outfile = new FileWriter("cmdbackup.txt", true);
            PrintWriter outi = new PrintWriter(outfile);
            outi.println(p.getName() + "");
            outi.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static public void logchatp(String tme, String p, String s)
    {
        String s2 = ChatColor.stripColor(s);
        try {
            FileWriter outfile = new FileWriter("log/chat.txt", true);
            PrintWriter outi = new PrintWriter(outfile);
            outi.println(tme + " " + p + ": " + s2);
            outi.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static public void logchat(String tme, String s)
    {
        String s2 = ChatColor.stripColor(s);
        try {
            FileWriter outfile = new FileWriter("log/chat.txt", true);
            PrintWriter outi = new PrintWriter(outfile);
            outi.println(tme + " " + ": " + s2);
            outi.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void readcommandstxt()
    {
        try {
            boolean p = new File(getDataFolder() + "/backup").mkdirs();
            File file = new File(getDataFolder() + "/names.txt");
            boolean newFile = file.createNewFile();

            if (p) {
                getLogger().info("Created a backup folder");
            }

            if (newFile) {
                getLogger().info("Created a file called names.txt");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
