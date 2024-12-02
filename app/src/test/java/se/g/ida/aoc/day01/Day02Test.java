package se.g.ida.aoc.day01;

import org.junit.jupiter.api.Test;
import se.g.ida.aoc.day02.Day02;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day02Test {

    @Test
    void exampleTest_2a(){
        Day02 day = new Day02();
        assertEquals(2, day.countSafeReports("02example.txt"));
    }

    @Test
    void run_2a(){
        Day02 day = new Day02();
        System.out.println(day.countSafeReports("02.txt"));
    }

    @Test
    void exampleTest_2b(){
        Day02 day = new Day02();
        assertEquals(4, day.countSafeReportsWithProblemDampener("02example.txt"));
    }

    @Test
    void run_2b(){
        Day02 day = new Day02();
        System.out.println(day.countSafeReportsWithProblemDampener("02.txt"));
    }
}
