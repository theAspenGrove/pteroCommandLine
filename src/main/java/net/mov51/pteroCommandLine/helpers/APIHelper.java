package net.mov51.pteroCommandLine.helpers;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class APIHelper {
    public static void sendCommand(config configuration, String command){
        //todo receive uuid to send command to

        try {
            //build API url
            URL url = new URL(configuration.getPterodactylPanelURL() + "/api/client/servers/"+ configuration.getPterodactylServerUUID() + "/command");

            HttpURLConnection http = (HttpURLConnection)url.openConnection();

            //set request type and related properties
            http.setRequestMethod("POST");
            http.setDoOutput(true);
            http.setRequestProperty("Content-Type", "application/json");
            http.setRequestProperty("Accept", "application/vnd.wisp.v1+json");

            //use provided key to authorize
            http.setRequestProperty("Authorization", "Bearer " + configuration.getPterodactylAPIkey());

            //send command
            String data = "{\"command\": \"" + command + "\"}";

            byte[] out = data.getBytes(StandardCharsets.UTF_8);

            OutputStream stream = http.getOutputStream();
            stream.write(out);

            if(http.getResponseCode() == 204){
                http.disconnect();
                //todo check for code 412 when server isn't running
            }else if(http.getResponseCode() == 412 ){
                System.out.println("Server is not running");
                http.disconnect();
            } else {
                //todo change to error logger
                //todo create fail safe class to notify user of failures
                //todo create error logger that can handle https errors
                System.out.println(http.getURL());
                System.out.println(http.getResponseCode());
                System.out.println(http.getResponseMessage());
                System.out.println(http.getErrorStream());
                System.out.println(http.getContentType());
                System.out.println(http.getContent());
                System.out.println(stream);
                http.disconnect();
            }
        } catch (IOException e) {
            //leaving this as error instead of fatal
            //the rest of the error response needs to be handled by the fail safe class
            //todo use Pterodactyl connection failsafe
            System.out.println("Could not connect to the Pterodactyl API for server with UUID " + configuration.getPterodactylServerUUID());
        }
    }
}
