package se.g.ida.aoc.common.mapping;

public class StringMapper implements LineMapper<String> {
    @Override
    public String mapFromString(String input, int line) {
        return input;
    }
}
