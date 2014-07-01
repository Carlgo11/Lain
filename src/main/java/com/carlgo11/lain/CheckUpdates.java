package com.carlgo11.lain;

import com.carlgo11.lain.player.disconnect.PlayerDisconnect;
import org.bukkit.Bukkit;

public class CheckUpdates {

    public static void runXMLCheck(final Lain Lain)
    {
        Bukkit.getScheduler().scheduleSyncRepeatingTask(Lain, new Runnable() {

            public void run()
            {
                if (!PlayerDisconnect.reboot) {
                    Lain.checkForUpdates();
                }
            }
        }, 12000l, 12000l);
    }
}
