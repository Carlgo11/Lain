package com.carlgo11.lain;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DotCommands {

    private Lain Lain;
    Properties commands = new Properties();
    Properties aliases = new Properties();
    String commandsfilename = "commands.properties";
    String aliasesfilename = "aliases.properties";

    public void main(Lain plugin)
    {
        this.Lain = plugin;
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
        File commands = new File(Lain.getDataFolder(), "\\commands.properties");
        File aliases = new File(Lain.getDataFolder(), "\\aliases.properties");

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

    public void setCommand(String command, String Message)
    {
        if (!containsCommand(command)) {
            commands.setProperty(command, Message);
            this.saveProperties();
        }
    }

    public void setAlias(String alias, String command)
    {
        if (!containsAlias(alias)) {
            aliases.setProperty(alias, command);
            this.saveProperties();
        }
    }

    public boolean removeCommand(String command)
    {
        if (commands.contains(command)) {
            commands.remove(command);
            this.saveProperties();
            return true;
        } else {
            return false;
        }
    }

    public boolean removeAlias(String alias)
    {
        if (aliases.contains(alias)) {
            aliases.remove(alias);
            this.saveProperties();
            return true;
        } else {
            return false;
        }
    }

    public boolean containsCommand(String command)
    {
        if (commands.contains(command)) {
            return true;
        } else {
            return false;
        }
    }

    public boolean containsAlias(String alias)
    {
        if (aliases.contains(alias)) {
            return true;
        } else {
            return false;
        }
    }
    public void saveProperties(){
        try {
            OutputStream outp = new FileOutputStream("commands.properties");
            commands.store(outp, null);
            outp.close();
        } catch (Exception ex) {
            Logger.getLogger(DotCommands.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
