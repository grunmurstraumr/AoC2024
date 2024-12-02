package se.g.ida.aoc.days;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day02Test {
    public static final String EXAMPLE_FILE = "02example.txt";
    public static final String PROBLEM_INPUT = "02.txt";

    @Test
    void partOneExample(){
        Day<Long> day = new Day02(EXAMPLE_FILE);
        assertEquals(2, day.runPart1());
    }

    @Test
    void partOne(){
        Day<Long> day = new Day02(PROBLEM_INPUT);
        long countSafeReports = day.runPart1();
        System.out.println(countSafeReports);
    }

    @Test
    void partTwoExample(){
        Day<Long> day = new Day02(EXAMPLE_FILE);
        assertEquals(4, day.runPart2());
    }

    @Test
    void partTwo(){
        Day<Long> day = new Day02(PROBLEM_INPUT);
        long safeReportsCount = day.runPart2();
        System.out.println(safeReportsCount);
    }
}
