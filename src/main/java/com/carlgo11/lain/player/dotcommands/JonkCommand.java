package com.carlgo11.lain.player.dotcommands;

import com.carlgo11.lain.ChatCommands;
import com.carlgo11.lain.Lain;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Random;
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
        if (p.hasPermission("lain.dotcommand.jonk")) {
            Lain.broadcastMessage(ChatColor.GREEN + "\"" + quote() + "\" - " + ChatColor.GRAY + name());
        } else {
            Lain.badperms(p);
        }
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
        int r = getInt(a.size());
        return a.get(r - 1);
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
        int r = getInt(b.size());
        return b.get(r - 1);
    }

    private int getInt(int i)
    {
        int qwe = 0;
        int a = 0;
        if (i < 3 || qwe == 0) {
            Random n = new Random();
            int num = 0;
            for (int count = 1; count <= 2; count++) {
                num = 1 + n.nextInt(i);
            }
            a = num;
        } else {
            Random n = new Random();
            int num = 0;
            for (int count = 1; count <= 2; count++) {
                num = n.nextInt(i);
            }
            a = num;
            if (qwe == a) {
                getInt(i);
            }
        }
        return a;
    }
}
