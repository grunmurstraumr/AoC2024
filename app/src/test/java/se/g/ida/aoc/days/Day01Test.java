package se.g.ida.aoc.days;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day01Test {

    public static final String EXAMPLE_FILE = "01example.txt";
    public static final String PUZZLE_INPUT = "01.txt";

    @Test
    void examplePart1Test(){
        Day<Long> day = new Day01(EXAMPLE_FILE);
        assertEquals(11, day.runPart1());
    }

    @Test
    void runDay01Part1(){
        Day<Long> day = new Day01(PUZZLE_INPUT);
        System.out.println(day.runPart1());
    }

    @Test
    void examplePart2Test(){
        Day<Long> day = new Day01(EXAMPLE_FILE);
        assertEquals(31, day.runPart2());
    }

    @Test
    void runDay01Part2(){
        Day<Long> day = new Day01(PUZZLE_INPUT);
        System.out.println(day.runPart2());
    }

}
