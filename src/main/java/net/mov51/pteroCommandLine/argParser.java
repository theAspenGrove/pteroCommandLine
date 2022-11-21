package net.mov51.pteroCommandLine;

import net.mov51.pteroCommandLine.helpers.config;

import java.io.File;

import static net.mov51.pteroCommandLine.helpers.APIHelper.sendCommand;
import static net.mov51.pteroCommandLine.helpers.config.configDir;

public class argParser {
    static String configurationFile;
    public static void parse(String[] args){
        switch (args.length){
            case 0:
                System.out.println("Please provide a configuration file and a command!");
                break;
            case 1:
                System.out.println("using default configuration file");
                configurationFile = "config.yml";
                run(configurationFile, args[0]);
                break;
            case 2:
                configurationFile = args[0];
                run(configurationFile, args[1]);
                break;
        }
    }
    private static void run(String configurationFile, String command){
        config configuration = new config(configurationFile);
        sendCommand(configuration,command);
    }
}
