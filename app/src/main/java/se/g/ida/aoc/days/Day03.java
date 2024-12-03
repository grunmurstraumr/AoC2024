package se.g.ida.aoc.days;

import se.g.ida.aoc.common.FileReader;
import se.g.ida.aoc.common.mapping.ValidMemoryMapper;
import se.g.ida.aoc.days.utils.day03.Operator;
import se.g.ida.aoc.days.utils.day03.ValidInstruction;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;

public class Day03 extends DefaultDay<Long>{
    private List<List<ValidInstruction>> input;
    public Day03(String inputFilename) {
        super(inputFilename);
        input = FileReader.readInputFile(inputFilename, new ValidMemoryMapper());
    }

    @Override
    public Long runPart1() {
        return input.stream().mapToLong(line -> line.stream().mapToLong(ValidInstruction::compute).sum()).sum();
    }

    @Override
    public Long runPart2() {
        var instructions = input.stream().flatMap(List::stream)
                .sorted(Comparator.comparingInt(ValidInstruction::getLine).thenComparing(ValidInstruction::getIndex)).toList();
        List<ValidInstruction> prunedInstrutionList = removeDisabledOperations(instructions);
        return prunedInstrutionList.stream().mapToLong(ValidInstruction::compute).sum();
    }

    private List<ValidInstruction> removeDisabledOperations(List<ValidInstruction> sortedInstructionList){
        boolean enabled = true;
        List<ValidInstruction> prunedList = new ArrayList<>();
        for (ValidInstruction instruction : sortedInstructionList){
            switch (instruction.getOperator()){
                case Operator.DONT -> enabled=false;
                case Operator.DO -> enabled=true;
                default -> {
                    if (enabled) {
                        prunedList.add(instruction);
                    }
                }
            }
        }
        return prunedList;
    }
}
