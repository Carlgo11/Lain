package com.carlgo11.lain;

import java.io.IOException;

public class Prowl {

    public static void send(String msg, int priority) throws IOException
    {
        String message = msg.replaceAll(" ", "%20");
        Process exec = Runtime.getRuntime().exec("curl -X POST 'https://api.prowlapp.com/publicapi/add?apikey=0d7e9af82f4714bf3b3bc2c033bf1aa4ad75db2c&priority=" + priority + "&application=lain&event=" + message + "'");
    }
}
