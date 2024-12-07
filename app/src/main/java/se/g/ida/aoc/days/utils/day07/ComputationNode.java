package se.g.ida.aoc.days.utils.day07;

import lombok.Getter;
import se.g.ida.aoc.common.MutablePair;

public class ComputationNode {

    @Getter
    private final Long value;

    private ComputationEdge sourceEdge;
    private MutablePair<ComputationEdge> targetEdges;

    public ComputationNode(Long value) {
        this.value = value;
        targetEdges = new MutablePair<ComputationEdge>();
    }

    public ComputationNode addSourceEdge(ComputationEdge edge){
        this.sourceEdge = edge;
        return this;
    }

    public ComputationNode addLeftTargetEdge(ComputationEdge edge){
        this.targetEdges.setFirst(edge);
        return this;
    }

    public ComputationNode addLeftChild(ComputationNode childNode, Operator operator){
        ComputationEdge edge = new ComputationEdge(this, childNode, operator);
        childNode.sourceEdge = edge;
        this.targetEdges.setFirst(edge);
        return this;
    }

    public ComputationNode addRightTargetEdge(ComputationEdge edge){
        this.targetEdges.setSecond(edge);
        return this;
    }

    public ComputationNode addRightChild(ComputationNode childNode, Operator operator){
        ComputationEdge edge = new ComputationEdge(this, childNode, operator);
        childNode.sourceEdge = edge;
        this.targetEdges.setSecond(edge);
        return this;
    }

    public boolean hasParent(){
        return this.sourceEdge != null;
    }

    public ComputationEdge getParentEdge(){
        return this.sourceEdge;
    }

    public boolean hasLeftChild(){
        return this.targetEdges.getFirst() != null;
    }

    public ComputationEdge getLeftEdge(){
        return this.targetEdges.getFirst();
    }

    public boolean hasRightChild(){
        return this.targetEdges.getSecond() != null;
    }

    public ComputationEdge getRightEdge(){
        return this.targetEdges.getSecond();
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ComputationNode that = (ComputationNode) o;

        return value.equals(that.value);
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
