package com.carlgo11.lain;

import org.bukkit.entity.Player;

public class API {

    public static boolean isAdmin(Player p, Lain Lain)
    {
        boolean outp = false;

        if (Lain.getConfig().getList("admins").contains(p.getName())) {
            outp = true;
        } else {
            outp = false;
        }

        return outp;
    }
}
