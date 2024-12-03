package se.g.ida.aoc.common.mapping;

import se.g.ida.aoc.days.utils.day03.Operator;
import se.g.ida.aoc.days.utils.day03.ValidInstruction;

import java.util.List;
import java.util.regex.MatchResult;
import java.util.stream.Stream;

public class ValidMemoryMapper implements LineMapper<List<ValidInstruction>> {

    @Override
    public List<ValidInstruction> mapFromString(String input, int line) {
        return Stream.of(Operator.values())
                .map(operator -> operator.getRegex().matcher(input).results()
                        .map(match -> new ValidInstruction(operator, match.start(),line, match.group())).toList())
                .flatMap(List::stream)
                .toList();

    }




}
