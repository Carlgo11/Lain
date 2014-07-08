package com.carlgo11.lain;

import org.bukkit.entity.Player;

public interface ChatCommands {

    public String getCommandName();

    public void onMessage(Lain Lain, Player p, String msg, String cmd, String[] args);

}
