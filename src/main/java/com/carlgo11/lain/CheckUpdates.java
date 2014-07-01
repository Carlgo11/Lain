package com.carlgo11.lain;

import com.carlgo11.lain.player.disconnect.PlayerDisconnect;
import java.net.URL;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.w3c.dom.Document;

public class CheckUpdates {

    public static void runXMLCheck(final Lain Lain)
    {
        Bukkit.getScheduler().scheduleSyncRepeatingTask(Lain, new Runnable() {

            public void run()
            {
                if (!PlayerDisconnect.reboot) {
                    try {
                        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory
                                .newInstance();
                        DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
                        Document document = documentBuilder.parse(new URL("http://ci.carlgo11.com/rssLatest").openStream());
                        String v = document.getElementsByTagName("title").item(1).getTextContent();
                        String[] a = v.split(" ");
                        String xv = a[1].toString().replace("#", "");
                        String version = CheckUpdates.class.getPackage().getImplementationVersion();
                        if (!version.equals("")) {
                            if (Integer.parseInt(xv) > Integer.parseInt(version)) {
                                Lain.broadcastMessage(ChatColor.GREEN + "New update found! Server restart scheduled. (Old version: 2."+version+" New version: 2."+xv+")");
                                if (Bukkit.getOfflinePlayers().length == 1) {
                                    Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "stop");
                                } else {
                                    PlayerDisconnect.reboot = true;
                                }
                            }
                        }
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
            }
        }, 12000l, 12000l);
    }
}
