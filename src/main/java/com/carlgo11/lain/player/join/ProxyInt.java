package com.carlgo11.lain.player.join;

import java.util.Random;

public class ProxyInt {

    static int a = 0;

    public static void onInt()
    {
        Random n = new Random();
        int num;
        for (int count = 1; count <= 2; count++) {
            num = 1 + n.nextInt(8);
            a = num;
        }
    }
}
