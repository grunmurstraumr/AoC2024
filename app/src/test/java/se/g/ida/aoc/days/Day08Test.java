package se.g.ida.aoc.days;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Day08Test {


    String EXAMPLE_FILE = "08example.txt";
    String EXAMPLE_PT2_FILE = "08example_pt2.txt";
    String PUZZLE_INPUT = "08.txt";

    @Test
    void example() {
        Day<Long> day = new Day08(EXAMPLE_FILE);
        assertEquals(14, day.runPart1());
    }

    @Test
    void runPartOne() {
        Day<Long> day = new Day08(PUZZLE_INPUT);
        long answer = day.runPart1();
        assertTrue(229 > answer);
        System.out.println(answer);
    }

    @Test
    void examplePartTwo_T_example() {
        Day<Long> day = new Day08(EXAMPLE_PT2_FILE);
        assertEquals(9, day.runPart2());
    }
    @Test
    void examplePartTwo() {
        Day<Long> day = new Day08(EXAMPLE_FILE);
        assertEquals(34, day.runPart2());
    }


    @Test
    void runPartTwo() {
        Day<Long> day = new Day08(PUZZLE_INPUT);
        long answer = day.runPart2();
        assertEquals( 884, answer);
        System.out.println(answer);
    }

}
