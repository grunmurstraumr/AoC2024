package se.g.ida.aoc.days;

import se.g.ida.aoc.days.utils.day07.Computation;
import se.g.ida.aoc.common.FileReader;
import se.g.ida.aoc.days.utils.day07.ComputationGraph;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.function.Predicate.not;

public class Day07 extends DefaultDay<Long> {
    List<Computation> computations;

    public Day07(String inputFilename) {
        super(inputFilename);
        computations = FileReader.readInputFile(inputFilename).stream().map(this::parseInputLines).toList();
    }

    private Computation parseInputLines(String line){
        String[] splitLine = line.split(":");
        long expectedResult = Long.parseLong(line.split(":")[0].strip());
        ComputationGraph graph = ComputationGraph.build(Arrays.stream(splitLine[1].split(" ")).filter(not(String::isBlank)).map(Long::valueOf).toList(), expectedResult);
        return new Computation(expectedResult, graph);
    }

    @Override
    public Long runPart1() {

        return computations.stream().filter(Computation::isValid).mapToLong(Computation::getResult).sum();
    }

    @Override
    public Long runPart2() {
        long sumOfValid
        return 0L;
    }
}
