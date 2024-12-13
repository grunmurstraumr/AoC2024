package se.g.ida.aoc.days;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Day10Test {

    String EXAMPLE_FILE = "10example.txt";
    String PUZZLE_INPUT = "10.txt";

    @Test
    void example() {
        Day<Long> day = new Day10(EXAMPLE_FILE);
        assertEquals(36, day.runPart1());
    }

    @Test
    void runPartOne() {
        Day<Long> day = new Day10(PUZZLE_INPUT);
        long answer = day.runPart1();
        assertEquals(582, answer);
        System.out.println(answer);
    }

    @Test
    void examplePartTwo() {
        Day<Long> day = new Day10(EXAMPLE_FILE);
        assertEquals(81, day.runPart2());
    }

    @Test
    void examplePartTwoFirst() {
        Day<Long> day = new Day10("10examplePt2A.txt");
        assertEquals(3, day.runPart2());
    }


    @Test
    void runPartTwo() {
        Day<Long> day = new Day10(PUZZLE_INPUT);
        long answer = day.runPart2();
        assertEquals( 1302, answer);
        System.out.println(answer);
    }
}
