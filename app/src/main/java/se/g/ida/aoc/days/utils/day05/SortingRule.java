package se.g.ida.aoc.days.utils.day05;

import java.util.Collection;
import java.util.Comparator;

public class SortingRule implements Comparator<Integer> {
    Collection<PrinterRule> rules;

    public SortingRule(Collection<PrinterRule> rules) {
        this.rules = rules;
    }

    @Override
    public int compare(Integer o1, Integer o2) {
        if (rules.stream().anyMatch(rule -> rule.getBefore().equals(o1) && rule.getAfter().equals(o2))) {
            return -1;
        } else if (rules.stream().anyMatch(rule -> rule.getAfter().equals(o1) && rule.getBefore().equals(o2))) {
            return 1;
        }
        return 0;
    }
}
