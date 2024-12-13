package se.g.ida.aoc.days;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Day11Test {

    String EXAMPLE_FILE = "11example.txt";
    String PUZZLE_INPUT = "11.txt";

    @Test
    void example() {
        Day<Long> day = new Day11(EXAMPLE_FILE);
        assertEquals(55312, day.runPart2());
    }

    @Test
    void runPartOne() {
        Day<Long> day = new Day11(PUZZLE_INPUT);
        long answer = day.runPart2();
        assertEquals(216996, answer);
        System.out.println(answer);
    }

    @Test
    void runPartTwo() {
        Day<Long> day = new Day11(PUZZLE_INPUT);
        long answer = day.runPart2();
        assertEquals( 257335372288947L, answer);
        System.out.println(answer);
    }
}
