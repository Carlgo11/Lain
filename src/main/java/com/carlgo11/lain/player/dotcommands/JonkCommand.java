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
        if (loadFiles(Lain)) {
            if (p.hasPermission("lain.dotcommand.jonk")) {
                Lain.broadcastMessage(ChatColor.GREEN + "\"" + getQuote(Lain) + "\" - " + ChatColor.GRAY + getName(Lain));
            } else {
                Lain.badperms(p);
            }
        }
    }

    private boolean loadFiles(Lain Lain)
    {
        return !(Lain.getConfig().getString("jonkquotes.quotesfile") != null || Lain.getConfig().getString("jonkquotes.quotefile").isEmpty());
    }

    private String getQuote(Lain Lain)
    {
        try {
            File file = new File(Lain.getDataFolder() + System.lineSeparator() + Lain.getConfig().getString("jonkquotes.quotefile"));
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

    private String getName(Lain Lain)
    {
        try {
            File file = new File(Lain.getDataFolder() + System.lineSeparator() + Lain.getConfig().getString("jonkquotes.namefile"));
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
        int ab;
        if (i < 3 || qwe == 0) {
            Random n = new Random();
            int num = 0;
            for (int count = 1; count <= 2; count++) {
                num = 1 + n.nextInt(i);
            }
            ab = num;
        } else {
            Random n = new Random();
            int num = 0;
            for (int count = 1; count <= 2; count++) {
                num = n.nextInt(i);
            }
            ab = num;
            if (qwe == ab) {
                getInt(i);
            }
        }
        return ab;
    }
}
