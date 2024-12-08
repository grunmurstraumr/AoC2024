package se.g.ida.aoc.days.utils.day02;

import se.g.ida.aoc.common.HomogenousPair;

import java.util.*;
import java.util.stream.Stream;

public class Violations<E>{
    public enum Type{DIFFERENCE_THRESHOLD, INCREASING_VIOLATION, DECREASING_VIOLATION}
    List<HomogenousPair<E>> differenceThresholdViolations;
    List<HomogenousPair<E>> increasingViolations;
    List<HomogenousPair<E>> decreasingViolations;

    public Violations() {
        differenceThresholdViolations = new ArrayList<>();
        increasingViolations = new ArrayList<>();
        decreasingViolations = new ArrayList<>();
    }

    public void add(Type violationType, HomogenousPair<E> violatingIndexes){
        switch(violationType){
            case DIFFERENCE_THRESHOLD -> differenceThresholdViolations.add(violatingIndexes);
            case INCREASING_VIOLATION -> increasingViolations.add(violatingIndexes);
            case DECREASING_VIOLATION -> decreasingViolations.add(violatingIndexes);
        }
    }

    public Set<E> getViolatingItems(){
        Set<E> violatingItems = new HashSet<>();
        differenceThresholdViolations.forEach(violatingItems::addAll);
        Stream.of(increasingViolations, decreasingViolations).min(Comparator.comparing(List::size))
                .orElseThrow().forEach(violatingItems::addAll);
        return violatingItems;
    }

    public int size(){
        return differenceThresholdViolations.size() +
                Stream.of(increasingViolations, decreasingViolations).mapToInt(List::size).min().orElse(0);
    }

    public boolean isEmpty(){
        return this.size() == 0;
    }
}
