package se.g.ida.aoc.common.mapping;

public class StringMapper implements ColumnMapper<String>{
    @Override
    public String mapFromString(String input) {
        return input;
    }
}
