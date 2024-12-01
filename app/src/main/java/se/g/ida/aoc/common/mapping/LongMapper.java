package se.g.ida.aoc.common.mapping;

public class LongMapper implements ColumnMapper<Long>{
    @Override
    public Long mapFromString(String input) {
        return Long.valueOf(input);
    }
}
