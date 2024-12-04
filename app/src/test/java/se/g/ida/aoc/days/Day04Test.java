package se.g.ida.aoc.days;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Day04Test {
    String EXAMPLE_FILE = "04example.txt";
    String PUZZLE_INPUT = "04.txt";

    @Test
    void example() {
        Day<Long> day = new Day04(EXAMPLE_FILE);
        assertEquals(18, day.runPart1());
    }

    @Test
    void runPartOne() {
        Day<Long> day = new Day04(PUZZLE_INPUT);
        long answer = day.runPart1();
        assertTrue(2589 < answer);
        System.out.println(answer);
    }

    @Test
    void examplePartTwo() {
        Day<Long> day = new Day04(EXAMPLE_FILE);
        assertEquals(9, day.runPart2());
    }

    @Test
    void runPartTwo() {
        Day<Long> day = new Day04(PUZZLE_INPUT);
        long answer = day.runPart2();
        assertEquals( 2046, answer);
        System.out.println(answer);
    }
}
