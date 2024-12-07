package se.g.ida.aoc.days.utils.day07;

import lombok.AllArgsConstructor;

import java.util.function.BinaryOperator;

@AllArgsConstructor
public enum Operator {
    MULTIPLY(new Multiplication()), ADD(new Addition()), CONCAT(new Concat());

    private BinaryOperator<Long> operation;

    public long apply(long a, long b){
        return this.operation.apply(a, b);
    }

    public static final class Multiplication implements BinaryOperator<Long> {

        @Override
        public Long apply(Long operandA, Long opearandB) {
            return operandA * opearandB;
        }
    }

    public static final class Addition implements BinaryOperator<Long> {

        @Override
        public Long apply(Long operandA, Long opearandB) {
            return operandA + opearandB;
        }
    }

    public static final class Concat implements BinaryOperator<Long> {

        @Override
        public Long apply(Long operandA, Long opearandB) {
            return Long.valueOf(operandA.toString() + opearandB.toString());
        }
    }
}
