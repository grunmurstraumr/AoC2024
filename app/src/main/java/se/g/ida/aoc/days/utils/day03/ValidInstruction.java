package se.g.ida.aoc.days.utils.day03;

import lombok.Getter;
import se.g.ida.aoc.common.HomogenousPair;

import java.util.regex.MatchResult;

public class ValidInstruction {
    @Getter
    private Operator operator;
    private final HomogenousPair<Long> operands;
    @Getter
    private final int index;
    @Getter
    private final int line;

    public ValidInstruction(Operator operator, int index, int line, String instructionToParse) {
        this.operator = operator;
        this.index = index;
        this.line = line;
        this.operands = extractOperands(operator, instructionToParse);
    }

    private HomogenousPair<Long> extractOperands(Operator operator, String instructionToParse) {
        if (operator.hasOperation()) {
            return new HomogenousPair<>(operator.getOperandMatcher().matcher(instructionToParse).results().map(MatchResult::group).map(Long::valueOf).toList());
        }
        return new HomogenousPair<>(0L, 0L);
    }

    public long compute() {

        return operator.apply(operands);
    }

}
