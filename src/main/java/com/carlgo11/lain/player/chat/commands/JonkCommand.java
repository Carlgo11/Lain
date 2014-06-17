package com.carlgo11.lain.player.chat.commands;

import com.carlgo11.lain.ChatCommands;
import com.carlgo11.lain.Lain;
import com.carlgo11.lain.random;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class JonkCommand implements ChatCommands{

    public String getCommandName()
    {
        return "jonk";
    }

    public void onMessage(Lain Lain, Player p, String msg, String cmd, String[] args)
    {
        Lain.broadcastMessage(ChatColor.GREEN+"\""+quote()+"\" - "+ChatColor.GRAY+name());
    }
    
    String quote(){
        int r = random.getInt(13);
        if(r == 1){
            return "Tja kids!";
        }else if(r == 2){
            return "Jag säger inte så!";
        }else if(r == 3){
            return "Och jag hoppas att ni gillar min kepps!";
        }else if(r == 4){
            return "It's a party! It's a door!";
        }else if(r == 5){
            return "Visst sjunger jag så fint? :)";
        }else if(r == 6){
            return "Då stänger jag av servern!";
        }else if(r == 7){
            return "Men kolla! Det är Kalgåelva! Screeny!";
        }else if(r == 8){
            return "No! plz! plz! plz!";
        }else if(r == 9){
            return "Jag sparar ihop till en dator";
        }else if(r == 10){
            return "Låter mina knappar? ;)";
        }else if(r == 11){
            return "Lain är snygg";
        }else if(r == 12){
            return "Caaaaarl vad är lösenordet till konsollen? :)";
        }else if(r == 13){
            return "När jag är 12 ska jag till Dreamhack";
        }
        
        
        return null;
    }
    
    String name(){
        int r = random.getInt(17);
        if(r == 1){
            return "Jonkanuuuuuun03";
        }else if(r == 2){
            return "Jonk kanonk03";
        }else if(r == 3){
            return "Junk";
        }else if(r == 4){
            return "Jan Citran null tre";
        }else if(r == 5){
            return "Jan Karavan03";
        }else if(r == 6){
            return "Jon Miljon03";
        }else if(r == 7){
            return "Jon Baron03";
        }else if(r == 8){
            return "Jan Milan03";
        }else if(r == 9){
            return "Jon Kommunikation03";
        }else if(r == 10){
            return "Jon Citron03";
        }else if(r == 11){
            return "Jon Filion03";
        }else if(r == 12){
            return "Jon Applikation03";
        }else if(r == 13){
            return "Jon Arrantion03";
        }else if(r == 14){
            return "Jon Kommunist Skvadron03";
        }else if(r == 15){
            return "JONK! JONK! JONK!";
        }else if(r == 16){
            return "Jönk";
        }else if(r == 17){
            return "Jon Multiplikation03";
        }
        
        return null;
    }

}
