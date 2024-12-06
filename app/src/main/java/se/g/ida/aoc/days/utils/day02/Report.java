package se.g.ida.aoc.days.utils.day02;

import lombok.AllArgsConstructor;
import se.g.ida.aoc.common.Pair;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@AllArgsConstructor
public class Report {
    private final List<Integer> values;

    private boolean isOutsideMinMaxThreshold(int a, int b) {
        int diff = Math.abs(a - b);
        return diff < 1 || 3 < diff;
    }

    private boolean isIncreasing(int a, int b) {
        return a < b;
    }

    private boolean isDecreasing(int a, int b) {
        return b < a;
    }

    public Violations<Integer> getViolations(boolean withProblemDamper){
        return getViolations(values, withProblemDamper);
    }
    public Violations<Integer> getViolations(List<Integer> report, boolean withProblemDamper) {
        Violations<Integer> violations = new Violations<>();
        for (int i = 1; i < report.size(); i++) {
            Integer a = report.get(i - 1);
            Integer b = report.get(i);
            Pair<Integer> offendingIndexes = Pair.of(i - 1, i);
            if (isOutsideMinMaxThreshold(a, b)) {
                violations.add(Violations.Type.DIFFERENCE_THRESHOLD, offendingIndexes);
            }
            if (isIncreasing(a, b)) {
                violations.add(Violations.Type.DECREASING_VIOLATION, offendingIndexes);
            }
            if (isDecreasing(a, b)) {
                violations.add(Violations.Type.INCREASING_VIOLATION, offendingIndexes);
            }
        }
        return !violations.isEmpty() && withProblemDamper ? tryProblemDampener(violations) : violations;

    }

    private Violations<Integer> tryProblemDampener(Violations<Integer> violations) {
        return violations.getViolatingItems().stream()
                .map(this::removeOffendingIndex)
                .map(modifiedReport -> getViolations(modifiedReport, false))
                .min(Comparator.comparing(Violations::size))
                .orElseThrow();
    }

    private List<Integer> removeOffendingIndex(int indexToRemove) {
        List<Integer> modifiedReport = new ArrayList<>(values);
        modifiedReport.remove(indexToRemove);
        return modifiedReport;
    }
}
