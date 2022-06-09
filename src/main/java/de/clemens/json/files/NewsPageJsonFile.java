package de.clemens.json.files;

import lombok.Getter;

@Getter
public class NewsPageJsonFile {

    private final String title1 = "Fisch ist ertrunken!";
    private final String preview1 = "images/preview1.png";
    private final String fileLink1 = "127.0.0.1/home";

    private final String title2 = "Neu Schülerzeitung";
    private final String preview2 = "\"images/preview2.png\"";
    private final String fileLink2 = "127.0.0.1/page1";

    private final String title3 = "unused";
    private final String preview3 = "unused";
    private final String fileLink3 = "unused";

}
