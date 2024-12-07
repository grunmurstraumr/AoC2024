package se.g.ida.aoc.days.utils.day07;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.LinkedList;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ComputationGraph {

    ComputationNode root;

    @Getter
    private int deapth = 0;


    public static ComputationGraph build(List<Long> operands, long targetValue) {
        ComputationGraph instance = new ComputationGraph();
        List<ComputationNode> previousNodes = new LinkedList<>();
        for (Long operand : operands) {
            instance.deapth++;

            List<ComputationNode> newNodes = new LinkedList<>();
            if (instance.root == null) {
                instance.root = new ComputationNode(operand);
                previousNodes.add(instance.root);
            }
            else {
                boolean foundTarget = false;
                for (var previousNode : previousNodes) {
                    for (OperatorPartOne operator : OperatorPartOne.values()) {
                        ComputationNode newNode = new ComputationNode(operand);
                        ComputationEdge edge = new ComputationEdge(previousNode, newNode, operator );
                        previousNode.addChild(newNode, operator);
                        newNodes.add(newNode);
                        if (instance.findInOrder(edge.getTarget(), targetValue)){
                            foundTarget = true;
                            break;
                        }
                    }
                    if (foundTarget)
                        break;
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
            if (node.hasChildren()) {
                for (ComputationEdge childEdge : node.getChildren()) {
                    boolean found = findInOrder(childEdge.getTarget(), value);
                    if (found) {
                        return true;
                    }
                }
                return false;
            }
            return node.getParentEdge().getValue() == value;
        }
        return false;
    }

}
