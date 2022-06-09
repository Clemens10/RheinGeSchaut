package de.clemens.json.files;

import de.clemens.json.files.link.PictureLink;
import lombok.Getter;
import lombok.Setter;

import java.io.File;
import java.util.List;

@Getter
@Setter
public class ArticleJsonFile {

    private String[] content;
    private List<PictureLink> linksForPictures;
    private String fileNameForHtml;

    private File getFile() {

        return null;
    }
}
