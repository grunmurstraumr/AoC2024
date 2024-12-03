package se.g.ida.aoc.common.mapping;

public class LongMapper implements LineMapper<Long> {
    @Override
    public Long mapFromString(String input, int line) {
        return Long.valueOf(input);
    }
}
