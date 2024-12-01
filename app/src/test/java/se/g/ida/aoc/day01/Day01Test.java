package se.g.ida.aoc.day01;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day01Test {

    @Test
    void examplePart1Test(){
        Day01 day = Day01.fromFile("01example.txt");
        assertEquals(11, day.getTotalDistance());
    }

    @Test
    void runDay01Part1(){
        Day01 day = Day01.fromFile("01.txt");
        System.out.println(day.getTotalDistance());
    }

    @Test
    void examplePart2Test(){
        Day01 day = Day01.fromFile("01example.txt");
        assertEquals(31, day.getTotalSimilarityScore());
    }

    @Test
    void runDay01Part2(){
        Day01 day = Day01.fromFile("01.txt");
        System.out.println(day.getTotalSimilarityScore());
    }

}
