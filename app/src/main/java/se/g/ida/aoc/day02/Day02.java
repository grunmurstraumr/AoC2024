package se.g.ida.aoc.day02;

import se.g.ida.aoc.common.FileReader;
import se.g.ida.aoc.common.mapping.IntegerMapper;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Stream;

public class Day02 {

    public long countSafeReports(String filename){
        List<List<Integer>> input = FileReader.readInputFile(filename, new IntegerMapper());
        return input.stream()
                .filter(report -> countViolations(report) == 0)
                .count();

    }

    public boolean isOutsideMinMaxThreshold(int a, int b){
        int diff = Math.abs(a - b);
        return diff < 1 || 3 < diff;
    }

    public boolean isIncreasing(int a, int b){
        return a < b;
    }

    public boolean isDecreasing(int a, int b){
        return b < a;
    }

    public long countSafeReportsWithProblemDampener(String filename){
        List<List<Integer>> input = FileReader.readInputFile(filename, new IntegerMapper());
        return input.stream()
                .filter(report -> countViolationsWithProblemDampener(report) == 0)
                .count();
    }


    int totalViolations (Map<ViolationType, List<List<Integer>>> violations){
        int minMaxViolations = violations.get(ViolationType.DIFF).size();

        int incDecViolations = Math.min(violations.get(ViolationType.NON_INC).size(), violations.get(ViolationType.NON_DEC).size());

        return incDecViolations + minMaxViolations;
    }

    int countViolations(List<Integer> splitReport){
        var violations = findViolations(splitReport);
        return totalViolations(violations);
    }

    int countViolationsWithProblemDampener(List<Integer> splitReport){
        var violations = findViolations(splitReport);
        int total = totalViolations(violations);
        if ( total != 0){
            total = tryProblemDampener(splitReport, violations);
        }
        return total;
    }
    private enum ViolationType {
        DIFF,NON_INC, NON_DEC;
    }

    private int tryProblemDampener(List<Integer> report, Map<ViolationType, List<List<Integer>>> violations){
        Set<Integer> offendingIndexes = new HashSet<>();
        for (var violation : violations.get(ViolationType.DIFF)) {
            offendingIndexes.addAll(violation);
        }
        if (violations.get(ViolationType.NON_INC).size() < violations.get(ViolationType.NON_DEC).size()){
            for (var violation : violations.get(ViolationType.NON_INC)) {
                offendingIndexes.addAll(violation);
            }
        }
        else {
            for (var violation : violations.get(ViolationType.NON_DEC)) {
                offendingIndexes.addAll(violation);
            }
        }
        List<Map<ViolationType, List<List<Integer>>>> newVIolations = new ArrayList<>();
        for (int index : offendingIndexes){
            List<Integer>modifiedReport = new ArrayList<>(report);
            modifiedReport.remove(index);
            newVIolations.add(findViolations(modifiedReport));
        }
        return newVIolations.stream().map(this::totalViolations).min(Integer::compareTo).get();
    }


    private Map<ViolationType, List<List<Integer>>> findViolations(List<Integer> splitReport){
        Map<ViolationType, List<List<Integer>>> violations = new EnumMap<>(ViolationType.class);
        Stream.of(ViolationType.values()).forEach(violationType -> violations.put(violationType, new ArrayList<>()));
        for (int i = 1; i < splitReport.size(); i++){
            Integer a = splitReport.get(i - 1);
            Integer b = splitReport.get(i);
            var offendingIndexes = List.of(i-1, i);
            if (isOutsideMinMaxThreshold(a, b)) {
                violations.get(ViolationType.DIFF).add(offendingIndexes);
            }
            if (isIncreasing(a, b)){
                violations.get(ViolationType.NON_INC).add(offendingIndexes);
            }
            if (isDecreasing(a, b)) {
                violations.get(ViolationType.NON_DEC).add(offendingIndexes);
            }
        }
        return violations;
    }

    int increasingCount(List<Integer> splitReport){
        int violations = 0;
        for (int i = 1; i < splitReport.size(); i++){
            if (splitReport.get(i-1) > splitReport.get(i)) {
                violations++;
            }
        }
        return violations;
    }



    int decreasingCount(List<Integer> splitReport){
        int violations = 0;
        for (int i = 1; i < splitReport.size(); i++){
            if (splitReport.get(i-1) < splitReport.get(i)) {
                violations++;
            }
        }
        return violations;
    }

    int minMaxViolationCount(List<Integer> splitReport){
        int violations = 0;
        for (int i = 1; i < splitReport.size(); i++){
            int diff = Math.abs(splitReport.get(i-1) - splitReport.get(i));
            if (diff < 1 || 3 < diff){
                violations++;
            }
        }
        return violations;
    }
}
