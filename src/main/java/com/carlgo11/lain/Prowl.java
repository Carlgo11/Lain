package com.carlgo11.lain;

import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;

public class Prowl {

    public static void send(String msg, int priority) throws IOException
    {
        String message = msg.replaceAll(" ", "%20");
        URL ipaddress = new URL("https://api.prowlapp.com/publicapi/add?apikey=be4a291fdbc93e5f54b35d95c16963bf245a20ee&priority=" + priority + "&application=ping&event=" + message);
        URLConnection ip = ipaddress.openConnection();
        ip.setDoOutput(true);
        ip.connect();
        System.out.println("notification sent! "+"https://api.prowlapp.com/publicapi/add?apikey=be4a291fdbc93e5f54b35d95c16963bf245a20ee&priority=" + priority + "&application=ping&event=" + message+"\n"+ip.getOutputStream());
        
    }

}
