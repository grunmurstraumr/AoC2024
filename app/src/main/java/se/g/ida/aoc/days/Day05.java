package se.g.ida.aoc.days;

import se.g.ida.aoc.common.FileReader;
import se.g.ida.aoc.common.HomogenousPair;
import se.g.ida.aoc.days.utils.day05.PrinterRule;
import se.g.ida.aoc.days.utils.day05.SortingRule;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static java.util.function.Predicate.not;

public class Day05 extends DefaultDay<Long> {
    List<PrinterRule> rules;
    List<List<Integer>> updates;

    public Day05(String inputFilename) {
        super(inputFilename);
        List<String> inputLines = FileReader.readInputFile(inputFilename);
        HomogenousPair<List<String>> sectionedInput = splitSections(inputLines);
        rules = parseRules(sectionedInput.getFirst());
        updates = parseInstructions(sectionedInput.getSecond());
    }

    private HomogenousPair<List<String>> splitSections(List<String> lines) {
        List<String> rules = new ArrayList<>();
        List<String> updates = new ArrayList<>();
        boolean rulesSection = true;
        for (String line : lines) {
            if (line.isBlank()) {
                rulesSection = false;
            } else if (rulesSection) {
                rules.add(line);
            } else {
                updates.add(line);
            }
        }
        return new HomogenousPair<>(rules, updates);
    }

    private List<PrinterRule> parseRules(List<String> lines) {
        return lines.stream()
                .map(line -> Stream.of(line.split("\\|"))
                        .map(Integer::valueOf).toList())
                .map(splitLine -> new PrinterRule(splitLine.get(0), splitLine.get(1)))
                .toList();
    }

    private List<List<Integer>> parseInstructions(List<String> lines) {
        return lines.stream().map(line -> Stream.of(line.split(",")).map(Integer::valueOf).toList()).toList();
    }

    @Override
    public Long runPart1() {
        var validUpdates = updates.stream().filter(not(this::hasRuleViolations)).toList();
        List<Integer> middleNumbers = getMiddleNumbers(validUpdates);
        return middleNumbers.stream().mapToLong(Integer::longValue).sum();
    }

    private boolean hasRuleViolations(List<Integer> updates) {
        for (PrinterRule rule : rules) {
            if (rule.violates(updates)) {
                return true;
            }
        }
        return false;
    }

    private List<Integer> getMiddleNumbers(List<List<Integer>> validUpdates) {
        List<Integer> middleNumbers = new ArrayList<>(validUpdates.size());
        for (List<Integer> updates : validUpdates) {
            middleNumbers.add(updates.get(updates.size() / 2));
        }
        return middleNumbers;
    }

    @Override
    public Long runPart2() {
        List<List<Integer>> violatingUpdates = new ArrayList<>(updates.stream().filter(this::hasRuleViolations).toList());
        List<Integer> middleNumbers = getCorrectMiddleNumbers(violatingUpdates);
        return middleNumbers.stream().mapToLong(Integer::longValue).sum();
    }

    private List<Integer> getCorrectMiddleNumbers(List<List<Integer>> violatingUpdates) {
        List<Integer> middleNumbers = new ArrayList<>();
        for (var item : violatingUpdates) {
            var mutableUpdates = new ArrayList<>(item);
            int middleIndex = mutableUpdates.size() / 2;
            mutableUpdates.sort(new SortingRule(rules));
            middleNumbers.add(mutableUpdates.get(middleIndex));
        }
        return middleNumbers;
    }
}
