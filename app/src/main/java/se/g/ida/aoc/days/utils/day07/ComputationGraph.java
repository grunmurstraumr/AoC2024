package se.g.ida.aoc.days.utils.day07;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ComputationGraph {

    ComputationNode root;

    @Getter
    private int deapth = 0;


    public static ComputationGraph build(List<Long> operands) {
        ComputationGraph instance = new ComputationGraph();
        List<ComputationNode> previousNodes = new ArrayList<>();
        for (Long operand : operands) {
            instance.deapth++;

            List<ComputationNode> newNodes = new ArrayList<>();
            if (instance.root == null) {
                instance.root = new ComputationNode(operand);
                previousNodes.add(instance.root);
            }
            else {
                for (var previousNode : previousNodes) {
                    ComputationNode leftNode = new ComputationNode(operand);
                    previousNode.addChild(leftNode, Operator.ADD);
                    newNodes.add(leftNode);

                    ComputationNode rightNode = new ComputationNode(operand);
                    previousNode.addChild(rightNode, Operator.MULTIPLY);
                    newNodes.add(rightNode);
                }
                previousNodes = newNodes;
            }
        }
        return instance;
    }

    boolean findInOrder(long value){
        return findInOrder(root, value);
    }


    boolean findInOrder(ComputationNode node, long value){
        if (node != null){
            if (node.hasLeftChild() && node.hasRightChild()){
                boolean foundInLeftBranch = findInOrder(node.getLeftEdge().getTarget(), value);
                return foundInLeftBranch ? foundInLeftBranch : findInOrder(node.getRightEdge().getTarget(), value);
//                return node.getLeftEdge().getValue() == value;
            }
            else if (node.hasRightChild()){
                return findInOrder(node.getRightEdge().getTarget(), value);
                //return node.getRightEdge().getValue() == value;
            }
            else if (node.hasLeftChild()){
                return findInOrder(node.getLeftEdge().getTarget(), value);
            }
            return node.getParentEdge().getValue() == value;
        }
        return false;
    }

}
