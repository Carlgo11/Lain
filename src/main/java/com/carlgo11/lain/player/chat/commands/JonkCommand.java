package com.carlgo11.lain.player.chat.commands;

import com.carlgo11.lain.ChatCommands;
import com.carlgo11.lain.Lain;
import com.carlgo11.lain.random;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class JonkCommand implements ChatCommands {

    public String getCommandName()
    {
        return "jonk";
    }

    ArrayList<String> a = new ArrayList<String>();
    ArrayList<String> b = new ArrayList<String>();

    public void onMessage(Lain Lain, Player p, String msg, String cmd, String[] args)
    {
        Lain.broadcastMessage(ChatColor.GREEN + "\"" + quote() + "\" - " + ChatColor.GRAY + name());
    }

    String quote()
    {
        try {
            File file = new File("jonquotes.txt");
            if (!file.exists()) {
                file.createNewFile();
            }
            BufferedReader read = new BufferedReader(new FileReader(file));
            String line;
            while ((line = read.readLine()) != null) {
                if (!a.contains(line)) {
                    if (!line.isEmpty()) {
                        a.add(line);
                    }
                }
            }
        } catch (Exception ex) {
            System.out.println(ex);
        }
        int r = random.getInt(a.size());
        return a.get(r - 1).toString();
    }

    String name()
    {

        try {
            File file = new File("jonnames.txt");
            if (!file.exists()) {
                file.createNewFile();
            }
            BufferedReader read = new BufferedReader(new FileReader(file));
            String line;
            while ((line = read.readLine()) != null) {
                if (!b.contains(line)) {
                    if (!line.isEmpty()) {
                        b.add(line);
                    }
                }
            }
        } catch (Exception ex) {
            System.out.println(ex);
        }
        int r = random.getInt(b.size());
        return b.get(r - 1).toString();
    }
}
