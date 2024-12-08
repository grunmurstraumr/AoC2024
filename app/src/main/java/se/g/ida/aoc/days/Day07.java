package se.g.ida.aoc.days;

import se.g.ida.aoc.common.Pair;
import se.g.ida.aoc.days.utils.day07.Computation;
import se.g.ida.aoc.common.FileReader;
import se.g.ida.aoc.days.utils.day07.ComputationGraph;
import se.g.ida.aoc.days.utils.day07.Operator;

import java.util.*;

import static java.util.function.Predicate.not;

public class Day07 extends DefaultDay<Long> {
    List<String> input;

    public Day07(String inputFilename) {
        super(inputFilename);
        input = FileReader.readInputFile(inputFilename);
    }

    @Override
    public Long runPart1() {
        List<Computation> computations = input.stream().map(line -> parseInputLines(line, List.of(Operator.ADD, Operator.MULTIPLY))).toList();
        return computations.stream().filter(Computation::isValid).mapToLong(Computation::getResult).sum();
    }

    private Computation parseInputLines(String line, List<Operator> operators){
        String[] splitLine = line.split(":");
        long expectedResult = Long.parseLong(line.split(":")[0].strip());
        List<Long> operands = Arrays.stream(splitLine[1].split(" ")).filter(not(String::isBlank)).map(Long::valueOf).toList();
        ComputationGraph graph = ComputationGraph.build(operands, expectedResult, operators);
        return new Computation(expectedResult,operands, graph);
    }

    @Override
    public Long runPart2(){
        List<Computation> computations = input.stream().map(line -> parseInputLines(line, List.of(Operator.ADD, Operator.MULTIPLY))).toList();
        Map<Boolean, List<Computation>> computationMapping = new HashMap();
        computationMapping.put(true, new LinkedList<>());
        computationMapping.put(false, new LinkedList<>());
        for (Computation computation : computations){
            if (computation.isValid()) {
                computationMapping.get(true).add(computation);
            }
            else {
                computationMapping.get(false).add(computation);
            }
        }
        long sum = computationMapping.get(true).stream().filter(Computation::isValid).mapToLong(Computation::getResult).sum();

        var computationsToRedo = computationMapping.get(false);
        // Set map to null to free memory
        computationMapping = null;
        sum += computationsToRedo.stream()
                .map(computation -> new Computation(computation.getResult(), computation.getOperands(),
                        ComputationGraph.build(computation.getOperands(), computation.getResult(), List.of(Operator.values()))))
                .filter(Computation::isValid).mapToLong(Computation::getResult).sum();

        return sum;
    }

}
