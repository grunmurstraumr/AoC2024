package se.g.ida.aoc.common.mapping;

public interface ColumnMapper <E>{
    E mapFromString(String input);
}
