package com.carlgo11.lain.player.chat.commands;

import com.carlgo11.lain.ChatCommands;
import com.carlgo11.lain.Lain;
import com.carlgo11.lain.Prowl;
import java.io.IOException;
import org.bukkit.entity.Player;

public class NotifyCommand implements ChatCommands{

    public String getCommandName()
    {
       return "notify";
    }

    public void onMessage(Lain Lain, Player p, String msg, String cmd, String[] args)
    {
        if(p.hasPermission("lain.notify")){
            if(args.length > 1){
                StringBuilder d = new StringBuilder();
                for (int i = 2; i < args.length; i++){
                    d.append(args[i]);
                    d.append("%20");
                }
                try {
                    Prowl.send(d.toString()+"sent%20%by%20"+p.getName(), 0);
                } catch (IOException ex) {
                  Lain.error(p, ex.toString());
                }
            }else{
                Lain.error(p, "Usage: .notify (message)");
            }
        }else{
            Lain.badperms(p);
        }
    }

}
