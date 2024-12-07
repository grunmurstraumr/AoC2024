package se.g.ida.aoc.days.utils.day07;

import lombok.Value;

import java.util.List;


@Value
public class Computation{

    Long result;
    List<Long> operands;
    ComputationGraph graph;

    public boolean isValid(){
        return graph.findInOrder(result);
    }
}
