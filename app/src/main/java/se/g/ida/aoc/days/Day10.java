package se.g.ida.aoc.days;

import lombok.AllArgsConstructor;
import lombok.Getter;
import se.g.ida.aoc.common.FileReader;
import se.g.ida.aoc.common.spatial.Coordinate;
import se.g.ida.aoc.common.spatial.Direction;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

import static se.g.ida.aoc.days.Day10.LocationType.PEAK;
import static se.g.ida.aoc.days.Day10.LocationType.TRAILHEAD;

public class Day10 extends DefaultDay<Long> {

    @Getter
    @AllArgsConstructor
    public enum LocationType {
        TRAILHEAD(0), PEAK(9);

        private int value;
    }

    List<List<Integer>> elevationMap;

    protected Day10(String inputFilename) {
        super(inputFilename);

        elevationMap = FileReader.readInputFile(inputFilename).stream()
                .map(line -> Arrays.stream(line.split(""))
                        .map(this::convertToInt).toList())
                .toList();

    }

    Integer convertToInt(String s){
        try {
            return Integer.valueOf(s);
        } catch(Exception e){
            return Integer.valueOf(-1);
        }
    }

    @Override
    public Long runPart1() {
        var locations = finaPotentialTrailheads(elevationMap);
        Map<Coordinate, Integer> trailheadValues = locations.get(TRAILHEAD).stream()
                .collect(Collectors.toMap(Function.identity(), trailhead -> findReachablePeaks(trailhead, 0, locations.get(PEAK), new HashSet<>(), new HashSet<>()).size()));
        return (long) trailheadValues.values().stream().mapToInt(Integer::intValue).sum();
    }


    private Map<LocationType, Set<Coordinate>> finaPotentialTrailheads(List<List<Integer>> elevationMap) {
        Set<Coordinate> trailheads = new HashSet<>();
        Set<Coordinate> peaks = new HashSet<>();
        for (int row = 0; row < elevationMap.size(); row++) {
            for (int column = 0; column < elevationMap.get(row).size(); column++) {
                Integer value = elevationMap.get(row).get(column);
                if (value.equals(TRAILHEAD.getValue())) {
                    trailheads.add(new Coordinate(column, row));
                } else if (value.equals(PEAK.getValue())) {
                    peaks.add(new Coordinate(column, row));
                }
            }
        }
        return Map.of(TRAILHEAD, trailheads,
                PEAK, peaks);
    }

    <T extends Collection<Coordinate>> T findReachablePeaks(Coordinate currentPosition, int lastValue, Set<Coordinate> peaks, T collector, Set<Coordinate> visitedNodes) {
        for (Direction direction : Direction.values()) {
            Coordinate next = currentPosition.add(direction.getMovement());
            if (checkNext(next, lastValue)) {
                if (peaks.contains(next)) {
                    collector.add(next);
                } else {
                    if (visitedNodes.contains(next)) {
                        continue;
                    }
                    int nextValue = elevationMap.get(next.getY()).get(next.getX());
                    visitedNodes.add(next);
                    collector.addAll(findReachablePeaks(next, nextValue, peaks, collector, visitedNodes));
                }
            }
        }
        return collector;
    }

    Map<Coordinate, Integer> countReachableTrailheads(Coordinate currentPosition, int lastValue, Map<Coordinate, Integer> previousCounts, Set<Coordinate> trailheads) {
        for (Direction direction : Direction.values()) {
            Coordinate next = currentPosition.add(direction.getMovement());
            if (checkNextReversed(next, lastValue)) {
                if (trailheads.contains(next)) {
                    previousCounts.putIfAbsent(next, 0);
                    previousCounts.compute(next, (key, val) -> ++val);
                } else {
                    int nextValue = elevationMap.get(next.getY()).get(next.getX());
                    countReachableTrailheads(next, nextValue, previousCounts, trailheads);
                }
            }
        }
        return previousCounts;
    }

    boolean checkNext(Coordinate next, int lastValue) {
        return next.getY() >= 0 &&
                next.getX() >= 0 &&
                next.getY() < elevationMap.size() &&
                next.getX() < elevationMap.get(next.getY()).size() &&
                elevationMap.get(next.getY()).get(next.getX()) - lastValue == 1;
    }

    boolean checkNextReversed(Coordinate next, int lastValue) {
        return next.getY() >= 0 &&
                next.getX() >= 0 &&
                next.getY() < elevationMap.size() &&
                next.getX() < elevationMap.get(next.getY()).size() &&
                lastValue - elevationMap.get(next.getY()).get(next.getX()) == 1;
    }

    @Override
    public Long runPart2() {
        var locations = finaPotentialTrailheads(elevationMap);
        Map<Coordinate, Integer> counts = new HashMap<>();
        for (var peak : locations.get(PEAK)){
            countReachableTrailheads(peak, PEAK.getValue(), counts, locations.get(TRAILHEAD));
        }
        return (long) counts.values().stream().mapToInt(Integer::intValue).sum();
    }
}
