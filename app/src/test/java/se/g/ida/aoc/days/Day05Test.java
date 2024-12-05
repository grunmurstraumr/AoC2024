package se.g.ida.aoc.days;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Day05Test {
    String EXAMPLE_FILE = "05example.txt";
    String PUZZLE_INPUT = "05.txt";

    @Test
    void example() {
        Day<Long> day = new Day05(EXAMPLE_FILE);
        assertEquals(143, day.runPart1());
    }

    @Test
    void runPartOne() {
        Day<Long> day = new Day05(PUZZLE_INPUT);
        long answer = day.runPart1();
        System.out.println(answer);
    }

    @Test
    void examplePartTwo() {
        Day<Long> day = new Day05(EXAMPLE_FILE);
        assertEquals(123, day.runPart2());
    }

    @Test
    void runPartTwo() {
        Day<Long> day = new Day05(PUZZLE_INPUT);
        long answer = day.runPart2();
        assertEquals( 4971, answer);
        System.out.println(answer);
    }
}
