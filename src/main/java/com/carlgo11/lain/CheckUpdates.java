package com.carlgo11.lain;

import com.carlgo11.lain.player.disconnect.PlayerDisconnect;
import java.net.URL;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.w3c.dom.Document;

/**
 * Version-checking function to determine if the plugin needs to update and
 * reboot the server.
 *
 * @since 2.0
 */
public class CheckUpdates {

    public static void runXMLCheck(final Lain Lain)
    {
        Bukkit.getScheduler().scheduleSyncRepeatingTask(Lain, new Runnable() {

            public void run()
            {
                if (!PlayerDisconnect.reboot) {
                    CheckUpdates.checkForUpdates(Lain);
                }
            }
        }, 12000l, 12000l);
    }

    /**
     * Check for newer versions via the CI-server.
     *
     * @param lain Main class.
     * @deprecated CI server no longer used.
     */
    public static void checkForUpdates(Lain lain)
    {
        try {
            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            Document document = documentBuilder.parse(new URL("http://ci.carlgo11.com/rssLatest").openStream());
            String v = document.getElementsByTagName("title").item(1).getTextContent();
            String[] a = v.split(" ");
            String xv = a[1].replace("#", "");
            String version = Lain.class.getPackage().getImplementationVersion();
            if (!version.equals("")) {
                if (Integer.parseInt(xv) > Integer.parseInt(version)) {
                    lain.broadcastMessage(ChatColor.GREEN + "New update found! Server restart scheduled. (Current version: 2." + version + " New version: 2." + xv + ")");
                    if (Bukkit.getOnlinePlayers().isEmpty()) {
                        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "stop");
                    } else {
                        System.out.println(Bukkit.getOnlinePlayers().size());
                        PlayerDisconnect.reboot = true;
                    }
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
