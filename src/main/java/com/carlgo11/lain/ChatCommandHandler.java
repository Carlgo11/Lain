package com.carlgo11.lain;

import com.carlgo11.lain.player.chat.commands.*;
import java.util.ArrayList;
import java.util.List;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class ChatCommandHandler implements Listener {
    
private Lain lain;

    public ChatCommandHandler(Lain plug)
    {
        this.lain = plug;
    }

    @EventHandler
    public void onPlayerChat(AsyncPlayerChatEvent e)
    {
        final List<ChatCommands> cmds;
        cmds = new ArrayList<ChatCommands>();
        cmds.add(new AdminCommand());
        cmds.add(new GoogleCommand());
        cmds.add(new HeadadminCommand());
        cmds.add(new ModCommand());
        cmds.add(new OpCommand());
        cmds.add(new OwnerCommand());
        cmds.add(new PerformCommand());
        cmds.add(new PingCommand());
        cmds.add(new VersionCommand());
        cmds.add(new JonkCommand());
        
        
        if(!e.isCancelled()){
        giveOutCommand(cmds, e.getMessage(), e.getPlayer());
        }
    }
    
    
    boolean giveOutCommand(List<ChatCommands> cmds, String msg, Player p){
        String [] args = msg.split(" ");
        for(ChatCommands cmd: cmds){
            if(args[0].equalsIgnoreCase("."+cmd.getCommandName())){
                cmd.onMessage(lain, p , msg, args[0], args);
                return true;
            }
        }
        ExternalCommands.Main(msg, p, lain);
        return false;
    }

}