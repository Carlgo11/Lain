package com.carlgo11.lain.player.join;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.player.PlayerLoginEvent;

public class ProxyListener {

    @EventHandler(priority = EventPriority.NORMAL)
    public static void checkForProxy(final PlayerLoginEvent event)
    {

        System.out.println("Searching for proxy");
        if (!event.getPlayer().hasPermission("antiproxy.bypass") && !event.getAddress().getHostAddress().equals("127.0.0.1") && !event.getAddress().getHostAddress().startsWith("192.168")) {
            try {
                String url = "http://botscout.com/test/?ip=" + event.getAddress().getHostAddress() + "&key=NUpLWF2hI94oGXd";
                Scanner scanner = new Scanner(new URL(url).openStream());
                if (scanner.findInLine("Y") != null) {
                    ProxyInt.onInt();
                    if (ProxyInt.a == 0) {
                        event.disallow(PlayerLoginEvent.Result.KICK_OTHER, "You are using a proxy!");
                    } else if (ProxyInt.a == 1) {
                        event.disallow(PlayerLoginEvent.Result.KICK_OTHER, "I once had a friend that used a proxy like you, He's dead now :(");
                    } else if (ProxyInt.a == 2) {
                        event.disallow(PlayerLoginEvent.Result.KICK_OTHER, "Just turn off that proxy mate :)");
                    } else if (ProxyInt.a == 3) {
                        event.disallow(PlayerLoginEvent.Result.KICK_OTHER, "Chuck Norris doesn't approve this proxy!");
                    } else if (ProxyInt.a == 4) {
                        event.disallow(PlayerLoginEvent.Result.KICK_OTHER, "Lain says hi but the proxy says BAN!");
                    } else if (ProxyInt.a == 5) {
                        event.disallow(PlayerLoginEvent.Result.KICK_OTHER, "Nah I don't really like when you use proxies on my server.");
                    } else if (ProxyInt.a == 6) {
                        event.disallow(PlayerLoginEvent.Result.KICK_OTHER, "Go turn off that proxy would ya?");
                    } else if (ProxyInt.a == 7) {
                        event.disallow(PlayerLoginEvent.Result.KICK_OTHER, "Hello mate! why not just turn off that proxy so we can play some minecraft toghether?");
                    } else if (ProxyInt.a == 8) {
                        event.disallow(PlayerLoginEvent.Result.KICK_OTHER, "I know a guy that uses Proxies like you, His name is Earl.");
                    }
                    System.out.println("Found proxy");
                } else {
                }
                scanner.close();
            } catch (MalformedURLException exception) {
                exception.printStackTrace();
            } catch (IOException exception) {
                exception.printStackTrace();
            }
        } else {
            System.out.println(event.getPlayer().getName() + "has permission to use proxies");
        }
    }
}
