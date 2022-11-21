package net.mov51.pteroCommandLine;

import net.mov51.pteroCommandLine.helpers.config;

import java.io.File;

import static net.mov51.pteroCommandLine.helpers.APIHelper.sendCommand;
import static net.mov51.pteroCommandLine.helpers.config.configDir;

public class argParser {
    static String configurationFile;
    public static void parse(String[] args){
        if(args.length == 0){
            System.out.println("No arguments provided");
            System.exit(1);
        }
        configurationFile = "config.yml";
        run(configurationFile, args);
    }
    private static void run(String configurationFile, String[] args){
        config configuration = new config(configurationFile);
        switch (args[0])
        {
            case "fish":
                sendCommand(configuration,configuration.getCommand(configuration.fish,args, 1));
                break;
            case "announce":
                sendCommand(configuration,configuration.getCommand(configuration.announce,args, 1));
                break;
            case "custom":
                sendCommand(configuration,configuration.getCommand(args[1],args, 2));
                break;
            default:
                System.out.println("Invalid command");
                System.exit(1);
        }

    }
}
