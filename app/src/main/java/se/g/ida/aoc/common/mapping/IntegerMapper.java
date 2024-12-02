package se.g.ida.aoc.common.mapping;

public class IntegerMapper implements ColumnMapper<Integer>{
    @Override
    public Integer mapFromString(String input) {
        return Integer.parseInt(input);
    }
}
