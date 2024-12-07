package se.g.ida.aoc.days.utils.day07;

import lombok.Getter;

@Getter
public class ComputationEdge {
    private final ComputationNode source;
    private final ComputationNode target;
    private final Operator operator;
    public ComputationEdge(ComputationNode source, ComputationNode target, Operator operator) {
        this.source = source;
        this.target = target;
        this.operator = operator;
    }

    private Long compute(){
        Long leftOperand = source.hasParent() ? source.getParentEdge().getValue() : source.getValue();
        return operator.apply(leftOperand, target.getValue());
    }

    public long getValue() {
        return this.compute();
    }
}
