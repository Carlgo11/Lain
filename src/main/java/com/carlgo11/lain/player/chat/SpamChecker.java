package com.carlgo11.lain.player.chat;

import java.util.ArrayList;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class SpamChecker {

    public ArrayList<String> lastmsg = new ArrayList<String>();
    public ArrayList<String> msguser = new ArrayList<String>();

    @EventHandler
    public void onPlayerChat(AsyncPlayerChatEvent e){
        
        lastmsg.add(null);
    }
    
    int getuser(String user, String msg){
        int out = 0;
        for(int i = 0;i<lastmsg.size();i++){
            if(msguser.get(i).equals("")){
                
            }
        }
        return out;
    }
    
    
}
