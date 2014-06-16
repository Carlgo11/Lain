/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.carlgo11.lain;

import org.bukkit.entity.Player;

/**
 *
 * @author Carl
 */
public interface ChatCommands {
    
    public String getCommandName();
    
    public void onMessage(Lain Lain, Player p, String msg, String cmd, String[] args);
    
}
