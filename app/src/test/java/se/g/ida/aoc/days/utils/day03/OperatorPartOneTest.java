package se.g.ida.aoc.days.utils.day03;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.regex.MatchResult;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class OperatorPartOneTest {

    @Test
    void patternMatchingTest(){
        String input = "xmul(2,4)%&mul[3,7]!@^do_not_mul(5,5)+mul(32,64]then(mul(11,8)mul(8,5))";
        List<String> l = Operator.MULTIPLICATION.getRegex().matcher(input).results().map(m -> m.group()).toList();
        l.forEach(System.out::println);
    }

    @Test
    void dontPatternMatchingTest(){
        String input = "xmul(2,4)&mul[3,7]!^don't()_mul(5,5)+mul(32,64](mul(11,8)undo()?mul(8,5))";
        var result = Operator.DONT.getRegex().matcher(input).results().map(MatchResult::start).findFirst().orElseThrow();
        assertEquals(20, result);
    }

    @Test
    void doDoesntMatchDont(){
        String input = "xmul(2,4)&mul[3,7]!^don't()_mul(5,5)+mul(32,64](mul(11,8)undo()?mul(8,5))";
        var result = Operator.DO.getRegex().matcher(input).results().map(MatchResult::start).findFirst().orElseThrow();
        assertEquals(59, result);
    }
}
