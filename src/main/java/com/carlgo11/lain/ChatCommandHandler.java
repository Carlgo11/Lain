package com.carlgo11.lain;

import com.carlgo11.lain.player.dotcommands.*;
import java.util.ArrayList;
import java.util.List;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class ChatCommandHandler implements Listener {

    private final Lain plugin;

    public ChatCommandHandler(Lain plug)
    {
        this.plugin = plug;
    }

    public static List<ChatCommands> cmds = new ArrayList<ChatCommands>();
    public static List<String> disabledcmds = new ArrayList<String>();

    /**
     * Calls the appropriate command.
     *
     * @param e Event
     * @since 2.0
     */
    @EventHandler
    public void onPlayerChat(AsyncPlayerChatEvent e)
    {
        if (!e.isCancelled()) {
            if (cmds.isEmpty()) {
                loadInternalCommands();
            }

            if (!internalCommandIsDisabled(e.getMessage().split(" ")[0])) {
                callInternalCommand(e.getMessage(), e.getPlayer());
            }
        }
    }

    /**
     * Load all the internal commands to {@link #cmds}.
     *
     * @since 2.1
     */
    private void loadInternalCommands()
    {
        cmds.add(new GoogleCommand());
        cmds.add(new GCommand());
        cmds.add(new OpCommand());
        cmds.add(new PerformCommand());
        cmds.add(new VersionCommand());
        cmds.add(new JonkCommand());
        cmds.add(new UpCommand());
        cmds.add(new DownCommand());
    }

    /**
     * Call an internal command.
     *
     * @param message Entire chat message.
     * @param player Player calling the command.
     * @since 2.0
     */
    private void callInternalCommand(String message, Player player)
    {
        String[] args = message.split(" ");
        for (ChatCommands cmd : ChatCommandHandler.cmds) {
            if (args[0].equalsIgnoreCase("." + cmd.getCommandName())) {
                cmd.onMessage(plugin, player, message, args[0], args);
                return;
            }
        }
        ExternalCommands.Main(message, player, plugin);
    }

    /**
     * Return whether or not an internal command exists.
     *
     * @param command Command to compare.
     * @return Returns true if the command exists. Returns false otherwise.
     * @since 2.0
     */
    public static boolean internalCommandExists(String command)
    {
        if (command != null) {
            for (ChatCommands cmd : ChatCommandHandler.cmds) {
                if (command.equalsIgnoreCase(cmd.getCommandName())) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean internalCommandIsDisabled(String command)
    {
        return disabledcmds.contains(command.toLowerCase());
    }
}
