package se.g.ida.aoc.common;

import se.g.ida.aoc.common.mapping.ColumnMapper;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

public class FileReader {
    public static List<String> readInputFile(String filename) {
        try {
            Path path = Path.of(FileReader.class.getClassLoader().getResource(filename).toURI());
            return Files.readAllLines(path);
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
        }
        return Collections.emptyList();
    }

    public static <T> List<List<T>> readInputFile(String filename, ColumnMapper<T> columnMapper) {
        try {
            Path path = Path.of(FileReader.class.getClassLoader().getResource(filename).toURI());
            return Files.readAllLines(path)
                        .stream()
                        .map(line -> Stream.of(line.split(" ")).map(columnMapper::mapFromString).toList())
                        .toList();

        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
        }
        return Collections.emptyList();
    }
}
