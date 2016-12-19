package com.carlgo11.lain.player.chat;

import com.carlgo11.lain.Lain;
import java.util.Random;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

/**
 * Phrases in the message line to react to.
 * @since 2.0
 */
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
        if (!e.isCancelled()) {
            if (equalsHi(msg)) {
                lain.broadcastMessage(ChatColor.GREEN + returnGreeting(p));
            } else if (msg.toLowerCase().startsWith("lain what is")) {
                //wa(p, args);
            } else {
                heatherCommand(p, args);
            }
        }
    }

    /**
     * Greetings to react to.
     * @param msg Message.
     * @return returns response message.
     * @since 2.0
     */
    public boolean equalsHi(String msg)
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

    /**
     * Responses for greetings.
     * @param player Player sending the greeting.
     * @return Response.
     * @since 2.0
     */
    public String returnGreeting(Player player)
    {
        int ra = random();
        String name = player.getName();
        String uuid = player.getUniqueId().toString();
        if (uuid.equals("634ee008-e2a1-4b6f-bce0-78e6f38b67b5") || uuid.equals("01964c49-2a88-47c3-8781-90aa6d693f08")) {
            name = "Carl";
        }
        if (uuid.equals("2307d8fc-dbbf-4598-a17c-c00de089381d")) {
            name = "Edwin";
        }
        if (uuid.equals("0c8198e0-77b6-4c7a-8319-5f3246c8dd31")) {
            name = "Heather";
        }
        if (uuid.equals("c990d756-1d22-4c1b-9e0d-8dc34f856027")) {
            name = "Sierra";
        }
        if (uuid.equals("5105cc92-0c0f-4503-8820-af2bb62c7600")) {
            name = "Jonk";
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
            outp = "Haaaaaaaaai " + name + "! :D";
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

    /**
     * Command for Heather to make the current time daytime.
     * @param player Player
     * @param message Message.
     * @since 2.0
     */
    public void heatherCommand(Player player, String[] message)
    {
        String a = "Make it day Lain";
        String[] b = a.split(" ");
        int c = 0;
        String d = "Lain make it day";
        String[] f = d.split(" ");
        int g = 0;
        for (int i = 0; i < 4; i++) {
            if (message.length >= 4) {
                if (message[i].equalsIgnoreCase(b[i])) {
                    c++;
                } else if (message[i].equalsIgnoreCase(f[i])) {
                    g++;
                }
            }
        }

        if (c == 4 || g == 4) {
            if (player.getUniqueId().toString().equals("0c8198e0-77b6-4c7a-8319-5f3246c8dd31")) {
                Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "day day all");
                lain.broadcastMessage(ChatColor.GREEN + "Okay! " + ChatColor.RED + "<3");
            } else {
                lain.broadcastMessage(ChatColor.LIGHT_PURPLE + "You're not Heather! :'(");
            }
        }
    }

//    void wa(Player p, String[] args)
//    {
//        StringBuilder d = new StringBuilder();
//        for (int i = 3; i < args.length; i++) {
//            d.append(args[i]);
//            d.append(" ");
//        }
//        ArrayList<String> a = WASearch.search(d.toString());
//
//    }
}
