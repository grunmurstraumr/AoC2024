package se.g.ida.aoc.common;

import se.g.ida.aoc.common.mapping.LineMapper;
import se.g.ida.aoc.common.mapping.StringMapper;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class FileReader {
    public static List<String> readInputFile(String filename) {
        return readInputFile(filename, new StringMapper());
    }

    public static <T> List<T> readInputFile(String filename, LineMapper<T> columnMapper) {
        try {
            Path path = Path.of(FileReader.class.getClassLoader().getResource(filename).toURI());
            List<T> mappedInput = new ArrayList<>();
            List<String> rawInput = Files.readAllLines(path);
            for (int i=0; i < rawInput.size(); i++){
                mappedInput.add(columnMapper.mapFromString(rawInput.get(i), i));
            }
            return mappedInput;

        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
        }
        return Collections.emptyList();
    }
}
