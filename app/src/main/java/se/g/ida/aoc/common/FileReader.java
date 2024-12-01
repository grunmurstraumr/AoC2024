package se.g.ida.aoc.common;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collections;
import java.util.List;

public class FileReader {
    static public List<String> readInputFile(String filename) {
        try {
            Path path = Path.of(FileReader.class.getClassLoader().getResource(filename).toURI());
            return Files.readAllLines(path);
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
        }
        return Collections.emptyList();
    }
}
