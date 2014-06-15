package com.carlgo11.lain.player.chat;

import com.carlgo11.lain.Lain;
import java.util.Random;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class Phrases implements Listener {

    private Lain lain;

    public Phrases(Lain plug)
    {
        this.lain = plug;
    }

    @EventHandler
    public void onPlayerChat(AsyncPlayerChatEvent e)
    {
        String msg = e.getMessage();
        Player p = e.getPlayer();
        final String[] args = msg.split(" ");
        if (equalsHi(msg)) {
            lain.broadcastMessage(ChatColor.GREEN + returnGreeting(p));
        } else {
            heatherCommand(p, args);
        }
    }

    boolean equalsHi(String msg)
    {
        boolean laintrue = false;
        boolean greetingtrue = false;
        final String[] args = msg.split(" ");
        for (int i = 0; i < args.length; i++) {
            if (args[i].equalsIgnoreCase("Lain")) {
                laintrue = true;
                break;
            } else if (args[0].equalsIgnoreCase("Lain?")) {
                laintrue = true;
                greetingtrue = true;
            }
        }
        for (int i = 0; i < args.length; i++) {
            if (args[i].equalsIgnoreCase("hi") || args[i].equalsIgnoreCase("hey") || args[i].equalsIgnoreCase("hai") || args[i].equalsIgnoreCase("greetings") || args[i].equalsIgnoreCase("good day") || args[i].equalsIgnoreCase("goodday") || args[i].equalsIgnoreCase("sup") || args[i].equalsIgnoreCase("what's up") || args[i].equalsIgnoreCase("whats up") || args[i].equalsIgnoreCase("waddup") || args[i].equalsIgnoreCase("hello")) {
                greetingtrue = true;
                break;
            }
        }
        if (laintrue && greetingtrue) {
            return true;
        } else {
            return false;
        }

    }

    int random()
    {
        int a = 0;
        Random n = new Random();
        int num;
        for (int count = 1; count <= 2; count++) {
            num = 1 + n.nextInt(10);
            a = num;
        }
        return a;
    }

    String returnGreeting(Player player)
    {
        int ra = random();
        String name = player.getName();
        if (name.equals("carlgo11") || name.equals("anminecrafter")) {
            name = "Carl";
        }
        if (name.equals("olback")) {
            name = "Edwin";
        }
        if (name.equals("8404Heather")) {
            name = "Heather";
        }
        if (name.equals("101sierra101")) {
            name = "Sierra";
        }
        if (name.equals("jonkanon03")) {
            name = "J�nk!";
        }
        String outp = null;
        if (ra == 0) {
            outp = "Hello " + name;
        }
        if (ra == 1) {
            outp = "What's up " + name + "?";
        }
        if (ra == 2) {
            outp = "Greetings " + name;
        }
        if (ra == 3) {
            outp = "Hai " + name + " :3";
        }
        if (ra == 4) {
            outp = "Yo yo " + name + "!";
        }
        if (ra == 5) {
            outp = "Do I know you?";
        }
        if (ra == 6) {
            outp = "http://puu.sh/63qC9";
        }
        if (ra == 7) {
            outp = "Oh hello " + name + " :)";
        }
        if (ra == 8) {
            outp = "How are you today " + name + "?";
        }
        if (ra == 9) {
            outp = "Bonjour " + name;
        }
        if (ra == 10) {
            outp = "Good day " + name + ".";
        }
        return outp;
    }

    void heatherCommand(Player p, String[] args)
    {
        String a = "Make it day Lain";
        String[] b = a.split(" ");
        int c = 0;
        String d = "Lain make it day";
        String[] f = d.split(" ");
        int g = 0;
        for (int i = 0; i < 4; i++) {
            if (args.length >= 4) {
                if (args[i].equalsIgnoreCase(b[i])) {
                    c++;
                } else if (args[i].equalsIgnoreCase(f[i])) {
                    g++;
                }
            }
        }

        if (c == 4 || g == 4) {
            if (p.getUniqueId().toString().equals("0c8198e0-77b6-4c7a-8319-5f3246c8dd31")) {
                Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "day day all");
                lain.broadcastMessage(ChatColor.GREEN + "Okay! " + ChatColor.RED + "<3");
            } else {
                lain.broadcastMessage(ChatColor.LIGHT_PURPLE + "You're not Heather! :'(");
            }
        }
    }

}
