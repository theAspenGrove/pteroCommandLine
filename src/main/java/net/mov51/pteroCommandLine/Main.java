package net.mov51.pteroCommandLine;

import java.io.IOException;

import static net.mov51.pteroCommandLine.argParser.parse;
import static net.mov51.pteroCommandLine.helpers.fileHelper.defaultConfig;

public class Main {

    public static void main(String[] args) throws IOException {
//        print working dir
        System.out.println(System.getProperty("user.dir"));
        defaultConfig();
        parse(args);
    }

}
