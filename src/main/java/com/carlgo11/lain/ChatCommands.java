package com.carlgo11.lain;

import org.bukkit.entity.Player;

/**
 * Interface for 'dotcommands'.
 *
 * @since 2.0
 */
public interface ChatCommands {

    public String getCommandName();

    public void onMessage(Lain Lain, Player p, String msg, String cmd, String[] args);

}
