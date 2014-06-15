package com.carlgo11.lain;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class DotCommands {

    Properties commands = new Properties();
    Properties aliases = new Properties();

    public void main() throws IOException
    {
        commands.load(new FileInputStream("commands.properties"));
        aliases.load(new FileInputStream("aliases.properties"));
    }

    public String getMessage(String command)
    {
        if (commands.contains(command)) {
            return commands.getProperty(command);
        } else if (aliases.contains(command)) {
            return aliases.getProperty(commands.getProperty(command));
        }
        return null;
    }

    public boolean setCommand(String command, String Message)
    {
        if (commands.contains(command)) {
            return false;
        } else {
            commands.setProperty(command, Message);
            return true;
        }
    }

    public boolean removeCommand(String command)
    {
        if (commands.contains(command)) {
            commands.remove(command);
            return true;
        } else {
            return false;
        }
    }

    public boolean removeAlias(String alias)
    {
        if (aliases.contains(alias)) {
            aliases.remove(alias);
            return true;
        } else {
            return false;
        }
    }

}
