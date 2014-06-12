package com.carlgo11.lain;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import org.bukkit.event.Listener;

public class Checkfiles implements Listener {

    Lain plugin;

    public Checkfiles(Lain plug)
    {
        super();
        this.plugin = plug;
        this.checkFiles();
    }

    public void checkFiles()
    {
        this.checkConfig();
        this.loadCommands();
    }

    public void checkConfig()
    {
        File config = new File(plugin.getDataFolder(), "config.yml");
        if (!config.exists()) {
            plugin.saveDefaultConfig();
            plugin.getConfig().options().copyHeader(true);

            System.out.println("[Lain] No config.yml detected, config.yml created");
        }

    }

    public void loadCommands()
    {
        try {
            File dir = new File("/var/www/lain/");;
            if (!dir.exists()) {
                dir.mkdir();
            }
            File file = new File(dir + "/" + "commands.txt");
            if (!file.exists()) {
                file.createNewFile();
            }
            BufferedReader read = new BufferedReader(new FileReader(file));
            String line;
            while ((line = read.readLine()) != null) {
                if (!plugin.commands.contains(line)) {
                    if (!line.isEmpty()) {
                        plugin.commands.add(line);
                    }
                }
            }
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }

    public static void saveCommands()
    {
        try {
            File file = new File("/var/www/lain/commands.txt");
            FileWriter d = new FileWriter(file);
            StringBuilder f = new StringBuilder();
            for (int i = 0; i < Lain.commands.size(); i++) {
                f.append(Lain.commands.get(i).toString());
                f.append("\n");
            }
            d.flush();
            d.write(f.toString());
            d.close();
        } catch (Exception ex) {
            System.out.println("save-ignored error: " + ex);
        }

    }
}
