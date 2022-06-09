package de.clemens;

import de.clemens.json.files.NewsPageJsonFile;
import de.clemens.json.jsonlib.JsonConfig;
import lombok.Getter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.File;
import java.util.Scanner;

@SpringBootApplication
public class Website {

    @Getter private static NewsPageJsonFile file;

    public static void main(String[] args) {

        createConfig();
        SpringApplication.run(Website.class, args);
        sendAscii();
    }


    private static void createConfig() {

        file = new JsonConfig(new File("newspage/config", "newspage.json"), c ->
                c.set("newspage", new NewsPageJsonFile())).get("newspage", NewsPageJsonFile.class);
    }


    public static void sendAscii() {

        System.out.println("[/../] enabling Website...");
        System.out.println("______ _          _         _____        _____      _                 _   \n" +
                           "| ___ \\ |        (_)       |  __ \\      /  ___|    | |               | |  \n" +
                           "| |_/ / |__   ___ _ _ __   | |  \\/ ___  \\ `--.  ___| |__   __ _ _   _| |_ \n" +
                           "|    /| '_ \\ / _ \\ | '_ \\  | | __ / _ \\  `--. \\/ __| '_ \\ / _` | | | | __|\n" +
                           "| |\\ \\| | | |  __/ | | | | | |_\\ \\  __/ /\\__/ / (__| | | | (_| | |_| | |_ \n" +
                           "\\_| \\_|_| |_|\\___|_|_| |_|  \\____/\\___| \\____/ \\___|_| |_|\\__,_|\\__,_|\\__|\n" +
                           " Entwickelt von Clemens Robrecht (development.clemens.robrecht@clemens.de)                                                                         \n" +
                           "                                                                          ");
        System.out.println("Schuelerzeitung des RGS");
        System.out.println("[+] Website enabled!");

        //Format for Console layout
        Scanner scanner = new Scanner(System.in);
        System.out.print("System: ");
        scanner.next();

    }
}
