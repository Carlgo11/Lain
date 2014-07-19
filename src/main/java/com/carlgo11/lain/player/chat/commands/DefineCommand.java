package com.carlgo11.lain.player.chat.commands;

import com.carlgo11.lain.ChatCommands;
import com.carlgo11.lain.Lain;
import com.carlgo11.lain.WASearch;
import java.util.ArrayList;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class DefineCommand implements ChatCommands {

    public String getCommandName()
    {
        return "define";
    }
    
    public void onMessage(Lain Lain, Player p, String msg, String cmd, String[] args)
    {
        StringBuilder d = new StringBuilder();
                for(int i = 1; i < args.length; i++){
                    d.append(args[i]);
                    d.append(" ");
                }
                ArrayList<String> a = WASearch.search(d.toString());
                
                for(int i = 0; i < a.size(); i++){
                     Bukkit.broadcastMessage(a.get(i));
                }
    }

}
