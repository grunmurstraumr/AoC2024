package se.g.ida.aoc.days.utils.day03;

import lombok.Getter;
import se.g.ida.aoc.common.HomogenousPair;

import java.util.function.BinaryOperator;
import java.util.regex.Pattern;

public enum Operator {

    MULTIPLICATION("mul\\((\\d+),(\\d+)\\)", "\\d+", new Multiplication()),
    DONT("don\'t", "", null),
    DO("do[^n]", "", null);
    @Getter
    private final Pattern regex;
    @Getter
    private final Pattern operandMatcher;
    private final BinaryOperator<Long> operation;


    Operator(String regex, String operandMatcherPattern, BinaryOperator<Long> operation) {
        this.regex = Pattern.compile(regex);
        this.operandMatcher = Pattern.compile(operandMatcherPattern);
        this.operation = operation;
    }

    public long apply(HomogenousPair<Long> operands){
        return this.operation.apply(operands.getFirst(), operands.getSecond());
    }

    boolean hasOperation(){
        return this.operation != null;
    }

    public static final class Multiplication implements BinaryOperator<Long>{

        @Override
        public Long apply(Long operandA, Long opearandB) {
            return operandA * opearandB;
        }
    }
}
