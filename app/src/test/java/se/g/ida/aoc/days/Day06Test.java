package se.g.ida.aoc.days;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Day06Test {
    String EXAMPLE_FILE = "06example.txt";
    String PUZZLE_INPUT = "06.txt";

    @Test
    void example() {
        Day<Long> day = new Day06(EXAMPLE_FILE);
        assertEquals(41, day.runPart1());
    }

    @Test
    void runPartOne() {
        Day<Long> day = new Day06(PUZZLE_INPUT);
        long answer = day.runPart1();
        assertEquals(5551, answer);
        System.out.println(answer);
    }

    @Test
    void examplePartTwo() {
        Day<Long> day = new Day06(EXAMPLE_FILE);
        assertEquals(6, day.runPart2());
    }

    @Test
    void runPartTwo() {
        Day<Long> day = new Day06(PUZZLE_INPUT);
        long answer = day.runPart2();
        assertEquals( 1939, answer);
        System.out.println(answer);
    }
}
