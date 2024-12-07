package se.g.ida.aoc.days.utils.day07;

import lombok.Getter;
import se.g.ida.aoc.common.MutablePair;

import java.util.ArrayList;
import java.util.List;

public class ComputationNode {

    @Getter
    private final Long value;

    private ComputationEdge sourceEdge;
    private List<ComputationEdge> targetEdges;

    public ComputationNode(Long value) {
        this.value = value;
        targetEdges = new ArrayList<>();
    }

    public ComputationNode addSourceEdge(ComputationEdge edge){
        this.sourceEdge = edge;
        return this;
    }

    public ComputationNode addChild(ComputationNode childNode, Operator operator) {
        ComputationEdge edge = new ComputationEdge(this, childNode, operator);
        if (!childNode.hasParent()) { // Sholud really also check that parent is this....
            childNode.addSourceEdge(edge);
        }
        this.targetEdges.add(edge);
        return this;
    }

    public boolean hasParent(){
        return this.sourceEdge != null;
    }

    public ComputationEdge getParentEdge(){
        return this.sourceEdge;
    }

    public boolean hasChildren(){
        return !this.targetEdges.isEmpty();
    }

    public List<ComputationEdge> getChildren(){
        return this.targetEdges;
    }

    public boolean hasLeftChild(){
        return this.targetEdges.size() > 0;
    }

    public ComputationEdge getLeftEdge(){
        return this.targetEdges.get(0);
    }

    public boolean hasRightChild(){
        return this.targetEdges.size() > 1;
    }

    public ComputationEdge getRightEdge(){
        return this.targetEdges.get(1);
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
