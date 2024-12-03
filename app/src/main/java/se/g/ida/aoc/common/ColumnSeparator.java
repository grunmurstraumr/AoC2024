package se.g.ida.aoc.common;

import se.g.ida.aoc.common.mapping.LineMapper;
import se.g.ida.aoc.common.mapping.StringMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static java.util.function.Predicate.not;

public class ColumnSeparator {
    public static <T> List<List<T>> separate(List<String> lines, LineMapper<T> columnMapper){
        List<List<T>> columns = new ArrayList<>();
        for (String line : lines){
            List<String> words = Stream.of(line.split("\\b")).filter(not(String::isBlank)).toList();
            for (int columnIndex = 0; columnIndex < words.size(); columnIndex++){
                if (columns.size() - 1 < columnIndex ) {
                    columns.add(new ArrayList<>(lines.size()));
                }
                T mappedValue = columnMapper.mapFromString(words.get(columnIndex).strip(), 0);
                columns.get(columnIndex).add(mappedValue);
            }
        }
        return columns;
    }

    public static <T> List<List<T>> separate(String input, LineMapper<T> columnMapper){
        String[] lines = input.split("\\n");
        return separate(List.of(lines), columnMapper);
    }

    public static List<List<String>> separate(String input){
        return separate(input, new StringMapper());
    }
}
