package se.g.ida.aoc.common.mapping;

import java.util.List;
import java.util.stream.Stream;

public class IntegerListMapper implements LineMapper<List<Integer>> {
    @Override
    public List<Integer> mapFromString(String input, int line) {
        return Stream.of(input.split(" ")).map(String::strip)
                .map(Integer::parseInt).toList();
    }
}
