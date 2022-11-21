package net.mov51.pteroCommandLine.helpers;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;


public class fileHelper {

    public static void defaultConfig() throws IOException {
        Path configDir = config.configDir;
        configDir.toFile().mkdirs();
        if (Files.isDirectory(configDir)) {
            try (Stream<Path> entries = Files.list(configDir)) {
                if(!entries.findFirst().isPresent()){
                    System.out.println("Creating an example configuration file!");
                    copyFromStream(config.defaultConfig, Paths.get(configDir + File.separator + "config.yml"));
                }
            }
        }
    }


    public static void copyFromStream(String inputString, Path path){
        try {
            InputStream inputStream = fileHelper.class.getResourceAsStream(inputString);
            if (inputStream != null) {
                Files.copy(inputStream, path);
                inputStream.close();
                if(!path.toFile().exists())
                    System.out.println("Could not find the " + path.getFileName() + " file after creating it!");
            } else {
                System.out.println("Could not create the " + path.getFileName() + " file do to the input stream being null!");
            }
        }catch (IOException e){
            System.out.println("Could not create the " + path.getFileName() + " file!");
        }
    }
}
