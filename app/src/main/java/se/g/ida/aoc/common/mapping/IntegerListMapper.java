package se.g.ida.aoc.common.mapping;

import java.util.List;
import java.util.stream.Stream;

public class IntegerListMapper implements ColumnMapper<List<Integer>>{
    @Override
    public List<Integer> mapFromString(String input) {
        return Stream.of(input.split(" ")).map(String::strip)
                .map(Integer::parseInt).toList();
    }
}
