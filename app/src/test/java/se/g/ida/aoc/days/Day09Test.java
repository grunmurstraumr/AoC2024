package se.g.ida.aoc.days;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class Day09Test {

    String EXAMPLE_FILE = "09example.txt";
    String EXAMPLE_PT2_FILE = "09example_pt2.txt";
    String PUZZLE_INPUT = "09.txt";

    @Test
    void example() {
        Day<Long> day = new Day09(EXAMPLE_FILE);
        assertEquals(1928, day.runPart1());
    }

    @Test
    void runPartOne() {
        Day<Long> day = new Day09(PUZZLE_INPUT);
        long answer = day.runPart1();
//        assertTrue(229 > answer);
        System.out.println(answer);
    }

    @Test
    void examplePartTwo() {
        Day<Long> day = new Day09(EXAMPLE_FILE);
        assertEquals(2858, day.runPart2());
    }


    @Test
    void runPartTwo() {
        Day<Long> day = new Day09(PUZZLE_INPUT);
        long answer = day.runPart2();
//        assertEquals( 884, answer);
        System.out.println(answer);
    }
}
