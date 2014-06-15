package com.carlgo11.lain;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DotCommands {

    private Lain Lain;
    Properties commands = new Properties();
    Properties aliases = new Properties();
    String commandsfilename = "commands.properties";
    String aliasesfilename = "aliases.properties";

    public void main(Lain l)
    {
        this.Lain = l;
        createFiles();
        try {
            commands.load(new FileInputStream(Lain.getDataFolder() + commandsfilename));
            aliases.load(new FileInputStream(Lain.getDataFolder() + aliasesfilename));
        } catch (IOException ex) {
            Logger.getLogger(DotCommands.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void createFiles()
    {
        File commands = new File(Lain.getDataFolder(), "config.yml");
        File aliases = new File(Lain.getDataFolder(), "config.yml");

        try {
            if (!commands.exists()) {
                commands.createNewFile();
            } else if (!aliases.exists()) {
                aliases.createNewFile();
            }
        } catch (IOException ex) {
            Logger.getLogger(DotCommands.class.getName()).log(Level.SEVERE, null, ex);
        }
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
