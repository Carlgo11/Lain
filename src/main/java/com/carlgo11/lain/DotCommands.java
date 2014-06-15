package com.carlgo11.lain;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class DotCommands {

    private Lain Lain;
    Properties file = new Properties();

    public void main(Lain l) throws IOException
    {
        this.Lain = l;
        file.load(new FileInputStream("commands.properties"));
    }

    public String getMessage(String command)
    {
        if (file.contains(command)) {
            return file.getProperty(command);
        }
        return null;
    }

}
