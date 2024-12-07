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
                    for (Operator operator : Operator.values()) {
                        ComputationNode leftNode = new ComputationNode(operand);
                        previousNode.addChild(leftNode, operator);
                        newNodes.add(leftNode);
                    }
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
            if (node.getChildren().size() > 1){
                boolean foundInLeftBranch = findInOrder(node.getChildren().get(0).getTarget(), value);
                return foundInLeftBranch ? foundInLeftBranch : findInOrder(node.getChildren().get(1).getTarget(), value);
            }
            else if (node.getChildren().size() == 2){
                return findInOrder(node.getRightEdge().getTarget(), value);
            }
            else if (node.getChildren().size() == 1){
                return findInOrder(node.getLeftEdge().getTarget(), value);
            }
            return node.getParentEdge().getValue() == value;
        }
        return false;
    }

}
