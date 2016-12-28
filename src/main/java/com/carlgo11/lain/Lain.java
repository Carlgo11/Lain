package com.carlgo11.lain;

import com.carlgo11.lain.player.chat.Phrases;
import com.carlgo11.lain.commands.*;
import com.carlgo11.lain.commands.preprocess.*;
import com.carlgo11.lain.ping.*;
import com.carlgo11.lain.player.*;
import com.carlgo11.lain.player.disconnect.*;
import com.carlgo11.lain.player.join.*;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * Main class of the 'Lain' plugin. Common functions can be found here as well
 * as plugin-essential functions.
 */
public class Lain extends JavaPlugin {

    long delay = 20;
    public static boolean debugm = false;
    public static ArrayList<String> commands = new ArrayList<String>();

    /**
     * Called upon server-start. Loads plugin-essential resources upon server
     * start.
     */
    @Override
    public void onEnable()
    {
        loadConfig();
        registerListeners(getServer().getPluginManager());
        commands();
        DotCommands dc = new DotCommands();
        dc.main(getConfig().getString("mysql.url"), getConfig().getString("mysql.username"), getConfig().getString("mysql.password"), getConfig().getString("mysql.ext-table"), getConfig().getString("mysql.database"));
        Mysql.updateStrings(getConfig().getString("mysql.url"), getConfig().getString("mysql.options"), getConfig().getString("mysql.username"), getConfig().getString("mysql.password"), getConfig().getString("mysql.database"), getConfig().getString("mysql.rank-table"), getConfig().getString("mysql.motd-table"));
        Mysql.createTables();
        this.getLogger().log(Level.INFO, "{0} {1} is enabled!", new Object[]{getDescription().getName(), getDescription().getVersion()});
    }

    /**
     * Called upon server-shutdown.
     */
    @Override
    public void onDisable()
    {
        this.getLogger().log(Level.INFO, "{0} {1} is disabled!", new Object[]{getDescription().getName(), getDescription().getVersion()});
    }

    void registerListeners(PluginManager pm)
    {
        pm.registerEvents(new ChatCommandHandler(this), this);
        pm.registerEvents(new PlayerJoin(this), this);
        pm.registerEvents(new PlayerDisconnect(this), this);
        pm.registerEvents(new consoleCommand(this), this);
        pm.registerEvents(new Phrases(this), this);
        pm.registerEvents(new ServerListPing(this), this);
        pm.registerEvents(new PlayerLogin(this), this);
        pm.registerEvents(new PlayerDamage(this), this);
    }

    /**
     * Calls classes used for commands.
     *
     * @since 2.0
     */
    public void commands()
    {
        getCommand("lain").setExecutor(new LainCommand(this));
        getCommand("setcmd").setExecutor(new SetcmdCommand(this));
        getCommand("setalias").setExecutor(new SetaliasCommand(this));
        getCommand("motd").setExecutor(new MotdCommand(this));
        getCommand("banall").setExecutor(new BanAllCommand(this));
    }

    /**
     * Send a message to a player.
     *
     * @param player Player to send message to.
     * @param message Message to send to the player.
     * @since 2.0
     */
    public void sendMessage(final Player player, final String message)
    {
        String s2s = message;
        final String s2m = ChatColor.translateAlternateColorCodes('&', s2s);
        Bukkit.getScheduler().scheduleSyncDelayedTask(this, new Runnable() {
            @Override
            public void run()
            {
                player.sendMessage(Messages.prefix + s2m);
            }
        }, delay);
    }

    /**
     * Send a message to a player.
     *
     * @param player Player to send message to.
     * @param message Message to send to the player.
     * @since 2.0
     */
    public void sendMessage(final CommandSender player, final String message)
    {
        String s2s = message;
        final String s2m = ChatColor.translateAlternateColorCodes('&', s2s);
        Bukkit.getScheduler().scheduleSyncDelayedTask(this, new Runnable() {
            @Override
            public void run()
            {
                player.sendMessage(Messages.prefix + s2m);
            }
        }, delay);
    }

    /**
     * Broadcast a message to the entire server.
     *
     * @param message Message to broadcast.
     * @since 2.0
     */
    public void broadcastMessage(final String message)
    {
        String s2s = message;
        final String s2m = ChatColor.translateAlternateColorCodes('&', s2s);
        Bukkit.getScheduler().scheduleSyncDelayedTask(this, new Runnable() {
            @Override
            public void run()
            {
                Bukkit.broadcastMessage(Messages.prefix + s2m);
            }
        }, delay);
    }

    /**
     * Send an error to the server log/console.
     *
     * @param message Error message.
     * @since 2.0
     */
    public void error(String message)
    {
        this.getLogger().log(Level.WARNING, Messages.error + "{0}", message);
    }

    /**
     * Send an error message to a player.
     *
     * @param player Player to send the message to.
     * @param message Message to send the player.
     * @since 2.0
     */
    public void error(final Player player, final String message)
    {
        Bukkit.getScheduler().scheduleSyncDelayedTask(this, new Runnable() {
            @Override
            public void run()
            {
                player.sendMessage(Messages.prefix + ChatColor.RED + Messages.error + ChatColor.RED + message);
            }
        }, delay);
    }

    /**
     * Send an error message to a player using CommandSender instead of player.
     *
     * @param player Player to send the message to.
     * @param message Message to send the player.
     * @since 2.0
     */
    public void error(final CommandSender player, final String message)
    {
        Bukkit.getScheduler().scheduleSyncDelayedTask(this, new Runnable() {
            @Override
            public void run()
            {
                player.sendMessage(Messages.prefix + ChatColor.RED + Messages.error + ChatColor.RED + message);
            }
        }, delay);
    }

    /**
     * Send a "permission denied" error message to a player.
     *
     * @param player Player to send the error message to.
     * @since 2.0
     */
    public void badperms(final Player player)
    {
        Bukkit.getScheduler().scheduleSyncDelayedTask(this, new Runnable() {
            @Override
            public void run()
            {
                player.sendMessage(Messages.prefix + Messages.badPerms);
            }
        }, delay);
    }

    /**
     * Send a "permission denied" error message to a player using CommandSender
     * instead of player..
     *
     * @param player Player to send the error message to.
     * @since 2.0
     */
    public void badperms(final CommandSender player)
    {
        Bukkit.getScheduler().scheduleSyncDelayedTask(this, new Runnable() {
            @Override
            public void run()
            {
                player.sendMessage(Messages.prefix + Messages.badPerms);
            }
        }, delay);
    }

    /**
     * Load config.yml
     *
     * @since 2.1
     */
    public void loadConfig()
    {
        File config = new File(getDataFolder(), "config.yml");
        if (!config.exists()) {
            saveDefaultConfig();
            getConfig().options().copyHeader(true);

            this.getLogger().log(Level.SEVERE, "No config.yml detected, config.yml created.");
        }
        if (getConfig().contains("disabled-dotcommands") && !getConfig().getList("disabled-dotcommands").isEmpty()) {
            ChatCommandHandler.disabledcmds = (List<String>) getConfig().getList("disabled-dotcommands");
        }
    }
}
