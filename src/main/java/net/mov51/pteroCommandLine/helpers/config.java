package net.mov51.pteroCommandLine.helpers;

import org.yaml.snakeyaml.Yaml;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;

public class config {

    Map<String, Object> configMap;

    public static final Path configDir = Paths.get("config");
    public static final String defaultConfig = "/default.yml";

    public String fish = "minecraft:execute at mov51 run summon minecraft:tropical_fish ~ ~1 ~ {CustomName:\\\"{0}\\\",DeathLootTable:\\\"mctools:entities/nodrop\\\",CustomNameVisible:1,PersistenceRequired:1b,CanPickUpLoot:0b,Health:10,Attributes:[{Name:\\\"generic.max_health\\\",Base:10}],HandDropChances:[2F,2F],HandItems:[{id:\\\"minecraft:tropical_fish\\\",tag:{display:{Name:\\'{\\\"text\\\":\\\"{0}\\\"}\\',Lore:[\\'{\\\"text\\\":\\\"You failed, you didnt save me.\\\"}\\',\\'{\\\"text\\\":\\\"\\\"}\\',\\'{\\\"text\\\":\\\"Sent with loathing,\\\"}\\',\\'{\\\"text\\\":\\\"-{0}\\\"}\\']}},Count:1},{}]}";

    public String announce = "title mov51 title {\\\"text\\\":\\\"You must save {0}\\\",\\\"color\\\":\\\"red\\\"}";

    private enum YAMLKeys {
        APIkey ("APIkey"),
        serverUUID ("serverUUID"),
        panelURL ("panelURL"),

        command ("command");

        public final String defaultKey;

        YAMLKeys(String defaultKey) {
            this.defaultKey = defaultKey;
        }
    }

    public config(String configPath){
        this.configMap = getMap(configPath);
    }

    public String getPterodactylAPIkey(){
        String key = YAMLKeys.APIkey.defaultKey;
        return loadGetter(key);
    }

    public String getPterodactylPanelURL(){
        String key = YAMLKeys.panelURL.defaultKey;
        return loadGetter(key);
    }

    public String getPterodactylServerUUID(){
        String key = YAMLKeys.serverUUID.defaultKey;
        return loadGetter(key);
    }

    public String getCommand(String command ,String[] args, int start){
        for(int i = start; i < args.length; i++){
            command = command.replace("{"+(i - start)+"}",args[i]);
        }
        return command;
    }


    private String loadGetter(String key){
        if(configMap.containsKey(key)){
            return configMap.get(key).toString();
        }else{
            System.out.println("Could not load key " + key + " from coreConfig!");
            return "";
        }
    }

    public static Map<String,Object> getMap(String Config){
        InputStream inputStream;
        try {
            inputStream = new FileInputStream(config.configDir + File.separator + Config);
            Yaml yaml = new Yaml();
            return yaml.load(inputStream);
        } catch (FileNotFoundException e) {
            System.out.println("Could not load yaml file at " + Config);
            e.printStackTrace();
        }
        return null;
    }
}
