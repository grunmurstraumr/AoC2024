package se.g.ida.aoc.common;

import se.g.ida.aoc.common.mapping.ColumnMapper;
import se.g.ida.aoc.common.mapping.StringMapper;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collections;
import java.util.List;

public class FileReader {
    public static List<String> readInputFile(String filename) {
        return readInputFile(filename, new StringMapper());
    }

    public static <T> List<T> readInputFile(String filename, ColumnMapper<T> columnMapper) {
        try {
            Path path = Path.of(FileReader.class.getClassLoader().getResource(filename).toURI());
            return Files.readAllLines(path)
                    .stream()
                    .map(columnMapper::mapFromString).toList();

        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
        }
        return Collections.emptyList();
    }
}
