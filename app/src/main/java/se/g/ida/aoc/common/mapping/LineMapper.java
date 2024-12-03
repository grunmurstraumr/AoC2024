package se.g.ida.aoc.common.mapping;

public interface LineMapper<E> {

    E mapFromString(String input, int line);
}
