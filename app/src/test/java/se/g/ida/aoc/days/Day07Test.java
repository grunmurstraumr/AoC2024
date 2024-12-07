package se.g.ida.aoc.days;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Day07Test {

    String EXAMPLE_FILE = "07example.txt";
    String PUZZLE_INPUT = "07.txt";

    @Test
    void example() {
        Day<Long> day = new Day07(EXAMPLE_FILE);
        assertEquals(3749, day.runPart1());
    }

    @Test
    void runPartOne() {
        Day<Long> day = new Day07(PUZZLE_INPUT);
        long answer = day.runPart1();
        assertEquals(303766880536L, answer);
        System.out.println(answer);
    }

    @Test
    void examplePartTwo() {
        Day<Long> day = new Day07(EXAMPLE_FILE);
        assertEquals(6, day.runPart2());
    }

    @Test
    void runPartTwo() {
        Day<Long> day = new Day07(PUZZLE_INPUT);
        long answer = day.runPart2();
        assertEquals( 1939, answer);
        System.out.println(answer);
    }
}
