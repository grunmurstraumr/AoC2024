package se.g.ida.aoc.days;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Day03Test {
    String EXAMPLE_FILE = "03example.txt";
    String EXAMPLE_FILE_B = "03example_B.txt";
    String PUZZLE_INPUT = "03.txt";

    @Test
    void example() {
        Day<Long> day = new Day03(EXAMPLE_FILE);
        assertEquals(161, day.runPart1());
    }

    @Test
    void runPartOne() {
        Day<Long> day = new Day03(PUZZLE_INPUT);
        long answer = day.runPart1();
        System.out.println(answer);
    }

    @Test
    void examplePartTwo() {
        Day<Long> day = new Day03(EXAMPLE_FILE_B);
        assertEquals(48, day.runPart2());
    }

    @Test
    void runPartTwo() {
        Day<Long> day = new Day03(PUZZLE_INPUT);
        long answer = day.runPart2();
        assertTrue(answer < 106266128);
        System.out.println(answer);
    }
}
